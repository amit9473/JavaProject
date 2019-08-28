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
@WebServlet("/NewUser")
public class NewUser extends GenericServlet
{

	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	Connection connection = null;
	String sql = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		
		int count = 0;
		String adminId = req.getParameter("un");
		String password = req.getParameter("pwd");
		String confirmPassword = req.getParameter("cpwd");
		String phoneNumber = req.getParameter("pn");
		String emailID = req.getParameter("en");
	
		PrintWriter out = res.getWriter();
		
		resultSet = CheckQuery.check();
		try 
		{
			while(resultSet.next())
			{
				String queryAdminId = resultSet.getString("admin_id");
				String queryphoneNumber = resultSet.getString("phone_number");
				String queryemailID = resultSet.getString("email_id");
				
				if(adminId.equals(queryAdminId) || phoneNumber.equals(queryphoneNumber) || emailID.equals(queryemailID))
				{
					out.println("Admin detail already exist");
					count++;
				}
			}
			
			if(count==0)
			{
				if(password.equals(confirmPassword))
				{
					insertRegister(phoneNumber, emailID);
					updateRegister(adminId, password, phoneNumber, emailID);
					out.println("Registration Successful");
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
	
	public void insertRegister(String phoneNumber, String emailId)
	{
		String insertSql = "insert into emp_mng_sys.admin_list (admin_emp_id) "+
						   "select emp_id from emp_mng_sys.employee_list "+
						   "where phone_number=? and email_id=?";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url);
			
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setString(1, phoneNumber);
			preparedStatement.setString(2, emailId);
			
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (Exception e)
		{
			System.err.println("Exception caught during registration");
			e.printStackTrace();
		}
		
	}
	
	public void updateRegister(String userName, String password, String phoneNumber, String emailId)
	{
		String updateSql = "update emp_mng_sys.admin_list right "+
						   "join emp_mng_sys.employee_list "+
						   "on admin_emp_id=emp_id set admin_id=?, admin_password=? "+
						   "where phone_number=? and email_id=?";
	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url);
			
			preparedStatement = connection.prepareStatement(updateSql);
			
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setString(4, emailId);
			
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (Exception e)
		{
			System.err.println("Exception caught during registration");
			e.printStackTrace();
		}
	}
}
