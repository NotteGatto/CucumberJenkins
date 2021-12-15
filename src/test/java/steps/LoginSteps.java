package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        LoginPage login=new LoginPage(); //dynamic code will be accepting everywhere
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        LoginPage login=new LoginPage();
        click(login.loginBtn);
    }

    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        DashBoardPage dash=new DashBoardPage();
        Assert.assertTrue(dash.welcomeMessage.isDisplayed()); //from junit
        System.out.println("Admin welcome message is displayed as: "+ dash.welcomeMessage.getText());

    }

    @When("user enters valid ess username and password")
    public void user_enters_valid_ess_username_and_password() {
        LoginPage login=new LoginPage();
        sendText(login.usernameBox, "johnny1234560000");
        sendText(login.passwordBox, "Syntax1253!!!!");

    }

    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        DashBoardPage dash=new DashBoardPage();
        Assert.assertTrue(dash.welcomeMessage.isDisplayed()); //from junit
        System.out.println("Welcome message is displayed as: "+dash.welcomeMessage.getText());
    }

    @When("user enters valid username and invalid password")
    public void user_enters_valid_username_and_invalid_password() {
        LoginPage login=new LoginPage();
        sendText(login.usernameBox, "Admin");
        sendText(login.passwordBox, "Syntax2021");
    }

    @Then("user see invalid credentials message on login page")
    public void user_see_invalid_credentials_message_on_login_page() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        System.out.println("Invalid credentials, message is displayed as: "+loginPage.errorMessage.getText());
    }

    @When("user enters invalid credentials and clicks on login and verify the error")
    public void user_enters_invalid_credentials_and_clicks_on_login_and_verify_the_error(DataTable errorValidation) {
        List<Map<String, String>> errorData = errorValidation.asMaps();

        for(Map<String, String> validation: errorData){
            String usernameValue = validation.get("username");
            String passwordValue = validation.get("password");
            String errorMessageValue = validation.get("errorMessage");

            LoginPage login=new LoginPage();
            sendText(login.usernameBox, usernameValue );
            sendText(login.passwordBox, passwordValue);
            click(login.loginBtn);

            String errorMessageActual = login.errorMessage.getText();
            Assert.assertEquals("Values do not match", errorMessageActual, errorMessageValue);

        }
    }

    @When("user enters invalid {string} and {string} and clicks on login and verify the {string}")
    public void user_enters_invalid_and_and_clicks_on_login_and_verify_the(String username, String password, String errorMessage) {
        LoginPage login=new LoginPage();
        sendText(login.usernameBox, username );
        sendText(login.passwordBox, password);
        click(login.loginBtn);
        String errorMessageActual = login.errorMessage.getText();
        Assert.assertEquals("Values do not match", errorMessageActual, errorMessage);

    }
}
