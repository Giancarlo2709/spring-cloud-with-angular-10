package pe.gyarlequej.microservices.app.users.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pe.gyarlequej.microservices.commons.students.models.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	@Query("select s from Student s where upper(s.name) like %?1% or upper(s.lastName) like %?1%")
	public List<Student> findByNameOrLastName(String search);
	

}
