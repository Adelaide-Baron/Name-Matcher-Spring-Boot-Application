import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

public class UnitTests {


    @Test
    @DisplayName("Check that actuator/health endpoint returns status code 200")
    void checkThatActuatorHealthReturnsCode200(){
//        System.out.println(RestAssured.get("http://localhost:15692/actuator/health").statusCode());
//        System.out.println("hi");
        Assertions.assertEquals(RestAssured.get("http://localhost:15692/actuator/health").statusCode(), 200);
    }
}
