package project_java;

import java.awt.CardLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import DB_Place_All.Place_All_DAO;
import DB_Place_All.Place_All_VO;
import DB_Place_Review.Place_Review_VO;
import DB_Place_Select.Place_Select_VO;
import DB_Planner.Planner_VO;
import DB_Travel_Location.Travel_Location_VO;
import DB_User.UserDAO;
import DB_User.UserVO;
import Server.ClientDAO;
import Server.Protocol;
import project_admin.AdminMain;
import project_admin.AdminPlaceVO;
import project_admin.AdminReviewVO;
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
	AllRV_table all_rv;
	Socket s;
	public ObjectOutputStream out;
	ObjectInputStream in;
	Protocol p;
	UserVO uservo;
	Planner_VO planvo;
	Planner_VO planvo2;
	Planner_Select selectvo;
	Travel_Location_VO location_VO;
	Travel_Location_VO location_VO2;
	Place_All_VO placeAllVo;
	List<Place_All_VO> list410;
	List<Place_Review_VO> list424_1;
	List<Planner_VO> list424_2;
	Planner_VO p426;
	Place_Review_VO p425;
	List<Planner_VO> planList;
	List<Place_All_VO> list211;
	List<Place_Select_VO> placeSelectList;
	List<Travel_Location_VO> travelLocationList;
	List<Travel_Location_VO> travelLocationList2;
	String TLNum;

	public List<AdminPlaceVO> list52;

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
		getRootPane().setDefaultButton(login_Main.log_bt);
		add(cardJPanel);

		cardLayout.show(cardJPanel, "login_Main");

		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		connected();
	}

	public void Main2(Protocol p) {
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
		this.p = p;
	}

	public void Main3(Protocol p) {
		adminMain = new AdminMain(this);
		cardJPanel.add("admin_greeting", adminMain.adminHome);
		cardJPanel.add("admin_places", adminMain.adminPlaces);
		cardJPanel.add("admin_users", adminMain.adminUsers);
		cardJPanel.add("admin_reviews", adminMain.adminReview);
		this.p = p;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					LocalDate dateObj = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String date = dateObj.format(formatter);
					System.out.println("Today is " + date + ", \nDAO.clientOpenInstance(today) result is "
							+ ClientDAO.clientOpenInstance(date));
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
								this.p.setVo(p.getVo());
								Main3(p);
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
								// p.setPlannerList(p.getPlannerList());
								uservo = p.getVo();
								planList = p.getPlannerList();
								location_VO = p.getLocation_VO();
								placeAllVo = p.getPlaceAllVO();
								planvo2 = p.getPlanvo();
								Main2(p);
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
						this.p.setVo(p.getVo());
						Main2(p);
						JOptionPane.showMessageDialog(null, "회원정보 수정이 완료되었습니다.", "Confirm",
								JOptionPane.INFORMATION_MESSAGE);
						cardLayout.show(cardJPanel, "planner_Select");
						break;
					case 23:
						location_VO = p.getLocation_VO();
						break;
					case 27:
						placeSelectList = p.getPlaceSelectList();
						break;
					case 30:
						placeAllVo = p.getPlaceAllVO();
						break;
					case 36:
						travelLocationList2 = p.getTravelLocationList();
						planner_Create.search2.removeAllItems();
						planner_Create.search2.addItem("Town");
						for (Travel_Location_VO vo36 : travelLocationList2) {
							planner_Create.search2.addItem(vo36.getTOWN());
							System.out.println(vo36.getTOWN());
						}
						break;
//					case 38:
//						location_VO2 = p.getLocation_VO();
//						System.out.println(p.getLocation_VO().getTL_NUM());
//						planner_Create.planVo.setTL_NUM(p.getLocation_VO().getTL_NUM());
//						break;
					case 41:
						uservo = p.getVo();
						planvo2 = p.getPlanvo();
						planList = p.getPlannerList();
						Main2(p);
						cardLayout.show(cardJPanel, "planner_Select");
						break;
					case 52:
						ProjectProtocol p52 = (ProjectProtocol) obj;
						list52 = p52.getPlaceList();
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
						adminMain.adminUsers.model
								.addRow(new String[] { vo74.getM_id(), vo74.getM_pw(), vo74.getM_name(),
										vo74.getM_birth(), vo74.getM_email(), vo74.getM_phone(), "수정", "삭제" });

						break;
					case 82: // (SELECT * FROM MEMBER)
						ProjectProtocol p82 = (ProjectProtocol) p;
						List<project_admin.AdminUserVO> list82 = p82.getUserList();
						adminMain.adminUsers.model.setRowCount(0);
						for (project_admin.AdminUserVO p821 : list82) {
							String pwd = p821.getM_pw();
							StringBuilder pwd2 = new StringBuilder(pwd);
							if (pwd != null) {
								if (pwd.length() > 2) {
									for (int i = 1; i < pwd.length() - 1; i++) {
										pwd2.setCharAt(i, '*');
									}
									pwd = pwd2.toString();
								}
							}

							adminMain.adminUsers.model.addRow(new String[] { p821.getM_id(), pwd, p821.getM_name(),
									p821.getM_birth(), p821.getM_email(), p821.getM_phone(), "수정", "삭제" });
						}
						break;
					case 85: // print all places
						ProjectProtocol p85 = (ProjectProtocol) p;
						p85.setCmd(51);
						out.writeObject(p85);
						out.flush();
						break;

					case 86: // error
						JOptionPane.showMessageDialog(null, "잘못된 입력값입니다.", "데이터 값 에러", JOptionPane.ERROR_MESSAGE);
						break;
					case 866: // error: can't change id
						JOptionPane.showMessageDialog(null, "아이디는 바꿀 수 없습니다.", "데이터 값 에러", JOptionPane.ERROR_MESSAGE);
						ProjectProtocol p866 = (ProjectProtocol) p;
						p866.setCmd(81);
						out.writeObject(p866);
						out.flush();
						break;
					case 867: // error :can't change price to string
						JOptionPane.showMessageDialog(null, "가격은 숫자만 입력 가능합니다.", "데이터 값 에러", JOptionPane.ERROR_MESSAGE);
						ProjectProtocol p867 = (ProjectProtocol) p;
						p867.setCmd(51);
						out.writeObject(p867);
						out.flush();
						break;

					case 88:
						ProjectProtocol p88 = (ProjectProtocol) p;
						p88.setCmd(81);
						out.writeObject(p88);
						out.flush();
						break;
					case 92: // add rows to adminreview model
						ProjectProtocol p92 = (ProjectProtocol) p;
						System.out.println("printing ReviewList");
						List<AdminReviewVO> list92 = p92.getReviewList();
						adminMain.adminReview.model.setRowCount(0);
						if (list92 == null) {
							JOptionPane.showMessageDialog(null, "등록된 리뷰가 없습니다.", "데이터 값 에러", JOptionPane.ERROR_MESSAGE);
						} else {
							for (project_admin.AdminReviewVO p921 : list92) {
								adminMain.adminReview.model.addRow(
										new String[] { p921.getM_id(), p921.getPa_name(), p921.getPr_con(), "삭제" });
							}
						}
						break;
//					case 93: // add rows without refreshing adrminreview
//						ProjectProtocol p93 = (ProjectProtocol) p;
//						System.out.println("printing ReviewList");
//						List<AdminReviewVO> list93 = p93.getReviewList();
//						for (project_admin.AdminReviewVO p921 : list93) {
//							adminMain.adminReview.model.addRow(new String[] {p921.getM_id(),p921.getPa_name(), p921.getPr_con() 
//									, "삭제"});
//							}
//						break;
//					case 94 :
//						adminMain.adminReview.model.setRowCount(0);
//						break;
					case 95:
						ProjectProtocol p95 = (ProjectProtocol) p;
						p95.setCmd(91);
						out.writeObject(p95);
						out.flush();
						break;

					case 101:
						uservo = p.getVo();
						planvo2 = p.getPlanvo();
						Main2(p);
						cardLayout.show(cardJPanel, "planner_InsertSpot");
						planner_Create.jtf_name.setText("");
						planner_Create.jtf_date.setText("");
						planner_Create.jtf_days.setText("");
						planner_Create.search1.setSelectedIndex(0);
						planner_Create.search2.setSelectedIndex(0);
						break;

					case 203:
						uservo = p.getVo();
						Main2(p);
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
					case 207: // 콤보박스 (PLAN_TITLE)
						myReview.search2.removeAllItems();
						myReview.search2.addItem("관광지선택");
						planList = p.getPlannerList();
						for (Planner_VO k : planList) {
							if (k.getPLAN_TITLE() == null) {

							} else {
								String plantitle = k.getPLAN_TITLE();
								myReview.search1.addItem(plantitle);
							}
						}
						break;

					case 209: // 콤보박스 (PLACE_NAME)
						list211 = p.getPlaceallList();
						for (Place_All_VO k : list211) {
							String placename = k.getPA_NAME();
							myReview.search2.addItem(placename);
						}
						break;

					case 211: // 내후기 작성
						JOptionPane.showMessageDialog(null, "후기 작성이 완료되었습니다.", "Confirm", JOptionPane.PLAIN_MESSAGE);
						myReview.review_jtf.setText("");
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

					case 409:
						System.out.println("팝업 메인");
						all_rv = new AllRV_table(allReview);
						all_rv.setVisible(true);
						List<Place_Review_VO> list409 = p.getPlaceRe();
						all_rv.model.setRowCount(0);
						System.out.println(p.getPlaceRe() + " 후기");
						int noc = 1;
						if (list409.size() == 0) {
							JOptionPane.showMessageDialog(null, "후기가 등록되어있지 않습니다.", "Confirm",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							for (Place_All_VO p444 : list410) {
								for (Place_Review_VO p409 : list409) {
									if (p444.getPA_NUM().equals(p409.getPA_NUM())) {
										System.out.println(p409.getPR_CON());
										all_rv.lb.setText(p444.getPA_NAME() + " 의 후기");
										all_rv.model.addRow(new Object[] { noc, p409.getPR_CON(), p409.getM_ID() });
										noc++;
									}
								}
							}
						}
						break;

					case 411:
						list410 = p.getPlaceAll();
						allReview.model.setRowCount(0);
						System.out.println(5);
						for (Place_All_VO p410 : list410) {
							System.out.println(p410.getPA_NAME());
							allReview.model.addRow(new String[] { p410.getPA_NUM(), p410.getPA_NAME(), "보기" });
						}
						break;

					case 413:
						List<Place_All_VO> list413 = p.getPlaceAll();

						allReview.model.setRowCount(0);
						System.out.println(5);
						for (Place_All_VO p413 : list413) {
							System.out.println(p413.getPA_NAME());
							allReview.model.addRow(new String[] { p413.getPA_NUM(), p413.getPA_NAME(), "보기" });
						}

						if (allReview.model.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "선택한 지역에 관광지가 없습니다.", "Confirm",
									JOptionPane.ERROR_MESSAGE);
							all_rv.setVisible(false);
						}
						break;

					case 421:
						System.out.println("selem");
						allReview.search2.removeAllItems();
						allReview.search2.addItem("::선택::");
						allReview.search2.addItem("전체");
						List<Travel_Location_VO> list415 = p.getLocationList();
						for (Travel_Location_VO k : list415) {
							String town415 = k.getTOWN();
							allReview.search2.addItem(town415);
						}
						break;

					case 423:
						System.out.println("selem2");
						allReview.search2.removeAllItems();
						allReview.search2.addItem("::선택::");
						allReview.search2.addItem("전체");
						List<Travel_Location_VO> list417 = p.getLocationList();
						for (Travel_Location_VO k : list417) {
							String town415 = k.getTOWN();
							allReview.search2.addItem(town415);
						}
						break;

					case 425:
						System.out.println("case425");
						list424_1 = p.getPlaceRe();
						list424_2 = p.getPlannerList();

						myReview.dtm.setRowCount(0);
						noc = 1;
						for (Place_Review_VO p425 : list424_1) {
							for (Planner_VO p426 : list424_2) {
								if (p425.getPLAN_NUM().equals(p426.getPLAN_NUM())) {
									System.out.println(p426.getPLAN_TITLE());
								}

							}
						}
						for (Place_Review_VO p425 : list424_1) {
							for (Planner_VO p426 : list424_2) {
								if (p425.getPLAN_NUM().equals(p426.getPLAN_NUM())) {
									myReview.dtm.addRow(new Object[] { p425.getPLAN_NUM(), noc, p426.getPLAN_TITLE(),
											Place_All_DAO.getPlaceInfo(p425.getPA_NUM()).getPA_NAME(), p425.getPR_CON(),
											"삭제" });
									noc++;
								}
							}
						}
						break;

					case 701:
						ProjectProtocol p701 = (ProjectProtocol) p;
						List<AdminReviewVO> list701 = p701.getReviewList();
						myReview.dtm.setRowCount(0);
						for (AdminReviewVO k : list701) {
							myReview.dtm.addRow(new String[] { k.getPa_name(), k.getPr_con(), "수정", "삭제" });
						}
						break;
					case 1000:
						JOptionPane.showMessageDialog(null, "일치한 정보가 없습니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
						break;
					case 2001:
						List<Place_Select_VO> list = p.getPlaceSelectList();
						for (Place_Select_VO vo : list) {
							if (vo.getPS_DAY().equals(planner_InsertSpot.day)) {
								Place_All_VO vo2 = Place_All_DAO.getPlaceAll(vo.getPA_NUM());
								if (vo2.getPA_PRICE().equals("")) {
									vo2.setPA_PRICE("0원");
								} else {
									vo2.setPA_PRICE(vo2.getPA_PRICE() + "원");
								}
								planner_InsertSpot.model2
										.addRow(new String[] { vo2.getPA_NAME(), vo2.getPA_LOCATION(), vo2.getPA_CON(),
												vo2.getPA_PRICE(), vo.getPS_TIME(), vo.getPS_CON(), vo.getPS_NUM() });
							}
						}
						break;
					case 2005:
						List<Place_All_VO> placeAllList = p.getPlaceAllList();
						if (placeAllList.size() == 0) {
							JOptionPane.showMessageDialog(null, "입력한 위치에 관광지 존재하지 않습니다.", "Confirm",
									JOptionPane.ERROR_MESSAGE);
						} else {
							planner_InsertSpot.model.setRowCount(0);
							for (Place_All_VO vo : placeAllList) {
								if (vo.getPA_PRICE().equals("")) {
									vo.setPA_PRICE("0원");
								} else {
									vo.setPA_PRICE(vo.getPA_PRICE() + "원");
								}
								planner_InsertSpot.model.addRow(new String[] { vo.getPA_NAME(), vo.getPA_LOCATION(),
										vo.getPA_CON(), vo.getPA_PRICE(), vo.getPA_NUM() });
							}
						}
						break;
					case 2012:
						List<Place_All_VO> placeAllList2 = p.getPlaceAllList();
						if (placeAllList2.size() == 0) {
							JOptionPane.showMessageDialog(null, "입력한 위치에 관광지 존재하지 않습니다.", "Confirm",
									JOptionPane.ERROR_MESSAGE);
						} else {
							planner_Select.planner_Edit.model.setRowCount(0);
							for (Place_All_VO vo : placeAllList2) {
								if (vo.getPA_PRICE().equals("")) {
									vo.setPA_PRICE("0원");
								} else {
									vo.setPA_PRICE(vo.getPA_PRICE() + "원");
								}
								planner_Select.planner_Edit.model.addRow(new String[] { vo.getPA_NAME(),
										vo.getPA_LOCATION(), vo.getPA_CON(), vo.getPA_PRICE(), vo.getPA_NUM() });
							}
						}
						break;
					case 2014:
						List<Place_Select_VO> list2014 = p.getPlaceSelectList();
						for (Place_Select_VO vo : list2014) {
							if (vo.getPS_DAY().equals(planner_Select.planner_Edit.day)) {
								Place_All_VO vo2 = Place_All_DAO.getPlaceAll(vo.getPA_NUM());
								if (vo2.getPA_PRICE().equals("")) {
									vo2.setPA_PRICE("0원");
								} else {
									vo2.setPA_PRICE(vo2.getPA_PRICE() + "원");
								}
								planner_Select.planner_Edit.model2
										.addRow(new String[] { vo2.getPA_NAME(), vo2.getPA_LOCATION(), vo2.getPA_CON(),
												vo2.getPA_PRICE(), vo.getPS_TIME(), vo.getPS_CON(), vo.getPS_NUM() });
							}
						}
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