package com.leyidai.web.util;

public class ConstantVariable{

	public static Integer wuqingNum=0;
	public static Integer wuqingMax=50;
	//东丽北辰大港塘沽和津南需要建立号池，取消的不即时返回总数，等待增加号
	public static String haochiRigion="1046;1043;1045;1103;1101";
	public static String testPersonString="120105197406144810,230303199309164935,410523199408156033,420114198011134812,510303198408250014,370686199306216515";
	public static boolean flagIsFlushOver=true;
	public static String webSocket="http://162.16.160.50/webservice/Real/AppointmentSvc.asmx/GetAppointmentResult";
	//在未发送自动审核时，如下是examineStatus的状态，0，1，2，3分别代表未设定，审核成功，审核失败，无响应，不进行操作，0是数据库默认值
	public static Integer preSendDataStatus=0;
	public static Integer sendDataFail=3;
	public static Integer dataExamineFail=2;
	public static Integer dataExamineSuccess=1;
	public static Integer dataExamineNoAction=4;
	//order表中除去1的其他状态
	public static Integer[] exceptionStatus={2,3,4,5,6,8,9,10};
	
	//sendDataTemp表的状态常量
	public static Integer sendDataTempStatusDefault=0;
	public static Integer sendDataTempStatusSuccess=1;
	public static Integer sendDataTempStatusFail=2;
	public static Integer sendDataTempStatusNoAction=3;
	public static Integer sendDataTempStatusOverTime=4;
	
	
	public static String queryFileStatusSocket="http://162.16.160.50/webservice/Real/FileStatusSvc.asmx/GetFileStatus";
	
	public static String shiquOrgId="1034";
	
	
	
	
	
	
	
	
	
	
	
