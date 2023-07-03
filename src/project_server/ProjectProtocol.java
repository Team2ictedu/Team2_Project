package project_server;

import java.io.Serializable;
import java.util.List;

import adminOld_dontuse.PlaceVO;
import project_admin.UserVO;

public class ProjectProtocol implements Serializable {
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
	
	UserVO uservo;
	PlaceVO placevo;
//	ReviewVO reviewvo;

	String name;
	String msg;
	String msg2;
	

	
	List<UserVO> userList;
	List<PlaceVO> placeList;
	
	
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
	public UserVO getUservo() {
		return uservo;
	}
	public void setUservo(UserVO uservo) {
		this.uservo = uservo;
	}
	public PlaceVO getPlacevo() {
		return placevo;
	}
	public void setPlacevo(PlaceVO placevo) {
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
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public List<PlaceVO> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<PlaceVO> placeList) {
		this.placeList = placeList;
	}
	
	
	
}
