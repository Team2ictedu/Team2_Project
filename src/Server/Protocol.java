package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import DB_Place_All.Place_All_VO;
import DB_Place_Select.Place_Select_VO;
import DB_Planner.Planner_VO;
import DB_Travel_Location.Travel_Location_VO;
import DB_User.UserVO;
import project_admin.AdminPlaceVO;

public class Protocol implements Serializable {
	// 1.전체보기, 2.삽입하기, 3.삭제, 4.검색, 5.고치기
	int cmd;
	int result;
	int row;
	UserVO vo;
	project_admin.AdminUserVO uservo;
	AdminPlaceVO placevo;
	DB_Planner.Planner_VO planvo;
	Travel_Location_VO location_VO;
	Place_Select_VO placeSelectVo;
	Place_All_VO placeAllVO;

	String name;
	String msg;
	String msg2;

	List<UserVO> list;	
	List<Planner_VO> plannerList;
	List<Place_Select_VO> placeSelectList;
	List<Travel_Location_VO> travelLocationList;
//	ReviewVO reviewvo;

	public List<Travel_Location_VO> getTravelLocationList() {
		return travelLocationList;
	}

	public void setTravelLocationList(List<Travel_Location_VO> travelLocationList) {
		this.travelLocationList = travelLocationList;
	}

	public Place_All_VO getPlaceAllVO() {
		return placeAllVO;
	}

	public void setPlaceAllVO(Place_All_VO placeAllVO) {
		this.placeAllVO = placeAllVO;
	}

	public Travel_Location_VO getLocation_VO() {
		return location_VO;
	}

	public void setLocation_VO(Travel_Location_VO location_VO) {
		this.location_VO = location_VO;
	}

	public DB_Planner.Planner_VO getPlanvo() {
		return planvo;
	}

	public void setPlanvo(DB_Planner.Planner_VO planvo) {
		this.planvo = planvo;
	}

	public Place_Select_VO getPlaceSelectVo() {
		return placeSelectVo;
	}

	public void setPlaceSelectVo(Place_Select_VO placeSelectVo) {
		this.placeSelectVo = placeSelectVo;
	}

	public List<Place_Select_VO> getPlaceSelectList() {
		return placeSelectList;
	}

	public void setPlaceSelectList(List<Place_Select_VO> placeSelectList) {
		this.placeSelectList = placeSelectList;
	}

	public List<Planner_VO> getPlannerList() {
		return plannerList;
	}

	public void setPlannerList(List<Planner_VO> plannerList) {
		this.plannerList = plannerList;
	}

	List<AdminPlaceVO> placeList;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public project_admin.AdminUserVO getUservo() {
		return uservo;
	}

	public void setUservo(project_admin.AdminUserVO uservo) {
		this.uservo = uservo;
	}

	public AdminPlaceVO getPlacevo() {
		return placevo;
	}

	public void setPlacevo(AdminPlaceVO placevo) {
		this.placevo = placevo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<AdminPlaceVO> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<AdminPlaceVO> placeList) {
		this.placeList = placeList;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<UserVO> getList() {
		return list;
	}

	public void setList(List<UserVO> list) {
		this.list = list;
	}

	public UserVO getVo() {
		return vo;
	}

	public void setVo(UserVO vo) {
		this.vo = vo;
	}
}