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
	
	// 모든 글 검색 후 List에 add
	public List<TodoVO> selectAllLists() {
		
		int num =0;
		List<TodoVO> lists = new ArrayList<TodoVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM todo_table ORDER BY writeNum";
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
				num++;
			}
			System.out.println( num + "개 데이터 호출");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	// 테이블 안 데이터 갯수
	private int selectListsCount() {
		int count=1;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM todo_table";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				count+=1;
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(count <11) {
			System.out.println("db안에 "+ count + "개 데이터가 있습니다.");
		}
		
		return count;
	}
	
	// 새 글 번호
	private int getMaxWriteNum() {
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT max(writeNum) FROM todo_table";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {	
				System.out.println("새 글 번호를 가져왔습니다.");
				return (rs.getInt(1)+1);
				
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 새 글 작성
	public int insertNewList(String text) {
		int result=-1;
		int count = selectListsCount();
		int writeNum = getMaxWriteNum();
		
		if(count < 11) {
			try {
				conn = dataFactory.getConnection();
				String query = "INSERT INTO todo_table (writeNum,content)"
						+ " VALUES (?,?)";
				System.out.println("SQL query : " + query);
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, writeNum);
				pstmt.setString(2, text);
				pstmt.executeUpdate();
				result =1;
				System.out.println("성공적으로 새 글을 DB에 넣었습니다.");
				pstmt.close();
				conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("error: 데이터 10개 초과(-1)");
			result=-1;
		}

		return result;
	}

	public void deleteOneList(int writeNum) {

		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM todo_table WHERE writeNum=?";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writeNum);
			pstmt.executeUpdate();
			System.out.println(writeNum + "번의 글을 데이터에서 삭제합니다.");
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAllList() {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM todo_table";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("데이터 전체 삭제");
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateComplete(String chkComplete,int writeNum) {
		try {
			conn = dataFactory.getConnection();
			String query = "Update todo_table SET complete=? WHERE writeNum=?";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chkComplete);
			pstmt.setInt(2, writeNum);
			pstmt.executeUpdate();
			System.out.println(writeNum + "번 글의 complete 수정 :" + chkComplete);
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
