package model;

import java.sql.Date;

public class StudentVO {
	private int no;				// 일련번호
	private String num;			// 학생번호
	private String name;		// 학생이름
	private String id;			// 학생아이디
	private String passwd;		// 학생비밀번호
	private String subjectNum;	// Foreign Key
	private String birthDay;	// 학생생일
	private String phone;		// 학생연락처
	private String address;	// 학생주소
	private String email;		// 학생메일
	private Date reigisterDate;	// 학생등록일자

	public StudentVO() {
		super();
	}
	
	public StudentVO(int no, String num, String name, String id, String passwd, String subjectNum, String birthDay,
			String phone, String addresss, String email, Date reigisterDate) {
		super();
		this.no = no;
		this.num = num;
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.subjectNum = subjectNum;
		this.birthDay = birthDay;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.reigisterDate = reigisterDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getReigisterDate() {
		return reigisterDate;
	}

	public void setReigisterDate(Date reigisterDate) {
		this.reigisterDate = reigisterDate;
	}

	@Override
	public String toString() {
		return "STUDENTVO [" + no + ", " + num + ", " + name + ", " + id + ", " + passwd
				+ ", " + subjectNum + ", " + birthDay + "," + phone + ", "
				+ address + ", " + email + ", " + reigisterDate + "]";
	}
}
