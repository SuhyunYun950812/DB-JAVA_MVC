package view;

import java.util.Scanner;

public class Menu{
	//메인메뉴
	public static void mainMenu() { 
		System.out.println("도서관 관리 프로그램"); 
		System.out.println("해당 번호를 입력하세요."); 
		System.out.println("1. 도서 정보 목록/입력/수정/삭제/검색"); 
		System.out.println("2. 회원 정보 목록/입력/수정/삭제"); 
		System.out.println("3. 대여 정보 목록/입력/수정/삭제"); 
		System.out.println("4. 프로그램 종료"); 
		System.out.print("번호 선택 : ");
	}
	
	//도서정보메뉴
	public static void booksMenu() { 
		System.out.println("도서정보 관리 프로그램"); 
		System.out.println("해당 번호를 입력하세요."); 
		System.out.println("1. 도서 정보 목록"); 
		System.out.println("2. 도서 정보 입력"); 
		System.out.println("3. 도서 정보 수정"); 
		System.out.println("4. 도서 정보 삭제"); 
		System.out.println("5. 도서 정보 검색"); 
		System.out.println("6. 메인 메뉴"); 
		System.out.print("번호 선택 : ");
	}

}
