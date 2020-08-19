package pe.gyarlequej.microservices.app.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"pe.gyarlequej.microservices.commons.exams.models.entity"})
public class MicroservicesExamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesExamsApplication.class, args);
	}

}
