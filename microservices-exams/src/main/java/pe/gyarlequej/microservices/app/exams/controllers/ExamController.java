package pe.gyarlequej.microservices.app.exams.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gyarlequej.microservices.app.exams.services.ExamService;
import pe.gyarlequej.microservices.commons.controllers.CommonController;
import pe.gyarlequej.microservices.commons.exams.models.entity.Exam;
import pe.gyarlequej.microservices.commons.exams.models.entity.Question;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {
	
	@GetMapping("/answereds/questions")
	public ResponseEntity<?> getExamIdsByQuestionsIdAnswereds(@RequestParam List<Long> questionIds) {
		return ResponseEntity.ok(this.service.findExamsIdsWithAnswersByQuestionIds(questionIds));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.validate(result);
		}
		
		Optional<Exam> examOptional = this.service.findById(id);
		
		if (!examOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Exam examDb = examOptional.get();
		examDb.setName(exam.getName());
		
		List<Question> deletes =  examDb.getQuestions()
			.stream()
			.filter(qdb -> !exam.getQuestions().contains(qdb))
			.collect(Collectors.toList());
		
		deletes.forEach(examDb::removeQuestion);
		
		examDb.setQuestions(exam.getQuestions());
		examDb.setSubjectChild(exam.getSubjectChild());
		examDb.setSubjectParent(exam.getSubjectParent());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(examDb));		
	}
	
	@GetMapping("/filters/{term}")
	public ResponseEntity<?> filters(@PathVariable String term) {
		return ResponseEntity.ok(this.service.findByNameLike(term));
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<?> findAllSubjects() {
		return ResponseEntity.ok(this.service.findAllSubjects());
	}

}
