

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Showpro")
public class Showpro extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text,html");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root","tiger");
			Statement st=con.createStatement();
			String qr="select * from product";
			ResultSet rs=st.executeQuery(qr);
			if(rs.next())
			{
				out.println("<table align='center' border='1px'>");
				out.println("<th>PRODUCT ID</th><th>NAME</th><th>CATEGORY</th><th>PRICE</th>");
				do
				{
					String pid=rs.getString("pid");
					String name=rs.getString("name");
					String cat=rs.getString("cat");
					int price=rs.getInt("price");
					out.println("<tr>");
					out.println("<td>"+pid+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+cat+"</td>");
					out.println("<td>"+price+"</td>");
					out.println("<td><a href=Deletepro?pid="+pid+">Delete</a></td>");
					out.println("<td><a href=Update?pid="+pid+"&name="+name+"&cat="+cat+"&price="+price+">Update</a></td>");
					out.println("</tr>");
				}while(rs.next());
				out.println("</table>");
				con.close();
			}
			else
			{
				out.println("no records found");
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
