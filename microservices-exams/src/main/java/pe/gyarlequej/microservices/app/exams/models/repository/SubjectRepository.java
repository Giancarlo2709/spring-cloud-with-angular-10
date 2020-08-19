package pe.gyarlequej.microservices.app.exams.models.repository;

import org.springframework.data.repository.CrudRepository;

import pe.gyarlequej.microservices.commons.exams.models.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

}
