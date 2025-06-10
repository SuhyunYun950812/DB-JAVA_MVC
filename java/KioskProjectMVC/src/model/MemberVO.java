package model;

public class MemberVO {
	private String memberId;
	private String pwd;
	private String name;
	private String phone;
	private int auth;

	public MemberVO() {
		super();
	}
	
	public MemberVO(String memberId, String pwd, String name, String phone,int auth) {
		super();
		this.memberId = memberId;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.auth = auth;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone + "]";
	}

}