package model;

import java.sql.Date;

public class PaymentVO {
	private int payId;
	private String memberId;
	private Date payDate;
	private int price;
	
	public PaymentVO() {
		super();
	}
	public PaymentVO(int payId, String memberId, Date payDate, int price) {
		super();
		this.payId = payId;
		this.memberId = memberId;
		this.payDate = payDate;
		this.price = price;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PaymentVO [payId=" + payId + ", memberId=" + memberId + ", payDate=" + payDate + ", price=" + price
				+ "]";
	}
}