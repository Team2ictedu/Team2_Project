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
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.table.DefaultTableModel;

import DB_Place_All.Place_All_DAO;
import DB_Place_All.Place_All_VO;
import DB_Place_Select.Place_Select_DAO;
import DB_Place_Select.Place_Select_VO;
import DB_Planner.Planner_DAO;
import DB_Planner.Planner_VO;
import DB_Travel_Location.Travel_Location_DAO;
import DB_Travel_Location.Travel_Location_VO;
import DB_User.UserVO;
import Server.Protocol;
import project_admin.ButtonColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

public class Planner_InsertSpot extends JPanel implements ActionListener {
	Main main;
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1;

	JLabel title, city;
	JPanel jp_SNB, jp_planner, jp_plan_left, jp_plan_right, jp_select, jp_sel, jp_comcan;
	JButton jb_delete_spot, jb_select, jb_add_spot, bt_Cancel, bt_Complete;
	JTextField jtf_select;
	Planner_Create planner_Create;
	public DefaultTableModel model = new DefaultTableModel();
	/* jpEastFootTable */ JTable placeTable = new JTable(model);
	public DefaultTableModel model2 = new DefaultTableModel();
	/* jpEastFootTable */ JTable placeTable2 = new JTable(model2);
	Place_All_VO vo2;
	String day = "1";
	int index2;

