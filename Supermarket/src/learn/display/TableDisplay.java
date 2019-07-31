package learn.display;

import learn.entity.Goods;
import learn.entity.Person;
import learn.operation.ManageGood;
import learn.operation.ManagePerson;
import learn.print_table.TextTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TableDisplay {

	public static void show_all_good() throws SQLException {

		List<Goods> goodslist = ManageGood.QueryAll();

		if (goodslist.size() == 0 ){
			System.err.println("！ 库存为空 ！");
		}
		else {
			String[] header = {"编号", "名称", "价格", "数量"};
			int row = goodslist.size();
			int column = Goods.class.getDeclaredFields().length;
			String[][] values = new String[row][column];

			for (int i=0; i<goodslist.size(); i++){
				for (int j=0; j<Goods.class.getDeclaredFields().length; j++){
					Goods good = goodslist.get(i);
					values[i][j] = good.getIndex(j+1);
				}
			}

			System.out.println(new TextTable(header, values));
		}
	}

	public static void show_keypart_good(String name) throws SQLException {
		List<Goods> goodslist = ManageGood.KeyQuery(name);

		if (goodslist.size() == 0) {
			System.err.println("！ 库存为空 ！");
		} else {
			String[] header = {"编号", "名称", "价格", "数量"};
			int row = goodslist.size();
			int column = Goods.class.getDeclaredFields().length;
			String[][] values = new String[row][column];


			for (int i = 0; i < goodslist.size(); i++) {
				for (int j = 0; j < Goods.class.getDeclaredFields().length; j++) {
					Goods good = goodslist.get(i);
					values[i][j] = good.getIndex(j + 1);
				}
			}
			System.out.println(new TextTable(header, values));
		}
	}

	public static void show_all_person() throws SQLException{

		List<Person> personlist = ManagePerson.QueryAll();

		if (personlist.size() == 0){
			System.out.println("！ 尚无管理人员 ！");
		}
		else {
			String[] header = {"编号", "姓名", "密码", "入职时间"};
			int row = personlist.size();
			int column = Person.class.getDeclaredFields().length;
			String[][] values = new String[row][column];

			for (int i = 0; i < personlist.size(); i++) {
				for (int j = 0; j < Goods.class.getDeclaredFields().length; j++) {
					Person person = personlist.get(i);
					values[i][j] = person.getIndex(j + 1);
				}
			}
			System.out.println(new TextTable(header, values));
		}
	}

	public static void show_keypart_person(String name) throws SQLException{
		List<Person> personlist = ManagePerson.KeyQuery(name);

		if (personlist.size() == 0){
			System.out.println("！ 尚无管理人员 ！");
		}
		else {
			String[] header = {"编号", "姓名", "密码", "入职时间"};
			int row = personlist.size();
			int column = Person.class.getDeclaredFields().length;
			String[][] values = new String[row][column];

			for (int i = 0; i < personlist.size(); i++) {
				for (int j = 0; j < Goods.class.getDeclaredFields().length; j++) {
					Person person = personlist.get(i);
					values[i][j] = person.getIndex(j + 1);
				}
			}
			System.out.println(new TextTable(header, values));
		}
	}

	public static void main (String[] args) throws SQLException{
		show_all_person();
	}
}

