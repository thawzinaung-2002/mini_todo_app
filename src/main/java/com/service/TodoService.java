package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.TodoRequestDTO;
import com.dto.TodoResponseDTO;
import com.dto.UserResponseDTO;

public class TodoService {

	Connection con = null;
	
	public int addTodo(TodoRequestDTO req)
	{
		con = DatabaseConnection.getConnection();
		int rs = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO todos (title, target, status, usr_id) VALUES (?,?,?,(select id from users where email=?))");
			ps.setString(1, req.getTitle());
			ps.setString(2, req.getTarget());
			ps.setString(3, req.getStatus());
			ps.setString(4, req.getUser().getEmail());
			rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("add-todo : "+ e.getMessage());
		}
		
		return rs;
		
	}
	
	
	public ArrayList<TodoResponseDTO> getTodos(UserResponseDTO res)
	{
		con = DatabaseConnection.getConnection();
		ArrayList<TodoResponseDTO> dto_arr = new ArrayList<>();
		TodoResponseDTO dto = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from todos where usr_id=(select id from users where email=?)");
			ps.setString(1, res.getEmail());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				dto = new TodoResponseDTO();
				dto.setTitle(rs.getString("title"));
				dto.setTarget(rs.getString("target"));
				dto.setStatus(rs.getString("status"));
				dto.setId(rs.getInt("todo_id"));
				dto_arr.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("get todo : "+ e.getMessage());
		}
		
		return dto_arr;
	}


	public ArrayList<TodoResponseDTO> getTodos(TodoRequestDTO req) {
		
		con = DatabaseConnection.getConnection();
		ArrayList<TodoResponseDTO> dto_arr = new ArrayList<>();
		TodoResponseDTO res = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from todos where usr_id=(select id from users where email=?)");
			ps.setString(1, req.getUser().getEmail());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res = new TodoResponseDTO();
				res.setTitle(rs.getString("title"));
				res.setTarget(rs.getString("target"));
				res.setStatus(rs.getString("status"));
				res.setId(rs.getInt("todo_id"));
				dto_arr.add(res);
			}
			
		} catch (SQLException e) {
			System.out.println("get todo : "+ e.getMessage());
		}
		
		return dto_arr;
		
	}


	public int updateTodo(TodoRequestDTO dto, int index) {
		Connection con = DatabaseConnection.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE todos SET title =?, target=?, status=? WHERE todo_id =?");
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getTarget());
			ps.setString(3, dto.getStatus());
			ps.setInt(4, index);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("update todo : "+ e.getMessage());
		}
		return result;
	}


	public int deleteTodo(int index) {
		
		Connection con = DatabaseConnection.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM todos WHERE todo_id = ?");
			ps.setInt(1, index);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete todo : "+ e.getMessage());
		}
		
		return result;
	}
	
}
