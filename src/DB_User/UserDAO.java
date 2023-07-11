package DB_User;

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
	
	// 로그인 전 - 아이디찾기
	public static UserVO getidChk(UserVO vo) {
		UserVO vo3 = getSession().selectOne("idChk", vo);
		return vo3;
	}
	
	// 로그인 전 - 비밀번호 찾기-1(유효성검사)
	public static UserVO getPwFind(UserVO vo) {
		UserVO vo3 = getSession().selectOne("pwfind1", vo);
		return vo3;
	}

	// 로그인 전 - 비밀번호 찾기-2(비밀번호 재설정)
	public static int getPWChange(UserVO vo) {
		int result = getSession().update("pwChg", vo);
		ss.commit();
		return result;
	}
	
	// 로그인
	public static UserVO getLogin(UserVO vo) {
		UserVO vo2 = getSession().selectOne("userLogin", vo);
		return vo2;
	}
	
	// 유저정보 저장
	public static UserVO getUser(String M_ID) {
		UserVO vo2 = getSession().selectOne("userInfo", M_ID);
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