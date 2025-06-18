package model;

public class CartMenuSVO {
	private String name; // 메뉴에서 참조하려는 메뉴명
	private int price; // 메뉴에서 참조하려는 메뉴가격
	private int quantity; // 카트에서 참조하려는 메뉴수량

	public CartMenuSVO() {
		super();
	}

	public CartMenuSVO(int quantity, String name, int price) {
		super();
		this.quantity = quantity;
		this.name = name;
		this.price = price;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "[메뉴명 = " + name + ", 개당 가격 =" + price + ", 수량 = " + quantity + "]";
	}
}