package cucumber;

import javax.persistence.*;

@Entity(name = "Person")
@Table(name="PEOPLE", schema="MINI PROJECT")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME", length = 50, nullable = false, unique = false)
    private String first_name;

    @Column(name = "LAST_NAME", length = 50, nullable = false, unique = false)
    private String last_name;


    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Person() {
    }

    public String concatName(){
        String fullName = first_name.toLowerCase() + last_name.toLowerCase();
        return fullName;
    }
}
