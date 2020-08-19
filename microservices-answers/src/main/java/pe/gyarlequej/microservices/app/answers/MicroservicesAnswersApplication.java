package pe.gyarlequej.microservices.app.answers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages = {
		"pe.gyarlequej.microservices.app.answers.models.entity",
		"pe.gyarlequej.microservices.commons.students.models.entity",
		"pe.gyarlequej.microservices.commons.exams.models.entity"
})
public class MicroservicesAnswersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAnswersApplication.class, args);
	}

}
