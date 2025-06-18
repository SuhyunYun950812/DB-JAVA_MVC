package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PayLogVO;

public class PayLogDAO {

	String SelectPayLogAllSQL = "SELECT * FROM PAYLOG ORDER BY LOG_TIME";
	String SelectPayLogByMemberSQL = "SELECT * FROM PAYLOG ORDER BY LOG_TIME";
	String SelectPayLogByDateSQL = "SELECT * FROM PAYLOG ORDER BY LOG_TIME";

	private ArrayList<PayLogVO> selectPayLogAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PayLogVO> PayLogList = new ArrayList<PayLogVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다.");
				return null;
			}
			pstmt = con.prepareStatement(SelectPayLogAllSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int logId = rs.getInt("LOG_ID");
				int payId = rs.getInt("PAY_ID");
				int menuId = rs.getInt("MENU_ID");
				String menuName = rs.getString("MENU_NAME");
				int quantity = rs.getInt("QUANTITY");
				int price = rs.getInt("PRICE");
				String logType = rs.getString("LOG_TYPE");
				Date logDate = rs.getDate("LOG_TIME");
				
				PayLogVO plvo = new PayLogVO(logId,payId,menuId,menuName,quantity,price,logType,logDate);
				PayLogList.add(plvo);
			}
		} catch (SQLException e) {
			System.out.println("createStateMent 오류발생");
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return null;
	}

	private ArrayList<PayLogVO> selectPayLogByMember(String logId) {
		return null;
	}

	private ArrayList<PayLogVO> selectPayLogByDate(Date logTime) {
		return null;
	}
}
