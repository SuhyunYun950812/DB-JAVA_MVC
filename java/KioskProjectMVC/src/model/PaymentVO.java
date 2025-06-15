package model;

import java.sql.Date;

public class PaymentVO {
	private int payId;
	private String memberId;
	private Date payDate;
	private int totalAmount;
	private String payMethod;	//결제 수단
	private String status;	// 결제완료 / 결제취소
	
	public PaymentVO() {
		super();
	}
	public PaymentVO(int payId, String memberId, Date payDate, int totalAmount, String payMethod, String status) {
		super();
		this.payId = payId;
		this.memberId = memberId;
		this.payDate = payDate;
		this.totalAmount = totalAmount;
		this.payMethod = payMethod;
		this.status = status;
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
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "PaymentVO [payId=" + payId + ", memberId=" + memberId + ", payDate=" + payDate + ", totalAmount="
				+ totalAmount + ", payMethod=" + payMethod + ", status=" + status + "]";
	}
}