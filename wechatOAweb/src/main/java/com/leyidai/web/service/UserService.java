package com.leyidai.web.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.leyidai.entity.User;
import com.leyidai.web.mapper.LogicUserMapper;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.Md5Tools;

/**
 * 用户信息Service
 * 
 * @author Administrator
 * 
 */
@Service
public class UserService {
	private static final Logger log = LoggerFactory
			.getLogger(UserService.class);
	@Autowired
	private LogicUserMapper userMapper;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private Md5Tools md5Tools;

	/**
	 * 添加新用户
	 * 
	 * @param loginUser
	 * @return
	 */
	@Transactional
	public Map<String, String> insertUser(User user) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		rtnMap.put("rtnCode", "9");

		if (user == null) {
			log.info("please don't insert null object user");
			throw new RuntimeException("please don't insert null object user");
		}

		if (user.getIdcard().isEmpty()) {
			log.info("idcard {} is null", user.getIdcard());
			rtnMap.put("rtnMsg", "身份证不能为空！");
			return rtnMap;
		}

//		if (isIdcardExist(user.getIdcard())) {
//			log.info("idcard {} had existed", user.getIdcard());
//			rtnMap.put("rtnMsg", "身份证已存在！");
//			return rtnMap;
//		}

		user.setIdcardmd5(md5Tools.encoderByMd5(user.getIdcard()));
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setStatus(1);

		if (user.getId() == 0) {
			if (getUserInfoByOpenId(user.getOpenid()) != null) {
				log.info("openid {} had existed", user.getOpenid());
				rtnMap.put("rtnMsg", "openid已存在！");
				return rtnMap;
			} else {
				userMapper.addUser(user);
			}
		} else {
			userMapper.update(user);
		}
		long userId = user.getId();
		log.info("insert login user {} success, id is {}", user.getName(),
				userId);
		rtnMap.put("rtnCode", "0");

		return rtnMap;
	}

	/**
	 * 修改用户
	 * 
	 * @param loginUser
	 * @return
	 */
	@Transactional
	public Map<String, String> updateUser(User user) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		rtnMap.put("rtnCode", "9");

		if (user == null) {
			log.info("please don't insert null object user");
			throw new RuntimeException("please don't insert null object user");
		}

//		User oldUser = userMapper.getUserInfoByOpenId(user.getOpenid());
//				getUserInfoById(user.getId());

		// if(oldUser.getMobile() != user.getMobile() &&
		// isMobileExist(user.getMobile())){
		// log.info("username {} had existed", user.getName());
		// rtnMap.put("rtnMsg", "手机号已存在！");
		// return rtnMap;
		// }

		if (user.getIdcard().isEmpty()) {
			log.info("idcard {} is null", user.getIdcard());
			rtnMap.put("rtnMsg", "身份证不能为空！");
			return rtnMap;
		}

