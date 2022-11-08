package cucumber;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

//@Validated

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DatabaseService databaseService;
    private static final Logger LOG = getLogger(DemoController.class.getName());
    String[] nameData = {"Jane Doe", "Joe Bloggs"};

//    @Autowired
    private PersonRepository personRepository;

//    private final PersonRepository personRepository;

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

    @GetMapping("DB")
    public Map<String, String> DBTest(){
//        isNameInDB(name.toLowerCase());
        Map<String, String> response = new HashMap<>();
        response.put("Match_found", personRepository.returnNameConcatIfMatch("Joe", "Bloggs").toString());
        LOG.info("Returning {}", response); //investigate this bit?
        return response;
    }


    @GetMapping("NameMatcher/{name}")
    public Map<String, String> nameMatcher(@PathVariable("name") String name){
        isNameInDB(name.toLowerCase());
        Map<String, String> response = new HashMap<>();
        response.put("Firstname_lastname", name);
        response.put("Match_found", isNameInDB(name.toLowerCase()));
        LOG.info("Returning {}", response); //investigate this bit?
        return response;
    }

    public String isNameInDB(String name) {
        ArrayList<String> arrayToCheck = databaseService.getConcatNamesInDB();
        if(arrayToCheck.contains(concatNameLowerCase(name))){
            return "MATCHED";
        } else {
            return "NOT_MATCHED";
        }
        // look at enum
    }

//    public ArrayList<String> getConcatNamesInDB(){
//        List<Person> persons = personRepository.findAll();
//        ArrayList<String> namesInDB = new ArrayList<>();
//        for(int i = 0; i < persons.size(); i ++){
//            String firstName = persons.get(i).getFirstName();
//            String surname = persons.get(i).getSurname();
//            String fullName = firstName.toLowerCase() + surname.toLowerCase();
//            namesInDB.add(fullName);
//        }
//        return namesInDB;
//    }

    public ArrayList<String> doNameConcat(){
        return databaseService.getConcatNamesInDB();
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


    public ArrayList<String> concatNamesInArray(String[] array) { // NO LONGER IN USE
        ArrayList<String> lowerCaseConcat = new ArrayList<>();
        for (String item : array) {
            String nameToAdd = concatNameLowerCase(item);
            lowerCaseConcat.add(nameToAdd);
        }
        return lowerCaseConcat;
    }
}
