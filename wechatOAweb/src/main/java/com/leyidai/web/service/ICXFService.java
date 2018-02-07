package com.leyidai.web.service;

import java.util.Map;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface ICXFService {

	public Map<String, Object> loadUserInfoByIdCard(String idCardMd5);
	
	public String updateOrderTaking(String idCardMd5, int orderTaking);
}
