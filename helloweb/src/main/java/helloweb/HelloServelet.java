package helloweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServelet
 */
public class HelloServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
   

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw =response.getWriter();
		pw.println("<h1>Hello 세상!!!"+id+"</h1>");
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
