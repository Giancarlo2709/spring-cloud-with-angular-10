package pe.gyarlequej.microservices.app.users.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gyarlequej.microservices.app.users.models.repository.StudentRepository;
import pe.gyarlequej.microservices.app.users.services.StudentService;
import pe.gyarlequej.microservices.commons.services.impl.CommonServiceImpl;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> 
	implements StudentService {

	@Override
	@Transactional(readOnly = true)
	public List<Student> findByNameOrLastName(String search) {
		return this.repository.findByNameOrLastName(search);
	}
	

}
