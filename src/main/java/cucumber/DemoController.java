package cucumber;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;


@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DatabaseService databaseService;
    private static final Logger LOG = getLogger(DemoController.class.getName());

    @GetMapping("HelloWorld")
    public Map<String, String> helloWorld() throws UnknownHostException {
        return getResponse();
    }

    private Map<String, String> getResponse() throws UnknownHostException {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        LOG.info("Returning {}", response);
        return response;
    }

    @GetMapping("NameMatcher/{name}")
    public Map<String, String> nameMatcher(@PathVariable("name") String name){
        isNameInDB(name.toLowerCase());
        Map<String, String> response = new HashMap<>();
        response.put("Firstname_lastname", name);
        response.put("Match_found", isNameInDB(name.toLowerCase()));
        LOG.info("Returning {}", response);
        return response;
    }

    public String isNameInDB(String name) {
        ArrayList<String> arrayToCheck = databaseService.getConcatNamesInDB();
        if(arrayToCheck.contains(concatNameLowerCase(name))){
            return "MATCHED";
        } else {
            return "NOT_MATCHED"; // Todo enum
        }
    }


    public ArrayList<String> doNameConcat(){
        return databaseService.getConcatNamesInDB();
    }

    public List<Person> findAllFromDB(){
        return databaseService.findAllFromDB();
    }

    public static String concatNameLowerCase(String name) {
        String lowerCaseName = null;
            if(name.contains(" ")){
                String[] nameToCheck = name.split(" ");
                lowerCaseName = String.join("", nameToCheck).toLowerCase();
                return lowerCaseName;
            } else if (name.contains("_")) {
                String[] nameToCheck = name.split("_");
                lowerCaseName = String.join("", nameToCheck).toLowerCase();
                return lowerCaseName;
            } else{
                lowerCaseName = name.toLowerCase();
                return lowerCaseName;
            }

    }
}
