package main;

import java.sql.Connection;
import java.util.Scanner;

import controller.DBUtil;
import controller.MemberManager;
import controller.MenuManager;
import model.MemberVO;
import view.AdminChoice;
import view.LoginChoice;
import view.Menu;
import view.MenuChoice;

public class KioskProjectClassMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		MemberManager mm = new MemberManager();
		MemberVO loginUser;
		// 메뉴 고를 때 쓸 변수 선언.
		int choice = 0;
		// 들어가고 나가기는 용 변수 선언
		boolean exitFlag = false;

		if (conn != null) {
			System.out.println("✅ DB 연결 성공!");
		} else {
			System.out.println("❌ DB 연결 실패...");
		}
		while (!exitFlag) {
			try {
				Menu.loginMenu();
				// 처음 화면 함수
				// 메뉴 고를 숫자를 정수형으로 입력
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case LoginChoice.로그인:
					loginUser = mm.loginMember();
					if (loginUser != null) {
						menuMenu(loginUser);
					}
					break;
				case LoginChoice.회원가입:
					mm.registerMember();
					break;
				case LoginChoice.키오스크종료:
					exitFlag = true;
					break;
				} // end of switch case
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다. \n 프로그램을 다시 시작하세요.");
				exitFlag = true;
			} // end of catch
		} // end of while
		System.out.println("키오스크 프로그램 종료");
	} // end of main
		// 메뉴 가져오기 할 예정

	public static void menuMenu(MemberVO mv) {
		// 메뉴 고를 때 쓸 변수 선언.
		MenuManager mm = new MenuManager();
		int choice = 0;
		// 들어가고 나가기는 용 변수 선언
		boolean exitFlag = false;
		while (!exitFlag) {
			Menu.menuMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case MenuChoice.메뉴선택:
				mm.showMenu();
				// 해당 DAO기능 넣기
				break;
			case MenuChoice.장바구니확인:
				// 해당 DAO기능 넣기
				break;
			case MenuChoice.장바구니비우기:
				// 해당 DAO기능 넣기
				break;
			case MenuChoice.결제하기:
				// 해당 DAO기능 넣기
				break;
			case MenuChoice.관리자모드:
				// 0은 일반회원 1은 관리자로 구분
				if (mv.getAuth() == 1) {
					adminMenu();
				} else {
					System.out.println("⚠️ 일반회원은 접근이 불가합니다.");
				}
				break;
			case MenuChoice.로그아웃:
				exitFlag = true;
				break;
			}
		}
	} // end of menuMenu

	public static void adminMenu() {
		MemberManager mmg = new MemberManager();
		MenuManager mm = new MenuManager();
		int adminChoice = 0;
		// 들어가고 나가기는 용 변수 선언
		boolean exitFlag = false;
		while (!exitFlag) {
			Menu.adminMenu();
			
			adminChoice = Integer.parseInt(scan.nextLine());
			switch (adminChoice) {
			case AdminChoice.회원리스트확인:
				mmg.showMember();
				break;
			case AdminChoice.메뉴리스트확인:
				mm.showMenu();
				break;
			case AdminChoice.메뉴추가:
				mm.addMenu();
				break;
			case AdminChoice.메뉴삭제:
				mm.terminateMenu();
				break;
			case AdminChoice.메뉴수정:
				mm.fixMenu();
				break;
			case AdminChoice.하루매출확인:
				// 해당 DAO기능 넣기
				break;
			case AdminChoice.관리자메뉴나가기:
				exitFlag = true;
				break;
			}
		}
	} // end of adminMenu
} // end of class