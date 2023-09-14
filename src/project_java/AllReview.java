package project_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DB_Place_All.Place_All_VO;
import DB_Travel_Location.Travel_Location_VO;
import Server.Protocol;
import project_admin.ButtonColumn;
import project_server.ProjectProtocol;

import java.awt.Component;
import javax.swing.SwingConstants;

public class AllReview extends JPanel implements ActionListener {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south,
			jp_south2, jp_west2;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1;

	JPanel review_bt_jp, left_review_jp, select_addr_jp, review2_jp, viewlb_jp, view_jp, jtf1_jp, jtf2_jp,
			center_add_jp, jp_center, center_jp;
	JLabel review_lb, look_lb;
	DefaultTableModel model;
	JTable review_jtb;
	JScrollPane review_jsp;
	JButton left_allreview_bt, left_myreview_bt, search_bt;

	JComboBox<String> search1, search2;
	String[] ser1 = { "::시/도::", "전체", "제주시", "서귀포시" };
	ArrayList<String> TravelList;
	String str_city, name_1;
	Border newBorder;
	Main main;
	Protocol p;
	public AllReview(Main main) {
		this.main = main;

		// buttoncolumn action
		Action pop = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				name_1 = table.getValueAt(modelRow, 0).toString();
				System.out.println(name_1);
				try {
					System.out.println("여기는 pop");
					ProjectProtocol p = new ProjectProtocol();
					Place_All_VO vo = new Place_All_VO();
					p.setCmd(408);
					//p.setRow(modelRow);
					p.setName(name_1);
					//p.setVo(vo);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		/*
		 * Action serch = new AbstractAction() { public void actionPerformed(ActionEvent
		 * e) { search1 = (JComboBox<String>) e.getSource(); Travel_Location_VO vo = new
		 * Travel_Location_VO(); vo.setCITY((String)
		 * main.allReview.search1.getSelectedItem());
		 * System.out.println(vo.getCITY()+"1"); try { Protocol p = new Protocol();
		 * p.setTravelvo(vo); System.out.println(vo.getCITY()+"2"); p.setCmd(414);
		 * AllReview.this.main.out.writeObject(p); AllReview.this.main.out.flush(); }
		 * catch (IOException e1) { e1.printStackTrace(); } } };
		 */

//	FONT
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts//Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//	변수 생성
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
			jp_center = new JPanel();
			jp_center.setOpaque(false);
			jp_south2 = new JPanel();
			jp_west2 = new JPanel();

			jb1 = new JButton("새 일정 만들기");
			jb1.setPreferredSize(new Dimension(120, 30));
			jb2 = new JButton("내 일정 조회");
			jb2.setPreferredSize(new Dimension(120, 30));
			jb3 = new JButton("여행 후기");
			jb3.setPreferredSize(new Dimension(120, 30));
			jb4 = new JButton("마이페이지");
			jb4.setPreferredSize(new Dimension(120, 30));

			jbName = new JButton(main.p.getVo().getM_NAME() + "님");
			jbMyInfo = new JButton("내 정보");
			jbLogOut = new JButton("로그아웃");

//	font 입력
			jLabel1 = new JLabel(" PERSONAL PLANNER");
			jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));
			jbName.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jbMyInfo.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jbLogOut.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jb1.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jb2.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jb3.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jb4.setFont(new Font("Jalnan", Font.PLAIN, 12));

//	jbName,jbMyInfo,jbLogout 투명하게 하기
			jbName.setOpaque(false);
			jbName.setContentAreaFilled(false);
			jbName.setBorderPainted(false);
			jbMyInfo.setOpaque(false);
			jbMyInfo.setContentAreaFilled(false);
			jbMyInfo.setBorderPainted(false);
			jbLogOut.setOpaque(false);
			jbLogOut.setContentAreaFilled(false);
			jbLogOut.setBorderPainted(false);

//	색 바꾸기
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
			jp_buttons.setBackground(Color.decode("#D4B8E8"));
			jp_west.setBackground(Color.decode("#D4B8E8"));
			jp_east.setBackground(Color.decode("#D4B8E8"));
			jp_south.setBackground(Color.decode("#D4B8E8"));
			jp.setBackground(Color.decode("#ffffff"));
			jb1.setBackground(Color.decode("#eee3f6"));
			jb2.setBackground(Color.decode("#eee3f6"));
			jb3.setBackground(Color.decode("#eee3f6"));
			jb4.setBackground(Color.decode("#eee3f6"));

//	레이아웃
			jp_buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSub.setLayout(new GridLayout(0, 2));
			jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));

			jp.setLayout(new BorderLayout());
			jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
			jp_center.setBorder(new EmptyBorder(50, 100, 30, 100));

