package pe.gyarlequej.microservices.app.courses.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pe.gyarlequej.microservices.commons.exams.models.entity.Exam;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@JsonIgnoreProperties(value = {"course"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course", 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CourseStudent> courseStudents;
	
	// @OneToMany(fetch = FetchType.LAZY)
	@Transient
	List<Student> students;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Exam> exams;
	
	public Course() {
		this.students = new ArrayList<>();
		this.exams = new ArrayList<>();
		this.courseStudents = new ArrayList<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	
	public void addExam(Exam exam) {
		this.exams.add(exam);
	}
	
	public void removeExam(Exam exam) {
		this.exams.remove(exam);
	}
	
	public void addCourseStudent(CourseStudent courseStudent) {
		this.courseStudents.add(courseStudent);
	}
	
	public void removeCourseStudent(CourseStudent courseStudent) {
		this.courseStudents.remove(courseStudent);
	}

}
