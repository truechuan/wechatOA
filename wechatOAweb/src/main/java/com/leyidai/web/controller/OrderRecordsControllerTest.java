package com.leyidai.web.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.leyidai.entity.OrderRecords;

public class OrderRecordsControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAssemblyJsonString() {
		OrderRecordsController orderRecordsController=new OrderRecordsController();
//		System.out.println(orderRecordsController.assemblyJsonString(91210,"张曦","120101198809074043","2005-0060791",1040,1));
//		fail("Not yet implemented");
	}

	@Test
	public void testWebSockte() {
		
	}

	@Test
	public void webSockteTest()
	{
		//构造测试数据
		OrderRecords orderModel=new OrderRecords();
		orderModel.setTransactOrgId(1041);
		orderModel.setTransactTypeName("新房办房本");
		orderModel.setIdcard("370828198604205315");
		orderModel.setName("刘兆文");
		orderModel.setHourseNumber("2015-0083745");
		orderModel.setTransactTime("09:00-11:00");
		orderModel.setTransactTypeId(996);
		orderModel.setTransactDate("2016-05-31");
		orderModel.setTransactOrgName("东丽区");
		
		OrderRecordsController orderRecordsController=new OrderRecordsController();
//		String jsonString = orderRecordsController.assemblyJsonString(orderModel);

//		String param = "strJson=[" + jsonString+"]";
////		String msg=orderRecordsController.webSockte(orderModel);
//		System.out.println(param);
	}
	
}
