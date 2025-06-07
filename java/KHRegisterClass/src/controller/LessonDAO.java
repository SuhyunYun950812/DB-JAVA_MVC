package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LessonVO;

public class LessonDAO {
	//디생
	//멤버변수
	private String selectSQL = "SELECT * FROM lesson order by no DESC";
	private String selectByAbbreSQL = "SELECT * FROM lesson where abbre = ?";
	private String selectByNameSQL = "SELECT * FROM lesson where name = ?";
	private String insertSQL = "INSERT INTO lesson VALUES (lesson_SEQ.nextval, ?, ?)"; 
	private String updateSQL = "update lesson set abbre = ?, name = ? where no = ?"; 
	private String deleteSQL = "DELETE FROM lesson WHERE no = ?";
	
	//과목목록
	public ArrayList<LessonVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		ArrayList<LessonVO> lessonList = new ArrayList<LessonVO>();
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
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				LessonVO lessonVO = new LessonVO(no, abbre, name);
				lessonList.add(lessonVO);
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return lessonList;
	}
	
	//과목약어 검색(조건:num)
	public LessonVO selectByNum(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		LessonVO _lessonVO = null;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectByAbbreSQL); 
			pstmt.setString(1, lessonVO.getAbbre());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				_lessonVO = new LessonVO(no, abbre, name);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _lessonVO;
	}
	
	//과목이름 검색(조건:name)
	public LessonVO selectByName(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		LessonVO _lessonVO = null;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectByAbbreSQL); 
			pstmt.setString(1, lessonVO.getName());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				_lessonVO = new LessonVO(no, abbre, name);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _lessonVO;
	}
	//과목입력
	public int insert(LessonVO lessonVO) {
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
			pstmt.setString(1, lessonVO.getAbbre());
			pstmt.setString(2, lessonVO.getName());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//과목수정
	public int update(LessonVO lessonVO) {
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
			pstmt.setString(1, lessonVO.getAbbre());
			pstmt.setString(2, lessonVO.getName());
			pstmt.setInt(3, lessonVO.getNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//학과정보삭제
	public int deleteByNo(LessonVO lessonVO) {
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
			pstmt.setInt(1, lessonVO.getNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
}