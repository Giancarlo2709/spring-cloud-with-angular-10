package pe.gyarlequej.microservices.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesZuulApplication.class, args);
	}

}
