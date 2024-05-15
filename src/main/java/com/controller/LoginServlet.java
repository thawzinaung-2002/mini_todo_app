package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.dto.TodoResponseDTO;
import com.dto.UserRequestDTO;
import com.dto.UserResponseDTO;
import com.model.User;
import com.service.TodoService;
import com.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || password == null || email.length() < 0 || !email.contains("@") || password.length() < 0) {
			request.setAttribute("msg", "Fields shouldn't be empty!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			User usr = new User();
			usr.setEmail(email);

			UserRequestDTO dto = new UserRequestDTO();
			dto.setEmail(usr.getEmail());

			UserService service = new UserService();
			boolean emailCheck = service.checkEmail(dto);
			if (emailCheck) {
				usr.setPassword(password);
				dto.setPassword(usr.getPassword());
				UserResponseDTO res = service.checkUser(dto);
				if (res != null) {
					TodoService todo = new TodoService();
					ArrayList<TodoResponseDTO> todo_res = todo.getTodos(res);
					if(todo_res != null)
					{
						request.getSession().setAttribute("todos", todo_res);	
					}
					request.getSession().setAttribute("user", res);
					request.getRequestDispatcher("home.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "Password is incorrect!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Email isn't registered!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		}

	}

}
