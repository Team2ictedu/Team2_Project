 package project_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlacesDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int result = 0 ;
	int count = getCount();
	
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
	
	public int getCount() {
		try {
			conn = getConnection();
			String query = "select count(*) from PLACE_ALL";
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			System.out.println("places count ssis :"+count);
			return count;
		} catch (Exception e) {
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return 0;
	}
	
	public Object[][] getSelectAll() {
		try {
			conn = getConnection();
			String sql = "select * from place_all" ;
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			Object[][] list = new Object[count][6];
			int i = 0;
			while(rs.next()) {
				PlaceVO vo = new PlaceVO();
//				vo.setPlacenumber(rs.getString(1));
//				vo.setPlacename(rs.getString(2));
//				vo.setPlacelocation(rs.getString(3));
//				vo.setPlacedescription(rs.getString(4));
//				vo.setPlaceprice(rs.getString(5));
//				vo.setPlacereview( rs.getString(6));
//				list[i][0] = rs.getString(1);
				list[i][0] = rs.getString(2);
				list[i][1] = rs.getString(3);
				list[i][2] = rs.getString(4);
				list[i][3] = rs.getString(5);
//				list[i][5] = rs.getString(6);
				list[i][4] = "Edit";
				list[i][5] = "Delete";
				i++;
				System.out.println("ss");
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












