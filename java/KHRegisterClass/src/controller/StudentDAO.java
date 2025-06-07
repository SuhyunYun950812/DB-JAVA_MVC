package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import model.StudentVO;

public class StudentDAO {
	//디생
	//멤버변수
	private String selectSQL = "SELECT * FROM student order by num DESC";
	private String selectByNumSQL = "SELECT *  FROM student where num = ?";
	private String selectByNameSQL = "SELECT *  FROM student where name = ?";
	private String selectByIdCheckSQL = "SELECT *  FROM student where id = ?";
	private String selectBySubjectNumCountSQL = "SELECT LPAD(COUNT(*)+1,4,'0') as student_count FROM STUDENT WHERE SUBJECT_NUM = ?";
	private String insertSQL = "INSERT INTO student VALUES (STUDENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)"; 
	private String updateSQL = "update student set NAME = ?,PHONE = ? where NUM = ?"; 
	private String deleteByNumSQL = "DELETE FROM student WHERE num = ?";
	//학생목록
	public ArrayList<StudentVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
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
                String id = rs.getString("ID");
                String passwd = rs.getString("PASSWD");
                String subjectNum = rs.getString("SUBJECT_NUM");
                String brithday = rs.getString("BIRTHDAY");
                String phone = rs.getString("PHONE");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                Date registerDate = rs.getDate("REGISTER_DATE");

                StudentVO StudentVO = new StudentVO(no, num, name, id, passwd, subjectNum, brithday, phone, address, email, registerDate);
                studentList.add(StudentVO);
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return studentList;
	}
	
	//학생번호 검색(조건:num)
	public StudentVO selectByNum(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		StudentVO _studentVO = null;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectByNumSQL); 
			pstmt.setString(1, studentVO.getNum());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int no = rs.getInt("NO");
                String num = rs.getString("NUM");
                String name = rs.getString("NAME");
                String id = rs.getString("ID");
                String passwd = rs.getString("PASSWD");
                String subjectNum = rs.getString("SUBJECT_NUM");
                String brithday = rs.getString("BIRTHDAY");
                String phone = rs.getString("PHONE");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                Date registerDate = rs.getDate("REGISTER_DATE");
                _studentVO = new StudentVO(no, num, name, id, passwd, subjectNum, brithday, phone, address, email, registerDate);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _studentVO;
	}
	
	//학생번호 검색(조건:name)
	public StudentVO selectByName(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		StudentVO _studentVO = null;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectByNameSQL); 
			pstmt.setString(1, studentVO.getName());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int no = rs.getInt("NO");
                String num = rs.getString("NUM");
                String name = rs.getString("NAME");
                String id = rs.getString("ID");
                String passwd = rs.getString("PASSWD");
                String subjectNum = rs.getString("SUBJECT_NUM");
                String brithday = rs.getString("BIRTHDAY");
                String phone = rs.getString("PHONE");
                String address = rs.getString("ADDRESS");
                String email = rs.getString("EMAIL");
                Date registerDate = rs.getDate("REGISTER_DATE");
                _studentVO = new StudentVO(no, num, name, id, passwd, subjectNum, brithday, phone, address, email, registerDate);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _studentVO;
	}
	
	//학생아이디 점검 검색(조건:IdCheck)
	public boolean selectByIdCheck(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		StudentVO _studentVO = null;
		boolean idCheck = false;
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
			}
			pstmt =  con.prepareStatement(selectByIdCheckSQL); 
			pstmt.setString(1, studentVO.getId());
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
	
	//학생학과 인원수 확인
	public String selectBySubjectNumCount(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		String studentCount = null;
		
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
			}
			pstmt =  con.prepareStatement(selectBySubjectNumCountSQL); 
			pstmt.setString(1, studentVO.getSubjectNum());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				studentCount = rs.getString("STUDENT_COUNT");
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return studentCount;
	}
	
	//학생정보입력
	public int insert(StudentVO studentVO) {
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
			pstmt.setString(1, studentVO.getNum());
            pstmt.setString(2, studentVO.getName());
            pstmt.setString(3, studentVO.getId());
            pstmt.setString(4, studentVO.getPasswd());
            pstmt.setString(5, studentVO.getSubjectNum());
            pstmt.setString(6, studentVO.getBirthDay());
            pstmt.setString(7, studentVO.getPhone());
            pstmt.setString(8, studentVO.getAddress());
            pstmt.setString(9, studentVO.getEmail());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//학생정보수정
	public int update(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL); 
			pstmt.setString(1, studentVO.getName());
			pstmt.setString(2, studentVO.getPhone());
			pstmt.setString(3, studentVO.getNum());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//학생정보삭제
	public int deleteByNum(StudentVO studentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt =  con.prepareStatement(deleteByNumSQL); 
			pstmt.setString(1, studentVO.getNum());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
}