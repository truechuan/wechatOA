package com.leyidai.web.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.leyidai.entity.Dictionary;
import com.leyidai.entity.OrderRecordTemp;
import com.leyidai.entity.OrderRecords;
import com.leyidai.entity.RecordType;
import com.leyidai.web.mapper.AreaMapper;
import com.leyidai.web.mapper.BookControlMapper;
import com.leyidai.web.mapper.OrderRecordsMapper;
import com.leyidai.web.mapper.TypeLimitRecordMapper;
import com.leyidai.web.schedule.WorkerScheduler;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.WeChatModelUtil;

@Service
@Transactional(readOnly = true)
public class OrderRecordsService {
	@Autowired
	private OrderRecordsMapper recordsMapper;
	@Autowired
	private BookControlMapper bookControlMapper;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private TypeLimitRecordMapper typeLimitRecordMapper;
	@Autowired
	private AreaMapper areaMapper;

	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(WorkerScheduler.class);

	@Transactional
	public int save(OrderRecords record, boolean flagIsQueue,
			RecordType recordType) {
		if (record.getId() == 0) {
			if (record.getStatus() != 2) {
				record.setStatus(1);
			}
			// 如果排队区县里预约最新一天数据，则进入排队
			if (flagIsQueue) {
				recordsMapper.insertTempByOrder(record);
				int insertId = (int) record.getId();
				if (insertId > 1) {
					invokePushMessage(record, flagIsQueue);
					return insertId;
				}
				return 0;
			} else {
				synchronized (this) {
					// 要更新recortype的数值+1
					int insertId = typeLimitRecordMapper
							.updateOrderednum(recordType);
					if (insertId > 0) {
						// 要更新bookcontrol的数值+1
						insertId = bookControlMapper.update(
								record.getTransactDate(),
								String.valueOf(record.getTransactTimeId()),
								record.getTransactOrgId());
						if (insertId > 0) {
							// 插入record
							recordsMapper.insert(record);
							insertId = (int) record.getId();
							// 如果没有更新成功，则返回0
							if (insertId <= 0) {
								return 0;
							} else {
								invokePushMessage(record, flagIsQueue);
								return insertId;
							}
						} else {
							// 如果没有更新成功，则返回0
							return 0;
						}
					} else {
						return 0;
					}
				}
			}
		} else {
			return recordsMapper.update(record);
		}
	}

	/**
	 * 执行发送微信提示消息 处理status=1或2的情况，处理是否为队列的情况
	 * 
	 * @param orderModel
	 * @param flagQueue
	 */
	public void invokePushMessage(OrderRecords orderModel, boolean flagQueue) {
		String rtnUrl = "http://www.tjsbdcdjzx.com/static/"
				+ orderModel.getTransactTypeId() + "/noticeInfo.html";
		String date = orderModel.getTransactDate() + " "
				+ orderModel.getTransactTime();
		Map<String, String> errmsg = null;
		if (orderModel.getStatus() == 2) {
			String remark = "\n备注：请您持身份证及业务办理所需材料在"
					+ orderModel.getTransactTime() + "完成取号，办理详情请点击查看";
			errmsg = WeChatModelUtil.pushSuccessMessage(orderModel.getOpenid(),
					rtnUrl, orderModel.getName(),
					orderModel.getTransactTypeName(), date,
					orderModel.getTransactOrgName(), remark);
		} else if (flagQueue) {
			String remark = "\n备注：预约信息已提交，预约排队中.......";
			errmsg = WeChatModelUtil.pushStatusMessage(orderModel.getOpenid(),
					rtnUrl, orderModel.getName(),
					orderModel.getTransactTypeName(), date,
					orderModel.getTransactOrgName(), remark, "排队中");
		} else {
			String remark = "\n备注：预约信息已提交审核，请耐心等待！";
			errmsg = WeChatModelUtil.pushMessage(orderModel.getOpenid(),
					rtnUrl, orderModel.getName(),
					orderModel.getTransactTypeName(), date,
					orderModel.getTransactOrgName(), remark);
		}
		log.info(String.valueOf(errmsg.toString()));
	}

