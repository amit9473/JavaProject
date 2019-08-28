package org.jsp.employeedatamanagement.employee;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.jsp.employeedatamanagement.validation.ValidateData;

public class EmployeeDetails extends EmployeeList
{
	
	
	private static Scanner scan = new Scanner(System.in);
	private static boolean status = false;
	private static int eAge =0;
	private static int eId = 001;
	private static String eName=null;
	private static String eDepartment=null;
	private static double eSalary=0.0;
	
	
	public static EmployeeDataInt getData()
	{
		EmployeeDataInt emp = new EmployeeData();
		return emp;
	}
	
	
	private static int setId()
	{
		int size = employee.size();
		return eId+size;
	}
	
	
	private static int setAge()
	{
		do
		{
			try
			{
				System.out.println("Enter employee's age, should be in range [22--60]");
				eAge = scan.nextInt();
				status = ValidateData.validateAge(eAge);
			}
			catch(InputMismatchException ii)
			{
				System.out.println("Invalid input : age should be integer data type");
				scan.nextLine();
			}
		}while(!status);
		return eAge;
	}
	
	
	private static String setName()
	{
		do
		{
			System.out.println("Enter employee's name, should only contain words and 'spaces'");
			eName = scan.next();
			eName+=scan.nextLine();
			status = ValidateData.validateName(eName);
		}while(!status);
		return eName;
	}
	
	
	public static String setDepartment()
	{
		System.out.println("Press 1 : For Developer");
		System.out.println("Press 2 : For Tester");
		System.out.println("Press 3 : For HR");
		System.out.println("Press 4 : For Management");
		int input=0;
		System.out.println("Select the department");
		try 
		{
			input = scan.nextInt();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Enter input should be of integer type");
		}
		switch (input) 
		{
		case 1: eDepartment="Developer";
				break;
		case 2: eDepartment="Tester";
				break;
		case 3: eDepartment="HR";
				break;
		case 4: eDepartment="Management";
				break;
		default:System.out.println("Wrong selection");
				break;
		}
		return eDepartment;
	}
	
	
	private static double setSalary()
	{
		do
		{
			System.out.println("Enter employee's Salary, should only contain decimal values");
			try 
			{
				eSalary = scan.nextDouble();
			} 
			catch (Exception e) 
			{
				System.out.println("Salary should only be in decimal");
			}
			
		}while(!status);
		return eSalary;
	}
	
	
	public static Employee scanData()
	{
		Employee e = new Employee(setId(), setAge(), setName(), setDepartment(), setSalary());
		return e;
	}
}