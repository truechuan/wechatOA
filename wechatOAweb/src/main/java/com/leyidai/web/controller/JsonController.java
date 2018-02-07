package com.leyidai.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leyidai.web.weChat.ConstantWeChat;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyidai.entity.AddOrder;
import com.leyidai.entity.OrderRecords;
import com.leyidai.entity.RecordTypeViewNotice;
import com.leyidai.entity.User;
import com.leyidai.web.mapper.BookControlMapper;
import com.leyidai.web.mapper.NoticeMapper;
import com.leyidai.web.mapper.OrderRecordsMapper;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.service.NoticeService;
import com.leyidai.web.service.OrderRecordsService;
import com.leyidai.web.service.RecordTypeService;
import com.leyidai.web.service.UserService;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.WeChatModelUtil;
import com.leyidai.web.util.DateUtil.DateFormat;
import com.leyidai.web.weChat.ConstantWeChat;

/**
 * 与硬件对接 Controller
 * 
 * @author Administrator
 * 
 */
@Controller
public class JsonController {
	private static final Logger log = LoggerFactory
			.getLogger(JsonController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private OrderRecordsService orderRecordsService;
	@Autowired
	private OrderRecordsMapper recordsMapper;
	@Autowired
	private BookControlMapper bookControlMapper;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private RecordTypeService recordTypeService;

	@RequestMapping(value = "/hardware/u/{idCardMd5}")
	public @ResponseBody
	Map<String, Object> loadUserInfoByIdCard(
			@PathVariable("idCardMd5") String idCardMd5) {
		log.debug("load UserInfo By IdCardMd5 {}", idCardMd5);

		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(idCardMd5)) {
			User user = userService.getUserInfoByIdCardMd5(idCardMd5);
			if (user != null) {
				List<OrderRecords> order = orderRecordsService
						.selectByOpenIdAndToady(user.getOpenid());
				if (order.size() == 0) {
					modelMap.put("text", "该身份证号今天未预约");
					modelMap.put("result", false);
				}
				// else if(order.getNumberOfTake() >= 2){
				// modelMap.put("text", "您取号已达两次，不能再次取号");
				// modelMap.put("result", false);
				// }
				else {

					// order.setNumberOfTake(order.getNumberOfTake() + 1);
					// orderRecordsService.updateOrderRecord(order);
					// modelMap.put("type", order.getTransactTypeName());
					// modelMap.put("name", user.getName());
					// modelMap.put("idcard", user.getIdcard());

					List<Map<String, Object>> userOrdersToday = new ArrayList<Map<String, Object>>();
					for (OrderRecords ors : order) {
						Map<String, Object> tempOrs = new HashMap<String, Object>();
						tempOrs.put("type", ors.getTransactTypeName());
						tempOrs.put("name", ors.getName());
						tempOrs.put("idcard", ors.getIdcard());
						tempOrs.put("transactTime", ors.getTransactTime()); // 取号时间段
						userOrdersToday.add(tempOrs);
					}

					modelMap.put("userOrdersToday", userOrdersToday);
					modelMap.put("result", true);
					modelMap.put("text", "查询成功");
				}
			} else {
				modelMap.put("text", "该身份证号未在不动产微信平台登记");
				modelMap.put("result", false);
			}
		} else {
			modelMap.put("text", "身份证号不能为空");
			modelMap.put("result", false);
		}

		return modelMap;
	}

