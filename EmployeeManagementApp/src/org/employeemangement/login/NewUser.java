package org.employeemangement.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/NewUser")
public class NewUser extends HttpServlet
{

	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	Connection connection = null;
	String sql = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		int count = 0;
		String adminId = req.getParameter("un");
		String password = req.getParameter("pwd");
		String confirmPassword = req.getParameter("cpwd");
		String phoneNumber = req.getParameter("pn");
		String emailID = req.getParameter("en");
	
		PrintWriter out = res.getWriter();
		
		resultSet = checkAdmin(phoneNumber, emailID);
		try 
		{
			if (resultSet.next()) 
			{
				resultSet = CheckQuery.check();
				while (resultSet.next())
				{
					String queryAdminId = resultSet.getString("admin_id");
					String queryphoneNumber = resultSet.getString("phone_number");
					String queryemailID = resultSet.getString("email_id");

					if (adminId.equals(queryAdminId) || phoneNumber.equals(queryphoneNumber) || emailID.equals(queryemailID))
					{
						out.println("Admin detail already exist");
						count++;
						break;
					}
				}

				if (count == 0)
				{
					if (password.equals(confirmPassword))
					{
						insertRegister(phoneNumber, emailID, adminId, password);
						out.println("Registration Successful");
					} 
					else
						out.println("Password Mismatch");
				}
			}
			else
			{
				out.println("user doesnt exist with provided details");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	public ResultSet checkAdmin(String phone, String email)
	{
		String sql = "select phone_number, email_id from emp_mng_sys.employee_list where phone_number=? and email_id=?";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, email);
			resultSet = preparedStatement.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void insertRegister(String phoneNumber, String emailId, String admin_id, String admin_password)
	{
		String insertSql = "insert into emp_mng_sys.admin_list values( "+ 
						   "(select emp_id from emp_mng_sys.employee_list "+ 
						   "where phone_number=? and email_id=?),?,?);";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(url);
			
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setString(1, phoneNumber);
			preparedStatement.setString(2, emailId);
			preparedStatement.setString(3, admin_id);
			preparedStatement.setString(4, admin_password);
			
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (Exception e)
		{
			System.err.println("Exception caught during registration");
			e.printStackTrace();
		}
		
	}
	
//	public void updateRegister(String userName, String password, String phoneNumber, String emailId)
//	{
//		String updateSql = "update emp_mng_sys.admin_list "+
//						   "right join emp_mng_sys.employee_list "+
//						   "on admin_emp_id=emp_id "+
//						   "set admin_id=?, admin_password=? "+
//						   "where phone_number=? and email_id=?";
//	
//		try 
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			connection = DriverManager.getConnection(url);
//			
//			preparedStatement = connection.prepareStatement(updateSql);
//			
//			preparedStatement.setString(1, userName);
//			preparedStatement.setString(2, password);
//			preparedStatement.setString(3, phoneNumber);
//			preparedStatement.setString(4, emailId);
//			
//			preparedStatement.executeUpdate();
//			
//			connection.close();
//		}
//		catch (Exception e)
//		{
//			System.err.println("Exception caught during registration");
//			e.printStackTrace();
//		}
//	}
}
