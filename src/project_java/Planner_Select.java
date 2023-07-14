package project_java;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import DB_Place_All.Place_All_VO;
import DB_Place_Select.Place_Select_VO;
import DB_Planner.Planner_DAO;
import DB_Planner.Planner_VO;
import DB_Travel_Location.Travel_Location_DAO;
import DB_Travel_Location.Travel_Location_VO;
import DB_User.UserVO;
import Server.Protocol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Planner_Select extends JPanel implements ActionListener {
	Main main;
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1;
	JLabel title, city, select_title;
	JPanel jp_SNB, jp_select, jp_sel, jp_comcan, jp_card_insert, jp_plan_bt, jp_card_select, jp_select_title;
	JButton jb_select, bt_plan_del, bt_plan_edit;
	JButton[] jb_day, jb_title;
	JTextField jtf_select;
	JTextArea add_jta, select_jta;
	JScrollPane add_jsp, select_jsp;
	CardLayout cardlayout;
	String TL;
	JTextArea[] textAreas;
	JScrollPane[] scrollPane;
	JPanel[] jparray;
	int title_su;
	Travel_Location_VO vo = new Travel_Location_VO();
	String planNum;
	Planner_Edit planner_Edit;
	public Planner_Select(Main main) {
		this.main = main;
		UserVO userVo = main.uservo;
		Planner_VO planner_VO = main.planvo2;
	
//		FONT
//		Font font = Font.loadFont("src/homework/fonts/Jalnan.ttf");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		변수 생성
		{
			jp = new JPanel();
			jp_headerMain = new JPanel();
			jp_headerSub = new JPanel();
			jp_headerSubLeft = new JPanel();
			jp_headerSubRight = new JPanel();
			jp_buttons = new JPanel();
			jp_east = new JPanel();
			jp_west = new JPanel();
			jp_south = new JPanel();
			jb1 = new JButton("새 일정 만들기");

			jb1.setPreferredSize(new Dimension(120, 30));
			jb2 = new JButton("내 일정 조회");
			jb2.setPreferredSize(new Dimension(120, 30));
			jb3 = new JButton("여행 후기");
			jb3.setPreferredSize(new Dimension(120, 30));
			jb4 = new JButton("마이페이지");
			jb4.setPreferredSize(new Dimension(120, 30));
//		jb4.setPreferredSize(new Dimension(80, 40));
			jbName = new JButton(main.p.getVo().getM_NAME() + "님");
			jbMyInfo = new JButton("내 정보");
			jbLogOut = new JButton("로그아웃");
		}

//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));
		jbName.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jbMyInfo.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jbLogOut.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb1.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb2.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb3.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb4.setFont(new Font("Jalnan", Font.PLAIN, 12));

//		jbName,jbMyInfo,jbLogout 투명하게 하기
		{
			jbName.setOpaque(false);
			jbName.setContentAreaFilled(false);
			jbName.setBorderPainted(false);
			jbMyInfo.setOpaque(false);
			jbMyInfo.setContentAreaFilled(false);
			jbMyInfo.setBorderPainted(false);
			jbLogOut.setOpaque(false);
			jbLogOut.setContentAreaFilled(false);
			jbLogOut.setBorderPainted(false);
		}

//		색 바꾸기
		// jb1.setBackground(Color.decode("#98b4d4"));
		{
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
			jp_buttons.setBackground(Color.decode("#D4B8E8"));
			jp_west.setBackground(Color.decode("#D4B8E8"));
			jp_east.setBackground(Color.decode("#D4B8E8"));
			jp_south.setBackground(Color.decode("#D4B8E8"));
			jp.setBackground(Color.decode("#eee3f6"));
			jb1.setBackground(Color.decode("#eee3f6"));
			jb2.setBackground(Color.decode("#eee3f6"));
			jb3.setBackground(Color.decode("#eee3f6"));
			jb4.setBackground(Color.decode("#eee3f6"));
		}

//		레이아웃
		{
			jp_buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSub.setLayout(new GridLayout(0, 2));
			jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
//		jp_headerMain.setLayout(new GridLayout(2,0));
			// jp_headerSub.setLayout(new FlowLayout(FlowLayout.LEFT));
		}

//		add image 
		ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back

		// 내일정 조회 페이지
		jp_card_select = new JPanel();
		jp_card_select.setBackground(Color.decode("#D4B8E8"));

		// SNB
		jp_SNB = new JPanel();
		title_su = main.planList.size();
		jb_title = new JButton[title_su];
		jp.setBackground(Color.decode("#D4B8E8"));
		for (int i = 0; i < title_su; i++) {
			jb_title[i] = new JButton(main.planList.get(i).getPLAN_TITLE());
			jp_SNB.add(jb_title[i]);
			jb_title[i].setPreferredSize(new Dimension(200, 50));
			jp.add(jp_SNB, BorderLayout.WEST);
			// 폰트흰색
			jb_title[i].setForeground(Color.white);
			jb_title[i].setFont(new Font("Aharoni", Font.BOLD, 15));
			jb_title[i].setBorderPainted(false);
			jb_title[i].addActionListener(this);

			// 첫번째 제목은 보라색
			if (i == 0) {
				jb_title[i].setBackground(Color.decode("#B19CCB"));
			} else
				jb_title[i].setBackground(Color.decode("#F083BA"));
			int index = i;

			jb_title[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JPanel jp_select_text = new JPanel(new GridLayout(0, 1));
					jp_select_text.setBackground(Color.WHITE);
					// 전체 버튼의 변경색
					for (int j = 0; j < jb_title.length; j++) {
						// 나머지 버튼의 배경색
						jb_title[j].setBackground(Color.decode("#F083BA"));
					}
					// 선택한 버튼의 배경색
					try {
						Protocol p2 = new Protocol();
						Travel_Location_VO vo2 = new Travel_Location_VO();
						vo2.setTL_NUM(main.planList.get(index).getTL_NUM());
						p2.setLocation_VO(vo2);
						p2.setCmd(22);
						main.out.writeObject(p2);
						main.out.flush();
					} catch (Exception e2) {
						System.out.println(e2);
					}

					try {
						Protocol p3 = new Protocol();
						Place_Select_VO vo3 = new Place_Select_VO();
						vo3.setPA_NUM(main.planList.get(index).getPLAN_NUM());
						p3.setPlaceSelectVo(vo3);
						p3.setCmd(26);
						main.out.writeObject(p3);
						main.out.flush();
					} catch (Exception e2) {
						System.out.println(e2);
					}

					jb_title[index].setBackground(Color.decode("#B19CCB"));
					select_title.setText(" 여행지: " + main.location_VO.getCITY() + " " + main.location_VO.getTOWN()
							+ "  |  날짜: " + main.planList.get(index).getPLAN_DATE() + "~"
							+  main.planList.get(index).getPLAN_LASTDATE() + "("
							+  main.planList.get(index).getPLAN_DAYS() + "일)");
					planNum =  main.planList.get(index).getPLAN_NUM();
					textAreas = new JTextArea[ main.planList.get(index).getPLAN_DAYS()];
					scrollPane = new JScrollPane[ main.planList.get(index).getPLAN_DAYS()];
					JPanel[] jp_select_text_panels = new JPanel[ main.planList.get(index).getPLAN_DAYS()];

					for (int k = 0; k < main.planList.get(index).getPLAN_DAYS(); k++) {
						jp_select_text_panels[k] = new JPanel(new BorderLayout());
						jp_select_text_panels[k].setBackground(Color.WHITE);

						textAreas[k] = new JTextArea();
						textAreas[k].append("Day " + (k + 1) + "\n");

						scrollPane[k] = new JScrollPane(textAreas[k], ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
								ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						textAreas[k].setLineWrap(true);
						textAreas[k].setEditable(false);
						textAreas[k].setBackground(Color.WHITE);

						textAreas[k].setText(""); // 이전 텍스트 초기화
						// Assign different values to each JTextArea
						jp_select_text_panels[k].add(scrollPane[k], BorderLayout.CENTER);
						jp_select_text.add(jp_select_text_panels[k]);
						jp_select.add(jp_select_text, BorderLayout.CENTER);
						JScrollPane jp_select_scrollPane = new JScrollPane(jp_select_text,
								JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						jp_select.add(jp_select_scrollPane, BorderLayout.CENTER);
					}
					if (main.placeSelectList != null) {
						boolean[] visitedDays = new boolean[textAreas.length]; // 각 day의 등장 여부를 저장하는 배열

						for (Place_Select_VO psvo : main.placeSelectList) {
							int day = Integer.parseInt(psvo.getPS_DAY()) - 1; // 일 수에 맞는 배열 인덱스 계산
							try {
								Protocol p4 = new Protocol();
								Place_All_VO vo4 = new Place_All_VO();
								vo4.setPA_NUM(psvo.getPA_NUM());
								p4.setPlaceAllVO(vo4);
								p4.setCmd(29);
								main.out.writeObject(p4);
								main.out.flush();
							} catch (Exception e2) {
								System.out.println(e2);
							}
							// 글자 간격 설정

							if (day >= 0 && day < textAreas.length) {
								if (!visitedDays[day]) {
									visitedDays[day] = true;
									textAreas[day].append("----------------------------------Day - " + psvo.getPS_DAY()
											+ "----------------------------------\n");
								}
								textAreas[day].append("관광지명: " + main.placeAllVo.getPA_NAME() + "\n");
								textAreas[day].append("위치: " + main.placeAllVo.getPA_LOCATION() + "\n");
								textAreas[day].append("설명: " + main.placeAllVo.getPA_CON() + "\n");
								textAreas[day].append("가격: " + main.placeAllVo.getPA_PRICE() + "\n");
								textAreas[day].append("--------------------------------------\n");
							}
						}
					}
				}
			});
		}

		jp_SNB.setPreferredSize(new Dimension(150, 600));
		jp_SNB.setBackground(Color.decode("#F083BA"));
		jp_card_select.add(jp_SNB, BorderLayout.WEST);

		// 일정조회 화면
		jp_select = new JPanel();
		jp_select.setLayout(new BorderLayout());
		jp_select.setPreferredSize(new Dimension(800, 600));
		jp_select.setBackground(Color.WHITE);
		jp_card_select.add(jp_select, BorderLayout.CENTER);

		// 선택한 일정에 제목, 날짜 정보제공
		jp_select_title = new JPanel();
		select_title = new JLabel(" 여행지: " + main.location_VO.getCITY() + " " + main.location_VO.getTOWN() + "  |  날짜: "
				+  main.planList.get(0).getPLAN_DATE() + "~" +  main.planList.get(0).getPLAN_LASTDATE() + "("
				+  main.planList.get(0).getPLAN_DAYS() + "일)");
		select_title.setFont(new Font("Aharoni", Font.BOLD, 18));
		select_title.setPreferredSize(new Dimension(800, 30));
		select_title.setForeground(Color.WHITE);

		bt_plan_edit = new JButton();
		bt_plan_edit = new JButton("Edit");
		bt_plan_edit.setFont(new Font("Aharoni", Font.BOLD, 13));
		bt_plan_edit.setForeground(Color.white);
		bt_plan_edit.setPreferredSize(new Dimension(70, 25));
		bt_plan_edit.setBackground(Color.decode("#F083BA"));
		bt_plan_edit.setBorderPainted(false);

		bt_plan_del = new JButton();
		bt_plan_del = new JButton("Delete");
		bt_plan_del.setFont(new Font("Aharoni", Font.BOLD, 13));
		bt_plan_del.setForeground(Color.white);
		bt_plan_del.setPreferredSize(new Dimension(70, 25));
		bt_plan_del.setBackground(Color.decode("#F083BA"));
		bt_plan_del.setBorderPainted(false);

		jp_plan_bt = new JPanel();
		jp_plan_bt.setBackground(Color.decode("#B19CCB"));
		jp_plan_bt.add(bt_plan_edit);
		jp_plan_bt.add(bt_plan_del);

		jp_select_title.setLayout(new BorderLayout());
		jp_select_title.add(select_title, BorderLayout.WEST);
		jp_select_title.add(jp_plan_bt, BorderLayout.EAST);
		jp_select_title.setBackground(Color.decode("#B19CCB"));

		jp_select.add(jp_select_title, BorderLayout.NORTH);

		// card에 담는구간
		cardlayout = new CardLayout();
		jp.setLayout(cardlayout);
		// jp.add("insert",jp_card_insert);;
		jp.add("select", jp_card_select);

//		ADD 
		{
			jp_headerSubLeft.add(new JLabel(imageIcon));
			jp_headerSubLeft.add(jLabel1);
			jp_headerSubRight.add(jbName);
			jp_headerSubRight.add(new JLabel(" | "));
			jp_headerSubRight.add(jbMyInfo);
			jp_headerSubRight.add(new JLabel(" | "));
			jp_headerSubRight.add(jbLogOut);
			jp_buttons.add(jb1);
			jp_buttons.add(jb2);
			jp_buttons.add(jb3);
			jp_buttons.add(jb4);
			jp_headerSub.add(jp_headerSubLeft);
			jp_headerSub.add(jp_headerSubRight);
			jp_headerMain.add(jp_headerSub);
			jp_headerMain.add(jp_buttons);

			setLayout(new BorderLayout());
			add(jp_headerMain, BorderLayout.NORTH);
			add(jp, BorderLayout.CENTER);
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			jb3.addActionListener(this);
			jb4.addActionListener(this);
			jbMyInfo.addActionListener(this);
			jbLogOut.addActionListener(this);
			bt_plan_edit.addActionListener(this);
			bt_plan_del.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton) e.getSource();
		if (obj == jb1) { // 새일정 만들기 jb1~jb4는 SNB바
			main.cardLayout.show(main.cardJPanel, "planner_Create");
		} else if (obj == jb2) { // 내일정 조회
			try {
				Protocol p = new Protocol();
				System.out.println(main.uservo.getM_ID());
				p.setMsg(main.uservo.getM_ID());
				p.setCmd(40);
				System.out.println(3);
				main.out.writeObject(p);
				main.out.flush();
				System.out.println(4);
			} catch (IOException e1) {
				System.out.println(e1);
			}
		} else if (obj == jb3) { // 여행 후기
			main.cardLayout.show(main.cardJPanel, "allReview");
		} else if (obj == jb4) { // 마이페이지
			main.cardLayout.show(main.cardJPanel, "login_My_Infomodify");
		} else if (obj == jbMyInfo) { // 내정보
			main.cardLayout.show(main.cardJPanel, "login_My_Infomodify");
		} else if (obj == jbLogOut) { // 로그아웃
			main.cardLayout.show(main.cardJPanel, "login_Main");
		} else if (obj == bt_plan_edit) { // 플래너 상세정보 수정
			planner_Edit = new Planner_Edit(this);
			main.cardJPanel.add("planner_Edit", planner_Edit);
			main.cardLayout.show(main.cardJPanel, "planner_Edit");
		} else if (obj == bt_plan_del) { // 플래너 삭제 후 새로고침
			int result = JOptionPane.showConfirmDialog(null, "정말 플래너를 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				Planner_DAO.getDeletePlanner(planNum);
				JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.", "Confirm", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "삭제가 취소되었습니다.", "Confirm", JOptionPane.INFORMATION_MESSAGE);
			}
			main.cardLayout.show(main.cardJPanel, "planner_Select");
		}
	}

	public Planner_Select() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		try {
			// Select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// Start the application
////	                    BaseSampleFrame app = new BaseSampleFrame("BaseSampleFrame");
//	                    app.setSize(800, 600);
//	                    app.setLocationRelativeTo(null);
//	                    app.setVisible(true);
					new Planner_Select();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}