//	add image 
			ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back

//	센터 좌측
			jp_west2.setPreferredSize(new Dimension(150, 700));
			jp_west2.setBackground(Color.decode("#F7C0DC"));
			JPanel jp_test = new JPanel();

//	전체 후기보기 작업
			// 좌측 변수
			review_lb = new JLabel(" 여행후기 ");
			left_review_jp = new JPanel();
			left_review_jp.setOpaque(false);
			left_allreview_bt = new JButton("전체 후기");
			left_myreview_bt = new JButton("내 후기");

			// 좌측 레이아웃
			left_review_jp.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

			// 좌측에 붙이기
			left_review_jp.add(review_lb);
			jp_west2.add(left_review_jp);
			review_bt_jp = new JPanel();
			review_bt_jp.setLayout(new GridLayout(0, 1));
			review_bt_jp.setBackground(Color.decode("#F7C0DC"));
			review_bt_jp.add(left_allreview_bt);
			review_bt_jp.add(new JLabel());
			review_bt_jp.add(left_myreview_bt);
			jp_west2.add(review_bt_jp);

			// 센터 가운데 변수
			look_lb = new JLabel("전체 후기 보기");
			center_add_jp = new JPanel();
			center_add_jp.setOpaque(false);

			center_add_jp.add(look_lb);
			jp_center.add(center_add_jp);

			select_addr_jp = new JPanel();
			select_addr_jp.setOpaque(false);

// 콤보박스
			TravelList = new ArrayList<String>();
			TravelList.add("::선택::");
			TravelList.add("전체");
			search1 = new JComboBox<>(ser1);
			search2 = new JComboBox<>(TravelList.toArray(new String[0]));
			search_bt = new JButton("검색");
			search1.setSelectedIndex(0);
			search2.setSelectedIndex(0);

