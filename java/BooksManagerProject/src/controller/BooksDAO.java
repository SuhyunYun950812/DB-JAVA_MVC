package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BooksVO;

public class BooksDAO {
	//디생
	//멤버변수
	private String selectSQL = "SELECT * FROM BOOKS order by book_id DESC";
	private String selectBookIdSQL = "SELECT *  FROM BOOKS where book_id = ?";
	private String selectTitleSQL = "SELECT *  FROM BOOKS where title = ?";
	private String insertSQL = "INSERT INTO books  VALUES (books_seq.nextval, ?, ?, ?, ?)"; 
	private String updateSQL = "update books set title = ?, PUBLISHER = ? ,YEAR = ?, PRICE = ? where book_id = ?"; 
	private String deleteSQL = "DELETE FROM BOOKS WHERE BOOK_ID = ?";
	//멤버함수
	//도서정보목록
	public ArrayList<BooksVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		ArrayList<BooksVO> booksList = new ArrayList<BooksVO>();
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectSQL); 
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int bookId = rs.getInt("BOOK_ID");
				String title = rs.getString("TITLE");
				String publisher = rs.getString("PUBLISHER");
				String year = rs.getString("YEAR");
				int price = rs.getInt("PRICE");
				BooksVO booksVO = new BooksVO(bookId, title, publisher, year, price);
				booksList.add(booksVO);
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return booksList;
	}
	
	//도서정보목록(조건:book_id)
	public void selectByBookID(BooksVO booksVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return;
			}
			pstmt =  con.prepareStatement(selectBookIdSQL); 
			pstmt.setInt(1, booksVO.getBookId());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int bookId = rs.getInt("BOOK_ID");
				String title = rs.getString("TITLE");
				String publisher = rs.getString("PUBLISHER");
				String year = rs.getString("YEAR");
				int price = rs.getInt("PRICE");
				BooksVO _booksVO = new BooksVO(bookId, title, publisher, year, price);
				System.out.println(_booksVO.toString());
			}else {
				System.out.println("해당되는 book_id 정보가 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
	}
	
	//도서정보검색(조건:title)
	public ArrayList<BooksVO> selectByTitle(BooksVO booksVO) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		ArrayList<BooksVO> booksList = new ArrayList<BooksVO>();
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			pstmt =  con.prepareStatement(selectTitleSQL); 
			pstmt.setString(1, booksVO.getTitle());
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int bookId = rs.getInt("BOOK_ID");
				String title = rs.getString("TITLE");
				String publisher = rs.getString("PUBLISHER");
				String year = rs.getString("YEAR");
				int price = rs.getInt("PRICE");
				BooksVO _booksVO = new BooksVO(bookId, title, publisher, year, price);
				booksList.add(_booksVO);
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return booksList; 
	}
	//도서정보입력
	public int insert(BooksVO booksVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return -1;
			}
			pstmt =  con.prepareStatement(insertSQL); 
			pstmt.setString(1, booksVO.getTitle());
			pstmt.setString(2, booksVO.getPublisher());
			pstmt.setString(3, booksVO.getYear());
			pstmt.setInt(4, booksVO.getPrice());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//도서정보수정
	public int update(BooksVO booksVO) {
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
			pstmt.setString(1, booksVO.getTitle());
			pstmt.setString(2, booksVO.getPublisher());
			pstmt.setString(3, booksVO.getYear());
			pstmt.setInt(4, booksVO.getPrice());
			pstmt.setInt(5, booksVO.getBookId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	//도서정보삭제
	public int deleteBookId(BooksVO booksVO) {
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
			pstmt.setInt(1, booksVO.getBookId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	

}












