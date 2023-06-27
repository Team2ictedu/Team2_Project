package admin2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int result = 0 ;
	int count = getCount();
	private static UsersDAO UsersDAO = new UsersDAO();
	public static UsersDAO getInstance() {
		return UsersDAO;
	}
	
	// DB 접속 메서드 
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##shlee";
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
			String query = "select count(*) from usertable";
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			System.out.println("places count is :"+count);
			return count;
		} catch (Exception e) {
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return 0;
	}
	
	
	public Object[][] getSelectAll() {
		try {
			conn = getConnection();
			String sql = "select * from usertable" ;
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			System.out.println("users : "+rs.getFetchSize());
			Object[][] list = new Object[count][9];
			int i = 0;
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserid(rs.getString(1));
				vo.setUserpwd(rs.getString(2));
				vo.setUsername(rs.getString(3));
				vo.setUserbirthday(rs.getString(4));
				vo.setUseremail(rs.getString(5));
				vo.setUserphone(rs.getString(6));
				vo.setUserterms(rs.getString(7));
				list[i][0] = rs.getString(1);
				list[i][1] = rs.getString(2);
				list[i][2] = rs.getString(3);
				list[i][3] = rs.getString(4);
				list[i][4] = rs.getString(5);
				list[i][5] = rs.getString(6);
				list[i][6] = rs.getString(7);
				list[i][7] = "Edit";
				list[i][8] = "Delete";
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
	
}












