package org.jsp.employeedatamanagement.databaseconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.jsp.employeedatamanagement.employee.Employee;
import org.jsp.employeedatamanagement.employee.EmployeeDetails;
import org.jsp.employeedatamanagement.validation.ValidateData;

public class DatabaseConnectivityPrepared
{
	
	static Scanner scanner = new Scanner(System.in);
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static boolean status = false;
	static String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	
	
	public static Connection connect() throws SQLException
	{
		connection = DriverManager.getConnection(url);
		return connection;
	}
	
	
	/*
	private static int inputId()
	{
		int id = 0;
		int count = 0;
		do 
		{
			try 
			{
				System.out.println("Enter the Id :");
				id = scanner.nextInt();
				status = ValidateData.validateId(id);
				if(status)
				{
					try 
					{
						connection = connect();
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from test.employee");
						while(resultSet.next())
						{
							count++;
						}
						System.out.println(count+" is the total number of employee in the company");
					} 
					catch (SQLException e)
					{
						System.err.println("Exception caught");
					}
				}
			}
			catch (InputMismatchException e) 
			{
				System.out.println("integer inputs are only allowed");
			}
		} while (!status);
		return id;
	}
	*/
	
	
	private static String inputName()
	{
		String name = null;
		do 
		{
			try 
			{
				System.out.println("Enter the Name :");
				name = scanner.next();
				status = ValidateData.validateName(name);
			}
			catch (InputMismatchException e) 
			{
				System.out.println("integer inputs are only allowed");
			}
		} while (!status);
		return name;
	}
	
	
	private static int inputAge()
	{
		int age = 0;
		do 
		{
			try 
			{
				System.out.println("Enter the Age :");
				age = scanner.nextInt();
				status = ValidateData.validateAge(age);
			}
			catch (InputMismatchException e) 
			{
				System.out.println("integer inputs are only allowed");
			}
		} while (!status);
		return age;
	}
	
	
	private static double inputSalary()
	{
		double salary = 0.0;
		do 
		{
			try 
			{
				System.out.println("Enter the Salary :");
				salary = scanner.nextDouble();
				status = true;
			}
			catch (InputMismatchException e) 
			{
				System.out.println("integer decimal inputs are only allowed");
			}
		} while (!status);
		return salary;
	}
	
	
	public static void executeInsert()
	{
		String sql = "insert into test.employee(empname, empdept, empage, empsalary) values (?,?,?,?)";
		try 
		{
			connection = connect();
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setInt(1, inputId());
			preparedStatement.setString(1, inputName());
			preparedStatement.setString(2, EmployeeDetails.setDepartment());
			preparedStatement.setInt(3, inputAge());
			preparedStatement.setDouble(4, inputSalary());
			preparedStatement.executeUpdate();
			connection.close();
		} 
		catch (SQLException e)
		{
			System.out.println("SQL Exception Caught");
		}
	}
	
	
	public static void executeUpdate()
	{
		
		String sql = null;
		int input = 0;
		System.out.println("Press 1: to update name");
		System.out.println("Press 2: to update department");
		System.out.println("Press 3: to update salary");
		System.out.println("Press 4: to update age");
		
		do 
		{
			try
			{
				input = scanner.nextInt();
				status = true;
			}
			catch (Exception e1) 
			{
				System.err.println("Please provide integer input");
			} 
		} while (!status);
		
		do 
		{
			try 
			{
				connection = connect();
				switch (input) 
				{
					case 1: sql = "update test.employee set EmpName=? where EmpId=?";
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, inputName());
							break;
					case 2: sql = "update test.employee set EmpDept=? where EmpId=?";
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, EmployeeDetails.setDepartment());
						    break;
					case 3: sql = "update test.employee set EmpSalary=? where EmpId=?";
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setDouble(1, inputSalary());
							break;
					case 4: sql = "update test.employee set EmpAge=? where EmpId=?";
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setInt(1, inputAge());
							break;
					default:System.out.println("Enter between 1-4");
							break;
				}
				System.out.println("Enter the id");
				preparedStatement.setInt(2, scanner.nextInt());
				preparedStatement.executeUpdate();
				connection.close();
			} 
			catch (SQLException e)
			{
				System.err.println("SQL Exception Caught");
			}
			System.out.println("To update again press Y else press N:");
			char repeat = scanner.next().charAt(0);
			if(repeat=='Y' || repeat=='y')
			{
				status = true;
			}
			else
			{
				System.out.println("Updation closed");
			}
			
		} while (!status);
	}
	
	
	public static void executeDelete()
	{
		String sql = "delete from test.employee where EmpId=?";
		try 
		{
			connection = connect();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			System.out.println("Enter the id:");
			preparedStatement.setString(1, scanner.next());
			preparedStatement.executeUpdate();
			connection.close();
		}
		catch (SQLException e) 
		{
			System.err.println("Exception caught");
		}
	}
	
	
	public static void displayList()
	{
		int count = 0;
		String selectQuery = "select * from test.employee";
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);
			while(resultSet.next())
			{
				count++;
				int id = resultSet.getInt("EmpId");
				String name = resultSet.getString("EmpName");
				String department = resultSet.getString("EmpDept");
				double Salary = resultSet.getDouble("EmpSalary");
				int age = resultSet.getInt("EmpAge");
				Employee employee = new Employee(id, age, name, department, Salary);
				System.out.println(employee);
			}
			System.out.println(count+" is the total number of employee in the company");
			connection.close();
		}
		catch (SQLException e) 
		{
			System.err.println("Exception");
		}
	}
}
