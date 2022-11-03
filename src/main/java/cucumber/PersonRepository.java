package cucumber;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT FIRST_NAME, SURNAME\n" +
            "FROM PEOPLE \n" +
            "WHERE FIRST_NAME = '%' AND SURNAME = '%'; ", nativeQuery = true)
    Collection<String> returnNamesIfMatch(@Param("first_name") String first_name, @Param("surname") String surname);

    @Query(value = "SELECT LOWER(CONCAT(FIRST_NAME, SURNAME))\n" +
            "FROM PEOPLE \n" +
            "WHERE FIRST_NAME = '%' AND SURNAME = '%'; ", nativeQuery = true)
    String returnNameConcatIfMatch(@Param("first_name") String first_name, @Param("surname") String surname);

}
