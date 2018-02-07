package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.leyidai.web.controller.OrderRecordsController;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.MyHttpRequest;
import com.mysql.jdbc.log.Log;

public class MyHttpRequestTest {
	@Autowired
	private DictionaryService dictionaryService;
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(MyHttpRequestTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSendGetStringMapOfStringObjectString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSendPostStringMapOfStringObjectString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSendGetStringStringString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSendPostStringStringString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testTest() {

		// fail("Not yet implemented");
		// 只排队6分钟，超过6分钟就停止
		Calendar c = Calendar.getInstance();
		// 获取到当天0时
		Calendar AllcationNumTime = Calendar.getInstance();
		AllcationNumTime.set(Calendar.HOUR_OF_DAY, 0);
		AllcationNumTime.set(Calendar.MINUTE, 0);
		AllcationNumTime.set(Calendar.SECOND, 0);

		// 根据0时获取到号池放号时间
		AllcationNumTime.set(Calendar.HOUR_OF_DAY, 8);
		AllcationNumTime.set(Calendar.MINUTE, 58);

		// 获取到放号时间4分钟内
		Calendar endTimeCalendar = (Calendar) AllcationNumTime.clone();
		endTimeCalendar.add(Calendar.MINUTE, 4);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		log.info(sdf.format(AllcationNumTime.getTime())
				+ sdf.format(endTimeCalendar.getTime()));
		System.out.println(sdf.format(c.getTime()));
		if (c.getTime().after(AllcationNumTime.getTime()))
			log.info("true");
		if (c.getTime().before(endTimeCalendar.getTime()))
			log.info("true");
		if (c.getTime().after(AllcationNumTime.getTime())
				&& c.getTime().before(endTimeCalendar.getTime())) {
			log.info("true");
		}
	}

}
