Feature: Syntax HRM API workflow

  Background:
    Given JWT is generated

  @APIWorkFlow
  Scenario: Creating an employee
    Given request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And employee created contains key "Message" and value "Employee Created"
    And employee id "Employee.employee_id" is stored as a global variable to be used for other calls

  @APIWorkFlow
  Scenario: Retrieving creating employee
    Given request is prepared to retrieve the created employee
    When Get call is made to retrieve the created employee
    Then status code for this employee is 200
    And retrieved employee ID "employee.employee_id" should match with globally stored employee id
    And retrieved data at "employee" object matches the data used to create an employee with employee id "employee.employee_id"
    |emp_firstname|emp_middle_name|emp_lastname|emp_birthday|emp_gender|emp_job_title|emp_status|
    |Jo           |Myrth and Tonka|Malone      |1996-06-26  |Male        |API Tester   |Employee  |

    @Dynamic
    Scenario: Create dynamic scenario
      Given request is prepared for creating an employee with dynamic data "Mike", "Serge", "John", "M", "20-12-12", "Employee", "API Tester"



