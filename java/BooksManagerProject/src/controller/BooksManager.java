package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.BooksVO;

public class BooksManager {
	public static Scanner scan = new Scanner(System.in); 
	
	//도서목록메니저
	public static void booksList() {
		BooksDAO booksDAO = new BooksDAO();
		System.out.println("****도서정보리스트****");
		ArrayList<BooksVO> _booksList =  booksDAO.selectAll();
		if(_booksList.size() <= 0 || _booksList == null) {
			System.out.println("책정보리스트에서 오류가 발생했습니다.");
			return; 
		}
		for(BooksVO data  : _booksList) {
			System.out.println(data.toString());
		}
		
	}
	
	//도서입력매니저
	public static void booksInsert() {
		BooksDAO booksDAO = new BooksDAO();
		BooksVO booksVO = null;
		//사용자가 입력
		System.out.print("input title :");
		String title = scan.nextLine();
		System.out.print("input publisher :");
		String publisher = scan.nextLine();
		System.out.print("input year :");
		String year = scan.nextLine();
		System.out.print("input price :");
		int price = Integer.parseInt(scan.nextLine());
		booksVO = new BooksVO(0, title, publisher, year, price);
		int count = booksDAO.insert(booksVO);
		if(count == 0) {
			System.out.println("입력에 문제발생하였습니다.");
		}else {
			System.out.println("입력성공하였습니다.");
		}
	}
	//도서수정매니저
	public static void booksUpdate() {
		BooksDAO booksDAO = new BooksDAO();
		BooksVO booksVO = null;
		//전체내용리스트
		booksList();
		//선택책정보
		System.out.print("수정할 번호를 선택해주세요 :");
		int bookId = Integer.parseInt(scan.nextLine());
		booksVO = new BooksVO(); 
		booksVO.setBookId(bookId);
		booksDAO.selectByBookID(booksVO);
		
		//사용자가 입력
		System.out.print("update title :");
		String title = scan.nextLine();
		System.out.print("update publisher :");
		String publisher = scan.nextLine();
		System.out.print("update year :");
		String year = scan.nextLine();
		System.out.print("update price :");
		int price = Integer.parseInt(scan.nextLine());
		
		booksVO = new BooksVO(bookId, title, publisher, year, price);
		int count = booksDAO.update(booksVO);
		if(count == 0) {
			System.out.println("수정에 문제발생하였습니다.");
		}else {
			System.out.println("수정성공하였습니다.");
		}
	}
	//도서삭제매니저
	public static void booksDelete() {
		BooksDAO booksDAO = new BooksDAO();
		BooksVO booksVO = null;
		//전체내용리스트
		booksList();
		//선택책정보
		System.out.print("삭제할 번호를 선택해주세요 :");
		int bookId = Integer.parseInt(scan.nextLine());
		booksVO = new BooksVO(); 
		booksVO.setBookId(bookId);
		int count = booksDAO.deleteBookId(booksVO);

		if(count == 0) {
			System.out.println("삭제에 문제발생하였습니다.");
		}else {
			System.out.println("삭제성공하였습니다.");
		}
	}
	
	//도서검색매니저
	public static void booksSearch() {
		BooksDAO booksDAO = new BooksDAO();
		BooksVO booksVO = null;
		
		System.out.print("검색할 TITLE를 입력해주세요 :");
		String title = scan.nextLine();
		booksVO = new BooksVO(); 
		booksVO.setTitle(title);
		
		ArrayList<BooksVO> booksList =  booksDAO.selectByTitle(booksVO);
		if(booksList.size() <= 0 || booksList == null) {
			System.out.println("책정보 검색에 오류가 발생했습니다.");
			return; 
		}
		for(BooksVO data  : booksList) {
			System.out.println(data.toString());
		}
	}
}
