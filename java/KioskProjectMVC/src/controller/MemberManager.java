package controller;

import java.util.ArrayList;
import java.util.Scanner;

import main.KioskProjectClassMain;
import model.MemberVO;

public class MemberManager {
	public static Scanner scan = new Scanner(System.in);

	public MemberVO loginMember() {
		MemberDAO md = new MemberDAO();
		MemberVO mv = new MemberVO();
		String id;
		String pwd;
		boolean loginCheckFlag = false;

		while (!loginCheckFlag) {
			System.out.print("아이디 입력:");
			id = scan.nextLine();
			System.out.print("패스워드 입력:");
			pwd = scan.nextLine();

			mv = md.loginMember(id, pwd);
			
			if (mv == null) {
				// 맞으면
				System.out.println("아이디 혹은 패스워드가 틀렸습니다.");
				// 아니면
			} else {
				loginCheckFlag = true;
				System.out.println("로그인 성공!");
				System.out.printf("어서오세요.%s님 \n", id);
				return mv;
			}
		}
		return null;
	}

	// 회원가입.
	public void registerMember() {
		MemberVO memberVO = new MemberVO();
		MemberDAO memberDAO = new MemberDAO();
		boolean idCheck = false;

		String id;
		String pwd;
		String name;
		String phone;
		int auth;

		// 아이디 작성시 패턴검색 추가해야함.
		do {
			System.out.print("아이디 입력: ");
			id = scan.nextLine();
			memberVO.setMemberId(id);
			idCheck = memberDAO.selectByIdCheck(memberVO);
			if (idCheck == true) {
				System.out.println("아이디가 중복 되었습니다.");
			}
		} while (idCheck == true);

		System.out.print("비밀번호 입력: ");
		pwd = scan.nextLine();

		System.out.print("이름 입력: ");
		name = scan.nextLine();

		System.out.print("전화번호 입력: ");
		phone = scan.nextLine();

		// 모든 가입자는 일반사용자(0)로 등록.
		auth = 0;

		memberVO.setMemberId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setPhone(phone);
		memberVO.setAuth(auth);
		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(memberVO);

		if (result > 0) {
			System.out.println("✅ 회원가입 성공!");
		} else {
			System.out.println("❌ 회원가입 실패...");
		}
	}
}
