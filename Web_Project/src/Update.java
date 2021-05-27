

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Update")
public class Update extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String pid=request.getParameter("pid");
		String name=request.getParameter("name");
		String cat=request.getParameter("cat");
		String price=request.getParameter("price");
		//int price=Integer.parseInt(p);
		
		out.println("<form action='Updatepro'>");
		out.println("<input type='hidden' name='pid' value="+pid+"><br><br>");
		out.println("Name<input type='text' name='name' value="+name+"><br><br>");
		out.println("Product Category<select name='cat' value="+cat+"><br><br>");
		out.println("<option>Mobile</option>");
		out.println("<option>Tab</option>");
		out.println("<option>Laptop</option>");
		out.println("<option>Led monitor</option>");
		out.println("<option>Bag</option>");
		out.println("<option>Assesories</option>");
		out.println("</select>");
		out.println("Price<input type='number' name='price' value='"+price+"'><br><br>");
		out.println("<input type='submit' value='Update'>");
		out.println("</form>");
	}

}
