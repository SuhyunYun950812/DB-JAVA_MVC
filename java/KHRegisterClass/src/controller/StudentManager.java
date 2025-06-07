package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import model.StudentVO;

public class StudentManager {
	// 학생 목록
	public void list() throws Exception {
		StudentDAO sd = new StudentDAO();
		System.out.println("학생 전체 리스트");
		ArrayList<StudentVO> studentList = sd.selectAll();
		if (studentList.size() <= 0) {
			System.out.println("학생 전체 리스트 내용이 없습니다.");
			return;
		} else if (studentList == null) {
			System.out.println("학생 전체 리스트 에러발생");
			return;
		}
		for (StudentVO data : studentList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}

	// 학생 등록
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);
		StudentDAO sd = new StudentDAO();
		SubjectManager subjectManager = new SubjectManager();
		StudentVO svo = new StudentVO();
		boolean idCheck = false;

		String num; // 학생번호
		String name; // 학생이름
		String id; // 학생아이디
		String passwd; // 학생비밀번호
		String subjectNum; // 외래키 subject_num
		String birthDay; // 학생생일
		String phone; // 학생전화번호
		String address; // 학생주소
		String email; // 학생이메일
		Date registerDate;

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		
		subjectManager.list(); // 학과번호 리스트 출력
		System.out.println("학과번호 : ");
		subjectNum = scan.nextLine();


		// 학생 번호는 8자리로 생성한다. (연도2자리+학과2자리+일련번호 - 예로06010001)
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		String year = sdf.format(new java.util.Date());
		svo.setSubjectNum(subjectNum);
		String studentCount = sd.selectBySubjectNumCount(svo);
		num = year + subjectNum + studentCount;
		System.out.printf("학생번호 : %s \n", num);

		System.out.printf("학생이름 :");
		name = scan.nextLine();

		do {
			System.out.println("아이디(8자 이상 12자 이내)");
			id = scan.nextLine();
			svo.setId(id);
			idCheck = sd.selectByIdCheck(svo);
			if (idCheck == true) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요");
			}
		} while (idCheck == true);
		
		System.out.print("비밀번호(12자 이내) : ");
		passwd = scan.nextLine();
		System.out.print("생년월일(8자리) : ");
		birthDay = scan.nextLine();
		System.out.print("전화번호 : ");
		phone = scan.nextLine();
		System.out.print("도로명 주소 : ");
		address = scan.nextLine();
		System.out.print("이메일   : ");
		email = scan.nextLine();
		
		svo.setNum(num);
		svo.setName(name);
		svo.setId(id);
		svo.setPasswd(passwd);
		svo.setSubjectNum(subjectNum); // 외래키 subject_num 학과번호
		svo.setBirthDay(birthDay);
		svo.setPhone(phone);
		svo.setAddress(address);
		svo.setEmail(email);
		
		sd.insert(svo);
		System.out.println();
		list();
		System.out.println();
	}

	// 학생 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		StudentDAO sd = new StudentDAO();
		StudentVO svo = new StudentVO();

		int no; // 입력한 일련번호
		String num; // 학생약어
		String name; // 학생명
		String phone; // 학생명

		System.out.println("학과 전체 리스트");
		list();
		System.out.println();

		System.out.println("수정할 학생번호 입력");
		System.out.print("학생번호 : ");
		num = scan.nextLine();

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("수정할 학생이름 : ");
		name = scan.nextLine();
		System.out.print("학생 핸드폰 : ");
		phone = scan.nextLine();

		svo.setNum(num);
		svo.setName(name);
		svo.setPhone(phone);
		int count = sd.update(svo);
		if (count == 0) {
			System.out.println("학생 정보 수정 오류 발생. 조치바람.");
			return;
		} else {
			System.out.println("학생 정보 수정 완료");
		}
		System.out.println();
		list();
		System.out.println();
	}

	// 학생 삭제 관리
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		StudentDAO sd = new StudentDAO();
		StudentVO svo = new StudentVO();
		
		String num;
		list();
		System.out.println();

		System.out.println("삭제할 학생일련번호 입력");
		System.out.print("일련번호 : ");
		num = scan.nextLine();
		svo.setNum(num);
		int count = sd.deleteByNum(svo);
		if (count == 0) {
			System.out.printf("%s 번호삭제 문제발생 조치바람\n", num);
		} else {
			System.out.printf("%s 번호삭제 성공\n", num);
		}
		System.out.println();
		list();
		System.out.println();
	}
}