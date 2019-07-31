package learn.entity;

import java.util.Date;

public class Person {
	private int id;
	private String name;
	private String password;
	private String intime;

	public Person(String name, String password){
		this.name = name;
		this.password = password;
	}

	public Person(int id, String name, String password, String intime){
		this.id = id;
		this.name = name;
		this.password = password;
		this.intime = intime;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getIntime() {
		return intime;
	}

	public String getIndex(int index){
		String value = null;
		switch (index){
			case 1:
				value = String.valueOf(getId());break;
			case 2:
				value = getName();break;
			case 3:
				value = getPassword();break;
			case 4:
				value = getIntime();break;
		}
		return value;
	}
}
