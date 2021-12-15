package API;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExampleOfAPI {
    /*
    Given – pre-condition – prepare the request
    When – Action – sending the request   ( hitting the endpoint)
    Then – result – verify response */

    // rest assured consider baseURL as baseURI  (baseURL hrm.syntaxtechs.net/syntaxapi/api ---> as baseURI)
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzkzMzE3ODEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzOTM3NDk4MSwidXNlcklkIjoiMzM2NiJ9.BzZ1Ys93qTCwxhf1OQdoU6ep9xUVWWN7SD2nu-g607Y";
    static String employee_id;

    @Test
   public void dgetDetailsOfOneEmployee() {
    //Given -request
    RequestSpecification preparedRequest = given().header("Authorization",token)
            .header("Content-Type", "application/json").queryParam("employee_id", "25797A");

    //When -  hitting the endpoint
    Response response = preparedRequest.when().get("/getOneEmployee.php");
    System.out.println(response.asString());

    //Then - result/assertion
    response.then().assertThat().statusCode(200);
  }

  @Test
  public void acreateEmployee(){
        //Given
      RequestSpecification preparedRequest = given().header("Authorization", token)
              .header("Content-Type", "application/json").body("{\n" +
                      "  \"emp_firstname\": \"Jo\",\n" +
                      "  \"emp_lastname\": \"Malone\",\n" +
                      "  \"emp_middle_name\": \"Myrth and Tonka\",\n" +
                      "  \"emp_gender\": \"M\",\n" +
                      "  \"emp_birthday\": \"1996-06-26\",\n" +
                      "  \"emp_status\": \"Employee\",\n" +
                      "  \"emp_job_title\": \"API Tester\"\n" +
                      "}");

      //When
      Response response = preparedRequest.when().post("/createEmployee.php");
      response.prettyPrint();
      //this prettyPrint does the same job as syso. // System.out.println(response.asString());


      //jsonPath() we use this to get specific information from the json object
      employee_id = response.jsonPath().getString("Employee.employee_id");
      System.out.println(employee_id);

      //then
      response.then().assertThat().statusCode(201);
      response.then().assertThat().body("Employee.emp_firstname", equalTo("Jo"));
      response.then().assertThat().body("Message", equalTo("Employee Created"));
      response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
  }

  @Test
  public void bgetCreatedEmployee(){
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpID = empID.contentEquals(employee_id);
        Assert.assertTrue(comparingEmpID);

        String firstName = response.jsonPath().getString("employee.emp_firstname");
        Assert.assertTrue(firstName.contentEquals("Jo"));

        String lastName = response.jsonPath().getString("employee.emp_lastname");
        Assert.assertTrue(lastName.contentEquals("Malone"));

}

     @Test
     public void cupdateCreatedEmployee(){

         RequestSpecification preparedRequest = given().header("Authorization", token)
                 .header("Content-Type", "application/json").body("{\n" +
                         "  \"employee_id\": \"" + employee_id + "\",\n" +
                         "  \"emp_firstname\": \"JoJo\",\n" +
                         "  \"emp_lastname\": \"MaloneMalone\",\n" +
                         "  \"emp_middle_name\": \"Myrth and Tonka\",\n" +
                         "  \"emp_gender\": \"M\",\n" +
                         "  \"emp_birthday\": \"1996-06-26\",\n" +
                         "  \"emp_status\": \"Employee\",\n" +
                         "  \"emp_job_title\": \"Database Administrator\"\n" +
                         "}");

         Response response = preparedRequest.when().post("/updateEmployee.php");
         response.prettyPrint();

         }


   /* @Test
    public void getAllEmployees(){
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json");
        Response response = preparedRequest.when().get("/getAllEmployee.php");

        String allEmployees = response.prettyPrint();
        JsonPath js = new JsonPath(allEmployees);

        int count = js.getInt("Employee.size()");
        System.out.println(count);

        for (int i=0; i<count; i++){
            String employeeIDs = js.getString("Employees[" + i + "].employee_id");
            System.out.println(employeeIDs);
        }
        }*/

}

