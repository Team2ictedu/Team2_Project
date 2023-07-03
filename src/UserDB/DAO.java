package UserDB;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;
	
	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized  static SqlSession getSession() {
		if(ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	public static int getInsert(VO vo) {
		int result = getSession().insert("userIns", vo);
		ss.commit();
		return result;
	}
	
	public static int getIdChk(String M_ID) {
		int result = 0; //중복일때
		VO vo = getSession().selectOne("idChk", M_ID);
		if(vo == null) {
			result = 1; // 중복x
		}
		return result;
	}
}
