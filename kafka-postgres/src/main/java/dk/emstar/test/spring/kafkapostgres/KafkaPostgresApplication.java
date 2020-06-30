package dk.emstar.test.spring.kafkapostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dk.emstar.test.spring.kafkapostgres")
public class KafkaPostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPostgresApplication.class, args);
	}

}
