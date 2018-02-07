package com.leyidai.entity.enumtype.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import com.leyidai.entity.Announcement.AnnouncementStatus;

public class AnnouncementStatusHandler extends LYDBaseEnumTypeHandler<AnnouncementStatus>{

	@Override
	public AnnouncementStatus getEnumByValue(int value) {
		AnnouncementStatus[] announcementStatuses = AnnouncementStatus.class.getEnumConstants();
		for(AnnouncementStatus announcementStatus: announcementStatuses){
			if(announcementStatus.getValue() == value ){
				return announcementStatus;
			}
		}
		throw new IllegalArgumentException("unknow enum value {" + value +"} for AnnouncementStatus.");
	}
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			AnnouncementStatus announcementStatus, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, announcementStatus.getValue());
	}
	
}
