package project_admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;





public class AdminConnectionDAO {
	private static SqlSession ss;
	
	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized  static SqlSession getSession() {
		if(ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	
	
	public static List<AdminConnectionVO> getList(){
		List<AdminConnectionVO> list = null;
		list = getSession().selectList("connectionList" );
		return list;
	}
	
	
	
	}