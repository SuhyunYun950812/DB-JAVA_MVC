package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MemberVO;

public class MemberDAO {
	
	String insertSQL = "INSERT INTO MEMBER (ID, PWD, NAME, PHONE ,AUTH) VALUES (?, ?, ?, ?, ?)";
	String selectByIdCheckSQL = "SELECT *  FROM MEMBER where id = ?";
	// 신규 가입자 리스트에 추가
	public int insertMember(MemberVO memberVO) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    int count = 0;
	    
	    try {
	        con = DBUtil.getConnection();
	        
	        if (con == null) {
	            System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
	            return -1;
	        }

	        pstmt = con.prepareStatement(insertSQL);
	        pstmt.setString(1, memberVO.getMemberId());
	        pstmt.setString(2, memberVO.getPwd());
	        pstmt.setString(3, memberVO.getName());
	        pstmt.setString(4, memberVO.getPhone());

	        count = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("[에러] 회원 등록 중 오류 발생: " + e.getMessage());
	    } finally {
	        DBUtil.dbClose(con, pstmt);
	    }

	    return count;
	}
	// 동일 아이디 유무 확인.
	public boolean selectByIdCheck(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		MemberVO _memberVO = null;
		boolean idCheck = false;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
			}
			pstmt =  con.prepareStatement(selectByIdCheckSQL); 
			pstmt.setString(1, memberVO.getMemberId());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				idCheck = true;
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return idCheck;
	}
}
