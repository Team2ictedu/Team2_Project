package DB_Travel_Location;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

public class Travel_Location_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// 
	public static Travel_Location_VO getLocation2(String TL_NUM) {
		Travel_Location_VO vo2 = getSession().selectOne("location", TL_NUM);
		return vo2;
	}
	
	// 시티에 관한 town 리스트 조회
	public static List<Travel_Location_VO> getTownList(String City) {
		List<Travel_Location_VO> list = null;
		list = getSession().selectList("townList", City);
		return list;
	}
	
	// 시티 and 타운인 여행위치번호 조회
	public static Travel_Location_VO getTLNumber(Travel_Location_VO vo) {
		Travel_Location_VO vo3 = getSession().selectOne("TLNum", vo);
		System.out.println("DAO" + vo3.getTL_NUM());
		return vo3;
	}
	
	// 선택된콤보박스 읍동 불러오기
	public static List<Travel_Location_VO> getlocation(String city){
		List<Travel_Location_VO> list = null;
		list= getSession().selectList("cityName", city);
			
		return list;
	}
}