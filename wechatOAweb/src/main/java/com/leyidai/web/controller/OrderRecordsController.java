package com.leyidai.web.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.lang.Integer;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.xml.ws.Service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyidai.entity.Area;
import com.leyidai.entity.BookControl;
import com.leyidai.entity.Dictionary;
import com.leyidai.entity.Notice;
import com.leyidai.entity.OrderRecordTemp;
import com.leyidai.entity.OrderRecords;
import com.leyidai.entity.RecordType;
import com.leyidai.entity.SendDataTemp;
import com.leyidai.entity.User;
import com.leyidai.entity.WorkTime;
import com.leyidai.web.mapper.AreaMapper;
import com.leyidai.web.mapper.BookControlMapper;
import com.leyidai.web.mapper.IpForbiddenMapper;
import com.leyidai.web.mapper.NoticeMapper;
import com.leyidai.web.mapper.OrderRecordsMapper;
import com.leyidai.web.mapper.SendDataTempMapper;
import com.leyidai.web.mapper.TypeLimitRecordMapper;
import com.leyidai.web.mapper.WorkTimeMapper;
import com.leyidai.web.schedule.WorkerScheduler;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.service.OrderRecordsService;
import com.leyidai.web.service.UserService;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.ConstantVariable;
import com.leyidai.web.util.Images1;
import com.leyidai.web.util.WeChatModelUtil;
import com.leyidai.web.util.webSocketUtil;

@Controller
public class OrderRecordsController {

	// 9-26 添加 start
	private static final long VALIDATIONTIMS = 3 * 60 * 1000; // 验证码有效市场 毫秒为单位
																// 该值为3分钟
	// 9-26 添加 end

	@Autowired
	private WorkTimeMapper workTimeMapper;
	@Autowired
	private SendDataTempMapper sendDataTempMapper;
	@Autowired
	private BookControlMapper bookControlMapper;
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderRecordsService orderRecordsService;
	@Autowired
	private TypeLimitRecordMapper typeLimitRecordMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private IpForbiddenMapper ipForbiddenMapper;
	@Autowired
	private OrderRecordsMapper orderRecordsMapper;
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(OrderRecordsController.class);

