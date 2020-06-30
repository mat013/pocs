package dk.emstar.test.spring.kafkapostgres;

import dk.emstar.test.spring.kafkapostgres.jpa.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}