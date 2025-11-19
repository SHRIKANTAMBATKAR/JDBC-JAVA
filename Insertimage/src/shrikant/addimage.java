package shrikant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class addimage {

	public static void main(String[] args) throws SQLException, IOException {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Connection
        java.sql.Connection con = null;
		try {
			 con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/student",
	                    "root",
	                    "SHRIKANT@2024"
	            );
	            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String sql = "INSERT INTO Addimage (name, image) VALUES (?, ?)";
        PreparedStatement ps = null;
		try {
			ps = ((java.sql.Connection) con).prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ps.setString(1, "my image");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Read image file
        FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\images\\my image.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ps.setBinaryStream(2, fis, fis.available());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        int rows = ps.executeUpdate();

        System.out.println("Image inserted: " + rows);

        con.close();

	}

}