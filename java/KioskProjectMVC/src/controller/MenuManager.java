package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MenuVO;

public class MenuManager {
	public static Scanner scan = new Scanner(System.in);

	public void showMenu() {
		MenuDAO md = new MenuDAO();
		System.out.println("메뉴리스트 출력");
		ArrayList<MenuVO> menuList = md.showMenu();
		if (menuList.size() <= 0) {
			System.out.println("메뉴가 단 1개도 없습니다.");
		} else if (menuList == null) {
			System.out.println("메뉴리스트 에러 발생");
			return;
		}
		for (MenuVO data : menuList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}

	public void addMenu() {
		MenuDAO md = new MenuDAO();
		MenuManager menuManager = new MenuManager();
		MenuVO mv = new MenuVO();
		boolean menuCheck = false;

		String name;
		int price;

		System.out.println();
		System.out.println("메뉴를 추가하도록 하겠습니다.");
		do {
			System.out.print("메뉴명 :");
			name = scan.nextLine();
			mv.setName(name);
			menuCheck = md.selectByMenuCheck(mv);
			if (menuCheck == true) {
				System.out.println("이미 동일한 메뉴가 있습니다.");
			}
		} while (menuCheck == true);
		System.out.print("메뉴가격 :");
		price = Integer.parseInt(scan.nextLine());

		mv.setName(name);
		mv.setPrice(price);

		// 작성한 메뉴를 리스트에 추가
		md.insertMenu(mv);
		System.out.println();
		// 추가되었는지 바로 리스트 출력하기
		showMenu();
		System.out.println();
	}

	public void fixMenu() {
		MenuDAO md = new MenuDAO();
		MenuVO mv = new MenuVO();
		boolean menuCheck = false;

		int id;
		String name;
		int price;

		System.out.println();
		showMenu();
		System.out.println();

		System.out.println("수정할 메뉴의 ID를 입력");
		System.out.print("메뉴 ID :");
		id = Integer.parseInt(scan.nextLine());

		mv.setId(id);
		System.out.println("해당 메뉴를 수정하도록 하겠습니다.");
		do {
			System.out.print("메뉴명 :");
			name = scan.nextLine();
			mv.setName(name);
			menuCheck = md.selectByMenuCheck(mv);
			if (menuCheck == true) {
				System.out.println("이미 동일한 메뉴가 있습니다.");
			}
		} while (menuCheck == true);
		
		System.out.print("메뉴가격 :");
		price = Integer.parseInt(scan.nextLine());

		mv.setName(name);
		mv.setPrice(price);

		int count = md.updateMenu(mv);
		if (count == 0) {
			System.out.println("메뉴수정 실패 다시 확인바람");
			return;
		} else {
			System.out.println("메뉴수정 성공");
		}
		System.out.println();
		// 수정되었는지 확인위해 다시 리스트 출력하기
		showMenu();
		System.out.println();
	}

	public void terminateMenu() {
		MenuDAO md = new MenuDAO();
		MenuVO mv = new MenuVO();

		int id;
		String name;
		int price;

		System.out.println();
		showMenu();
		System.out.println();

		System.out.println("삭제할 메뉴의 ID를 입력");
		System.out.print("메뉴 ID :");
		id = Integer.parseInt(scan.nextLine());

		mv.setId(id);
		System.out.println("해당 메뉴를 삭제하도록 하겠습니다.");

		int count = md.deleteMenu(mv);
		if (count == 0) {
			System.out.printf("메뉴 ID :%s 메뉴삭제 실패 다시 확인바람\n", id);
		} else {
			System.out.printf("메뉴 ID :%s 메뉴삭제 성공\n", id);
		}
		System.out.println();
		// 삭제되었는지 확인위해 다시 리스트 출력하기
		showMenu();
		System.out.println();
	}
}
