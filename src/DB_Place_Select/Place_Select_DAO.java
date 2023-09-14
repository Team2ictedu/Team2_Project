package DB_Place_Select;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

public class Place_Select_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// 사용자 플래너 선택한 관광지
	public static List<Place_Select_VO> getPlace_select(String PLAN_NUM) {
		List<Place_Select_VO> list = null;
		list = getSession().selectList("placeSelect", PLAN_NUM);
		return list;
	}
	
	// 플래너 개인별 관광지리스트
	public static List<Place_Select_VO> getUserPlaceList(String PLAN_NUM) {
		List<Place_Select_VO> list = null;
		list = getSession().selectList("userPlaceList", PLAN_NUM);
		return list;
	}


	// 유저가 모든관광지에서 선택관광지로 추가
	public static int getUserAddPlcae(Place_Select_VO vo) {
		int result = getSession().insert("userAddPlace", vo);
		ss.commit();
		return result;
	}

	// 수정에서 유저가 모든관광지에서 선택관광지로 추가
	public static int getUserAddPlcae2(Place_Select_VO vo) {
		int result = getSession().insert("userAddPlace2", vo);
		ss.commit();
		return result;
	}

	// 수정에서 유저가 캔슬 시 추가한 관광지 삭제
	public static int getUserEditCancle(Place_Select_VO vo) {
		int result = getSession().delete("userEditCancle", vo);
		ss.commit();
		return result;
	}
	
	// 유저가 선택한 관광지 제거
	public static int getUserDeletePlace(String PS_NUM) {
		int result = getSession().delete("userDeletePlace", PS_NUM);
		ss.commit();
		return result;
	}
	
	public static int getUserEditComplete(String PLAN_NUM) {
		int result = getSession().update("userEditComplete", PLAN_NUM);
		ss.commit();
		return result;
	}
	
	// 유저가 선택한 관광지 조회
	public static List<Place_Select_VO> getUserSelectPlace(String PLAN_NUM) {
		List<Place_Select_VO> list = null;
		list = getSession().selectList("userSelectPlace", PLAN_NUM);
		return list;
	}
	
	// 유저가 선택한 관광지 메모, 타임작성
	public static int getUserConTime(Place_Select_VO vo) {
		int result = getSession().update("userConTime", vo);
		return result;
	}
}