//테이블을 담자
			model = new DefaultTableModel();
			model.addColumn("관광지 번호");
			model.addColumn("관광지 이름");
			model.addColumn("보기");

			review_jtb = new JTable(model);
			review_jsp = new JScrollPane(review_jtb, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			review_jsp.setPreferredSize(new Dimension(0, 30));

			ButtonColumn buttonColumn = new ButtonColumn(review_jtb, pop, 2);
			review_jtb.getColumn("관광지 번호").setWidth(0);
			review_jtb.getColumn("관광지 번호").setMinWidth(0);
			review_jtb.getColumn("관광지 번호").setMaxWidth(0);

			// 센터에 붙이기
			select_addr_jp.add(search1);
			select_addr_jp.add(search2);
			select_addr_jp.add(search_bt);
			select_addr_jp.setPreferredSize(new Dimension(5, -400));
			center_add_jp.add(select_addr_jp);
			center_add_jp.setLayout(new BoxLayout(center_add_jp, BoxLayout.Y_AXIS));
			center_add_jp.add(review_jsp);

			// font 및 버튼 꾸미기
			review_lb.setFont(new Font("Jalnan", Font.PLAIN, 20));
			look_lb.setFont(new Font("Jalnan", Font.PLAIN, 20));
			left_allreview_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
			left_myreview_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
			search_bt.setFont(new Font("Jalnan", Font.PLAIN, 15));
			search_bt.setForeground(Color.WHITE);
			search_bt.setPreferredSize(new Dimension(100, 30));
			left_allreview_bt.setOpaque(false);
			left_allreview_bt.setContentAreaFilled(false);
			left_myreview_bt.setOpaque(false);
			left_myreview_bt.setContentAreaFilled(false);
			search_bt.setBackground(Color.decode("#D4B8E8"));

			jp.add(jp_center);

//	ADD 
			jp_test.add(jp_west2, BorderLayout.CENTER);
			jp_test.setBackground(Color.decode("#D4B8E8"));
			jp.add(jp_test, BorderLayout.WEST);

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

			search1.setSelectedIndex(0);
			search2.setSelectedIndex(0);
		}
		left_allreview_bt.addActionListener(this);
		left_myreview_bt.addActionListener(this);
		search_bt.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jbMyInfo.addActionListener(this);
		jbLogOut.addActionListener(this);
		search1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton) {
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
			} else if (obj == left_allreview_bt) { // 전체후기 보기
				main.cardLayout.show(main.cardJPanel, "allReview");
			} else if (obj == left_myreview_bt) {
				main.cardLayout.show(main.cardJPanel, "myReview");
			} else if (obj == search_bt) {
				if (search1.getSelectedIndex() == 0 || search2.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "지역을 선택하세요", "Confirm", JOptionPane.ERROR_MESSAGE);
				} else if ((search1.getSelectedItem().toString()).equals("전체")
						&& (search2.getSelectedItem().toString()).equals("전체")) {
					try {
						// 데이터베이스 전체테이블 보기 추가하기
						Protocol p = new Protocol();
						p.setCmd(410);
						main.out.writeObject(p);
						main.out.flush();
						System.out.println(2);
					} catch (Exception e2) {
						System.out.println(e2);
					}
				} else if (!(search1.getSelectedItem().toString()).equals("전체")) {
					String city =search1.getSelectedItem().toString();
					String town =search2.getSelectedItem().toString();
					if(town.toString().equals("전체")) {
						town = "";
					}
					try {
						System.out.println(town+"rs");
						Protocol p = new Protocol();
						String str = "%"+city+" "+town+"%";
						p.setMsg(str);
						System.out.println(str);
						p.setCmd(412);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
//콤보박스 이벤트
		} else if (obj instanceof JComboBox) {  
			if (obj == search1) {
				if (search1.getSelectedItem().equals("제주시")) {
					Travel_Location_VO vo = new Travel_Location_VO();
					vo.setCITY((String) search1.getSelectedItem());
					str_city = vo.getCITY();
					try {
						Protocol p = new Protocol();
						p.setTravelvo(vo);
						System.out.println(vo.getCITY() + "1" + ".");
						p.setCmd(420);
						main.out.writeObject(p);
						main.out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else if (search1.getSelectedItem().equals("서귀포시")) {
					Travel_Location_VO vo = new Travel_Location_VO();
					vo.setCITY((String) search1.getSelectedItem());
					try {
						Protocol p = new Protocol();
						p.setTravelvo(vo);
						System.out.println(vo.getCITY() + "2" + ".");
						p.setCmd(422);
						main.out.writeObject(p);
						main.out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					search2.removeAllItems();
					search2.addItem("::선택::");
					search2.addItem("전체");
				}
			}
		} 
	}

	/*
	 * public void populateComboBox2(String selectedCity) { if (selectedCity != null
	 * && !selectedCity.isEmpty()) { try { // 콤보박스2에 선택된 도시에 해당하는 동/읍/리(town) 데이터 추가
	 * search2.addItem(""); // 빈 항목 추가
	 * 
	 * while (resultSet.next()) { String town = resultSet.getString("TOWN");
	 * search2.addItem(town); } } catch (SQLException e) { e.printStackTrace(); } }
	 * }
	 */

	public AllReview() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		try {
			// Select the Look and Feel
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// Start the application
////                    BaseSampleFrame app = new BaseSampleFrame("BaseSampleFrame");
//                    app.setSize(800, 600);
//                    app.setLocationRelativeTo(null);
//                    app.setVisible(true);
					new AllReview();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}