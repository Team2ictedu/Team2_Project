package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import DB_Place_All.Place_All_DAO;
import DB_Place_All.Place_All_VO;
import DB_Place_Review.Place_Review_DAO;
import DB_Place_Review.Place_Review_VO;
import DB_Place_Select.Place_Select_DAO;
import DB_Place_Select.Place_Select_VO;
import DB_Planner.Planner_DAO;
import DB_Planner.Planner_VO;
import DB_Travel_Location.Travel_Location_DAO;
import DB_Travel_Location.Travel_Location_VO;
import DB_User.UserDAO;
import DB_User.UserVO;
import project_admin.AdminPlaceVO;
import project_admin.AdminPlacesDAO;
import project_admin.AdminReviewVO;
import project_admin.AdminReviewsDAO;
import project_admin.AdminUsersDAO;
import project_server.ProjectProtocol;

public class CP_Client extends Thread {
	Socket s;
	DB_Server server;
	ObjectInputStream in;
	ObjectOutputStream out;
	String username;
	String ip;

	public CP_Client(Socket s, DB_Server server) {
		this.s = s;
		this.server = server;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			ip = s.getInetAddress().getHostAddress();
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					Protocol p = (Protocol) obj;
					UserVO vo = p.getVo();
					Planner_VO planvo = p.getPlanvo();// 새일정 만들기 VO
					Place_Review_VO reviewvo = p.getReviewVo2();
					switch (p.getCmd()) {
					case 0:
						out.writeObject(p);
						out.flush();
						break;
					case 1: // 중복제거
						int result = UserDAO.getChk(vo.getM_ID());
						if (result == 0) { // 아이디 중복됨
							p.setCmd(2);
							out.writeObject(p);
							out.flush();
						} else { // 아이디 중복되지 않음
							UserDAO.getInsert(vo);
							p.setCmd(3);
							out.writeObject(p);
							out.flush();
						}
						break;
					case 4: // 유저 로그인
						UserVO vo4 = UserDAO.getLogin(vo);
						p.setVo(vo4);
						if (vo4 == null) {
							p.setCmd(1000);
							out.writeObject(p);
							out.flush();
						} else if (vo4.getM_CLASS().equals("1")) {
							p.setPlannerList(Planner_DAO.getPlannerList(vo4.getM_ID()));
							p.setLocation_VO(Travel_Location_DAO.getLocation2(p.getPlannerList().get(0).getTL_NUM()));
							p.setPlanvo(Planner_DAO.getPlanner(p.getPlannerList().get(0).getPLAN_NUM()));
							p.setPlaceAllVO(Place_All_DAO.getPlaceAll("1"));
							p.setCmd(5);
							out.writeObject(p);
							out.flush();
						} else {
							p.setCmd(5);
							out.writeObject(p);
							out.flush();
						}
						break;
					case 6: // 유저 정보변경
						UserDAO.getUserUpdate(vo);
						p.setVo(UserDAO.getLogin(vo));
						p.setPlannerList(Planner_DAO.getPlannerList(vo.getM_ID()));
						p.setCmd(7);
						out.writeObject(p);
						out.flush();
						break;
					case 8: // 유저 회원탈퇴
						UserDAO.getUserDelete(vo);
						break;
					case 9: // 마지막 로그인
						UserDAO.getUserLastLogin(vo.getM_ID());
						break;
					case 22:
						p.setLocation_VO(Travel_Location_DAO.getLocation2(p.getLocation_VO().getTL_NUM()));
						p.setCmd(23);
						out.writeObject(p);
						out.flush();
						break;
					case 35:
						List<Travel_Location_VO> list35 = Travel_Location_DAO.getTownList(p.getMsg());
						p.setCmd(36);
						p.setTravelLocationList(list35);
						out.writeObject(p);
						out.flush();
						break;
//					case 37:
//						p.setLocation_VO(Travel_Location_DAO.getTLNumber(p.getLocation_VO()));
//						p.setCmd(38);
//						out.writeObject(p);
//						out.flush();
//						break;
//					case 39:
//						int result2 = Planner_DAO.getDeletePlanner(p.getMsg());
//						if(result2 > 0) {
//							// 성공시
//							System.out.println("성공");
//							p.setCmd(40);
//							out.writeObject(p);
//							out.flush();
//						} else {
//							// 실패시
//							p.setCmd(41);
//							System.out.println("실패");
//							out.writeObject(p);
//							out.flush();
//						}
//						break;
					case 40:
						p.setVo(UserDAO.getUser(p.getMsg()));
						p.setPlannerList(Planner_DAO.getPlannerList(p.getMsg()));
						p.setPlanvo(Planner_DAO.getPlanner(p.getPlannerList().get(0).getPLAN_NUM()));
						p.setCmd(41);
						out.writeObject(p);
						out.flush();
						break;
					case 51: // AdminPlaces SELECT * FROM place_all
						System.out.println("ProjectCopyClient running CMD:51 :" + ip);
						ProjectProtocol p51 = new ProjectProtocol();
						List<AdminPlaceVO> list51 = AdminPlacesDAO.getList();
						p51.setCmd(52);
						p51.setPlaceList(list51);
						out.writeObject(p51);
						out.flush();
						break;

					case 53: // AdminPlaces select * from place_all where var = var
						ProjectProtocol p53 = (ProjectProtocol) p;
						System.out.println("ProjectCopyClient running CMD:53 :" + ip);
						String str53 = "%" + p53.getMsg2() + "%";
						if (p53.getMsg().equalsIgnoreCase("관광지이름")) {
							System.out.println("place location check");
							List<AdminPlaceVO> list53 = AdminPlacesDAO.getListName(str53);
							if (list53 != null) {
								System.out.println("VO SELECTED. VO 광광지이름 IS : " + p53.getMsg2());
								p53.setPlaceList(list53);
								p53.setCmd(52);
								out.writeObject(p53);
								out.flush();
							}
						}
						if (p53.getMsg().equalsIgnoreCase("위치")) {
							System.out.println("place location check");
							List<AdminPlaceVO> list53 = AdminPlacesDAO.getListLocation(str53);
							if (list53 != null) {
								System.out.println("VO SELECTED. VO 위치 IS : " + p53.getMsg2());
								p53.setPlaceList(list53);
								p53.setCmd(52);
								out.writeObject(p53);
								out.flush();
							}
						}
						if (p53.getMsg().equalsIgnoreCase("설명")) {
							System.out.println("place desc check");
							List<AdminPlaceVO> list53 = AdminPlacesDAO.getListCon(str53);
							if (list53 != null) {
								System.out.println("VO SELECTED. VO 설명 IS : " + p53.getMsg2());
								p53.setPlaceList(list53);
								p53.setCmd(52);
								out.writeObject(p53);
								out.flush();
							}
						}
						if (p53.getMsg().equalsIgnoreCase("금액")) {
							System.out.println("place price check");
							List<AdminPlaceVO> list53 = AdminPlacesDAO.getListPrice(str53);
							if (list53 != null) {
								System.out.println("VO SELECTED. VO EMAIL IS : " + p53.getMsg2());
								p53.setPlaceList(list53);
								p53.setCmd(52);
								out.writeObject(p53);
								out.flush();
							}
						}

						break;
					case 54: // AdminUsers DELETE row at button

						ProjectProtocol p54 = (ProjectProtocol) p;
						System.out.println("CASe 83 DELETING");
						AdminPlaceVO vo54 = p54.getPlacevo();

						int result54 = AdminPlacesDAO.getDelete(vo54);

						if (result54 > 0) {
							System.out.println("PLACE DELETE COMPLETEEE CMD 54");
							List<AdminPlaceVO> list54 = AdminPlacesDAO.getList();
							p54.setCmd(52);
							p54.setPlaceList(list54);
							out.writeObject(p54);
							out.flush();
						}

						break;

					case 71: // authencity check : is username == "root"?
						System.out.println("ProjectCopyClient running CMD:71 :: " + ip);
						username = p.getName();
						if (username.equals("root")) {
							ProjectProtocol p3 = new ProjectProtocol();
							p3.setCmd(72);
							p3.setName(username);
							out.writeObject(p3);
							out.flush();
						}
						break;

					case 73: // adminUsers select * from MEMEBER WHERE M_ID = $M_ID
						ProjectProtocol p73 = (ProjectProtocol) p;
						System.out.println("ProjectCopyClient running CMD:73 :" + ip);
						String str73 = p73.getMsg2();
						if (p73.getMsg().equalsIgnoreCase("아이디")) {
							System.out.println("id check");
							project_admin.AdminUserVO vo73 = AdminUsersDAO.getOneId(str73);
							if (vo73 != null) {
								System.out.println("VO SELECTED. VO EMAIL IS : " + vo73.getM_email());
								p73.setUservo(vo73);
								p73.setCmd(74);
								out.writeObject(p73);
								out.flush();
							}
						}
						if (p73.getMsg().equalsIgnoreCase("이름")) {
							System.out.println("name check");
							List<project_admin.AdminUserVO> list73 = AdminUsersDAO.getOneName(str73);
							if (list73 != null) {
								System.out.println("VO SELECTED. VO EMAIL IS : " + p73.getMsg2());
								p73.setUserList(list73);
								p73.setCmd(82);
								out.writeObject(p73);
								out.flush();
							}
						}
						if (p73.getMsg().equalsIgnoreCase("이메일")) {
							System.out.println("email check");
							List<project_admin.AdminUserVO> list73 = AdminUsersDAO.getOneEmail(str73);
							if (list73 != null) {
								System.out.println("VO SELECTED. VO EMAIL IS : " + p73.getMsg2());
								p73.setUserList(list73);
								p73.setCmd(82);
								out.writeObject(p73);
								out.flush();
							}
						}

						if (p73.getMsg().equalsIgnoreCase("생년월일")) {
							System.out.println("birthday check");
							List<project_admin.AdminUserVO> list73 = AdminUsersDAO.getOneBirth(str73);
							if (list73 != null) {
								System.out.println("VO SELECTED. VO BIRTHDAY IS : " + p73.getMsg2());
								p73.setUserList(list73);
								p73.setCmd(82);
								out.writeObject(p73);
								out.flush();
							}
						}

						if (p73.getMsg().equalsIgnoreCase("전화번호")) {
							System.out.println("birthday check");
							List<project_admin.AdminUserVO> list73 = AdminUsersDAO.getOnePhone(str73);
							if (list73 != null) {
								System.out.println("VO SELECTED. VO PHONE IS : " + p73.getMsg2());
								p73.setUserList(list73);
								p73.setCmd(82);
								out.writeObject(p73);
								out.flush();
							}
						}

						break;
					case 81: // AdminUsers SELECT * FROM MEMBER
						System.out.println("ProjectCopyClient running CMD:81 :" + ip);
						ProjectProtocol p81 = new ProjectProtocol();
						List<project_admin.AdminUserVO> list = AdminUsersDAO.getList();
						p81.setCmd(82);
						p81.setUserList(list);
						out.writeObject(p81);
						out.flush();
						break;

					case 83: // AdminUsers DELETE row at button
						ProjectProtocol p83 = (ProjectProtocol) p;
						System.out.println("CASe 83 DELETING");
						project_admin.AdminUserVO vo83 = p83.getUservo();
						int result83 = AdminUsersDAO.getDelete(vo83);

						if (result83 > 0) {
							System.out.println("DELETE COMPLETEEE CMD 83");
							list = AdminUsersDAO.getList();
							p83.setCmd(82);
							p83.setUserList(list);
							out.writeObject(p83);
							out.flush();
						}
						break;

					case 84: // insert PLACE into PLACE ALL
						ProjectProtocol p84 = (ProjectProtocol) p;
						System.out.println("Adding Place. CMD:84");
						project_admin.AdminPlaceVO vo84 = p84.getPlacevo();
						try {
							int result84 = AdminPlacesDAO.getInsert(vo84);
							if (result84 > 0) {
								System.out.println("Insert Succesful : CMD 84");
								p84.setCmd(85);
								out.writeObject(p84);
								out.flush();
							}
						} catch (Exception e) {
							System.out.println("insert FAILED CMD 84");
							p84.setCmd(86);
							out.writeObject(p84);
							out.flush();
						}
						break;
					case 87: // insert user into MEMBER
						ProjectProtocol p87 = (ProjectProtocol) p;
						System.out.println("Adding user. CMD: 87");
						project_admin.AdminUserVO vo87 = p87.getUservo();
						try {
							int result87 = AdminUsersDAO.getInsert(vo87);
							if (result87 > 0) {
								System.out.println("Insert Succesful : CMD 87");
								p87.setCmd(88);
								out.writeObject(p87);
								out.flush();
							}
						} catch (Exception e) {
							System.out.println("insert failed CMD 87");
							p87.setCmd(86);
							out.writeObject(p87);
							out.flush();
						}
						break;
					case 89: // update user
						ProjectProtocol p89 = (ProjectProtocol) p;
						project_admin.AdminUserVO vo89 = p89.getUservo();
						System.out.println("before String extraction");
						String cmd89class = AdminUsersDAO.getOneId(vo89.getM_id()).getM_class();
						String resultpwd = AdminUsersDAO.getOneId(vo89.getM_id()).getM_pw();
						System.out.println("to String extraction complete");
						System.out.println("class extracted is " + cmd89class);
						System.out.println("pwd extracted is " + resultpwd);
						String censoredPwd = resultpwd;
						StringBuilder pwd2 = new StringBuilder(censoredPwd);
						if (censoredPwd != null) {
							if (censoredPwd.length() > 2) {
								for (int i = 1; i < resultpwd.length() - 1; i++) {
									pwd2.setCharAt(i, '*');
								}
								censoredPwd = pwd2.toString();
							}
						}
						if (vo89.getM_pw().equals(censoredPwd)) {
							vo89.setM_pw(resultpwd);
							vo89.setM_class(cmd89class);
						} else {
							vo89.setM_class(cmd89class);
						}
						int result89 = AdminUsersDAO.getUpdate(vo89);
						System.out.println("updateDAO success");

						if (result89 > 0) {
							System.out.println("Update Succesful : CMD 89");
							p89.setCmd(88);
							out.writeObject(p89);
							out.flush();
						} else {
							System.out.println("update failed : cmd89");
							p89.setCmd(86);
							out.writeObject(p89);
							out.flush();
						}
						break;

					case 90: // update place
						ProjectProtocol p90 = (ProjectProtocol) p;
						System.out.println("Updating place. CMD 90");
						project_admin.AdminPlaceVO vo90 = p90.getPlacevo();
						vo90.setPa_num(p90.getMsg());

						if (isParsable(vo90.getPa_price()) == true) {
							int result90 = AdminPlacesDAO.getUpdate(vo90);
							if (result90 > 0) {
								System.out.println("Update Succesful : CMD 90");
								p90.setCmd(85);
								out.writeObject(p90);
								out.flush();
							} else {
								System.out.println("update failed : cmd89");
								p90.setCmd(866);
								out.writeObject(p90);
								out.flush();
							}
						} else {
							p90.setCmd(867);
							out.writeObject(p90);
							out.flush();
						}

						break;

					case 91: // place_review select *
						System.out.println("ProjectCopyClient running CMD:91 :" + ip);
						ProjectProtocol p91 = new ProjectProtocol();
						List<AdminReviewVO> list91 = AdminReviewsDAO.getList();
						System.out.println(AdminPlacesDAO.getPlaceName(list91.get(0).getPa_num()));
						for (AdminReviewVO k : list91) {
							k.setPa_name(AdminPlacesDAO.getPlaceName(k.getPa_num()));
						}
						p91.setCmd(92);
						p91.setReviewList(list91);
						out.writeObject(p91);
						out.flush();
						System.out.println("sending cmd 92");
						break;

					case 93: // place_review select * where like
						ProjectProtocol p93 = (ProjectProtocol) p;
						System.out.println("ProjectCopyClient running CMD:93 :" + ip);
						String str93 = p93.getMsg2();
						System.out.println("str93 is " + str93);

						if (p93.getMsg().equalsIgnoreCase("아이디")) {
							System.out.println("review getting * where 아이디");
							List<AdminReviewVO> list93 = AdminReviewsDAO.getListId(str93);
							for (AdminReviewVO k : list93) {
								k.setPa_name(AdminPlacesDAO.getPlaceName(k.getPa_num()));
							}
							if (list93 != null) {
								System.out.println("VO SELECTED. VO 아이디 IS : " + p93.getMsg2());
								p93.setCmd(92);
								p93.setReviewList(list93);
								out.writeObject(p93);
								out.flush();
							}
						}
						if (p93.getMsg().equalsIgnoreCase("관광지")) {
							System.out.println("review getting * where 관광지");
							String str933 = AdminPlacesDAO.getPlaceNum2(str93);
							List<AdminReviewVO> list93 = AdminReviewsDAO.getListNum(str933);
							for (AdminReviewVO k : list93) {
								k.setPa_name(AdminPlacesDAO.getPlaceName(k.getPa_num()));
							}
							if (list93 != null) {
								System.out.println("VO SELECTED. VO 광광지이름 IS : " + p93.getMsg2());
								p93.setCmd(92);
								p93.setReviewList(list93);
								out.writeObject(p93);
								out.flush();
							}
						}
						break;

					case 94: // AdminReviewss DELETE row at button

						ProjectProtocol p94 = (ProjectProtocol) p;
						System.out.println("CASe 94 DELETING");
						AdminReviewVO vo94 = p94.getReviewvo();

						int result94 = AdminReviewsDAO.getDelete(vo94);

						if (result94 > 0) {
							System.out.println("PLACE DELETE COMPLETEEE CMD 54");
							p94.setCmd(95);
							out.writeObject(p94);
							out.flush();
						}

						break;

					case 100: // 새일정만들기
						p.setVo(UserDAO.getUser(planvo.getM_ID()));
						p.setPlannerList(Planner_DAO.getPlannerList(planvo.getM_ID()));
						String Plan_Num = Planner_DAO.getInsert(planvo);
						p.setPlanvo(Planner_DAO.getPlanner(Plan_Num));
						p.setCmd(101);
						out.writeObject(p);
						out.flush();
						break;

					case 202: // 비밀번호 변경
						UserDAO.getPWUpdate(vo);
						p.setVo(UserDAO.getLogin(vo));
						p.setCmd(203);
						out.writeObject(p);
						out.flush();
						break;

					case 204: // 아이디 찾기
						p.setVo(UserDAO.getidChk(vo));
						p.setCmd(205);
						out.writeObject(p);
						out.flush();
						break;

					case 208: // 콤보박스 (PLAN_TITLE)
						p.setPlannerList(Planner_DAO.getPlantitleList(p.getName()));
						p.setCmd(207);
						out.writeObject(p);
						out.flush();
						break;

					case 210: // 콤보박스 (PA_NAME)
						p.setPlaceallList(Place_All_DAO.getPlacenameList(p.getName()));
						p.setCmd(209);
						out.writeObject(p);
						out.flush();
						break;

					case 212: // 내후기 작성
						int result212 = Place_Review_DAO.getReviewIns(reviewvo);
						if (result212 > 0) {
							p.setCmd(211);
							out.writeObject(p);
							out.flush();
						}
						break;

					case 402: // 비밀번호 찾기
						System.out.println(4);
						p.setVo(UserDAO.getPwFind(vo));
						p.setCmd(403);
						out.writeObject(p);
						out.flush();
						break;

					case 404: // 비밀번호 재설정
						System.out.println(5);
						UserDAO.getPWChange(vo);
						p.setCmd(405);
						out.writeObject(p);
						out.flush();
						break;

					case 408: // 후기보기 팝업테이블
						p.setPlaceAll(Place_All_DAO.getplName());
						p.setPlaceRe(Place_Review_DAO.getReviewTb(p.getName()));
						String msg;
						p.setCmd(409);
						out.writeObject(p);
						out.flush();
						break;

					case 410: // 전체후기보기(전체콤보버튼)
						System.out.println(3);
						p.setPlaceAll(Place_All_DAO.getplName());
						System.out.println(p.getPlaceAll().get(0).getPA_NAME());
						p.setCmd(411);
						out.writeObject(p);
						out.flush();
						System.out.println(4);
						break;

					case 412: // 시 테이블
						System.out.println(p.getMsg() + "제주cp-1");
						p.setPlaceAll(Place_All_DAO.getCitySer(p.getMsg()));
						p.setCmd(413);
						out.writeObject(p);
						out.flush();
						break;

					case 420: // 제주시 콤보박스sorting
						System.out.println("select1");
						p.setLocationList(Travel_Location_DAO.getlocation(p.getTravelvo().getCITY()));
						System.out.println(p.getLocationList().get(0).getTOWN());
						p.setCmd(421);
						out.writeObject(p);
						out.flush();
						break;

					case 422: // 서귀포시 콤보박스sorting
						System.out.println("select2");
						p.setLocationList(Travel_Location_DAO.getlocation(p.getTravelvo().getCITY()));
						System.out.println(p.getLocationList().get(0).getTOWN());
						p.setCmd(423);
						out.writeObject(p);
						out.flush();
						break;

					case 424: // 마이리뷰 테이블
						try {
							p.setPlaceRe(Place_Review_DAO.getReview_plan(p.getName()));
							p.setPlannerList(Planner_DAO.getTitle_plan(p.getName()));
							p.setCmd(425);
							out.writeObject(p);
							out.flush();

						} catch (Exception e) {
							System.out.println(e);
						}
						break;

					case 426: // 마이리뷰 삭제버튼
						Place_Review_VO vo1 = p.getReviewVo2();
						System.out.println(vo1.getPR_CON() + "삭제");
						System.out.println(vo1.getPLAN_NUM());
						System.out.println(vo1.getM_ID());
						int result426 = Place_Review_DAO.getReview_del(vo1);

						if (result426 > 0) {
							p.setName(vo1.getM_ID());
							p.setPlaceRe(Place_Review_DAO.getReview_plan(p.getName()));
							p.setPlannerList(Planner_DAO.getTitle_plan(p.getName()));
							p.setCmd(425);
							out.writeObject(p);
							out.flush();
						}
						break;

					case 700: // 후기 불러오기
						Protocol p700 = (Protocol) p;
						UserVO vo700 = p700.getVo();
						System.out.println(vo700.getM_ID());
						List<AdminReviewVO> list700 = AdminReviewsDAO.getListId(vo700.getM_ID());
						for (AdminReviewVO k : list700) {
							k.setPa_name(AdminPlacesDAO.getPlaceName(k.getPa_num()));
						}
						ProjectProtocol p700x = new ProjectProtocol();
						p700x.setReviewList(list700);
						p700x.setCmd(701);
						out.writeObject(p700x);
						out.flush();

						break;

					case 702:
						ProjectProtocol p702 = (ProjectProtocol) p;
						System.out.println("CASe 94 DELETING");
						AdminReviewVO vo702 = p702.getReviewvo();
						System.out.println(vo702.getM_id());
						System.out.println(vo702.getPr_con());

						int result702 = AdminReviewsDAO.getDelete(vo702);

						if (result702 > 0) {
							List<AdminReviewVO> list702 = AdminReviewsDAO.getListId(vo702.getM_id());
							for (AdminReviewVO k : list702) {
								k.setPa_name(AdminPlacesDAO.getPlaceName(k.getPa_num()));
							}
							System.out.println("PLACE DELETE COMPLETEEE CMD 702");
							p702.setReviewList(list702);
							p702.setCmd(701);
							out.writeObject(p702);
							out.flush();
						}
						break;

					case 2000:
						p.setPlaceSelectList(Place_Select_DAO.getUserSelectPlace(p.getMsg()));
						p.setCmd(2001);
						out.writeObject(p);
						out.flush();
						break;
					case 2002:
						Place_Select_DAO.getUserAddPlcae(p.getPlaceSelectVo());
						out.writeObject(p);
						out.flush();
						break;
					case 2003:
						Place_Select_DAO.getUserDeletePlace(p.getMsg());
						break;
					case 2004:
						p.setPlaceAllList(Place_All_DAO.getPlaceFind(p.getMsg()));
						p.setCmd(2005);
						out.writeObject(p);
						out.flush();
						break;
					case 2006:
						Place_Select_DAO.getUserConTime(p.getPlaceSelectVo());
						out.writeObject(p);
						out.flush();
						break;
					case 2007:
						Planner_DAO.getDeletePlanner(p.getMsg());
						break;

					// edit
					case 2010:
						Place_Select_DAO.getUserAddPlcae2(p.getPlaceSelectVo());
						break;
					case 2011:
						p.setPlaceAllList(Place_All_DAO.getPlaceFind(p.getMsg()));
						p.setCmd(2012);
						out.writeObject(p);
						out.flush();
						break;
					case 2013:
						p.setPlaceSelectList(Place_Select_DAO.getUserSelectPlace(p.getMsg()));
						p.setCmd(2014);
						out.writeObject(p);
						out.flush();
						break;
					case 2015:
						Place_Select_DAO.getUserEditComplete(p.getMsg());
						break;
					case 2016:
						Place_Select_DAO.getUserEditCancle(p.getPlaceSelectVo());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}
}