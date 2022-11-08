package cucumber;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DatabaseService {

//    @Autowired
    private final PersonRepository personRepository;

    public ArrayList<String> getConcatNamesInDB(){
        List<Person> persons = personRepository.findAll();
        ArrayList<String> namesInDB = new ArrayList<>();
        for(int i = 0; i < persons.size(); i ++){
            String firstName = persons.get(i).getFirstName();
            String surname = persons.get(i).getSurname();
            String fullName = firstName.toLowerCase() + surname.toLowerCase();
            namesInDB.add(fullName);
        }
        return namesInDB;
    }


}
