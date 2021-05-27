

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Ulogin")
public class Ulogin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root","tiger");
			String qr="select * from user where email=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String qr1="select * from user where email=? and password=?";
				PreparedStatement ps1=con.prepareStatement(qr1);
				ps1.setString(1, email);
				ps1.setString(2, pwd);
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next())
				{
					HttpSession session=request.getSession();
					session.setAttribute("id", email);
					RequestDispatcher rd=request.getRequestDispatcher("Uhome");
					rd.forward(request,response);
				}
				else
				{
					out.println("<center>invalid id or password</center>");
					out.println("<hr>");
					RequestDispatcher rd=request.getRequestDispatcher("User.html");
					rd.include(request,response);
				}
				
			}
			else
			{
				out.println("<center>No matching acounts found, you can register from the link below..</center>");
				out.println("<hr>");
				RequestDispatcher rd=request.getRequestDispatcher("User.html");
				rd.include(request,response);
			}
			con.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
