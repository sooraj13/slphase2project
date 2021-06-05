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
 * Servlet implementation class AssignClassServlet
 */
public class AssignClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssignClassServlet() {
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
		AdminController admin = new AdminController();
		flag = admin.assignClass(className,subjectName);
		rd = request.getRequestDispatcher("AssignClassForSubject.html");
		rd.include(request, response);
		if(flag) {
			out.print("</br><center><span style = 'color:blue'> Class Assigned To Subject SuccessFully </span></center>");
		}
		else {
			out.print("</br><center><span style = 'color:red'> Something Went Wrong.Mentioned Class And Subject Should Already Exist in DataBase If Not Please add Them From Home Page </span></center>");
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
