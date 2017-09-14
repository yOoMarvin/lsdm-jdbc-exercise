package jdbc;

import java.sql.*;

public class Jdbc {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/movieDB";
	
	// Database user and password
	static final String USER = "student";
	static final String PASSWORD = "student";


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			//Execute query
			stmt = conn.createStatement();
			String sql = "SELECT * from MovieExec where networth >= 500 ORDER BY networth DESC;";
			ResultSet rs = stmt.executeQuery(sql);
			
			//Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				String name = rs.getString("name");
				int networth = rs.getInt("networth");
				
				//Display values
				System.out.print("Name: " + name);
				System.out.println(", Networth: " + networth);
			}
			rs.close();
		}catch(SQLException se) {
			//Handle JDBC errors
			se.printStackTrace();
		}catch(Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally {
			//final block used for close resources
			try {
				if(stmt != null)
					conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("-------------------------------------------------------------");
	}//end main
}//end JDBC
