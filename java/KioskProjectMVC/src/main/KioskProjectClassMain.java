package main;

import java.sql.Connection;
import java.util.Scanner;

import controller.DBUtil;
import controller.MemberManager;
import view.LoginChoice;
import view.Menu;

public class KioskProjectClassMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		MemberManager mm = new MemberManager(); 
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
				; // 처음 화면 함수
				// 메뉴 고를 숫자를 정수형으로 입력
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case LoginChoice.로그인:
					
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
	public static void menuMenu() {
		// 메뉴 고를 때 쓸 변수 선언.
		int choice = 0;
		// 들어가고 나가기는 용 변수 선언
		boolean exitFlag = false;
		while(!exitFlag) {
			Menu.menuMenu();

		}
	}	// end of menuMenu
} // end of class