package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartVO;
import model.MenuVO;

public class CartDAO {

	String InsertCartSQL = "INSERT INTO CART VALUES (CART_SEQ.NEXTVAL,? , ? ,? ,? , SYSDATE)";
	String SelectCartByMemberSQL = "SELECT * FROM CART WHERE MEMBER_ID = ? AND STATUS = 'IN_CART'";
	String UpdateMemberSQL = "UPDATE CART SET STATUS = 'PAID' WHERE MEMBER_ID = ? AND STATUS = 'IN_CART'";
    String UpdateQUANTITYSQL = "UPDATE CART SET QUANTITY = QUANTITY + ? WHERE ID = ?";
    String FindCartSQL = "SELECT * FROM CART WHERE MEMBER_ID = ? AND MENU_ID = ? AND STATUS = 'IN_CART'";
	String DeleteCartSQL = "DELETE FROM CART WHERE ID = ?";
    
    public int insertCart(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
				return -1;
			}
			pstmt = con.prepareStatement(InsertCartSQL);
			pstmt.setString(1, cartVO.getMemberId());
			pstmt.setInt(2, cartVO.getMenuId());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.setString(4, cartVO.getStatus());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[Error] 카트 생성 중 오류 발생: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}

		return count;
	}

	public ArrayList<CartVO> selectCartByMember(String memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<CartVO> cartList = new ArrayList<>();


		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
				return null;
			}
			pstmt = con.prepareStatement(SelectCartByMemberSQL);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartVO vo = new CartVO();
				vo.setId(rs.getInt("ID"));
				vo.setMemberId(rs.getString("MEMBER_ID"));
				vo.setMenuId(rs.getInt("MENU_ID"));
				vo.setQuantity(rs.getInt("QUANTITY"));
				vo.setStatus(rs.getString("STATUS"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				cartList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("[Error] 장바구니 출력 오류: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}

		return cartList;
	}
	public CartVO findInCart(String memberId, int menuId) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    CartVO vo = null;


	    try {
	        con = DBUtil.getConnection();
	        pstmt = con.prepareStatement(FindCartSQL);
	        pstmt.setString(1, memberId);
	        pstmt.setInt(2, menuId);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	    	    vo = new CartVO();
	    	    vo.setId(rs.getInt("ID"));
	    	    vo.setMemberId(rs.getString("MEMBER_ID"));
	    	    vo.setMenuId(rs.getInt("MENU_ID"));
	    	    vo.setQuantity(rs.getInt("QUANTITY"));
	    	    vo.setStatus(rs.getString("STATUS"));
	    	    vo.setRegDate(rs.getDate("REG_DATE"));
	        }
	    } catch (SQLException e) {
	        System.out.println("[Error] 장바구니 확인 중 오류: " + e.getMessage());
	    } finally {
	        DBUtil.dbClose(con, pstmt, rs);
	    }

	    return vo;
	}
	
	public int updateQuantity(int cartId, int addQty) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    int count = 0;

	    try {
	        con = DBUtil.getConnection();

	        pstmt = con.prepareStatement(UpdateQUANTITYSQL);
	        pstmt.setInt(1, addQty);
	        pstmt.setInt(2, cartId);

	        count = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("[Error] 수량 추가 실패: " + e.getMessage());
	    } finally {
	        DBUtil.dbClose(con, pstmt);
	    }

	    return count;
	}
	
	public int updateCartStatusToPaid(String memberId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;


		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 실패. 관리자에게 문의하세요.");
				return -1;
			}
			pstmt = con.prepareStatement(UpdateMemberSQL);
			
			pstmt.setString(1, memberId);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[Error] 장바구니 상태 업데이트 실패: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}

		return count;
	}
	
	public int deleteCartById(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DeleteCartSQL);
			pstmt.setInt(1, id);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[Error] 장바구니 항목 삭제 오류: " + e.getMessage());
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	public int getTotalAmount(String memberId) {
	    int total = 0;
	    MenuDAO md = new MenuDAO();
	    ArrayList<CartVO> cartList = selectCartByMember(memberId);
	    for (CartVO data : cartList) {
	    	MenuVO mv = md.selectByMenuId(data.getMenuId());
	    	if(mv == null) {
	    		System.out.println("❌ 메뉴 ID " + data.getMenuId() + "에 해당하는 메뉴 정보가 없습니다.");
	    		break;
	    	}
	    	total += mv.getPrice() * data.getQuantity();
	    }
	    return total;
	}
}
