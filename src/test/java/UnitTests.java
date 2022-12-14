import cucumber.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class UnitTests {

    String baseURL = "http://localhost:15692/";
    @Autowired
    private DatabaseService databaseService;

    List<Person> allFromDB;

    @DisplayName("Endpoint")
    @Nested
    class endpointTests {
        @Test
        @DisplayName("Check that actuator/health endpoint returns status code 200")
        void checkThatActuatorHealthReturnsCode200() {
            Assertions.assertEquals(RestAssured.get(baseURL + "actuator/health").statusCode(), 200);
        }

        @Test
        @DisplayName("Check that HelloWorld endpoint contains Hello World")
        void checkThatHelloWorldEndpointBodyContainsHelloWorld() {
            get(baseURL + "HelloWorld").then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("message", equalTo("Hello World"));
        }

        @Test
        @DisplayName("Check that NameMatcher/firstName_surname endpoint contains Match_found ")
        void checkThatNameMatcherEndpointContainsMatch_found() {

            RestAssured.baseURI = baseURL;
            RequestSpecification httpRequest = RestAssured.given();
            Response response = httpRequest.get("NameMatcher/Joe_Bloggs");

            ResponseBody body = response.getBody();

            String bodyAsString = body.asString();
            Assertions.assertTrue(bodyAsString.contains("Match_found"));
        }

        @Test
        @DisplayName("Check that NameMatcher/firstName_surname endpoint contains Firstname_lastname")
        void checkThatNameMatcherEndpointContainsFirstname_lastname() {

            RestAssured.baseURI = baseURL;
            RequestSpecification httpRequest = RestAssured.given();
            Response response = httpRequest.get("NameMatcher/Joe_Bloggs");

            ResponseBody body = response.getBody();

            String bodyAsString = body.asString();
            Assertions.assertTrue(bodyAsString.contains("Firstname_lastname"));
        }

        @Test
        @DisplayName("Check that NameMatcher endpoint returns MATCHED for /firstName_surname in DB")
        void checkThatNameMatcherEndpointReturnsMatchForMatch() {

            RestAssured.baseURI = baseURL;
            RequestSpecification httpRequest = RestAssured.given();

            DemoController demoController1 = new DemoController(databaseService);
            Person personInDB = demoController1.findAllFromDB().get(0);
            String fullName = personInDB.getFirstName() + "_" + personInDB.getSurname();


            Response response = httpRequest.get("NameMatcher/" + fullName);

            ResponseBody body = response.getBody();

            String bodyAsString = body.asString();
            Assertions.assertTrue(bodyAsString.contains("MATCHED"));
        }

        @Test
        @DisplayName("Check that NameMatcher endpoint returns NOT_MATCHED for /firstName_surname not in DB")
        void checkThatNameMatcherEndpointReturnsNOT_MATCHEDForMatch() {

            RestAssured.baseURI = baseURL;
            RequestSpecification httpRequest = RestAssured.given();

            DemoController demoController1 = new DemoController(databaseService);
            Person personInDB = demoController1.findAllFromDB().get(0);
            String fullName = personInDB.getFirstName() + "1000_" + personInDB.getSurname();


            Response response = httpRequest.get("NameMatcher/" + fullName);

            ResponseBody body = response.getBody();

            String bodyAsString = body.asString();
            Assertions.assertTrue(bodyAsString.contains("NOT_MATCHED"));
        }

    }

    @DisplayName("DB Methods")
    @Nested
    class DBMethodTests{
        @Test
        @DisplayName("Check that concatNameLowerCase works on seperated by space and underscore")
        void concatNameLowerCaseSpaceAndUnderscore() {
            String[] actual = {DemoController.concatNameLowerCase("hi tHere"), DemoController.concatNameLowerCase("hi_There")};
            String[] expected = {"hithere", "hithere"};
            Assertions.assertArrayEquals(expected, actual);
        }

        @Test
        @DisplayName("Check DB Connection")
        void checkDBConnection() {
            databaseService.getConcatNamesInDB();
            DemoController demoController1 = new DemoController(databaseService);
            Assertions.assertNotNull(demoController1.doNameConcat().toString());
        }


        @Test
        @DisplayName("Check I can findAll in the DB")
        void checkICanFindAllInDB(){
            DemoController demoController = new DemoController(databaseService);

            allFromDB = demoController.findAllFromDB();
        }

        @Test
        @DisplayName("Check that MATCHED is returned for match in DB")
        void checkThatMATCHEDisReturnedForMatchInDB() {
            DemoController demoController1 = new DemoController(databaseService);
            Assertions.assertEquals("MATCHED", demoController1.isNameInDB("Joe_Bloggs"));
        }

        @Test
        @DisplayName("Check that NOT_MATCHED is returned for no match in DB")
        void checkThatNOT_MATCHEDisReturnedForNoMatchInDB() {
            DemoController demoController1 = new DemoController(databaseService);
            Assertions.assertEquals("NOT_MATCHED", demoController1.isNameInDB("Joe_B"));
        }

        @Test
        @DisplayName("Returns a list of people")
        void checkThatSOMETHINGReturnsAListOfPeople(){

            DemoController demoController = new DemoController(databaseService);
            allFromDB = demoController.findAllFromDB();

            Person person = new Person((long) 1, "Joe", "Bloggs");
            Person person2 = new Person((long) 2, "James", "Smith");
            List<Person> exectedResults = Arrays.asList(person, person2);
            Assertions.assertEquals(exectedResults, allFromDB);
        }
    }

}









