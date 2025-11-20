package shrikant;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetTXT {

    public static void main(String[] args) {
        Scanner Sc= new Scanner(System.in);
        
        System.out.println("chose the path of folder");
        String path =Sc.next();
        
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String password = "SHRIKANT@2024";

        String sql = "SELECT image FROM Addimage WHERE name = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(sql);
      
            ps.setString(1, "my image");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                InputStream is = rs.getBinaryStream("image");

                try (FileOutputStream fos = new FileOutputStream(path)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Image retrieved and saved to: " + path);

            }else 
            {
                System.out.println("No image found for this name");
            }
          
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            Sc.close();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
