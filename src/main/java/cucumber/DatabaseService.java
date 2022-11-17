package cucumber;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DatabaseService {

    private final PersonRepository personRepository;

    public ArrayList<String> getConcatNamesInDB(){
        List<Person> persons = findAllFromDB();
        ArrayList<String> namesInDB = new ArrayList<>();
        for(int i = 0; i < persons.size(); i ++){
            String firstName = persons.get(i).getFirstName();
            String surname = persons.get(i).getSurname();
            String fullName = firstName.toLowerCase() + surname.toLowerCase();
            namesInDB.add(fullName); // Todo look at for each
            // Todo look at SQL Query
        }
        return namesInDB;
    }

    public List<Person> findAllFromDB(){
        return personRepository.findAll();
    }


}
