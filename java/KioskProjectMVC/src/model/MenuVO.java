package model;

public class MenuVO {
	private int id;
	private String name;
	private int price;
	
	public MenuVO() {
		super();
	}

	public MenuVO(int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuVO [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}