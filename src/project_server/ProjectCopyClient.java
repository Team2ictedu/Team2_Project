package project_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import project_admin.AdminPlaceVO;
import project_admin.AdminPlacesDAO;
import project_admin.AdminUserVO;
import project_admin.AdminUsersDAO;

public class ProjectCopyClient extends Thread{
	Socket s;
	ProjectServer server;
	ObjectInputStream in ;
	ObjectOutputStream out;
	String username;
	String ip;
	
	public ProjectCopyClient(Socket s, ProjectServer server) {
		this.s = s;
		this.server = server;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			ip = s.getInetAddress().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void run() {
		esc: while(true) {
			try {
				/*	0: 종료
				 * 	1: 로그인
				 * 	2:
				 * 	3:
				 * 	4:
				 * 	5:
				 * 	6:
				 *  
				 *  71: 관광지 테이블 SELECT *
				 *  
				 * */
				Object obj = in.readObject();
				if(obj != null) {
					
					ProjectProtocol p = (ProjectProtocol) obj;
					switch(p.getCmd()) {
						case 0: //종료
							out.writeObject(p);
							out.flush();
							break esc;
						case 1: //로그인 get name
							username = p.getName();
							System.out.println("ProjectCopyClient set name to "+ username);
							ProjectProtocol p2 = new ProjectProtocol();
							p2.setName(username);
							out.writeObject(p2);
							out.flush();
							break;
							
						case 3:  // 회원가입
							ProjectProtocol p3_1 = p;
							boolean idchk = AdminUsersDAO.getIdChk(p.getMsg());
							if(idchk) {
								System.out.println("m_id 존재하지 않습니다");
								AdminUsersDAO.getInsert(p3_1.getUservo());
								p3_1.setCmd(5);
								out.writeObject(p3_1);
								out.flush();
								
							}else {
								System.err.println("m_id가 존재합니다");
								ProjectProtocol p3_2 = new ProjectProtocol();
								p3_2.setCmd(4);
								out.writeObject(p3_2);
								out.flush();
							}
							
							break;
							
							
						case 51: //AdminPlaces SELECT * FROM place_all
							System.out.println("ProjectCopyClient running CMD:51 :" + ip);
							ProjectProtocol p51 = new ProjectProtocol();
							List<AdminPlaceVO> list51 = AdminPlacesDAO.getList();
							p51.setCmd(52);
							p51.setPlaceList(list51);
							out.writeObject(p51);
							out.flush();							
							break;
							
						case 53: //AdminPlaces select * from place_all where var = var
							ProjectProtocol p53 = p;
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
						case 54: //AdminUsers DELETE row at button
							
							ProjectProtocol p54 = p;
							System.out.println("CASe 83 DELETING");
							AdminPlaceVO vo54 = p54.getPlacevo();
							int result54 = AdminPlacesDAO.getDelete(vo54);
							
							if(result54>0) {
								System.out.println("DELETE COMPLETEEE");
								List<AdminPlaceVO> list54 = AdminPlacesDAO.getList();
								p54.setCmd(52);
								p54.setPlaceList(list54);
								out.writeObject(p54);
								out.flush();
							}
							break;
							
							
						case 71: //authencity check : is username == "root"? 
							System.out.println("ProjectCopyClient running CMD:71 :: " + ip);
							username = p.getName();
							if(username.equals("root")) {
								ProjectProtocol p3 = new ProjectProtocol();
								p3.setCmd(72);
								p3.setName(username);
								out.writeObject(p3);
								out.flush();
							}
								break;
								
						case 73: //adminUsers select * from MEMEBER WHERE M_ID = $M_ID
							ProjectProtocol p73 = p;
							System.out.println("ProjectCopyClient running CMD:73 :" + ip);
							String str73 = p73.getMsg2();
							if (p73.getMsg().equalsIgnoreCase("id")) {
								System.out.println("id check");
								AdminUserVO vo73 = AdminUsersDAO.getOneId(str73);
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
								List<AdminUserVO> list73 = AdminUsersDAO.getOneName(str73);
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
								List<AdminUserVO> list73 = AdminUsersDAO.getOneEmail(str73);
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
							List<AdminUserVO> list = AdminUsersDAO.getList();
							p81.setCmd(82);
							p81.setUserList(list);
							out.writeObject(p81);
							out.flush();							
							break;
							
						case 83: // AdminUsers DELETE row at button
							ProjectProtocol p83 = p;
							System.out.println("CASe 83 DELETING");
							AdminUserVO vo = p83.getUservo();
							int result = AdminUsersDAO.getDelete(vo);
							
							if(result>0) {
								System.out.println("DELETE COMPLETEEE");
								list = AdminUsersDAO.getList();
								p83.setCmd(82);
								p83.setUserList(list);
								out.writeObject(p83);
								out.flush();
							}
							break;

					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		server.removeClient(this);
		try {
			out.close();
			in.close();
			s.close();
		} catch (Exception e) {
		}
		
	}
	
}
