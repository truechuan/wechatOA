package com.leyidai.entity.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leyidai.entity.Announcement.AnnouncementStatus;

public class AnnouncementStatusEditor extends PropertyEditorSupport {
	private static Logger log = LoggerFactory.getLogger(AnnouncementStatusEditor.class);
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		log.debug("announcement status editor for resource {}", text);
		
		if(StringUtils.isNotEmpty(text)){
			AnnouncementStatus[] announcementStatuses = AnnouncementStatus.class.getEnumConstants();
			for(AnnouncementStatus announcementStatus: announcementStatuses){
				if(announcementStatus.getValue() == Integer.valueOf(text)){
					setValue(announcementStatus);
					break;
				}
			}
		} else{
			setValue(null);
		}
	}
	}
