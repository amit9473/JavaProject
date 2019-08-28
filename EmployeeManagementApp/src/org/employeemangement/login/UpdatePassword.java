package org.employeemangement.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/UpdatePassword")
public class UpdatePassword extends GenericServlet
{
	
	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	Connection connection = null;
	String sql = null;
	ResultSet resultSet = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		int count = 0;
		String userName = req.getParameter("un");
		String password = req.getParameter("pwd");
		String confirmPassword = req.getParameter("cpwd");
		String phoneNumber = req.getParameter("mn");
		String emailId = req.getParameter("un");
	
		PrintWriter out = res.getWriter();
		
		resultSet = CheckQuery.check();
		try 
		{
			while(resultSet.next())
			{
				String queryAdminId = resultSet.getString("admin_id");
				String queryphoneNumber = resultSet.getString("phone_number");
				String queryemailID = resultSet.getString("email_id");
				
				if((userName.equals(queryAdminId) || emailId.equals(queryemailID)) && phoneNumber.equals(queryphoneNumber))
				{
					count++;
				}
				
			}
			if (count==0)
				out.println("Not Registered with this credentials");
			else
			{
				if(password.equals(confirmPassword))
				{
					forgotPassword(confirmPassword, userName, phoneNumber, emailId);
					out.println("Password Updated");
				}
				else
					out.println("Password Mismatch");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void forgotPassword(String password, String userName, String phoneNumber, String emailId)
	{
		sql = "update emp_mng_sys.admin_list "+
			  "join emp_mng_sys.employee_list "+ 
			  "on admin_list.admin_emp_id = employee_list.emp_id "+
			  "set admin_Password = ? "+
			  "where ((admin_id = ? or email_Id = ?) and phone_Number = ?)";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, emailId);
			preparedStatement.setString(4, phoneNumber);
			
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (Exception e) 
		{
			System.err.println("Exception caught during password reset");
			e.printStackTrace();
		}
		
	}
}
