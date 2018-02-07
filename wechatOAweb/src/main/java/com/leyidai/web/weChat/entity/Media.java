package com.leyidai.web.weChat.entity;

/**
 * 多媒体类
 * @author mikan
 * @version 1.0
 * 
 */
public class Media {
	/**
	 * 媒体ID
	 */
	private String mediaId;
	
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public Media(String mediaId) {
		super();
		this.mediaId = mediaId;
	}
	
}
