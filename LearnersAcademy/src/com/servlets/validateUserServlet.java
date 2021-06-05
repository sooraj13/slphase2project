package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.AdminController;

/**
 * Servlet implementation class validateUserServlet
 */

public class validateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList userList = new ArrayList();
	public static ArrayList passwordList = new ArrayList();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public validateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	static {
		userList.add("admin");
		passwordList.add("admin");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");


		if (userList.contains(username) && passwordList.contains(password)) {
			//System.out.println("in if");
			response.sendRedirect("HomePage.html");

		} else {
			//System.out.println("in else");
			rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
			out.print("<center><span style = 'color:red'> Invalid Credentials !! </span></center>");
		}

	}





}
