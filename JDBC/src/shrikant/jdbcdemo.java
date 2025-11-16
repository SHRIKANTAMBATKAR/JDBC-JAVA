package shrikant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcdemo {

    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student",
                "root",
                "SHRIKANT@2024"
            );

            st = con.createStatement();
            String sql= "UPDATE shrikant SET name='mahesh' WHERE name='Amit'";
            st.executeUpdate(sql);
            
          //  String sql1="Insert into shrikant values(1 , 'shri',21 ,'m','it', 20)";
            String sql1="delete from shrikant where name='shri'";

            st.executeUpdate(sql1);

            System.out.println("Update successful");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
