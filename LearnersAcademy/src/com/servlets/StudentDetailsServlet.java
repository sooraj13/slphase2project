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

/**
 * Servlet implementation class StudentDetailsServlet
 */
public class StudentDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminController admin = new AdminController();
		List<String> studentList  = admin.fetchStudentDetails();

		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("StudentDetails.html");
		rd.include(request, response);
		for(String s:studentList) {
			out.print("<span style = 'color:black'>" +s+" </span></br>");
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
