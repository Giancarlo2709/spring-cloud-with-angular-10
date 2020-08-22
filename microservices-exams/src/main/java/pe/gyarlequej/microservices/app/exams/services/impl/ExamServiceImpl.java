package pe.gyarlequej.microservices.app.exams.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gyarlequej.microservices.app.exams.models.repository.ExamRepository;
import pe.gyarlequej.microservices.app.exams.models.repository.SubjectRepository;
import pe.gyarlequej.microservices.app.exams.services.ExamService;
import pe.gyarlequej.microservices.commons.exams.models.entity.Exam;
import pe.gyarlequej.microservices.commons.exams.models.entity.Subject;
import pe.gyarlequej.microservices.commons.services.impl.CommonServiceImpl;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Exam> findByNameLike(String term) {
		return this.repository.findByNameLike(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Subject> findAllSubjects() {
		return this.subjectRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamsIdsWithAnswersByQuestionIds(Iterable<Long> questionIds) {
		return this.repository.findExamsIdsWithAnswersByQuestionIds(questionIds);
	}


}
