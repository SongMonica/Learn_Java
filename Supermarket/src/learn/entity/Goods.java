package learn.entity;


/**
 * @title:商品实体类
 * @description:包含商品编号,名称,价格,数量
 * @author:lin
 * @time:2019-7-25
 */

public class Goods {
	private int id;
	private double price;
	private int quantity;
	private String name;


	public Goods(){
		super();
	}

	public Goods(int id , String name, double price, int quantity){
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Goods(String name, double price, int quantity){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex(int index){
		String value = null;
		switch (index){
			case 1:
				value = String.valueOf(getId());break;
			case 2:
				value = getName();break;
			case 3:
				value = String.valueOf(getPrice());break;
			case 4:
				value = String.valueOf(getQuantity());break;
		}
		return value;
	}
}

