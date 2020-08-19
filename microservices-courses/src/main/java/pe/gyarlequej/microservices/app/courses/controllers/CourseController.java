package pe.gyarlequej.microservices.app.courses.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gyarlequej.microservices.app.courses.models.entity.Course;
import pe.gyarlequej.microservices.app.courses.services.CourseService;
import pe.gyarlequej.microservices.commons.controllers.CommonController;
import pe.gyarlequej.microservices.commons.exams.models.entity.Exam;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@RestController
public class CourseController extends CommonController<Course, CourseService> {
	
	@Value("${config.balancer.test}")
	private String balancerTest;
	
	@GetMapping("/balancer-test")
	public ResponseEntity<?> balancerTest() {
		Map<String, Object> response = new HashMap<>();
		response.put("balancer", balancerTest);
		response.put("courses", this.service.findAll());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.validate(result);
		}
		
		Optional<Course> courseOptional = this.service.findById(id);
		
		if(!courseOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = courseOptional.get();
		courseDb.setName(course.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@PutMapping("/{id}/assign-students")
	public ResponseEntity<?> assignStudents(@RequestBody List<Student> students, @PathVariable Long id) {
		Optional<Course> courseOptional = this.service.findById(id);
		if(!courseOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = courseOptional.get();
		students.forEach(courseDb::addStudent);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@PutMapping("/{id}/delete-student")
	public ResponseEntity<?> deleteStudent(@RequestBody Student student, @PathVariable Long id) {
		Optional<Course> courseOptional = this.service.findById(id);
		if(!courseOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = courseOptional.get();
		courseDb.removeStudent(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<?> findCourseByStudentId(@PathVariable Long id) {		
		Course course = this.service.findCourseByStudentId(id);
		if (Objects.nonNull(course)) {
			List<Long> examIds = (List<Long>) this.service.findExamsIdsWithAnswersByStudent(id);
			
			List<Exam> exams = course.getExams().stream()
					.map(e -> {
						if(examIds.contains(e.getId())) {
							e.setAnswered(true);
						}
						return e;
					})
					.collect(Collectors.toList());
			
			course.setExams(exams);
		}
		return ResponseEntity.ok(course);
	}
	
	@PutMapping("/{id}/assign-exams")
	public ResponseEntity<?> assignExams(@RequestBody List<Exam> exams, @PathVariable Long id) {
		Optional<Course> courseOptional = this.service.findById(id);
		if(!courseOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = courseOptional.get();
		exams.forEach(courseDb::addExam);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}
	
	@PutMapping("/{id}/delete-exam")
	public ResponseEntity<?> deleteStudent(@RequestBody Exam exam, @PathVariable Long id) {
		Optional<Course> courseOptional = this.service.findById(id);
		if(!courseOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Course courseDb = courseOptional.get();
		courseDb.removeExam(exam);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDb));
	}

}
