package project_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ProjectServer implements Runnable{
	ServerSocket ss;
	Socket s;
	
	ArrayList<ProjectCopyClient> list;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	
	//현재접속자의 정보
//	ArrayList<CopyClient> c_list;
//	
	public ProjectServer() {
		list = new ArrayList<>();;
		try {
			ss = new ServerSocket(7779);
			System.out.println("프젝 서버 대기중 . . . ");
			new Thread(this).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				
				s = ss.accept();
				ProjectCopyClient cc = new ProjectCopyClient(s, this);
				list.add(cc);
				cc.start();
				System.out.println("CC started. In ProjectServer.java" + cc.ip+ " " + cc.username);
				
		
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeClient(ProjectCopyClient cc) {
		list.remove(cc);
	}
	

	
	public static void main(String[] args) {
		new ProjectServer();
	}
	
}
