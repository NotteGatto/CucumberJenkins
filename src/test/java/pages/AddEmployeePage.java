package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy(css="input#firstName")
    public WebElement firstName;

    @FindBy(css="input#middleName")
    public WebElement middleName;

    @FindBy(css="input#lastName")
    public WebElement lastName;

    @FindBy(id="photofile")
    public WebElement photograph;

    @FindBy(id="chkLogin")
    public WebElement createLoginCheckBox;

    @FindBy(id="user_name")
    public WebElement createUsername;

    @FindBy(id ="user_password")
    public WebElement createPassword;

    @FindBy(id ="re_password")
    public WebElement rePassword;

    @FindBy(id="btnSave")
    public WebElement saveBtn;

    @FindBy(id="employeeId")
    public WebElement employeeId;

    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }
}
