package com.leyidai.entity.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leyidai.entity.Announcement.AnnouncementLanmu;

public class AnnouncementLanmuEditor extends PropertyEditorSupport {
	private static Logger log = LoggerFactory.getLogger(AnnouncementLanmuEditor.class);
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		log.debug("category status editor for resource {}", text);
		
		if(StringUtils.isNotEmpty(text)){
			AnnouncementLanmu[] announcementLanmus = AnnouncementLanmu.class.getEnumConstants();
			for(AnnouncementLanmu announcementLanmu: announcementLanmus){
				if(announcementLanmu.getValue() == Integer.valueOf(text)){
					setValue(announcementLanmu);
					break;
				}
			}
		} else{
			setValue(null);
		}
	}
	}
