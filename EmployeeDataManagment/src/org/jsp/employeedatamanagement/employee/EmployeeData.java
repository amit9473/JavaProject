package org.jsp.employeedatamanagement.employee;

import java.util.InputMismatchException;
import java.util.Iterator;
import org.jsp.employeedatamanagement.exception.InvalidAgeException;
import org.jsp.employeedatamanagement.validation.ValidateData;

public class EmployeeData extends EmployeeList implements EmployeeDataInt
{
	private int count = 0;
	private boolean status = false;
	private Employee e = null;
	private Iterator<Employee> i = employee.iterator();
	
	
	@Override
	public Employee addEmployee(Employee e)
	{
		employee.add(e);
		return e;
	}
	
	
	@Override
	public int checkAge(int eAge)
	{
		for(Employee e:employee)
		{
			if((""+e.geteAge()).equals(""+eAge))
			{
				count++;
				System.out.println(e);
			}
		}
		return count;
	}
	
	
	@Override
	public int checkDepartment(String eDepartment)
	{
		for(;i.hasNext();)
		{
			e = i.next();
			if((e.geteDepartment()).equals(eDepartment))
			{
				count++;
				System.out.println(e);
			}
		}
		return count;
	}
	
	
	@Override
	public Employee checkId(int eId)
	{
		e = employee.get(eId-1);
		return e;
	}
	
	
	@Override
	public int totalEmployee()
	{
		int totalEmployee = employee.size();
		return totalEmployee;
		
	}	
	
	
	public void deleteEmployeeData()
	{
		int gid = 0;
		System.out.println("Press 1: If you want to delete data using Id");
		System.out.println("Press 2: If you want to delete data using index number on list, total number of employee data is "+employee.size());
		int data = scan.nextInt();
		switch(data)
		{
		case 1: do
				{
					try
					{
						System.out.println("Enter the id to be deleted");
						gid = scan.nextInt();
						status = ValidateData.validateId(gid);
					}
					catch(InputMismatchException ii)
					{
						System.out.println("Invalid input : Id must be int data type");
						scan.nextLine();
					}
				}while(!status);
				boolean status1 = false;
				for(;i.hasNext();)
				{
					e = i.next();
					int e1 = e.geteId();
					if(e1==gid)
					{
						status1 = true;
						count = 1;
						System.out.println(e);
						break;
					}
				}
				if(count==0)
				{
					System.out.println("EmployeeId is OutOfBound");
				}
				if(status1)
				{
					System.out.println("Press 1 : If you want to delete the data corresponding "+e+" or press any key to terminate");
					String input = scan.next();
					if(input.charAt(0)=='1')
					{
						employee.remove(e);
					}
					else
					{
						scan.nextLine();
						System.out.println("Deletion process terminated");
					}
				}
				break;
		case 2: System.out.println("Enter the index number from list whoes data you to remove");
		  		try
		  		{
		  			if(scan.nextInt()<=employee.size())
			  		{
			  			System.out.println("If you want to delete "+employee.get(scan.nextInt()-1)+" Press 1 else to terminate Press any key");
				  		int op = scan.nextInt();
				  		if(op==1)
				  		{
				  			employee.remove(scan.nextInt()-1);
				  		}
				  		else
				  		{
				  			System.out.println("Deletion Process terminated");
				  		}
			  		}
			  		else
			  		{
			  			System.out.println("Index number is OutOfBound");
			  		}
		  		}
				catch(InputMismatchException ii)
		  		{
					System.out.println("Invalid input : Index number must be int data type");
					scan.nextLine();
		  		}
		  		break;
		  	default : System.out.println("Invaid input");
		}
	}
	
	
	//display using iterator
	public void displayAllEmployee()
	{
		for(;i.hasNext();)
		{
			System.out.println(i.next());		
		}
	}


	@Override
	public int checkName(String eName)
	{
		for(Employee e:employee)
		{
			if(e.geteName().equals(eName))
			{
				System.out.println(e);
				count++;
			}
		}
		return count;
	}


	@Override
	public void update() 
	{
		int age=0;
		String name=null;
		String department=null;
		double salary=0.0;
		System.out.println("Enter the EmployeeId");
		int id=scan.nextInt();
		e=employee.get(id-1);
		System.out.println("1: Update employee's Age");
		System.out.println("2: Update employee's Name");
		System.out.println("3: Update employee's Department");
		System.out.println("4: Update employee's Salary");
		System.out.println("Enter your choice");
		int input=0;
		try {
			input = scan.nextInt();
		}
		catch (InputMismatchException e1) 
		{
			System.out.println("Enter integer type data always");
		}
		switch(input)
		{
		case 1: System.out.println("Enter the new age");
				do
				{
					try 
					{
						age=scan.nextInt();
						status=ValidateData.validateAge(age);
					} 
					catch (InvalidAgeException e1)
					{
						System.out.println(e1);
					}
				}while(!status);
				e.seteAge(age);
				break;
		case 2: System.out.println("Enter the new Name");
				name=scan.next();
				name+=scan.nextLine();
				status=ValidateData.validateName(name);
		  		e.seteName(name);
		  		break;
		case 3: department=EmployeeDetails.setDepartment();
		  		e.seteDepartment(department);
		  		break;
		case 4: System.out.println("Enter the new salary");
				do
				{
					try
					{
						salary=scan.nextDouble();
						status=true;
					} 
					catch (InputMismatchException e1)
					{
						System.out.println("Salary should be decimal number");
					}
				}while(!status);
				e.seteSalary(salary);
				break;
		default:System.out.println("Wrong input"); 
		}

	
	}


}
