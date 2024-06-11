package ch01.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class emp {

	public static void main(String[] args) {

		// DTO 설계하고
		// 값을 담아서 . 연산자를 사용해보시오

		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			statement=connection.createStatement();
			resultSet=statement.executeQuery("SELECT * FROM employee");
			
			while (resultSet.next()) {
				Client client=new Client();
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));
				client.setPassword(resultSet.getString("department"));
				System.out.println(client.toString());
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
