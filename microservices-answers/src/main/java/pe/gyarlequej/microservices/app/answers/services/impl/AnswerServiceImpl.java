package pe.gyarlequej.microservices.app.answers.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gyarlequej.microservices.app.answers.models.entity.Answer;
import pe.gyarlequej.microservices.app.answers.models.repository.AnswerRespository;
import pe.gyarlequej.microservices.app.answers.services.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRespository answerRepository;

	@Override
	@Transactional
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		return this.answerRepository.saveAll(answers);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId) {
		return this.answerRepository.findAnswerByStudentByExam(studentId, examId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId) {
		return this.answerRepository.findExamsIdsWithAnswersByStudent(studentId);
	}

}
