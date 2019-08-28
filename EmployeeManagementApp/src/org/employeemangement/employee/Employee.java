package org.employeemangement.employee;

public class Employee
{
	
	String firstName;
	String middleName;
	String lastName;
	int sexId;
	int deptId;
	int mgrId;
	String phoneNumber;
	String emailId;
	
	public Employee(String firstName, String middleName, String lastName, int sexId, int deptId, int mgrId,
			String phoneNumber, String emailId) 
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.sexId = sexId;
		this.deptId = deptId;
		this.mgrId = mgrId;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	@Override
	public String toString() 
	{
		return "Employee [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", sexId="
				+ sexId + ", deptId=" + deptId + ", mgrId=" + mgrId + ", phoneNumber=" + phoneNumber + ", emailId="
				+ emailId + "]";
	}
	
	
}
