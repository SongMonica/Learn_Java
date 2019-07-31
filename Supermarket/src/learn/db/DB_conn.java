package learn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DB_conn {

	private static Connection conn;

	public static Connection getConn(){

		//加载数据库驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		//连接数据库
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/supermarket?useUnicode=true&characterEncoding=utf-8",
					"root", "password"
			);

		}catch (SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
}
