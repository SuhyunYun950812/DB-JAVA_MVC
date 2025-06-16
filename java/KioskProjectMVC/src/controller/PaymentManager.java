package controller;

import java.util.ArrayList;

import model.CartVO;
import model.PaymentVO;

public class PaymentManager {

	public void payday(String memberId,String payMethod) {
		CartDAO cd = new CartDAO();
		PaymentDAO pd = new PaymentDAO();
		
		ArrayList<CartVO> cartList = cd.selectCartByMember(memberId);
		
		System.out.println("결제 전 장바구니 상태: " + cartList.size());
		for (CartVO item : cartList) {
		    System.out.println(item);
		}
		if(cartList == null || cartList.size() == 0) {
			System.out.println("장바구니가 비었습니다.");
			return;
		}
		
		int totalAmont = cd.getTotalAmount(memberId);
		
		PaymentVO pv = new PaymentVO();
		pv.setMemberId(memberId);
		pv.setTotalAmount(totalAmont);
		pv.setPayMethod(payMethod);
		pv.setStatus("payDone");
		
		int result = pd.insertPayment(pv);
		
		System.out.println("총 결제 금액 : " + totalAmont);
		
		if (result > 0) {
	        cd.updateCartStatusToPaid(memberId);
	        
	        System.out.println("✅ 결제 완료!");
	    } else {
	        System.out.println("❌ 결제 실패");
	    }
	}
}
