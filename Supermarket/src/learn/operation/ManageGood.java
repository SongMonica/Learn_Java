package learn.operation;

/**
 * @title:数据库商品操作
 * @description:对商品进行添加、修改、删除、查询
 * @author:lin
 * @time:2019-7-25
 */

import com.sun.source.tree.WhileLoopTree;
import learn.db.DB_conn;
import learn.entity.Goods;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageGood {
	private static Connection conn = DB_conn.getConn();
	private static PreparedStatement sql = null;

	//查询所有商品信息
	public static List<Goods> QueryAll() throws SQLException {

		sql = conn.prepareStatement("SELECT * FROM Goods");
		ResultSet res = sql.executeQuery();
		List<Goods> goodslist = new ArrayList<>();

		while (res.next()){
			int id = res.getInt("id");
			String name = res.getString("name");
			int quantity = res.getInt("quantity");
			double price = res.getDouble("price");

			Goods good = new Goods(id, name, price, quantity);
			goodslist.add(good);
			//System.out.print("编号:"+id+"  名称:"+name+"  价格:"+price+"  数量:"+quantity+"\n");
		}

		return goodslist;
	}

	//根据名称查询商品
	public static List<Goods> KeyQuery(String which) throws SQLException{
		sql = conn.prepareStatement("SELECT * FROM Goods WHERE name = ?");
		sql.setString(1, which);
		ResultSet res = sql.executeQuery();
		List<Goods> goodslist = new ArrayList<>();

		while (res.next()){
			int id = res.getInt("id");
			String name = res.getString("name");
			int quantity = res.getInt("quantity");
			double price = res.getDouble("price");

			Goods good = new Goods(id, name, price, quantity);
			goodslist.add(good);
		}
		return goodslist;
	}

	//添加商品
	public static void AddGood(Goods good) throws SQLException{

		sql = conn.prepareStatement("INSERT INTO Goods (name, price, quantity) " +
				"VALUES (?, ?, ?)");
		sql.setString(1, good.getName());
		sql.setDouble(2, good.getPrice());
		sql.setInt(3, good.getQuantity());
		sql.executeUpdate();

	}

	//删除商品
	public static void DeleteGood(String name) throws SQLException{
		sql = conn.prepareStatement("DELETE FROM Goods WHERE name = ?");
		sql.setString(1, name);
		sql.executeUpdate();
	}

	//修改商品
	public static void UpdateGood(String which, String[] index) throws SQLException{
		switch (Integer.valueOf(index[0])) {
			case 1:
				sql = conn.prepareStatement("UPDATE Goods SET name = ? where name = ?");
				sql.setString(1, index[1]);
				sql.setString(2, which);
				sql.executeUpdate();
				break;
			case 2:
				sql = conn.prepareStatement("UPDATE Goods SET price = ? WHERE name = ?");
				sql.setDouble(1, Double.valueOf(new InputStreamReader(System.in).toString()));
				sql.setString(2, which);
				sql.executeUpdate();
				break;
			case 3:
				sql = conn.prepareStatement("UPDATE Goods SET quantity = quantity - ? WHERE name = ?");
				sql.setInt(1, Integer.valueOf(index[1]));
				sql.setString(2, which);
				sql.executeUpdate();
				break;
			case 4:
				sql = conn.prepareStatement("UPDATE Goods SET quantity = ? WHERE name = ?");
				sql.setInt(1, Integer.valueOf(index[1]));
				sql.setString(2, which);
				sql.executeUpdate();
				break;
		}
	}
}
