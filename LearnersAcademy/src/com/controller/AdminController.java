package com.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.dto.ClassTable;
import com.dto.Student;
import com.dto.Subject;
import com.dto.Teacher;


public class AdminController {

	public AdminController() {
		super();
	}

	public Session createSession(){

		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(com.dto.Subject.class);
		configuration.addAnnotatedClass(com.dto.Student.class);
		configuration.addAnnotatedClass(com.dto.ClassTable.class);
		configuration.addAnnotatedClass(com.dto.Teacher.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session session = factory.openSession();
		return session;

	}


	public boolean  addSubject(String subjectName) {

		try {
			Session session = createSession();
			Transaction transaction = session.beginTransaction();
			
			//HQL queries
			List<String> validateList = null;
			Query query = session.createQuery("Select subjectName from Subject where subjectName=:subjectName");
			query.setString("subjectName", subjectName);
			validateList = query.list();
			if(validateList!=null && !validateList.isEmpty()) {
				transaction.commit();
				session.close();
				return false;
			}
			else {
				Subject newSubject = new Subject(subjectName);
				session.save(newSubject);
				transaction.commit();
				session.close();
				return true;
			}
			
			
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean  addTeacher(String teacherName) {

		try {
			Session session = createSession();
			Transaction transaction = session.beginTransaction();
			Teacher newTeacher = new Teacher(teacherName);
			session.save(newTeacher);
			transaction.commit();
			session.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean  addClass(String className) {
		try {
			Session session = createSession();
			Transaction transaction = session.beginTransaction();

			//HQL queries
			List<String> validateList = null;
			Query query = session.createQuery("Select ClassName from ClassTable where ClassName=:className");
			query.setString("className", className);
			validateList = query.list();
			if(validateList!=null && !validateList.isEmpty()) {
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return false;
			}
			else {
				ClassTable classTable = new ClassTable(className);
				session.save(classTable);
				System.out.println("after save");
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return true;
			}

		}
		catch(Exception e) {
			System.out.println("exception" + e);
			return false;
		}
	}

	public boolean  addStudent(String studentName) {
		try {
			System.out.println("in addStudent");
			Session session = createSession();
			Transaction transaction = session.beginTransaction();
			Student newStudent = new Student(studentName);
			session.save(newStudent);
			System.out.println("after save");
			transaction.commit();
			System.out.println("after commit");
			session.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}


	public List<String> fetchStudentDetails() {
		try {
			Session session = createSession();
			Transaction transaction = session.beginTransaction();
			List<String> studentList = null;

			//HQL queries
			Query query = session.createQuery("Select studentName from Student");
			studentList = query.list();
			transaction.commit();
			System.out.println("after commit");
			session.close();
			return studentList;

		}
		catch(Exception e) {
			return null;
		}
	}


	public List<ClassTable> fetchClassDetails(String className) {
		try {
			Session session = createSession();
			Transaction transaction = session.beginTransaction();
			List<ClassTable> classList = null;

			//HQL queries
			System.out.println("hql begin");
			Query query = session.createQuery("from ClassTable where ClassName=:className");
			query.setString("className", className);
			classList = query.list();
			System.out.println("classList " + classList);
			transaction.commit();
			System.out.println("after commit");
			session.close();
			return classList;

		}
		catch(Exception e) {
			return null;
		}
	}
	

	public boolean assignClass(String className, String subjectName) {
		try {
			System.out.println("in assignClass");
			Session session = createSession();
			Transaction transaction = session.beginTransaction();

			//HQL queries
			List<Integer> validateList1 = null;
			Query query1 = session.createQuery("Select classId from ClassTable where ClassName=:className");
			query1.setString("className", className);
			validateList1 = query1.list();
			System.out.println("list1 " + validateList1);
			
			//HQL queries
			List<Integer> validateList2 = null;
			Query query2 = session.createQuery("Select subjectId from Subject where subjectName=:subjectName");
			query2.setString("subjectName", subjectName);
			validateList2 = query2.list();
			System.out.println("list1 " + validateList2);
			
			if(validateList1!=null && !validateList1.isEmpty() && validateList2!=null && !validateList2.isEmpty()) {
				System.out.println("validateList1.get(0)-" + validateList1.get(0));
				ClassTable classt = (ClassTable)session.get(ClassTable.class, validateList1.get(0));
				System.out.println("validateList2.get(0)-" + validateList2.get(0));
				Subject sub = (Subject)session.get(Subject.class,  validateList2.get(0));
				classt.getSubjects().add(sub);
				session.update(classt);
				System.out.println("after save");
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return true;
			}
			else {
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return false;
			}

		}
		catch(Exception e) {
			System.out.println("exception " + e);
			return false;
		}
	}
	
	

	public boolean assignStudent(String className, String studentName) {
		try {
			System.out.println("in assignStudent");
			Session session = createSession();
			Transaction transaction = session.beginTransaction();

			//HQL queries
			List<Integer> validateList1 = null;
			Query query1 = session.createQuery("Select classId from ClassTable where ClassName=:className");
			query1.setString("className", className);
			validateList1 = query1.list();
			System.out.println("list1 " + validateList1);
			
			//HQL queries
			List<Integer> validateList2 = null;
			System.out.println("studentName " + studentName );
			Query query2 = session.createQuery("Select studentId from Student where studentName=:studentName");
			query2.setString("studentName", studentName);
			validateList2 = query2.list();
			System.out.println("list2 " + validateList2);
			
			if(validateList1!=null && !validateList1.isEmpty() && validateList2!=null && !validateList2.isEmpty()) {
				System.out.println("validateList1.get(0)-" + validateList1.get(0));
				ClassTable classt = (ClassTable)session.get(ClassTable.class, validateList1.get(0));
				System.out.println("validateList2.get(0)-" + validateList2.get(0));
				Student student = (Student)session.get(Student.class,  validateList2.get(0));
				student.setClassTable(classt);
				session.update(student);
				System.out.println("after save");
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return true;
			}
			else {
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return false;
			}

		}
		catch(Exception e) {
			System.out.println("exception " + e);
			return false;
		}

	}
	
	

	public boolean assignTeacher(String teacherName, String subjectName) {
		try {
			System.out.println("in assignTeacher");
			Session session = createSession();
			Transaction transaction = session.beginTransaction();

			//HQL queries
			List<Integer> validateList1 = null;
			Query query1 = session.createQuery("Select teacherId from Teacher where teacherName=:teacherName");
			query1.setString("teacherName", teacherName);
			validateList1 = query1.list();
			System.out.println("list1 " + validateList1);
			
			//HQL queries
			List<Integer> validateList2 = null;
			Query query2 = session.createQuery("Select subjectId from Subject where subjectName=:subjectName");
			query2.setString("subjectName", subjectName);
			validateList2 = query2.list();
			System.out.println("list1 " + validateList2);
			
			if(validateList1!=null && !validateList1.isEmpty() && validateList2!=null && !validateList2.isEmpty()) {
				System.out.println("validateList1.get(0)-" + validateList1.get(0));
				Teacher teacher = (Teacher)session.get(Teacher.class, validateList1.get(0));
				System.out.println("validateList2.get(0)-" + validateList2.get(0));
				Subject sub = (Subject)session.get(Subject.class,  validateList2.get(0));
				teacher.getSubjects().add(sub);
				session.update(teacher);
				System.out.println("after save");
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return true;
			}
			else {
				transaction.commit();
				System.out.println("after commit");
				session.close();
				return false;
			}

		}
		catch(Exception e) {
			System.out.println("exception " + e);
			return false;
		}
	}





	public void  addStudent(String studentName,String className,String subjectName) {

		System.out.println("in add student new");
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(com.dto.Student.class);
		configuration.addAnnotatedClass(com.dto.ClassTable.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());

		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Student student = new Student(studentName);
		ClassTable classTable = new ClassTable(className);
		session.save(classTable);
		student.setClassTable(classTable);
		Subject subject = new Subject(subjectName);
		session.save(subject);
		System.out.println("in add student");
		session.save(student);
		System.out.println("after save");


		System.out.println("in add");
		transaction.commit();
		System.out.println("in add");
		session.close();
	}








}
