package model;

import java.sql.Date;

public class PayLogVO {
	private int logId;
	private int payId;
	private int menuId;
	private String menuName;
	private int quantity;
	private int price;
	private String logType;
	private Date logTime;
	
	public PayLogVO() {
		super();
	}
	
	public PayLogVO(int logId, int payId, int menuId, String menuName, int quantity, int price, String logType,
			Date logTime) {
		super();
		this.logId = logId;
		this.payId = payId;
		this.menuId = menuId;
		this.menuName = menuName;
		this.quantity = quantity;
		this.price = price;
		this.logType = logType;
		this.logTime = logTime;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Override
	public String toString() {
		return "PayLogVO [logId=" + logId + ", payId=" + payId + ", menuId=" + menuId + ", menuName=" + menuName
				+ ", quantity=" + quantity + ", price=" + price + ", logType=" + logType + ", logTime=" + logTime + "]";
	}
}
