package cucumber.stepdefs;

import cucumber.DatabaseService;
import cucumber.DemoController;
import cucumber.Person;
import cucumber.PersonRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.slf4j.LoggerFactory.getLogger;

public class MyStepdefs {


    private String applicationUrl = "http://localhost:15692/";
    private Response response;
//
//    @Autowired
//    private PersonRepository personRepository;
    @Autowired
    private DatabaseService databaseService;

    List<Person> allFromDB;

    @When("a GET request is performed on {string}")
    public void a_get_request_is_performed_on_actuator_health(String endpoint) {
        response = get(applicationUrl + endpoint);
    }
    @Then("the response code is {int}")
    public void the_response_code_is(int code) {
        Assertions.assertEquals(response.statusCode(), code);
    }


    @Then("the response contains {string} with value {string}")
    public void the_response_contains_with_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @When("a GET request is performed on the baseURL")
    public void aGETRequestIsPerformedOnTheBaseURL() {
        response = get(applicationUrl);
    }

}
