package shrikant;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ViewStudents")
public class ViewStudents extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection con;

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student",
                    "root",
                    "SHRIKANT@2024"
            );
        } catch (Exception e) {
            throw new ServletException("Connection error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();

            // HTML start
            response.getWriter().println("<!DOCTYPE html><html><head>");
            response.getWriter().println("<title>View Students</title>");
            response.getWriter().println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            response.getWriter().println("</head><body class='bg-light'>");

            // Navbar
            response.getWriter().println("<nav class='navbar navbar-expand-lg navbar-dark bg-primary mb-4'>"
                    + "<div class='container'>"
                    + "<a class='navbar-brand' href='#'>Student Management</a>"
                    + "<ul class='navbar-nav ms-auto'>"
                    + "<li class='nav-item'><a class='nav-link' href='index.html'>Add Student</a></li>"
                    + "<li class='nav-item'><a class='nav-link' href='delete.html'>Delete Student</a></li>"
                    + "<li class='nav-item'><a class='nav-link' href='ViewStudents'>View Students</a></li>"
                    + "<li class='nav-item'><a class='nav-link' href='update.html'>Update Students</a></li>"

                    + "</ul></div></nav>");

            // Table start
            response.getWriter().println("<div class='container'><h3 class='mb-4'>Student Records</h3>");
            response.getWriter().println("<table class='table table-striped table-bordered'>");
            response.getWriter().println("<thead><tr>"
                    + "<th>ID</th>"
                    + "<th>Name</th>"
                    + "<th>Age</th>"
                    + "<th>Gender</th>"
                    + "<th>Branch</th>"
                    + "<th>Marks</th>"
                    + "</tr></thead><tbody>");

            while (rs.next()) {
                response.getWriter().println("<tr>"
                        + "<td>" + rs.getInt("id") + "</td>"
                        + "<td>" + rs.getString("name") + "</td>"
                        + "<td>" + rs.getInt("age") + "</td>"
                        + "<td>" + rs.getString("gender") + "</td>"
                        + "<td>" + rs.getString("branch") + "</td>"
                        + "<td>" + rs.getInt("marks") + "</td>"
                        + "</tr>");
            }

            response.getWriter().println("</tbody></table></div>");
            response.getWriter().println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
            response.getWriter().println("</body></html>");

            rs.close();
            ps.close();

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            System.out.println("Error closing connection");
        }
    }
}
