package pe.gyarlequej.microservices.app.users.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gyarlequej.microservices.app.users.client.CourseFeignClient;
import pe.gyarlequej.microservices.app.users.models.repository.StudentRepository;
import pe.gyarlequej.microservices.app.users.services.StudentService;
import pe.gyarlequej.microservices.commons.services.impl.CommonServiceImpl;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> 
	implements StudentService {
	
	@Autowired
	private CourseFeignClient courseClient;

	@Override
	@Transactional(readOnly = true)
	public List<Student> findByNameOrLastName(String search) {
		return this.repository.findByNameOrLastName(search.toUpperCase());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Student> findAllById(List<Long> ids) {
		return this.repository.findAllById(ids);
	}

	@Override
	public void deleteCourseStudentByStudentId(Long studentId) {
		this.courseClient.deleteCourseStudentByStudentId(studentId);		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		this.deleteCourseStudentByStudentId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Student> findAll() {
		return this.repository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findAll(Pageable pageable) {
		return this.repository.findAllByOrderByIdAsc(pageable);
	}	

}