	public Planner_InsertSpot(Main main) {
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

		// planner 작업
		jp_SNB = new JPanel();
		JButton[] jb_day = new JButton[main.planvo2.getPLAN_DAYS()];
		jp.setBackground(Color.decode("#D4B8E8"));
		for (int i = 0; i < main.planvo2.getPLAN_DAYS(); i++) {
			jb_day[i] = new JButton("Day " + (i + 1));
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
					jtf_select.setText("");
					// 전체 버튼의 변경색
					for (int j = 0; j < jb_day.length; j++) {
						// 나머지 버튼의 배경색
						if (j != index) {
							jb_day[j].setBackground(Color.decode("#F083BA"));
						}
					}
					int cnt = 0;
					int cnt2 = 0;
					for (int i = 0; i < placeTable2.getRowCount(); i++) {
						if (placeTable2.getValueAt(i, 4) == null) {
							cnt++;
						}
					}

					if (cnt == 0) {
						// 선택한 버튼의 배경색
						jb_day[index].setBackground(Color.decode("#B19CCB"));
						day = Integer.toString(index + 1);

						for (int i = 0; i < placeTable2.getRowCount(); i++) {
							try {
								Place_Select_VO vo = new Place_Select_VO();
								vo.setPS_TIME((String) placeTable2.getValueAt(i, 4));
								if (placeTable2.getValueAt(i, 5) == null) {
									vo.setPS_CON("");
								} else {
									vo.setPS_CON((String) placeTable2.getValueAt(i, 5));
								}
								vo.setPS_NUM((String) placeTable2.getValueAt(i, 6));
								Protocol p = new Protocol();
								p.setPlaceSelectVo(vo);
								p.setCmd(2006);
								main.out.writeObject(p);
								main.out.flush();
								index2 = index;
							} catch (Exception e2) {
								System.out.println(e2);
							}
						}
						// Day에 맞는 자기가 선택한 관광지 조회
						try {
							model.setRowCount(0);
							model2.setRowCount(0);
							Protocol p = new Protocol();
							p.setMsg(main.planvo2.getPLAN_NUM());
							p.setCmd(2000);
							main.out.writeObject(p);
							main.out.flush();
						} catch (Exception e2) {
							// TODO: handle exception
						}
					} else {
						JOptionPane.showMessageDialog(null, "시간은 필수 입력사항입니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
						cnt = 0;
						// 시간을 입력하지 않은 경우에도 jb_day[index]의 배경색을 변경
						jb_day[index2].setBackground(Color.decode("#B19CCB"));
					}
					// 모든 관광지 조회
					try {
						Protocol p = new Protocol();
						p.setMsg("%%");
						p.setCmd(2004);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}
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
		title = new JLabel("제목: " + main.planvo2.getPLAN_TITLE());
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Aharoni", Font.BOLD, 18));
		title.setPreferredSize(new Dimension(10, 30));

		// 선택한 관광지
		model2.addColumn("관광지명");
		model2.addColumn("위치");
		model2.addColumn("설명");
		model2.addColumn("가격");
		model2.addColumn("시간");
		model2.addColumn("메모");
		model2.addColumn("번호");
		placeTable2 = new JTable(model2);
		placeTable2.setShowGrid(false);
		placeTable2.setShowHorizontalLines(false);
		placeTable2.setShowVerticalLines(false);
		placeTable2.setRowMargin(0);
		placeTable2.setIntercellSpacing(new Dimension(0, 0));
		placeTable2.setFillsViewportHeight(true);
		JScrollPane placeTableSP2 = new JScrollPane(placeTable2);
		// 검색한 정보
		placeTable2.getColumn("번호").setWidth(0);
		placeTable2.getColumn("번호").setMinWidth(0);
		placeTable2.getColumn("번호").setMaxWidth(0);

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
		jp_plan_left.add(placeTableSP2, BorderLayout.CENTER);
		jp_plan_left.add(jb_delete_spot, BorderLayout.SOUTH);

		// 플래너 오른쪽 부분
		jp_plan_right = new JPanel();
		jp_plan_right.setBorder(new LineBorder(Color.decode("#E6E6E6")));
		jp_plan_right.setBackground(Color.decode("#B19CCB"));
		Travel_Location_VO vo = Travel_Location_DAO.getLocation2(main.planvo2.getTL_NUM());
		// 제목 부분
		city = new JLabel("여행지: 제주 " + vo.getCITY() + " " + vo.getTOWN());
		city.setFont(new Font("Aharoni", Font.BOLD, 18));
		city.setForeground(Color.WHITE);
		city.setPreferredSize(new Dimension(10, 30));

		// 검색기능 부분
		jp_select = new JPanel();
		// 검색기능 부분 검색하기
		jp_sel = new JPanel();
		jp_sel.setBackground(Color.WHITE);
		jtf_select = new JTextField(20);
		jtf_select.requestFocus();
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

		model.addColumn("관광지명");
		model.addColumn("위치");
		model.addColumn("설명");
		model.addColumn("가격");
		model.addColumn("번호");
		placeTable = new JTable(model);
		placeTable.setShowGrid(false);
		placeTable.setShowHorizontalLines(false);
		placeTable.setShowVerticalLines(false);
		placeTable.setRowMargin(0);
		placeTable.setIntercellSpacing(new Dimension(0, 0));
		placeTable.setFillsViewportHeight(true);
		JScrollPane placeTableSP = new JScrollPane(placeTable);
		// 검색한 정보
		placeTable.getColumn("번호").setWidth(0);
		placeTable.getColumn("번호").setMinWidth(0);
		placeTable.getColumn("번호").setMaxWidth(0);
		// 엔터 키 입력시 버튼 클릭 이벤트

		// 검색한 정보 일정에 담는 버튼
		jb_add_spot = new JButton("Add Spot");
		jb_add_spot.setForeground(Color.white);
		jb_add_spot.setFont(new Font("Aharoni", Font.BOLD, 18));
		jb_add_spot.setBackground(Color.decode("#F083BA"));
		jb_add_spot.setBorderPainted(false);
		jb_add_spot.setPreferredSize(new Dimension(30, 45));

		// 모든 관광지 조회
		try {
			Protocol p = new Protocol();
			p.setMsg("%%");
			p.setCmd(2004);
			main.out.writeObject(p);
			main.out.flush();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		// 검색부분 담는 구간
		jp_select.setLayout(new BorderLayout());
		jp_select.add(jp_sel, BorderLayout.NORTH);
		jp_select.add(placeTableSP, BorderLayout.CENTER);
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
			if (jtf_select.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "검색어를 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jtf_select.requestFocus();
			} else {
				try {
					Protocol p = new Protocol();
					p.setMsg("%" + jtf_select.getText() + "%");
					p.setCmd(2004);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else if (obj == jb_add_spot) {
			if (placeTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "추가할 관광지를 선택하세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
			} else {
				int row = placeTable.getSelectedRow();
				int column = 4;
				String num = (String) placeTable.getValueAt(row, column);
				// 선택한 관광지 추가
				try {
					Place_Select_VO vo = new Place_Select_VO();
					Protocol p = new Protocol();
					vo.setPA_NUM(num);
					vo.setPS_DAY(day);
					vo.setPLAN_NUM(main.planvo2.getPLAN_NUM());
					p.setPlaceSelectVo(vo);
					p.setCmd(2002);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 선택한 관광지 조회
				try {
					model2.setRowCount(0);
					Protocol p = new Protocol();
					p.setMsg(main.planvo2.getPLAN_NUM());
					p.setCmd(2000);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else if (obj == jb_delete_spot) {
			if (placeTable2.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "삭제할 관광지를 선택하세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
			} else {
				int row = placeTable2.getSelectedRow();
				int column = 6;
				String num = (String) placeTable2.getValueAt(row, column);

				// 선택한 관광지 제거
				try {
					Protocol p = new Protocol();
					p.setMsg(num);
					p.setCmd(2003);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < placeTable2.getRowCount(); i++) {
					try {
						Place_Select_VO vo = new Place_Select_VO();
						vo.setPS_TIME((String) (placeTable2.getValueAt(i, 4)));
						if (placeTable2.getValueAt(i, 5) == null) {
							vo.setPS_CON("");
						} else {
							vo.setPS_CON((String) (placeTable2.getValueAt(i, 5)));
						}
						vo.setPS_NUM((String) (placeTable2.getValueAt(i, 6)));
						Protocol p = new Protocol();
						p.setPlaceSelectVo(vo);
						p.setCmd(2006);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				// 선택한 관광지 조회
				try {
					model2.setRowCount(0);
					Protocol p = new Protocol();
					p.setMsg(main.planvo2.getPLAN_NUM());
					p.setCmd(2000);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else if (obj == bt_Complete) {
			int cnt = 0;
			for (int i = 0; i < placeTable2.getRowCount(); i++) {
				if ((String) (placeTable2.getValueAt(i, 4)) == null) {
					cnt++;
				}
			}
			if (cnt == 0) {
				for (int i = 0; i < placeTable2.getRowCount(); i++) {
					try {
						Place_Select_VO vo = new Place_Select_VO();
						vo.setPS_TIME((String) (placeTable2.getValueAt(i, 4)));
						if (placeTable2.getValueAt(i, 5) == null) {
							vo.setPS_CON("");
						} else {
							vo.setPS_CON((String) (placeTable2.getValueAt(i, 5)));
						}
						vo.setPS_NUM((String) (placeTable2.getValueAt(i, 6)));
						Protocol p = new Protocol();
						p.setPlaceSelectVo(vo);
						p.setCmd(2006);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				// 플래너 새로고침
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
				main.cardLayout.show(main.cardJPanel, "planner_Select");
			} else {
				JOptionPane.showMessageDialog(null, "시간은 필수 입력사항입니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == bt_Cancel) {
			int result = JOptionPane.showConfirmDialog(null, "플래너 작성을 취소하시겠습니까?\n현재까지 작업한 모든 내용이 삭제됩니다.", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				// 플래너 삭제
				try {
					Protocol p = new Protocol();
					p.setMsg(main.planvo2.getPLAN_NUM());
					p.setCmd(2007);
					System.out.println(3);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}

				// 플래너 새로고침
				try {
					Protocol p = new Protocol();
					p.setMsg(main.uservo.getM_ID());
					p.setCmd(40);
					System.out.println(3);
					main.out.writeObject(p);
					main.out.flush();
					System.out.println(4);
				} catch (IOException e1) {
					System.out.println(e1);
				}
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