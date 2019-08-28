package org.jsp.employeedatamanagement.employee;

public interface EmployeeDataInt
{
	void update();
	Employee addEmployee(Employee e);
	int checkAge(int eAge);
	Employee checkId(int eId);
	int totalEmployee();
	void deleteEmployeeData();
	void displayAllEmployee();
	int checkDepartment(String eDepartment);
	int checkName(String eName);
}
