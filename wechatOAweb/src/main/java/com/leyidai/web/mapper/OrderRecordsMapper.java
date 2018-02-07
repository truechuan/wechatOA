package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.AddOrder;
import com.leyidai.entity.OrderRecordTemp;
import com.leyidai.entity.OrderRecords;
import com.leyidai.entity.OrderRecordsProvider;
import com.leyidai.entity.RecordType;

public interface OrderRecordsMapper {

	@Insert("insert into estatedb_order_records(`uid`,`openid`,`transactOrgId`,`transactOrgName`,`transactTypeId`,`transactTypeName`,"
			+ "`transactDate`,`transactTimeId`,`transactTime`,`deadTime`,`name`,`idcard`,`mobile`,`noticeFlg`,`area`,`hourseNumber`,"
			+ "`hourseAddress`,`createTime`,`status`,`del`,`failReason`,`usertype`,`obligeename`,`obligeeidcard`,`isHaveHasagent`,`IPAddress`,`examineStatus`) "
			+ "values (#{uid}, #{openid}, #{transactOrgId}, #{transactOrgName}, #{transactTypeId}, #{transactTypeName},"
			+ " #{transactDate}, #{transactTimeId}, #{transactTime}, #{deadTime}, #{name}, #{idcard}, #{mobile}, #{noticeFlg}, #{area}, "
			+ "#{hourseNumber}, #{hourseAddress}, #{createTime}, #{status}, #{del}, #{failReason}, #{usertype}, #{obligeename}, #{obligeeidcard}, #{isHaveHasagent},#{IPAddress},#{examineStatus})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id",
			before = false, resultType = Integer.class)
	public int insert(OrderRecords orderRecords);

	@Insert("insert into estatedb_order_records_temp(`uid`,`openid`,`transactOrgId`,`transactOrgName`,`transactTypeId`,`transactTypeName`,"
			+ "`transactDate`,`transactTimeId`,`transactTime`,`deadTime`,`name`,`idcard`,`mobile`,`noticeFlg`,`area`,`hourseNumber`,"
			+ "`hourseAddress`,`createTime`,`status`,`del`,`failReason`,`usertype`,`obligeename`,`obligeeidcard`,`isHaveHasagent`,`IPAddress`) "
			+ "values (#{uid}, #{openid}, #{transactOrgId}, #{transactOrgName}, #{transactTypeId}, #{transactTypeName},"
			+ " #{transactDate}, #{transactTimeId}, #{transactTime}, #{deadTime}, #{name}, #{idcard}, #{mobile}, #{noticeFlg}, #{area}, "
			+ "#{hourseNumber}, #{hourseAddress}, #{createTime}, #{status}, #{del}, #{failReason}, #{usertype}, #{obligeename}, #{obligeeidcard}, #{isHaveHasagent},#{IPAddress})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id",
			before = false, resultType = Integer.class)
	public int insertTemp(OrderRecordTemp orderRecordTemp);

	@Insert("insert into estatedb_order_records_temp(`uid`,`openid`,`transactOrgId`,`transactOrgName`,`transactTypeId`,`transactTypeName`,"
			+ "`transactDate`,`transactTimeId`,`transactTime`,`deadTime`,`name`,`idcard`,`mobile`,`noticeFlg`,`area`,`hourseNumber`,"
			+ "`hourseAddress`,`createTime`,`status`,`del`,`failReason`,`usertype`,`obligeename`,`obligeeidcard`,`isHaveHasagent`,`IPAddress`) "
			+ "values (#{uid}, #{openid}, #{transactOrgId}, #{transactOrgName}, #{transactTypeId}, #{transactTypeName},"
			+ " #{transactDate}, #{transactTimeId}, #{transactTime}, #{deadTime}, #{name}, #{idcard}, #{mobile}, #{noticeFlg}, #{area}, "
			+ "#{hourseNumber}, #{hourseAddress}, #{createTime}, #{status}, #{del}, #{failReason}, #{usertype}, #{obligeename}, #{obligeeidcard}, #{isHaveHasagent},#{IPAddress})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id",
			before = false, resultType = Integer.class)
	public int insertTempByOrder(OrderRecords orderRecords);

	@Insert("insert into estatedb_order_records(`uid`,`openid`,`transactOrgId`,`transactOrgName`,`transactTypeId`,`transactTypeName`,"
			+ "`transactDate`,`transactTimeId`,`transactTime`,`deadTime`,`name`,`idcard`,`mobile`,`noticeFlg`,`area`,`hourseNumber`,"
			+ "`hourseAddress`,`createTime`,`status`,`del`,`failReason`,`usertype`,`obligeename`,`obligeeidcard`,`isHaveHasagent`,`IPAddress`) "
			+ "values (#{uid}, #{openid}, #{transactOrgId}, #{transactOrgName}, #{transactTypeId}, #{transactTypeName},"
			+ " #{transactDate}, #{transactTimeId}, #{transactTime}, #{deadTime}, #{name}, #{idcard}, #{mobile}, #{noticeFlg}, #{area}, "
			+ "#{hourseNumber}, #{hourseAddress}, #{createTime}, #{status}, #{del}, #{failReason}, #{usertype}, #{obligeename}, #{obligeeidcard}, #{isHaveHasagent},#{IPAddress})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id",
			before = false, resultType = Integer.class)
	public int insertOrderByTemp(OrderRecordTemp orderRecords);

