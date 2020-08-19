package pe.gyarlequej.microservices.app.exams.services;

import java.util.List;

import pe.gyarlequej.microservices.commons.exams.models.entity.Exam;
import pe.gyarlequej.microservices.commons.exams.models.entity.Subject;
import pe.gyarlequej.microservices.commons.services.CommonService;

public interface ExamService extends CommonService<Exam> {
	
	public List<Exam> findByNameLike(String term);
	
	public Iterable<Subject> findAllSubjects();

}
