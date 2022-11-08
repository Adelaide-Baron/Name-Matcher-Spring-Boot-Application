package cucumber.stepdefs;

import cucumber.Person;
import cucumber.PersonRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class MyStepdefs {


    private String applicationUrl = "http://localhost:15692/";
    private Response response;

    private DataSource dataSource;

    @When("a GET request is performed on {string}")
    public void a_get_request_is_performed_on_actuator_health(String endpoint) {
        response = get(applicationUrl + endpoint); //have this be applicationURL + endpoint, just wasn't working previously
    }
    @Then("the response code is {string}")
    public void the_response_code_is(String code) {
        int desiredCode = Integer.parseInt(code);
        Assertions.assertEquals(response.statusCode(), desiredCode);
    }


    @Then("the response contains {string} with value {string}")
    public void the_response_contains_with_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @When("a GET request is performed on the baseURL")
    public void aGETRequestIsPerformedOnTheBaseURL() {
        response = get(applicationUrl);
    }

//DB
    @Given("a database connection is established")
    public void aDatabaseConnectionIsEstablished() {
        assertThat(dataSource, is(notNullValue()));
    }

    @Autowired
    private PersonRepository personRepository;

    @And("the person table is not empty")
    public void theTableIsNotEmpty() {
        Assertions.assertNotNull(personRepository.findAll());
    }

    private List<Person> personResults;

    @When("I {string} in the database")
    public void iInTheDatabase(String arg0) {
//        personResults = personRepository.arg0();
    }


    @Then("I am presented with a list of the people in the DB")
    public void iAmPresentedWithAListOfThePeopleInTheDB() {
        Person person = new Person((long) 1, "Joe", "Bloggs");
        Person person2 = new Person((long) 2, "James", "Smith");
        List<Person> exectedResults = Arrays.asList(person, person2);
        Assertions.assertEquals(exectedResults, personResults);
    }
}