	@Transactional
	public String saveForTencent(OrderRecords record, boolean flagIsQueue) {
		Map<String, String> rtnmap = null;
		if (record.getId() == 0) {
			if (record.getStatus() != 2) {
				record.setStatus(1);
			}
			if (!flagIsQueue) {
				String remark = "\n备注：预约信息已提交审核，请耐心等待！";
				String rtnUrl = "http://www.tjsbdcdjzx.com/static/"
						+ record.getTransactTypeId() + "/noticeInfo.html";
				String date = record.getTransactDate() + " "
						+ record.getTransactTime();
				rtnmap = WeChatModelUtil.pushMessage(record.getOpenid(),
						rtnUrl, record.getName(), record.getTransactTypeName(),
						date, record.getTransactOrgName(), remark);
				log.info(String.valueOf(rtnmap.get("errcode"))
						+ rtnmap.get("result_page_url"));
			} else {
				record.setStatus(10);
			}
			recordsMapper.insert(record);

		} else {
			recordsMapper.update(record);
		}
		if (rtnmap != null)
			return rtnmap.get("result_page_url");
		else {
			return null;
		}
	}

	@Transactional
	public boolean updateBookControl(OrderRecords orderRecords,
			boolean isMinusNum) {
		// 更新预约时间表
		/***************** 许云强 2016-09-16 start **********************/
		// 如果notReturnNum为真，
		if (isMinusNum) {
			int count = bookControlMapper.updateCancel(
					orderRecords.getTransactDate(),
					orderRecords.getTransactTimeId(),
					orderRecords.getTransactOrgId());
			if (count == 0) {
				count = bookControlMapper.updateCancel2(
						orderRecords.getTransactDate(),
						orderRecords.getTransactTime(),
						orderRecords.getTransactOrgId());
				if (count <= 0)
					return false;
			}
		} else {
			int count = bookControlMapper.updateCancel1(
					orderRecords.getTransactDate(),
					orderRecords.getTransactTimeId(),
					orderRecords.getTransactOrgId());
			if (count == 0) {
				count = bookControlMapper.updateCancel12(
						orderRecords.getTransactDate(),
						orderRecords.getTransactTime(),
						orderRecords.getTransactOrgId());
				if (count <= 0)
					return false;
			}
		}

		/***************** 许云强 2016-09-16 end **********************/
		return true;
	}

	/**
	 * 根据openId查询当天预约信息
	 * 
	 * @param record
	 * @return
	 */
	public List<OrderRecords> selectByOpenIdAndToady(String openId) {
		return recordsMapper.selectByOpenIdAndToady(openId);
	}

	/**
	 * 根据openId待处理预约
	 * 
	 * @param record
	 * @return
	 */
	public List<OrderRecords> selectByOpenIdAndStatus(String openId) {
		return recordsMapper.selectByOpenIdAndStatus(openId);
	}

	/**
	 * 根据openId待处理预约的temp表
	 * 
	 * @param record
	 * @return
	 */
	public List<OrderRecordTemp> selectByOpenIdAndFlushOver(String openId) {
		return recordsMapper.selectByOpenIdAndFlushOver(openId);
	}

	/**
	 * 根据openId待处理预约 fast
	 * 
	 * @param record
	 * @return
	 */
	public List<OrderRecords> selectByOpenIdAndStatusFast(String openId) {
		return recordsMapper.selectByOpenIdAndStatusFast(openId);
	}

	/**
	 * 根据openid 查询用户全部预约
	 * 
	 * @param openid
	 * @return
	 */
	public List<OrderRecords> selectByOpenid(String openid) {

		return recordsMapper.selectByOpenid(openid);
	}

	/**
	 * 根据openid 查询用户Temp表的全部预约
	 * 
	 * @param openid
	 * @return
	 */
	public List<OrderRecordTemp> selectTempByOpenid(String openid) {

		return recordsMapper.selectTempByOpenid(openid);
	}

	public OrderRecords selectById(long id) {

		return recordsMapper.selectById(id);
	}
}
