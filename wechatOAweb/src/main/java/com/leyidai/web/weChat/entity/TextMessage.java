package com.leyidai.web.weChat.entity;

/**
 * 文本消息
 * @author mikan
 * @version 1.0
 * 
 */
public class TextMessage extends CustomerBaseMessage{
	
	/**
	 * 文本消息对象
	 */
	private Text text;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public TextMessage(Text text) {
		super();
		this.text = text;
	}

	public TextMessage() {
		super();
	}
	
}
