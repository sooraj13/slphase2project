package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.AdminController;

/**
 * Servlet implementation class CreateMasterListServlet
 */
public class CreateMasterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateMasterListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		PrintWriter out = response.getWriter();
		boolean flag = false;
		String className = request.getParameter("className");
		String subjectName = request.getParameter("subjectName");
		String teacherName = request.getParameter("teacherName");
		String studentName = request.getParameter("studentName");
		AdminController admin = new AdminController();
		if(className!=null) {
			flag = admin.addClass(className);
		}
		else if(subjectName!=null) {
			flag = admin.addSubject(subjectName);
		}
		else if(teacherName!=null) {
			flag = admin.addTeacher(teacherName);
		}
		else {
			flag = admin.addStudent(studentName);
		}

		rd = request.getRequestDispatcher("MasterList.html");
		rd.include(request, response);
		if(flag) {
			if(className!=null) {
				out.print("<center><span style = 'color:blue'> Class Details Added SuccessFully </span></center>");
			}
			else if(subjectName!=null) {
				out.print("<center><span style = 'color:blue'> Subject Details Added SuccessFully </span></center>");
			}
			else if(teacherName!=null) {
				out.print("<center><span style = 'color:blue'> Teacher Details Added SuccessFully </span></center>");
			}
			else {
				out.print("<center><span style = 'color:blue'> Student Details Added SuccessFully </span></center>");
			}

		}
		else {
			if(className!=null) {
				out.print("<center><span style = 'color:red'> SomeThing Went Wrong.Please Check If className Already Exists </span></center>");
			}
			else if(subjectName!=null) {
				out.print("<center><span style = 'color:red'> SomeThing Went Wrong.Please Check If subjectName Already Exists  </span></center>");
			}
			else if(teacherName!=null) {
				out.print("<center><span style = 'color:red'> SomeThing Went Wrong.Please Try Again </span></center>");
			}
			else {
				out.print("<center><span style = 'color:red'> SomeThing Went Wrong.Please Try Again </span></center>");
			}


		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
