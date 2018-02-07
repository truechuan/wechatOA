/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : estatedb

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-05-29 23:55:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c3p0testtable`
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable` (
  `a` char(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c3p0testtable
-- ----------------------------

-- ----------------------------
-- Table structure for `estatedb_book_control`
-- ----------------------------
DROP TABLE IF EXISTS `estatedb_book_control`;
CREATE TABLE `estatedb_book_control` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL COMMENT '日期',
  `timeId` int(11) DEFAULT NULL COMMENT '办理时间表外键',
  `time` varchar(255) DEFAULT NULL COMMENT '办理时间',
  `areaId` int(11) DEFAULT NULL COMMENT '办理机构id',
  `areaName` varchar(255) DEFAULT NULL COMMENT '办理机构名称',
  `members` int(11) DEFAULT '0' COMMENT '默认预约人数',
  `bookMember` int(11) DEFAULT '0' COMMENT '实际预约人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of estatedb_book_control
-- ----------------------------
INSERT INTO `estatedb_book_control` VALUES ('1', '2016-05-30', '1', '13:00-13:30', '1034', '不动产登记事务中心和平部', '10', '4');

-- ----------------------------
-- Table structure for `estatedb_order_records`
-- ----------------------------
DROP TABLE IF EXISTS `estatedb_order_records`;
CREATE TABLE `estatedb_order_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `openid` varchar(255) DEFAULT NULL COMMENT 'openid',
  `transactOrgId` int(11) NOT NULL COMMENT '办理机构id',
  `transactOrgName` varchar(255) DEFAULT NULL COMMENT '办理机构名称',
  `transactTypeId` int(11) NOT NULL COMMENT '业务类型id',
  `transactTypeName` varchar(255) DEFAULT NULL COMMENT '业务类型名称',
  `transactDate` varchar(255) DEFAULT NULL COMMENT '办理日期',
  `transactTimeId` int(11) DEFAULT '0' COMMENT '办理时间表外键',
  `transactTime` varchar(255) DEFAULT NULL COMMENT '办理时间段（13:00-14:30）',
  `deadTime` time DEFAULT NULL COMMENT '过期时间',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `idcard` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `noticeFlg` int(1) DEFAULT NULL COMMENT '短信是否通知（0:通知 1：不通知）',
  `area` varchar(255) DEFAULT NULL COMMENT '行政区',
  `hourseNumber` varchar(255) DEFAULT NULL COMMENT '房产登记字号',
  `hourseAddress` varchar(255) DEFAULT NULL COMMENT '房产证地址',
  `orderTaking` int(11) DEFAULT '0' COMMENT '取号顺序',
  `numberOfTake` int(1) DEFAULT '0' COMMENT '取号次数',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(1) DEFAULT '0' COMMENT '状态',
  `del` int(1) DEFAULT '0' COMMENT '是否删除',
  `failReason` varchar(255) DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of estatedb_order_records
-- ----------------------------
INSERT INTO `estatedb_order_records` VALUES ('8', '0', 'ov5KSuHjtkGq2sMhVyC1s8mpJV-M', '1034', '不动产登记事务中心和平部', '0', '新建商品房买卖的转移登记', '2016-05-30', '0', '13:00 13:30', '13:30:00', '张松涛', '120222798803056212', '15822578742', '0', null, '把', '啊啊啊啊', '0', '0', '2016-05-29 20:29:48', '4', '0', null);
INSERT INTO `estatedb_order_records` VALUES ('9', '0', 'ov5KSuF4dWroSjsaofDZwaM-tb9g', '1034', '不动产登记事务中心和平部', '0', '新建商品房买卖的转移登记', '2016-05-30', '0', '13:00 13:30', '13:30:00', '宋', '120222199002054414', '15902266763', '0', null, '兔兔', '噢噢', '0', '0', '2016-05-29 22:28:28', '1', '0', null);
INSERT INTO `estatedb_order_records` VALUES ('10', '0', 'ov5KSuHjtkGq2sMhVyC1s8mpJV-M', '1034', '不动产登记事务中心和平部', '0', '新建商品房买卖的转移登记', '2016-05-30', '0', '13:00-13:30', '13:30:00', '张松涛', '120222798803056212', '15822578742', '0', null, '啊', '啊', '0', '0', '2016-05-29 23:16:52', '2', '0', null);
INSERT INTO `estatedb_order_records` VALUES ('11', '0', 'ov5KSuJi0WTweExk5VXhw2OQa_f4', '1034', '不动产登记事务中心和平部', '0', '新建商品房买卖的转移登记', '2016-05-30', '0', '13:00-13:30', '13:30:00', '咪咪', '130222167809084567', '13516856789', '0', null, '46836957905789', '大护肤科技风雨交加', '0', '0', '2016-05-29 23:27:50', '4', '0', null);

-- ----------------------------
-- Table structure for `estatedb_user`
-- ----------------------------
DROP TABLE IF EXISTS `estatedb_user`;
CREATE TABLE `estatedb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `mobile` bigint(11) DEFAULT NULL COMMENT '手机号',
  `idcardType` int(1) DEFAULT NULL COMMENT '证件类型id',
  `idcardName` varchar(255) DEFAULT NULL COMMENT '证件类型名称',
  `idcard` varchar(255) DEFAULT NULL COMMENT '证件号',
  `idcardmd5` varchar(255) DEFAULT NULL COMMENT '证件号MD5',
  `idcardImgUrl` varchar(255) DEFAULT NULL COMMENT '证件照片地址',
  `idcardImgUrl2` varchar(255) DEFAULT NULL COMMENT '证件照片地址2',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '0' COMMENT '0：待审核；1：通过；2：未通过',
  `type` int(1) DEFAULT '1' COMMENT '状态  1：个人；2：企业',
  `lastLoginTime` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `reason` varchar(255) DEFAULT NULL,
  `loginTimes` int(11) DEFAULT NULL COMMENT '登录次数',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of estatedb_user
-- ----------------------------
INSERT INTO `estatedb_user` VALUES ('9', 'ov5KSuHjtkGq2sMhVyC1s8mpJV-M', '张松涛', '15822578742', '0', null, '120222798803056212', '4xyLcoQrrHkzt6sluBEp0g==', 'http://www.tjsbdcdjzx.com//upload/images/users/1464524909022.jpeg ', 'http://www.tjsbdcdjzx.com//upload/images/users/1464524915483.jpeg ', '2016-05-29 20:28:37', '1', '1', null, null, null, '2016-05-29 23:43:06');
INSERT INTO `estatedb_user` VALUES ('10', 'ov5KSuF4dWroSjsaofDZwaM-tb9g', '宋', '15902266763', '0', null, '120222199002054414', 'hFEnHE3JfUfAnxRnVG+nMA==', 'http://www.tjsbdcdjzx.com//upload/images/users/1464531547699.png ', 'http://www.tjsbdcdjzx.com//upload/images/users/1464531644148.png ', '2016-05-29 22:20:52', '1', '1', null, null, null, '2016-05-29 22:27:36');
INSERT INTO `estatedb_user` VALUES ('11', 'ov5KSuBeH1szZ3VHxZ0JWlAAMCxs', '滚滚滚', '15888888888', '0', null, '120222199002054414', 'hFEnHE3JfUfAnxRnVG+nMA==', 'http://www.tjsbdcdjzx.com//upload/images/users/1464533335479.jpeg ', 'http://www.tjsbdcdjzx.com//upload/images/users/1464533396455.jpeg ', '2016-05-29 22:50:22', '1', '1', null, null, null, '2016-05-29 22:55:27');
INSERT INTO `estatedb_user` VALUES ('12', 'ov5KSuJi0WTweExk5VXhw2OQa_f4', '咪咪', '13516856789', '0', null, '130222167809084567', 'XGK5aIuPPeIWEMukUgP34Q==', 'http://www.tjsbdcdjzx.com//upload/images/users/1464533441363.jpeg ', 'http://www.tjsbdcdjzx.com//upload/images/users/1464533460393.jpeg ', '2016-05-29 22:51:06', '1', '1', null, null, null, '2016-05-29 22:55:28');

-- ----------------------------
-- Table structure for `estatedb_work_time`
-- ----------------------------
DROP TABLE IF EXISTS `estatedb_work_time`;
CREATE TABLE `estatedb_work_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` varchar(10) DEFAULT NULL COMMENT '办理开始时间',
  `endTime` varchar(10) DEFAULT NULL COMMENT '办理结束时间',
  `members` int(11) DEFAULT NULL COMMENT '办理最大人数',
  `deadTime` varchar(10) DEFAULT NULL COMMENT '最后领号时间',
  `status` int(1) DEFAULT NULL COMMENT '时间状态',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `areaId` int(11) DEFAULT NULL COMMENT '行政区id',
  `areaName` varchar(255) DEFAULT NULL COMMENT '行政区名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of estatedb_work_time
-- ----------------------------
INSERT INTO `estatedb_work_time` VALUES ('1', '13:00', '13:30', '10', '13:20', null, '2016-05-29 14:49:34', '1034', '天津市市区不动产登记事务中心和平部');

-- ----------------------------
-- Table structure for `lyd_announcement`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_announcement`;
CREATE TABLE `lyd_announcement` (
  `announcementId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `keyWord` varchar(100) DEFAULT NULL COMMENT '关键词',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `ralateWord` varchar(100) DEFAULT NULL COMMENT '关联词',
  `imageUrl` varchar(255) NOT NULL DEFAULT '' COMMENT '图片地址',
  `lanmu` int(1) NOT NULL DEFAULT '1' COMMENT '栏目',
  `detailInfo` text NOT NULL COMMENT '内容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`announcementId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_announcement
-- ----------------------------
INSERT INTO `lyd_announcement` VALUES ('20', '微信预约系统内测通知', null, '公告关键词', '公告描述', '测试', '12', '1', '<p><span style=\"font-size:18px\">　　天津市不动产登记网上预约服务系统将于<span style=\"color:#FF0000\">2016年7月1日9：00</span>开始进行内部压力测试，请各位测试人员充分模拟在现实您能想到的各种情况，并在平台中进行不同的点击、填写、申请、撤销等操作，以查找系统可能存在的各种漏洞、测试后台人员的工作强度，检验从申请注册到完成预约登记的各项流程。有任何意见或建议请发送至<span style=\"color:#0000FF\">tj_djzx@163.com</span>，让我们一同努力更好地完善预约系统、更好地为服务企业群众。</span></p>\r\n', '2015-08-12 15:21:48', '2016-05-26 11:40:57', '1');
INSERT INTO `lyd_announcement` VALUES ('25', '关于天津市实施不动产统一登记有关事项的公告', null, '', '', '', '', '1', '<p><span style=\"font-size:16px; line-height:1.6em\">　　按照国务院《不动产登记暂行条例》(国务院令656号)以及党中央、国务院关于建立不动产统一登记制度的工作部署，经天津市人民政府批准，现将我市启动不动产统一登记有关事项公告如下：</span></p>\r\n\r\n<p><span style=\"font-size:16px\">　一、将市和区县土地登记、房屋登记、林地登记、草原登记、海域登记和农村土地承包经营权登记等不动产登记职责进行整合，统一交由天津市国土资源和房屋管理局承担，其行业管理和不动产交易监管等职责仍由原相关部门承担。<br />\r\n<br />\r\n　　二、自2015年11月30日起，我市土地、房屋、林地、海域等不动产登记业务，统一到天津市国土资源和房屋管理局设立或者委托的不动产登记经办机构办理，领取《不动产权证书》、《不动产登记证明》。海洋部门、林业部门不再受理海域、林地等登记发证业务。国家规定的过渡期内农村土地承包经营权登记仍由原部门办理，过渡期后纳入统一登记的具体时间另行公告。<br />\r\n<br />\r\n　　2015年11月30日前原海洋部门、林业部门已经受理的登记业务，仍到海洋部门、林业部门领取证书；原房地登记部门已经受理但尚未核准登记的，统一颁发新的《不动产权证书》、《不动产登记证明》。<br />\r\n<br />\r\n　　原已依法颁发的各类不动产权属证书、不动产登记证明继续有效，按照&ldquo;不变不换&rdquo;的原则，在今后依法办理不动产转移、变更等登记时予以换发新的《不动产权证书》或者《不动产登记证明》。<br />\r\n<br />\r\n　　三、不动产登记申请人按照下列业务分工，向市或区县不动产登记经办机构申请办理登记业务：<br />\r\n<br />\r\n　　天津市不动产登记中心(原天津市房地产登记发证交易中心)负责受理全市范围内海域使用权登记，涉及国防军事、国家安全和保密单位的各类不动产登记(不包括军队开发建设的商品房、房改购房转移登记)，市人民政府所属直管公产非住宅房地登记，不能分别办理的跨区县行政区域不动产登记，外产代管产房地登记。不再受理原市房地产登记发证交易中心负责办理的各类登记业务，相关业务由不动产所在地的区、县不动产登记经办机构受理。<br />\r\n<br />\r\n　　其中，市区不动产登记事务中心具体受理市内六区范围内的不动产登记(包括首次登记、转移登记、变更登记、注销登记、更正登记、异议登记、预告登记、查封登记等，下同)。滨海新区、东丽区、西青区、津南区、北辰区、武清区、宝坻区、静海区、宁河区、蓟县不动产登记事务中心具体受理辖区内的不动产登记。<br />\r\n<br />\r\n　　开发区、保税区、高新技术产业开发区管委会设立的不动产登记经办机构继续负责受理滨海新区以外所辖区域内的不动产登记；海河教育园区管委会负责受理所辖区域内的不动产登记。上述区域内不动产所在地的区县不动产登记事务中心不受理该区域的不动产登记。<br />\r\n<br />\r\n　　四、为方便企业群众，市区不动产登记事务中心在市内六区分别设置业务部受理各自辖区范围内的不动产登记业务；市不动产登记中心在市海洋局增设窗口受理全市范围内的海域使用权登记业务。各区县不动产登记事务中心和市内六区各业务部仍在各自辖区内原房地登记服务大厅受理各类登记业务。申请办理各类不动产登记业务的要件、办理时限等具体内容可点击天津市国土资源和房屋管理局政务网站(具体网址为：www.tjfdc.gov.cn )查询。<br />\r\n<br />\r\n　　五、具体办公地点和咨询电话<br />\r\n<br />\r\n　　天津市国土资源和房屋管理局业务咨询电话为：23301382；信访受理电话为：23145281。<br />\r\n<br />\r\n　　天津市滨海新区各类登记业务受理分工和办公场所详见天津市滨海新区规划和国土资源管理局发布的公告。<br />\r\n<br />\r\n　　特此公告。<br />\r\n<br />\r\n　　<strong>天津市国土资源和房屋管理局</strong></span></p>\r\n\r\n<p><span style=\"font-size:16px\"><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/737441261564249.jpg\" style=\"height:459px; width:500px\" /></span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:16px\">　　</span><span style=\"font-size:16px\">2015年11月26日按照天津市人民政府办公厅《天津市人民政府办公厅关于印发天津市不动产统一登记工作实施方案的通知》(津政办发[2015]81号)文件精神以及天津市不动产统一登记工作部署，经天津市国土资源和房屋管理局批准，现将滨海新区启动不动产统一登记有关事项公告如下：<br />\r\n<br />\r\n　　一、自2015年11月30日起，滨海新区土地、房屋、林地等不动产登记业务，统一到滨海新区各不动产登记经办机构办理，领取《不动产权证书》、《不动产登记证明》。林业部门不再受理林地登记发证业务。国家规定的过渡期内农村土地承包经营权登记仍由原部门办理，过渡期后纳入统一登记的具体时间另行公告。<br />\r\n<br />\r\n　　2015年11月30日前原林业部门已经受理的登记业务，仍到林业部门领取证书；原房地登记部门已经受理但尚未核准登记的，统一颁发新的《不动产权证书》、《不动产登记证明》。<br />\r\n<br />\r\n　　原已依法颁发的各类不动产权属证书、登记证明继续有效，按照&ldquo;不变不换&rdquo;的原则，在今后依法办理不动产转移、变更等登记时予以换发新的《不动产权证书》或《不动产登记证明》。<br />\r\n<br />\r\n　　二、不动产登记申请人按照下列业务分工，向滨海新区各不动产登记经办机构申请办理登记业务：<br />\r\n<br />\r\n　　天津市滨海新区不动产登记中心(原天津市滨海新区房地产登记交易中心)负责受理天津市滨海新区行政辖区范围内商品房预售登记，负责受理除开发区、保税区、高新技术产业开发区、中新生态城及东疆港保税区以外的土地变更登记、房地产初始登记、土地抵押权设定、变更及注销登记、在建工程房地产权属登记、土地使用权注销登记工作；负责受理中心商务区、临港工业区、原滨海旅游区、原中心渔港、原北塘经济区、原轻纺经济区范围内的除海域使用权登记，涉及国防军事、国家安全和保密单位的各类不动产登记(不包括军队开发建设的商品房、房改购房转移登记)，市人民政府所属直管公产非住宅房地登记，不能分别办理的跨区县行政区域不动产登记，外产代管产房地登记以外的不动产登记及不动产登记档案查询工作。<br />\r\n<br />\r\n　　天津市滨海新区不动产登记第一中心(原天津市滨海新区塘沽房地产交易中心)负责滨海新区北塘街(除北塘经济区)、塘沽街(除中心商务区)、大沽街(除中心商务区)、新河街、新北街、杭州道街、胡家园街、新城镇区域内除商品房预售登记、国防军事、国家安全、公安等单位所有的各类不动产登记、海域使用权登记、土地变更登记、房地产初始登记、土地抵押权设定、变更及注销登记、在建工程房地产权属登记、土地使用权注销登记外的不动产登记及不动产登记档案查询工作。<br />\r\n<br />\r\n　　天津市滨海新区不动产登记第二中心(原天津市滨海新区汉沽房地产交易所)负责滨海新区汉沽街、茶淀街、寨上街、杨家泊镇区域内除商品房预售登记、国防军事、国家安全、公安等单位所有的各类不动产登记、海域使用权登记、土地变更登记、房地产初始登记、土地抵押权设定、变更及注销登记、在建工程房地产权属登记、土地使用权注销登记外的不动产登记及不动产登记档案查询工作。<br />\r\n<br />\r\n　　天津市滨海新区不动产登记第三中心(原天津市滨海新区大港房地产交易所)负责滨海新区大港街、古林街、海滨街、中塘镇、小王庄镇、太平镇区域内除商品房预售登记、国防军事、国家安全、公安等单位所有的各类不动产登记、海域使用权登记、土地变更登记、房地产初始登记、土地抵押权设定、变更及注销登记、在建工程房地产权属登记、土地使用权注销登记外的不动产登记及不动产登记档案查询工作。<br />\r\n<br />\r\n　　天津市滨海新区不动产登记第四中心(原天津市滨海新区大港海滨房地产交易所)负责滨海新区大港油田区域内除商品房预售登记、国防军事、国家安全、公安等单位所有的各类不动产登记、海域使用权登记、土地变更登记、房地产初始登记、土地抵押权设定、变更及注销登记、在建工程房地产权属登记、土地使用权注销登记外的不动产登记及不动产登记档案查询工作。<br />\r\n<br />\r\n　　开发区、保税区、高新技术产业开发区、中新生态城、东疆保税港区设立的不动产登记经办机构继续负责受理本管理区域内的除商品房预售登记，海域使用权登记，涉及国防军事、国家安全和保密单位的各类不动产登记(不包括军队开发建设的商品房、房改购房转移登记)，市人民政府所属直管公产非住宅房地登记，不能分别办理的跨区县行政区域不动产登记，外产代管产房地登记以外的不动产登记及不动产登记档案查询工作。<br />\r\n<br />\r\n　　开发区、保税区、高新技术产业开发区设立的不动产登记经办机构继续受理滨海新区以外所辖区域内的不动产登记。<br />\r\n<br />\r\n　　三、为方便企业群众，滨海新区各不动产登记经办机构分别增设窗口受理滨海新区范围内的林地登记业务。各登记经办机构仍在辖区内原登记服务大厅受理各类登记。<br />\r\n<br />\r\n　　特此公告。<br />\r\n<br />\r\n　　<strong>天津市滨海新区规划和国土资源管理局</strong></span></p>\r\n\r\n<p><span style=\"font-size:16px\"><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/737650581049267.jpg\" style=\"height:280px; width:500px\" /></span></p>\r\n', '2016-05-18 16:41:24', '2016-05-26 11:43:20', '1');
INSERT INTO `lyd_announcement` VALUES ('26', '天津市执行调整房产交易环节契税', null, '', '', '', '', '1', '<p><span style=\"font-size:16px\"><strong>关于调整房地产交易环节契税 营业税优惠政策的通知(财税[2016]23号)&nbsp;</strong><br />\r\n　　各省、自治区、直辖市、计划单列市财政厅（局）、地方税务局、住房城乡建设厅（建委、房地局），西藏、宁夏、青海省（自治区）国家税务局，新疆生产建设兵团财务局、建设局：<br />\r\n<br />\r\n　　根据国务院有关部署，现就调整房地产交易环节契税、营业税优惠政策通知如下：<br />\r\n<br />\r\n　　一、关于契税政策&nbsp;<br />\r\n<br />\r\n　　（一）对个人购买家庭唯一住房（家庭成员范围包括购房人、配偶以及未成年子女，下同），面积为90平方米及以下的，减按1%的税率征收契税；面积为90平方米以上的，减按1.5%的税率征收契税。<br />\r\n<br />\r\n　　（二）对个人购买家庭第二套改善性住房，面积为90平方米及以下的，减按1%的税率征收契税；面积为90平方米以上的，减按2%的税率征收契税。&nbsp;<br />\r\n<br />\r\n　　家庭第二套改善性住房是指已拥有一套住房的家庭，购买的家庭第二套住房。<br />\r\n<br />\r\n　　（三）纳税人申请享受税收优惠的，根据纳税人的申请或授权，由购房所在地的房地产主管部门出具纳税人家庭住房情况书面查询结果，并将查询结果和相关住房信息及时传递给税务机关。暂不具备查询条件而不能提供家庭住房查询结果的，纳税人应向税务机关提交家庭住房实有套数书面诚信保证，诚信保证不实的，属于虚假纳税申报，按照《中华人民共和国税收征收管理法》的有关规定处理，并将不诚信记录纳入个人征信系统。<br />\r\n<br />\r\n　　按照便民、高效原则，房地产主管部门应按规定及时出具纳税人家庭住房情况书面查询结果，税务机关应对纳税人提出的税收优惠申请限时办结。<br />\r\n<br />\r\n　　（四）具体操作办法由各省、自治区、直辖市财政、税务、房地产主管部门共同制定。<br />\r\n<br />\r\n　　二、关于营业税政策&nbsp;<br />\r\n<br />\r\n　　个人将购买不足2年的住房对外销售的，全额征收营业税；个人将购买2年以上（含2年）的住房对外销售的，免征营业税。<br />\r\n<br />\r\n　　办理免税的具体程序、购买房屋的时间、开具发票、非购买形式取得住房行为及其他相关税收管理规定，按照《国务院办公厅转发建设部等部门关于做好稳定住房价格工作意见的通知》（国办发〔2005〕26号）、《国家税务总局 财政部 建设部关于加强房地产税收管理的通知》（国税发〔2005〕89号）和《国家税务总局关于房地产税收政策执行中几个具体问题的通知》（国税发〔2005〕172号）的有关规定执行。<br />\r\n<br />\r\n　　三、关于实施范围&nbsp;<br />\r\n<br />\r\n　　北京市、上海市、广州市、深圳市暂不实施本通知第一条第二项契税优惠政策及第二条营业税优惠政策，上述城市个人住房转让营业税政策仍按照《财政部 国家税务总局关于调整个人住房转让营业税政策的通知》（财税〔2015〕39号）执行。<br />\r\n<br />\r\n　　上述城市以外的其他地区适用本通知全部规定。<br />\r\n　　本通知自2016年2月22日起执行。<br />\r\n<br />\r\n　　<strong>财政部国家税务总局住房城乡建设部</strong></span></p>\r\n\r\n<p><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/737733461688007.jpg\" style=\"height:309px; width:500px\" /></p>\r\n', '2016-05-19 10:43:13', '2016-05-26 14:15:18', '1');

-- ----------------------------
-- Table structure for `lyd_backstage_admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_backstage_admin_user`;
CREATE TABLE `lyd_backstage_admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(25) DEFAULT NULL,
  `realName` varchar(25) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `createTime` varchar(25) DEFAULT NULL,
  `updateTime` varchar(25) DEFAULT NULL,
  `userStatus` varchar(1) DEFAULT '0',
  `loginTimes` varchar(25) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_backstage_admin_user
-- ----------------------------
INSERT INTO `lyd_backstage_admin_user` VALUES ('99', 'admin', 'admin', null, '$2a$10$SMHNzhSMzgV5CTosHrOtyePxobQoS2U8HttPUffuNWZlSEt5AOh5O', null, null, null, '0', '0');
INSERT INTO `lyd_backstage_admin_user` VALUES ('100', '12', '12', '12123423434', '$2a$10$BeBi.3LnBE3gSvrIKzur6e6OGLLHGKNoUYINBGmIKZTcyKLvILiri', '2016-04-14 14:25:01', null, '0', null, '12');

-- ----------------------------
-- Table structure for `lyd_categoryresource`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_categoryresource`;
CREATE TABLE `lyd_categoryresource` (
  `rcategoryId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  PRIMARY KEY (`rcategoryId`,`resourceId`),
  KEY `category_resource_ibfk_1` (`rcategoryId`),
  KEY `category_resource_ibfk_2` (`resourceId`),
  CONSTRAINT `category_resource_ibfk_1` FOREIGN KEY (`rcategoryId`) REFERENCES `lyd_rcategory` (`rcategoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `category_resource_ibfk_2` FOREIGN KEY (`resourceId`) REFERENCES `lyd_resource` (`resourceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_categoryresource
-- ----------------------------
INSERT INTO `lyd_categoryresource` VALUES ('70', '95');
INSERT INTO `lyd_categoryresource` VALUES ('70', '97');
INSERT INTO `lyd_categoryresource` VALUES ('70', '99');
INSERT INTO `lyd_categoryresource` VALUES ('70', '101');
INSERT INTO `lyd_categoryresource` VALUES ('70', '103');
INSERT INTO `lyd_categoryresource` VALUES ('70', '105');
INSERT INTO `lyd_categoryresource` VALUES ('70', '107');
INSERT INTO `lyd_categoryresource` VALUES ('70', '109');
INSERT INTO `lyd_categoryresource` VALUES ('70', '153');
INSERT INTO `lyd_categoryresource` VALUES ('71', '139');
INSERT INTO `lyd_categoryresource` VALUES ('72', '111');
INSERT INTO `lyd_categoryresource` VALUES ('72', '113');
INSERT INTO `lyd_categoryresource` VALUES ('72', '114');
INSERT INTO `lyd_categoryresource` VALUES ('73', '115');
INSERT INTO `lyd_categoryresource` VALUES ('73', '116');
INSERT INTO `lyd_categoryresource` VALUES ('73', '177');
INSERT INTO `lyd_categoryresource` VALUES ('73', '178');
INSERT INTO `lyd_categoryresource` VALUES ('73', '179');
INSERT INTO `lyd_categoryresource` VALUES ('76', '182');
INSERT INTO `lyd_categoryresource` VALUES ('76', '192');
INSERT INTO `lyd_categoryresource` VALUES ('76', '193');
INSERT INTO `lyd_categoryresource` VALUES ('76', '194');
INSERT INTO `lyd_categoryresource` VALUES ('77', '183');
INSERT INTO `lyd_categoryresource` VALUES ('77', '184');
INSERT INTO `lyd_categoryresource` VALUES ('77', '185');
INSERT INTO `lyd_categoryresource` VALUES ('77', '186');
INSERT INTO `lyd_categoryresource` VALUES ('77', '187');
INSERT INTO `lyd_categoryresource` VALUES ('77', '188');
INSERT INTO `lyd_categoryresource` VALUES ('77', '189');
INSERT INTO `lyd_categoryresource` VALUES ('77', '190');
INSERT INTO `lyd_categoryresource` VALUES ('78', '195');
INSERT INTO `lyd_categoryresource` VALUES ('78', '196');
INSERT INTO `lyd_categoryresource` VALUES ('78', '197');
INSERT INTO `lyd_categoryresource` VALUES ('78', '198');
INSERT INTO `lyd_categoryresource` VALUES ('79', '199');
INSERT INTO `lyd_categoryresource` VALUES ('79', '200');
INSERT INTO `lyd_categoryresource` VALUES ('80', '202');

-- ----------------------------
-- Table structure for `lyd_grouprole`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_grouprole`;
CREATE TABLE `lyd_grouprole` (
  `groupId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`groupId`,`roleId`),
  KEY `group_role_idfk_1` (`groupId`),
  KEY `group_role_idfk_2` (`roleId`),
  CONSTRAINT `group_role_idfk_1` FOREIGN KEY (`groupId`) REFERENCES `lyd_igroup` (`groupId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_role_idfk_2` FOREIGN KEY (`roleId`) REFERENCES `lyd_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_grouprole
-- ----------------------------
INSERT INTO `lyd_grouprole` VALUES ('3', '7');
INSERT INTO `lyd_grouprole` VALUES ('64', '65');

-- ----------------------------
-- Table structure for `lyd_igroup`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_igroup`;
CREATE TABLE `lyd_igroup` (
  `groupId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enable` int(1) DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_igroup
-- ----------------------------
INSERT INTO `lyd_igroup` VALUES ('3', '超级管理员', '超级管理员组', '1', '2013-12-25 09:01:20', null);
INSERT INTO `lyd_igroup` VALUES ('64', '编辑管理', '编辑管理', '1', '2014-11-13 10:00:29', '2016-04-27 10:58:56');

-- ----------------------------
-- Table structure for `lyd_rcategory`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_rcategory`;
CREATE TABLE `lyd_rcategory` (
  `rcategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rcategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_rcategory
-- ----------------------------
INSERT INTO `lyd_rcategory` VALUES ('70', '权限管理');
INSERT INTO `lyd_rcategory` VALUES ('71', '会员管理');
INSERT INTO `lyd_rcategory` VALUES ('72', '后台用户管理');
INSERT INTO `lyd_rcategory` VALUES ('73', '公告管理');
INSERT INTO `lyd_rcategory` VALUES ('75', '友情链接管理');
INSERT INTO `lyd_rcategory` VALUES ('76', '问答管理');
INSERT INTO `lyd_rcategory` VALUES ('77', '系统字典管理');
INSERT INTO `lyd_rcategory` VALUES ('78', '办件须知管理');
INSERT INTO `lyd_rcategory` VALUES ('79', '时间管理');
INSERT INTO `lyd_rcategory` VALUES ('80', '预约管理');

-- ----------------------------
-- Table structure for `lyd_resource`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_resource`;
CREATE TABLE `lyd_resource` (
  `resourceId` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `menu` int(1) DEFAULT '0',
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_resource
-- ----------------------------
INSERT INTO `lyd_resource` VALUES ('95', '/group/new', '添加角色', '1');
INSERT INTO `lyd_resource` VALUES ('96', '/group/*/edit/*', '编辑角色', '0');
INSERT INTO `lyd_resource` VALUES ('97', '/groups/*', '角色列表', '1');
INSERT INTO `lyd_resource` VALUES ('98', '/group/*/delete', '删除角色', '0');
INSERT INTO `lyd_resource` VALUES ('99', '/role/new', '添加权限', '1');
INSERT INTO `lyd_resource` VALUES ('100', '/role/*/edit/*', '编辑权限', '0');
INSERT INTO `lyd_resource` VALUES ('101', '/roles/*', '权限列表', '1');
INSERT INTO `lyd_resource` VALUES ('102', '/role/*/delete', '删除权限', '0');
INSERT INTO `lyd_resource` VALUES ('103', '/url/new', '新建URL', '1');
INSERT INTO `lyd_resource` VALUES ('104', '/url/*/edit/*', '编辑URL', '0');
INSERT INTO `lyd_resource` VALUES ('105', '/urls/*', 'URL列表', '1');
INSERT INTO `lyd_resource` VALUES ('107', '/rcategory/new', '添加URL分类', '1');
INSERT INTO `lyd_resource` VALUES ('108', '/rcategory/*/edit/*', '编辑URL分类', '0');
INSERT INTO `lyd_resource` VALUES ('109', '/rcategories/*', 'URL分类列表', '1');
INSERT INTO `lyd_resource` VALUES ('110', '/rcategory/*/delete', '删除URL分类', '0');
INSERT INTO `lyd_resource` VALUES ('111', '/createAdminUser', '添加后台用户', '1');
INSERT INTO `lyd_resource` VALUES ('112', '/password', '修改密码', '0');
INSERT INTO `lyd_resource` VALUES ('113', '/adminUser/*/edit/*', '修改后台用户信息', '0');
INSERT INTO `lyd_resource` VALUES ('114', '/adminUsers/*', '后台用户列表', '1');
INSERT INTO `lyd_resource` VALUES ('115', '/createAnnouncement', '添加公告', '1');
INSERT INTO `lyd_resource` VALUES ('116', '/announcements/*', '公告列表', '1');
INSERT INTO `lyd_resource` VALUES ('117', '/fileUpload?paramFile=file', '上传文件', '0');
INSERT INTO `lyd_resource` VALUES ('139', '/users/*', '会员列表', '1');
INSERT INTO `lyd_resource` VALUES ('153', '/system/reloadResourceDefine', '更新权限定义', '1');
INSERT INTO `lyd_resource` VALUES ('154', '/user/welcome.htm', '欢迎页', '0');
INSERT INTO `lyd_resource` VALUES ('155', '/adminUser/*/delete', '删除后台用户', '0');
INSERT INTO `lyd_resource` VALUES ('177', '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images&CKEditor=content&CKEditorFuncNum=*&langCode=zh-cn', '上传图片-ckEditor', '0');
INSERT INTO `lyd_resource` VALUES ('178', '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images&CKEditor=page&CKEditorFuncNum=*&langCode=zh-cn', '上传图片', '0');
INSERT INTO `lyd_resource` VALUES ('179', '/resources/upload/images/*.jpg', '上传图片访问地址', '0');
INSERT INTO `lyd_resource` VALUES ('180', '/url/*/delete', '删除URL', '0');
INSERT INTO `lyd_resource` VALUES ('181', '/updateAnnouncement/*', '修改公告', '0');
INSERT INTO `lyd_resource` VALUES ('182', '/question/new', '添加问题', '1');
INSERT INTO `lyd_resource` VALUES ('183', '/categorys/*', '字典分类列表', '1');
INSERT INTO `lyd_resource` VALUES ('184', '/category/new', '新建字典分类', '1');
INSERT INTO `lyd_resource` VALUES ('185', '/category/*/edit/*', '修改字典分类', '0');
INSERT INTO `lyd_resource` VALUES ('186', '/category/*/delete', '删除字典分类', '0');
INSERT INTO `lyd_resource` VALUES ('187', '/dictionarys/*', '字典列表', '1');
INSERT INTO `lyd_resource` VALUES ('188', '/dictionary/new', '新建字典值', '1');
INSERT INTO `lyd_resource` VALUES ('189', '/dictionary/*/edit/*', '修改字典值', '0');
INSERT INTO `lyd_resource` VALUES ('190', '/dictionary/*/delete', '删除字典值', '0');
INSERT INTO `lyd_resource` VALUES ('191', '/question/*/edit/*', '初始化更新问题', '0');
INSERT INTO `lyd_resource` VALUES ('192', '/questions/*', '问题列表', '1');
INSERT INTO `lyd_resource` VALUES ('193', '/answer/new/*', '添加回答', '0');
INSERT INTO `lyd_resource` VALUES ('194', '/answer/*/*/edit/*', '修改回答', '0');
INSERT INTO `lyd_resource` VALUES ('195', '/createNotice', '添加须知', '1');
INSERT INTO `lyd_resource` VALUES ('196', '/updateNotice/*', '修改办件须知', '0');
INSERT INTO `lyd_resource` VALUES ('197', '/notices/*', '须知列表', '1');
INSERT INTO `lyd_resource` VALUES ('198', '/notice/*/*', '删除须知', '0');
INSERT INTO `lyd_resource` VALUES ('199', '/workTime', '办理时间设置', '1');
INSERT INTO `lyd_resource` VALUES ('200', '/workTimeList', '办理时间列表', '1');
INSERT INTO `lyd_resource` VALUES ('201', '/updateWorkTime/*', '修改时间', '0');
INSERT INTO `lyd_resource` VALUES ('202', '/loadOrderRecords/*', '预约列表', '1');
INSERT INTO `lyd_resource` VALUES ('203', '/updateOrderStatus/*/*/*', ' 修改预约', '0');
INSERT INTO `lyd_resource` VALUES ('204', '/updateUser/*/*/*', '审核用户', '0');

-- ----------------------------
-- Table structure for `lyd_role`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_role`;
CREATE TABLE `lyd_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_role
-- ----------------------------
INSERT INTO `lyd_role` VALUES ('7', '超级权限', '所有URL访问权限', '2013-12-25 09:00:25', null);
INSERT INTO `lyd_role` VALUES ('64', '后台用户操作', '添加-修改-删除', '2014-10-29 16:10:06', null);
INSERT INTO `lyd_role` VALUES ('65', '内容编辑', '编辑部', '2014-11-13 10:00:02', null);

-- ----------------------------
-- Table structure for `lyd_roleresource`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_roleresource`;
CREATE TABLE `lyd_roleresource` (
  `roleId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`resourceId`),
  KEY `role_resource_ibfk_1` (`roleId`),
  KEY `role_resource_ibfk_2` (`resourceId`),
  CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `lyd_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_resource_ibfk_2` FOREIGN KEY (`resourceId`) REFERENCES `lyd_resource` (`resourceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_roleresource
-- ----------------------------
INSERT INTO `lyd_roleresource` VALUES ('7', '95');
INSERT INTO `lyd_roleresource` VALUES ('7', '96');
INSERT INTO `lyd_roleresource` VALUES ('7', '97');
INSERT INTO `lyd_roleresource` VALUES ('7', '98');
INSERT INTO `lyd_roleresource` VALUES ('7', '99');
INSERT INTO `lyd_roleresource` VALUES ('7', '100');
INSERT INTO `lyd_roleresource` VALUES ('7', '101');
INSERT INTO `lyd_roleresource` VALUES ('7', '102');
INSERT INTO `lyd_roleresource` VALUES ('7', '103');
INSERT INTO `lyd_roleresource` VALUES ('7', '104');
INSERT INTO `lyd_roleresource` VALUES ('7', '105');
INSERT INTO `lyd_roleresource` VALUES ('7', '107');
INSERT INTO `lyd_roleresource` VALUES ('7', '108');
INSERT INTO `lyd_roleresource` VALUES ('7', '109');
INSERT INTO `lyd_roleresource` VALUES ('7', '110');
INSERT INTO `lyd_roleresource` VALUES ('7', '111');
INSERT INTO `lyd_roleresource` VALUES ('7', '112');
INSERT INTO `lyd_roleresource` VALUES ('7', '113');
INSERT INTO `lyd_roleresource` VALUES ('7', '114');
INSERT INTO `lyd_roleresource` VALUES ('7', '115');
INSERT INTO `lyd_roleresource` VALUES ('7', '116');
INSERT INTO `lyd_roleresource` VALUES ('7', '117');
INSERT INTO `lyd_roleresource` VALUES ('7', '139');
INSERT INTO `lyd_roleresource` VALUES ('7', '153');
INSERT INTO `lyd_roleresource` VALUES ('7', '154');
INSERT INTO `lyd_roleresource` VALUES ('7', '155');
INSERT INTO `lyd_roleresource` VALUES ('7', '177');
INSERT INTO `lyd_roleresource` VALUES ('7', '178');
INSERT INTO `lyd_roleresource` VALUES ('7', '179');
INSERT INTO `lyd_roleresource` VALUES ('7', '180');
INSERT INTO `lyd_roleresource` VALUES ('7', '181');
INSERT INTO `lyd_roleresource` VALUES ('7', '182');
INSERT INTO `lyd_roleresource` VALUES ('7', '183');
INSERT INTO `lyd_roleresource` VALUES ('7', '184');
INSERT INTO `lyd_roleresource` VALUES ('7', '185');
INSERT INTO `lyd_roleresource` VALUES ('7', '186');
INSERT INTO `lyd_roleresource` VALUES ('7', '187');
INSERT INTO `lyd_roleresource` VALUES ('7', '188');
INSERT INTO `lyd_roleresource` VALUES ('7', '189');
INSERT INTO `lyd_roleresource` VALUES ('7', '190');
INSERT INTO `lyd_roleresource` VALUES ('7', '191');
INSERT INTO `lyd_roleresource` VALUES ('7', '192');
INSERT INTO `lyd_roleresource` VALUES ('7', '193');
INSERT INTO `lyd_roleresource` VALUES ('7', '194');
INSERT INTO `lyd_roleresource` VALUES ('7', '195');
INSERT INTO `lyd_roleresource` VALUES ('7', '196');
INSERT INTO `lyd_roleresource` VALUES ('7', '197');
INSERT INTO `lyd_roleresource` VALUES ('7', '198');
INSERT INTO `lyd_roleresource` VALUES ('7', '199');
INSERT INTO `lyd_roleresource` VALUES ('7', '200');
INSERT INTO `lyd_roleresource` VALUES ('7', '201');
INSERT INTO `lyd_roleresource` VALUES ('7', '202');
INSERT INTO `lyd_roleresource` VALUES ('7', '203');
INSERT INTO `lyd_roleresource` VALUES ('7', '204');
INSERT INTO `lyd_roleresource` VALUES ('64', '111');
INSERT INTO `lyd_roleresource` VALUES ('64', '114');
INSERT INTO `lyd_roleresource` VALUES ('64', '154');
INSERT INTO `lyd_roleresource` VALUES ('65', '111');
INSERT INTO `lyd_roleresource` VALUES ('65', '114');
INSERT INTO `lyd_roleresource` VALUES ('65', '115');
INSERT INTO `lyd_roleresource` VALUES ('65', '116');
INSERT INTO `lyd_roleresource` VALUES ('65', '117');
INSERT INTO `lyd_roleresource` VALUES ('65', '154');

-- ----------------------------
-- Table structure for `lyd_user`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_user`;
CREATE TABLE `lyd_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '用户类型',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `registerIp` varchar(50) DEFAULT '' COMMENT '注册ip',
  `loginIp` varchar(50) DEFAULT '' COMMENT '登录ip',
  `loginTimes` int(11) DEFAULT '0' COMMENT '登录次数',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `recommenderMobile` varchar(11) DEFAULT NULL COMMENT '推荐人手机',
  `withdrawPrivilege` int(2) NOT NULL DEFAULT '0' COMMENT '提现特权',
  `freeFeePrivilege` int(2) NOT NULL DEFAULT '0' COMMENT '充值提现免费特权',
  `loginType` int(2) NOT NULL DEFAULT '0' COMMENT '登录来源',
  `registerType` int(2) NOT NULL DEFAULT '0' COMMENT '注册来源',
  `identificationTime` datetime DEFAULT NULL COMMENT '认证易宝时间',
  `identificationSuccessTime` datetime DEFAULT NULL COMMENT '认证易宝成功时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `lyd_user_userId` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=981 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_user
-- ----------------------------
INSERT INTO `lyd_user` VALUES ('29', 'songchuan', '$2a$10$r7uGGFgj/vO/3Fo68x.imu4hp5APL54BBre9ZRl0s8WKgv8Q5YRRe', '1', '1', null, '127.0.0.1', '110', '2015-10-28 14:17:29', '2014-10-01 16:10:59', '2016-04-14 14:17:29', null, '0', '0', '0', '0', null, null);
INSERT INTO `lyd_user` VALUES ('980', 'lisa06070', '$2a$10$8kxP.T1IerM5u.yhtZxagul1gCfS7tsac3sALCGwHleYv10voXCve', '1', '1', '127.0.0.1', '127.0.0.1', '5', '2015-07-10 16:45:48', '2015-06-15 11:28:40', '2016-04-14 16:45:48', null, '0', '0', '0', '0', null, null);

-- ----------------------------
-- Table structure for `lyd_usergroup`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_usergroup`;
CREATE TABLE `lyd_usergroup` (
  `userId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`groupId`),
  KEY `user_group_idfk_1` (`userId`),
  KEY `user_group_idfk_2` (`groupId`),
  CONSTRAINT `user_group_idfk_1` FOREIGN KEY (`userId`) REFERENCES `lyd_backstage_admin_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_group_idfk_2` FOREIGN KEY (`groupId`) REFERENCES `lyd_igroup` (`groupId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_usergroup
-- ----------------------------
INSERT INTO `lyd_usergroup` VALUES ('99', '3');
INSERT INTO `lyd_usergroup` VALUES ('100', '3');

-- ----------------------------
-- Table structure for `lyd_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `lyd_user_info`;
CREATE TABLE `lyd_user_info` (
  `userId` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `identification` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `tranPassword` varchar(100) DEFAULT NULL COMMENT '交易密码',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `checkIdentityNum` int(11) NOT NULL DEFAULT '0' COMMENT '验证身份总次数',
  `checkIdentityTodayNum` int(11) NOT NULL DEFAULT '0' COMMENT '验证身份当天次数',
  `checkIdentityLastTime` datetime DEFAULT NULL COMMENT '最后验证身份证时间',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`) USING BTREE,
  KEY `lyd_user_info_userId` (`userId`),
  CONSTRAINT `lyd_user_info_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `lyd_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyd_user_info
-- ----------------------------
INSERT INTO `lyd_user_info` VALUES ('29', '1254608319@qq.com', '宋川', '120222199002054414', '15902266763', null, '2014-10-08 23:18:09', '2014-11-24 09:50:27', '1', '1', '2014-11-24 00:00:00');
INSERT INTO `lyd_user_info` VALUES ('980', null, '88', '370305199006070724', null, null, '2015-07-01 11:06:01', '2015-07-01 11:06:01', '0', '0', null);

-- ----------------------------
-- Table structure for `p_answer`
-- ----------------------------
DROP TABLE IF EXISTS `p_answer`;
CREATE TABLE `p_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theAnswer` text NOT NULL COMMENT '答案',
  `questionId` int(11) DEFAULT '0' COMMENT '问题id',
  `answerUserId` int(11) DEFAULT '0' COMMENT '回答用户id',
  `status` int(2) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_answer_p_question` (`questionId`),
  CONSTRAINT `p_answer_p_quesion` FOREIGN KEY (`questionId`) REFERENCES `p_question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_answer
-- ----------------------------
INSERT INTO `p_answer` VALUES ('1', '<p><strong><span style=\"font-size:22px\">新建商品房买卖的转移登记所需<span style=\"line-height:1.6em\">要件：</span></span></strong></p>\r\n\r\n<p><span style=\"font-size:18px\">（1）申请书；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（2）申请人身份证明（查验原件、复印件留存）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（3）天津市商品房买卖合同；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（4）购房交款凭证（查验原件、复印件留存）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（5）契税缴纳凭证（查验原件）;</span></p>\r\n\r\n<p><span style=\"font-size:18px\">其他说明：</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（6）首次登记时开发企业领取了不动产权属证书的，还应提交不动产权属证书;</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（7）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料;</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\">符合《天津市房屋权属登记条例》第53条、54条规定情形(以下内容可详见&ldquo;预告登记权利人入住满两年房地登记&rdquo;)，申请转移登记的，申请人是房屋所有权预告登记权利人。申请人应提交房屋所有权预告登记证明，不再提交天津市商品房买卖合同和契税缴纳凭证。</span></p>\r\n\r\n<p><span style=\"font-size:18px\">预购商品房预告登记权利人根据《天津市房屋权属登记条例》第53条规定申请转移登记的，应在房屋所有权首次登记后的三个月内向登记经办机构提出申请。预告登记已失效，因开发企业吊销、注销或下落不明，购房人单方申请转移登记的，登记经办机构受理申请后，应当进行核查并将有关情况在本市主要报纸上公告；公告三个月期满无异议的，予以登记。</span></p>\r\n', '5', '0', '0', '2016-04-27 10:19:18', null);
INSERT INTO `p_answer` VALUES ('2', '<p><span style=\"color:#000000\"><strong><span style=\"font-size:20px\">存量房屋买卖的转移登记所需要件</span></strong></span></p>\r\n\r\n<p><span style=\"font-size:18px\">（1）申请书；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（2）申请人身份证明（查验原件、复印件留存）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（4）天津市房产买卖协议；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（5）个人售房发票（查验原件）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（6）契税缴纳凭证（查验原件）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（7）属于划拨方式取得的土地使用权转让的，应当提交有批准权的人民政府或者主管部门的批准文件；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（8）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（9）部分房屋转移登记引起面积变动的，还应提交房屋测绘成果；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；属于原土地登记成果无解析坐标等按照规定需要进行权籍调查的，还应提交不动产权籍调查成果，不再提交地籍测绘成果；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（10）属于本市企事业单位有偿转让国有、集体非住宅房屋的，还应提交《产权交易鉴证书》；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（11）购买拍卖的房屋，申请人提交网签的房产买卖协议。</span></p>\r\n', '6', '0', '0', '2016-04-27 11:06:19', null);
INSERT INTO `p_answer` VALUES ('3', '222222222222222222222222222222222222222222222222222222222222222222222222222222222222', '6', '0', '0', '2016-04-27 11:06:22', null);
INSERT INTO `p_answer` VALUES ('4', '<p><span style=\"font-size:20px\"><span style=\"color:#0000FF\">Q1</span>：您好！请能简明扼要地告诉我这个预约系统怎么使用吗？</span></p>\r\n\r\n<p><span style=\"font-size:20px\"><span style=\"color:#0000FF\">A1</span>：<strong>首先</strong>是填写资料进行注册，<strong>然后</strong>是选择时间地点业务种类申请预约，<strong>最后</strong>在预约当日请准时到各区县经办机构办理具体业务。</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:20px\"><span style=\"color:#FF0000\">Q2</span>：我有好几个手机号，是否可以注册多个用户？</span></p>\r\n\r\n<p><span style=\"font-size:20px\"><span style=\"color:#FF0000\">A2</span>：身份证号码是注册用户的唯一编码，即使有多个手机，<span style=\"color:#FF0000\"><strong>一个身份证号也只能注册一个用户</strong></span>，请您仔细考虑好用哪个手机号后再申请注册。</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:20px\"><span style=\"color:#0000FF\">Q3</span>：我曾经用135XXXXXXXX手机号成功进行了注册，现在因故换成138XXXXXXXX的手机号了，重新申请新用户时系统告诉我该身份证号已注册过，该怎么办？</span></p>\r\n\r\n<p><span style=\"font-size:20px\"><span style=\"color:#0000FF\">A3</span>：若您更换手机号，请向微信服务号发送&ldquo;<span style=\"color:#FF0000\"><strong>更换手机号</strong></span>&rdquo;，我们后台工作人员将和您联系、核实后更改您的注册信息。</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:20px\"><span style=\"color:#FF0000\">Q4</span>：我想申请登记预约，但房产是在我父亲名下，那必须用我父亲的手机号注册吗？</span></p>\r\n\r\n<p><span style=\"font-size:20px\"><span style=\"color:#FF0000\">A4</span>：是的，<span style=\"color:#FF0000\"><strong>预约用户必须是产权人</strong></span>，我们在后台会进行严格审核。您的父亲是产权人，您就需要用他的手机号进行注册和预约，请多帮助不熟悉微信功能的亲人们吧。</span></p>\r\n', '7', '0', '0', '2016-05-18 16:33:20', null);

-- ----------------------------
-- Table structure for `p_notice`
-- ----------------------------
DROP TABLE IF EXISTS `p_notice`;
CREATE TABLE `p_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noticeName` varchar(100) NOT NULL COMMENT '须知名称',
  `noticeDescription` text COMMENT '须知描述',
  `noticeTypeId` int(2) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_notice_p_system_dictionary` (`noticeTypeId`),
  CONSTRAINT `p_notice_p_system_dictionary` FOREIGN KEY (`noticeTypeId`) REFERENCES `p_system_dictionary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_notice
-- ----------------------------
INSERT INTO `p_notice` VALUES ('1', '新建商品房买卖的转移登记', '<p><span style=\"font-size:20px\">　　该类登记属于新房买卖中的一个环节，常见情形描述：在开发商与业主签订完商品房买卖合同，业主缴纳齐购房款和契税，开发商办理完首次登记后，<span style=\"color:#ff6600\"><strong>双方向登记机关申请将房屋产权从开发商转移到业主名下，业主领取不动产权属证书的过程</strong></span>（目前由开发商代办的居多）。</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\">登记要件：</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（1）申请书；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（待附上申请书样本）</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（2）申请人身份证明（查验原件、复印件留存）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\"><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/1716531491317751.png\" style=\"height:307px; width:249px\" /></span></p>\r\n\r\n<p><span style=\"font-size:18px\">（3）天津市商品房买卖合同；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（待附图）</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（4）购房交款凭证（查验原件、复印件留存）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（待附图）</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（5）契税缴纳凭证（查验原件）；</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（待附图）</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（6）首次登记时开发企业领取了不动产权属证书的，还应提交不动产权属证书;</span></p>\r\n\r\n<p><span style=\"font-size:18px\">（7）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料;</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\">　　符合《天津市房屋权属登记条例》第53条、54条规定情形，申请转移登记的，申请人是房屋所有权预告登记权利人。申请人应提交房屋所有权预告登记证明，不再提交天津市商品房买卖合同和契税缴纳凭证。</span></p>\r\n\r\n<p><span style=\"font-size:18px\">　　预购商品房预告登记权利人根据《天津市房屋权属登记条例》第53条规定申请转移登记的，应在房屋所有权首次登记后的三个月内向登记经办机构提出申请。预告登记已失效，因开发企业吊销、注销或下落不明，购房人单方申请转移登记的，登记经办机构受理申请后，应当进行核查并将有关情况在本市主要报纸上公告；公告三个月期满无异议的，予以登记。</span></p>\r\n', '1028', '2016-05-04 15:28:03', null);
INSERT INTO `p_notice` VALUES ('3', '预购商品房预告登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）通过商品房销售网打印的已备案的天津市商品房买卖合同；</p>\r\n\r\n<p>（4）登记人员与登记簿核对是否办理预售登记，2005年9月1日前的除外；</p>\r\n\r\n<p>（5）契税缴纳凭证（查验原件）。</p>\r\n', '1028', '2016-05-04 17:14:23', null);
INSERT INTO `p_notice` VALUES ('4', '注销预购商品房预告登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书;</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）;</p>\r\n\r\n<p>（3）不动产预告登记证明;</p>\r\n\r\n<p>（4）预告登记终止的证明文件（查验原件、复印件留存）；</p>\r\n\r\n<p>（5）法律、行政法规规定的其他材料。</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>人民法院、仲裁委员会的生效法律文书证明预售合同终止的，凭生效法律文书和协助执行通知书（查验原件、复印件留存），可申请注销预告登记。</p>\r\n\r\n<p>预告登记权利人办理转移登记后，登记簿记载的预购商品房预告登记自动撤销。</p>\r\n', '1028', '2016-05-04 17:14:32', null);
INSERT INTO `p_notice` VALUES ('6', '抵押权预告登记', '<p>必备要件：</p>\r\n\r\n<p>1.申请书;</p>\r\n\r\n<p>2.申请人身份证明（查验原件、复印件留存）;</p>\r\n\r\n<p>3.不动产预告登记证明（原件暂存）;</p>\r\n\r\n<p>4.抵押合同;</p>\r\n\r\n<p>5.抵押担保的主合同。</p>\r\n', '1028', '2016-05-09 14:52:31', null);
INSERT INTO `p_notice` VALUES ('7', '注销抵押权预告登记', '<p>必备要件：</p>\r\n\r\n<p>1.申请书；</p>\r\n\r\n<p>2.申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>3.预购商品房预告登记证明（原件暂存）；</p>\r\n\r\n<p>4.抵押权预告登记证明；</p>\r\n\r\n<p>5.证明房屋抵押权终止的证明文件。</p>\r\n', '1028', '2016-05-09 14:53:27', null);
INSERT INTO `p_notice` VALUES ('8', '存量房屋买卖的转移登记', '<p>二手房买卖即属于该类登记，常见情形描述：<strong>买卖双方签订房产买卖协议，缴纳齐契税并向登记机关申请将房屋产权从卖方过户到买方名下，买方领取不动产权属证书的过程，</strong>是最繁忙的登记业务之一。</p>\r\n\r\n<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/1715282528954657.png\" style=\"height:307px; width:249px\" /></p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/1715346346576450.jpg\" style=\"height:178px; width:500px\" /></p>\r\n\r\n<p>（4）天津市房产买卖协议；</p>\r\n\r\n<p>（5）个人售房发票（查验原件）；</p>\r\n\r\n<p><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/1715381562844160.jpg\" style=\"height:313px; width:437px\" /></p>\r\n\r\n<p>（6）契税缴纳凭证（查验原件）；</p>\r\n\r\n<p><img alt=\"\" src=\"http://101.201.76.39/upload/images/images/1715448579884594.jpg\" style=\"height:278px; width:358px\" /></p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（7）属于划拨方式取得的土地使用权转让的，应当提交有批准权的人民政府或者主管部门的批准文件；</p>\r\n\r\n<p>（8）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料；</p>\r\n\r\n<p>（9）部分房屋转移登记引起面积变动的，还应提交房屋测绘成果；</p>\r\n\r\n<p>属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；属于原土地登记成果无解析坐标等按照规定需要进行权籍调查的，还应提交不动产权籍调查成果，不再提交地籍测绘成果；</p>\r\n\r\n<p>（10）属于本市企事业单位有偿转让国有、集体非住宅房屋的，还应提交《产权交易鉴证书》；</p>\r\n\r\n<p>（11）购买拍卖的房屋，申请人提交网签的房产买卖协议。</p>\r\n', '1029', '2016-05-09 14:57:43', null);
INSERT INTO `p_notice` VALUES ('9', '房改售房的转移登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）公有住房买卖协议书；</p>\r\n\r\n<p>（5）出售公有住房价格评估审核表；</p>\r\n\r\n<p>（6）天津市出售公有住房专用发票；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（7）出售单位产公有住房（含单位内部调增互换）的，售房单位应提交公有住房出售批准书；</p>\r\n\r\n<p>（8）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料；</p>\r\n\r\n<p>（9）部分房屋转移登记引起面积变动的，还应提交房屋测绘成果。</p>\r\n', '1029', '2016-05-09 15:03:50', null);
INSERT INTO `p_notice` VALUES ('10', '房地产赠与的转移登记', '<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证书、土地使用权证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）赠与合同；</p>\r\n\r\n<p>（5）契税缴纳凭证（查验原件）；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（6）属于划拨方式取得的土地使用权转让的，应当提交有批准权的人民政府或者主管部门的批准文件；</p>\r\n\r\n<p>（7）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料；</p>\r\n\r\n<p>（8）部分房屋转移登记引起面积变动的，还应提交房屋测绘成果；</p>\r\n\r\n<p>属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；属于原土地登记成果无解析坐标等需要进行权籍调查的，还应提交权籍调查成果，不再提交地籍测绘成果；</p>\r\n', '1029', '2016-05-09 15:04:49', null);
INSERT INTO `p_notice` VALUES ('11', '房地产继承、受遗赠的转移登记', '<p>原产权人去世后，产权转给法定继承人的称为继承，转给非法定继承人的称为遗赠。因继承、受遗赠取得不动产的，按照国家有关规定办理公证手续。</p>\r\n\r\n<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）继承书或者遗赠书；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（5）受遗赠的还应提交契税缴纳凭证（查验原件）；</p>\r\n\r\n<p>（6）属于划拨方式取得的土地使用权转让的，应当提交有批准权的人民政府或者主管部门的批准文件；</p>\r\n\r\n<p>（7）部分房屋转移登记引起面积变动的，还应提交房屋测绘成果；</p>\r\n\r\n<p>属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；属于原土地登记成果无解析坐标等需要进行不动产权籍调查的，还应提交不动产权籍调查成果，不再提交地籍测绘成果；</p>\r\n', '1029', '2016-05-09 15:05:22', null);
INSERT INTO `p_notice` VALUES ('12', '依生效法律文书办理的转移登记', '<p>常见情形：按照人马法院出具的协助执行通知书上的要求，登记机关进行房屋产权转移登记。</p>\r\n\r\n<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）人民法院或者仲裁委员会的生效法律文书（查验原件、复印件留存）和协助执行通知书；</p>\r\n\r\n<p>（5）契税缴纳凭证（查验原件）；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（6）属于划拨方式取得的土地使用权转让的，应当提交有批准权的人民政府或者主管部门的批准文件；</p>\r\n\r\n<p>（7）部分房屋转移登记引起面积变动的，还应提交房屋测绘成果，属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；属于原土地登记成果无解析坐标等情形需进行权籍调查的，还应提交权籍调查成果，不再提交地籍测绘成果；</p>\r\n\r\n<p>申请人不能提交原不动产权属证书的，登记经办机构依据协助执行通知书中载明的不能提交证书的情况办理转移登记，原不动产权属证书作废。</p>\r\n', '1029', '2016-05-09 15:05:46', null);
INSERT INTO `p_notice` VALUES ('13', '权利人姓名、名称等变更登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）原颁发身份证件机关出具的姓名、名称等变更的证明文件（查验原件、复印件留存）；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（5）属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；</p>\r\n\r\n<p>属于原土地登记成果无解析坐标等需进行不动产权籍调查的，还应提交权籍调查成果，不再提交地籍测绘成果。</p>\r\n\r\n<p>因企业改制导致名称变更的，应当提交有批准权的人民政府或者主管部门的批准文件。改制后以有偿方式使用土地的，还应提交土地出让合同、土地出让价款及契税缴纳凭证等（查验原件、复印件留存）。</p>\r\n', '1031', '2016-05-09 15:06:24', null);
INSERT INTO `p_notice` VALUES ('14', '地名改变的变更登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）房地产坐落的街道、门牌号等名称发生改变的证明文件（查验原件、复印件留存）；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（5）属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；</p>\r\n\r\n<p>属于原土地登记成果无解析坐标等需要进行不动产权籍调查的，还应提交不动产权籍调查成果，不再提交地籍测绘成果。</p>\r\n', '1031', '2016-05-09 15:06:38', null);
INSERT INTO `p_notice` VALUES ('15', '配偶之间变更房地登记权利人的变更登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）婚姻关系证明文件（查验原件、复印件留存）；</p>\r\n\r\n<p>（5）配偶之间变更房地登记权利人的协议（查验原件、复印件留存）；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（6）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料；；</p>\r\n\r\n<p>（7）属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；</p>\r\n\r\n<p>属于原土地登记成果无解析坐标等需要进行不动产权籍调查的，还应提交不动产权籍调查成果，不再提交地籍测绘成果。</p>\r\n', '1031', '2016-05-09 15:06:55', null);
INSERT INTO `p_notice` VALUES ('16', '房屋分割的变更登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）房屋分割的证明文件（查验原件、复印件留存）；</p>\r\n\r\n<p>（5）房屋测绘成果；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（6）因分割导致地名发生变更的，还应提交标准地名证明文件（查验原件，复印件留存）；</p>\r\n\r\n<p>（7）有抵押权、地役权登记的，应提交抵押权人、地役权人同意的书面材料；</p>\r\n\r\n<p>（8）属于独用宗地的，还应提交地籍测绘成果2份（包括电子版）；</p>\r\n\r\n<p>属于原土地登记成果无解析坐标等需要进行不动产权籍调查的，还应提交不动产权籍调查成果，不再提交地籍测绘成果。</p>\r\n', '1031', '2016-05-09 15:07:27', null);
INSERT INTO `p_notice` VALUES ('17', '房屋灭失的注销登记', '<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）房屋征收决定（房屋拆迁许可证）、征收实施单位（拆迁单位）依法完成征收（拆迁）及补偿的书面承诺以及与原权利人签订的拆迁补偿协议或土地整理储备计划、与原土地使用者签订的收购整理协议等有关房地权利消灭的证明文件；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（5）属于房屋被依法征收（拆迁）或收购整理储备土地，且已办理土地使用证的，还应提交土地使用证。</p>\r\n\r\n<p>涉及军用土地办理土地使用权注销登记的，提交经军队不动产主管部门审核后的相关材料。</p>\r\n', '1031', '2016-05-09 15:07:44', null);
INSERT INTO `p_notice` VALUES ('18', '放弃房地产权利的注销登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证或房地产权证或不动产权证书；</p>\r\n\r\n<p>（4）权利人放弃房地产权利的书面声明；</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>（5）收购整理协议、土地整理储备计划等证明文件；</p>\r\n\r\n<p>（6）已办理土地使用证的，还应提交土地使用证。</p>\r\n\r\n<p>（7）有抵押权、地役权登记记载的，还应提交抵押权、地役权权利人的书面同意材料。</p>\r\n\r\n<p>涉及军用土地办理土地使用权注销登记的，提交经军队不动产主管部门审核后的相关材料。</p>\r\n', '1031', '2016-05-09 15:08:00', null);
INSERT INTO `p_notice` VALUES ('19', '不动产抵押权登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书;</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）;</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或土地承包经营权证或林权证或海域使用权证或不动产权证书等不动产权属证书（原件暂存）;</p>\r\n\r\n<p>（4）抵押担保的主合同；</p>\r\n\r\n<p>（5）抵押合同。</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>以划拨土地使用权抵押的，还应当提供抵押权实现时优先交纳出让金的确认文件，无地上物的应提交批准抵押文件；</p>\r\n\r\n<p>以集体建设用地使用权抵押的，提交集体建设用地所有权人同意抵押证明；</p>\r\n\r\n<p>申请人持房证、地证申请办理抵押权登记的，应当同时申请办理房地统一登记，换发《不动产权证书》。</p>\r\n\r\n<p>国有土地范围内共用宗地上的房屋无土地信息的，申请人可持房屋所有权证等申请办理抵押权登记。</p>\r\n', '1031', '2016-05-09 15:08:34', null);
INSERT INTO `p_notice` VALUES ('20', '不动产抵押权注销登记', '<p>必备要件：</p>\r\n\r\n<p>（1）申请书；</p>\r\n\r\n<p>（2）申请人身份证明（查验原件、复印件留存）；</p>\r\n\r\n<p>（3）房屋所有权证、土地使用证或房地产权证或土地承包经营权证或林权证或海域使用权证或不动产权证书等不动产权属证书（原件暂存）；</p>\r\n\r\n<p>（4）土地他项权利证明书或抵押权证明书或房地产他项权证或不动产登记证明等；</p>\r\n\r\n<p>（5）证明抵押权消灭的文件。</p>\r\n\r\n<p>其他说明：</p>\r\n\r\n<p>申请抵押权设立、转移、变更登记符合下列条件的，登记经办机构应予以登记：</p>\r\n', '1031', '2016-05-09 15:08:50', null);
INSERT INTO `p_notice` VALUES ('21', '预告登记权利人入住满两年房地登记', '<p><strong>一、适用情形：</strong></p>\r\n\r\n<p>1.根据《天津市房屋权属登记条例》第24条规定，房地产开发企业应当自开发建设的商品房竣工验收之日起30日内申请办理房屋所有权首次登记，并将登记结果及时通知购房人。</p>\r\n\r\n<p>2.房地产开发企业未按照上述规定办理房屋所有权首次登记的，根据《天津市房屋权属登记条例》第54条规定，预购期房的房屋所有权预告登记权利人入住两年的，由不动产所在地的登记经办机构在不动产登记系统上按本文件规定进行项目数据补录后，购房人可直接到登记经办机构申请办理房屋所有权登记，领取不动产权证书。</p>\r\n\r\n<p>3.&ldquo;入住满两年&rdquo;是商品房取得《住宅商品房准许交付使用证》已满两年；或者购房人与房地产开发企业签定的房屋交接单满两年。无法提供上述文件的，由预告登记权利人出具关于入住已满两年的具结书。</p>\r\n\r\n<p>4.对于2002年12月31日以前未办理房屋所有权首次登记的项目，按照《天津市房屋权属登记条例》第59条规定办理补登手续。</p>\r\n\r\n<p><strong>二、办理程序：</strong></p>\r\n\r\n<p>1.清理项目，填写明细。登记经办机构应根据本辖区群众反映情况以及房地产执法检查等情况，每月整理预告登记权利人入住满两年的房地产开发企业未办理房屋首次登记的项目情况，对于已为购房人办理过预告登记，且权利人入住满两年的项目，填写预告登记权利人入住满两年或即将满两年商品房项目明细表。</p>\r\n\r\n<p>2.核实相关情况。登记经办机构按规定调查整理项目的有关土地登记以及项目销售等情况，并向房地产市场管理部门查询商品房销售许可手续、土地使用证、建设工程规划许可证、规划总平面图等文件，全面、细致地掌握项目相关情况。房地产市场管理部门应予积极配合，及时提供商品房销售许可档案留存的全部资料。</p>\r\n\r\n<p>3.委托测绘，补录房地数据。对符合规定的项目，由登记经办机构书面委托测绘部门进行房地测绘，测绘完成后，由登记经办机构通知信息中心将已售房屋数据信息从商品房网络系统导入登记网络系统，并补录土地信息。</p>\r\n\r\n<p>4.建立联系机制。数据补录同时，登记经办机构函告房地产市场管理部门，对未售出的房屋暂停商品房网上打印合同的功能。待房地产开发建设单位办理房屋所有权首次登记后，再函告房地产市场管理部门，恢复商品房网上打印合同的功能。</p>\r\n\r\n<p>5.为预告登记权利人办理房地登记。登记经办机构根据补录的已售房屋信息，依据本规程中商品房转移登记的相关规定，为入住满两年的预告登记权利人办理房地登记。其中，房地产开发企业仍在经营的，由买卖双方共同提出申请；房地产开发企业已注销、吊销或下落不明以及其他原因无法配合办理的，由预告登记权利人直接申请。其中，入住满两年的购房人，符合预告登记条件但未办理预告登记的，数据补录后，购房人到登记经办机构申请办理商品房登记时，应一并申请预告登记。登记经办机构核准房地产登记、核发不动产权证书的，视为已核准预告登记。</p>\r\n\r\n<p><strong>三、审核内容：</strong></p>\r\n\r\n<p>认真核实调查项目，并全面准确填写有关项目明细表，审核以下内容：</p>\r\n\r\n<p>1.按幢核查是否属于商品房销售许可或预售登记范围，核查商品房销售许可证号、范围、面积等情况；</p>\r\n\r\n<p>2.按幢核查购房人是否入住满两年；</p>\r\n\r\n<p>3.核查是否已办理预告登记或商品房买卖合同备案；</p>\r\n\r\n<p>4.核查项目开发建设单位是否存在，是否正在申请办理房屋所有权首次登记或房地产登记测量，督促开发建设单位积极办理房屋所有权首次登记；</p>\r\n\r\n<p>5.核查项目已售及未售情况；</p>\r\n\r\n<p>6.核查有关土地、规划等情况；</p>\r\n\r\n<p>7.核查项目的抵押、查封情况。</p>\r\n\r\n<p>核查上述情况时，房地产市场管理部门应配合登记经办机构的调查核实工作，出具相关书面核查证明。</p>\r\n\r\n<p>对属于商品房销售许可范围内的国有土地上的项目，测绘部门结合项目商品房预测算报告、土地登记或土地使用等有关情况，进行房产和地籍测量。</p>\r\n', '1031', '2016-05-09 15:09:35', null);
INSERT INTO `p_notice` VALUES ('22', '各类登记审核要点', '<p><strong>转移登记审核要点：</strong></p>\r\n\r\n<p>1.转让人是不动产登记簿记载的权利人或者预售人，受让人是有关证明文件中载明的受让人；</p>\r\n\r\n<p>2.申请转移登记的房屋及其占用范围内的土地在不动产登记簿记载范围内；</p>\r\n\r\n<p>3.无正在办理的更正登记、异议登记记载；</p>\r\n\r\n<p>4.无他人房屋所有权预告登记记载；</p>\r\n\r\n<p>5.无人民法院、人民检察院、公安机关依据法律规定采取限制措施记载；</p>\r\n\r\n<p>6.同一宗地分割转让的，办理转移登记前应先办理房地产分割登记；</p>\r\n\r\n<p>7.整宗土地已全部竣工的，不得对其中空地进行分割转让，不予办理分割转让登记手续。</p>\r\n\r\n<p>共用宗地的住宅无土地信息的，不动产登记经办机构应按有关规定进行土地补登补测。办理转移登记时，申请人应按本市有关规定补缴土地出让金。如确因土地权属来源文件不齐、土地查封等原因无法进行土地补登的，可先行办理房屋转移登记。&nbsp;&nbsp;&nbsp;</p>\r\n\r\n<p><strong>变更登记审核要点：</strong></p>\r\n\r\n<p>1.申请变更登记的房屋及其占用范围内的土地在不动产登记簿记载的范围内；</p>\r\n\r\n<p>2.申请变更登记的内容与有关文件证明的变更事实一致；</p>\r\n\r\n<p>3.无正在办理的更正登记、异议登记记载；</p>\r\n\r\n<p>4.无人民法院、人民检察院、公安机关依据法律规定采取限制措施记载。</p>\r\n\r\n<p>共用宗地的房屋无土地信息的，登记经办机构应按有关规定进行土地补登补测。如确因土地权属来源文件不齐、土地查封等原因无法进行土地补登的，可先行为申请人办理房屋变更登记。</p>\r\n\r\n<p><strong>注销登记审核要点：</strong></p>\r\n\r\n<p>1.申请人是征收实施单位（拆迁人）、土地储备机构或者不动产登记簿记载的权利人；</p>\r\n\r\n<p>2.申请注销登记的房地在不动产登记簿记载的范围内；</p>\r\n\r\n<p>3.有抵押权、地役权登记记载，权利人放弃房地产权利申请注销登记的，应经抵押权、地役权权利人书面同意。</p>\r\n\r\n<p><strong>抵押登记审核要点：</strong></p>\r\n\r\n<p>1.下列不动产不得抵押：</p>\r\n\r\n<p>（1）土地所有权；</p>\r\n\r\n<p>（2）耕地、宅基地、自留地、自留山等集体所有的土地使用权。但抵押人依法承包并经发包方同意抵押的荒山、荒沟、荒丘、荒滩等荒地的土地使用权，及以乡（镇）、村企业的厂房等建筑物抵押的占用范围内的土地使用权除外；</p>\r\n\r\n<p>（3）学校、幼儿园、医院等以公益为目的的事业单位、社会团体的教育设施、医疗卫生设施和其他社会公益设施</p>\r\n\r\n<p>（4）所有权、使用权不明或者有争议的不动产；</p>\r\n\r\n<p>（5）依法被查封等限制权利的不动产；</p>\r\n\r\n<p>（6）无地上物划拨国有土地使用权未经批准不得抵押。</p>\r\n\r\n<p>（7）油气及其他海洋矿产资源勘查开采的；</p>\r\n\r\n<p>（8）未按规定缴纳海域使用金、改变海域用途等违法用海的；</p>\r\n\r\n<p>（9）法律、行政法规规定不得抵押的其他不动产。</p>\r\n\r\n<p>2.属于《天津市新建住宅配套非经营性公建建设和管理办法》（津政办发〔2012〕29号）规定的教育、社区医疗卫生、文化体育、社区服务（含菜市场）、行政管理和市政公用等新建住宅配套非经营性公建，不得办理在建工程抵押；</p>\r\n\r\n<p>3.申请人是首次、转移、变更抵押权的当事人，且抵押人是不动产登记簿记载的权利人；属于在建工程抵押的,抵押人还应当是建设工程规划许可证载明的建设单位，抵押人是房地产开发企业的，应是登记簿记载的预售人；抵押人和抵押权人应与抵押合同签订双方一致；</p>\r\n\r\n<p>4.申请登记的房屋（以非商品房在建工程抵押的除外）及其占用范围内的土地（共用宗地上的房屋无土地信息的除外）以及海域等不动产在登记簿记载范围内；</p>\r\n\r\n<p>5.抵押合同清单载明的房地坐落、土地面积、房屋建筑面积等与登记簿记载一致；</p>\r\n\r\n<p>6.分割抵押的，应先办理宗地分割登记手续；</p>\r\n\r\n<p>7.乡镇、村企业的建设用地使用权不得单独抵押，设定抵押权的集体土地使用权应为已设定抵押的乡（镇）、村企业厂房等建筑物占用范围的集体土地使用权，及依法承包、租赁、拍卖取得的荒山、荒沟、荒丘、荒滩等荒地的集体土地使用权；</p>\r\n\r\n<p>8.无正在办理的更正登记、异议登记记载；</p>\r\n\r\n<p>9.无他人不动产预告登记记载；</p>\r\n\r\n<p>10.无人民法院、人民检察院、公安机关依据法律规定采取限制措施记载；</p>\r\n\r\n<p>11.持房、地两证申请办理房地产抵押权登记的，应先办理房地合一登记。</p>\r\n', '1032', '2016-05-09 15:11:38', null);
INSERT INTO `p_notice` VALUES ('23', '功能使用说明', '<p><span style=\"color:#ff6600\"><span style=\"font-size:22px\">　　欢迎使用天津市不动产登记网上预约服务系统，在互联网时代的今天，</span></span><span style=\"color:rgb(255, 102, 0); font-size:22px\">衷心希望我们的努力能使得办理业务更便捷、了解政策更容易、大伙距离更贴近。</span><span style=\"color:#ff6600\"><span style=\"font-size:22px\">为企业群众提供更好的服务是我们一直以来的宗旨！</span></span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\">　　本系统目前暂开放<span style=\"color:#00ccff\"><strong>办件指引、注册信息、登记预约、通知公告</strong></span>四个版块，以下为您介绍各版块的具体功能：</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\"><span style=\"color:#00ccff\"><strong>办件指引</strong></span>分为使用说明、登记要件、常见问题三个部分：</span></p>\r\n\r\n<p><span style=\"font-size:18px\"><span style=\"color:#33cc99\">使用说明</span>向您详细指导本系统的使用流程以及简要介绍各类不动产登记业务，目前您正在阅读的页面的就是其中的&ldquo;功能使用说明&rdquo;，很有用吧<img alt=\"wink\" src=\"http://www.tjsbdcdjzx.com:8080/projectmasteradmin/resources/js/ckeditor/plugins/smiley/images/wink_smile.png\" style=\"height:23px; width:23px\" title=\"wink\" /></span></p>\r\n\r\n<p><span style=\"font-size:18px\"><span style=\"color:#33cc99\">登记要件</span>向您详细介绍常见的19类不动产登记所需材料以及注意事项；</span></p>\r\n\r\n<p><span style=\"font-size:18px\"><span style=\"color:#33cc99\">常见问题</span>汇总了企业群众在办理登记业务中经常遇到的共性问题，请您有时间多看一看，没准能帮上大忙！</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\"><span style=\"color:#00ccff\"><strong>注册信息</strong></span>采用实名制，<strong>注册成功后才能使用预约功能</strong>，请按照要求认真填写各项信息并上传清晰的照片，我们在后台</span><span style=\"font-size:18px\">将</span><span style=\"font-size:18px\">进行严格的人工审核，避免虚假，杜绝黄牛！</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\"><span style=\"color:#00ccff\"><strong>登记预约</strong></span>在注册成功后就可以申请了，同申请注册时一样，请您仔细阅读预约协议，认真填写预约信息，预约成功后请按时赴约或及时取消以更好地利用宝贵的公共资源，也避免影响个人的信用记录。为了公平高效，目前<strong>个人业务必须是产权人才能申请预约</strong>，我们也会对预约资料进行严格审核。</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:18px\"><span style=\"color:#00ccff\"><strong>通知公告里</strong></span>有及时准确的资讯信息，请多多关注不要错过！</span></p>\r\n', '1033', '2016-05-12 14:40:43', null);
INSERT INTO `p_notice` VALUES ('24', '登记分类说明', '<p><span style=\"font-size:20px\">　　我们汇总了日常工作中较常见的19类登记，测试期间暂开通最常见的4类登记，分别是：</span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:20px\">新建商品房买卖的转移登记(从开发商处购买新房);</span></span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:20px\">存量房屋买卖的转移登记(</span><span style=\"font-size:20px\">买卖二手房);</span></span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:20px\">不动产抵押权登记;</span></span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:20px\">不动产抵押权注销登记。</span></span></p>\r\n\r\n<p><span style=\"font-size:18px\">　　各类登记的所需材料及注意事项请详见<span style=\"color:#00ccff\"><strong>办件指引</strong></span>&rarr;<span style=\"color:#33cc99\">登记要件</span>版块。</span></p>\r\n\r\n<hr />\r\n<p><span style=\"font-size:16px\">附19类登记列表：</span></p>\r\n\r\n<p><span style=\"font-size:18px\"><strong>一、新建商品房登记</strong></span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:18px\">1、新建商品房买卖的转移登记</span></span></p>\r\n\r\n<p><span style=\"font-size:18px\">2、预购商品房预告登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">3、注销预购商品房预告登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">4、抵押权预告登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">5、注销抵押权预告登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\"><strong>二、存量房转移登记</strong></span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:18px\">1、存量房屋买卖的转移登记</span></span></p>\r\n\r\n<p><span style=\"font-size:18px\">2、房改售房的转移登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">3、房地产赠与的转移登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">4、房地产继承、受遗赠的转移登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">5、依生效法律文书办理的转移登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">&nbsp;<strong>三、其他登记</strong></span></p>\r\n\r\n<p><span style=\"font-size:18px\">1、权利人姓名、名称等变更登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">2、地名改变的变更登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">3、配偶之间变更房地登记权利人的变更登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">4、房屋分割的变更登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">5、房屋灭失的注销登记</span></p>\r\n\r\n<p><span style=\"font-size:18px\">6、放弃房地产权利的注销登记</span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:18px\">7、不动产抵押权登记</span></span></p>\r\n\r\n<p><span style=\"color:#ff6600\"><span style=\"font-size:18px\">8、不动产抵押权注销登记</span></span></p>\r\n\r\n<p><span style=\"font-size:18px\">9、预告登记权利人入住满两年房地登记</span></p>\r\n', '1033', '2016-05-12 14:41:39', null);

-- ----------------------------
-- Table structure for `p_question`
-- ----------------------------
DROP TABLE IF EXISTS `p_question`;
CREATE TABLE `p_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionName` varchar(100) NOT NULL COMMENT '问题名称',
  `askUserId` int(11) DEFAULT '0' COMMENT '提问用户',
  `status` int(2) NOT NULL DEFAULT '0',
  `questionTypeId` int(2) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_question_p_system_dictionary` (`questionTypeId`),
  CONSTRAINT `p_question_p_system_dictionary` FOREIGN KEY (`questionTypeId`) REFERENCES `p_system_dictionary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_question
-- ----------------------------
INSERT INTO `p_question` VALUES ('5', '新房办房本要哪些资料？', '0', '0', '1024', '2016-04-26 11:04:24', null);
INSERT INTO `p_question` VALUES ('6', '二手房办房本要哪些资料？', '0', '0', '1026', '2016-04-26 11:06:48', null);
INSERT INTO `p_question` VALUES ('7', '注册&预约 常见问题', '0', '0', '1027', '2016-04-26 14:32:39', null);

-- ----------------------------
-- Table structure for `p_system_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `p_system_dictionary`;
CREATE TABLE `p_system_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dictionaryCategoryid` int(11) NOT NULL,
  `dictionaryValue` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_p_sytem_dictionary_p_system_dictionary_category` (`dictionaryCategoryid`),
  CONSTRAINT `fk_p_sytem_dictionary_p_system_dictionary_category` FOREIGN KEY (`dictionaryCategoryid`) REFERENCES `p_system_dictionary_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1036 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_system_dictionary
-- ----------------------------
INSERT INTO `p_system_dictionary` VALUES ('1024', '1008', '新建商品房');
INSERT INTO `p_system_dictionary` VALUES ('1026', '1008', '二手房买卖');
INSERT INTO `p_system_dictionary` VALUES ('1027', '1008', '其他');
INSERT INTO `p_system_dictionary` VALUES ('1028', '1009', '新建商品房登记');
INSERT INTO `p_system_dictionary` VALUES ('1029', '1009', '存量房转移登记');
INSERT INTO `p_system_dictionary` VALUES ('1031', '1009', '其他登记');
INSERT INTO `p_system_dictionary` VALUES ('1032', '1009', '须知常见问题');
INSERT INTO `p_system_dictionary` VALUES ('1033', '1009', '使用说明');
INSERT INTO `p_system_dictionary` VALUES ('1034', '1010', '不动产登记事务中心和平部');
INSERT INTO `p_system_dictionary` VALUES ('1035', '1010', '不动产登记事务中心河北部');

-- ----------------------------
-- Table structure for `p_system_dictionary_category`
-- ----------------------------
DROP TABLE IF EXISTS `p_system_dictionary_category`;
CREATE TABLE `p_system_dictionary_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryCode` varchar(45) NOT NULL,
  `categoryDesc` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_system_dictionary_category
-- ----------------------------
INSERT INTO `p_system_dictionary_category` VALUES ('1008', 'question', '问题');
INSERT INTO `p_system_dictionary_category` VALUES ('1009', 'notice', '须知类型');
INSERT INTO `p_system_dictionary_category` VALUES ('1010', 'handleOrg', '办理机构');
INSERT INTO `p_system_dictionary_category` VALUES ('1011', 'businessType', '业务类型');

-- 05-31必须更新 否则报错 注意id对应
INSERT INTO `estatedb`.`p_system_dictionary_category` (`id`, `categoryCode`, `categoryDesc`) VALUES ('1012', 'bookDate', '预约天数');
INSERT INTO `estatedb`.`p_system_dictionary` (`id`, `dictionaryCategoryid`, `dictionaryValue`) VALUES ('1036', '1012', '2');

-- 06-01数据库修改
ALTER TABLE `estatedb_book_control`
MODIFY COLUMN `date`  date NULL DEFAULT NULL COMMENT '日期' AFTER `id`;

-- 07-04 管理员表增加分区字段
ALTER TABLE `lyd_backstage_admin_user` ADD COLUMN `area` varchar(50) NOT NULL COMMENT '区县';
-- 07-04 p_notice增加分区字段
ALTER TABLE `p_notice` ADD COLUMN `area` int(2) DEFAULT 0 COMMENT '区县';
-- 07-04 p_question增加分区字段
ALTER TABLE `p_question` ADD COLUMN `area` int(2) DEFAULT 0 COMMENT '区县';
-- 07-04 lyd_announcement增加分区字段
ALTER TABLE `lyd_announcement` ADD COLUMN `area` int(2) DEFAULT 0 COMMENT '区县';
-- ----------------------------
-- 07-09 区县表修改 Table structure for p_area
-- ----------------------------
DROP TABLE IF EXISTS `p_area`;
CREATE TABLE `p_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaCode` int(2) NOT NULL,
  `areaDesc` varchar(8) NOT NULL,
  `handleOrgId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_area
-- ----------------------------
INSERT INTO `p_area` VALUES ('1', '0', '市区', '1034');
INSERT INTO `p_area` VALUES ('2', '1', '和平区', null);
INSERT INTO `p_area` VALUES ('3', '2', '河西区', null);
INSERT INTO `p_area` VALUES ('4', '3', '河北区', '1035');
INSERT INTO `p_area` VALUES ('5', '4', '河东区', null);
INSERT INTO `p_area` VALUES ('6', '5', '南开区', null);
INSERT INTO `p_area` VALUES ('7', '6', '红桥区', null);
INSERT INTO `p_area` VALUES ('8', '7', '东丽区', null);
INSERT INTO `p_area` VALUES ('9', '8', '西青区', null);
INSERT INTO `p_area` VALUES ('10', '9', '津南区', null);
INSERT INTO `p_area` VALUES ('11', '10', '北辰区', null);
INSERT INTO `p_area` VALUES ('12', '11', '武清区', null);
INSERT INTO `p_area` VALUES ('13', '12', '宝坻', null);
INSERT INTO `p_area` VALUES ('14', '13', '蓟县', null);
INSERT INTO `p_area` VALUES ('15', '14', '静海', null);
INSERT INTO `p_area` VALUES ('16', '15', '宁河', null);
INSERT INTO `p_area` VALUES ('17', '16', '塘沽', null);
INSERT INTO `p_area` VALUES ('18', '17', '大港', null);
INSERT INTO `p_area` VALUES ('19', '18', '汉沽', null);

-- ----------------------------
-- 20160718 预约时间增加删除字段
-- ----------------------------
ALTER TABLE `estatedb_work_time`
ADD COLUMN `isDel`  int(1) NULL DEFAULT 0 AFTER `areaName`;

-- ----------------------------
-- Table structure for p_area
-- ----------------------------
DROP TABLE IF EXISTS `p_area`;
CREATE TABLE `p_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaCode` int(2) NOT NULL,
  `areaDesc` varchar(8) NOT NULL,
  `handleOrgId` int(11) DEFAULT NULL,
  `typeOrTime` varchar(4) DEFAULT NULL COMMENT '区分类型或者时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_area
-- ----------------------------
INSERT INTO `p_area` VALUES ('1', '0', '市区', '1034', null);
INSERT INTO `p_area` VALUES ('2', '1', '和平区', '1038', null);
INSERT INTO `p_area` VALUES ('3', '2', '河西区', '1041', null);
INSERT INTO `p_area` VALUES ('4', '3', '河北区', '1035', null);
INSERT INTO `p_area` VALUES ('5', '4', '河东区', '1040', null);
INSERT INTO `p_area` VALUES ('6', '5', '南开区', '1039', null);
INSERT INTO `p_area` VALUES ('7', '6', '红桥区', '1042', null);
INSERT INTO `p_area` VALUES ('8', '7', '东丽区', '1043', null);
INSERT INTO `p_area` VALUES ('9', '8', '西青区', '1044', null);
INSERT INTO `p_area` VALUES ('10', '9', '津南区', '1046', null);
INSERT INTO `p_area` VALUES ('11', '10', '北辰区', '1045', null);
INSERT INTO `p_area` VALUES ('12', '11', '武清区', '1047', null);
INSERT INTO `p_area` VALUES ('13', '12', '宝坻区', '1048', null);
INSERT INTO `p_area` VALUES ('14', '13', '蓟州区', '1050', null);
INSERT INTO `p_area` VALUES ('15', '14', '静海区', '1037', null);
INSERT INTO `p_area` VALUES ('16', '15', '宁河区', '1049', null);

-- 07-04 p_system_dictionary_category 增加分区字段
ALTER TABLE `p_system_dictionary_category` ADD COLUMN `handleOrgId` int(4) DEFAULT 0 COMMENT '区县';

-- 08-06 estatedb_work_time增加 根据 子业务类型 发布预约数
ALTER TABLE `estatedb_work_time` ADD COLUMN `noticeName` varchar(50) default NULL COMMENT '子业务类型';
ALTER TABLE `estatedb_work_time` ADD COLUMN `noticeId` int(11) default 0 COMMENT '子业务类型ID';

-- 08-06 estatedb_book_control 增加 根据 子业务类型 发布预约数
ALTER TABLE `estatedb_book_control` ADD COLUMN `noticeName` varchar(50) default NULL COMMENT '子业务类型';
ALTER TABLE `estatedb_book_control` ADD COLUMN `noticeId` int(50) default 0 COMMENT '子业务类型ID';

-- 08-09 estatedb_order_records 增加东丽字段
ALTER TABLE `estatedb_order_records` ADD COLUMN `usertype` int(11) DEFAULT NULL;
ALTER TABLE `estatedb_order_records` ADD COLUMN `obligeename` varchar(11) DEFAULT NULL COMMENT '权利人姓名';
ALTER TABLE `estatedb_order_records` ADD COLUMN `obligeeidcard` varchar(18) DEFAULT NULL COMMENT '权利人身份证号';
ALTER TABLE `estatedb_order_records` ADD COLUMN `isHaveHasagent` int(2) DEFAULT NULL COMMENT '是否有代理人';

INSERT INTO `p_system_dictionary` VALUES ('1038', '1010', '不动产登记事务中心和平部');
INSERT INTO `p_system_dictionary` VALUES ('1039', '1010', '不动产登记事务中心南开部');
INSERT INTO `p_system_dictionary` VALUES ('1040', '1010', '不动产登记事务中心河东部');
INSERT INTO `p_system_dictionary` VALUES ('1041', '1010', '不动产登记事务中心河西部');
INSERT INTO `p_system_dictionary` VALUES ('1042', '1010', '不动产登记事务中心红桥部');
INSERT INTO `p_system_dictionary` VALUES ('1043', '1010', '不动产登记事务中心东丽部');
INSERT INTO `p_system_dictionary` VALUES ('1044', '1010', '不动产登记事务中心西青部');
INSERT INTO `p_system_dictionary` VALUES ('1045', '1010', '不动产登记事务中心北辰部');
INSERT INTO `p_system_dictionary` VALUES ('1046', '1010', '不动产登记事务中心津南部');
INSERT INTO `p_system_dictionary` VALUES ('1047', '1010', '不动产登记事务中心武清部');
INSERT INTO `p_system_dictionary` VALUES ('1048', '1010', '不动产登记事务中心宝坻部');
INSERT INTO `p_system_dictionary` VALUES ('1049', '1010', '不动产登记事务中心宁河部');
INSERT INTO `p_system_dictionary` VALUES ('1050', '1010', '不动产登记事务中心蓟州部');
INSERT INTO `p_system_dictionary` VALUES ('1051', '1010', '不动产登记事务中心滨海部');


INSERT INTO `lyd_resource` VALUES ('208', '/notice/selectTypeByhandleOrgId', '根据区县获取类型', '0');
INSERT INTO `lyd_resource` VALUES ('210', '/bookControls/*', '全部预约数列表', '1');  -- 放在时间管理下
INSERT INTO `lyd_resource` VALUES ('211', '/bookControlForm/*/*', '修改预约数', '0'); -- 放在时间管理下
INSERT INTO `lyd_resource` VALUES ('212', '/orderInfo/*', '预约详情页', '0'); -- 放在会员预约管理下