	/**
	 * 生成验证码 许云强 2016-9-22 add
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// session = request.getSession();

		Images1 vCode = new Images1(100, 30, 5, 10);
		session.setAttribute("vcode", vCode.getCode());
		// 9-26 添加时间戳 start
		session.setAttribute("vcodeTime", System.currentTimeMillis());
		// 9-26 添加时间戳 end
		// System.out.println(vCode.getCode());
		vCode.write(response.getOutputStream());

		// response.setContentType("text/html");
		// // 设置响应的类型格式为图片格式
		// response.setContentType("image/jpeg");
		// // 禁止图像缓存。
		// response.setHeader("Pragma", "no-cache");
		// response.setHeader("Cache-Control", "no-cache");
		// response.setDateHeader("Expires", 0);
		//
		// // *************************************//
		// String vcode1 = Image2.getRandomJianHan(2);
		// String verifyCode = Image2.generateVerifyCode(2);
		// StringBuilder vCodeSB = new StringBuilder();
		//
		// vCodeSB.append(vcode1);
		// vCodeSB.append(verifyCode);
		// session.setAttribute("vcode", vCodeSB.toString());
		// session.setAttribute("vcodeTime", System.currentTimeMillis());
		// Image2.outputImage(110, 40, response.getOutputStream(),
		// vCodeSB.toString());
	}

	@RequestMapping(value = "/static/bookForWeb/{area}")
	public String bookForWeb(HttpSession session, Model model,
			@PathVariable("area") String area) {

		// 如果没有区县，默认河北区
		if (area == null || area.isEmpty()) {
			area = "1035";
		}
		// 办理机构
		List<Dictionary> arrhandleOrg = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.HANDLEORG, null);
		List<Dictionary> handleOrg = new ArrayList<Dictionary>();
		for (int i = 0; i < arrhandleOrg.size(); i++) {
			Dictionary item = arrhandleOrg.get(i);
			if (area.indexOf(String.valueOf(item.getId())) != -1) {
				handleOrg.add(item);
			}
		}
		model.addAttribute("openid", "ov5KSuMZ_s6KgM-h-pYal5ILyhRM");
		// model.addAttribute("arrDays", arrDays);
		model.addAttribute("handleOrg", handleOrg);
		return "bookForWeb";
	}

	@RequestMapping(value = "/static/book/index")
	public String bookInit(HttpSession session, Model model) {

		String area = (String) session.getAttribute("area");
		// 是否是腾讯的页面
		String isTentcent = (String) session.getAttribute("isTencent");
		if (StringUtils.isNotEmpty(isTentcent) && isTentcent.equals("true")) {
			log.info(isTentcent + "这是腾讯true");
			model.addAttribute("isTencent", "tencent");
		} else {
			model.addAttribute("isTencent", "notTencent");
		}
		if (StringUtils.isEmpty(area)) {
			return "/areaSelect";
		}

		String openid = (String) session.getAttribute("openid");
		User user = userService.getUserInfoByOpenId(openid);
		if (user == null) {
			model.addAttribute("rtnMsg", "您是首次预约，请先完善个人信息！");
			return "redirect:/static/register?rtnCode=0";
		} else if (user.getStatus() == 0) {
			model.addAttribute("rtnMsg", "你的个人信息还没有通过审核，请等待通过审核！");
			return "redirect:/static/myAccount";
		} else if (user.getStatus() == 2) {
			model.addAttribute("rtnMsg", "你的个人信息没有审核通过，请修改后，重新提交审核！");
			return "redirect:/static/register?rtnCode=1";
		} else if (user.getIsForbidden() == 1) {
			// 如果被拉入黑名单，就不要预约，1为是黑名单里的人
			model.addAttribute("rtnMsg", "你已经被列为黑名单，暂时无法进行预约！");
			return "redirect:/";
		}

		// 办理机构
		List<Dictionary> arrhandleOrg = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.HANDLEORG, null);
		List<Dictionary> handleOrg = new ArrayList<Dictionary>();
		for (int i = 0; i < arrhandleOrg.size(); i++) {
			Dictionary item = arrhandleOrg.get(i);
			if (area.indexOf(String.valueOf(item.getId())) != -1) {
				handleOrg.add(item);
			}
		}
		model.addAttribute("openid", openid);
		// model.addAttribute("arrDays", arrDays);
		model.addAttribute("handleOrg", handleOrg);
		// model.addAttribute("businessType", businessType);
		model.addAttribute("userItem", user);
		return "book";
	}

	@ResponseBody
	@RequestMapping(value = "/static/book/getMyChild")
	public Map<String, Object> getMyChild(HttpSession session,
			String handleOrgId, Integer param) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("rtnCode", "9");

		// 保证不能预约的类型不出现在子类型选项中
		List<Notice> arrNotices = noticeMapper.getAllNoticesByAreaAndUseful(
				String.valueOf(areaMapper.getAreasByHandleOrgId(
						Integer.valueOf(handleOrgId)).getAreaCode()), param);
		for (Notice model : arrNotices) {
			model.setNoticeDescription("");
		}
		if (arrNotices.size() > 0) {
			rtnMap.put("rtnCode", "0");
			rtnMap.put("arrItem", arrNotices);
		}
		return rtnMap;
	}

	@ResponseBody
	@RequestMapping(value = "/static/book/getWorkTime")
	public Map<String, Object> getWorkTime(String date, Integer areaId,
			Integer transactTypeId) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("rtnCode", "9");

		// List<WorkTime> arrItem = workTimeMapper.findByAreaId(areaId);
		BookControl bookControl = new BookControl();
		bookControl.setDate(date);
		bookControl.setAreaId(areaId);
		List<BookControl> arrControl = bookControlMapper.find(bookControl);
		List<Map<String, String>> arrTime = new ArrayList<Map<String, String>>();
		Map<String, String> temMap = null;
		RecordType recordType = new RecordType();

		for (BookControl item : arrControl) {
			temMap = new HashMap<String, String>();
			String temTime = item.getTime();
			temMap.put("timeId", String.valueOf(item.getTimeId()));
			temMap.put("time", temTime);
			// 把members的数量从类型限制预约数表里查询出来
			recordType.setArea(areaMapper.getAreasByHandleOrgId(areaId)
					.getAreaCode());
			recordType.setDate(date);
			recordType.setTransacttypeid(transactTypeId);
			recordType.setTime(temTime);
			List<RecordType> list = typeLimitRecordMapper.find(recordType);
			// 如果可以查询出记录来，
			// 如果总数已经是满的
			if ((item.getMembers() - item.getBookMember()) <= 0) {
				temMap.put("members", String.valueOf(0));
			} else if (list.size() > 0 && list.get(0).getOrderlimit() != 999) {
				if (list.get(0).getOrderlimit() - list.get(0).getOrderednum() <= 0) {
					temMap.put("members", String.valueOf(0));
				} else {
					temMap.put(
							"members",
							String.valueOf(list.get(0).getOrderlimit()
									- list.get(0).getOrderednum()));
				}
			} else {
				temMap.put("members", String.valueOf(item.getMembers()
						- item.getBookMember()));
			}
			arrTime.add(temMap);
		}
		rtnMap.put("rtnCode", "0");
		rtnMap.put("arrTime", arrTime);

		return rtnMap;
	}

	/**
	 * 预约页面选择区县的ajax
	 * 
	 * @param areaId
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/static/book/handleOrgChange")
	public Map<String, Object> handleOrgChange(String areaId)
			throws ParseException {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("rtnCode", "9");

		// 查询所属行政区数据
		List<Area> arrArea = areaMapper.getAreasByOrgId(areaId);
		rtnMap.put("arrArea", arrArea);
		// 业务类型
		List<Dictionary> businessType = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.NOTICE, areaId);
		rtnMap.put("arrBussinessType", businessType);

		// 办理日期获取
		List<Dictionary> arrBookDate = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.BOOKDATE, null);
		Integer bookDate = 0;
		if (arrBookDate.size() > 0) {
			bookDate = Integer.valueOf(arrBookDate.get(0).getDictionaryValue());
		}

		/*
		 * 修改了可以预约下个工作日
		 */
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// ==================节假日和调休的处理=================================================================================

		// 获取节假日
		List<Dictionary> arrDateScale = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.HOLIDAY,
						String.valueOf(0));
		String DateScale = "";
		if (arrDateScale.size() > 0) {
			DateScale = arrDateScale.get(0).getDictionaryValue();
		}

		// 获取调休日
		List<Dictionary> arrChangeScale = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.CHANGEHOLIDAY,
						String.valueOf(0));
		String changeScale = "";
		if (arrDateScale.size() > 0) {
			changeScale = arrChangeScale.get(0).getDictionaryValue();
		}

		String[] dateScaleList = DateScale.split(",");
		String[] changeScaleList = changeScale.split(",");

		// ==================节假日和调休的处理end===============================
		// 变量定义
		java.util.Date startDate;
		java.util.Date endDate;
		SimpleDateFormat sdfforstart = new SimpleDateFormat("yyyyMMdd");
		// 是否是节假日的标识
		Boolean isHoliday = false;
		// 是否是节假日之前一天的标识
		Boolean isBeforeHoliday = false;
		// 是否是调休日的标识
		Boolean isChangeDay = false;
		// calendar.add(Calendar.DAY_OF_YEAR, 1);
		// 循环找出所有的假期来匹配看是否是假期
		for (String item : dateScaleList) {

			// 起始日期
			startDate = sdfforstart.parse(item.split("-")[0]);
			// 结束日期
			endDate = sdfforstart.parse(item.split("-")[1]);

			// 转换date和canlender
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(startDate);
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(endDate);
			// 给末尾的日期加一天，以让日期正确
			calEnd.add(Calendar.DAY_OF_YEAR, 1);
			// log.info(c.getTime().toString());
			// 如果处于起始和终止日期之间，则认为是节假日
			if (c.after(calStart) && c.before(calEnd)) {
				isHoliday = true;
				log.info("我是假期，当前日期是" + c.getTime().toString());
				break;
			} else {
				// 判断是否是节假日之前的一天
				calStart.add(Calendar.DAY_OF_YEAR, -1);
				// log.info(calStart.getTime().toString()
				// + sdfforstart.format(c.getTime()).equals(calStart));
				if (sdfforstart.format(c.getTime()).equals(
						sdfforstart.format(calStart.getTime()))) {
					isBeforeHoliday = true;
					log.info("我是假前日，当前日期是" + c.getTime().toString());
				}
			}
		}
		// 小循环看当前日期是否是调休日，就设置为跳过状态
		for (String item : changeScaleList) {
			if (sdfforstart.format(c.getTime()).equals(item)) {
				isChangeDay = true;
				log.info("我是调休日，当前日期是" + c.getTime().toString());
			}
		}

		List<Map<String, String>> arrDays;
		// ==============================先把假期判断完毕================================================
		// 假期
		if (isHoliday) {
			arrDays = bookControlMapper.findDate(areaId,
					new Date(System.currentTimeMillis()), bookDate + 1);
			arrDays = arrDays.subList(1, arrDays.size());
		}
		// 假前日
		else if (isBeforeHoliday) {

			if ((hour > Integer.parseInt(arrArea.get(0).getOpenOrderTime()
					.split(":")[0]))
					|| (hour == Integer.parseInt(arrArea.get(0)
							.getOpenOrderTime().split(":")[0]) && minute >= Integer
							.parseInt(arrArea.get(0).getOpenOrderTime()
									.split(":")[1]))) {// 超过了16点，去掉周一的那条
				arrDays = bookControlMapper.findDate(areaId,
						new Date(System.currentTimeMillis()), bookDate + 1);
				arrDays = arrDays.subList(1, arrDays.size());
			} else {// 没超过16点，按照普通的可以约周一的
				arrDays = bookControlMapper.findDate(areaId,
						new Date(System.currentTimeMillis()), bookDate);
			}
		}
		// 调休日
		else if (isChangeDay) {

			// 如果时间大于下午4点，认为超过了预约时间，不能预约下个工作日的
			if ((hour > Integer.parseInt(arrArea.get(0).getOpenOrderTime()
					.split(":")[0]))
					|| (hour == Integer.parseInt(arrArea.get(0)
							.getOpenOrderTime().split(":")[0]) && minute >= Integer
							.parseInt(arrArea.get(0).getOpenOrderTime()
									.split(":")[1]))) {
				arrDays = bookControlMapper.findDate(areaId,
						new Date(System.currentTimeMillis() + 86400000),
						bookDate);
			} else {
				arrDays = bookControlMapper.findDate(areaId,
						new Date(System.currentTimeMillis()), bookDate);
			}
		}
		// ==============================================================================
		else {

			// 处理星期六和星期日的情况 去掉周一的那条即可
			if (c.get(Calendar.DAY_OF_WEEK) == 7
					|| c.get(Calendar.DAY_OF_WEEK) == 1) {
				arrDays = bookControlMapper.findDate(areaId,
						new Date(System.currentTimeMillis()), bookDate + 1);
				arrDays = arrDays.subList(1, arrDays.size());
				// 处理星期五的情况
			} else {
				// log.info(hour + ":" + minute + ":");
				// 如果时间大于下午4点，认为超过了预约时间，不能预约下个工作日的
				if ((hour > Integer.parseInt(arrArea.get(0).getOpenOrderTime()
						.split(":")[0]))
						|| (hour == Integer.parseInt(arrArea.get(0)
								.getOpenOrderTime().split(":")[0]) && minute >= Integer
								.parseInt(arrArea.get(0).getOpenOrderTime()
										.split(":")[1]))) {
					// log.info(hour + ":" + minute + ":");
					arrDays = bookControlMapper.findDate(areaId, new Date(
							System.currentTimeMillis()), bookDate + 1);
					arrDays = arrDays.subList(1, arrDays.size());

				} else {
					// log.info(hour + ":" + minute + ":   11");
					arrDays = bookControlMapper.findDate(areaId, new Date(
							System.currentTimeMillis()), bookDate);
				}
			}
		}
		rtnMap.put("arrDays", arrDays);

		rtnMap.put("rtnCode", "0");

		return rtnMap;
	}

	public static boolean useList(String[] arr, String targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}

	// public boolean IsBeforeDistribute() throws ParseException {
	// // 获取到当天的日期，判断节假日，如果预约当天是节假日或周末，则不执行这个方法，直接返回false
	// Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
	// int hour = c.get(Calendar.HOUR_OF_DAY);
	// int minute = c.get(Calendar.MINUTE);
	// boolean isDateScale = isDateScale(c);
	// boolean isChangeScale = isChangeScale(c);
	// boolean isWeekend = isWeekend(c);
	//
	// if (isDateScale == true) {
	// continue;
	// }
	// // 不是休假日但是调休日
	// else if ((isDateScale == false) && (isChangeScale == true)) {
	//
	//
	// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//
	// 可以方便地修改日期格式
	// String todayDate = dateFormat.format(new java.util.Date());
	//
	// // 如果不是节假日，获取数据库最大日期
	// // 查询区县的放号时间，
	// // 如果提交进来的预约日期是最大日期，而时间小于放号时间，则不允许提交
	// return false;
	// }

	@SuppressWarnings("deprecation")
	/**
	 * 预约界面提交
	 * 
	 * @param session
	 * @param orderRecord
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/static/book/submit")
	public Map<String, Object> submit(HttpSession session,
			OrderRecords orderRecord, HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			NumberFormatException, ParseException {

		// log.info(orderRecord.getvCode());
		orderRecord.setTransactOrgName(URLDecoder.decode(
				orderRecord.getTransactOrgName(), "UTF-8"));
		orderRecord.setTransactTypeName(URLDecoder.decode(
				orderRecord.getTransactTypeName(), "UTF-8"));
		orderRecord.setName(URLDecoder.decode(orderRecord.getName(), "UTF-8"));
		orderRecord.setHourseAddress(URLDecoder.decode(
				orderRecord.getHourseAddress(), "UTF-8"));
		orderRecord.setHourseNumber(URLDecoder.decode(
				orderRecord.getHourseNumber(), "UTF-8"));
		orderRecord.setObligeename(URLDecoder.decode(
				orderRecord.getObligeename(), "UTF-8"));

		Map<String, Object> rtnMap = new HashMap<String, Object>();

		// 设置生成时间为系统当前时间
		Timestamp d = new Timestamp(System.currentTimeMillis());
		orderRecord.setCreateTime(d);

		rtnMap.put("rtnCode", "9");
		// 如果在假期或周末，就不能约最大日期号
		Calendar cal = Calendar.getInstance();
		// 如果是假期或者此周末不是调休日 暂时什么也不做
		// 如果是工作日，则要判断他是否提前
		if (!(isDateScale(cal) || (!isChangeScale(cal) && isWeekend(cal)))) {
			// 如果他约的是最大日期的
			if (bookControlMapper.selectDateTop1().trim()
					.equals(orderRecord.getTransactDate().trim())) {
				// 获取到区县的放号时间
				Area area = areaMapper.getAreasByHandleOrgId(orderRecord
						.getTransactOrgId());
				int hour = Integer
						.valueOf(area.getOpenOrderTime().split(":")[0]);
				int minuteString = Integer.valueOf(area.getOpenOrderTime()
						.split(":")[1]);
				// 获取到当前时间
				int curruntHour = cal.get(Calendar.HOUR_OF_DAY);
				int curruntMinut = cal.get(Calendar.MINUTE);
				// 如果放号小时数大于当前小时数或者如果时间相同，且分钟数不为零，放号分钟数大于当前分钟数
				if (hour > curruntHour
						|| (hour == curruntHour && minuteString != 0 && minuteString > curruntMinut)) {
					rtnMap.put("rtnMsg", "请不要提前预约");
					return rtnMap;
				}
			}
		}

		// 如果在锁定期，则停止预约
		if (!ConstantVariable.flagIsFlushOver) {
			rtnMap.put("rtnMsg", "系统正忙");
			return rtnMap;
		}

		String openid = "ov5KSuMZ_s6KgM-h-pYal5ILyhRM";
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		if (!openid.equals(orderRecord.getOpenid())) {
			rtnMap.put("rtnMsg", "禁止预约");
			return rtnMap;
		}

		// 如果openid存在于封禁的openid表中，则禁止预约
		if (ipForbiddenMapper.IsForbiddenOpenId(openid) > 0) {
			rtnMap.put("rtnMsg", "您已被封禁,禁止预约");
			return rtnMap;
		}

		/************* 许云强 修改 校验是否在封停期 start *******************/

		User userTmp = userService.getUserInfoByOpenId(orderRecord.getOpenid());
		if (userTmp != null) {
			if (userTmp.getIsForbidden() != 0) {
				rtnMap.put("rtnMsg", "你已经被列入黑名单，禁止预约！");
				return rtnMap;
			}
			if (userTmp.getStoptime_status() == 1) {
				String stopTimeTmp = userTmp.getStoptime_end().toString();
				rtnMap.put("rtnMsg", "该账户已被封停，禁止预约，" + stopTimeTmp + "后可以使用！");
				return rtnMap;
			}
		}

		boolean isIdcardForbidden = userService.isIdcardForbidden(orderRecord
				.getIdcard());
		if (isIdcardForbidden) {
			rtnMap.put("rtnMsg", "此身份证已被列入黑名单，禁止预约！");
			return rtnMap;
		}
		if (orderRecord.getTransactOrgId() != 1037
				&& orderRecord.getTransactOrgId() != 1047) {
			boolean isIdcardDuplicate = orderRecordsMapper
					.isIdcardDuplicate(orderRecord.getIdcard()) > 0;
			if (isIdcardDuplicate) {
				rtnMap.put("rtnMsg", "此身份证号有未处理的预约！");
				return rtnMap;
			}
		}

		/************* 许云强 修改 校验是否在封停期 end *******************/

		// ========================================防止提交过快的部分
		// 5分钟=========================================
		// 静海的可以很快提交
		java.sql.Timestamp ts = new java.sql.Timestamp(
				System.currentTimeMillis());

		boolean flagIsCooling = dictionaryService
				.queryIsCoolingArea(orderRecord.getTransactOrgId());

		if (flagIsCooling) {
			if (!orderRecord.getOpenid().equals("ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
				// 判断用户ip是否已经在库中，而且提交过快，在一分钟之内
				OrderRecords orderForIp = new OrderRecords();
				orderForIp = orderRecordsMapper
						.selectByFastIP(getIpAddr(request));
				if (orderForIp != null && orderForIp.getIPAddress() != "") {
					// 如果是今天的日期
					long current = System.currentTimeMillis();// 当前时间毫秒数
					long zero = current / (1000 * 3600 * 24)
							* (1000 * 3600 * 24)
							- TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
					if (orderForIp.getCreateTime().after(
							new java.util.Date(zero))) {
						log.info("零时" + zero);
						log.info(orderForIp.getIdcard() + "身份证号"
								+ orderRecord.getIdcard());
						if (orderForIp.getIdcard().equals(
								orderRecord.getIdcard())) {

						} else {
							if ((orderRecord.getTransactOrgId() == 1047 || orderRecord
									.getTransactOrgId() == 1045)) {
								rtnMap.put("rtnMsg", "该网段今天不可再约了");
								return rtnMap;
							}
							if ((ts.getTime() - orderForIp.getCreateTime()
									.getTime()) / 60000 < 30) {
								rtnMap.put("rtnMsg", "两次提交有30分钟的冷却时间，请耐心等待");
								return rtnMap;
							}
						}
					}
				}
			}
		}

		// ====================================防止提交过快end===============================================
		/************* 许云强 add 校验验证码 start *******************/
		String vcode = (String) session.getAttribute("vcode");
		log.info(vcode + orderRecord.getvCode());
		// System.out.println(vcode);
		// System.out.println(orderRecord.getvCode().toLowerCase());
		if (!vcode.toLowerCase().equals(orderRecord.getvCode().toLowerCase())) {
			if (!orderRecord.getOpenid().equals("ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
				rtnMap.put("rtnMsg", "验证码输入有误！");
				return rtnMap;
			}
		}
		// 9-26 add 校验验证码有效时间 start
		Long vcodeTime = Long.valueOf(session.getAttribute("vcodeTime")
				.toString());// 添加验证码的时间

		Long nowTimes = System.currentTimeMillis();
		if ((nowTimes - vcodeTime) > VALIDATIONTIMS) { // 时间过长 验证码失效 重新请求
			rtnMap.put("rtnMsg", "该验证码已失效！请重新获得验证码！");
			return rtnMap;
		}
		// 如果都验证了成功的验证码，则使该验证码清空。
		session.setAttribute("vcode", "");
		log.info((String) session.getAttribute("vcode"));
		// 9-26 add 校验验证码有效时间 end
		/************* 许云强 add 校验验证码 end *******************/

		// 是否是需要进行排队预约的区县
		boolean flagIsQueue = dictionaryService.queryIsQueueArea(orderRecord
				.getTransactOrgId()) && testTimeInQueue(orderRecord);
		log.info(flagIsQueue + "是否排队区县");
		// 如果是排队抢号，判断一下验证码的生成时间是否在放号之前，如果在放号之前，认为验证码失效，让他重新获取
		if (flagIsQueue) {
			// 通常是整点或者半点放号，所以可以比较验证码时间和当前时间舍去各位分钟数的时间，比如3点01提交的，或者3点31提交的，比较的则是3点整和3点30
			Calendar calendar = Calendar.getInstance();
			int minute = calendar.get(Calendar.MINUTE);
			if (minute < 30) {
				calendar.add(Calendar.MINUTE, -minute);
			} else {
				// 如果是31分类型的，则减去除掉30的部分
				calendar.add(Calendar.MINUTE, -(minute - 30));
			}
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			if (calendar.getTimeInMillis() - vcodeTime > 0) {
				rtnMap.put("rtnMsg", "请重新获得验证码！");
				return rtnMap;
			}
		}

		if (!flagIsQueue) {
			// 查询总数是否满了，满了就不能预约
			int memberSurplus = 0;
			memberSurplus = bookControlMapper.chenckMemberSurplus(
					orderRecord.getTransactDate(),
					String.valueOf(orderRecord.getTransactTimeId()),
					orderRecord.getTransactOrgId());
			if (memberSurplus <= 0) {
				rtnMap.put("rtnMsg", "您手慢了哦，剩余名额已被抢！请更换其他日期吧");
				return rtnMap;
			}
		}
		// 获取预约次数上限
		List<Dictionary> dictionary = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.APPOINTMENTSNUMBER,
						null);
		int number = 0;
		if (dictionary.size() == 0) {
			number = 1;
		} else {
			number = Integer.valueOf(dictionary.get(0).getDictionaryValue());
		}
		WorkTime workTime = workTimeMapper.findById(Integer.valueOf(orderRecord
				.getTransactTimeId()));

		if (flagIsQueue) {
			// 未处理的预约次数
			int appointmentNum = orderRecordsService
					.selectByOpenIdAndFlushOver(orderRecord.getOpenid()).size();
			if (orderRecord.getTransactOrgId() != 1037
					&& orderRecord.getTransactOrgId() != 1047) {
				if (!orderRecord.getOpenid().equals(
						"ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
					if (appointmentNum >= number) {
						rtnMap.put("rtnMsg", "您最多只能预约" + number + "次哦！");
						return rtnMap;
					}
				}
			}
		} else {
			// 未处理的预约次数
			int appointmentNum = orderRecordsService
					.selectByOpenIdAndStatusFast(orderRecord.getOpenid())
					.size();
			if (orderRecord.getTransactOrgId() != 1037
					&& orderRecord.getTransactOrgId() != 1047) {
				if (!orderRecord.getOpenid().equals(
						"ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
					if (appointmentNum >= number) {
						rtnMap.put("rtnMsg", "您最多只能预约" + number + "次哦！");
						return rtnMap;
					}
				}
			}
		}

		RecordType recordType = fillModel(orderRecord, workTime);

		// 此处的方法是带锁的
		if (!flagIsQueue) {
			Map<String, Object> map = findRecordType(recordType, rtnMap);
			if (map != null) {
				return map;
			}
		}
		orderRecord.setTransactTimeId((int) workTime.getId());
		orderRecord.setTransactTime(workTime.getStartTime() + "-"
				+ workTime.getEndTime());
		orderRecord.setDeadTime(workTime.getEndTime());

		orderRecord.setIPAddress(getIpAddr(request));
		boolean flagUseSocket = false;
		// 如果是需要自动审核的区县，在sendDataTemp中插入一条预约审核信息。并且现将order表预约项当前状态设为未发送
		if (dictionaryService.queryIsUseSocket(orderRecord.getTransactOrgId())) {
			// orderRecord.setExamineStatus(ConstantVariable.preSendDataStatus);
			if (dictionaryService.queryIsNotUseSocketType(orderRecord
					.getTransactTypeId())) {
				flagUseSocket = false;
				orderRecord.setExamineStatus(5);
			} else {
				flagUseSocket = true;
			}
		}
		int intId = orderRecordsService.save(orderRecord, flagIsQueue,
				recordType);

		if (intId <= 0) {
			rtnMap.put("rtnMsg", "您未能提交成功，请稍候重试");
			return rtnMap;
		} else {
			if (flagUseSocket&&!flagIsQueue&&intId>1) {
				SendDataTemp sendModel = new SendDataTemp();
				sendModel.setArea(orderRecord.getTransactOrgId());
				sendModel.setHourseNumber(orderRecord.getHourseNumber());
				sendModel.setOrderId(intId);
				sendModel.setIdcard(orderRecord.getIdcard());
				sendModel.setTransactType(webSocketUtil.switchType(orderRecord
						.getTransactTypeName()));
				sendModel.setName(orderRecord.getName());
				sendDataTempMapper.addSendDataTemp(sendModel);
			}
		}
		if (flagIsQueue) {
			rtnMap.put("rtnInQueue", "1");
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "您的申请已经加入排队，排队结果可查询个人中心或等待消息提醒");
			return rtnMap;
		} else {
			rtnMap.put("rtnCode", "0");
			return rtnMap;
		}
	}

	// 根据orderRecords来填充model
	public RecordType fillModel(OrderRecords orderRecord, WorkTime workTime) {
		RecordType recordType = new RecordType();
		recordType.setArea(Integer.valueOf(orderRecord.getArea()));
		recordType.setDate(orderRecord.getTransactDate());
		recordType.setTransacttypeid(orderRecord.getTransactTypeId());
		recordType.setTime(workTime.getStartTime() + "-"
				+ workTime.getEndTime());
		return recordType;
	}

	// 找到recordType，来取得上限
	public synchronized Map<String, Object> findRecordType(
			RecordType recordType, Map<String, Object> rtnMap) {
		// 根据区域，日期和类型来判断是否超过当日的数量限制，如果超过了就不允许他预约这种类型
		// 查找到新的类型限制对象，就替换掉
		List<RecordType> recordTypes = typeLimitRecordMapper.find(recordType);
		if (recordTypes.size() > 0) {
			recordType = recordTypes.get(0);
			// 如果类型限制小于已经预约的个数，认为可以预约这种类型，否则不可以
			if ((recordType.getOrderednum() + 1) > recordType.getOrderlimit()) {
				rtnMap.put("rtnMsg", "您选择的类型在" + recordType.getTime() + "上限为"
						+ recordType.getOrderlimit()
						+ "个，目前已达上限，请更换类型、日期或时间段再次预约");
				log.info("进入了错误分支，将要跳出方法");
				return rtnMap;
			}
		} else {
			rtnMap.put("rtnMsg", "暂时无法预约此项业务！");
			return rtnMap;
		}
		return null;
	}

	// 判断是否是在区县的排队时间内
	public boolean testTimeInQueue(OrderRecords order) {
		// 只排队6分钟，超过6分钟就停止
		Calendar c = Calendar.getInstance();
		// 获取到当天0时
		Calendar AllcationNumTime = Calendar.getInstance();
		AllcationNumTime.set(Calendar.HOUR_OF_DAY, 0);
		AllcationNumTime.set(Calendar.MINUTE, 0);
		AllcationNumTime.set(Calendar.SECOND, 0);

		// 根据0时获取到号池放号时间
		AllcationNumTime.set(
				Calendar.HOUR_OF_DAY,
				Integer.parseInt(dictionaryService
						.getDictionarysByCatetory(
								CategoryConstants.ALLOCATIONNUMTIME, 0).get(0)
						.getDictionaryValue().split(":")[0]));
		AllcationNumTime.set(
				Calendar.MINUTE,
				Integer.parseInt(dictionaryService
						.getDictionarysByCatetory(
								CategoryConstants.ALLOCATIONNUMTIME, 0).get(0)
						.getDictionaryValue().split(":")[1]));

		// 获取到放号时间4分钟内
		Calendar endTimeCalendar = (Calendar) AllcationNumTime.clone();
		endTimeCalendar.add(Calendar.MINUTE, 4);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");		
		if (c.getTime().after(AllcationNumTime.getTime()) && c.getTime().compareTo(endTimeCalendar.getTime())<0) {
			return true;
		}

		// 查询出放号时间的时间和分钟
		int hour = Integer.valueOf(areaMapper
				.getAreasByHandleOrgId(order.getTransactOrgId())
				.getOpenOrderTime().split(":")[0]);
		int minute = Integer.valueOf(areaMapper
				.getAreasByHandleOrgId(order.getTransactOrgId())
				.getOpenOrderTime().split(":")[1]);
		log.info(hour + "小时" + minute + "分钟");
		log.info(bookControlMapper.selectDateTop1().trim()
				+ "----bookcontrol最大日期" + order.getTransactDate());

		log.info(c.get(Calendar.MINUTE) + "---" + (minute + 3));
		// 放号是几点，就是几点，分钟数小于放号分钟+6，大于放号分钟，日期是数据库最新日期
		return c.get(Calendar.HOUR_OF_DAY) == hour
				&& c.get(Calendar.MINUTE) <= (minute + 3)
				&& c.get(Calendar.MINUTE) >= minute
				&& bookControlMapper.selectDateTop1().trim()
						.equals(order.getTransactDate().trim());
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 我的预约
	 * 
	 * @param model
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/static/myOrder/{openid}")
	public String myOrderRecords(Model model,
			@PathVariable("openid") String openid) {

		List<OrderRecords> orderRecords = orderRecordsService
				.selectByOpenid(openid);
		User user = userService.getUserInfoByOpenId(openid);

		model.addAttribute("orderRecords", orderRecords);
		if (user != null) {
			model.addAttribute("idcardmd5", user.getIdcardmd5());
		} else {
			model.addAttribute("idcardmd5", "");
		}

		return "/user/myOrder";
	}

	/**
	 * 排队结果
	 * 
	 * @param model
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/static/myQueueOrder/{openid}")
	public String myQueueOrder(Model model,
			@PathVariable("openid") String openid) {

		List<OrderRecordTemp> orderRecords = orderRecordsService
				.selectTempByOpenid(openid);
		// User user = userService.getUserInfoByOpenId(openid);

		model.addAttribute("orderRecords", orderRecords);
		return "user/myQueueOrder";
	}

	/**
	 * 取消预约
	 * 
	 * @param orderId
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelOrder/{orderId}")
	public String cancelOrder(@PathVariable("orderId") long orderId,
			HttpSession session) {

		log.info("执行到这里");

		// 根据查到的orderrecord对象来查询类型限制数
		OrderRecords orderRecords = orderRecordsService.selectById(orderId);

		if (orderRecords.getStatus() != 3 && orderRecords.getStatus() != 8) {

			// 认为是状态为2的人在取消
			if (orderRecords.getStatus() == 2) {
				// 将状态设置为审核后取消
				orderRecords.setStatus(8);
			} else {
				orderRecords.setStatus(3);
			}
			// 准备更新order表，如果更新失败,判断是否为增加号
			if (orderRecordsMapper.updateOrderRecord(orderRecords) <= 0) {
				if (orderRecords.getTransactTypeName().indexOf("增加") < 0) {
					// log.info("没有增加号");
					return "failure";
				} else {
					// log.info("是增加号，不报错");
				}
			}

			// 获取到使用号池的区县
			String numPoolArea = dictionaryService
					.getDictionarysByCatetory(
							CategoryConstants.NUMPOOLAREAORGID, "0").get(0)
					.getDictionaryValue();

			// 标志位，是否减号，默认为减号
			boolean isMinusNum = true;
			for (String orgid : numPoolArea.split(";")) {
				if (String.valueOf(orderRecords.getTransactOrgId()).equals(
						orgid)) {
					isMinusNum = false;
				}
			}

			// 如果返回true即是成功，否则就告诉用户取消失败了
			if (!orderRecordsService
					.updateBookControl(orderRecords, isMinusNum)) {
				return "failure";
			}

			RecordType recordType = new RecordType();
			recordType.setArea(Integer.valueOf(orderRecords.getArea()));
			recordType.setDate(orderRecords.getTransactDate());
			recordType.setTransacttypeid(orderRecords.getTransactTypeId());
			recordType.setTime(orderRecords.getTransactTime());

			if (isMinusNum) {
				// 为类型限制减一
				if (typeLimitRecordMapper.updateOrderednumminurs(recordType) <= 0) {
					log.info("检测是否有增加号");
					if (orderRecords.getTransactTypeName().indexOf("增加") < 0) {
						return "failure";
					} else {
						log.info("是增加号，不报错");
					}
				}
			} else {
				if (typeLimitRecordMapper.updateCancelOrderednum(recordType) <= 0) {
					if (orderRecords.getTransactTypeName().indexOf("增加") < 0) {
						return "failure";
					} else {
						log.info("是增加号，不报错");
					}
				}
			}

			log.info("执行到这里");
			String date = orderRecords.getTransactDate() + " "
					+ orderRecords.getTransactTime();
			String remark = "\n备注：您的预约已经取消，感谢您使用本预约系统！";
			String rtnUrl = "http://www.tjsbdcdjzx.com/static/"
					+ orderRecords.getTransactTypeId() + "/noticeInfo.html";
			Map<String, String> rtnmsg = WeChatModelUtil.pushFailedMessage(
					orderRecords.getOpenid(), rtnUrl, orderRecords.getName(),
					orderRecords.getTransactTypeName(), date,
					orderRecords.getTransactOrgName(), remark);
			log.info(rtnmsg.get("errCode"));

			// 如果是腾讯的，要把结果页显示为消息通路的
			String isTentcent = (String) session.getAttribute("isTencent");
			if (StringUtils.isNotEmpty(isTentcent) && isTentcent.equals("true")) {
				return rtnmsg.get("result_page_url");
			}
		} else {
			return "success";
		}

		return "success";
	}

	/*
	 * ============================硬件现场预约部分======================================
	 * ==================
	 */

	@RequestMapping(value = "/static/book/hard/{area}")
	public String bookForHard(HttpSession session, Model model,
			@PathVariable("area") String area) {

		// 如果没有区县，默认河北区
		if (area == null || area.isEmpty()) {
			area = "1035";
		}
		// 办理机构
		List<Dictionary> arrhandleOrg = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.HANDLEORG, null);
		List<Dictionary> handleOrg = new ArrayList<Dictionary>();
		for (int i = 0; i < arrhandleOrg.size(); i++) {
			Dictionary item = arrhandleOrg.get(i);
			if (area.indexOf(String.valueOf(item.getId())) != -1) {
				handleOrg.add(item);
			}
		}
		model.addAttribute("handleOrg", handleOrg);
		return "bookForHard";
	}

	/*
	 * 现场预约的提交
	 */
	@ResponseBody
	@RequestMapping(value = "/static/book/hardSubmit")
	public Map<String, Object> hardSubmit(OrderRecords orderRecord,
			HttpSession session, HttpServletRequest request) throws IOException {
		orderRecord.setTransactOrgName(URLDecoder.decode(
				orderRecord.getTransactOrgName(), "UTF-8"));
		orderRecord.setTransactTypeName(URLDecoder.decode(
				orderRecord.getTransactTypeName(), "UTF-8"));
		orderRecord.setName(URLDecoder.decode(orderRecord.getName(), "UTF-8"));
		orderRecord.setHourseAddress(URLDecoder.decode(
				orderRecord.getHourseAddress(), "UTF-8"));
		orderRecord.setHourseNumber(URLDecoder.decode(
				orderRecord.getHourseNumber(), "UTF-8"));

		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("rtnCode", "9");

		boolean flagNotUseHard = dictionaryService.queryNotUseHard(orderRecord
				.getTransactOrgId());
		if (flagNotUseHard) {
			rtnMap.put("rtnMsg", "暂停使用现场预约业务，非常抱歉给您带来不便");
			return rtnMap;
		}

		if (orderRecord.getTransactOrgId() != 1037
				&& orderRecord.getTransactOrgId() != 1047) {
			boolean isIdcardDuplicate = orderRecordsMapper
					.isIdcardDuplicate(orderRecord.getIdcard()) > 0;
			if (isIdcardDuplicate) {
				rtnMap.put("rtnMsg", "此身份证号有未处理的预约！");
				return rtnMap;
			}
		}
		WorkTime workTime = workTimeMapper.findById(Integer.valueOf(orderRecord
				.getTransactTimeId()));
		int memberSurplus = 0;
		memberSurplus = bookControlMapper.chenckMemberSurplus(
				orderRecord.getTransactDate(),
				String.valueOf(orderRecord.getTransactTimeId()),
				orderRecord.getTransactOrgId());
		if (memberSurplus <= 0) {
			rtnMap.put("rtnMsg", "您手慢了哦，剩余名额已被抢！请更换其他日期吧");
			return rtnMap;
		}
		// 根据区域，日期和类型来判断是否超过当日的数量限制，如果超过了就不允许他预约这种类型
		RecordType recordType = new RecordType();
		recordType.setArea(areaMapper.getAreasByHandleOrgId(
				orderRecord.getTransactOrgId()).getAreaCode());
		recordType.setDate(orderRecord.getTransactDate());
		recordType.setTransacttypeid(orderRecord.getTransactTypeId());
		recordType.setTime(workTime.getStartTime() + "-"
				+ workTime.getEndTime());
		log.info(recordType.getDate() + recordType.getTime()
				+ recordType.getArea() + recordType.getTransacttypeid());
		// 查找到新的类型限制对象，就替换掉
		List<RecordType> recordTypes = typeLimitRecordMapper.find(recordType);
		if (recordTypes.size() > 0) {
			recordType = recordTypes.get(0);
			// 如果类型限制小于已经预约的个数，认为可以预约这种类型，否则不可以
			if ((recordType.getOrderednum() + 1) > recordType.getOrderlimit()) {
				rtnMap.put("rtnMsg", "您选择的类型已经达上限，请更换类型或日期再次预约");
				log.info("进入了错误分支，将要跳出方法");
				return rtnMap;
			}
		} else {
			rtnMap.put("rtnMsg", "暂时无法预约此项业务！");
			return rtnMap;
		}
		orderRecord.setTransactTimeId((int) workTime.getId());
		orderRecord.setTransactTime(workTime.getStartTime() + "-"
				+ workTime.getEndTime());
		orderRecord.setDeadTime(workTime.getEndTime());
		orderRecord.setArea(String.valueOf(areaMapper.getAreasByHandleOrgId(
				orderRecord.getTransactOrgId()).getAreaCode()));
		// 现场预约的让他审核通过
		orderRecord.setStatus(2);

		orderRecord.setIPAddress(getIpAddr(request));

		boolean flagIsQueue = dictionaryService.queryIsQueueArea(orderRecord
				.getTransactOrgId()) && testTimeInQueue(orderRecord);
		// log.info(String.valueOf(orderRecord.getStatus())+"这是他此时的状态码");

		// 如果在现场预约里排队，则不予生成
		if (flagIsQueue) {
			rtnMap.put("rtnMsg", "排队时间请使用微信预约！");
			return rtnMap;
		}

		orderRecord.setOpenid("现场预约");
		orderRecordsService.save(orderRecord, flagIsQueue, recordType);

		rtnMap.put("rtnCode", "0");
		session.setAttribute(
				"strData",
				orderRecord.getTransactOrgName() + ","
						+ orderRecord.getTransactTypeName() + ","
						+ orderRecord.getTransactDate() + ","
						+ orderRecord.getTransactTime() + ","
						+ orderRecord.getName() + "," + orderRecord.getIdcard()
						+ "," + orderRecord.getMobile() + ","
						+ orderRecord.getHourseNumber());
		return rtnMap;
	}

	@RequestMapping(value = "/book/hardSubmitSuccess")
	public String hardSubmitSuccess(HttpSession session, Model model) {

		String[] data = session.getAttribute("strData").toString().split(",");
		model.addAttribute("transactOrgName", data[0]);
		model.addAttribute("transactTypeName", data[1]);
		model.addAttribute("transactDate", data[2]);
		model.addAttribute("transactTime", data[3]);
		model.addAttribute("name", data[4]);
		model.addAttribute("idcard", data[5]);
		model.addAttribute("mobile", data[6]);
		model.addAttribute("hourseNumber", data[7]);
		model.addAttribute("strData", session.getAttribute("strData")
				.toString());
		return "hardSubmitSuccess";
	}

	/**
	 * 
	 * @param 验证是否是周末
	 * @return 验证结果
	 */
	public boolean isWeekend(Calendar cal) {
		boolean flagForWeekend = false;
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			flagForWeekend = true;
		}
		return flagForWeekend;
	}

	/**
	 * 
	 * @param 验证是否是节假日
	 * @return 验证结果
	 * @throws ParseException
	 */

	public boolean isDateScale(Calendar cal) throws ParseException {
		boolean flagForDate = false;
		// 定义起始日期和终止日期
		java.util.Date startDate;
		java.util.Date endDate;
		List<Dictionary> arrDateScale = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.HOLIDAY, 0);
		String DateScale = "";
		// 判断是否有节假日
		if (arrDateScale.size() > 0) {
			// 获取节假日信息
			DateScale = arrDateScale.get(0).getDictionaryValue();
			log.info(DateScale + "这是节假日");
			String[] dateScaleList = DateScale.split(",");
			// 格式化日期
			SimpleDateFormat sdfforstart = new SimpleDateFormat("yyyyMMdd");
			// 循环找出所有的假期来匹配当前日期是否是假期
			for (String item : dateScaleList) {
				// 起始日期
				startDate = sdfforstart.parse(item.split("-")[0]);
				// 结束日期
				endDate = sdfforstart.parse(item.split("-")[1]);
				// 转换date和calendar
				Calendar calStart = Calendar.getInstance();
				calStart.setTime(startDate);
				Calendar calEnd = Calendar.getInstance();
				calEnd.setTime(endDate);
				// 给末尾的日期加一天，以让日期正确
				calEnd.add(Calendar.DAY_OF_YEAR, 1);
				// 如果处于起始和终止日期之间，则认为是节假日，返回true，终止循环
				if (cal.after(calStart) && cal.before(calEnd)) {
					flagForDate = true;
					log.info("我是假期，跳出了假期小循环，当前日期是" + cal.getTime().toString());
					break;
				}
			}
		}
		return flagForDate;
	}

	/**
	 * 
	 * @param 验证是否是调休日
	 * @return验证结果
	 */
	public boolean isChangeScale(Calendar cal) {
		boolean flagForChange = false;
		List<Dictionary> arrChangeScale = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.CHANGEHOLIDAY, 0);
		String changeScale = "";
		if (arrChangeScale.size() > 0) {
			changeScale = arrChangeScale.get(0).getDictionaryValue();
			log.info(changeScale + "这是调休日");
			String[] changeScaleList = changeScale.split(",");
			// 格式化日期
			SimpleDateFormat sdfforstart = new SimpleDateFormat("yyyyMMdd");
			// 循环找出所有的调休日来匹配当前日期是否是调休日
			for (String item : changeScaleList) {
				// 如果与调休日相同，则认为是调休日，返回true，终止循环
				if (sdfforstart.format(cal.getTime()).equals(item)) {
					flagForChange = true;
					log.info("我是调休日，我跳出了调休日小循环，当前日期是"
							+ cal.getTime().toString());
					break;
				}
			}
		}
		return flagForChange;
	}

	@SuppressWarnings("deprecation")
	/**
	 * 预约界面提交
	 * 
	 * @param session
	 * @param orderRecord
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/static/book/submitInfoCenter")
	public Map<String, Object> submitInfoCenter(HttpSession session,
			OrderRecords orderRecord, HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			NumberFormatException, ParseException {

		// log.info(orderRecord.getvCode());
		orderRecord.setTransactOrgName(URLDecoder.decode(
				orderRecord.getTransactOrgName(), "UTF-8"));
		orderRecord.setTransactTypeName(URLDecoder.decode(
				orderRecord.getTransactTypeName(), "UTF-8"));
		orderRecord.setName(URLDecoder.decode(orderRecord.getName(), "UTF-8"));
		orderRecord.setHourseAddress(URLDecoder.decode(
				orderRecord.getHourseAddress(), "UTF-8"));
		orderRecord.setHourseNumber(URLDecoder.decode(
				orderRecord.getHourseNumber(), "UTF-8"));
		orderRecord.setObligeename(URLDecoder.decode(
				orderRecord.getObligeename(), "UTF-8"));

		Map<String, Object> rtnMap = new HashMap<String, Object>();

		rtnMap.put("rtnCode", "9");
		// 如果在假期或周末，就不能约最大日期号
		Calendar cal = Calendar.getInstance();
		// 如果是假期或者此周末不是调休日 暂时什么也不做
		// 如果是工作日，则要判断他是否提前
		if (!(isDateScale(cal) || (!isChangeScale(cal) && isWeekend(cal)))) {
			// 如果他约的是最大日期的
			if (bookControlMapper.selectDateTop1().trim()
					.equals(orderRecord.getTransactDate().trim())) {
				// 获取到区县的放号时间
				Area area = areaMapper.getAreasByHandleOrgId(orderRecord
						.getTransactOrgId());
				int hour = Integer
						.valueOf(area.getOpenOrderTime().split(":")[0]);
				int minuteString = Integer.valueOf(area.getOpenOrderTime()
						.split(":")[1]);
				// 获取到当前时间
				int curruntHour = cal.get(Calendar.HOUR_OF_DAY);
				int curruntMinut = cal.get(Calendar.MINUTE);
				// 如果放号小时数大于当前小时数或者如果时间相同，且分钟数不为零，放号分钟数大于当前分钟数
				if (hour > curruntHour
						|| (hour == curruntHour && minuteString != 0 && minuteString > curruntMinut)) {
					rtnMap.put("rtnMsg", "请不要提前预约");
					return rtnMap;
				}
			}
		}

		// 如果在锁定期，则停止预约
		if (!ConstantVariable.flagIsFlushOver) {
			rtnMap.put("rtnMsg", "系统正忙");
			return rtnMap;
		}

		String openid = "ov5KSuMZ_s6KgM-h-pYal5ILyhRM";
		if (session.getAttribute("openid") != null) {
			openid = (String) session.getAttribute("openid");
		}
		if (!openid.equals(orderRecord.getOpenid())) {
			rtnMap.put("rtnMsg", "禁止预约");
			return rtnMap;
		}

		// 如果openid存在于封禁的openid表中，则禁止预约
		if (ipForbiddenMapper.IsForbiddenOpenId(openid) > 0) {
			rtnMap.put("rtnMsg", "您已被封禁,禁止预约");
			return rtnMap;
		}

		/************* 许云强 修改 校验是否在封停期 start *******************/
		User userTmp = userService.getUserInfoByOpenId(orderRecord.getOpenid());
		if (userTmp.getIsForbidden() != 0) {
			rtnMap.put("rtnMsg", "你已经被列入黑名单，禁止预约！");
			return rtnMap;
		}
		if (userTmp.getStoptime_status() == 1) {
			String stopTimeTmp = userTmp.getStoptime_end().toString();
			rtnMap.put("rtnMsg", "该账户已被封停，禁止预约，" + stopTimeTmp + "后可以使用！");
			return rtnMap;
		}

		boolean isIdcardForbidden = userService.isIdcardForbidden(orderRecord
				.getIdcard());
		if (isIdcardForbidden) {
			rtnMap.put("rtnMsg", "此身份证已被列入黑名单，禁止预约！");
			return rtnMap;
		}
		if (orderRecord.getTransactOrgId() != 1037
				&& orderRecord.getTransactOrgId() != 1047) {
			boolean isIdcardDuplicate = orderRecordsMapper
					.isIdcardDuplicate(orderRecord.getIdcard()) > 0;
			if (isIdcardDuplicate) {
				rtnMap.put("rtnMsg", "此身份证号有未处理的预约！");
				return rtnMap;
			}
		}

		/************* 许云强 修改 校验是否在封停期 end *******************/

		// ========================================防止提交过快的部分
		// 5分钟=========================================
		// 静海的可以很快提交
		java.sql.Timestamp ts = new java.sql.Timestamp(
				System.currentTimeMillis());

		boolean flagIsCooling = dictionaryService
				.queryIsCoolingArea(orderRecord.getTransactOrgId());

		if (flagIsCooling) {
			if (!orderRecord.getOpenid().equals("ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
				// 判断用户ip是否已经在库中，而且提交过快，在一分钟之内
				OrderRecords orderForIp = new OrderRecords();
				orderForIp = orderRecordsMapper
						.selectByFastIP(getIpAddr(request));
				if (orderForIp != null && orderForIp.getIPAddress() != "") {
					// 如果是今天的日期
					long current = System.currentTimeMillis();// 当前时间毫秒数
					long zero = current / (1000 * 3600 * 24)
							* (1000 * 3600 * 24)
							- TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
					if (orderForIp.getCreateTime().after(
							new java.util.Date(zero))) {
						log.info("零时" + zero);
						log.info(orderForIp.getIdcard() + "身份证号"
								+ orderRecord.getIdcard());
						if (orderForIp.getIdcard().equals(
								orderRecord.getIdcard())) {

						} else {
							if ((orderRecord.getTransactOrgId() == 1047 || orderRecord
									.getTransactOrgId() == 1045)) {
								rtnMap.put("rtnMsg", "该网段今天不可再约了");
								return rtnMap;
							}
							if ((ts.getTime() - orderForIp.getCreateTime()
									.getTime()) / 60000 < 30) {
								rtnMap.put("rtnMsg", "两次提交有30分钟的冷却时间，请耐心等待");
								return rtnMap;
							}
						}
					}
				}
			}
		}

		// ====================================防止提交过快end===============================================

		// 是否是需要进行排队预约的区县
		boolean flagIsQueue = dictionaryService.queryIsQueueArea(orderRecord
				.getTransactOrgId()) && testTimeInQueue(orderRecord);
		log.info(flagIsQueue + "是否排队区县");

		if (!flagIsQueue) {
			// 查询总数是否满了，满了就不能预约
			int memberSurplus = 0;
			memberSurplus = bookControlMapper.chenckMemberSurplus(
					orderRecord.getTransactDate(),
					String.valueOf(orderRecord.getTransactTimeId()),
					orderRecord.getTransactOrgId());
			if (memberSurplus <= 0) {
				rtnMap.put("rtnMsg", "您手慢了哦，剩余名额已被抢！请更换其他日期吧");
				return rtnMap;
			}
		}
		// 获取预约次数上限
		List<Dictionary> dictionary = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.APPOINTMENTSNUMBER,
						null);
		int number = 0;
		if (dictionary.size() == 0) {
			number = 1;
		} else {
			number = Integer.valueOf(dictionary.get(0).getDictionaryValue());
		}
		WorkTime workTime = workTimeMapper.findById(Integer.valueOf(orderRecord
				.getTransactTimeId()));

		if (flagIsQueue) {
			// 未处理的预约次数
			int appointmentNum = orderRecordsService
					.selectByOpenIdAndFlushOver(orderRecord.getOpenid()).size();
			if (orderRecord.getTransactOrgId() != 1037
					&& orderRecord.getTransactOrgId() != 1047) {
				if (!orderRecord.getOpenid().equals(
						"ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
					if (appointmentNum >= number) {
						rtnMap.put("rtnMsg", "您最多只能预约" + number + "次哦！");
						return rtnMap;
					}
				}
			}
		} else {
			// 未处理的预约次数
			int appointmentNum = orderRecordsService
					.selectByOpenIdAndStatusFast(orderRecord.getOpenid())
					.size();
			if (orderRecord.getTransactOrgId() != 1037
					&& orderRecord.getTransactOrgId() != 1047) {
				if (!orderRecord.getOpenid().equals(
						"ov5KSuMZ_s6KgM-h-pYal5ILyhRM")) {
					if (appointmentNum >= number) {
						rtnMap.put("rtnMsg", "您最多只能预约" + number + "次哦！");
						return rtnMap;
					}
				}
			}
		}

		RecordType recordType = fillModel(orderRecord, workTime);

		// 此处的方法是带锁的
		if (!flagIsQueue) {
			Map<String, Object> map = findRecordType(recordType, rtnMap);
			if (map != null) {
				return map;
			}
		}
		orderRecord.setTransactTimeId((int) workTime.getId());
		orderRecord.setTransactTime(workTime.getStartTime() + "-"
				+ workTime.getEndTime());
		orderRecord.setDeadTime(workTime.getEndTime());

		orderRecord.setIPAddress(getIpAddr(request));
		boolean flagUseSocket = false;
		// 如果是需要自动审核的区县，在sendDataTemp中插入一条预约审核信息。并且现将order表预约项当前状态设为未发送
		if (dictionaryService.queryIsUseSocket(orderRecord.getTransactOrgId())) {
			// orderRecord.setExamineStatus(ConstantVariable.preSendDataStatus);
			flagUseSocket = true;
		}
		int intId = orderRecordsService.save(orderRecord, flagIsQueue,
				recordType);

		if (intId <= 0) {
			rtnMap.put("rtnMsg", "您未能提交成功，请稍候重试");
			return rtnMap;
		} else {
			if (flagUseSocket) {
				SendDataTemp sendModel = new SendDataTemp();
				sendModel.setArea(orderRecord.getTransactOrgId());
				sendModel.setHourseNumber(orderRecord.getHourseNumber());
				sendModel.setOrderId(intId);
				sendModel.setIdcard(orderRecord.getIdcard());
				sendModel.setTransactType(webSocketUtil.switchType(orderRecord
						.getTransactTypeName()));
				sendModel.setName(orderRecord.getName());
				sendDataTempMapper.addSendDataTemp(sendModel);
			}
		}
		if (flagIsQueue) {
			rtnMap.put("rtnInQueue", "1");
			rtnMap.put("rtnCode", "0");
			rtnMap.put("rtnMsg", "您的申请已经加入排队，排队结果可查询个人中心或等待消息提醒");
			return rtnMap;
		} else {
			rtnMap.put("rtnCode", "0");
			return rtnMap;
		}
	}

	// =================================================增加政务平台提交==================================
}
