package learn.operation;

import learn.db.DB_conn;
import learn.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ManagePerson {
	private static Connection conn = DB_conn.getConn();
	private static PreparedStatement sql = null;

	//验证用户密码
	public static Boolean verify(Person person) throws SQLException {
		sql = conn.prepareStatement("SELECT * FROM Salepersons WHERE name = ?");
		sql.setString(1, person.getName());
		ResultSet res = sql.executeQuery();

		String actual_password = "";

		while (res.next()) {
			actual_password = res.getString("password");
		}
		return actual_password.equals(person.getPassword());
	}

	//查询所有用户
	public static List<Person> QueryAll() throws SQLException{
		sql = conn.prepareStatement("SELECT * FROM Salepersons");
		ResultSet res = sql.executeQuery();
		List<Person> personlist = new ArrayList<>();

		while (res.next()){
			int id = res.getInt("id");
			String name = res.getString("name");
			String password = res.getString("password");
			String intime = res.getString("intime");

			personlist.add(new Person(id, name, password, intime));
		}
		return personlist;
	}

	//根据姓名查询用户
	public static List<Person> KeyQuery(String which) throws SQLException{

		sql = conn.prepareStatement("SELECT * FROM Salepersons WHERE name = ?");
		sql.setString(1, which);
		ResultSet res = sql.executeQuery();
		List<Person> personlist = new ArrayList<>();

		while (res.next()){
			int id = res.getInt("id");
			String name = res.getString("name");
			String password = res.getString("password");
			String intime = res.getString("intime");

			personlist.add(new Person(id, name, password, intime));
		}
		return personlist;
	}

	//添加用户
	public static void AddPerson(Person person) throws SQLException{

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		sql = conn.prepareStatement("INSERT INTO Salepersons (name, password, intime) VALUES (?,?,?)");
		sql.setString(1, person.getName());
		sql.setString(2, person.getPassword());
		sql.setString(3, new String(df.format(new Date())));
		sql.executeUpdate();
	}

	//删除用户
	public static void DeletePerson(String name) throws SQLException{
		sql = conn.prepareStatement("DELETE FROM Salepersons WHERE name = ?");
		sql.setString(1, name);
		sql.executeUpdate();
	}

	//修改用户
	public static void UpadtePerson(String which, String[] index) throws SQLException{
		switch (Integer.valueOf(index[0])){
			case 1:
				sql = conn.prepareStatement("UPDATE Salepersons SET name = ? WHERE name = ?");
				sql.setString(1, index[1]);
				sql.setString(2,which);
				sql.executeUpdate();
				break;
			case 2:
				sql = conn.prepareStatement("UPDATE Salepersons SET password = ? WHERE name = ?");
				sql.setString(1, index[1]);
				sql.setString(2,which);
				break;
		}
	}

	public static void main(String[] args) throws SQLException{
		//AddPerson(new Person("校长", "password"));
		QueryAll();
	}
}


