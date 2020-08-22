package pe.gyarlequej.microservices.app.courses.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gyarlequej.microservices.app.courses.clients.AnswerFeignClient;
import pe.gyarlequej.microservices.app.courses.clients.StudentFeignClient;
import pe.gyarlequej.microservices.app.courses.models.entity.Course;
import pe.gyarlequej.microservices.app.courses.models.repository.CourseRepository;
import pe.gyarlequej.microservices.app.courses.services.CourseService;
import pe.gyarlequej.microservices.commons.services.impl.CommonServiceImpl;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

	@Autowired
	private AnswerFeignClient client;
	
	@Autowired
	private StudentFeignClient studentClient;
	
	@Override
	@Transactional(readOnly = true)
	public Course findCourseByStudentId(Long id) {
		return this.repository.findCourseByStudentId(id);
	}

	@Override
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId) {
		return this.client.findExamsIdsWithAnswersByStudent(studentId);
	}

	@Override
	public Iterable<Student> getStudentsByCourse(List<Long> ids) {
		return this.studentClient.getStudentsByCourse(ids);
	}

	@Override
	@Transactional
	public void deleteCourseStudentById(Long studentId) {
		this.repository.deleteCourseStudentById(studentId);
	}

}
