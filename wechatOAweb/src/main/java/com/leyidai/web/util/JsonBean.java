package com.leyidai.web.util;

public class JsonBean {
	public String return_code;
	public String return_message;
	public Data data;

	public static class Data {
		public String url;
		public String expired;
		public String key;
	}
}