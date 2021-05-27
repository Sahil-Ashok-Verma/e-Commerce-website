

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Alogin")
public class Alogin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		if(id.equals("Admin")&pwd.equals("518111"))
		{
			//response.sendRedirect("adminhome.html");
			RequestDispatcher rd=request.getRequestDispatcher("adminhome.html");
			rd.forward(request,response);
		}
		else
		{
			//response.sendRedirect("admin.html");
			out.println("<center>incorrect username or password</center>");
			RequestDispatcher rd=request.getRequestDispatcher("admin.html");
			rd.include(request,response);
		}
	}

}
