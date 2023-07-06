package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DB_Server implements Runnable{
	ServerSocket ss = null;
	Socket s = null;
	public DB_Server() {
		try {
			ss = new ServerSocket(7780);
<<<<<<< HEAD
			System.out.println("서버 대기 중 ...");
=======
			System.out.println("아이피 변경하세요.");
>>>>>>> a56ae3cd91e59f6178c2495b585c20bb062595cc
					
			new Thread(this).start();
		} catch (IOException e) {
		
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				s = ss.accept();
				CP_Client cc = new CP_Client(s, this);
				cc.start();
			} catch (Exception e) {
				System.out.println("db server: " +e);
			}
		}
		
	}
	public static void main(String[] args) {
		new DB_Server();
	}
}