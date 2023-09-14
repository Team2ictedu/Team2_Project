package DB_Place_All;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

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
		
	// 관광지이름 조회 (콤보박스(PA_NAME))
		public static List<Place_All_VO> getPlacenameList(String M_ID){
			try {
				List<Place_All_VO> list = null;
				list = getSession().selectList("placename", M_ID);
				return list;
			} catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		public static Place_All_VO getPlaceAll(String PA_NUM) {
			Place_All_VO vo3 = getSession().selectOne("placeFind", PA_NUM);
			return vo3;
		}
	// 관광지 이름조회 (전체후기보기)
		public static List<Place_All_VO> getplName() {
			List<Place_All_VO> list = null;
			list = getSession().selectList("plname");
			return list;
		}
			
	// 관광지 검색
		public static List<Place_All_VO> getPlaceFind(String msg) {
			List<Place_All_VO> list = null;
			list = getSession().selectList("placeFindList", msg);
			return list;
		}
	// 관광지 콤보박스
		public static List<Place_All_VO> getCitySer(String city){
			List<Place_All_VO> list = null;
			list = getSession().selectList("citySer", city);
			return list;
		}
					
		public static Place_All_VO getPlaceInfo(String PA_NUM) {
			Place_All_VO vo = getSession().selectOne("placeInfo", PA_NUM);
			return vo;
		}
}