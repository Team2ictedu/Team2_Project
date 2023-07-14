package project_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class AdminReviewsDAO {
	private static SqlSession ss;

	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}
	
	public static List<AdminReviewVO> getList(){
		List<AdminReviewVO> list = null;
		list = getSession().selectList("reviewList");
		return list;
	}
	
	
	public static List<AdminReviewVO> getListId(String str){
		List<AdminReviewVO> list = null;
		list = getSession().selectList("reviewListId", str);
		return list;
	}
	public static List<AdminReviewVO> getListNum(String str){
		List<AdminReviewVO> list = null;
		list = getSession().selectList("reviewListNum", str);
		return list;
	}
	
	
	public static int getDelete(AdminReviewVO vo) {
		int result = getSession().delete("reviewDelVo", vo);
		ss.commit();
		return result;
	}
	public static int getDeleteStr(String vo) {
		int result = getSession().delete("reviewDelNum", vo);
		ss.commit();
		System.out.println("getDeleteStr : " + result);
		return result;
	}

}