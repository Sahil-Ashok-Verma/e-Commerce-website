

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Uregs")
public class Uregs extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("adrs");
		String password=request.getParameter("pwd");
		String confirmpassword=request.getParameter("cpwd");
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
				out.println("<center>email already exist</center>");
				out.println("<hr>");
				RequestDispatcher rd=request.getRequestDispatcher("Uregs.html");
				rd.include(request,response);
				
			}
			else
			{
				if(password.equals(confirmpassword))
				{
					String qr1="insert into user(name,email,address,password) values(?,?,?,?)";
					PreparedStatement ps1=con.prepareStatement(qr1);
					ps1.setString(1, name);
					ps1.setString(2, email);
					ps1.setString(3, address);
					ps1.setString(4, password);
					int i=ps1.executeUpdate();
					if(i>0)
					{
						out.println("<center>successfully registered, you can login now!!!</center>");
						out.println("<hr>");
						RequestDispatcher rd=request.getRequestDispatcher("User.html");
						rd.include(request,response);
					}
					else
					{
						out.println("<center>cant register!!!</center>");
						out.println("<hr>");
						RequestDispatcher rd=request.getRequestDispatcher("User.html");
						rd.include(request,response);
					}
				}
				else
				{
					out.println("<center>password and confirm password both needs to be same...</center>");
					out.println("<hr>");
					RequestDispatcher rd=request.getRequestDispatcher("Uregs.html");
					rd.include(request,response);
				}
				
			}
			con.close();
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
