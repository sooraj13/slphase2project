package com.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int teacherId;

	@Column
	private String teacherName;


	@ManyToMany(mappedBy = "teachers")
	private Collection<Subject> subjects= new ArrayList();


	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public Collection<Subject> getSubjects() { 
		return subjects;
	}

	public void setSubjects(Collection<Subject> subjects) {
		this.subjects =
				subjects; 
	}


	public Teacher(String teacherName) { 
		super(); 
		this.teacherName = teacherName;
	}
	


	public Teacher() { 
		super(); 
	}






}
