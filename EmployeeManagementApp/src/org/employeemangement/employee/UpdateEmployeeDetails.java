package org.employeemangement.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/UpdateEmployeeDetails")
public class UpdateEmployeeDetails extends GenericServlet
{
	
	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		
	}
	
	public void updateDetails()
	{
		
	}
	
}
