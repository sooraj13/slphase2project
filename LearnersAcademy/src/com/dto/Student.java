package com.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table
public class Student {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;
	
	@Column
	private String studentName;
	

	public Student() {
		super();
	}





	@ManyToOne
	@JoinColumn(name="classId")
	private ClassTable classTable;
	


	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public ClassTable getClassTable() {
		return classTable;
	}

	public void setClassTable(ClassTable classTable) {
		this.classTable = classTable;
	}
	




	public Student(String studentName) {
		super();
		this.studentName = studentName;
	}


	
	
	
	
}
