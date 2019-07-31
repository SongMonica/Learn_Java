package learn.display;

import learn.entity.Goods;
import learn.entity.Person;
import learn.operation.ManagePerson;
import learn.operation.ManageGood;

import java.sql.SQLException;
import java.util.Scanner;

public class MainPage {

	public static void mainpage(){

		System.out.println("超市管理系统");
		System.out.println("*******************************\n");
		System.out.println("          1.登录系统           \n");
		System.out.println("          2.退出系统           \n");
		System.out.println("*******************************");
		System.out.println("请根据数字选择");

		Scanner scanner = new Scanner(System.in);
		System.out.print("输入数字选择:");
		int number = scanner.nextInt();

		switch (number){
			case 1:
				System.out.print("请输入用户名:");
				Scanner scanner1 = new Scanner(System.in);
				String name = scanner1.nextLine();
				System.out.print("请输入密码:");
				String password = scanner1.nextLine();
				Person person = new Person(name, password);
				try {
					if (ManagePerson.verify(person))
					{
						clear();
						page1();
					}
					else {
						System.err.println("密码错误, 退出系统");
					}
				}catch (SQLException e){
					e.printStackTrace();
				}
				break;
			case 2:
				break;
		}
	}

	public static void page1(){

		System.out.println("超市管理系统");
		System.out.println("*******************************\n");
		System.out.println("          1.商品管理           \n");
		System.out.println("          2.前台收银           \n");
		System.out.println("          3.人员管理           \n");
		System.out.println("*******************************");
		System.out.println("请根据数字选择, 或者按0返回上一级菜单");

		Scanner scanner = new Scanner(System.in);
		System.out.print("输入数字选择:");
		int number = scanner.nextInt();

		clear();

		switch (number){
			case 1:
				page1_1();break;
			case 2:
				page1_2();break;
			case 3:
				page1_3();break;
			default:
				mainpage();break;
		}

	}

