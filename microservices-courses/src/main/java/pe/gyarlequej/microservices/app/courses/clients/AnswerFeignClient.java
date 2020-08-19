package pe.gyarlequej.microservices.app.courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-answers")
public interface AnswerFeignClient {
	
	@GetMapping("/students/{studentId}/exams-answereds")
	public Iterable<Long> findExamsIdsWithAnswersByStudent(@PathVariable Long studentId);
	
}
