package DB_Planner;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

public class Planner_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	// 새 일정 만들기
	public static int getInsert(Planner_VO vo) {
		try {
			int result = getSession().insert("createplan", vo);
			ss.commit();
		} catch (Exception e) {
			System.out.pr0intln(e);
		}
		return 0;
	}
	
	public static List<Planner_VO> getList(){
		List<Planner_VO> list = null;
		// selectList() : 결과가 하나이상일때 
		// selectOne()  : 반드시 결과가 하나일때
		// 파라미터가 있는 메서드와 파라미터가 없는메서드로 나눈다.
		// 파라미터가 있는 메서드 : selectList("mepper의 id",파라미터);
		// 파라미터가 없는 메서드  : selectList("mepper의 id")
		list = getSession().selectList("plannerList");
		return list;
	}
	
	
	
}