	/**
	 * 测试二维码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hardware/testQRcode.html",
			method = RequestMethod.GET)
	public String createQRcodeTest() {

		return "/user/qrcodeTest";
	}

	/**
	 * 回调方法 ，用户更新 预约号
	 * 
	 * @param idCardMd5
	 * @param orderTaking
	 * @return
	 */
	@RequestMapping(value = "/hardware/u/{orderTaking}/{idCardMd5}")
	public @ResponseBody
	String updateOrderTaking(@PathVariable("idCardMd5") String idCardMd5,
			@PathVariable("orderTaking") int orderTaking) {
		log.debug("update orderTaking By IdCardMd5 {}", idCardMd5);
		String result = "FAILURE";
		try {
			User user = userService.getUserInfoByIdCardMd5(idCardMd5);
			// OrderRecords order =
			// orderRecordsService.selectByOpenIdAndToady(user.getOpenid());
			// order.setOrderTaking(orderTaking);
			// orderRecordsService.updateOrderRecord(order);

			result = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 回调方法 ，处理每天的取号结果，更新订单表，推送模板消息，按照区县
	 * 
	 * @param idCardMd5
	 * @param orderTaking
	 * @return
	 */
	@RequestMapping(value = "/hardware/u/updateOrderToday/{area}",
			method = RequestMethod.POST)
	public @ResponseBody
	String updateOrderToday(HttpServletRequest request,
			@PathVariable("area") int area) {
		log.debug("update order By area {}", area);
		String jsonString;
		String result = "FAILURE";
		// User user;
		OrderRecords orderRecords = new OrderRecords();
		try {
			// 第一步 处理传过来的json数据

			jsonString = request.getParameter("jsondata");
			// jsonString=URLDecoder.decode(jsonString);
			jsonString = StringEscapeUtils.unescapeHtml(jsonString);
			log.debug("json {}", jsonString);
			// 判断jsonstring是否格式正确
			if (jsonString != null
					&& (jsonString.startsWith("[") || jsonString
							.startsWith("{"))
					&& (jsonString.endsWith("]") || jsonString.endsWith("}"))) {
				JSONObject jb = JSONObject.fromObject(jsonString);
				JSONArray ja = jb.getJSONArray("jsondata");

				log.debug("jsonData is {}", jsonString);

				// ====================在发模板消息之前，先把accesstoken从txt读取出来写到静态常量里==============
				try {
					// String encoding = "GBK";
					File file = new File("C:/ACCEESS_TOKEN.txt");
					if (file.isFile() && file.exists()) { // 判断文件是否存在
						InputStreamReader read = new InputStreamReader(
								new FileInputStream(file));// 考虑到编码格式
						BufferedReader bufferedReader = new BufferedReader(read);
						String lineTxt = null;
						while ((lineTxt = bufferedReader.readLine()) != null) {
							ConstantWeChat.ACCEESS_TOKEN = lineTxt;
						}
						read.close();
					} else {
						log.info("找不到指定的文件");
					}
				} catch (Exception e) {
					log.info("读取文件内容出错");
					e.printStackTrace();
				}
				// ==================================================================================================
				for (int i = 0; i < ja.size(); i++) {
					// 循环时把订单表顺便更新了
					orderRecords.setId(Integer.parseInt(ja.getJSONObject(i)
							.getString("id")));
					orderRecords.setStatus(Integer.parseInt(ja.getJSONObject(i)
							.getString("Status")));
					// orderList.add(orderRecords);
					// 第二步 更新订单状态 按照id更新订单 如果订单表的状态不是审核通过的，不更新
					if (orderRecords.getId() != 000000) {
						if (recordsMapper.selectById(orderRecords.getId())
								.getStatus() == 2) {
							recordsMapper.update(orderRecords);
							// 第三步 推送模板消息给用户
							pushWechatMsg(orderRecords.getId());
						}
					}
				}
			}
			result = "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 回调方法 ，处理每天的取号结果，更新订单表，推送模板消息，按照区县
	 * 
	 * @param idCardMd5
	 * @param orderTaking
	 * @return
	 */
	@RequestMapping(value = "/hardware/u/updateOrderTodayGet/{data}",
			method = RequestMethod.GET)
	public @ResponseBody
	String updateOrderTodayGet(HttpServletRequest request,
			@PathVariable("data") String data) {
		log.debug("update order By area {}", data);
		String[] DataString;
		String result = "FAILURE";
		// User user;
		OrderRecords orderRecords = new OrderRecords();
		// ====================在发模板消息之前，先把accesstoken从txt读取出来写到静态常量里==============
		try {
			// String encoding = "GBK";
			File file = new File("C:/ACCEESS_TOKEN.txt");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					ConstantWeChat.ACCEESS_TOKEN = lineTxt;
				}
				read.close();
			} else {
				log.info("找不到指定的文件");
			}
		} catch (Exception e) {
			log.info("读取文件内容出错");
			e.printStackTrace();
		}
		// ==================================================================================================

		// DataString = data.split(",");
		// for (String item : DataString) {
		// log.info(item.split(":")[0]);
		// 不要让加号被处理
		if (data.split(":")[0].toString() != "000000") {
			orderRecords.setId(Integer.parseInt(data.split(":")[0].toString()));

			orderRecords.setStatus(Integer.parseInt(data.split(":")[1]
					.toString()));
			if (recordsMapper.update(orderRecords) > 0) {
				// 第三步 推送模板消息给用户
				pushWechatMsg(orderRecords.getId());
				// }
				result = "SUCCESS";
			}
		} else {
			result = "SUCCESS";
		}
		return result;
	}

	/*
	 * 此方法为了推送微信消息
	 */
	public String pushWechatMsg(long id) {
		OrderRecords orderRecords = orderRecordsService.selectById(id);
		// 发送微信预约
		String date = orderRecords.getTransactDate() + " "
				+ orderRecords.getTransactTime();
		// 预约成功
		if (orderRecords.getStatus() == 5 || orderRecords.getStatus() == 6
				|| orderRecords.getStatus() == 9) {
			log.debug("cancel book begin");
			// 更新预约时间表
			int count = bookControlMapper.updateCancel(
					orderRecords.getTransactDate(),
					orderRecords.getTransactTimeId(),
					orderRecords.getTransactOrgId());
			if (count == 0) {
				bookControlMapper.updateCancel2(orderRecords.getTransactDate(),
						orderRecords.getTransactTime(),
						orderRecords.getTransactOrgId());
			}
			// 预约失败
			String remark = "";
			// 爽约
			if (orderRecords.getStatus() == 5) {
				remark = "\n备注：因您未按时赴约，本系统将按照爽约进行记录！";
				// 补证
			} else if (orderRecords.getStatus() == 6) {
				remark = "\n备注：请您按照工作人员的要求补齐完善相关资料后再重新申请预约，谢谢！";
			} else {
				remark = "\n备注：您的信息填写有误，请更正后，重新预约！";
			}
			String rtnUrl = "http://www.tjsbdcdjzx.com/static/"
					+ orderRecords.getTransactTypeId() + "/noticeInfo.html";
			WeChatModelUtil.pushFailedMessage(orderRecords.getOpenid(), rtnUrl,
					orderRecords.getName(), orderRecords.getTransactTypeName(),
					date, orderRecords.getTransactOrgName(), remark);
		} else if (orderRecords.getStatus() == 4) {
			String remark = "\n备注：您的登记业务已办理完毕，感谢您使用本预约系统！";
			String rtnUrl = "http://www.tjsbdcdjzx.com/static/"
					+ orderRecords.getTransactTypeId() + "/noticeInfo.html";
			WeChatModelUtil.pushFinishMessage(orderRecords.getOpenid(), rtnUrl,
					orderRecords.getName(), orderRecords.getTransactTypeName(),
					date, orderRecords.getTransactOrgName(), remark);
		}
		return "";
	}

	/**
	 * 获取本日预约人信息 旧的版本 暂时保留
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/hardware/u/getBookToday")
	public List<Map<String, Object>> getBookToday() {
		log.debug("get book infomation");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = sdf.format(calendar.getTime());

		List<OrderRecords> or = recordsMapper.selectByDate(date);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (OrderRecords ors : or) {
			Map<String, Object> tempOrs = new HashMap<String, Object>();
			tempOrs.put("type", ors.getTransactTypeName());
			tempOrs.put("name", ors.getName());
			tempOrs.put("idcard", ors.getIdcard().toLowerCase());
			tempOrs.put("transactTime", ors.getTransactTime()); // 取号时间段
			returnList.add(tempOrs);
		}

		return returnList;
	}

	/**
	 * 获取本日预约人信息 新的版本
	 * 
	 * @return 返回的值有 id，type，name，idcard，transactTime
	 */
	@ResponseBody
	@RequestMapping(value = "/hardware/u/getBookToday/{area}")
	public List<Map<String, Object>> getBookToday(@PathVariable("area") int area) {
		log.debug("get book infomation");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = sdf.format(calendar.getTime());

		List<OrderRecords> or = recordsMapper.selectByDateAndArea(date, area);

		// 后台加号的下载
		List<AddOrder> addOrders = recordsMapper.selectAddOrderByDateAndArea(
				date, area);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (OrderRecords ors : or) {
			Map<String, Object> tempOrs = new HashMap<String, Object>();
			tempOrs.put("id", ors.getId());
			tempOrs.put("type", ors.getTransactTypeName());
			tempOrs.put("name", ors.getName());
			tempOrs.put("idcard", ors.getIdcard().toUpperCase());
			tempOrs.put("transactTime", ors.getTransactTime()); // 取号时间段
			returnList.add(tempOrs);
		}
		// 后台加号的下载
		for (AddOrder ors : addOrders) {
			Map<String, Object> tempOrs = new HashMap<String, Object>();
			tempOrs.put("id", "000000");
			tempOrs.put("type", ors.getTransactTypeName());
			tempOrs.put("name", ors.getName());
			tempOrs.put("idcard", ors.getIdcard().toUpperCase());
			tempOrs.put("transactTime", ors.getTransactTime()); // 取号时间段
			returnList.add(tempOrs);
		}

		return returnList;
	}

	/**
	 * 获取本日预约人信息 包含地址和房产证号版本
	 * 
	 * @return 返回的值有 id，type，name，idcard，transactTime,产权证号，房地产坐落
	 */
	@ResponseBody
	@RequestMapping(value = "/hardware/u/getBookTodayHasAddr/{area}")
	public List<Map<String, Object>> getBookTodayHasAddr(
			@PathVariable("area") int area) {
		log.debug("get book infomation");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = sdf.format(calendar.getTime());

		List<OrderRecords> or = recordsMapper.selectByDateAndArea(date, area);

		// 后台加号的下载
		List<AddOrder> addOrders = recordsMapper.selectAddOrderByDateAndArea(
				date, area);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (OrderRecords ors : or) {
			Map<String, Object> tempOrs = new HashMap<String, Object>();
			tempOrs.put("id", ors.getId());
			// 是塘沽，需要将子业务名称改为主业务名称并发送
			if (area == 17) {
				tempOrs.put(
						"type",
						dictionaryService.getDictionaryById(
								noticeService.getNoticeById(
										ors.getTransactTypeId(),
										String.valueOf(ors.getTransactOrgId()))
										.getNoticeTypeId())
								.getDictionaryValue());
			} else {
				tempOrs.put("type", ors.getTransactTypeName());
			}
			tempOrs.put("name", ors.getName());
			tempOrs.put("idcard", ors.getIdcard().toUpperCase());
			tempOrs.put("transactTime", ors.getTransactTime()); // 取号时间段

			// 清除掉所有特殊字符
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(ors.getHourseAddress());

			tempOrs.put("HouseAddress", m.replaceAll("").trim());
			tempOrs.put("HouseNumber", ors.getHourseNumber());
			returnList.add(tempOrs);
		}

		// 后台加号的下载
		for (AddOrder ors : addOrders) {
			Map<String, Object> tempOrs = new HashMap<String, Object>();
			tempOrs.put("id", "000000");
			// 是塘沽，需要将子业务名称改为主业务名称并发送
			if (area == 17) {
				tempOrs.put(
						"type",
						dictionaryService.getDictionaryById(
								noticeService.getNoticeById(
										ors.getTransactTypeId(),
										String.valueOf(ors.getTransactOrgId()))
										.getNoticeTypeId())
								.getDictionaryValue());
			} else {
				tempOrs.put("type", ors.getTransactTypeName());
			}
			tempOrs.put("name", ors.getName());
			tempOrs.put("idcard", ors.getIdcard().toUpperCase());
			tempOrs.put("transactTime", ors.getTransactTime()); // 取号时间段

			// 清除掉所有特殊字符
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(ors.getHourseAddress());

			tempOrs.put("HouseAddress", m.replaceAll("").trim());
			tempOrs.put("HouseNumber", ors.getHourseNumber());
			returnList.add(tempOrs);
		}

		return returnList;
	}

	/**
	 * 获取区县的各个类型剩余号
	 * 
	 * @return 返回的值有类型名，类型ID，类型剩余数，类型总数，已预约数，调整后剩余数
	 */
	@ResponseBody
	@RequestMapping(value = "/hardware/u/leftNum/{area}")
	public List<Map<String, Object>> leftNum(@PathVariable("area") int area) {
		String dateString = DateUtil.getDate();
		RecordTypeViewNotice recordTypeViewNotice = new RecordTypeViewNotice();
		recordTypeViewNotice.setArea(area);
		recordTypeViewNotice.setDate(dateString);
		List<RecordTypeViewNotice> listView = recordTypeService
				.getViewByDateAndArea(recordTypeViewNotice);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (RecordTypeViewNotice model : listView) {
			Map<String, Object> tempView = new HashMap<String, Object>();
			tempView.put("typeName", model.getNoticeName());
			tempView.put("typeId", model.getTransacttypeid());
			tempView.put("leftNum",
					model.getOrderlimit() - model.getOrderednum());
			tempView.put("sumNum", model.getOrderlimit());
			tempView.put("orderedNum", model.getOrderednum());
			tempView.put("time", model.getTime());
			returnList.add(tempView);
		}
		return returnList;
	}

	/**
	 * 叫号回传接口 data的格式为：id
	 * 
	 * @return 返回的值是否成功success和failure
	 */
	@ResponseBody
	@RequestMapping(value = "/hardware/u/callNum.html")
	public String callNum(HttpServletRequest request) {
		String result = "FAILURE";
		
		OrderRecords orderRecords = orderRecordsService.selectById(Integer
				.parseInt(String.valueOf(request.getParameter("id"))));
		String windowNumString=request.getParameter("userId");
		String userIdString=request.getParameter("winNum");
		@SuppressWarnings("deprecation")
		String eventTime=URLDecoder.decode(request.getParameter("EventTime"));
		DateUtil dateUtil=new DateUtil();
		log.info(windowNumString+"="+userIdString+"="+dateUtil.getFormatTime(eventTime,DateFormat.YYYY_MM_DD_HH_mm_ss)+"这是输出");
		if (orderRecords != null) {
			if (orderRecords.getId() > 0)
			{
				log.info(orderRecords.getTransactDate()+"+"+DateUtil.getDate());
				if(orderRecords.getTransactDate().equals(DateUtil.getDate()))
				{
					// ===todo 根据类型id，区县，日期，时间段，为排队数+1
					result = "SUCCESS";
				}
			}
				
		}
		return result;
	}
}
