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
	public static String getInsert(Planner_VO vo) {
	        int result = getSession().insert("createplan", vo);
	        ss.commit();
	        // 추가된 부분: 생성된 PLAN_NUM 값을 반환
	        return vo.getPLAN_NUM();
	}
	
	// 사용자 모든 플래너 조회
	public static List<Planner_VO> getPlannerList(String M_ID){
		List<Planner_VO> list = null;
		list = getSession().selectList("plannerList", M_ID);
		return list;
	}
	
	// 플래너 한개 정보조회
	public static Planner_VO getPlanner(String PLAN_NUM) {
		Planner_VO vo2 = getSession().selectOne("planner", PLAN_NUM);
		return vo2;
	}
	
	// 플래너 삭제
	public static int getDeletePlanner(String PLAN_NUM) {
		int result = getSession().delete("deletePlanner", PLAN_NUM);
		ss.commit();
		return result;
	}
	
}