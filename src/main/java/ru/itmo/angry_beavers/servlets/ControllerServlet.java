package ru.itmo.angry_beavers.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			double x = Double.parseDouble(request.getParameter("x"));
			double y = Double.parseDouble(request.getParameter("y"));
			double r = Double.parseDouble(request.getParameter("r"));
			
			getServletContext().getRequestDispatcher("/area_check").forward(request, response);
		} catch (Exception ignored) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
