package project_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import project_admin.UserVO;
import project_admin.UsersDAO;

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
							boolean idchk = UsersDAO.getIdChk(p.getMsg());
							if(idchk) {
								System.out.println("m_id 존재하지 않습니다");
								UsersDAO.getInsert(p3_1.getUservo());
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
						case 71: // ProjectCopyClient authencity check : is username == "root"? 
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
								
						case 73: // select * from MEMEBER WHERE M_ID = $M_ID
							ProjectProtocol p73 = p;
							System.out.println("ProjectCopyClient running CMD:73 :" + ip);
							String str73 = p73.getMsg2();
							if (p73.getMsg().equalsIgnoreCase("id")) {
								System.out.println("id check");
								UserVO vo73 = UsersDAO.getOneId(str73);
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
								List<UserVO> list = UsersDAO.getOneName(str73);
								if (list != null) {
									System.out.println("VO SELECTED. VO EMAIL IS : " + p73.getMsg2());
									p73.setUserList(list);
									p73.setCmd(82);
									out.writeObject(p73);
									out.flush();
								}
							}
							if (p73.getMsg().equalsIgnoreCase("email")) {
								System.out.println("email check");
								List<UserVO> list = UsersDAO.getOneEmail(str73);
								if (list != null) {
									System.out.println("VO SELECTED. VO EMAIL IS : " + p73.getMsg2());
									p73.setUserList(list);
									p73.setCmd(82);
									out.writeObject(p73);
									out.flush();
								}
							}
							
							break;
						case 81: // SELECT * FROM MEMBER
							System.out.println("ProjectCopyClient running CMD:81 :" + ip);
							ProjectProtocol p81 = new ProjectProtocol();
							List<UserVO> list = UsersDAO.getList();
							p81.setCmd(82);
							p81.setUserList(list);
							out.writeObject(p81);
							out.flush();							
							break;
							
						case 83: // DELETE row at button
							ProjectProtocol p83 = p;
							System.out.println("CASe 83 DELETING");
							UserVO vo = p83.getUservo();
							int result = UsersDAO.getDelete(vo);
							
							if(result>0) {
								System.out.println("DELETE COMPLETEEE");
								list = UsersDAO.getList();
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
