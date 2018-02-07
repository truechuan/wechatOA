package com.leyidai.admin.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.leyidai.entity.NoticeViewType;

public interface NoticeViewTypeMapper {
	/******************************* 2017-3-9史晓昊 add *************************************/
	@Select("SELECT limitNum FROM `noticeviewtype`WHERE noticeViewTimeId=#{noticeViewTimeId}")
	public NoticeViewType getLimitNumByNoticeViewTimeId(int noticeViewTimeId);

	@Update("UPDATE noticeviewtype SET limitNum=#{limitNum} WHERE noticeViewTimeId=#{noticeViewTimeId}")
	public int updateLimitNumBynoticeViewTimeId(NoticeViewType noticeViewType);

}
