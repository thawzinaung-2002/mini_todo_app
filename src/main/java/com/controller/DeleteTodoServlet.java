package com.controller;

import java.io.IOException;

import com.dto.TodoRequestDTO;
import com.dto.UserResponseDTO;
import com.model.TodoBean;
import com.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteTodoServlet
 */
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteTodoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int index=Integer.valueOf(request.getParameter("id"));
		
		TodoService service = new TodoService();
		int result = service.deleteTodo(index);
		if(result > 0)
		{	
			UserResponseDTO res = (UserResponseDTO) request.getSession().getAttribute("user");
			request.getSession().setAttribute("todos", service.getTodos(res));
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
