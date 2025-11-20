package shrikant;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTEXT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter TXT file:");
        String TXTFILE = sc.nextLine();  
        try {
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String pass = "SHRIKANT@2024";

                Class.forName("com.mysql.cj.jdbc.Driver");

          
                Connection con = DriverManager.getConnection(url, user, pass);
                PreparedStatement ps = con.prepareStatement("INSERT INTO Addimage (name, image) VALUES (?, ?)");
                FileInputStream fis = new FileInputStream(TXTFILE);
        

                ps.setString(2, "TEXTs");
                ps.setBinaryStream(2, fis, fis.available());

                int rows = ps.executeUpdate();
                System.out.println("Image inserted: " + rows);
            

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
