package UserDB;

import org.apache.ibatis.session.SqlSession;

public class UserDAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// 회원가입
	public static int getInsert(UserVO vo) {
		int result = getSession().insert("userIns", vo);
		ss.commit();
		return result;
	}

	// 회원가입 - 아이디 중복체크
	public static int getChk(String M_ID) {
		int result = 0; // 중복일때
		UserVO vo = getSession().selectOne("Chk", M_ID);
		if (vo == null) {
			result = 1; // 중복x
		}
		return result;
	}

	// 유저정보 저장
	public static UserVO getUser(UserVO vo) {
		UserVO vo2 = getSession().selectOne("userInfo", vo);
		return vo2;
	}

	// 마지막 로그인
	public static int getUserLastLogin(String M_ID) {
		try {
			int result = getSession().selectOne("userLastLogin", M_ID);
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 로그인 후 - 비밀번호 변경
	public static int getPWUpdate(UserVO vo) {
		int result = getSession().update("pwUpdate", vo);
		ss.commit();
		return result;
	}

	// 로그인 후 - 유저정보 변경
	public static int getUserUpdate(UserVO vo) {
		int result = getSession().update("userUpdate", vo);
		
		ss.commit();
		return result;
	}

	// 로그인 후 - 회원탈퇴
	public static int getUserDelete(UserVO vo) {
		int result = getSession().update("userDelete", vo);
		ss.commit();
		return result;
	}
}