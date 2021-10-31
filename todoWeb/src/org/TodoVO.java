package org;

import java.sql.Date;

public class TodoVO {

	private String content;		// 글 내용
	private String complete;	// 수행 여부
	private Date insertDate;	// 글 작성 날짜
	
	public TodoVO() {
		
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

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
}

