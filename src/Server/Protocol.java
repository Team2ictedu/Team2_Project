package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import DB_Place_All.Place_All_VO;
import DB_Place_Review.Place_Review_VO;
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
	Place_All_VO allrevo;
	Travel_Location_VO travelvo;
	Place_All_VO pAvo;
	project_admin.AdminUserVO uservo;
	AdminPlaceVO placevo;
	DB_Planner.Planner_VO planvo;
	Travel_Location_VO location_VO;
	Place_Select_VO placeSelectVo;
	Place_All_VO placeAllVO;
	Place_Review_VO reviewVo2;
	public final Place_Review_VO getReviewVo2() {
		return reviewVo2;
	}

	public final void setReviewVo2(Place_Review_VO reviewVo2) {
		this.reviewVo2 = reviewVo2;
	}

	String name;
	String msg;
	String msg2;

	List<AdminPlaceVO> placeList;
	List<Place_All_VO> placeAll;
	List<Travel_Location_VO> locationList;
	List<Place_Review_VO> placeRe;
	List<UserVO> list;	

	public final List<Place_All_VO> getPlaceAllList() {
		return placeAllList;
	}

	public final void setPlaceAllList(List<Place_All_VO> placeAllList) {
		this.placeAllList = placeAllList;
	}

	List<Planner_VO> plannerList;
	List<Place_Review_VO> reviewPlanList;
	List<Place_All_VO> placeallList;
	List<Place_Select_VO> placeSelectList;
	List<Travel_Location_VO> travelLocationList;
	List<Place_All_VO> placeAllList;
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

	public Place_All_VO getpAvo() {
		return pAvo;
	}
	
	public void setpAvo(Place_All_VO pAvo) {
		this.pAvo = pAvo;
	}
	
	public List<Place_Review_VO> getPlaceRe() {
		return placeRe;
	}
	
	public void setPlaceRe(List<Place_Review_VO> placeRe) {
		this.placeRe = placeRe;
	}
	
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
	
	public List<Place_Review_VO> getReviewPlanList() {
		return reviewPlanList;
	}

	public void setReviewPlanList(List<Place_Review_VO> reviewPlanList) {
		this.reviewPlanList = reviewPlanList;
	}
	public List<Place_All_VO> getPlaceallList() {
		return placeallList;
	}

	public void setPlaceallList(List<Place_All_VO> placeallList) {
		this.placeallList = placeallList;
	}
	
	public Place_All_VO getAllrevo() {
		return allrevo;
	}

	public void setAllrevo(Place_All_VO allrevo) {
		this.allrevo = allrevo;
	}
	
	public List<Place_All_VO> getPlaceAll() {
		return placeAll;
	}

	public void setPlaceAll(List<Place_All_VO> placeAll) {
		this.placeAll = placeAll;
	}

	public Travel_Location_VO getTravelvo() {
		return travelvo;
	}

	public void setTravelvo(Travel_Location_VO travelvo) {
		this.travelvo = travelvo;
	}

	public List<Travel_Location_VO> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Travel_Location_VO> locationList) {
		this.locationList = locationList;
	}
	
}