import cucumber.DemoController;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.function.BooleanSupplier;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class UnitTests {

    String baseURL = "http://localhost:15692/";
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

    @Test
    @DisplayName("Check that concatNamesInArray concatenates and converts to lower case")
    void checkThatconcatNamesInArrayConcatsAndLower(){
        String[] array = {"Adelaide Baron", "baron AdeLiade"};
        String[] expected = {"adelaidebaron", "baronadeliade"};
        DemoController demoController = new DemoController();
        Assertions.assertArrayEquals(expected, demoController.concatNamesInArray(array).toArray());
    }

    @Test
    @DisplayName("Check that isNameInDB returns MATCHED for name in the DB")
    void checkThatisNameInDBReturnsMatchedForNameInDB(){
        DemoController demoController = new DemoController();
        Assertions.assertEquals("MATCHED", demoController.isNameInDB("Joe Bloggs"));
    }
    @Test
    @DisplayName("Check that isNameInDB returns NOT_MATCHED for name in the DB")
    void checkThatisNameInDBReturnsNotMatchedForNameInDB(){
        DemoController demoController = new DemoController();
        // demoController.isNameInDB("Joe bloggs");
        Assertions.assertEquals("NOT_MATCHED", demoController.isNameInDB("Joseph Bloggs"));
    }

    @Test
    @DisplayName("Check that concatNameLowerCase works on seperated by space and underscore")
    void concatNameLowerCaseSpaceAndUnderscore(){
        String[] actual = { DemoController.concatNameLowerCase("hi tHere"), DemoController.concatNameLowerCase("hi_There")};
        String[] expected = {"hithere", "hithere"};
        Assertions.assertArrayEquals(expected, actual);


    }



}
