package project_java;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Server.Protocol;
import project_admin.AdminMain;
import UserDB.DAO;
import UserDB.VO;

public class Main extends JFrame implements Runnable {
	public CardLayout cardLayout;
	public JPanel cardJPanel;
	Login_My_Infomodify login_My_Infomodify;
	Login_Register login_Register;
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;

	public Main() {
		super("PERSONAL PLANNER");
		cardJPanel = new JPanel();
		cardLayout = new CardLayout();
		cardJPanel.setLayout(cardLayout);

		// 로그인 후
		// 객체 선언
		// 로그인 전
		Login_Main login_Main = new Login_Main(this);
		login_Register = new Login_Register(this);
		Id_Search id_Search = new Id_Search(this);
		Pw_Search pw_Search = new Pw_Search(this);
		// PwChange_login pwChange_login = new PwChange_login(this);
		// 로그인 전
		cardJPanel.add("login_Main", login_Main);
		cardJPanel.add("login_Register", login_Register);
		cardJPanel.add("id_Search", id_Search);
		cardJPanel.add("pw_Search", pw_Search);
		// cardJPanel.add("pwChange_login", pwChange_login);

		add(cardJPanel);

		cardLayout.show(cardJPanel, "login_Main");

		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		connected();
	}

	public void Main2() {
		login_My_Infomodify = new Login_My_Infomodify(this);
		Login_My_PWmodify login_My_PWmodify = new Login_My_PWmodify(this);
		Login_Withdrawal login_Withdrawal = new Login_Withdrawal(this);

		// 리뷰 객체 선언
		AllReview allReview = new AllReview(this);
		MyReview myReview = new MyReview(this);

		// 플래너 객체 선언
		Planner_Create planner_Create = new Planner_Create(this);
		Planner_InsertSpot planner_InsertSpot = new Planner_InsertSpot(this);
		Planner_Select planner_Select = new Planner_Select(this);

		// 관리자 객체 선언
		AdminMain adminMain = new AdminMain(this);

		// 카드 패널 추가

		// 로그인 후
		cardJPanel.add("login_My_Infomodify", login_My_Infomodify);
		cardJPanel.add("login_My_PWmodify", login_My_PWmodify);
		cardJPanel.add("login_Withdrawal", login_Withdrawal);

		// 리뷰
		cardJPanel.add("allReview", allReview);
		cardJPanel.add("myReview", myReview);

		// 플래너
		cardJPanel.add("planner_Create", planner_Create);
		cardJPanel.add("planner_InsertSpot", planner_InsertSpot);
		cardJPanel.add("planner_Select", planner_Select);

		// 관리자
		cardJPanel.add("admin_greeting", adminMain.adminHome);
		cardJPanel.add("admin_places", adminMain.adminPlaces);
		cardJPanel.add("admin_users", adminMain.adminUsers);
		cardJPanel.add("admin_reviews", adminMain.adminReview);
	
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new Main();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	@Override
	public void run() {
		esc: while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					Protocol p = (Protocol) obj;
					switch (p.getCmd()) {
					case 0:
						break esc;
					case 2:
						JOptionPane.showMessageDialog(null, "같은 아이디가 존재합니다.", " Confirm", JOptionPane.INFORMATION_MESSAGE);
						out.writeObject(p);
						out.flush();
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", " Confirm", JOptionPane.INFORMATION_MESSAGE);
						cardLayout.show(cardJPanel, "login_Main");
						login_Register.tf_id.setText("");
						login_Register.jpf_pw.setText("");
						login_Register.jpf_pwchk.setText("");
						login_Register.tf_mail.setText("");
						login_Register.tf_name.setText("");
						login_Register.tf_birth.setText("");
						login_Register.tf_phone.setText("");
						login_Register.cb_TermsofUse.setSelected(false);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		closed();
	}
	// 접속
	public void connected() {
		try {
			s = new Socket("192.168.0.44", 7780);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	// 끝내기
	public void closed() {
		try {
			out.close();
			in.close();
			s.close();
			System.exit(0);
		} catch (Exception e) {
		}
	}
}
