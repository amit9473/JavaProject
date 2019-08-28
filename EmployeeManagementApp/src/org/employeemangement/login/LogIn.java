package org.employeemangement.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@SuppressWarnings("serial")
@WebServlet("/LogIn")
public class LogIn extends GenericServlet
{

	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	ResultSet resultSet = null;
	String sql = null;
	Connection connection = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		int count = 0;
		
		String adminId = req.getParameter("un");
		String adminPassword = req.getParameter("pwd");
		String phoneNumber = req.getParameter("un");
		String emailID = req.getParameter("un");
		PrintWriter out = res.getWriter();
		resultSet = CheckQuery.check();
		
		try 
		{
			while(resultSet.next())
			{
				String queryAdminId = resultSet.getString("admin_id");
				String queryPassword = resultSet.getString("admin_password");
				String queryphoneNumber = resultSet.getString("phone_number");
				String queryemailID = resultSet.getString("email_id");
				
				if(adminId.equals(queryAdminId) || phoneNumber.equals(queryphoneNumber) || emailID.equals(queryemailID))
				{
					count++;
					if(queryPassword.equals(adminPassword))
					{
						out.println("Login Successful");
					}
					else
						out.println("Invalid Password");
				}
			}
			if(count == 0)
				out.println("Invalid Id/phone_number/email_id");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
	}

}
