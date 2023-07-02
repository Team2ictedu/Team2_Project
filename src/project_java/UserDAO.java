package project_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UserDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int result = 0 ;
	
	private static UserDAO dao = new UserDAO();
	public static UserDAO getInstance() {
		return dao;
	}
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
	
	// 삽입
		public int getInsert(UserVO vo) {
			try {
				conn = getConnection();
				String sql = "insert into MEMBER(M_ID, M_PW, M_EMAIL, M_NAME, M_BIRTH, M_PHONE, M_TERMS, M_CLASS) values(?,?,?,?,?,?,?,?)";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, vo.getM_ID());
				pstm.setString(2, vo.getM_PW());
				pstm.setString(3, vo.getM_EMAIL());
				pstm.setString(4, vo.getM_NAME());
				pstm.setString(5, vo.getM_BIRTH());
				pstm.setString(6, vo.getM_PHONE());
				pstm.setString(7, vo.getM_TERMS());
				pstm.setString(8, "1");
				int result = pstm.executeUpdate();
				return result;
			} catch (Exception e) {
				System.out.print(e);
			} finally {
				try {
					pstm.close();
					conn.close();
				} catch (Exception e2) {
				}
			}
			return 0;
		}	
}
