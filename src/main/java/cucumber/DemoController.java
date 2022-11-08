package cucumber;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class DemoController {
    private static final Logger LOG = getLogger(DemoController.class.getName());


    @GetMapping("HelloWorld")
    public Map<String, String> helloWorld() throws UnknownHostException {
        return getResponse();
    }

    private Map<String, String> getResponse() throws UnknownHostException {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        LOG.info("Returning {}", response); //investigate this bit?
        return response;
    }


}
