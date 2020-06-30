package dk.emstar.test.spring.kafkapostgres.jpa;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Audited
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "entitybseq")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    private Long id;

    @Version
    public Integer version;

    private String firstName;
    private String lastName;

    @CreationTimestamp
    private ZonedDateTime creationDatetime;

    @UpdateTimestamp
    private ZonedDateTime updateDatetime;

    protected Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}