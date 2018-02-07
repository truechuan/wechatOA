package test;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import com.leyidai.entity.OrderRecords;
import com.leyidai.web.util.ConstantVariable;
import com.leyidai.web.util.MyHttpRequest;
import com.leyidai.web.util.SiteUtil;

public class SiteUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSendGet() {
		// System.out.println(SiteUtil.sendGet("http://www.baidu.com", ""));
		// System.out.println(MyHttpRequest.sendPost("http://gc.ditu.aliyun.com/geocoding",
		// "a=苏州市","utf-8"));
	}

	@Test
	public void testFilterNumber() {
		// System.out.println(SiteUtil.filterNumber("2017zhogong86553中国人什么的**&&…………#@*（）@"));
		// System.out.println(SiteUtil.filterNumberM("哈哈哈20172788凡达发大水"));

		OrderRecords orderModel = new OrderRecords();
		orderModel.setTransactOrgId(1041);
		orderModel.setTransactTypeName("新房办房本");
		orderModel.setIdcard("120105198907074560");
		orderModel.setName("刘莉莉");
		orderModel.setHourseNumber("2014—0148801");
		orderModel.setTransactTime("09:00-11:00");
		orderModel.setTransactTypeId(1);
		orderModel.setTransactDate("2016-05-31");
		orderModel.setTransactOrgName("东丽区");

		for (String item : ConstantVariable.strJsonStrings) {
			JSONObject jsonObject = JSONObject.fromObject(item);
			if (jsonObject.get("name").toString()
					.equals(orderModel.getName().trim())
					&& jsonObject.get("idcard").toString()
							.equals(orderModel.getIdcard().trim())
					&& jsonObject.get("hourseNumber").toString()
							.equals(orderModel.getHourseNumber().trim())
					&& Integer.valueOf(jsonObject.get("transactType")
							.toString()) == orderModel.getTransactTypeId()
					&& Integer.valueOf(jsonObject.get("area").toString()) == orderModel
							.getTransactOrgId()) {
				System.out.println("成功找到一个");
				break;
			}
		}

	}

}
