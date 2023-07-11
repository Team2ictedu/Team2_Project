package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import project_server.ProjectCopyClient;

public class DB_Server implements Runnable{
	ServerSocket ss = null;
	Socket s = null;
	
	
	ArrayList<CP_Client> list;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public DB_Server() {
		list = new ArrayList<>();;
		try {
			ss = new ServerSocket(7780);
			System.out.println("서버 대기 중 ...");
			new Thread(this).start();
					
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				s = ss.accept();
				CP_Client cc = new CP_Client(s, this);
				list.add(cc);
				cc.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void removeClient(ProjectCopyClient cc) {
		list.remove(cc);
	}
	
	public static void main(String[] args) {
		new DB_Server();
	}
}