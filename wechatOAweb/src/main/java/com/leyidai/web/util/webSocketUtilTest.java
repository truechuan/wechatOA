package com.leyidai.web.util;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.leyidai.entity.SendDataTemp;
import com.leyidai.web.mapper.BookControlMapper;
import com.leyidai.web.mapper.SendDataTempMapper;
import com.leyidai.web.schedule.WorkerScheduler;
import com.mysql.jdbc.log.Log;

public class webSocketUtilTest {

	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(WorkerScheduler.class);
	@Autowired
	private BookControlMapper sendDataTempMapper;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWebSockte() {
//		fail("Not yet implemented");
//		SendDataTemp sendDataTemp=new SendDataTemp();
//		sendDataTemp.setArea(1);
//		sendDataTemp.setHourseNumber("1");
//		sendDataTemp.setIdcard("1");
//		sendDataTemp.setName("1");
//		sendDataTemp.setStatus(1);
//		sendDataTemp.setTransactType(1);
//		JSONObject jsonObject = JSONObject.fromObject(webSocketUtil.webSockte(sendDataTemp));
//		System.out.println(jsonObject.get("returnMsg").toString().equals("1"));
//		System.out.println(sendDataTempMapper.selectStatusDefault().size());
//		WorkerScheduler workerScheduler=new WorkerScheduler();
//		workerScheduler.refreshExamineData();
	}

	@Test
	public void testAssemblyJsonString() {
//		log.info(sendDataTempMapper.selectStatusDefault().get(0).getIdcard());
		log.info(sendDataTempMapper.selectDateTop1());
//		fail("Not yet implemented");
	}

	@Test
	public void testSwitchType() {
//		fail("Not yet implemented");
	}

}
