package com.leyidai.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.leyidai.web.mapper.IpForbiddenMapper;
import com.leyidai.web.util.DateUtil;

@Service
public class IpForbiddenService {
	private static final Logger log = LoggerFactory
			.getLogger(IpForbiddenService.class);

	@Autowired
	IpForbiddenMapper ipMapper;

	/**
	 * 判断ip是否被禁止
	 * 
	 * @param ip
	 * @return
	 */
	public boolean IsIpForbidden(String ip) {

		if (ipMapper.IsForbiddenIp(ip) > 0)
		{
			return true;
		}else {
			return false;
		}
	}

}
