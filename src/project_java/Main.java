package project_java;

import java.awt.CardLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import DB_User.UserVO;
import Server.Protocol;
import project_admin.AdminMain;
import project_admin.AdminPlaceVO;
import project_server.ProjectProtocol;

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
	PwChange_login pw_ck;
	Socket s;
	public ObjectOutputStream out;
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
								System.out.println("Main run() received Protocol CMD : 72");
								System.out.println("Main CMD:72 = LogIn as Admin" + p.getVo().getM_NAME());
								adminMain.adminHome.adminLabel.setText(p.getVo().getM_NAME() + "");
								adminMain.adminHome.welcomeLabel.setText(String.format("어서오세요, %s님!      %s",
										p.getVo().getM_NAME(), adminMain.adminHome.todayDate));
								adminMain.adminUsers.adminLabel.setText(adminMain.adminHome.adminLabel.getText());
								adminMain.adminPlaces.adminLabel.setText(adminMain.adminHome.adminLabel.getText());
								adminMain.adminReview.adminLabel.setText(adminMain.adminHome.adminLabel.getText());
								cardLayout.show(cardJPanel, "admin_greeting");
							} else if (p.getVo().getM_CLASS().equals("1")) {
								Main2();
								JOptionPane.showMessageDialog(null, "로그인 되었습니다.(유저)", "Confirm",
										JOptionPane.INFORMATION_MESSAGE);
								cardLayout.show(cardJPanel, "planner_Select");
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
						JOptionPane.showMessageDialog(null, "회원정보 수정이 완료되었습니다.", "Confirm",
								JOptionPane.INFORMATION_MESSAGE);
						cardLayout.show(cardJPanel, "planner_Select");
						break;

					case 52:
						ProjectProtocol p52 = (ProjectProtocol) obj;
						List<AdminPlaceVO> list52 = p52.getPlaceList();
						adminMain.adminPlaces.model.setRowCount(0);
						for (AdminPlaceVO p521 : list52) {
							adminMain.adminPlaces.model.addRow(new String[] { p521.getPa_name(), p521.getPa_location(),
									p521.getPa_con(), p521.getPa_price(), "수정", "삭제" });
						}
						break;
//					case 72: // login as Admin
//						System.out.println("Main run() received Protocol CMD : 72");
//						System.out.println("Main CMD:72 = LogIn as Admin"+p.getName());
//						adminMain.adminHome.adminLabel.setText(p.getName());
//						adminMain.adminHome.welcomeLabel.setText(String.format("어서오세요, %s님!      %s", p.getName(), adminMain.adminHome.todayDate));
//						adminMain.adminUsers.adminLabel.setText(adminMain.adminHome.adminLabel.getText());
//						adminMain.adminPlaces.adminLabel.setText(adminMain.adminHome.adminLabel.getText());
//						adminMain.adminReview.adminLabel.setText(adminMain.adminHome.adminLabel.getText());
//						break;
					case 74:
						ProjectProtocol p74 = (ProjectProtocol) obj;
						adminMain.adminUsers.model.setRowCount(0);
						project_admin.AdminUserVO vo74 = p74.getUservo();
						adminMain.adminUsers.model.addRow(new String[] { vo74.getM_id(), vo74.getM_pw(),
								vo74.getM_name(), vo74.getM_birth(), vo74.getM_email(), "수정", "삭제" });

						break;
					case 82: // (SELECT * FROM MEMBER)
						ProjectProtocol p82 = (ProjectProtocol) p;
						List<project_admin.AdminUserVO> list82 = p82.getUserList();
						adminMain.adminUsers.model.setRowCount(0);
						for (project_admin.AdminUserVO p821 : list82) {
							adminMain.adminUsers.model.addRow(new String[] { p821.getM_id(), p821.getM_pw(),
									p821.getM_name(), p821.getM_birth(), p821.getM_email(), "수정", "삭제" });
						}
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

					case 205: // 아이디 찾기
						if (p.getVo() == null) {
							JOptionPane.showMessageDialog(null, "입력된 정보가 없습니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
						} else {
							int result = JOptionPane.showConfirmDialog(null, id_Search.jtf_name.getText() + "님의 아이디는 "
									+ p.getVo().getM_ID() + "입니다.\n비밀번호도 찾으시겠습니까?", "Confirm",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								cardLayout.show(cardJPanel, "pw_Search");
							} else {
								cardLayout.show(cardJPanel, "login_Main");
								id_Search.jtf_name.setText("");
								id_Search.jtf_em.setText("");
							}
						}
						break;
					case 403:
						if (p.getVo() == null) {
							JOptionPane.showMessageDialog(null, "일치한 정보가 존재하지 않습니다.", " Confirm",
									JOptionPane.ERROR_MESSAGE);
						} else {
							pw_ck = new PwChange_login(pw_Search);
							pw_ck.setVisible(true);
							pw_ck.vo.setM_ID(p.getVo().getM_ID());
							pw_Search.idCg_jtf.setText("");
							pw_Search.name_jtf.setText("");
							pw_Search.em_jtf.setText("");
						}
						break;
					case 405:
						JOptionPane.showMessageDialog(null, "비밀번호 설정이 완료되었습니다.", "Confirm",
								JOptionPane.INFORMATION_MESSAGE);
						pw_ck = new PwChange_login(pw_Search);
						pw_ck.setVisible(false);
						pw_Search.main.cardLayout.show(pw_Search.main.cardJPanel, "login_Main");
						pw_ck.pwck1_jtf.setText("");
						pw_ck.pwck2_jtf.setText("");
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