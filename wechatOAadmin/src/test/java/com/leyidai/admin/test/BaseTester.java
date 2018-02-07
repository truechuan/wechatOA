//package com.leyidai.admin.test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.leyidai.admin.mapper.AreaMapper;
//import com.leyidai.admin.mapper.BookControlMapper;
//import com.leyidai.admin.mapper.NoticeMapper;
//import com.leyidai.admin.mapper.TypeLimitRecordMapper;
//import com.leyidai.admin.mapper.WorkTimeMapper;
//import com.leyidai.admin.schedule.WorkerScheduler;
//import com.leyidai.admin.service.DictionaryService;
//import com.leyidai.admin.util.CategoryConstants;
//import com.leyidai.entity.BookControl;
//import com.leyidai.entity.Dictionary;
//import com.leyidai.entity.Notice;
//import com.leyidai.entity.RecordType;
//import com.leyidai.entity.WorkTime;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"/spring/business-config.xml"})
//public class BaseTester {
//
//	@Autowired
//	private WorkTimeMapper worTimeMapper;
//	@Autowired
//	private DictionaryService dictionaryService;
//	@Autowired
//	private BookControlMapper bookControlMapper;
//	@Autowired
//	private NoticeMapper noticeMapper;
//	@Autowired
//	private AreaMapper areaMapper;
//	@Autowired
//	private TypeLimitRecordMapper typeLimitRecordMapper;
//	private static Logger log = LoggerFactory.getLogger(WorkerScheduler.class);
//
//	public void createWorkTime() throws ParseException {
//		List<WorkTime> delWorkTime = worTimeMapper.findByDel();
//		for (WorkTime item : delWorkTime) {
//			bookControlMapper.delBydelByAreaIdAndTimeId(
//					String.valueOf(item.getAreaId()),
//					String.valueOf(item.getId()));
//		}
//		// 获取预约天数
//		List<Dictionary> arrBookDate = dictionaryService
//				.getDictionarysByCatetory(CategoryConstants.BOOKDATE, 0);
//		Integer bookDate = 0;
//		if (arrBookDate.size() > 0) {
//			bookDate = Integer.valueOf(arrBookDate.get(0).getDictionaryValue());
//
//		}
//		// 获取节假日
//		List<Dictionary> arrDateScale = dictionaryService
//				.getDictionarysByCatetory(CategoryConstants.HOLIDAY, 0);
//		String DateScale = "";
//		if (arrDateScale.size() > 0) {
//			DateScale = arrDateScale.get(0).getDictionaryValue();
//		}
//		log.info(DateScale + "这是节假日");
//		// 获取调休日
//		List<Dictionary> arrChangeScale = dictionaryService
//				.getDictionarysByCatetory(CategoryConstants.CHANGEHOLIDAY, 0);
//		String changeScale = "";
//		if (arrDateScale.size() > 0) {
//			changeScale = arrChangeScale.get(0).getDictionaryValue();
//		}
//		log.info(changeScale + "这是调休日");
//		String[] dateScaleList = DateScale.split(",");
//		String[] changeScaleList = changeScale.split(",");
//		// 变量定义
//		Date startDate;
//		Date endDate;
//		Date changeHoliday;
//
//		List<Dictionary> handleOrg = dictionaryService
//				.getDictionarysByCatetory(CategoryConstants.HANDLEORG, 0);
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdfforstart = new SimpleDateFormat("yyyyMMdd");
//		List<String> arrDays = new ArrayList<String>();
//		// 定义一个标识字段用于判定是否跳出大循环
//
//		for (int i = 0; i < 20; i++) {
//			Boolean Continueflag = false;
//			Boolean ContinueFlagForChange = false;
//			calendar.add(Calendar.DAY_OF_YEAR, 1);
//			// 循环找出所有的假期来匹配看是否是假期
//			for (String item : dateScaleList) {
//				// 起始日期
//				startDate = sdfforstart.parse(item.split("-")[0]);
//				// 结束日期
//				endDate = sdfforstart.parse(item.split("-")[1]);
//
//				// 转换date和canlender
//				Calendar calStart = Calendar.getInstance();
//				calStart.setTime(startDate);
//				Calendar calEnd = Calendar.getInstance();
//				calEnd.setTime(endDate);
//				// 给末尾的日期加一天，以让日期正确
//				calEnd.add(Calendar.DAY_OF_YEAR, 1);
//				// 如果处于起始和终止日期之间，则认为是节假日，要跳过
//				if (calendar.after(calStart) && calendar.before(calEnd)) {
//					Continueflag = true;
//					log.info("我跳出了假期小循环，当前日期是" + calendar.getTime().toString());
//					break;
//				}
//				log.info(Continueflag.toString());
//			}
//			// 小循环看当前日期是否是调休日，就设置为跳过状态
//			for (String item : changeScaleList) {
//				if (sdfforstart.format(calendar.getTime()).equals(item)) {
//					ContinueFlagForChange = true;
//					log.info("我跳出了调休日小循环，当前日期是" + calendar.getTime().toString());
//				}
//			}
//			if (Continueflag) {
//				continue;
//			}
//			// 如果判定这一天是调休日就不执行周末的分支，否则执行周末的分支
//			if (!ContinueFlagForChange) {
//				if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
//						|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//					continue;
//				}
//			}
//
//			arrDays.add(sdf.format(calendar.getTime()));
//			if (arrDays.size() == bookDate + 1) {
//				break;
//			}
//		}
//
//		// 最外层循环是区域的
//		for (Dictionary item : handleOrg) {
//			List<WorkTime> workTime = worTimeMapper.findByAreaId(item.getId());
//			List<Notice> notices = noticeMapper.geNoticesByAreaId(areaMapper.getAreaByHandleOrgId(item.getId()).getAreaCode());
//			RecordType recordType = new RecordType();
//			// 第二层循环是天数的
//			for (String day : arrDays) {
//				BookControl queryItem = new BookControl();
//				queryItem.setAreaId(item.getId());
//				queryItem.setDate(day);
//				List<BookControl> controls = bookControlMapper.find(queryItem);
//				if (controls.size() > 0) {
//					continue;
//				} else {
//					BookControl insertItem = null;
//					// 第三层循环是办理时间的
//					for (WorkTime time : workTime) {
//						insertItem = new BookControl();
//						insertItem.setAreaId(item.getId());
//						insertItem.setAreaName(item.getDictionaryValue());
//						insertItem.setBookMember(0);
//						insertItem.setDate(day);
//						insertItem.setMembers(time.getMembers());
//						insertItem.setTime(time.getStartTime() + "-"
//								+ time.getEndTime());
//						insertItem.setTimeId((int) time.getId());
//						// insertItem.setNoticeId(time.getNoticeId());
//						// insertItem.setNoticeName(time.getNoticeName());
//						bookControlMapper.insert(insertItem);
//						for (Notice noticeItem : notices) {
////							log.info("第三层循环开始"+i++);
//							recordType.setArea(areaMapper.getAreaByHandleOrgId(item.getId()).getAreaCode());
//							recordType.setDate(day);
//							recordType.setTransacttypeid(noticeItem.getId());
//							recordType.setOrderlimit(noticeItem.getNumLimit());
//							recordType.setOrderednum(0);
//							recordType.setTime(time.getStartTime() + "-"
//									+ time.getEndTime());
//							// 如果找到了这一天这个区的这种类型限制数据，就不再生成
//							if (typeLimitRecordMapper.find(recordType).size() > 0) {
//								continue;
//							} else {
//								typeLimitRecordMapper.insert(recordType);
//							}
//						}
//					}
//				}
//			}
//		}
//
//		// 循环出预约类型和预约上限表
//		// 外层循环是区域
////		List<Area> areas = areaMapper.getAreas();
////		RecordType recordType = new RecordType();
////		for (Area item : areas) {
//////			log.info("最外层循环开始");
////			List<Notice> notices = noticeMapper.geNoticesByAreaId(item.getAreaCode());
////			// 第二层循环是天数的
////			for (String day : arrDays) {
////				// 获取到每个区的预约类型
//////				log.info("第二层层循环开始");
//////				int i=0;
////				for (Notice noticeItem : notices) {
//////					log.info("第三层循环开始"+i++);
////
////					recordType.setArea(item.getAreaCode());
////					recordType.setDate(day);
////					recordType.setTransacttypeid(noticeItem.getId());
////					recordType.setOrderlimit(noticeItem.getNumLimit());
////					recordType.setOrderednum(0);
////					// 如果找到了这一天这个区的这种类型限制数据，就不再生成
////					if (typeLimitRecordMapper.find(recordType).size() > 0) {
////						continue;
////					} else {
////						typeLimitRecordMapper.insert(recordType);
////					}
////				}
////			}
////		}
//	}
//}
