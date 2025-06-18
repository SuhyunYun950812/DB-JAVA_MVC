package model;

import java.sql.Date;

public class CartVO {
	private int id;
	private String memberId;
	private int menuId;
	private int quantity;
	private String status;	// 재결제 방지
	private Date regDate;
	
	public CartVO() {
		super();
	}
	
	public CartVO(int id, String memberId, int menuId, int quantity, String status, Date regDate) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.status = status;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "[ 등록 번호 = " + id + ", 메뉴 번호 = " + menuId + ", 수량 =" + quantity
				+ "]";
	}
}