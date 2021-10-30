package org;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TodoDAO {

	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	
	public TodoDAO() {
		
		try {
			System.out.println("DAO 생성");
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TodoVO> selectAllLists() {
		
		List<TodoVO> lists = new ArrayList<TodoVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM todo_table ORDER BY writeNum DESC";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				int writeNum = rs.getInt("writeNum");
				String content = rs.getString("content");
				String complete = rs.getString("complete");
				Date insertDate = rs.getDate("insertDate");
				
				TodoVO todoVO = new TodoVO();
				todoVO.setWriteNum(writeNum);
				todoVO.setContent(content);
				todoVO.setComplete(complete);
				todoVO.setInsertDate(insertDate);
				
				lists.add(todoVO);
				System.out.println(writeNum + "번 데이터 add");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}
}
