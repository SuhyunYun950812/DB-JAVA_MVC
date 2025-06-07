import java.sql.Connection;
import java.util.Scanner;

import controller.DBUtil;
import controller.LessonManager;
import controller.StudentManager;
import controller.SubjectManager;
import view.Lessonchoice;
import view.Menu;
import view.MenuChoice;
import view.StudentChoice;
import view.SubjectChoice;

public class KHRegisterClassMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Connection con = DBUtil.getConnection();
		int choice = 0;
		boolean exitFlag = false;
		if (con == null) {
			System.out.println("DB 연결에 실패했습니다.");
			return;
		}
		// 화면설계 진행
		while (!exitFlag) {
			try {
				Menu.mainMenu();

				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case MenuChoice.학과관리:
					subjectMenu();
					break;
				case MenuChoice.학생관리:
					StudentMenu();
					break;
				case MenuChoice.과목관리:
					lessonMenu();
					break;
				case MenuChoice.수강관리:
					break;
				case MenuChoice.EXIT:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다. \n 프로그램을 다시 시작하세요.");
				exitFlag = true;
			}
		} // end of while
		System.out.println("수강신청 프로그램 종료");
	}// end of main

	// 학과 관리 메뉴
	public static void subjectMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.subjectMenu();
				SubjectManager sm = new SubjectManager();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case SubjectChoice.학과목록:
					sm.list();
					break;
				case SubjectChoice.학과등록:
					sm.register();
					break;
				case SubjectChoice.학과수정:
					sm.update();
					break;
				case SubjectChoice.학과삭제:
					sm.delete();
					break;
				case SubjectChoice.메인복귀:
					System.out.println("학과관리 종료");
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("학과정보 예외발생 조치바람");
			}
		} // end of while
	} // end of subjectMenu
		// 과목 관리 메뉴

	public static void lessonMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.lessonMenu();
				LessonManager lm = new LessonManager();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case Lessonchoice.과목목록:
					lm.list();
					break;
				case Lessonchoice.과목등록:
					lm.register();
					break;
				case Lessonchoice.과목수정:
					lm.update();
					break;
				case Lessonchoice.과목삭제:
					lm.delete();
					break;
				case SubjectChoice.메인복귀:
					System.out.println("과목관리 종료");
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("과목정보 예외발생 조치바람");
			}
		} // end of while
	} // end of subjectMenu
	
	// 과목 관리 메뉴
	public static void StudentMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.studentMenuView();
				StudentManager sm = new StudentManager();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case StudentChoice.학생목록:
					sm.list();
					break;
				case StudentChoice.학생등록:
					sm.register();
					break;
				case StudentChoice.학생수정:
					sm.update();
					break;
				case StudentChoice.학생삭제:
					sm.delete();
					break;
				case StudentChoice.메인복귀:
					System.out.println("학생관리 종료");
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("학생정보 예외발생 조치바람");
			}
		} // end of while
	} // end of subjectMenu
} // end of class
