package pe.gyarlequej.microservices.app.courses.services;

import pe.gyarlequej.microservices.app.courses.models.entity.Course;
import pe.gyarlequej.microservices.commons.services.CommonService;

public interface CourseService extends CommonService<Course> {
	
	public Course findCourseByStudentId(Long id);
	
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId);

}
