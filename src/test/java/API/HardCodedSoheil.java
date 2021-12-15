package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HardCodedSoheil {
    /*
Given - pre condition - prepare the request
When - Action - sending the request/hitting the endpoint
Then - result - verify response
     */

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzg5ODcxMDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzOTAzMDMwMSwidXNlcklkIjoiMzM2MCJ9.R40RlXiDC2G4oxlfQHNFAmJTOVMwM2NAsM81k3iClvs";
    static String employee_id;

    @Test
    public void getDetailsOfOneEmployee(){
        //rest assured consider baseurl as baseuri
        //given
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", "25598A");

        //when - hitting the endpoint
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        System.out.println(response.asString());

        //then - result/assertion
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void createEmployee(){

        //given
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"emp_firstname\": \"msthegreat123\",\n" +
                        "  \"emp_lastname\": \"Andru2\",\n" +
                        "  \"emp_middle_name\": \"elenam123\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1999-01-12\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"API Tester\"\n" +
                        "}");


        //when
        Response response = preparedRequest.when().post("/createEmployee.php");
        response.prettyPrint();
        //this pretty print does the same job as syso. //   System.out.println(response.asString());

        //jsonPath() we use this to get specific information from the json object
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        //then
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("msthegreat123"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

    }

}
