package pe.gyarlequej.microservices.app.courses.services;

import java.util.List;

import pe.gyarlequej.microservices.app.courses.models.entity.Course;
import pe.gyarlequej.microservices.commons.services.CommonService;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

public interface CourseService extends CommonService<Course> {
	
	public Course findCourseByStudentId(Long id);
	
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId);
	
	public Iterable<Student> getStudentsByCourse(List<Long> ids);
	
	public void deleteCourseStudentById(Long studentId);

}
