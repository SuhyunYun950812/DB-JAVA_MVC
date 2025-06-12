package controller;

public class MenuDAO {
	// 할것 : 메뉴 목록&담기 ,메뉴 추가, 메뉴 삭제 ,메뉴 수정
	String SelectMenuSQL = "SELECT * FROM MENU ORDER BY ID";
	String InsertMenuSQL = "INSERT INTO MENU VALUES(MENU_SEQ.NEXTVAL,?,?)";
	String UpdateMenuSQL = "UPDATE MENU SET NAME = ?,PRICE = ? WHERE ID = ?";
	String DeleteMenuSQL = "DELETE FROM MENU WHERE ID = ?";
}
