

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Deletepro")
public class Deletepro extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String pid=request.getParameter("pid");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root","tiger");
			String qr="delete from product where pid=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, pid);
			int i=ps.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("Showpro");
			if(i>0)
			{
				rd.include(request,response);
				out.println("one product successfuly deleted");
			}
			else
			{
				rd.include(request,response);
				out.println("no product deleted");
			}
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		}
}
