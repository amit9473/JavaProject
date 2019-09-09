package org.employeemangement.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/LogIn")
public class LogIn extends HttpServlet
{

	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	ResultSet resultSet = null;
	String sql = null;
	Connection connection = null;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
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
				String fName = resultSet.getString("first_name");
				String mName = resultSet.getString("middle_name");
				String lName = resultSet.getString("last_name");
				String queryAdminId = resultSet.getString("username");
				String queryPassword = resultSet.getString("password");
				String queryphoneNumber = resultSet.getString("phone_number");
				String queryemailID = resultSet.getString("email_id");
				
				if(adminId.equals(queryAdminId) || phoneNumber.equals(queryphoneNumber) || emailID.equals(queryemailID))
				{
					count++;
					
					if(queryPassword.equals(adminPassword))
					{
						String name = null;
						if(strCheck(mName))
							name = fName+" "+lName;
						else
							name = fName+" "+mName+" "+lName;
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
//		finally
//		{
//			try 
//			{
//				connection.close();
//			} 
//			catch (SQLException e) 
//			{
//				e.printStackTrace();
//			}
//		}
		
	}
	
	private static boolean strCheck(String s)
	{
		if(s == null || s.isEmpty())
			return true;
		return false;
	}

}
