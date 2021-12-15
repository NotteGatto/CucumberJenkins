package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dash=new DashBoardPage();
        click(dash.addEmployeeButton);
    }
    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        sendText(addEmployeePage.firstName, "Noor");
        sendText(addEmployeePage.middleName, "007breakmanager");
        sendText(addEmployeePage.lastName, "khan");

    }
    @When("user selects checkbox")
    public void user_selects_checkbox() {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        click(addEmployeePage.createLoginCheckBox);

    }
    @When("user enters username password and confirmpassword")
    public void user_enters_username_password_and_confirmpassword() {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        sendText(addEmployeePage.createUsername, "Johnny");
        sendText(addEmployeePage.createPassword, "Mnemonik123");
        sendText(addEmployeePage.rePassword, "Mnemonik123");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        click(addEmployeePage.saveBtn);
    }
    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        System.out.println("Employee added successfully");
    }
    @When("user deletes employee id")
    public void user_deletes_employee_id() {
       AddEmployeePage addEmployeePage=new AddEmployeePage();
       addEmployeePage.employeeId.clear();
    }

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dash=new DashBoardPage();
        click(dash.pimOption);
    }
    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("user enters {string} {string} and {string} for an employee")
    public void user_enters_and_for_an_employee(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);

    }
    @When("I add multiple employees and verify then that user has been added successfully")
    public void i_add_multiple_employees_and_verify_then_that_user_has_been_added_successfully(DataTable employees) throws InterruptedException {
        List<Map<String, String>> employeeNames = employees.asMaps();
        for(Map<String, String> employeeName: employeeNames){
            String valueFirstName = employeeName.get("firstName");
            String valueMiddleName = employeeName.get("middleName");
            String valueLastName = employeeName.get("lastName");

            AddEmployeePage addEmployeePage=new AddEmployeePage();
            sendText(addEmployeePage.firstName, valueFirstName);
            sendText(addEmployeePage.middleName, valueMiddleName);
            sendText(addEmployeePage.lastName, valueLastName);
            click(addEmployeePage.saveBtn);

            //assertion in homework
            //verify the employee has been added

            DashBoardPage dash = new DashBoardPage();
            click(dash.addEmployeeButton);
            Thread.sleep(3000);
        }
    }

    @When("user adds multiple employee from excel file using {string} sheet and verify the added employee")
    public void user_adds_multiple_employee_from_excel_file_using_sheet_and_verify_the_added_employee(String sheetName) {
      List<Map<String, String>> newEmployees = ExelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetName);
        DashBoardPage dash = new DashBoardPage();
        AddEmployeePage add = new AddEmployeePage();

        Iterator<Map<String, String>> it = newEmployees.iterator();
        while(it.hasNext()){
            Map<String, String> mapNewEmp = it.next();
            sendText(add.firstName, mapNewEmp.get("FirstName"));
            sendText(add.middleName, mapNewEmp.get("MiddleName"));
            sendText(add.lastName, mapNewEmp.get("LastName"));
           click(add.saveBtn);


           //Assertion in HW
            click(dash.addEmployeeButton);
        }

    }
}
