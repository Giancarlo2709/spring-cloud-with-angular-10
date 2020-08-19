package pe.gyarlequej.microservices.app.courses.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gyarlequej.microservices.app.courses.clients.AnswerFeignClient;
import pe.gyarlequej.microservices.app.courses.models.entity.Course;
import pe.gyarlequej.microservices.app.courses.models.repository.CourseRepository;
import pe.gyarlequej.microservices.app.courses.services.CourseService;
import pe.gyarlequej.microservices.commons.services.impl.CommonServiceImpl;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

	@Autowired
	private AnswerFeignClient client;
	
	@Override
	@Transactional(readOnly = true)
	public Course findCourseByStudentId(Long id) {
		return this.repository.findCourseByStudentId(id);
	}

	@Override
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId) {
		return this.client.findExamsIdsWithAnswersByStudent(studentId);
	}

}
