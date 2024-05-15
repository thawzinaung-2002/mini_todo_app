package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.UserRequestDTO;
import com.dto.UserResponseDTO;

public class UserService {
	
	Connection con = null;
	
	public int insertUser(UserRequestDTO req)
	{
		con = DatabaseConnection.getConnection();
		int rs = 0;
		try {
			PreparedStatement ps = con.prepareStatement("insert into users(name, email, password) values(?,?,?)");
			ps.setString(1, req.getName());
			ps.setString(2, req.getEmail());
			ps.setString(3, req.getPassword());
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Insert error : "+ e.getMessage());
		}
		return rs;
	}
	
	public boolean checkEmail(UserRequestDTO req)
	{
		con = DatabaseConnection.getConnection();
		boolean flag = false;
		try {
			PreparedStatement ps = con.prepareStatement("select email from users where email=?");
			ps.setString(1, req.getEmail());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println("Check email : "+ e.getMessage());
		}
		return flag;
	}
	
	
	public UserResponseDTO checkUser(UserRequestDTO req)
	{
		UserResponseDTO res = null;
		con = DatabaseConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, req.getEmail());
			ps.setString(2, req.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res = new UserResponseDTO();
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("checkUser : "+ e.getMessage());
		}
		return res;
		
	}
	

}
