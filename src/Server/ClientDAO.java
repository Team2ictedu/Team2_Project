package Server;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

public class ClientDAO {
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	public static int clientOpenInstance(String str) {
		boolean hasDate = hasDate(str);
		if (hasDate == true) {
			int result = getSession().update("dateIncrease",str);
			ss.commit();
			return result;
		} else if (hasDate ==false) {
			int result = getSession().insert("dateInsert", str);
			ss.commit();
			return result;
		} else {
			return -1;
		}
		
	}
	public static boolean hasDate(String str) {
		List<String> list = getSession().selectList("dateHas", str);
		if (list.size()==0) {
			return false;
		} else {
			return true; 
		}
		
	}

}