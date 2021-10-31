package org;

import java.util.List;

public class TodoService {

	TodoDAO todoDAO;
	
	public TodoService() {
		
		System.out.println("���� on");
		todoDAO = new TodoDAO();
	}

	public List<TodoVO> searchTodoLists() {
		
		
		return todoDAO.selectAllLists();

	}

	public int newContent(String text) {
		
		int result = todoDAO.insertNewList(text);
		return result;
		
	}
	
}
