package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.SubjectVO;

public class SubjectManager {
	// 학과 목록
	public void list() throws Exception {
		SubjectDAO sd = new SubjectDAO();
		System.out.println("학과 전체 리스트");
		ArrayList<SubjectVO> subjectList = sd.selectAll();
		if(subjectList.size() <= 0) {
			System.out.println("학과전체리스트 내용이 없습니다.");
			return;
		}else if(subjectList == null){
			System.out.println("학과전체리스트 에러발생");
			return;
		}
		for(SubjectVO data : subjectList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
	// 학과 등록
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);

		SubjectDAO sd = new SubjectDAO();
		SubjectVO svo = new SubjectVO();

		String num; // 학과 번호
		String name; // 학과명

		System.out.println("학과 정보 입력");
		System.out.print("학과번호 : ");
		num = scan.nextLine();
		System.out.print("학과명  : ");
		name = scan.nextLine();

		svo.setNum(num);
		svo.setName(name);

		sd.insert(svo);

		System.out.println();
		System.out.println("학과 전체 리스트");
		list();
		System.out.println();
	}

	// 학과 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		SubjectDAO sd = new SubjectDAO();
		SubjectVO svo = new SubjectVO();

		int no; // 입력한 일련번호
		String num; // 학과 번호
		String name; // 학과명

		System.out.println("학과 전체 리스트");
		list();
		System.out.println();

		System.out.println("수정할 학과 일련번호 입력");
		System.out.print("일련번호 : ");
		no = Integer.parseInt(scan.nextLine());

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("학과번호 : ");
		num = scan.nextLine();
		System.out.print("학과명 : ");
		name = scan.nextLine();

		svo.setNo(no);
		svo.setNum(num);
		svo.setName(name);
		int count = sd.update(svo);
		if (count == 0) {
			System.out.println("학과 정보 수정 오류 발생. 조치바람.");
			return;
		}else {
			System.out.println("학과 정보 수정 완료");
		}
		System.out.println();
		System.out.println("학과 전체 리스트");
		list();
		System.out.println();
	}
	// 학과 삭제 관리
	public void delete() throws Exception {
	Scanner scan = new Scanner(System.in);

	SubjectDAO sd = new SubjectDAO();
	SubjectVO svo = new SubjectVO();

	String num; // 학과번호
	System.out.println("학과 전체 리스트");
	list();
	System.out.println();

	System.out.println("삭제할 학과번호 입력");
	System.out.print("학과번호 : ");
	num = scan.nextLine();
	svo.setNum(num);
	int count = sd.deleteByNum(svo);
	if(count == 0) {
		System.out.printf("%s 번호삭제 문제발생 조치바람\n",num);
	}else {
		System.out.printf("%s 번호삭제 성공\n",num);
	}
	System.out.println();
	System.out.println("학과 전체 리스트");
	list();
	System.out.println();
	}
}