	@Update("update estatedb_order_records set `orderTaking`=#{orderTaking},`status`=#{status}, "
			+ "`numberOfTake`=#{numberOfTake} where id=#{id}")
	public int updateOrderRecord(OrderRecords orderRecords);

	// 更新temp表条目的状态，是否flush完成
	@Update("update estatedb_order_records_temp set `isFlushOver`=#{isFlushOver} where id=#{id}")
	public void updateOrderRecordTempFlag(OrderRecordTemp orderRecordTemp);

	// 更新temp表条目的状态,全部排队失败
	@Update("update estatedb_order_records_temp set `isFlushOver`=2 where `isFlushOver`=0")
	public void updateOrderRecordTempFlagOver();

	@InsertProvider(type = OrderRecordsProvider.class, method = "update")
	public int update(OrderRecords orderRecords);

	@Select("select * from estatedb_order_records where openid = #{openid} order by createTime desc")
	public List<OrderRecords> selectByOpenid(String openid);

	// 查询出temp表匹配openid的数据集，用户在temp表中的排队情况。
	@Select("select * from estatedb_order_records_temp where openid = #{openid} order by createTime desc")
	public List<OrderRecordTemp> selectTempByOpenid(String openid);

	@Select("select * from estatedb_order_records where openid = #{openid} and date(transactDate) = curdate() and status=2")
	public List<OrderRecords> selectByOpenIdAndToady(String openid);

	@Select("select * from estatedb_order_records where openid = #{openid} and status in(1,2,10)")
	public List<OrderRecords> selectByOpenIdAndStatus(String openid);

	@Select("select * from estatedb_order_records_temp where openid = #{openid} and isFlushOver=0")
	public List<OrderRecordTemp> selectByOpenIdAndFlushOver(String openid);

	@Select("select * from  estatedb_order_records where openid = #{openid} and status in(1,2,10)")
	public List<OrderRecords> selectByOpenIdAndStatusFast(String openid);

	@Select("select * from estatedb_order_records where date(transactDate) = #{date} and status = 2")
	public List<OrderRecords> selectByDate(String date);

	@Select("select * from estatedb_order_records where date(transactDate) = #{date} and status = 2 and area=#{area}")
	public List<OrderRecords> selectByDateAndArea(@Param("date") String date,
			@Param("area") int area);

	@Select("select * from estatedb_addorder where date(transactDate) = #{date} and status = 2 and area=#{area}")
	public List<AddOrder> selectAddOrderByDateAndArea(
			@Param("date") String date, @Param("area") int area);

	@Select("select * from estatedb_order_records where id = #{id}")
	public OrderRecords selectById(long id);

	@Select("select * from estatedb_order_records where IPAddress = #{IPAddress} order by createTime desc LIMIT 0,1")
	public OrderRecords selectByIP(String IPAddress);

	@Select("select * from estatedb_order_records where IPAddress = #{IPAddress} order by createTime desc LIMIT 1")
	public OrderRecords selectByFastIP(String IPAddress);

	// 查询某个recordtype条件下的总数
	@Select("select count(*) from estatedb_order_records_temp where date(transactDate) = #{date} and area=#{area} and transactTypeId=#{transacttypeid} and transactTime=#{time} and isFlushOver=0")
	public int getCountTempList(RecordType recordType);

	// 查询某个recordtype条件下的集合
	@Select("select * from estatedb_order_records_temp where date(transactDate) = #{date} and area=#{area} and transactTypeId=#{transacttypeid} and transactTime=#{time} and isFlushOver=0")
	public List<OrderRecordTemp> getTempList(RecordType recordType);

	// 查询是否存在未刷的缓存数据
	@Select("select id from estatedb_order_records_temp where isFlushOver=0 limit 1")
	public String hasFlush();

	@Select("select count(*) from estatedb_order_records where status in (1,2) and idcard=#{idcard}")
	public int isIdcardDuplicate(@Param("idcard") String idcard);

	// 根据校验状态值查询到需要校验的数据集
	@Select("select id,transactOrgId,transactTypeName,name,idcard,hourseNumber,examineStatus from estatedb_order_records where examineStatus=#{examineStatus}")
	public List<OrderRecords> getPreSendData(
			@Param("examineStatus") int examineStatus);

	// 更新order表条目的状态，是否校验通过
	@Update("update estatedb_order_records set `status`=#{status},`examineStatus`=#{examineStatus} where id=#{id}")
	public void updateOrderExamine(OrderRecords orderRecords);

	// @Param("date")String date,@Param("area")int
	// area,@Param("transactTypeId")String transactTypeId,@Param("time")String
	// time
}
