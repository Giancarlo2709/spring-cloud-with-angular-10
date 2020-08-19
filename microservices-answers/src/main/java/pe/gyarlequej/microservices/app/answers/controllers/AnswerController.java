package pe.gyarlequej.microservices.app.answers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gyarlequej.microservices.app.answers.models.entity.Answer;
import pe.gyarlequej.microservices.app.answers.services.AnswerService;

@RestController
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@PostMapping
	public ResponseEntity<?> saveAll(@RequestBody Iterable<Answer> answers) {
		Iterable<Answer> answersDb = this.answerService.saveAll(answers);
		return ResponseEntity.status(HttpStatus.CREATED).body(answersDb);
	}
	
	@GetMapping("/students/{studentId}/exams/{examId}")
	public ResponseEntity<?> findAnswersByStudentIdAndExamId(@PathVariable Long studentId, @PathVariable Long examId) {
		Iterable<Answer> answers = this.answerService.findAnswerByStudentByExam(studentId, examId);
		return ResponseEntity.ok(answers);
	}
	
	@GetMapping("/students/{studentId}/exams-answereds")
	public ResponseEntity<?> findExamsIdsWithAnswersByStudent(@PathVariable Long studentId) {
		Iterable<Long> examIds = this.answerService.findExamsIdsWithAnswersByStudent(studentId);
		return ResponseEntity.ok(examIds);
	}

}
