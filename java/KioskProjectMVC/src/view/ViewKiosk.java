package view;

import java.util.List;
import java.util.Scanner;

import model.MenuVO;

public class ViewKiosk {
    private Scanner sc = new Scanner(System.in);

    public void showMenuList(List<MenuVO> menuVO) {
        System.out.println("=====  메뉴 목록 =====");
        for (MenuVO item : menuVO) {
            System.out.println(item);
        }
        System.out.println("========================");
    }

    public int inputMenuSelection() {
        System.out.print("메뉴 번호 선택 (0: 종료): ");
        return Integer.parseInt(sc.nextLine());
    }
}