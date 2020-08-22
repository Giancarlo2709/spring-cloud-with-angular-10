package pe.gyarlequej.microservices.app.answers.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gyarlequej.microservices.commons.exams.models.entity.Question;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "answers")
public class Answer {
	
	@Id
	private String id;
	
	private String text;
	
	private Student student;
	
	private Long studentId;
	
	private Question question;
	
	private Long questionId;

}
