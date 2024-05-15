package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dto.UserRequestDTO;
import com.model.User;
import com.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		boolean t1 = true, 
				t2 = true,
				t3 = true;
		
		if(name == null || name.length() <= 6)
		{
			t1 = false;
			request.setAttribute("nameError", "Name should be six or greater than!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		else if(email == null || !email.contains("@"))
		{
			t2 = false;
			request.setAttribute("emailError", "Email should contain '@' sign!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		else if(password == null || password.length() < 6)
		{
			t3 = false;
			request.setAttribute("passwordError", "Password length should be greater than 6!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
		
		if(t1 && t2 && t3)
		{
			User u1 = new User();
			u1.setName(name);
			u1.setEmail(email);
			u1.setPassword(password);
			
			UserRequestDTO req = new UserRequestDTO();
			req.setName(u1.getName());
			req.setEmail(u1.getEmail());
			req.setPassword(u1.getPassword());
			
			UserService ser = new UserService();
			boolean emailCheck = ser.checkEmail(req);
			if(emailCheck)
			{
				request.setAttribute("msg", "Email is already registered!Try another or log in!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else
			{
				int rs = ser.insertUser(req);
				if(rs > 0)
				{
					request.setAttribute("msg", "Registration succeed!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			
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
