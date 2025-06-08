package controller;

import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
	public void registerMember() {
		Scanner sc = new Scanner(System.in);
		MemberVO memberVO = new MemberVO();
		boolean idCheck = false;

		// 아이디 작성시 패턴검색 추가해야함.
		System.out.print("아이디 입력: ");
		memberVO.setId(sc.nextLine());

		System.out.print("비밀번호 입력: ");
		memberVO.setPwd(sc.nextLine());

		System.out.print("이름 입력: ");
		memberVO.setName(sc.nextLine());

		System.out.print("전화번호 입력: ");
		memberVO.setPhone(sc.nextLine());

		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(memberVO);

		if (result > 0) {
			System.out.println("✅ 회원가입 성공!");
		} else {
			System.out.println("❌ 회원가입 실패...");
		}
	}
}
