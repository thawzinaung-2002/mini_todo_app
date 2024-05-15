package com.controller;

import java.io.IOException;

import com.dto.TodoRequestDTO;
import com.dto.UserResponseDTO;
import com.model.TodoBean;
import com.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateTodoServlet
 */
@WebServlet("/updateTodo")
public class UpdateTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateTodoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title=request.getParameter("title");
		String target=request.getParameter("target");
		String status=request.getParameter("status");
		int index = Integer.valueOf(request.getParameter("id"));
		
		boolean titleTest = title != null && title.length() > 6;
		boolean targetTest = target != null;
		boolean statusTest = status != null;
		if(titleTest && targetTest && statusTest)
		{
			TodoBean td = new TodoBean();
			td.setTitle(title);
			td.setTarget(target);
			td.setStatus(status);
			
			TodoRequestDTO dto = new TodoRequestDTO();
			dto.setTitle(td.getTitle());
			dto.setTarget(td.getTarget());
			dto.setStatus(td.getStatus());
			dto.setUser((UserResponseDTO) request.getSession().getAttribute("user"));
			
			TodoService service = new TodoService();
			int result = service.updateTodo(dto, index);
			if(result > 0)
			{
				request.getSession().setAttribute("todos" ,service.getTodos(dto));
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			
		}
		else
		{
			request.setAttribute("msg", "Fail to add!! Input right data and try again!");
			request.getRequestDispatcher("add-todo.jsp").forward(request, response);
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
