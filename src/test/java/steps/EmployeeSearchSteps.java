package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethods {

    @Given("user is navigated to HRMS")
    public void user_is_navigated_to_hrms() {
        openBrowser();
    }

    @Given("user is logged in with valid admin credentials")
    public void user_is_logged_in_with_valid_admin_credentials() {
        LoginPage login=new LoginPage(); //dynamic code will be accepting everywhere
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        click(login.loginBtn);
    }

    @Given("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        DashBoardPage dash=new DashBoardPage();
        click(dash.pimOption);
        click(dash.employeeListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        EmployeeListPage emp=new EmployeeListPage();
        sendText(emp.idEmployee, "20119000");
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        EmployeeListPage emp=new EmployeeListPage();
        sendText(emp.employeeNameField, "sohail");
    }

    @When("click on search button")
    public void click_on_search_button() {
        EmployeeListPage emp=new EmployeeListPage();
        click(emp.searchBtn);
    }


    @Then("user see employee information is displayed via Emp ID")
    public void user_see_employee_information_is_displayed_via_emp_id() {
        EmployeeListPage employeeListPage=new EmployeeListPage();
        String empID = "20119000";
        Assert.assertTrue(employeeListPage.emplTable.getText().contains(empID));
        System.out.println("Searching employee via ID is displayed");
    }

    @Then("user see employee information is displayed via Emp Name")
    public void user_see_employee_information_is_displayed_via_emp_name() {
        EmployeeListPage employeeListPage=new EmployeeListPage();
        String actualResult=employeeListPage.emplTable.getText();
        System.out.println(actualResult);
        String empName= "Sohail Sohail Abassi";
        Assert.assertTrue(employeeListPage.emplTable.getText().contains(empName));
        System.out.println("Searching employee via NAME is displayed");

    }
    }


