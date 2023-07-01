package User;

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
	private static UserDAO UsersDAO = new UserDAO();
	public static UserDAO getInstance() {
		return UsersDAO;
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
	
	// login시 확인
	public UserVO getLogin(String M_ID){
		try {
			conn = getConnection();
			String sql = "select M_ID, M_PW, M_NAME, to_char(M_BIRTH, 'yyyy-mm-dd'), M_EMAIL, M_PHONE, M_TERMS, M_CLASS, DELETE_CON, DELETE_TIME, M_LASTLOGIN from Member where M_ID = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, M_ID);
			rs = pstm.executeQuery();
			UserVO vo = new UserVO();
			while(rs.next()) {
				vo.setM_ID(rs.getString(1));
				vo.setM_PW(rs.getString(2));
				vo.setM_NAME(rs.getString(3));
				vo.setM_BIRTH(rs.getString(4));
				vo.setM_EMAIL(rs.getString(5));
				vo.setM_PHONE(rs.getString(6));
				vo.setM_TERMS(rs.getString(7));
				vo.setM_CLASS(rs.getString(8));
				vo.setDELETE_CON(rs.getString(9));
				vo.setDELETE_TIME(rs.getString(10));
				vo.setM_LASTLOGIN(rs.getString(11));
			}
			return vo;
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
	
	public int getUserUpdate(UserVO vo){
		try {
			conn = getConnection();
			String sql = "update member set m_email=?, m_birth = ?, m_phone =? where m_id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, vo.getM_EMAIL());
			pstm.setString(2, vo.getM_BIRTH());
			pstm.setString(3, vo.getM_PHONE());
			pstm.setString(4, vo.getM_ID());
			int result = pstm.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println(e);
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
