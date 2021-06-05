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
 * Servlet implementation class AssignTeacherServlet
 */
public class AssignTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssignTeacherServlet() {
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
		String teacherName = request.getParameter("teacherName");
		String subjectName = request.getParameter("subjectName");
		AdminController admin = new AdminController();
		flag = admin.assignTeacher(teacherName,subjectName);
		rd = request.getRequestDispatcher("AssignTeacher.html");
		rd.include(request, response);
		if(flag) {
			out.print("</br></br><center><span style = 'color:blue'> Teacher Assigned To Subject SuccessFully </span></center>");
		}
		else {
			out.print("</br></br><center><span style = 'color:red'> Something Went Wrong.Mentioned Teacher And Subject Should Already Exist in DataBase If Not Please add Them From Home Page </span></center>");
		}
		out.print("</br></br><a href='HomePage.html'>back</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
