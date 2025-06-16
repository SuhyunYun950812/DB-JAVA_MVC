package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MenuVO;

public class MenuDAO {
	// 할것 : 메뉴 목록&담기 ,메뉴 추가, 메뉴 삭제 ,메뉴 수정
	String ShowMenuSQL = "SELECT * FROM MENU ORDER BY ID";
	String selectByMenuCheckSQL = "SELECT *  FROM MENU WHERE NAME = ?";
	String selectByMenuIdSQL = "SELECT * FROM MENU WHERE ID = ?";
	String InsertMenuSQL = "INSERT INTO MENU (NAME, PRICE) VALUES (?, ?)";
	String UpdateMenuSQL = "UPDATE MENU SET NAME = ?,PRICE = ? WHERE ID = ?";
	String DeleteMenuSQL = "DELETE FROM MENU WHERE ID = ?";

	// 신규 메뉴 리스트에 추가
	public int insertMenu(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
				return -1;
			}
			pstmt = con.prepareStatement(InsertMenuSQL);
			pstmt.setString(1, menuVO.getName());
			pstmt.setInt(2, menuVO.getPrice());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[Error] 메뉴 등록 중 오류 발생: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}

		return count;
	}

	public ArrayList<MenuVO> showMenu() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다.");
				return null;
			}
			pstmt = con.prepareStatement(ShowMenuSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");

				MenuVO mv = new MenuVO(id, name, price);
				menuList.add(mv);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return menuList;
	}

	// 동일 메뉴 유무 확인
	public boolean selectByMenuCheck(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean menuCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
			}
			pstmt = con.prepareStatement(selectByMenuCheckSQL);
			pstmt.setString(1, menuVO.getName());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				menuCheck = true;
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return menuCheck;
	}

	public MenuVO selectByMenuId(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO mv = null;

		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
				return null;
			}
			
			pstmt = con.prepareStatement(selectByMenuIdSQL);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mv = new MenuVO();
				mv.setId(rs.getInt("ID"));
				mv.setName(rs.getString("NAME"));
				mv.setPrice(rs.getInt("PRICE"));
			}else {
				System.out.println("❌ menuId " + id + "에 해당하는 메뉴가 없습니다.");
			}
			
		} catch (SQLException e) {	
			System.out.println("[Error] 메뉴 ID 조회 실패: " + e.getMessage	());
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return mv;
	}
	
	public int updateMenu(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
				return -1;
			}
			pstmt = con.prepareStatement(UpdateMenuSQL);
			pstmt.setString(1, menuVO.getName());
			pstmt.setInt(2, menuVO.getPrice());
			pstmt.setInt(3, menuVO.getId());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[Error] 메뉴 수정 중 오류 발생: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}

		return count;
	}
	public int deleteMenu(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
				return -1;
			}
			pstmt = con.prepareStatement(DeleteMenuSQL);
			pstmt.setInt(1, menuVO.getId());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[Error]  메뉴 삭제 중 오류 발생: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}

		return count;
	}
}
