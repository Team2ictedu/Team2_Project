package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import UserDB.UserDAO;
import UserDB.UserVO;

public class CP_Client extends Thread {
	Socket s;
	DB_Server server;
	ObjectInputStream in;
	ObjectOutputStream out;

	public CP_Client(Socket s, DB_Server server) {
		this.s = s;
		this.server = server;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
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
						UserDAO.getUserLastLogin(vo);
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
					}
				
						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
