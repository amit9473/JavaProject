package org.employeemangement.employee;

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
@WebServlet("/InsertEmployeeDetails")
public class InsertEmployeeDetails extends GenericServlet 
{

	String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		
		int count = 0;
		String firstName = req.getParameter("fn");
		String middleName = req.getParameter("mn");
		String lastName = req.getParameter("ln");
		int sexId = Integer.parseInt(req.getParameter("Sex"));
		int deptId = Integer.parseInt(req.getParameter("Department"));
		int mgrId = Integer.parseInt(req.getParameter("mi"));
		String phoneNumber = req.getParameter("pn");
		String emailId = req.getParameter("ei");
		
		resultSet = CheckInfo.check();
		PrintWriter out = res.getWriter();
		
		try 
		{
			while(resultSet.next())
			{
				String phone = resultSet.getString("phone_number");
				String email = resultSet.getString("email_id");
				if(phone.equals(phoneNumber) || email.equals(emailId))
					count++;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(count == 0)
		{
			insertDetails(firstName, middleName, lastName, sexId, deptId, mgrId, phoneNumber, emailId);
			Employee e = new Employee(firstName, middleName, lastName, sexId, deptId, mgrId, phoneNumber, emailId);
			out.println("Added details of "+e);
		}
	}
	
	public void insertDetails(String firstName, String middleName, String lastName, int sexId, int deptId, int mgrId, String phoneNumber, String emailId)
	{
		
		String sql = "insert into emp_mng_sys.employee_list(first_name, middle_name, last_name, sex_id, dept_id, mgr_id, phone_number, email_id) "+ 
				     "values(?, ?, ?, ?, ?, ?, ?, ?)";
	
		try 
		{
			connection = DriverManager.getConnection(url);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, middleName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setInt(4, sexId);
			preparedStatement.setInt(5, deptId);
			preparedStatement.setInt(6, mgrId);
			preparedStatement.setString(7, phoneNumber);
			preparedStatement.setString(8, emailId);
			
			preparedStatement.executeUpdate();
			
			connection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
