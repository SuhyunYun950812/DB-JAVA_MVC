package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SubjectVO;

public class SubjectDAO {
	//디생
	//멤버변수
	private String selectSQL = "SELECT * FROM subject order by num DESC";
	private String selectByNumSQL = "SELECT *  FROM subject where num = ?";
	private String selectByNameSQL = "SELECT *  FROM subject where name = ?";
	private String insertSQL = "INSERT INTO subject VALUES (SUBJECT_SEQ.nextval, ?, ?)"; 
	private String updateSQL = "update subject set num = ?, name = ? where no = ?"; 
	private String deleteSQL = "DELETE FROM subject WHERE num = ?";
	//학과목록
	public ArrayList<SubjectVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		ArrayList<SubjectVO> subjectList = new ArrayList<SubjectVO>();
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectSQL); 
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int no = rs.getInt("NO");
				String num = rs.getString("NUM");
				String name = rs.getString("NAME");
				SubjectVO subjectVO = new SubjectVO(no, num, name);
				subjectList.add(subjectVO);
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return subjectList;
	}
	
	//학과번호 검색(조건:num)
	public SubjectVO selectByNum(SubjectVO subjectVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		SubjectVO _subjectVO = null;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectByNumSQL); 
			pstmt.setString(1, subjectVO.getNum());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int no = rs.getInt("NO");
				String num = rs.getString("NUM");
				String name = rs.getString("NAME");
				_subjectVO = new SubjectVO(no, num, name);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _subjectVO;
	}
	
	//학과번호 검색(조건:name)
	public SubjectVO selectByName(SubjectVO subjectVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		SubjectVO _subjectVO = null;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectByNumSQL); 
			pstmt.setString(1, subjectVO.getName());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int no = rs.getInt("NO");
				String num = rs.getString("NUM");
				String name = rs.getString("NAME");
				_subjectVO = new SubjectVO(no, num, name);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _subjectVO;
	}
	//학과정보입력
	public int insert(SubjectVO subjectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(insertSQL); 
			pstmt.setString(1, subjectVO.getNum());
			pstmt.setString(2, subjectVO.getName());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//학과정보수정
	public int update(SubjectVO subjectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt =  con.prepareStatement(updateSQL); 
			pstmt.setString(1, subjectVO.getNum());
			pstmt.setString(2, subjectVO.getName());
			pstmt.setInt(3, subjectVO.getNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//학과정보삭제
	public int deleteByNum(SubjectVO subjectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt =  con.prepareStatement(deleteSQL); 
			pstmt.setString(1, subjectVO.getNum());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
}