package adminOld;

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
	
	public ArrayList<UserVO> getSelectAll() {
		try {
			conn = getConnection();
			String sql = "select * from usertable" ;
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ArrayList<UserVO> list = new ArrayList<>();
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserid(rs.getString(1));
				vo.setUserpwd(rs.getString(2));
				vo.setUsername(rs.getString(3));
				vo.setUserbirthday(rs.getString(4));
				vo.setUseremail(rs.getString(5));
				vo.setUserphone(rs.getString(6));
				vo.setUserterms(rs.getString(7));
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
	
}












