package DB_Place_All;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;
import DB_User.UserVO;

public class Place_All_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	// 리뷰 전체 보기
	public static List<Place_All_VO> getAllRev() {
		List<Place_All_VO> list = null;
		list = getSession().selectList("allReview");
		return list;
	}
	
	// 콤보 박스에 db값 넣기
	public static List<Place_All_VO> getCity() {
		List<Place_All_VO> list = null;
		list = getSession().selectList("combo1");
		return list;
	}
	
	
}