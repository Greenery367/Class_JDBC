package ch03;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertExample {
	
	public static void main(String[] args) {
		
		String url="jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user="root";
		String password="asd123";
		
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,user,password);
			String query="insert into employee values(?,?,?,?,now())";
			
			
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, 9);
			preparedStatement.setString(2, "조드");
			preparedStatement.setString(3, "인사관리");
			preparedStatement.setString(4,"800000.00");
			
			int rowCount=preparedStatement.executeUpdate();
			System.out.println("rowCount : "+rowCount);
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
				
				
		// Connection 객체를 얻어서, insert 구문을 직접 만들어보자.
		// mydb2 사용, employee 테이블에 값을 넣는 코드를 작성하세요.
		
	} // end of main

}
