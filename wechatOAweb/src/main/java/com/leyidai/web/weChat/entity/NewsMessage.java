package com.leyidai.web.weChat.entity;

/**
 * 发送图文消息
 * @author mikan
 * @version 1.0
 * 
 */
public class NewsMessage extends CustomerBaseMessage{
	
	/**
	 * 图文消息对象
	 */
	private News news;

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public NewsMessage(News news) {
		super();
		this.news = news;
	}

	public NewsMessage() {
		super();
	}
	
}
