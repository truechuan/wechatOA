package com.leyidai.web.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyidai.web.mapper.CountQueryMapper;
import com.leyidai.web.util.ConstantVariable;
import com.leyidai.web.util.SiteUtil;
import com.leyidai.web.util.webSocketUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

@Controller
public class QueryFileStatusController {
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(OrderRecordsController.class);
	@Autowired
	private CountQueryMapper countQueryMapper;
	@RequestMapping(value = "/queryFileStatus", method = RequestMethod.GET)
	public String queryFileStatus() {
		return "queryFileStatus";
	}

	@ResponseBody
	@RequestMapping(value = "/queryFileStatusSubmit",
			method = RequestMethod.GET)
	public Map<String, Object> queryFileStatus(String strFileID, String strZjhm)
			throws DocumentException {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		String param = "strFileID=" + strFileID + "&strZjhm=" + strZjhm;
		String postResult = SiteUtil.sendPost2(
				ConstantVariable.queryFileStatusSocket, param);
		try {
			countQueryMapper.updateCount();
		} catch (Exception e) {
		}
		if (postResult == null || postResult.length() <= 0
				|| postResult.equals("false")) {
			log.info("post 错误");
			rtnMap.put("rtnCode", "9");
			rtnMap.put("rtnMsg", "接口请求错误或超时");
		} else {
			Document doc = DocumentHelper.parseText(webSocketUtil
					.replaceBlank(postResult));
			if (doc.selectNodes("//string").size() > 0) {
				rtnMap.put("rtnCode", "0");
				rtnMap.put("rtnMsg", doc.selectSingleNode("//string").getText());
			} else {
				log.info("找不到返回结果");
				rtnMap.put("rtnCode", "9");
				rtnMap.put("rtnMsg", "接口请求错误或超时");
			}
		}
		return rtnMap;
	}

}