	public static void page1_1(){

		System.out.println("超市管理系统>>商品管理");
		System.out.println("*******************************\n");
		System.out.println("          1.添加商品           \n");
		System.out.println("          2.修改商品           \n");
		System.out.println("          3.删除商品           \n");
		System.out.println("          4.查询商品           \n");
		System.out.println("*******************************");
		System.out.println("请根据数字选择, 或者按0返回上一级菜单");

		Scanner scanner = new Scanner(System.in);
		System.out.print("输入数字选择:");
		int number = scanner.nextInt();

		switch (number){
			case 1: {
				System.out.print("\n添加商品名称:");
				String nothing = scanner.nextLine();
				String name = scanner.nextLine();
				System.out.print("\n添加商品价格:");
				double price = scanner.nextDouble();
				System.out.print("\n添加商品数量:");
				int quantity = scanner.nextInt();
				nothing = scanner.nextLine();
				System.out.print("\n是否继续(y/n):");
				String choice = scanner.nextLine();

				if ("y".equals(choice)) {
					Goods good = new Goods(name, price, quantity);
					try {
						ManageGood.AddGood(good);
						System.out.println("\n添加成功!");
						System.out.println("\n是否回到上一界面(y/n):");
						choice = scanner.nextLine();
						if ("y".equals(choice)){
							page1_1();
						}else {
							return;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("\n取消添加!");
					System.out.println("\n是否回到上一界面(y/n):");
					choice = scanner.nextLine();
					if ("y".equals(choice)){
						page1_1();
					}else {
						return;
					}

				}
				break;
			}
			case 2: {
				System.out.print("\n输入要更改的商品名称:");
				String nothing = scanner.nextLine();
				String name = scanner.nextLine();
				try {
					TableDisplay.show_keypart_good(name);
				}catch (SQLException e){
					e.printStackTrace();
				}
				System.out.println("选择你要更改的内容:");
				System.out.println("1.更改商品名称;");
				System.out.println("2.更改商品价格;");
				System.out.println("3.更改商品数量;");
				System.out.println("4.退回上一界面;");
				System.out.print("输入数字选择:");
				int choice = scanner.nextInt();
				nothing = scanner.nextLine();

				switch (choice) {
					case 1:
						System.out.print("\n请输入新的名称:");
						String new_name = scanner.nextLine();
						nothing = scanner.nextLine();
						try {
							ManageGood.UpdateGood(name, new String[]{"1", new_name});
							System.out.println("\n更改成功!");
							String choice1 = scanner.nextLine();
							if ("y".equals(choice1)){
								page1_1();
							}else {
								return;
							}
						}catch (SQLException e){
							e.printStackTrace();
						}
						break;
					case 2:
						System.out.print("\n请输入新的价格:");
						double new_price = scanner.nextDouble();
						nothing = scanner.nextLine();
						try {
							ManageGood.UpdateGood(name, new String[]{"2", Double.toString(new_price)});
							System.out.println("\n更改成功!");
							System.out.println("\n是否回到上一界面(y/n):");
							String choice2 = scanner.nextLine();
							if ("y".equals(choice2)){
								page1_1();
							}else {
								return;
							}
						}catch (SQLException e){
							e.printStackTrace();
						}
						break;
					case 3:
						System.out.print("\n请输入新的数量:");
						int new_quantity = scanner.nextInt();
						nothing = scanner.nextLine();
						try {
							ManageGood.UpdateGood(name, new String[]{"4", Integer.toString(new_quantity)});
							System.out.println("\n更改成功!");
							System.out.println("\n是否回到上一界面(y/n):");
							String choice3 = scanner.nextLine();
							if ("y".equals(choice3)){
								page1_1();
							}else {
								return;
							}
						}catch (SQLException e){
							e.printStackTrace();
						}
						break;
					case 4:
						page1_1();
						break;
				}
			}

			case 3:{
				System.out.print("\n输入要更改的商品名称:");
				String nothing = scanner.nextLine();
				String name = scanner.nextLine();
				try {
					TableDisplay.show_keypart_good(name);
					System.out.print("是否确认删除(y/n):");
					String choice = scanner.nextLine();
					if ("y".equals(choice)){
						ManageGood.DeleteGood(name);
						System.out.println("\n删除成功!");
					}else {
						System.out.println("\n取消删除!");
						System.out.println("是否回到上一界面(y/n)");
						choice = scanner.nextLine();
						if ("y".equals(choice)){
							page1_1();
						}else {
							return;
						}
					}
				}catch (SQLException e){
					e.printStackTrace();
				}
				break;
			}
			case 4:{
				String nothing = scanner.nextLine();
				try {
					TableDisplay.show_all_good();
					System.out.print("是否回到上一界面(y/n):");
					String choice = scanner.nextLine();
					if ("y".equals(choice)){
						page1_1();
					}else {
						return;
					}
				}catch (SQLException e){
					e.printStackTrace();
				}
				break;
			}
			default:
				page1();break;
		}

	}


	public static void page1_2(){

		System.out.println("超市管理系统>>前台收银");
		System.out.println("*******************************");
		System.out.print("输入商品名称:");

		Scanner scanner1 = new Scanner(System.in);
		String key_name = scanner1.nextLine();
		try {
			TableDisplay.show_keypart_good(key_name);
			System.out.print("请输入购买数量:");
			int buy_quantity = scanner1.nextInt();
			String nothing = scanner1.nextLine();
			double price = ManageGood.KeyQuery(key_name).get(0).getPrice();

			System.out.println("\n"+key_name+"   ￥"+price
					+"    购买数量"+buy_quantity+"   总价"+buy_quantity*price);


			System.out.print("\n请输入实际交费金额:");
			int money = scanner1.nextInt();
			nothing = scanner1.nextLine();
			System.out.println("\n应找余额:"+ (money-Double.valueOf(buy_quantity*price)));
			ManageGood.UpdateGood(key_name, new String[]{"3", Integer.toString(buy_quantity)});
			System.out.println("\n谢谢惠顾!");
			System.out.print("\n是否回到上一界面(y/n):");
			String choice = scanner1.nextLine();
			if ("y".equals(choice)){
				page1_1();
			}else {
				return;
			}

		}catch (SQLException e){
			e.printStackTrace();
		}

	}

	public static void page1_3(){
		System.out.println("超市管理系统>>人员管理");
		System.out.println("*******************************");
		System.out.println("          1.添加管理员         \n");
		System.out.println("          2.修改管理员         \n");
		System.out.println("          3.删除管理员         \n");
		System.out.println("          4.查询管理员         \n");
		System.out.println("*******************************");
		System.out.println("请根据数字选择, 或者按0返回上一级菜单");

		Scanner scanner = new Scanner(System.in);
		System.out.print("输入数字选择:");
		int number = scanner.nextInt();
		String nothing = scanner.nextLine();

		switch (number){
			case 1: {
				System.out.print("\n添加售货员姓名:");
				String name = scanner.nextLine();
				System.out.print("\n添加售货员密码:");
				String password = scanner.nextLine();
				System.out.print("\n是否确认添加(y/n):");
				String choice = scanner.nextLine();

				if ("y".equals(choice)) {
					Person person = new Person(name, password);
					try {
						ManagePerson.AddPerson(person);
						System.out.println("\n添加成功!");
						System.out.print("\n是否回到上一界面(y/n):");
						choice = scanner.nextLine();
						if ("y".equals(choice)) {
							clear();
							page1_3();
						} else {
							return;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("\n取消添加!");
					System.out.print("\n是否回到上一界面(y/n):");
					choice = scanner.nextLine();
					if ("y".equals(choice)) {
						page1_3();
					} else {
						return;
					}
				}
				break;
			}
			case 2: {
				System.out.print("\n输入更改的售货员姓名:");
				String name = scanner.nextLine();
				try{
					TableDisplay.show_keypart_person(name);
				}catch (SQLException e){
					e.printStackTrace();
				}
				System.out.println("选择你要更改的内容:");
				System.out.println("1.更改售货员姓名;");
				System.out.println("2.更改售货员密码;");
				System.out.println("3.退回上一界面;");
				System.out.print("\n输入你的选择:");

				int choice = scanner.nextInt();
				nothing = scanner.nextLine();

				switch (choice){
					case 1: {
						System.out.print("\n请输入新的售货员姓名:");
						String new_name = scanner.nextLine();
						try {
							ManagePerson.UpadtePerson(name, new String[]{"1", new_name});
						} catch (SQLException e) {
							e.printStackTrace();
						}
						System.out.println("修改成功!");
						System.out.print("\n是否回到上一界面(y/n):");
						String n_choice = scanner.nextLine();
						if ("y".equals(n_choice)) {
							page1_3();
						} else {
							return;
						}
						break;
					}
					case 2: {
						System.out.print("\n请输入新的售货员密码:");
						String new_password = scanner.nextLine();
						try {
							ManagePerson.UpadtePerson(name, new String[]{"2", new_password});
						} catch (SQLException e) {
							e.printStackTrace();
						}
						System.out.println("修改成功!");
						System.out.print("\n是否回到上一界面(y/n):");
						String n_choice = scanner.nextLine();
						if ("y".equals(n_choice)) {
							page1_3();
						} else {
							return;
						}
						break;
					}
					case 3:
						page1_3();break;
				}

			}
			case 3:
				System.out.print("\n输入要删除的售货员姓名:");
				String name = scanner.nextLine();
				try{
					TableDisplay.show_keypart_person(name);
				}catch (SQLException e){
					e.printStackTrace();
				}
				System.out.print("\n是否确认删除(y/n):");
				String choice = scanner.nextLine();

				if ("y".equals(choice)){
					try {
						ManagePerson.DeletePerson(name);
						System.out.println("删除成功!");
						System.out.print("\n是否回到上一界面(y/n):");
						String n_choice = scanner.nextLine();
						if ("y".equals(n_choice)) {
							page1_3();
						} else {
							return;
						}

					}catch (SQLException e){
						e.printStackTrace();
					}
				}else {
					System.out.print("\n是否回到上一界面(y/n):");
					String n_choice = scanner.nextLine();
					if ("y".equals(n_choice)) {
						page1_3();
					} else {
						return;
					}
				}
				break;
			case 4:
				try{
					TableDisplay.show_all_person();
					System.out.print("\n是否回到上一界面(y/n):");
					String n_choice = scanner.nextLine();
					if ("y".equals(n_choice)) {
						page1_3();
					} else {
						return;
					}
				}catch (SQLException e){
					e.printStackTrace();
				}
				break;
		}

	}

	public static void clear(){
		for (int i=0; i<20; i++){
			System.out.print("\n");
		}
	}

	public static void main(String[] args){
		page1_3();
	}
}
