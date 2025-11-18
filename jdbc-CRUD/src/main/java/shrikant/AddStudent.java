package shrikant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student",
                    "root",
                    "SHRIKANT@2024"
            );
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO students  VALUES (?, ?, ?, ?, ?, ?)");
            response.setContentType("text/html");
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String branch = request.getParameter("branch");
            int marks = Integer.parseInt(request.getParameter("marks"));
            
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, branch);
            ps.setInt(6, marks);

         
            int result = ps.executeUpdate();
          
            response.getWriter().println("data is added sucesfully" +result);
            response.getWriter().println(
            	    "<a href='index.html' style='display:inline-block; padding:8px 16px; background:#007bff; color:white; text-decoration:none; border-radius:4px; font-size:14px;'>Back</a>"
            	);
         
            ps.close();
            con.close();

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
