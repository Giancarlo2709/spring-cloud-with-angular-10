package pe.gyarlequej.microservices.app.users.services;

import java.util.List;

import pe.gyarlequej.microservices.commons.services.CommonService;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

public interface StudentService extends CommonService<Student> {
	
	public List<Student> findByNameOrLastName(String search);
	
	public Iterable<Student> findAllById(List<Long> ids);
	
	public void deleteCourseStudentByStudentId(Long studentId);

}