//		if (!oldUser.getIdcard().equals(user.getIdcard())
//				&& isIdcardExist(user.getIdcard())) {
//			log.info("idcard {} had existed", user.getIdcard());
//			rtnMap.put("rtnMsg", "身份证已存在！");
//			return rtnMap;
//		}

		user.setIdcardmd5(md5Tools.encoderByMd5(user.getIdcard()));
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setStatus(1);
		if (user.getId() == 0) {
			if (getUserInfoByOpenId(user.getOpenid()) != null) {
				log.info("openid {} had existed", user.getOpenid());
				rtnMap.put("rtnMsg", "openid已存在！");
				return rtnMap;
			} else {
				userMapper.addUser(user);
			}
		} else {
			user.setStatus(1);
			userMapper.update(user);
		}
		long userId = user.getId();
		log.info("insert login user {} success, id is {}", user.getName(),
				userId);
		rtnMap.put("rtnCode", "0");
		return rtnMap;
	}

	/**
	 * 用户名获取LoginUsers
	 * 
	 * @param username
	 * @return
	 */
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) {

		User loginUser = userMapper.getUserByUsername(username);

		if (loginUser != null) {

			log.info("user {} exist", username);
		} else {

			log.info("user {} doesn't exist", username);
		}

		return loginUser;
	}

	/**
	 * 根据身份证号码查询userInfo
	 * 
	 * @param mobile
	 * @return
	 */
	@Transactional(readOnly = true)
	public User getUserInfoByIdCardMd5(String idCardMd5) {

		return userMapper.getUserInfoByIdCardMd5(idCardMd5);
	}

	/**
	 * 根据openid查询用户信息
	 * 
	 * @param openId
	 * @return
	 */
	public User getUserInfoByOpenId(String openId) {
		return userMapper.getUserInfoByOpenId(openId);
	}

	/**
	 * 手机号是否存在
	 * 
	 * @param mobile
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isMobileExist(long mobile) {

		User userInfo = userMapper.getUserInfoByMobile(mobile);
		log.debug("check mobile {} if exist {}", mobile, userInfo != null);

		return userInfo != null;
	}

	/**
	 * 身份证号是否存在
	 * 
	 * @param mobile
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isIdcardExist(String idcard) {

		User userInfo = userMapper.getUserInfoByIdcard(idcard);
		log.debug("check idcard {} if exist {}", idcard, userInfo != null);

		return userInfo != null;
	}

	/**
	 * 身份证号是否被限制为黑名单
	 * 
	 * @param mobile
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isIdcardForbidden(String idcard) {

		List<User> listUsers = userMapper.getUserListByIdcard(idcard);
		if (listUsers != null) {
			for (User item : listUsers) {
				// 发现有不是0的返回真
				if(item.getIsForbidden() != 0)
					return true;
			}
		}
		return false;
	}

	//
	// /**
	// * id获取User
	// */
	// @Transactional(readOnly = true)
	// public User getUserByUserId(int userId){
	//
	// User loginUser = userMapper.getUserByUserId(userId);
	//
	// return loginUser;
	// }
	//
	// /**
	// * 用户名是否存在
	// * @param username
	// * @return
	// */
	// @Transactional(readOnly = true)
	// public boolean isUsernameExist(String username){
	//
	// User loginUser = userMapper.getUserByUsername(username);
	// log.info("check user {} if exist, result is {}", username, loginUser !=
	// null);
	//
	// return loginUser != null;
	// }
	//

	//
	// /**
	// * 更新登录信息
	// * @param username
	// * @param loginIp
	// */
	// @Transactional
	// public void updateLoginInfo(String username, String loginIp, ClientType
	// loginType){
	//
	// User user = userMapper.getUserByUsername(username);
	// if(user == null){
	//
	// log.info("please don't update null object loginUser");
	// throw new RuntimeException("please don't update null object loginUser");
	// }
	//
	// user.setLastLoginTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
	// user.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
	// user.setLoginIp(loginIp);
	// user.setLoginTimes(user.getLoginTimes() + 1);
	// user.setLoginType(loginType);
	// log.info("update login info, username {}, login ip {}", username,
	// loginIp);
	//
	// userMapper.updateUser(user);
	// }
	//
	// /**
	// * 更新密码
	// * @param username
	// * @param password
	// */
	// @Transactional
	// public void updatePassword(String username, String password){
	//
	// User user = userMapper.getUserByUsername(username);
	// if(user == null){
	//
	// log.info("please don't update null object user, username {}", username);
	// throw new
	// RuntimeException("please don't update null object user, username " +
	// username);
	// }
	//
	// user.setPassword(bcryptEncoder.encode(password));
	// user.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
	// userMapper.updateUser(user);
	// }
	//
	// @Transactional(readOnly=true)
	// public boolean isPasswordCorrect(String username, String password){
	// User user = userMapper.getUserByUsername(username);
	// if(user == null){
	//
	// log.info("username not exist {}", username);
	// throw new RuntimeException("username not exit " + username);
	// }
	//
	// return bcryptEncoder.matches(password, user.getPassword());
	// }
	//
	// /**
	// * 设置手机号
	// * @param userId
	// * @param mobile
	// */
	// @Transactional
	// public UserInfo saveOrUpdateMobile(int userId, String mobile){
	//
	// UserInfo userInfo = userMapper.getUserInfoByUserId(userId);
	// if(userInfo == null){
	//
	// userInfo = new UserInfo();
	// }
	//
	// userInfo.setMobile(mobile);
	// saveOrUpdateUserInfo(userId, userInfo);
	//
	// return userInfo;
	// }
	//
	// /**
	// * 设置真实姓名
	// * @param userId
	// * @param realName
	// * @return
	// */
	// @Transactional
	// public UserInfo saveOrUpdateRealName(int userId, String realName,String
	// identification){
	// UserInfo userInfo = userMapper.getUserInfoByUserId(userId);
	// if(userInfo == null){
	//
	// userInfo = new UserInfo();
	// }
	//
	// userInfo.setRealName(realName);
	// userInfo.setIdentification(identification);
	// saveOrUpdateUserInfo(userId, userInfo);
	//
	// return userInfo;
	// }
	//
	//
	// @Transactional
	// public UserInfo saveOrUpdateUserInfo(int userId, UserInfo userInfo){
	//
	// if(userInfo.getUserId() > 0){
	//
	// userInfo.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
	// userMapper.updateUserInfo(userInfo);
	// } else{
	//
	// userInfo.setUserId(userId);
	// userInfo.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
	// userMapper.addUserInfo(userInfo);
	// }
	//
	// return userInfo;
	// }
	//
	// /**
	// * 根据userId查询userinfo
	// */
	// @Transactional(readOnly = true)
	// public UserInfo getUserInfoByUserId(int userId){
	//
	// return userMapper.getUserInfoByUserId(userId);
	// }
	//
	// /**
	// * 根据手机号码查询userInfo
	// * @param mobile
	// * @return
	// */
	// @Transactional(readOnly = true)
	// public UserInfo getUserInfoByMobile(String mobile){
	//
	// return userMapper.getUserInfoByMobile(mobile);
	// }
	//

}
