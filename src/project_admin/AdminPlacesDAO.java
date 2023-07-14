package project_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;





public class AdminPlacesDAO {
	private static SqlSession ss;
	
	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized  static SqlSession getSession() {
		if(ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	
	
	public static List<AdminPlaceVO> getList(){
		List<AdminPlaceVO> list = null;
		// selectList() : 결과가 하나이상일때 
		// selectOne()  : 반드시 결과가 하나일때
		// 파라미터가 있는 메서드와 파라미터가 없는메서드로 나눈다.
		// 파라미터가 있는 메서드 : selectList("mepper의 id",파라미터);
		// 파라미터가 없는 메서드  : selectList("mepper의 id")
		list = getSession().selectList("placesList");
		return list;
	}
	
	public static List<AdminPlaceVO> getListName(String str){
		List<AdminPlaceVO> list = null;
		list = getSession().selectList("placesListName", str);
		return list;
	}
	
	public static List<AdminPlaceVO> getListLocation(String str){
		List<AdminPlaceVO> list = null;
		list = getSession().selectList("placesListLocation", str);
		return list;
	}
	public static List<AdminPlaceVO> getListCon(String str){
		List<AdminPlaceVO> list = null;
		list = getSession().selectList("placesListCon", str);
		return list;
	}
	public static List<AdminPlaceVO> getListPrice(String str){
		List<AdminPlaceVO> list = null;
		list = getSession().selectList("placesListPrice", str);
		return list;
	}
	
	public static String getPlaceNum(AdminPlaceVO vo) {
		String str = "";
		str = getSession().selectOne("placeNum", vo);
		return str;
	}
	
	public static String getPlaceNum2(String str) {
		String str1 = "";
		str1 = getSession().selectOne("getPlacesNum", str);
		return str1;
	}
	
	
	public static String getPlaceName(String str) {
		String str1 = getSession().selectOne("getPlaceName" , str);
		return str1;
	}
	
	public static int getDelete(AdminPlaceVO vo) {
		int result = getSession().delete("placeDelVo", vo);
		ss.commit();
		return result;
	}
	public static int getDeleteStr(String vo) {
		int result = getSession().delete("placeDelNum", vo);
		ss.commit();
		return result;
	}
	
	public static int getInsert(AdminPlaceVO vo) {
		int result = getSession().insert("placeInsert", vo);
		ss.commit();
		return result;
	}
	public static int getUpdate(AdminPlaceVO vo) {
		int result = getSession().update("placeUpdate", vo);
		ss.commit();
		return result;
	}
	
	
	
	
	}