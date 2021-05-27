

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Uhome")
public class Uhome extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<center><h3>Welcome to user home</h3></center>");
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		out.println("<h4 text align=center>"+id+"</h4><br>");
		out.println("<center><a href=Logout>logout</a></center>");
	}

}
