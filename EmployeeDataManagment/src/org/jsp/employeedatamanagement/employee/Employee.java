package org.jsp.employeedatamanagement.employee;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable
{
	int eId;
	int eAge;
	String eName;
	String eDepartment;
	double eSalary;
	
	
	public Employee(int eId, int eAge, String eName, String eDepartment, double eSalary)
	{
		this.eId = eId;
		this.eAge = eAge;
		this.eName = eName;
		this.eDepartment = eDepartment;
		this.eSalary = eSalary;
	}
	
	
	public void seteId(int eId)
	{
		this.eId = eId;
	}
	
	
	public void seteAge(int eAge) 
	{
		this.eAge = eAge;
	}
	
	
	public void seteName(String eName)
	{
		this.eName = eName;
	}
	
	
	public void seteDepartment(String eDepartment)
	{
		this.eDepartment = eDepartment;
	}
	
	
	public void seteSalary(double eSalary) 
	{
		this.eSalary = eSalary;
	}
	
	
	public int geteId()
	{
		return eId;
	}
	
	
	public int geteAge()
	{
		return eAge;
	}
	
	
	public String geteName()
	{
		return eName;
	}
	
	
	public String geteDepartment()
	{
		return eDepartment;
	}
	
	
	public double geteSalary()
	{
		return eSalary;
	}
	
	
	@Override
	public String toString() 
	{
		if(eId<=9)
		{
			return "Employee Id= 00"+this.eId+", Emp Name= "+this.eName+", Emp Age= "+this.eAge+", Emp Department= "+this.eDepartment+", Emp Salary= "+this.eSalary;
		}
		else if(eId<=99 && eId>9)
		{
			return "Employee Id= 0"+this.eId+", Emp Name= "+this.eName+", Emp Age= "+this.eAge+", Emp Department= "+this.eDepartment+", Emp Salary= "+this.eSalary;
		}
		else
		{
			return "Employee Id= "+this.eId+", Emp Name= "+this.eName+", Emp Age= "+this.eAge+", Emp Department= "+this.eDepartment+", Emp Salary= "+this.eSalary;
		}
	}
}