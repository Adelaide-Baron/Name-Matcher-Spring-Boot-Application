package cucumber;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT firstName, surname\n" +
            "FROM persons \n" +
            "WHERE firstName = :firstName AND surname = :surname ; ", nativeQuery = true)
    Person returnNamesIfMatch(@Param("firstName") String firstName, @Param("surname") String surname);

    @Query(value = "SELECT LOWER(CONCAT(FIRST_NAME, SURNAME))\n" +
            "FROM persons \n" +
            "WHERE firstName = '%' AND surname = '%'; ", nativeQuery = true)
    String returnNameConcatIfMatch(@Param("firstName") String first_name, @Param("surname") String surname);

}
