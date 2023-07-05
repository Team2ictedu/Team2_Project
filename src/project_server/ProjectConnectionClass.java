package project_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;



public class ProjectConnectionClass extends JFrame implements Runnable {
	public Socket s;
	public ObjectOutputStream out;
	public ObjectInputStream in;

	public ProjectConnectionClass(String string) {
		super(string);
	}
	public void connected() {
		try {
			s = new Socket("192.168.0.86", 7779);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			new Thread(this).start();
			System.out.println("ProjectConnectionClass Successfully extended by Main.java");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void closed() {
		try {
			ProjectProtocol p = new ProjectProtocol();
			p.setCmd(0);
			out.writeObject(p);
			out.flush();
			
			out.close();
			in.close();
			s.close();
			System.out.println("ProjectConnectionClass.Closed()");
			System.exit(0);
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		esc:while(true) {
			try {
				Object obj = in.readObject();
				if(obj != null) {
					ProjectProtocol p = (ProjectProtocol)obj;
					System.out.println("ProjectConnectionClass run() received Protocol");
					switch (p.getCmd()) {
						case 0 : // 종료 
							System.out.println("ProjectConnectionClass run() case 0 break esc;");
							break esc;
						case 11 : 
							
							System.out.println("HELLOWORLD1111");
							break;
						case 72:
							System.out.println("ProjectConnectionClass case72");
							
							break;
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 무한 루프 끝
		closed();
		System.exit(0);
	}

}
