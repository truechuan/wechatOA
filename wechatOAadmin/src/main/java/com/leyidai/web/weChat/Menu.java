package com.leyidai.web.weChat;

/**
 * 菜单
 * @author mikan
 * @version 1.0
 * 
 */
public class Menu {
	
	/**
	 * 菜单按钮
	 */
	private Button[] button;

	public Button[] getButton() {
		return button;
	}


	public void setButton(Button[] button) {
		this.button = button;
	}


	public Menu(Button[] button) {
		super();
		this.button = button;
	}


	public Menu() {
		super();
	}  
}
