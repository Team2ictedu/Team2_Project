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

import DB_Planner.Planner_VO;
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
	JPanel jp_SNB, jp_planner, jp_plan_left, jp_plan_right, jp_select, jp_sel, jp_comcan, jp_card_insert, jp_plan_bt,
			jp_card_select, jp_select_title, jp_select_text;
	JButton jb_delete_spot, jb_select, jb_add_spot, bt_Cancel, bt_Complete, bt_plan_del, bt_plan_edit;
	JButton[] jb_day, jb_title;
	JTextField jtf_select;
	JTextArea add_jta, select_jta;
	JScrollPane add_jsp, select_jsp;
	CardLayout cardlayout;
	Planner_VO vo = new Planner_VO();
	Protocol p = new Protocol();
	int title_su;

	public Planner_Select(Main main) {
		this.main = main;
		p.setPlannerList(main.p.getPlannerList());
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

		// 새 일정 만들기 페이지
		jp_card_insert = new JPanel();
		jp_card_insert.setBackground(Color.decode("#D4B8E8"));
		jp_SNB = new JPanel();
		jb_day = new JButton[3];
		jp.setBackground(Color.decode("#D4B8E8"));
		for (int i = 0; i < 3; i++) {
			jb_day[i] = new JButton("Day " + (i + 1) + " (23.06." + (25 + i) + ")");
			jp_SNB.add(jb_day[i]);
			jb_day[i].setPreferredSize(new Dimension(150, 50));
			jp.add(jp_SNB, BorderLayout.WEST);
			// 폰트흰색
			jb_day[i].setForeground(Color.white);
			jb_day[i].setFont(new Font("Aharoni", Font.BOLD, 15));
			jb_day[i].setBorderPainted(false);

			// 기본 DAY1은 보라색, 나머지는 분홍
			if (i == 0) {
				jb_day[i].setBackground(Color.decode("#B19CCB"));
			} else
				jb_day[i].setBackground(Color.decode("#F083BA"));

			int index = i;
			jb_day[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 전체 버튼의 변경색
					for (int j = 0; j < jb_day.length; j++) {
						// 나머지 버튼의 배경색
						jb_day[j].setBackground(Color.decode("#F083BA"));
					}
					// 선택한 버튼의 배경색
					jb_day[index].setBackground(Color.decode("#B19CCB"));
				}
			});
		}

		jp_SNB.setPreferredSize(new Dimension(150, 600));
		jp_SNB.setBackground(Color.decode("#F083BA"));
		jp_card_insert.add(jp_SNB, BorderLayout.WEST);

		// 플래너 내용들
		jp_planner = new JPanel();
		jp_planner.setPreferredSize(new Dimension(800, 600));
		jp_planner.setBackground(Color.WHITE);

		// 플래너 왼쪽
		jp_plan_left = new JPanel();
		jp_plan_left.setBackground(Color.decode("#B19CCB"));
		jp_plan_left.setBorder(new LineBorder(Color.decode("#E6E6E6")));

		// 제목 부분
		title = new JLabel("   서울여행" + "의 일정" + " - Day 1");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Aharoni", Font.BOLD, 18));
		title.setPreferredSize(new Dimension(10, 30));

		// 일정 추가 구간
		add_jta = new JTextArea();
		add_jsp = new JScrollPane(add_jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add_jta.setLineWrap(true);
		add_jta.setEditable(false);
		add_jta.setBackground(Color.WHITE);

		// 일정추가정보
		add_jta.append("1. 관광지명 \n");
		add_jta.append("위치: 관광지위치 \n");
		add_jta.append("설명: 관광지설명 \n");
		add_jta.append("평점: 관광지평점 \n");
		add_jta.append("기본 금액: ₩ 관광지금액 \n");
		add_jta.append("추가 금액: ₩ 추가할 금액 입력\n");
		add_jta.append("총 금액: ₩ 기본금액 + 추가금액\n");
		add_jta.append("관광시간: 관광시간 입력\n\n");

		// 일정 추가한것중에 삭제하는 버튼
		jb_delete_spot = new JButton("Delete Spot");
		jb_delete_spot.setForeground(Color.WHITE);
		jb_delete_spot.setFont(new Font("Aharoni", Font.BOLD, 18));
		jb_delete_spot.setBackground(Color.decode("#F083BA"));
		jb_delete_spot.setBorderPainted(false);
		jb_delete_spot.setPreferredSize(new Dimension(30, 45));

		// 플래너 왼쪽부분 담는 구간
		jp_plan_left.setLayout(new BorderLayout());
		jp_plan_left.setPreferredSize(new Dimension(400, 400));
		jp_plan_left.add(title, BorderLayout.NORTH);
		jp_plan_left.add(add_jsp, BorderLayout.CENTER);
		jp_plan_left.add(jb_delete_spot, BorderLayout.SOUTH);

		// 플래너 오른쪽 부분
		jp_plan_right = new JPanel();
		jp_plan_right.setBorder(new LineBorder(Color.decode("#E6E6E6")));
		jp_plan_right.setBackground(Color.decode("#B19CCB"));

		// 제목 부분
		city = new JLabel("   여행지: 대한민국 서울");
		city.setFont(new Font("Aharoni", Font.BOLD, 18));
		city.setForeground(Color.WHITE);
		city.setPreferredSize(new Dimension(10, 30));

		// 검색기능 부분
		jp_select = new JPanel();
		// 검색기능 부분 검색하기
		jp_sel = new JPanel();
		jp_sel.setBackground(Color.WHITE);
		jtf_select = new JTextField(20);
		ImageIcon bt_img = new ImageIcon("src/images/button.png");
		jb_select = new JButton(bt_img);
		jb_select.setPreferredSize(new Dimension(30, 30));
		jb_select.setBackground(Color.WHITE);
		jp_sel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_sel.add(jtf_select);
		jp_sel.add(jb_select);
		jb_select.setBorderPainted(false);

		// 검색한 정보
		select_jta = new JTextArea();
		select_jsp = new JScrollPane();
		select_jsp = new JScrollPane(select_jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		select_jta.setLineWrap(true);
		select_jta.setEditable(false);
		select_jta.setBackground(Color.WHITE);

		// 검색정보
		select_jta.append("1. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		// 검색한 정보 일정에 담는 버튼
		jb_add_spot = new JButton("Add Spot");
		jb_add_spot.setForeground(Color.white);
		jb_add_spot.setFont(new Font("Aharoni", Font.BOLD, 18));
		jb_add_spot.setBackground(Color.decode("#F083BA"));
		jb_add_spot.setBorderPainted(false);
		jb_add_spot.setPreferredSize(new Dimension(30, 45));

		// 검색부분 담는 구간
		jp_select.setLayout(new BorderLayout());
		jp_select.add(jp_sel, BorderLayout.NORTH);
		jp_select.add(select_jsp, BorderLayout.CENTER);
		jp_select.add(jb_add_spot, BorderLayout.SOUTH);

		// 작성완료 구간
		jp_comcan = new JPanel();
		jp_comcan.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Complete 버튼
		bt_Complete = new JButton("Complete");
		bt_Complete.setFont(new Font("Aharoni", Font.BOLD, 13));
		bt_Complete.setForeground(Color.white);
		bt_Complete.setPreferredSize(new Dimension(120, 45));
		bt_Complete.setBackground(Color.decode("#F083BA"));
		bt_Complete.setBorderPainted(false);

		// Cancel 버튼
		bt_Cancel = new JButton("Cancel");
		bt_Cancel.setFont(new Font("Aharoni", Font.BOLD, 13));
		bt_Cancel.setForeground(Color.white);
		bt_Cancel.setPreferredSize(new Dimension(120, 45));
		bt_Cancel.setBackground(Color.decode("#F7C0DC"));
		bt_Cancel.setBorderPainted(false);

		// 작성완료 담는 구간
		jp_comcan.setBackground(Color.WHITE);
		jp_comcan.add(bt_Complete);
		jp_comcan.add(bt_Cancel);

		// 플래너 오른쪽 담는 구간
		jp_plan_right.setLayout(new BorderLayout());
		jp_plan_right.add(city, BorderLayout.NORTH);
		jp_plan_right.add(jp_select, BorderLayout.CENTER);

		// 플래너 구간 담는 곳
		jp_planner.setLayout(new BorderLayout());
		jp_planner.add(jp_plan_left, BorderLayout.WEST);
		jp_planner.add(jp_plan_right, BorderLayout.CENTER);
		jp_planner.add(jp_comcan, BorderLayout.SOUTH);

		jp_card_insert.add(jp_planner, BorderLayout.CENTER);

		// 내일정 조회 페이지
		jp_card_select = new JPanel();
		jp_card_select.setBackground(Color.decode("#D4B8E8"));

		// SNB
		jp_SNB = new JPanel();
		title_su = main.p.getPlannerList().size();
		jb_title = new JButton[title_su];
		jp.setBackground(Color.decode("#D4B8E8"));
		for (int i = 0; i <= title_su - 1; i++) {
			jb_title[i] = new JButton(main.p.getPlannerList().get(i).getPLAN_TITLE());
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
					// 전체 버튼의 변경색
					for (int j = 0; j < jb_title.length; j++) {
						// 나머지 버튼의 배경색
						jb_title[j].setBackground(Color.decode("#F083BA"));
					}
					// 선택한 버튼의 배경색
					jb_title[index].setBackground(Color.decode("#B19CCB"));
					select_title.setText(" 여행지: " + main.p.getPlannerList().get(index).getTL_NUM() + "  |  날짜: "  + main.p.getPlannerList().get(index).getPLAN_DATE() + "~" + main.p.getPlannerList().get(index).getPLAN_LASTDATE() + "(" + main.p.getPlannerList().get(index).getPLAN_DAYS() + "일)" );
				
					// 선택한 일정에 상세일정 정보
					jp_select_text = new JPanel(new GridLayout(0, 1));
					jp_select_text.setBackground(Color.WHITE);
					int r = main.p.getPlannerList().get(index).getPLAN_DAYS();
					JTextArea[] textAreas = new JTextArea[r];
					JScrollPane[] scrollPanes = new JScrollPane[r];

					for (int i = 0; i < r; i++) {
						textAreas[i] = new JTextArea();
						textAreas[i].append("Day" + (i + 1) + "\n");

						scrollPanes[i] = new JScrollPane(textAreas[i], ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
								ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						textAreas[i].setLineWrap(true);
						textAreas[i].setEditable(false);
						textAreas[i].setBackground(Color.WHITE);

						// Assign different values to each JTextArea
						switch (i) {
						case 0:
							textAreas[i].append("Value for JTextArea 2 \n");
							textAreas[i].append("Value for JTextArea 2 \n");
							textAreas[i].append("Value for JTextArea 2 \n");
							textAreas[i].append("Value for JTextArea 2 \n");
							textAreas[i].append("Value for JTextArea 2 \n");
							textAreas[i].append("Value for JTextArea 2 \n");
							break;
						case 1:
							textAreas[i].append("Value for JTextArea 2");
							textAreas[i].append("Value for JTextArea 2");
							textAreas[i].append("Value for JTextArea 2");
							textAreas[i].append("Value for JTextArea 2");
							textAreas[i].append("Value for JTextArea 2");
							textAreas[i].append("Value for JTextArea 2");
							textAreas[i].append("Value for JTextArea 2");
							break;
						case 2:
							textAreas[i].append("Value for JTextArea 3");
							break;
						case 3:
							textAreas[i].append("Value for JTextArea 3");
							break;
						case 4:
							textAreas[i].append("Value for JTextArea 3");
							break;
						case 5:
							textAreas[i].append("Value for JTextArea 3");
							break;
						case 6:
							textAreas[i].append("Value for JTextArea 3");
							break;
						}

						jp_select_text.add(scrollPanes[i]);
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
		select_title = new JLabel(" 여행지: " + main.p.getPlannerList().get(0).getTL_NUM() + "  |  날짜: "  + main.p.getPlannerList().get(0).getPLAN_DATE() + "~" + main.p.getPlannerList().get(0).getPLAN_LASTDATE() + "(" + main.p.getPlannerList().get(0).getPLAN_DAYS() + "일)" );
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
		jp_select.add(jp_select_text, BorderLayout.CENTER);

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
			main.cardLayout.show(main.cardJPanel, "planner_Select");
		} else if (obj == jb3) { // 여행 후기
			main.cardLayout.show(main.cardJPanel, "allReview");
		} else if (obj == jb4) { // 마이페이지
			main.cardLayout.show(main.cardJPanel, "login_My_Infomodify");
		} else if (obj == jbMyInfo) { // 내정보
			main.cardLayout.show(main.cardJPanel, "login_My_Infomodify");
		} else if (obj == jbLogOut) { // 로그아웃
			main.cardLayout.show(main.cardJPanel, "login_Main");
		} else if (obj == bt_plan_edit) { // 플래너 상세정보 수정
			main.cardLayout.show(main.cardJPanel, "planner_InsertSpot");
		} else if (obj == bt_plan_del) { // 플래너 삭제 후 새로고침
			int result = JOptionPane.showConfirmDialog(null, "정말 플래너를 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
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