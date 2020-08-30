package pe.gyarlequej.microservices.commons.exams.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonIgnoreProperties(value = { "childs", "handler", "hibernateLazyInitializer" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Subject parent;
	
	@JsonIgnoreProperties(value = { "parent", "handler", "hibernateLazyInitializer" }, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Subject> childs;
	
	public Subject() {
		this.childs = new ArrayList<>();
	}
	

}
