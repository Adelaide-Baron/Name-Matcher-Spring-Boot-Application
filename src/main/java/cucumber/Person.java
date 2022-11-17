package cucumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Todo  delete PEOPLE table
// Todo look at SQL query in person entity
import javax.persistence.*;

@Entity(name = "Person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

}
