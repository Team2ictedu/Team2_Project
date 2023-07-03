package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

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
					switch (p.getCmd()) {
					case 0:
						out.writeObject(p);
						out.flush();
						break;
					case 1: // 아이디체크
						VO vo2 = p.getVo();
						DAO.getIdCheck(vo2.getM_ID());
						out.writeObject(p);
						out.flush();
						break;
					case 2: //회원가입
						VO vo = p.getVo();
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
