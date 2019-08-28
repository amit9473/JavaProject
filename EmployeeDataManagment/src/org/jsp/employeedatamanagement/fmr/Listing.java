package org.jsp.employeedatamanagement.fmr;

import java.util.List;
import java.util.stream.Stream;

import org.jsp.employeedatamanagement.employee.Employee;
import org.jsp.employeedatamanagement.employee.EmployeeList;

public class Listing 
{
	public static void main(String[] args) 
	{
		List<Employee> emp = EmployeeList.employee;
		
		Employee e1 =new Employee(5, 25, "rishabh", "mech", 5.6);
		Employee e2 =new Employee(6, 32, "naman", "ce", 12.8);
		Employee e3 =new Employee(7, 29, "mortal", "cs", 10.6);
		Employee e4 =new Employee(8, 45, "raka", "ee", 5.9);
		Employee e5 =new Employee(9, 56, "venom", "it", 8.6);
		
		emp.add(e1);
		emp.add(e2);
		emp.add(e3);
		emp.add(e4);
		emp.add(e5);
		
		emp.forEach(x -> System.out.println(x.geteName()));
		
		Stream<Employee> emp1 = emp.stream();
		
		emp1.forEach(x -> System.out.println(x));
	}
}
