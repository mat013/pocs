package dk.emstar.test.spring.kafkapostgres.controller;

import dk.emstar.test.spring.kafkapostgres.CustomerRepository;
import dk.emstar.test.spring.kafkapostgres.kafka.KafkaProducer;
import dk.emstar.test.spring.kafkapostgres.jpa.Customer;
import dk.emstar.test.spring.kafkapostgres.rest.Greeting;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class GreetingController {

    private static final Logger logger = getLogger(GreetingController.class);
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final CustomerRepository customerRepository;
    private final KafkaProducer kafkaProducer;

    public GreetingController(CustomerRepository customerRepository, KafkaProducer kafkaProducer) {
        this.customerRepository = customerRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
       return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/kafka")
    public String kafka(@RequestParam(value = "name", defaultValue = "World") String name) {
        kafkaProducer.sendMessage(name);
        return name;
    }

    @GetMapping("/repo")
    public String repo(@RequestParam(value = "name", defaultValue = "World") String name) {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        logger.info("Customers found with findAll():");
        logger.info("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            logger.info(customer.toString());
        }
        logger.info("");

        // fetch customers by last name
        logger.info("Customer found with findByLastName('Bauer'):");
        logger.info("--------------------------------------------");
        customerRepository.findByLastName("Bauer").forEach(bauer -> {
            logger.info(bauer.toString());
        });
        // for (Customer bauer : repository.findByLastName("Bauer")) {
        //  log.info(bauer.toString());
        // }
        logger.info("");

        return name;
    }
}