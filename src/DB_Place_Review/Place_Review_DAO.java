package DB_Place_Review;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DB_User.DBService;

public class Place_Review_DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	// 내후기 작성
		public static int getReviewIns(Place_Review_VO vo) {
			try {
				int result = getSession().insert("reviewIns", vo);
				ss.commit();
				return result;
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return 0;
		}
		
		// 리뷰 데이터 테이블
			public static List<Place_Review_VO> getReviewTb(String pa_name){
				List<Place_Review_VO> list = null;
				list = getSession().selectList("reviewTb", pa_name);
				return list;
			}
			
		//id값으로 리뷰 찾기- 1
			public static List<Place_Review_VO> getReview_plan(String m_id){
				List<Place_Review_VO> list = null;
				list = getSession().selectList("review_plan", m_id);
				return list;
			}
			
		// id값,관광지번호,후기내용찾아서 삭제하기
			public static int getReview_del(Place_Review_VO vo){
				int result = getSession().delete("review_del", vo);
				ss.commit();
				return result;
			}

}