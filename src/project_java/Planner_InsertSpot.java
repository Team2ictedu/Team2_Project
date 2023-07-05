package project_java;

import java.awt.BorderLayout;
import java.awt.Button;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Planner_InsertSpot extends JPanel implements ActionListener {
	Main main;
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1;

	JLabel title, city;
	JPanel jp_SNB, jp_planner, jp_plan_left, jp_plan_right, jp_select, jp_sel, jp_comcan;
	JButton jb_delete_spot, jb_select, jb_add_spot, bt_Cancel, bt_Complete;
	JButton[] jb_day;
	JTextField jtf_select;
	JTextArea add_jta, select_jta;
	JScrollPane add_jsp, select_jsp;

	public Planner_InsertSpot(Main main) {
		this.main = main;
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

		// planner 작업

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
		jp.add(jp_SNB, BorderLayout.WEST);

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
		add_jta.append("1. 관광지명 \n");
		add_jta.append("위치: 관광지위치 \n");
		add_jta.append("설명: 관광지설명 \n");
		add_jta.append("평점: 관광지평점 \n");
		add_jta.append("기본 금액: ₩ 관광지금액 \n");
		add_jta.append("추가 금액: ₩ 추가할 금액 입력\n");
		add_jta.append("총 금액: ₩ 기본금액 + 추가금액\n");
		add_jta.append("관광시간: 관광시간 입력\n\n");
		add_jta.append("1. 관광지명 \n");
		add_jta.append("위치: 관광지위치 \n");
		add_jta.append("설명: 관광지설명 \n");
		add_jta.append("평점: 관광지평점 \n");
		add_jta.append("기본 금액: ₩ 관광지금액 \n");
		add_jta.append("추가 금액: ₩ 추가할 금액 입력\n");
		add_jta.append("총 금액: ₩ 기본금액 + 추가금액\n");
		add_jta.append("관광시간: 관광시간 입력\n\n");
		add_jta.append("1. 관광지명 \n");
		add_jta.append("위치: 관광지위치 \n");
		add_jta.append("설명: 관광지설명 \n");
		add_jta.append("평점: 관광지평점 \n");
		add_jta.append("기본 금액: ₩ 관광지금액 \n");
		add_jta.append("추가 금액: ₩ 추가할 금액 입력\n");
		add_jta.append("총 금액: ₩ 기본금액 + 추가금액\n");
		add_jta.append("관광시간: 관광시간 입력\n\n");
		add_jta.append("1. 관광지명 \n");
		add_jta.append("위치: 관광지위치 \n");
		add_jta.append("설명: 관광지설명 \n");
		add_jta.append("평점: 관광지평점 \n");
		add_jta.append("기본 금액: ₩ 관광지금액 \n");
		add_jta.append("추가 금액: ₩ 추가할 금액 입력\n");
		add_jta.append("총 금액: ₩ 기본금액 + 추가금액\n");
		add_jta.append("관광시간: 관광시간 입력\n\n");
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
		TextPrompt tp_name = new TextPrompt("검색어를 입력하세요.", jtf_select);
		tp_name.setFont(new Font("Jalnan", Font.PLAIN, 10));
		tp_name.setForeground(Color.gray);
		tp_name.changeAlpha(0.5f);
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
		select_jta = new JTextArea();
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

		select_jta.append("2. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		select_jta.append("3. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		select_jta.append("4. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		select_jta.append("5. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		select_jta.append("6. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		select_jta.append("7. 관광지명 \n");
		select_jta.append("위치: 관광지위치 \n");
		select_jta.append("설명: 관광지설명 \n");
		select_jta.append("평점: 관광지평점 \n");
		select_jta.append("기본 금액: ₩ 관광지금액 \n\n");

		select_jta.append("8. 관광지명 \n");
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

		jp.add(jp_planner, BorderLayout.CENTER);

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
			setBackground(Color.decode("#D4B8E8"));
		}
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jbMyInfo.addActionListener(this);
		jbLogOut.addActionListener(this);
		jb_select.addActionListener(this);
		jb_add_spot.addActionListener(this);
		jb_delete_spot.addActionListener(this);
		bt_Complete.addActionListener(this);
		bt_Cancel.addActionListener(this);
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
		} else if (obj == jb_select) {
			if(jtf_select.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "검색어를 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jtf_select.requestFocus();
			} else {
				// 검색내용출력
			}
		} else if(obj == jb_add_spot) {
			// 선택안하면 하면 메세지창 띄우고 선택하면 담는것
		} else if(obj == jb_delete_spot) {
			// 위와 동일
		} else if (obj == bt_Complete) {
			main.cardLayout.show(main.cardJPanel, "planner_Select");
		} else if (obj == bt_Cancel) {
			int result = JOptionPane.showConfirmDialog(null, "플래너 작성을 취소하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				main.cardLayout.show(main.cardJPanel, "planner_Select");
			}
		}
	}

	public Planner_InsertSpot() {
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
					new Planner_InsertSpot();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}