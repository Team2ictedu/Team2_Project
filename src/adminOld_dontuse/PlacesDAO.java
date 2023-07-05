package adminOld_dontuse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlacesDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int result = 0 ;
	
	private static PlacesDAO PlacesDAO = new PlacesDAO();
	public static PlacesDAO getInstance() {
		return PlacesDAO;
	}
	
	// DB 접속 메서드 
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##team2";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
		}
		return null;
	}
	
	public ArrayList<PlaceVO> getSelectAll() {
		try {
			conn = getConnection();
			String sql = "select * from placetable" ;
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ArrayList<PlaceVO> list = new ArrayList<>();
			while(rs.next()) {
				PlaceVO vo = new PlaceVO();
				vo.setPlacenumber(rs.getString(1));
				vo.setPlacename(rs.getString(2));
				vo.setPlacelocation(rs.getString(3));
				vo.setPlacedescription(rs.getString(4));
				vo.setPlaceprice(rs.getString(5));
				vo.setPlacereview( rs.getString(6));
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return null;
	}
	
	public void deleteRow() {
		
	}
	
}












