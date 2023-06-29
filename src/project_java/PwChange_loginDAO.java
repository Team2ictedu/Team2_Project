package project_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PwChange_loginDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int result = 0;
	int count = getCount();
	private static PwChange_loginDAO PwChange_loginDAO = new PwChange_loginDAO();

	public static PwChange_loginDAO getInstance() {
		return PwChange_loginDAO;
	}

	// DB 접속 메소드
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##team2";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
		}
		return null;
	}

	private int getCount() {
		try {
			conn = getConnection();
			String query = "select * from member";
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			System.out.println("places count is :" + count);
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
}
