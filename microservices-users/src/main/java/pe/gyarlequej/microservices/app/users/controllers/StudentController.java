package pe.gyarlequej.microservices.app.users.controllers;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gyarlequej.microservices.app.users.services.StudentService;
import pe.gyarlequej.microservices.commons.controllers.CommonController;
import pe.gyarlequej.microservices.commons.students.models.entity.Student;

@RestController
public class StudentController extends CommonController<Student, StudentService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Student student, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validate(result);
		}

		Optional<Student> studentOptional = this.service.findById(id);

		if (!studentOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Student studentDb = studentOptional.get();
		studentDb.setName(student.getName());
		studentDb.setLastName(student.getLastName());
		studentDb.setEmail(student.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(studentDb));
	}

	@GetMapping("/filters/{search}")
	public ResponseEntity<?> filter(@PathVariable String search) {
		return ResponseEntity.ok(this.service.findByNameOrLastName(search));
	}

	@PostMapping("/photos")
	public ResponseEntity<?> createWithPhoto(@Valid Student student, BindingResult result,
			@RequestParam MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			student.setPhoto(file.getBytes());
		}
		return super.create(student, result);
	}

	@PutMapping("/photos/{id}")
	public ResponseEntity<?> updateWithPhoto(@Valid Student student, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile file) throws IOException {

		if (result.hasErrors()) {
			return this.validate(result);
		}

		Optional<Student> studentOptional = this.service.findById(id);

		if (!studentOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Student studentDb = studentOptional.get();
		studentDb.setName(student.getName());
		studentDb.setLastName(student.getLastName());
		studentDb.setEmail(student.getEmail());
		if (!file.isEmpty()) {
			studentDb.setPhoto(file.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(studentDb));
	}

	@GetMapping("/photos/upload/{id}")
	public ResponseEntity<?> viewPhoto(@PathVariable Long id) {
		Optional<Student> studentOptional = this.service.findById(id);

		if (!studentOptional.isPresent() || Objects.isNull(studentOptional.get().getPhoto())) {
			return ResponseEntity.notFound().build();
		}
		
		Resource image = new ByteArrayResource(studentOptional.get().getPhoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(image);		
	}

}
