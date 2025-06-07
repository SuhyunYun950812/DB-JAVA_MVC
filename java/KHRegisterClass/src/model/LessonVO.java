package model;

/**
 * 
 */
public class LessonVO {
	private int no; // 일련번호
	private String abbre; // 과목약어
	private String name; // 학과명
	
	public LessonVO() {
		super();
	}
	
	public LessonVO(int no, String abbre, String name) {
		super();
		this.no = no;
		this.abbre = abbre;
		this.name = name;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAbbre() {
		return abbre;
	}
	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LessonVO [no=" + no + ", abbre=" + abbre + ", name=" + name + "]";
	}

}
