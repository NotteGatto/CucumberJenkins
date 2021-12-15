package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeListPage extends CommonMethods {

    @FindBy(id="empsearch_id")
    public WebElement idEmployee;

    @FindBy(id="searchBtn")
    public WebElement searchBtn;

    @FindBy(id="empsearch_employee_name_empName")
    public WebElement employeeNameField;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr/td")
    public WebElement emplTable;

    public EmployeeListPage(){
        PageFactory.initElements(driver, this);

    }
}
