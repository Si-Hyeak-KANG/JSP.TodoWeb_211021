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
			System.out.println("DAO ����");
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ��� �� �˻� �� List�� add
	public List<TodoVO> selectAllLists() {
		
		int num =0;
		List<TodoVO> lists = new ArrayList<TodoVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM todo_table ORDER BY insertDate DESC";
			System.out.println("SQL query : " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				String content = rs.getString("content");
				String complete = rs.getString("complete");
				Date insertDate = rs.getDate("insertDate");
				
				TodoVO todoVO = new TodoVO();
				todoVO.setContent(content);
				todoVO.setComplete(complete);
				todoVO.setInsertDate(insertDate);
				
				lists.add(todoVO);
				num++;
			}
			System.out.println( num + "�� ������ ȣ��");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	// ���̺� �� ������ ����
	public int selectListsCount() {
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
			System.out.println("db�ȿ� "+ count + "�� �����Ͱ� �ֽ��ϴ�.");
		}
		
		return count;
	}
	
	// �� �� �ۼ�
	public int insertNewList(String text) {
		int result=-1;
		int count = selectListsCount();
		
		if(count < 11) {
			try {
				conn = dataFactory.getConnection();
				String query = "INSERT INTO todo_table (content)"
						+ " VALUES (?)";
				System.out.println("SQL query : " + query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, text);
				pstmt.executeUpdate();
				result =1;
				
				System.out.println("���������� �� ���� DB�� �־����ϴ�.");
				pstmt.close();
				conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("error: ������ 10�� �ʰ�(-1)");
			result=-1;
		}

		return result;
	}
}
