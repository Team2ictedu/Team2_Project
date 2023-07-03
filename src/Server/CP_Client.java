package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import UserDB.DAO;
import UserDB.VO;

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
					VO vo = p.getVo();
					switch (p.getCmd()) {
					case 0:
						out.writeObject(p);
						out.flush();
						break;
					case 1: // 회원가입
					case 2: // 중복제거
						int result = DAO.getIdChk(vo.getM_ID());
						if (result == 0) {
							p.setCmd(3);
						} else {
							p.setCmd(4);
						}
						out.writeObject(p);
						out.flush();
						break;
					case 5: // 회원가입
						DAO.getInsert(vo);
						out.writeObject(p);
						out.flush();
						break;

					}
				}
			} catch (Exception e) {
				System.out.println("CP_Client: " + e);
			}
		}

	}
}
