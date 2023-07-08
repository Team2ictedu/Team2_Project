package project_server;

import java.io.Serializable;
import java.util.List;

import Server.Protocol;
import project_admin.AdminPlaceVO;
import project_admin.AdminReviewVO;
import project_admin.AdminUserVO;

public class ProjectProtocol extends Protocol implements Serializable {
	/*	0: 종료
	 * 	1: 
	 * 	2:
	 * 	3:
	 * 	4:
	 * 	5:
	 * 	6:
	 *  
	 *  71: 관광지 테이블 SELECT *
	 *  
	 * */
	
	int cmd;


	int row;
	
	AdminUserVO uservo;
	AdminPlaceVO placevo;
	AdminReviewVO reviewvo;
//	ReviewVO reviewvo;

	String name;
	String msg;
	String msg2;
	
	
	List<AdminUserVO> userList;
	List<AdminPlaceVO> placeList;
	List<AdminReviewVO> reviewList;
	
	
	
	public AdminReviewVO getReviewvo() {
		return reviewvo;
	}
	public void setReviewvo(AdminReviewVO reviewvo) {
		this.reviewvo = reviewvo;
	}
	public List<AdminReviewVO> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<AdminReviewVO> reviewList) {
		this.reviewList = reviewList;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public AdminUserVO getUservo() {
		return uservo;
	}
	public void setUservo(AdminUserVO uservo) {
		this.uservo = uservo;
	}
	public AdminPlaceVO getPlacevo() {
		return placevo;
	}
	public void setPlacevo(AdminPlaceVO placevo) {
		this.placevo = placevo;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AdminUserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<AdminUserVO> userList) {
		this.userList = userList;
	}
	public List<AdminPlaceVO> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<AdminPlaceVO> placeList) {
		this.placeList = placeList;
	}
	
	
	
}
