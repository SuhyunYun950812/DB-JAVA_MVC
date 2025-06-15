package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;

public class CartManager {
	public static Scanner scan = new Scanner(System.in);

	public void addToCart(String memberId) {
		CartDAO cd = new CartDAO();

		System.out.print("추가할 메뉴 ID : ");
		int menuId = Integer.parseInt(scan.nextLine());

		System.out.print("수량 입력 : ");
		int quantity = Integer.parseInt(scan.nextLine());

		CartVO existing = cd.findInCart(memberId, menuId);

		if (existing != null) {
			cd.updateQuantity(existing.getId(), menuId);
			System.out.println("해당 메뉴 수량 추가 완료");
		} else {
			CartVO vo = new CartVO();
			vo.setMemberId(memberId);
			vo.setMenuId(menuId);
			vo.setQuantity(quantity);
			vo.setStatus("IN_CART");

			int result = cd.insertCart(vo);
			if (result > 0) {
				System.out.println("✅ 장바구니에 담겼습니다.");
			} else {
				System.out.println("❌ 장바구니 추가 실패");
			}
		}
	}

	public void showCart(String memberId) {
		CartDAO cd = new CartDAO();
		System.out.println("메뉴리스트 출력");
		ArrayList<CartVO> CartList = cd.selectCartByMember(memberId);
		if (CartList.size() <= 0) {
			System.out.println("메뉴가 단 1개도 없습니다.");
		} else if (CartList == null) {
			System.out.println("메뉴리스트 에러 발생");
			return;
		}
		for (CartVO data : CartList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
	
	public void removeFromCart(String memberId) {
		CartDAO cd = new CartDAO();

		System.out.print("삭제할 메뉴 ID: ");
		int menuId = Integer.parseInt(scan.nextLine());

		CartVO item = cd.findInCart(memberId, menuId);

		if (item == null) {
			System.out.println("❌ 해당 메뉴는 장바구니에 없습니다.");
			return;
		}
		
		
		System.out.print("몇 개를 삭제하시겠습니까?: ");
		int qtyToRemove = Integer.parseInt(scan.nextLine());

		if (qtyToRemove > item.getQuantity()) {
			System.out.println("❌ 가진 수량보다 많이 삭제할 수 없습니다.");
			return;
		}

		if (qtyToRemove >= item.getQuantity()) {
			// 전부 삭제
			cd.deleteCartById(item.getId());
			System.out.println("✅ 메뉴가 장바구니에서 삭제되었습니다.");
		} else {
			// 수량만 감소
			cd.updateQuantity(item.getId(), -qtyToRemove);
			System.out.println("✅ 수량이 감소되었습니다.");
		}
	}
}
