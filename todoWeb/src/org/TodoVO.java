package org;

import java.sql.Date;

public class TodoVO {

	private int writeNum;		// 글 번호
	private String content;		// 글 내용
	private String complete;	// 수행 여부
	private Date insertDate;	// 글 작성 날짜
	
	public TodoVO() {
		System.out.println("VO생성");
	}

	public int getWriteNum() {
		return writeNum;
	}

	public void setWriteNum(int writeNum) {
		this.writeNum = writeNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setJoinDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
}

