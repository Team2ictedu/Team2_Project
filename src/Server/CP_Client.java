package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import DB_User.UserDAO;
import DB_User.UserVO;
import project_admin.AdminPlaceVO;
import project_admin.AdminPlacesDAO;
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
						p.setVo(UserDAO.getUser(vo));
						p.setCmd(5);
						out.writeObject(p);
						out.flush();
						break;
					case 6: // 유저 정보변경
						UserDAO.getUserUpdate(vo);
						p.setVo(UserDAO.getUser(vo));
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
						String str53 = p53.getMsg2();
						if (p53.getMsg().equalsIgnoreCase("관광지이름")) {
							System.out.println("place location check");
							List<AdminPlaceVO> list53 = AdminPlacesDAO.getListName(str53);
							if (list53 != null) {
								System.out.println("VO SELECTED. VO EMAIL IS : " + p53.getMsg2());
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
								System.out.println("VO SELECTED. VO EMAIL IS : " + p53.getMsg2());
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
								System.out.println("VO SELECTED. VO EMAIL IS : " + p53.getMsg2());
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
							System.out.println("DELETE COMPLETEEE");
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
						if (p73.getMsg().equalsIgnoreCase("id")) {
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
						if (p73.getMsg().equalsIgnoreCase("name")) {
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
						if (p73.getMsg().equalsIgnoreCase("email")) {
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
							System.out.println("DELETE COMPLETEEE");
							list = AdminUsersDAO.getList();
							p83.setCmd(82);
							p83.setUserList(list);
							out.writeObject(p83);
							out.flush();
						}
						break;
					case 202: // 비밀번호 변경
						UserDAO.getPWUpdate(vo);
						p.setVo(UserDAO.getUser(vo));
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
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}