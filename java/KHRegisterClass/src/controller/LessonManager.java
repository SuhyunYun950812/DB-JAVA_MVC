package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.LessonVO;

public class LessonManager {
	// 과목 목록
	public void list() throws Exception {
		LessonDAO ld = new LessonDAO();
		System.out.println("과목 전체 리스트");
		ArrayList<LessonVO> lessonList = ld.selectAll();
		if(lessonList.size() <= 0) {
			System.out.println("과목 전체 리스트 내용이 없습니다.");
			return;
		}else if(lessonList == null){
			System.out.println("과목 전체 리스트 에러발생");
			return;
		}
		for(LessonVO data : lessonList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
	// 과목 등록
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);

		LessonDAO ld = new LessonDAO();
		LessonVO lvo = new LessonVO();

		String abbre; // 과목 약어
		String name; // 과목명

		System.out.println("과목 정보 입력");
		System.out.print("과목약어 : ");
		abbre = scan.nextLine();
		System.out.print("과목명  : ");
		name = scan.nextLine();

		lvo.setAbbre(abbre);
		lvo.setName(name);

		ld.insert(lvo);

		System.out.println();
		list();
		System.out.println();
	}

	// 과목 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		LessonDAO ld = new LessonDAO();
		LessonVO lvo = new LessonVO();

		int no; // 입력한 일련번호
		String abbre; // 과목약어
		String name; // 과목명

		System.out.println("학과 전체 리스트");
		list();
		System.out.println();

		System.out.println("수정할 과목 일련번호 입력");
		System.out.print("일련번호 : ");
		no = Integer.parseInt(scan.nextLine());

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("과목약어 : ");
		abbre = scan.nextLine();
		System.out.print("과목명 : ");
		name = scan.nextLine();

		lvo.setNo(no);
		lvo.setAbbre(abbre);
		lvo.setName(name);
		int count = ld.update(lvo);
		if (count == 0) {
			System.out.println("과목 정보 수정 오류 발생. 조치바람.");
			return;
		}else {
			System.out.println("과목 정보 수정 완료");
		}
		System.out.println();
		list();
		System.out.println();
	}
	// 과목 삭제 관리
	public void delete() throws Exception {
	Scanner scan = new Scanner(System.in);

	LessonDAO ld = new LessonDAO();
	LessonVO lvo = new LessonVO();

	int no; // 일련번호
	System.out.println("과목 전체 리스트");
	list();
	System.out.println();

	System.out.println("삭제할 과목일련번호 입력");
	System.out.print("일련번호 : ");
	no = Integer.parseInt(scan.nextLine());
	lvo.setNo(no);
	int count = ld.deleteByNo(lvo);
	if(count == 0) {
		System.out.printf("%s 번호삭제 문제발생 조치바람\n",no);
	}else {
		System.out.printf("%s 번호삭제 성공\n",no);
	}
	System.out.println();
	list();
	System.out.println();
	}
}