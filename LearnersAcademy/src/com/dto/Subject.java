package com.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subjectId;

	public Subject() {
		super();
	}
	@Column
	private String subjectName;


	@ManyToMany(mappedBy = "subjects")
	private Collection<ClassTable> classes= new ArrayList();


	@ManyToMany(cascade = CascadeType.ALL) 
	@JoinTable(name = "Subject_Teacher",joinColumns = {@JoinColumn(name = "subjectId")},inverseJoinColumns = {@JoinColumn(name = "teacherId")})
	private Collection<Teacher> teachers = new ArrayList();



	public Subject( String subjectName) {
		super();

		this.subjectName = subjectName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public Collection<Teacher> getTeachers() { 
		return teachers;
	}

	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers; 
	}

	public Collection<ClassTable> getClasses() { return classes; } public void
	setClasses(Collection<ClassTable> classes) { this.classes = classes; } 



}
