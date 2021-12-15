package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APIWorkFlowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("request is prepared for creating an employee")
    public void request_is_prepared_for_creating_an_employee() {
         request = given().header(APIConstants.Header_Content_type, APIConstants.Content_Type).
                 header(APIConstants.Header_Authorization, GenerateTokenSteps.token).body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);

    }

    @Then("employee created contains key {string} and value {string}")
    public void employee_created_contains_key_message_abd_value_employee_created(String message, String employeecreatedvalue) {
       response.then().assertThat().body(message, equalTo(employeecreatedvalue));
    }


    @Then("employee id {string} is stored as a global variable to be used for other calls")
    public void employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String empID) {
        employee_id = response.jsonPath().getString(empID);
        System.out.println(employee_id);

    }

   /* ----------------------------------------------------------------------*/


    @Given("request is prepared to retrieve the created employee")
    public void request_is_prepared_to_retrieve_the_created_employee() {
        request = given().header(APIConstants.Header_Content_type, APIConstants.Content_Type).
                header(APIConstants.Header_Authorization, GenerateTokenSteps.token).queryParam("employee_id", employee_id);
    }

    @When("Get call is made to retrieve the created employee")
    public void get_call_is_made_to_retrieve_the_created_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("status code for this employee is {int}")
    public void status_code_for_this_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }


    @Then("retrieved employee ID {string} should match with globally stored employee id")
    public void retrieved_employee_id_should_match_with_globally_stored_employee_id(String employeeIdFromResponse) {
       String tempEmpID = response.jsonPath().getString(employeeIdFromResponse);
        Assert.assertEquals(employee_id, tempEmpID);
    }

    @Then("retrieved data at {string} object matches the data used to create an employee with employee id {string}")
    public void retrieved_data_at_object_matches_the_data_used_to_create_an_employee_with_employee_id
            (String employeeObject, String responseEmployeeID, DataTable dataTable) {
        List<Map<String, String>> expectedData =  dataTable.asMaps(String.class, String.class);
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject);

        int index = 0;
        for(Map<String, String> map: expectedData){
            Set<String> keys = map.keySet();
            for(String key: keys){
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);

                Assert.assertEquals(expectedValue, actualValue);
            }
            index++;
        }

        String empID = response.body().jsonPath().getString(responseEmployeeID);
        Assert.assertEquals(empID, employee_id);  // empId - from scenario 2; employee_id - from scenario 1

    }

    @Given("request is prepared for creating an employee with dynamic data {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void request_is_prepared_for_creating_an_employee_with_dynamic_data(String emp_firstname, String emp_lastname,
                                                                               String emp_middle_name, String emp_gender,
                                                                               String emp_birthday, String emp_status, String emp_job_title) {

        request = given().header(APIConstants.Header_Content_type, APIConstants.Content_Type).
                header(APIConstants.Header_Authorization, GenerateTokenSteps.token).body(APIPayloadConstants.payloadMoreDynamic
                        (emp_firstname, emp_lastname, emp_middle_name, emp_gender, emp_birthday, emp_status,  emp_job_title ));
        System.out.println(request);

    }

}
