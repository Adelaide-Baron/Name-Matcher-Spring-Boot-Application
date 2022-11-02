import io.restassured.RestAssured;
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
    @DisplayName("Check that message body contains Hello World")
    void checkThatMessageBodyContainsHelloWorld(){
        get(baseURL).then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("message", equalTo("Hello World"));
    }
}
