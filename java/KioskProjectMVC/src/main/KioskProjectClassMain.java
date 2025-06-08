package main;

import java.sql.Connection;
import controller.DBUtil;
import view.Menu;

public class KioskProjectClassMain {
    public static void main(String[] args) {
    	Menu menu = new Menu();
    	
        Connection conn = DBUtil.getConnection();

        if (conn != null) {
            System.out.println("✅ DB 연결 성공!");
        } else {
            System.out.println("❌ DB 연결 실패...");
        }
        new Menu().loginMenu();;	// 처음 화면 함수
    }
}