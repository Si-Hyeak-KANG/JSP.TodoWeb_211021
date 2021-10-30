package org;

import java.util.List;

public class TodoService {

	TodoDAO todoDAO;
	
	public TodoService() {
		
		System.out.println("¼­ºñ½º on");
		todoDAO = new TodoDAO();
	}

	public List<TodoVO> searchTodoLists() {
		
		
		return todoDAO.selectAllLists();

	}
	
}
