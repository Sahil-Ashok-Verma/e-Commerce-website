

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Addpro")
public class Addpro extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String pid=request.getParameter("pid");
		String name=request.getParameter("name");
		String cat=request.getParameter("cat");
		String price=request.getParameter("price");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root","tiger");
			String qr="insert into product values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, pid);
			ps.setString(2, name);
			ps.setString(3, cat);
			ps.setString(4, price);
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.println("one product successfuly added");
			}
			else
			{
				out.println("no product added");
			}
			con.close();
		} 
		
		catch (Exception e) {
			out.println(e);
		}
		
		
		
	}

}
