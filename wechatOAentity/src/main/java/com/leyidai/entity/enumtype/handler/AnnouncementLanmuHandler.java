package com.leyidai.entity.enumtype.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import com.leyidai.entity.Announcement.AnnouncementLanmu;


/**
 * 公告栏目枚举类型转换
 * @author song
 *
 */

public class AnnouncementLanmuHandler extends LYDBaseEnumTypeHandler<AnnouncementLanmu>{

	@Override
	public AnnouncementLanmu getEnumByValue(int value) {
		AnnouncementLanmu[] announcementLanmus = AnnouncementLanmu.class.getEnumConstants();
		for(AnnouncementLanmu announcementLanmu: announcementLanmus){
			if(announcementLanmu.getValue() == value ){
				return announcementLanmu;
			}
		}
		throw new IllegalArgumentException("unknow enum value {" + value +"} for AnnouncementLanmu.");
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			AnnouncementLanmu announcementLanmu, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, announcementLanmu.getValue());
		
	}
	
}
