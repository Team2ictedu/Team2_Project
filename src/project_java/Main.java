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
import UserDB.UserDAO;
import UserDB.UserVO;

public class Main extends JFrame implements Runnable {
	public CardLayout cardLayout;
	public JPanel cardJPanel;
	Login_My_Infomodify login_My_Infomodify;
	Login_Register login_Register;
	Login_Main login_Main;
	Id_Search id_Search;
	Pw_Search pw_Search;
	Login_My_PWmodify login_My_PWmodify;
	Login_Withdrawal login_Withdrawal;
	AllReview allReview;
	MyReview myReview;
	Planner_Create planner_Create;
	Planner_InsertSpot planner_InsertSpot;
	Planner_Select planner_Select;
	AdminMain adminMain;
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;
	Protocol p;
	
	public Main() {
		super("PERSONAL PLANNER");
		cardJPanel = new JPanel();
		cardLayout = new CardLayout();
		cardJPanel.setLayout(cardLayout);

		// 로그인 후
		// 객체 선언
		// 로그인 전
		login_Main = new Login_Main(this);
		login_Register = new Login_Register(this);
		id_Search = new Id_Search(this);
		pw_Search = new Pw_Search(this);
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
		login_My_PWmodify = new Login_My_PWmodify(this);
		login_Withdrawal = new Login_Withdrawal(this);

		// 리뷰 객체 선언
		allReview = new AllReview(this);
		myReview = new MyReview(this);

		// 플래너 객체 선언
		planner_Create = new Planner_Create(this);
		planner_InsertSpot = new Planner_InsertSpot(this);
		planner_Select = new Planner_Select(this);

		// 관리자 객체 선언
		adminMain = new AdminMain(this);

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
					p = (Protocol) obj;
					switch (p.getCmd()) {
					case 0:
						break esc;
					case 2:
						JOptionPane.showMessageDialog(null, "같은 아이디가 존재합니다.", " Confirm",
								JOptionPane.INFORMATION_MESSAGE);
						out.writeObject(p);
						out.flush();
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", " Confirm",
								JOptionPane.INFORMATION_MESSAGE);
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
					case 5:
						if (p.getVo() == null) {
							JOptionPane.showMessageDialog(null, "일치한 정보가 없습니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
						} else {
							if (p.getVo().getM_CLASS().equals("0")) {
								Main2();
								JOptionPane.showMessageDialog(null, "로그인 되었습니다.(관리자)", "Confirm",
										JOptionPane.INFORMATION_MESSAGE);
								cardLayout.show(cardJPanel, "admin_greeting");
								p.setCmd(9);
								out.writeObject(p);
								out.flush();
							} else if (p.getVo().getM_CLASS().equals("1")) {
								Main2();
								JOptionPane.showMessageDialog(null, "로그인 되었습니다.(유저)", "Confirm",
										JOptionPane.INFORMATION_MESSAGE);
								cardLayout.show(cardJPanel, "planner_Select");
								p.setCmd(9);
								out.writeObject(p);
								out.flush();
							} else if (p.getVo().getM_CLASS().equals("4")) {
								JOptionPane.showMessageDialog(null, p.getVo().getM_ID() + "님은 탈퇴한 계정입니다.", "Confirm",
										JOptionPane.ERROR_MESSAGE);
							}
							login_Main.jtf_id.setText("");
							login_Main.jtf_pw.setText("");
						}
						break;
					case 7:
						Main2();
						JOptionPane.showMessageDialog(null, "회원정보 수정이 완료되었습니다.", "Confirm", JOptionPane.INFORMATION_MESSAGE);
						cardLayout.show(cardJPanel, "planner_Select");
						break;
					case 203:
						Main2();
						JOptionPane.showMessageDialog(null, "비밀번호 수정이 완료되었습니다.", "Confirm",
								JOptionPane.INFORMATION_MESSAGE);
						cardLayout.show(cardJPanel, "planner_Select");
						login_My_PWmodify.jpf_pw.setText("");
						login_My_PWmodify.jpf_newPw1.setText("");
						login_My_PWmodify.jpf_newPw2.setText("");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e);
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
