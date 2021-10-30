package org;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class todoController
 */
@WebServlet("/todo/*")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TodoService todoService;
	TodoVO todoVO;
	
	public void init(ServletConfig config) throws ServletException {
		todoService = new TodoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String nextPage="";
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		try {
			if(action == null) {
				List<TodoVO> lists = todoService.searchTodoLists();	// 테이블에 저장된 데이터들을 List에 넣고 가져옴.
				request.setAttribute("lists", lists);	// 가져온 List를 "lists" 이름의 session에 보관
				nextPage="/jsp/home.jsp";
				
			}else if(action.equals("/home.do")) {		// 첫 페이지 URL 접속시
				List<TodoVO> lists = todoService.searchTodoLists();	// 테이블에 저장된 데이터들을 List에 넣고 가져옴.
				request.setAttribute("lists", lists);	// 가져온 List를 "lists" 이름의 session에 보관
				nextPage="/jsp/home.jsp";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
