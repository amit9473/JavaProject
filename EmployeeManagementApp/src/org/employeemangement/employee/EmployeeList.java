package org.employeemangement.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/EmployeeList")
public class EmployeeList extends GenericServlet
{

	static String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	static Connection connection = null;
	static String sql = null;
	static ResultSet resultSet = null;
	static Statement statement = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		PrintWriter out = res.getWriter();
				
		resultSet = list();
		
		try 
		{
			out.println("----------------------------------------------------------------------------------------------------------------------");
		    out.printf("%-4s %-30s %-8s %-25s %-15s %-15s %-30s", "Id", "Name", "Sex", "Department", "Manager", "Phone Number", "Email Id");
		    out.println();
		    out.println("----------------------------------------------------------------------------------------------------------------------");
			while(resultSet.next())
			{
				int emp_id = Integer.parseInt(resultSet.getString("emp_id"));
				String first_name = resultSet.getString("first_name");
				String middle_name = resultSet.getString("middle_name");
				String last_name = resultSet.getString("last_name");
				String sex = resultSet.getString("sex");
				String department = resultSet.getString("department");
				String manager = resultSet.getString("manager");
				String phone_number = resultSet.getString("phone_number");
				String email_id = resultSet.getString("email_id");
				if(middle_name.equals("na"))
				{
					out.format("%-4d %-30s %-8s %-25s %-15s %-15s %-30s", emp_id, first_name+" "+last_name, sex, department, manager, phone_number, email_id);
					out.println();
				}
				else
				{
					out.format("%-4d %-30s %-8s %-25s %-15s %-15s %-30s", emp_id, first_name+" "+middle_name+" "+last_name, sex, department, manager, phone_number, email_id);
					out.println();
				}
					
			}
			out.println("----------------------------------------------------------------------------------------------------------------------");
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
	
	public static ResultSet list()
	{
		String sql = "SELECT emp.emp_id, emp.first_name, COALESCE(emp.middle_name,'na') AS middle_name, emp.last_name, sex.sex, department.dept_name AS department, COALESCE(mgr.first_name, 'no manager') AS manager, emp.phone_number, emp.email_id \r\n" + 
					 "FROM emp_mng_sys.employee_list AS emp \r\n" + 
					 "JOIN emp_mng_sys.emp_sex AS sex \r\n" + 
					 "ON sex.sex_id = emp.sex_id \r\n" + 
					 "LEFT JOIN emp_mng_sys.employee_list mgr \r\n" + 
					 "ON emp.mgr_id = mgr.emp_id \r\n" + 
					 "JOIN emp_mng_sys.dept_list AS department \r\n" + 
					 "ON department.dept_id = emp.dept_id \r\n" + 
					 "ORDER BY emp.emp_id";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultSet;
	}
	
}
