package model;

public class CartVO {
	private int no;
	private String memberid;
	private int menuid;
	private int quantity;
	private int total;
	
	public CartVO() {
		super();
	}

	public CartVO(int no, String memberid, int menuid, int quantity, int total) {
		super();
		this.no = no;
		this.memberid = memberid;
		this.menuid = menuid;
		this.quantity = quantity;
		this.total = total;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CartVO [no=" + no + ", memberid=" + memberid + ", menuid=" + menuid + ", quantity=" + quantity
				+ ", total=" + total + "]";
	}
	
}