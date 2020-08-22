package pe.gyarlequej.microservices.app.answers.services;

import pe.gyarlequej.microservices.app.answers.models.entity.Answer;

public interface AnswerService {
	
	public Iterable<Answer> saveAll(Iterable<Answer> answers);
	
	public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId);
	
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId);
	
	public Iterable<Answer> findByStudentId(Long studentId);

}
