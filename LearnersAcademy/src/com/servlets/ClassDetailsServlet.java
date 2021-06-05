package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.AdminController;
import com.dto.ClassTable;
import com.dto.Student;
import com.dto.Subject;

/**
 * Servlet implementation class ClassDetailsServlet
 */
public class ClassDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminController admin = new AdminController();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		String className = request.getParameter("className");
		List<ClassTable> classList  = admin.fetchClassDetails(className);
		if(classList!=null && !classList.isEmpty()) {
			rd = request.getRequestDispatcher("ClassDetails.html");
			rd.include(request, response);
			out.print("</br></br><b><span style = 'color:black'>" + "Class Report Of : "+className +" </span></b></br></br>");
			out.print("</br></br><b><span style = 'color:black'>" + "Subjects : " +" </span></b></br>");
			classList.get(0);
			for(Subject s:classList.get(0).getSubjects()) {
				out.print("<span style = 'color:black'>" +s.getSubjectName()+" </span></br>");
			}
			out.print("</br></br><b><span style = 'color:black'>" + "Students : " +" </span></b></br>");
			for(Student st:classList.get(0).getStudents()) {
				out.print("<span style = 'color:black'>" +st.getStudentName()+" </span></br>");
			}
		}
		else {
			rd = request.getRequestDispatcher("ClassDetails.html");
			rd.include(request, response);
			out.print("</br></br><b><span style = 'color:red'>" + "Mentioned Class not Found " +" </span></b></br>");
		}
		out.print("</br><a href='HomePage.html'>back</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
