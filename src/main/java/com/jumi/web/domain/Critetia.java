package com.jumi.web.domain;

public class Critetia {
	private int page;
	private String field;
	private String keyword;
	
	public Critetia() {
		this(1);
	}
	
	public Critetia(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Critetia [page=" + page + ", field=" + field + ", keyword=" + keyword + "]";
	}
	
	
}
