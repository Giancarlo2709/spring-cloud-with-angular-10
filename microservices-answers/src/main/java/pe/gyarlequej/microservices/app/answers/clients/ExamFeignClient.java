package pe.gyarlequej.microservices.app.answers.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pe.gyarlequej.microservices.commons.exams.models.entity.Exam;

@FeignClient(name = "microservice-exams")
public interface ExamFeignClient {
	
	@GetMapping("/{id}")
	public Exam findExamById(@PathVariable Long id);
	
	@GetMapping("/answereds/questions")
	public List<Long> getExamIdsByQuestionsIdAnswereds(@RequestParam List<Long> questionIds);

}
