package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    @FindBy(id="txtUsername")
    public WebElement usernameBox;

    @FindBy(id="txtPassword")
    public WebElement passwordBox;

    @FindBy(id="btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage") //from LoginTest if u enter invalid credentials
    public WebElement errorMessage;


    public LoginPage(){ //constructor
        PageFactory.initElements(driver, this); //initializing elements
    }

    public void login(String username, String password){
        sendText(usernameBox, username);
        sendText(passwordBox, password);
        click(loginBtn);
}
}
