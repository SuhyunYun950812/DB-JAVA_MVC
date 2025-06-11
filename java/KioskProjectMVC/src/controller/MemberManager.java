package controller;

import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
	public static Scanner scan = new Scanner(System.in);
	public void loginMember() {
		MemberVO mv = new MemberVO();
		String id;
		String pwd;
		boolean loginCheckFlag = false;
		
		while(!loginCheckFlag) {
		System.out.print("아이디 입력:");
		id = scan.nextLine();
		System.out.print("패스워드 입력:");
		pwd = scan.nextLine();
		}
		for(MemberVO data : MemberVO) {
			if(data 이퀄스?) {
				// 맞으면
				loginCheckFlag = true;
				System.out.println("로그인 성공!");
				break;
			}else //아니면
				System.out.println("아이디 혹은 패스워드가 틀렸습니다.");
		}
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
		
		// 아이디 작성시 패턴검색 추가해야함.
		do {
		System.out.print("아이디 입력: ");
		id = scan.nextLine();
		memberVO.setMemberId(id);
		idCheck = memberDAO.selectByIdCheck(memberVO);
		if (idCheck == true) {
			System.out.println("아이디가 중복 되었습니다.");
		}
		}while(idCheck == true);

		System.out.print("비밀번호 입력: ");
		pwd = scan.nextLine();

		System.out.print("이름 입력: ");
		name = scan.nextLine();

		System.out.print("전화번호 입력: ");
		phone = scan.nextLine();
		
		
		memberVO.setMemberId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setPhone(phone);
		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(memberVO);

		if (result > 0) {
			System.out.println("✅ 회원가입 성공!");
		} else {
			System.out.println("❌ 회원가입 실패...");
		}
	}
}
