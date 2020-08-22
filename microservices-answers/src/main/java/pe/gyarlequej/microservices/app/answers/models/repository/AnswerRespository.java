package pe.gyarlequej.microservices.app.answers.models.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import pe.gyarlequej.microservices.app.answers.models.entity.Answer;

public interface AnswerRespository extends MongoRepository<Answer, String> {
	
	@Query("{ 'studentId': ?0, 'questionId': { $in : ?1 } }")
	public Iterable<Answer> findAnswerByStudentByQuestionIds(Long studentId, Iterable<Long> questionIds);
	
	@Query("{ 'studentId': ?0 }")
	public Iterable<Answer> findByStudentId(Long studentId);
	
	@Query("{ 'studentId': ?0, 'question.exam.id': ?1 }")
	public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId);
	
	@Query(value = "{ 'studentId': ?0 }", fields = "{ 'question.exam.id': 1 }")
	public Iterable<Answer> findExamIdsWithAnswersByStudent(Long studentId);

	// @Query("select a from Answer a join fetch a.question q join fetch q.exam e where a.studentId= ?1 and e.id= ?2")
	// public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId);
	
	// @Query("select e.id from Answer a join a.question q join q.exam e where a.studentId = ?1 group by e.id ")
	// public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId);
	
	
	
}
