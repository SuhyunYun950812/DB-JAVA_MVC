package view;

import java.util.Scanner;

import controller.MemberManager;

public class Menu {
	public static Scanner scan = new Scanner(System.in);
	MemberManager memberManager = new MemberManager();
	// 로그인 메뉴
	public static void loginMenu() {
		System.out.println();
		System.out.println("-------------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 키오스크 종료");
		System.out.println("-------------------");
		System.out.print(">> ");
	}

	// 메뉴
	public static void menuMenu() {
		System.out.println();
		System.out.println("-------------------");
		System.out.println("1. 메뉴선택");
		System.out.println("2. 장바구니확인");
		System.out.println("3. 장바구니비우기");
		System.out.println("4. 결제하기(매장&포장)");
		System.out.println("5. 관리자모드");
		System.out.println("6. 로그아웃");
		System.out.println("-------------------");
		System.out.print("번호 선택 : ");
	}
	// 관리자 메뉴
	public static void adminMenu() {
        System.out.println();
        System.out.println("과목 정보 메뉴 번호를 입력하세요.");
        System.out.println("1. 회원리스트확인");
        System.out.println("2. 메뉴리스트확인");
        System.out.println("3. 메뉴추가");
        System.out.println("4. 메뉴삭제");
        System.out.println("5. 메뉴수정");
        System.out.println("6. 하루매출확인");
        System.out.println("7. 뒤로가기");
        System.out.print("번호 선택 : ");
    }
}