package com.leyidai.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PageUtil {

	@Value("#{systemConfigProperties[pageRecord]}")
	private int pageRecord;// 每页面的记录数
	
	private int totalRecord;// 总记录数
	private int currentPage = 1;// 当前页

	private int pageNumStart;// 页码显示开始;
	private int pageNumEnd;// 页码显示结束;
	
	@Value("#{systemConfigProperties[showPageCount]}")
	private int showPageCount;// 显示页码个数，默认是10
	
	private String pageUrl;//页面路径

	public PageUtil(){
		
	}
	
	public PageUtil(int currentPage, int totalRecord) {
		this.totalRecord = totalRecord;
		this.currentPage = currentPage;
	}
	
	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}
	
	public int getPageRecord() {
		return pageRecord;
	}
	
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		return this.totalRecord % this.pageRecord == 0 ? this.totalRecord
				/ this.pageRecord : this.totalRecord / this.pageRecord + 1;
	}

	/**
	 * 获取当前页
	 * @return
	 */
	public int getCurrentPage() {
		
		if (currentPage > getTotalPage()) {
			
			this.currentPage = getTotalPage();
		} else {
			if (currentPage < 1) {
				this.currentPage = 1;
			}
		}
		
		return currentPage;
	}

	/**
	 * next page
	 * @return
	 */
	public int getNextPage() {
		return this.getCurrentPage() + 1;
	}

	/**
	 * previous page
	 * @return
	 */
	public int getPrePage() {
		return this.getCurrentPage() - 1;
	}

	/**
	 * 页码显示起始数字
	 * @return
	 */
	public int getPageNumStart() {
		// 显示页数的一半
		int halfPage = (int) Math.ceil((double) showPageCount / 2);
		if (halfPage >= currentPage) {
			this.pageNumStart = 1;
		} else {
			if (currentPage + halfPage > getTotalPage()) {
				this.pageNumStart = (getTotalPage() - showPageCount + 1) <= 0 ? 1
						: (getTotalPage() - showPageCount + 1);
			} else {
				this.pageNumStart = currentPage - halfPage + 1;
			}
		}
		return pageNumStart;
	}

	/**
	 * 页码显示结束数字
	 * @return
	 */
	public int getPageNumEnd() {
		// 显示页数的一半
		int halfPage = (int) Math.ceil((double) showPageCount / 2);
		if (halfPage >= currentPage) {
			this.pageNumEnd = showPageCount > getTotalPage() ? getTotalPage() : showPageCount;
		} else {
			if (currentPage + halfPage >= getTotalPage()) {
				
				this.pageNumEnd = getTotalPage();
			} else {
				
				this.pageNumEnd = currentPage + halfPage;
			}
		}
		return pageNumEnd;
	}

	public int getShowPageNum() {
		
		return showPageCount;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * 页码导航
	 * @return
	 */
	public String getPageNavigation() {
		
		StringBuilder pageNav = new StringBuilder();
		for(int i=getPageNumStart(); i<= (getShowPageNum() + getPageNumStart()); i++){
			
			pageNav.append("<a href=\"").append(pageUrl).append("\">").append(i).append("</a>");
		}
		
		return pageNav.toString();
	}
}
