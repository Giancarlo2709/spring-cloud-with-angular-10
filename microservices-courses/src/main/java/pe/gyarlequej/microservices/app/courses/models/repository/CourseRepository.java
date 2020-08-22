package pe.gyarlequej.microservices.app.courses.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pe.gyarlequej.microservices.app.courses.models.entity.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

	@Query("select c from Course c join fetch c.courseStudents s where s.studentId=?1")
	public Course findCourseByStudentId(Long id);
	
	@Modifying
	@Query("delete from CourseStudent cs where cs.studentId =?1")
	public void deleteCourseStudentById(Long studentId);
	
}
