package com.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ClassTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int classId;


	@Column
	private String ClassName;

	@OneToMany(mappedBy = "classTable",fetch = FetchType.EAGER)
	private Set<Student> students= new HashSet();

	
	@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL,})
	@JoinTable(name = "Class_Subject",joinColumns = {@JoinColumn(name = "classId")},inverseJoinColumns = {@JoinColumn(name = "subjectId")})
	private Set<Subject> subjects= new HashSet();
	 


	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
	}




	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public ClassTable(String className) {
		super();
		ClassName = className;
	}
	
	public ClassTable() {
		super();
	}



}
