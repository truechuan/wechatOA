package com.leyidai.entity;
public class Announcement {
	
	/**
	 * 栏目
	 * @author song
	 *
	 */
	public enum AnnouncementLanmu{
		THE_ANNOUNCEMENT(1, "通知公告");
		//MEDIA(2, "媒体报道"),
		//NEWS(3, "新闻动态");
		private int value;
		private String description;
		AnnouncementLanmu(int value, String description){
			this.value = value;
			this.description = description;
		}
		public int getValue(){
			return this.value;
		}
		public String getDescription(){
			return this.description;
		}
	}
	
	/**
	 * 公告状态
	 *
	 */
	public enum AnnouncementStatus{
		INVAID(0,"无效"),
		VALID(1,"有效");
		
		public void setValue(int value) {
			this.value = value;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		private int value;
		private String description;
		AnnouncementStatus(int value, String description){
			this.value = value;
			this.description = description;
		}
		public int getValue(){
			return this.value;
		}
		public String getDescription(){
			return this.description;
		}
	}
	private int announcementId;
	private String title;
	private String summary;
	private String keyWord;
	private String description;
	private String ralateWord;
	private String imageUrl;
	private AnnouncementLanmu lanmu;
	private AnnouncementStatus status;
	private String detailInfo;
	private String createTime;
	private String updateTime;
	private int area;
	
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRalateWord() {
		return ralateWord;
	}
	public void setRalateWord(String ralateWord) {
		this.ralateWord = ralateWord;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public AnnouncementLanmu getLanmu() {
		return lanmu;
	}
	public void setLanmu(AnnouncementLanmu lanmu) {
		this.lanmu = lanmu;
	}
	
	public String getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public AnnouncementStatus getStatus() {
		return status;
	}
	public void setStatus(AnnouncementStatus status) {
		this.status = status;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	
	
}
