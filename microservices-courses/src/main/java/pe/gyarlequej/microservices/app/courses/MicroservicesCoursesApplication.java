package pe.gyarlequej.microservices.app.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages = { 
	"pe.gyarlequej.microservices.app.courses.models.entity",
	"pe.gyarlequej.microservices.commons.exams.models.entity"
})
public class MicroservicesCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCoursesApplication.class, args);
	}

}
