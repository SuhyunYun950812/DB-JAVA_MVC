import java.sql.Connection;
import java.util.Scanner;

import controller.BooksManager;
import controller.DBUtil;
import view.BooksChoice;
import view.Menu;
import view.MenuChoice;

public class BooksManagerProjectMain {
	public static Scanner scan = new Scanner(System.in); 
	public static void main(String[] args) {
		int choice = 0;
		boolean exitFlag = false; 
		while(!exitFlag) {
			Menu.mainMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch(choice) {
			case MenuChoice.BOOK_INFO : 
				booksMenu();
				break; 
			case MenuChoice.MEMBER_MANAGE :
				//memberMenu();
				break; 
			case MenuChoice.RENTAL_MANAGE : 
				break; 
			case MenuChoice.EXIT : 
				System.out.println("EXIT");
				exitFlag = true; 
				break; 
			}
			
		}//end of while
		System.out.println("도서관 관리 프로그램 종료");
	}
	
	//도서관리 메뉴
	public static void booksMenu() {
		int choice = 0;
		boolean exitFlag = false; 
		while(!exitFlag) {
			Menu.booksMenu();;
			choice = Integer.parseInt(scan.nextLine());
			switch(choice) {
			case BooksChoice.도서목록 : 
				BooksManager.booksList();
				break; 
			case  BooksChoice.도서입력  :
				BooksManager.booksInsert();
				break; 
			case  BooksChoice.도서수정 : 
				BooksManager.booksUpdate();
				break; 
			case  BooksChoice.도서삭제: 
				BooksManager.booksDelete();
				break; 
			case  BooksChoice.도서검색: 
				BooksManager.booksSearch();;
				break; 
			case  BooksChoice.메인메뉴 :
				System.out.println("도서관리화면 종료");
				exitFlag = true; 
				break; 
			}
			
		}//end of while
	}
}
