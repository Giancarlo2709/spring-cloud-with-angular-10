package pe.gyarlequej.microservices.app.answers.services.impl;

import java.util.List;
import java.util.stream.Collectors;

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

	/*
	@Autowired
	private ExamFeignClient examClient;
	*/

	@Override
	@Transactional
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		return this.answerRepository.saveAll(answers);
	}

	@Override
	public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId) {

		/*Exam exam = this.examClient.findExamById(examId);

		List<Question> questions = exam.getQuestions();

		List<Long> questionIds = questions.stream().map(q -> q.getId()).collect(Collectors.toList());

		List<Answer> answers = (List<Answer>) this.answerRepository.findAnswerByStudentByQuestionIds(studentId,
				questionIds);
		return answers.stream().map(a -> {
			questions.forEach(q -> {
				if (q.getId().equals(a.getQuestionId())) {
					a.setQuestion(q);
				}
			});
			return a;
		}).collect(Collectors.toList());*/
		return this.answerRepository.findAnswerByStudentByExam(studentId, examId);
	}

	@Override
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId) {
		/*List<Answer> answersStudent = (List<Answer>) this.answerRepository.findByStudentId(studentId);
		List<Long> examIds = Collections.emptyList();
		
		if (!answersStudent.isEmpty()) {
			List<Long> questionIds = answersStudent.stream().map(a -> a.getQuestionId()).collect(Collectors.toList());
			examIds = this.examClient.getExamIdsByQuestionsIdAnswereds(questionIds);
		}

		return examIds;*/
		List<Answer> answersStudent = (List<Answer>) this.answerRepository.findExamIdsWithAnswersByStudent(studentId);
		return answersStudent.stream()
				.map(a -> a.getQuestion().getExam().getId())
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<Answer> findByStudentId(Long studentId) {
		return this.answerRepository.findByStudentId(studentId);
	}

}
