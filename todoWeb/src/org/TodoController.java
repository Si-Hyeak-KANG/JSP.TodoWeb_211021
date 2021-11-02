package org;

import java.io.IOException;
import java.io.PrintWriter;
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
				List<TodoVO> lists = todoService.searchTodoLists();	// ���̺� ����� �����͵��� List�� �ְ� ������.
				request.setAttribute("lists", lists);	// ������ List�� "lists" �̸����� JSP�� ����
				nextPage="/jsp/home.jsp";
				
			}else if(action.equals("/home.do")) {		// ù ������ URL ���ӽ�
				List<TodoVO> lists = todoService.searchTodoLists();	// ���̺� ����� �����͵��� List�� �ְ� ������.
				request.setAttribute("lists", lists);	// ������ List�� "lists" �̸����� JSP�� ����
				nextPage="/jsp/home.jsp";
			}else if(action.equals("/newContent.do")) { // �� �� �ۼ� ��û
				String text = request.getParameter("inputText");
				int result = todoService.newContent(text);
				PrintWriter pw = response.getWriter();
				
				if(result==1) {
					pw.println("<script>"
							+ " alert('�� ���� �߰��߽��ϴ�.');" 
							+ " location.href='" + request.getContextPath()
							+ "/todo/home.do';"
							+ " </script>");
				} else if(result==-1) {
					pw.println("<script>"
							+ " alert('�� �̻� ���� �߰��� �� �����ϴ�.');"
							+ " location.href='" + request.getContextPath()
							+ "/todo/home.do';"
							+ " </script>");
				}

				return;
				
			}else if(action.equals("/chkComplete.do")){ // checkbox ���ý� ���� ������Ʈ
				String chkComplete = request.getParameter("complete");
				int writeNum = Integer.parseInt(request.getParameter("writeNum"));
				todoService.checkComplete(chkComplete,writeNum);
				nextPage="/todo/home.do";
			}else if(action.equals("/delOne.do")) {	// ���� �� ����
				int writeNum = Integer.parseInt(request.getParameter("writeNum"));
					todoService.delOne(writeNum);
					System.out.println(writeNum+"�� �� ���� �Ϸ�");
					nextPage="/todo/home.do";
			}else if(action.equals("/delAll.do")) {	// �� ��ü ����
				todoService.delAll();
				System.out.println("�� ��ü ���� �Ϸ�");
				nextPage="/todo/home.do";
			}else {
				nextPage="/todo/home.do";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
