package cucumber;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class DemoController {
    private static final Logger LOG = getLogger(DemoController.class.getName());
    String[] nameData = {"Jane Doe", "Joe Bloggs"};


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


    @GetMapping("NameMatcher/{name}")
    public Map<String, String> nameMatcher(@PathVariable("name") String name){
        isNameInDB(name.toLowerCase());
        Map<String, String> response = new HashMap<>();
        response.put("Match_found", isNameInDB(name.toLowerCase()));
        LOG.info("Returning {}", response); //investigate this bit?
        return response;
    }

    public String isNameInDB(String name) { //name will be taken in as FirstLast
        // for now, assuming we have dummy data - so will make this be an Array in this class. In later tasks make a MySQL DB
        ArrayList<String> arrayToCheck = concatNamesInArray(nameData);
        if(arrayToCheck.contains(concatNameLowerCase(name))){
            return "MATCHED";
        } else {
            return "NOT_MATCHED";
        }
    }

    public ArrayList<String> concatNamesInArray(String[] array) {
        ArrayList<String> lowerCaseConcat = new ArrayList<>();
        for (String item : array) {
            String nameToAdd = concatNameLowerCase(item);
            lowerCaseConcat.add(nameToAdd);
        }
        return lowerCaseConcat;
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
        } 
        
        return lowerCaseName; 

    }
}
