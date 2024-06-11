package ch03;

import com.mysql.cj.jdbc.ConnectionGroupManager;

import ch01.DTO.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectExample {
public static void main(String[] args) throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user="root";
		String password="asd123";
		
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,user,password);
			
			String query= "SELECT * from employee";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
		
			resultSet=preparedStatement.executeQuery();
			
			ArrayList<Employee> employeeList=new ArrayList<>();
			
			while(resultSet.next()) {
				Employee employee=new Employee();
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setSalary(resultSet.getInt("Salary"));
				employeeList.add(employee);
				
				System.out.println("name:"+employee.getName());
				System.out.println("department: "+employee.getDepartment());	
				System.out.println("salary: "+employee.getSalary());	
				System.out.println("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Connection 객체를 얻어서, 삭제 구문을 직접 만들어보세요.
		// mydb2 사용, employee 테이블의 값을 수정하는 코드를 작성하세요.
		
	} // end of main
}
