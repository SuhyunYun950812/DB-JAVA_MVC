package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.MemberVO;

public class MemberDAO {
	
	public int insertMember(MemberVO member) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    int count = 0;
	    
	    String insertSQL = "INSERT INTO MEMBER (ID, PWD, NAME, PHONE) VALUES (?, ?, ?, ?)";

	    try {
	        con = DBUtil.getConnection();
	        
	        if (con == null) {
	            System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
	            return -1;
	        }

	        pstmt = con.prepareStatement(insertSQL);
	        pstmt.setString(1, member.getId());
	        pstmt.setString(2, member.getPwd());
	        pstmt.setString(3, member.getName());
	        pstmt.setString(4, member.getPhone());

	        count = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("[에러] 회원 등록 중 오류 발생: " + e.getMessage());
	    } finally {
	        DBUtil.dbClose(con, pstmt);
	    }

	    return count;
	}
	
}
