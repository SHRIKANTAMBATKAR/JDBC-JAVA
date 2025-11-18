package shrikant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection con;

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student",
                "root",
                "SHRIKANT@2024"
            );
        } catch (Exception e) {
            throw new ServletException("Database connection error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String branch = request.getParameter("branch");
            int marks = Integer.parseInt(request.getParameter("marks"));

            String sql = "UPDATE students SET name=?, age=?, gender=?, branch=?, marks=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, branch);
            ps.setInt(5, marks);
            ps.setInt(6, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.getWriter().println("Student updated successfully!");
                response.getWriter().println(
                	    "<a href='update.html' style='display:inline-block; padding:8px 16px; background:#007bff; color:white; text-decoration:none; border-radius:4px; font-size:14px;'>Back</a>"
                	);

            } else {
                response.getWriter().println("No student found with ID: " + id);
            }

            ps.close();

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
