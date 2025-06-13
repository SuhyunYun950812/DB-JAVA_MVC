package model;

public class CartVO {
	private int no;
	private String memberId;
	private int menuid;
	private int quantity;
	private int price;
	
	public CartVO() {
		super();
	}

	public CartVO(int no, String memberId, int menuid, int quantity, int price) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.menuid = menuid;
		this.quantity = quantity;
		this.price = price;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemberid() {
		return memberId;
	}

	public void setMemberid(String memberId) {
		this.memberId = memberId;
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getprice() {
		return price;
	}

	public void setprice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartVO [no=" + no + ", memberId=" + memberId + ", menuid=" + menuid + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
}