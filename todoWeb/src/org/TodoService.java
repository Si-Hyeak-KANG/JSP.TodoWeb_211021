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

	public int newContent(String text) {
		
		int result = todoDAO.insertNewList(text);
		return result;
		
	}

	public void delOne(int writeNum) {
		
		todoDAO.deleteOneList(writeNum);
		
	}
	
	public void delAll() {
		todoDAO.deleteAllList();
	}

	public void checkComplete(String chkComplete,int writeNum) {
		todoDAO.updateComplete(chkComplete,writeNum);
		
	}
	
}
