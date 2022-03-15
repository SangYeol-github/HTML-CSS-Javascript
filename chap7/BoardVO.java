package com.tjoeun.vo;

import java.util.Objects;

public class BoardVO {
	
	private int num;
	private String author;
	private String title;
	private String contents;
	private java.sql.Date date;
	
	public BoardVO() {}
	
	public BoardVO(int num, String title) {
		this.num = num;
		this.title = title;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(this.num);
	}
	@Override
	public boolean equals(Object obj) {
		BoardVO vo = (BoardVO)obj;
		if(this.num==vo.getNum()) {
			return true;
		}
		return false;
	}
	
	  @Override public String toString() {
	  
		  return String.format("%d|%s|%s|%s|%s", num, author, title, contents,date.toString()); 
	  }

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
	
	
}
