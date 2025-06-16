package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.PaymentVO;

public class PaymentDAO {
	String PaymentSQL ="INSERT INTO PAYMENT VALUES (PAYMENT_SEQ.NEXTVAL, ?, SYSDATE, ?, ?, ?)";
	
	public int insertPayment(PaymentVO pv){
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(PaymentSQL);
			
	        pstmt.setString(1, pv.getMemberId());
	        pstmt.setInt(2, pv.getTotalAmount());
	        pstmt.setString(3, pv.getPayMethod());
	        pstmt.setString(4, pv.getStatus());

	        count = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("[Error] 결제처리 오류: " + e.getMessage());
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
}
