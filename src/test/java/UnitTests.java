import cucumber.*;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class UnitTests {

    //nested classes
    String baseURL = "http://localhost:15692/";

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DatabaseService demoController;

    @Test
    @DisplayName("Check that actuator/health endpoint returns status code 200")
    void checkThatActuatorHealthReturnsCode200(){
        Assertions.assertEquals(RestAssured.get(baseURL + "actuator/health").statusCode(), 200);
    }

    @Test
    @DisplayName("Check that HelloWorld endpoint contains Hello World")
    void checkThatHelloWorldEndpointBodyContainsHelloWorld(){
        get(baseURL + "HelloWorld").then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("message", equalTo("Hello World"));
    }

//    @Disabled("Method no longer in use ")
//    @Test
//    @DisplayName("Check that concatNamesInArray concatenates and converts to lower case")
//    void checkThatconcatNamesInArrayConcatsAndLower(){
//        String[] array = {"Adelaide Baron", "baron AdeLiade"};
//        String[] expected = {"adelaidebaron", "baronadeliade"};
//        DemoController demoController = new DemoController(demoController);
//        Assertions.assertArrayEquals(expected, demoController.concatNamesInArray(array).toArray());
//    }

//    @Test
//    @DisplayName("Check that isNameInDB returns MATCHED for name in the DB")
//    void checkThatisNameInDBReturnsMatchedForNameInDB(){
//        DemoController demoController = new DemoController();
//        Assertions.assertEquals("MATCHED", demoController.isNameInDB("Joe Bloggs"));
//    }
//    @Test
//    @DisplayName("Check that isNameInDB returns NOT_MATCHED for name in the DB")
//    void checkThatisNameInDBReturnsNotMatchedForNameInDB(){
//        DemoController demoController = new DemoController();
//        // demoController.isNameInDB("Joe bloggs");
//        Assertions.assertEquals("NOT_MATCHED", demoController.isNameInDB("Joseph Bloggs"));
//    }

    @Test
    @DisplayName("Check that concatNameLowerCase works on seperated by space and underscore")
    void concatNameLowerCaseSpaceAndUnderscore(){
        String[] actual = { DemoController.concatNameLowerCase("hi tHere"), DemoController.concatNameLowerCase("hi_There")};
        String[] expected = {"hithere", "hithere"};
        Assertions.assertArrayEquals(expected, actual);
    }




    @Test
    @DisplayName("Check DB Connection")
    void checkDBConnection() {
        Assertions.assertNotNull(personRepository.findAll());
    }

    @Test
    @DisplayName("generic")
    void generic(){
        demoController.getConcatNamesInDB();
        DemoController demoController1 = new DemoController(demoController);
        System.out.println(demoController1.doNameConcat().toString());
    }

    @Test
    @DisplayName("Check that MATCHED is returned for match in DB")
    void checkThatMATCHEDisReturnedForMatchInDB() {
        DemoController demoController1 = new DemoController(demoController);
        Assertions.assertEquals("MATCHED", demoController1.isNameInDB("Joe_Bloggs"));
    }

    @Test
    @DisplayName("Check that NOT_MATCHED is returned for no match in DB")
    void checkThatNOT_MATCHEDisReturnedForNoMatchInDB() {

    }




    }