	public static String[] strJsonStrings={
			"{'id':77604,'name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':1}",
			"{'id':'0','name':'孙福芹','idcard':'372925197008125734','hourseNumber':'2015-0051543','area':1041,'transactType':1}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013058607','area':1041,'transactType':1}",
			"{'id':'0','name':'陈建文','idcard':'232303198501300826','hourseNumber':'2015-0130600','area':1041,'transactType':1}",
			"{'id':'0','name':'陈建辉','idcard':'23230319820120092x','hourseNumber':'2015-0130601','area':1041,'transactType':1}",
			"{'id':'0','name':'刘树立','idcard':'120112196904130920','hourseNumber':'103020833737','area':1041,'transactType':1}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013050607','area':1041,'transactType':1}",
			"{'id':'0','name':'吴佩荣','idcard':'120114197509288426','hourseNumber':'2014-0148643','area':1041,'transactType':1}",
			"{'id':'0','name':'刘建东','idcard':'120103196901023218','hourseNumber':'10303001254','area':1041,'transactType':1}",
			"{'id':'0','name':'王一心','idcard':'210423197810010022','hourseNumber':'2014-0075481','area':1041,'transactType':1}",
			"{'id':'0','name':'魏秀丽','idcard':'120112197801151328','hourseNumber':'103021116237','area':1041,'transactType':1}",
			"{'id':'0','name':'罗艳','idcard':'610521198512234265','hourseNumber':'2014-0075607','area':1041,'transactType':1}",
			"{'id':'0','name':'陈婷','idcard':'120109197609146520','hourseNumber':'2014-01748824','area':1041,'transactType':1}",
			"{'id':'0','name':'余金权','idcard':'330325197504283115','hourseNumber':'103021117467','area':1041,'transactType':1}",
			"{'id':'0','name':'杨静','idcard':'120110198212251226','hourseNumber':'1026358','area':1041,'transactType':1}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':1}",
			"{'id':'0','name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':1}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':1}",
			"{'id':'0','name':'刘景琪','idcard':'210281198909260512','hourseNumber':'2015-0051493','area':1041,'transactType':1}",
			"{'id':'0','name':'蓝颖','idcard':'120101198010155520','hourseNumber':'41003131925357','area':1041,'transactType':1}",
			"{'id':'0','name':'张茜','idcard':'120106198505200540','hourseNumber':'20150083758','area':1041,'transactType':1}",
			"{'id':'0','name':'李富英','idcard':'120113197108270827','hourseNumber':'2015-0083759','area':1041,'transactType':1}",
			"{'id':'0','name':'韩景辉','idcard':'120102199109180014','hourseNumber':'103020800535','area':1041,'transactType':1}",
			"{'id':'0','name':'朱江','idcard':'120105196904172414','hourseNumber':'103021534455','area':1041,'transactType':1}",
			"{'id':'0','name':'朱江','idcard':'120106197611090010','hourseNumber':'103020759656','area':1041,'transactType':1}",
			"{'id':'0','name':'朱江','idcard':'120103197508212615','hourseNumber':'103020911698','area':1041,'transactType':1}",
			"{'id':'0','name':'朱江','idcard':'120103196807152646','hourseNumber':'103021107777','area':1041,'transactType':1}",
			"{'id':77604,'name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':4}",
			"{'id':'0','name':'孙福芹','idcard':'372925197008125734','hourseNumber':'2015-0051543','area':1041,'transactType':4}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013058607','area':1041,'transactType':4}",
			"{'id':'0','name':'陈建文','idcard':'232303198501300826','hourseNumber':'2015-0130600','area':1041,'transactType':4}",
			"{'id':'0','name':'陈建辉','idcard':'23230319820120092x','hourseNumber':'2015-0130601','area':1041,'transactType':4}",
			"{'id':'0','name':'刘树立','idcard':'120112196904130920','hourseNumber':'103020833737','area':1041,'transactType':4}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013050607','area':1041,'transactType':4}",
			"{'id':'0','name':'吴佩荣','idcard':'120114197509288426','hourseNumber':'2014-0148643','area':1041,'transactType':4}",
			"{'id':'0','name':'刘建东','idcard':'120103196901023218','hourseNumber':'10303001254','area':1041,'transactType':4}",
			"{'id':'0','name':'王一心','idcard':'210423197810010022','hourseNumber':'2014-0075481','area':1041,'transactType':4}",
			"{'id':'0','name':'魏秀丽','idcard':'120112197801151328','hourseNumber':'103021116237','area':1041,'transactType':4}",
			"{'id':'0','name':'罗艳','idcard':'610521198512234265','hourseNumber':'2014-0075607','area':1041,'transactType':4}",
			"{'id':'0','name':'陈婷','idcard':'120109197609146520','hourseNumber':'2014-01748824','area':1041,'transactType':4}",
			"{'id':'0','name':'余金权','idcard':'330325197504283115','hourseNumber':'103021117467','area':1041,'transactType':4}",
			"{'id':'0','name':'杨静','idcard':'120110198212251226','hourseNumber':'1026358','area':1041,'transactType':4}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':4}",
			"{'id':'0','name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':4}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':4}",
			"{'id':'0','name':'刘景琪','idcard':'210281198909260512','hourseNumber':'2015-0051493','area':1041,'transactType':4}",
			"{'id':'0','name':'蓝颖','idcard':'120101198010155520','hourseNumber':'41003131925357','area':1041,'transactType':4}",
			"{'id':'0','name':'张茜','idcard':'120106198505200540','hourseNumber':'20150083758','area':1041,'transactType':4}",
			"{'id':'0','name':'李富英','idcard':'120113197108270827','hourseNumber':'2015-0083759','area':1041,'transactType':4}",
			"{'id':'0','name':'韩景辉','idcard':'120102199109180014','hourseNumber':'103020800535','area':1041,'transactType':4}",
			"{'id':'0','name':'朱江','idcard':'120105196904172414','hourseNumber':'103021534455','area':1041,'transactType':4}",
			"{'id':'0','name':'朱江','idcard':'120106197611090010','hourseNumber':'103020759656','area':1041,'transactType':4}",
			"{'id':'0','name':'朱江','idcard':'120103197508212615','hourseNumber':'103020911698','area':1041,'transactType':4}",
			"{'id':'0','name':'朱江','idcard':'120103196807152646','hourseNumber':'103021107777','area':1041,'transactType':4}",
			"{'id':77604,'name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':2}",
			"{'id':'0','name':'孙福芹','idcard':'372925197008125734','hourseNumber':'2015-0051543','area':1041,'transactType':2}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013058607','area':1041,'transactType':2}",
			"{'id':'0','name':'陈建文','idcard':'232303198501300826','hourseNumber':'2015-0130600','area':1041,'transactType':2}",
			"{'id':'0','name':'陈建辉','idcard':'23230319820120092x','hourseNumber':'2015-0130601','area':1041,'transactType':2}",
			"{'id':'0','name':'刘树立','idcard':'120112196904130920','hourseNumber':'103020833737','area':1041,'transactType':2}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013050607','area':1041,'transactType':2}",
			"{'id':'0','name':'吴佩荣','idcard':'120114197509288426','hourseNumber':'2014-0148643','area':1041,'transactType':2}",
			"{'id':'0','name':'刘建东','idcard':'120103196901023218','hourseNumber':'10303001254','area':1041,'transactType':2}",
			"{'id':'0','name':'王一心','idcard':'210423197810010022','hourseNumber':'2014-0075481','area':1041,'transactType':2}",
			"{'id':'0','name':'魏秀丽','idcard':'120112197801151328','hourseNumber':'103021116237','area':1041,'transactType':2}",
			"{'id':'0','name':'罗艳','idcard':'610521198512234265','hourseNumber':'2014-0075607','area':1041,'transactType':2}",
			"{'id':'0','name':'陈婷','idcard':'120109197609146520','hourseNumber':'2014-01748824','area':1041,'transactType':2}",
			"{'id':'0','name':'余金权','idcard':'330325197504283115','hourseNumber':'103021117467','area':1041,'transactType':2}",
			"{'id':'0','name':'杨静','idcard':'120110198212251226','hourseNumber':'1026358','area':1041,'transactType':2}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':2}",
			"{'id':'0','name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':2}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':2}",
			"{'id':'0','name':'刘景琪','idcard':'210281198909260512','hourseNumber':'2015-0051493','area':1041,'transactType':2}",
			"{'id':'0','name':'蓝颖','idcard':'120101198010155520','hourseNumber':'41003131925357','area':1041,'transactType':2}",
			"{'id':'0','name':'张茜','idcard':'120106198505200540','hourseNumber':'20150083758','area':1041,'transactType':2}",
			"{'id':'0','name':'李富英','idcard':'120113197108270827','hourseNumber':'2015-0083759','area':1041,'transactType':2}",
			"{'id':'0','name':'韩景辉','idcard':'120102199109180014','hourseNumber':'103020800535','area':1041,'transactType':2}",
			"{'id':'0','name':'朱江','idcard':'120105196904172414','hourseNumber':'103021534455','area':1041,'transactType':2}",
			"{'id':'0','name':'朱江','idcard':'120106197611090010','hourseNumber':'103020759656','area':1041,'transactType':2}",
			"{'id':'0','name':'朱江','idcard':'120103197508212615','hourseNumber':'103020911698','area':1041,'transactType':2}",
			"{'id':'0','name':'朱江','idcard':'120103196807152646','hourseNumber':'103021107777','area':1041,'transactType':2}",
			"{'id':77604,'name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':3}",
			"{'id':'0','name':'孙福芹','idcard':'372925197008125734','hourseNumber':'2015-0051543','area':1041,'transactType':3}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013058607','area':1041,'transactType':3}",
			"{'id':'0','name':'陈建文','idcard':'232303198501300826','hourseNumber':'2015-0130600','area':1041,'transactType':3}",
			"{'id':'0','name':'陈建辉','idcard':'23230319820120092x','hourseNumber':'2015-0130601','area':1041,'transactType':3}",
			"{'id':'0','name':'刘树立','idcard':'120112196904130920','hourseNumber':'103020833737','area':1041,'transactType':3}",
			"{'id':'0','name':'张悦','idcard':'120101198711103512','hourseNumber':'2013050607','area':1041,'transactType':3}",
			"{'id':'0','name':'吴佩荣','idcard':'120114197509288426','hourseNumber':'2014-0148643','area':1041,'transactType':3}",
			"{'id':'0','name':'刘建东','idcard':'120103196901023218','hourseNumber':'10303001254','area':1041,'transactType':3}",
			"{'id':'0','name':'王一心','idcard':'210423197810010022','hourseNumber':'2014-0075481','area':1041,'transactType':3}",
			"{'id':'0','name':'魏秀丽','idcard':'120112197801151328','hourseNumber':'103021116237','area':1041,'transactType':3}",
			"{'id':'0','name':'罗艳','idcard':'610521198512234265','hourseNumber':'2014-0075607','area':1041,'transactType':3}",
			"{'id':'0','name':'陈婷','idcard':'120109197609146520','hourseNumber':'2014-01748824','area':1041,'transactType':3}",
			"{'id':'0','name':'余金权','idcard':'330325197504283115','hourseNumber':'103021117467','area':1041,'transactType':3}",
			"{'id':'0','name':'杨静','idcard':'120110198212251226','hourseNumber':'1026358','area':1041,'transactType':3}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':3}",
			"{'id':'0','name':'刘兆文','idcard':'370828198604205315','hourseNumber':'2015-0083745','area':1041,'transactType':3}",
			"{'id':'0','name':'彭娟','idcard':'120105198209103926','hourseNumber':'2014-0075608','area':1041,'transactType':3}",
			"{'id':'0','name':'刘景琪','idcard':'210281198909260512','hourseNumber':'2015-0051493','area':1041,'transactType':3}",
			"{'id':'0','name':'蓝颖','idcard':'120101198010155520','hourseNumber':'41003131925357','area':1041,'transactType':3}",
			"{'id':'0','name':'张茜','idcard':'120106198505200540','hourseNumber':'20150083758','area':1041,'transactType':3}",
			"{'id':'0','name':'李富英','idcard':'120113197108270827','hourseNumber':'2015-0083759','area':1041,'transactType':3}",
			"{'id':'0','name':'韩景辉','idcard':'120102199109180014','hourseNumber':'103020800535','area':1041,'transactType':3}",
			"{'id':'0','name':'朱江','idcard':'120105196904172414','hourseNumber':'103021534455','area':1041,'transactType':3}",
			"{'id':'0','name':'朱江','idcard':'120106197611090010','hourseNumber':'103020759656','area':1041,'transactType':3}",
			"{'id':'0','name':'朱江','idcard':'120103197508212615','hourseNumber':'103020911698','area':1041,'transactType':3}",
			"{'id':'0','name':'朱江','idcard':'120103196807152646','hourseNumber':'103021107777','area':1041,'transactType':3}",
			"{'id':'0','name':'刘莉莉','idcard':'120105198907074560','hourseNumber':'2014—0148801','area':1041,'transactType':1}"};
}
