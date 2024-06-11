package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class practice {

public static void main(String[] args) {

String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
String user ="root";
String password = "asd123";

// Connection 객체를 얻어서 삭제 구문을 직접 만들어 보세요.
// mydb2 사용, employee 테이블에 값을 넣는 코드를 작성하세요
Connection connection = null;
ResultSet resultSet = null;
Statement statement = null;
try {
Class.forName("com.mysql.cj.jdbc.Driver");
connection = DriverManager.getConnection(url,user,password);
statement = connection.createStatement();
resultSet =  statement.executeQuery("SELECT * FROM employee");
          
            
            while (resultSet.next()) {
            Employee ee = new Employee(); 
            ee.setName(resultSet.getString("name"));
            System.out.println(resultSet.getString("name"));
            }

} catch (Exception e) {
}






} // end of main


}