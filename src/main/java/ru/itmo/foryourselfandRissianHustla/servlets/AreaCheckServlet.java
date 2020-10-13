package ru.itmo.foryourselfandRissianHustla.servlets;

import ru.itmo.foryourselfandRissianHustla.models.Entries;
import ru.itmo.foryourselfandRissianHustla.models.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/area_check")
public class AreaCheckServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double x = 3;
		double y = 0;
		double r = 0;
		
		try {
			x = Double.parseDouble(request.getParameter("x"));
			y = Double.parseDouble(request.getParameter("y"));
			r = Double.parseDouble(request.getParameter("r"));
		} catch (NumberFormatException ignored) {
		}
		
		boolean result = getResult(x, y, r);
		Date currentTime = new Date();
		
		Entry entry = new Entry(x, y, r, result, currentTime);
		
		HttpSession session = request.getSession();
		
		Entries entries = (Entries) session.getAttribute("entries");
		entries = entries == null ? new Entries() : entries;
		
		entries.addEntry(entry);
		session.setAttribute("entries", entries);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean getResult(double x, double y, double r) {
		return inFirst(x, y, r) || inThird(x, y, r) || inForth(x, y, r);
	}
	
	private boolean inFirst(double x, double y, double r) {
		return (x >= 0 && y >= 0 && x <= r && y <= r / 2 && x + 2 * y <= r);
	}
	
	private boolean inThird(double x, double y, double r) {
		return (x <= 0 && y <= 0 && x >= - r / 2 && y >= - r);
	}
	
	private boolean inForth(double x, double y, double r) {
		return (x >= 0 && y <= 0 && x * x + y * y <= r * r);
	}
	
}
