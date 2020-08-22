package pe.gyarlequej.microservices.app.courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@FeignClient(name = "microservice-users")
public interface StudentFeignClient {
	
	@GetMapping("/student-by-course")
	public Iterable<Student> getStudentsByCourse(@RequestParam Iterable<Long> ids);

}
