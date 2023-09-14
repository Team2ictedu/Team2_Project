package project_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DB_Place_All.Place_All_VO;
import DB_Place_Review.Place_Review_DAO;
import DB_Place_Review.Place_Review_VO;
import DB_Planner.Planner_VO;
import Server.Protocol;
import project_admin.ButtonColumn;

public class MyReview extends JPanel implements ActionListener {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south,
			jp_south2, jp_west2;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1;

	JPanel lb_jp, bt1_jp, bt2_jp, review1_jp, review2_jp, wr_jp, viewlb_jp, combo_jp, view_jp, jtf1_jp, jtf2_jp, viewjp,
			writejp, add1_jp, add2_jp, jp_center;
	JLabel re_lb, wr_lb, view_lb;
	JTextField review_jtf;
	JScrollPane review_jsp;
	JButton left_allreview_bt, left_myreview_bt, search_bt, delete_bt;
	JComboBox<String> search1, search2;
	Border newBorder;
	JTable table;
	DefaultTableModel dtm;
	Object[][] datas = { { "1", "가평캠핑", "한라봉가게", "불멍 물멍 힐링했어요루룰", "삭제" },
			{ "2", "일본 탐방", "한라봉가게2", "오사카 난바 더웠지만 재밌어요", "삭제" } };
	String name, plantitle, placename, review, name2, name3, name4;

	Main main;

	public MyReview(Main main) {

		this.main = main;

		// buttoncolumn action
		Action pop = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				name = table.getValueAt(modelRow, 4).toString(); // 후기내용 추출후 삭제
				name2 = table.getValueAt(modelRow, 0).toString(); // 플래너번호 추출후 삭제

				System.out.println(name);
				System.out.println(name4);
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					try {
						System.out.println("여기는 pop");
						Protocol p = new Protocol();
						Place_Review_VO vo = new Place_Review_VO();
						vo.setM_ID(name4); // id값 넣기 main.p.getVo().getM_NAME()
						// vo.setM_id(main.p.getVo().getM_ID()); // id값 넣기 main.p.getVo().getM_NAME()
//						String name = main.p.getVo().getM_ID();

						vo.setPR_CON(name);
						vo.setPLAN_NUM(name2);
						p.setReviewVo2(vo);
						p.setCmd(426);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
				}
			}
		};

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 콤보박스 메서드
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JCheckBox box = new JCheckBox();
				box.setSelected(((Boolean) value).booleanValue());
				box.setHorizontalAlignment(JLabel.CENTER);
				return box;
			}
		};

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

//회원정보수정			
			jp.setLayout(new BorderLayout());
			jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
			jp_center.setBorder(new EmptyBorder(50, 100, 50, 100));

//	jb4.setPreferredSize(new Dimension(80, 40));
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

//	add image 
			ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back

//		 센터 좌측
			jp_west2.setPreferredSize(new Dimension(150, 700));
			jp_west2.setBackground(Color.decode("#F7C0DC"));
			JPanel jp_test = new JPanel();

			// 신실 작업 툴
			{
				// 변수 선언
				re_lb = new JLabel(" 여행후기 ");
				wr_lb = new JLabel("후기작성");
				view_lb = new JLabel("작성한 후기");
				lb_jp = new JPanel();
				lb_jp.setOpaque(false);
				add1_jp = new JPanel();
				add1_jp.setOpaque(false);
				add2_jp = new JPanel();
				add2_jp.setOpaque(false);
				wr_jp = new JPanel();
				wr_jp.setOpaque(false);
				viewlb_jp = new JPanel();
				viewlb_jp.setOpaque(false);
				view_jp = new JPanel();
				view_jp.setOpaque(false);
				jtf1_jp = new JPanel();
				jtf1_jp.setOpaque(false);
				jtf2_jp = new JPanel();
				jtf2_jp.setOpaque(false);
				bt1_jp = new JPanel();
				bt1_jp.setOpaque(false);
				bt2_jp = new JPanel();
				bt2_jp.setOpaque(false);
				review1_jp = new JPanel();
				review1_jp.setOpaque(false);
				review2_jp = new JPanel();
				review2_jp.setOpaque(false);
				review_jtf = new JTextField(50);
				left_allreview_bt = new JButton("전체 후기");
				left_myreview_bt = new JButton("내 후기");
				search_bt = new JButton("작성");
				delete_bt = new JButton("삭제");
				combo_jp = new JPanel();
				combo_jp.setOpaque(false);

				search1 = new JComboBox<>();
				search1.addItem("플래너 선택");
				search2 = new JComboBox<>();
				search2.addItem("관광지 선택");

				// 모든 panel들 합칠 panel의 레이아웃 box로 설정
				add1_jp.setLayout(new BoxLayout(add1_jp, BoxLayout.Y_AXIS));

				// font 및 버튼 꾸미기
				re_lb.setFont(new Font("Jalnan", Font.PLAIN, 20));
				wr_lb.setFont(new Font("Jalnan", Font.PLAIN, 20));
				view_lb.setFont(new Font("Jalnan", Font.PLAIN, 20));
				left_allreview_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
				left_myreview_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
				search_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
				delete_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
				left_allreview_bt.setOpaque(false);
				left_allreview_bt.setContentAreaFilled(false);
				left_myreview_bt.setOpaque(false);
				left_myreview_bt.setContentAreaFilled(false);
				search_bt.setBackground(Color.decode("#D4B8E8"));
				delete_bt.setBackground(Color.decode("#D4B8E8"));

				// 패널에 붙이기
				// 1. 왼쪽 라벨
				lb_jp.add(re_lb);
				// 위치조정
				lb_jp.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
				jp_west2.add(lb_jp);

				// 전체후기, 후기작성 버튼 붙이기
				bt1_jp.add(left_allreview_bt);
				bt2_jp.add(left_myreview_bt);
				jp_west2.add(bt1_jp);
				jp_west2.add(bt2_jp);

				// 콤보박스 붙이기
				combo_jp.setLayout(new BoxLayout(combo_jp, BoxLayout.Y_AXIS));
				combo_jp.add(search1);
				combo_jp.add(search2);

				// 2. 리뷰 작성, 보기 패널 붙이기
				// wr_jp.add(wr_lb);
				// 위치조정
				wr_lb.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
				review1_jp.add(search1);
				review1_jp.add(search2);
				TextPrompt textprompt = new TextPrompt("여행후기를 작성해주세요", review_jtf);
				textprompt.setForeground(Color.gray);
				textprompt.changeAlpha(0.7f);
				textprompt.changeStyle(Font.BOLD + Font.ITALIC);
				review1_jp.add(review_jtf);

				// 크기조정
				review_jtf.setPreferredSize(new Dimension(150, 50));
				review1_jp.add(search_bt);

				// 모든 panel들 합칠 panel의 레이아웃 box로 설정
				viewjp = new JPanel();
				viewjp.setLayout(new FlowLayout());
				viewjp.add(view_lb);
				viewjp.setBackground(Color.WHITE);
				view_jp.setLayout(new BorderLayout());
				view_jp.add(viewjp, BorderLayout.NORTH);
// 테이블
				dtm = new DefaultTableModel();
				dtm.addColumn("관광지 번호");
				dtm.addColumn("글번호");
				dtm.addColumn("플래너이름");
				dtm.addColumn("관광지이름");
				dtm.addColumn("후기 내용");
				dtm.addColumn("삭제");

				table = new JTable(dtm);
				table.getColumn("관광지 번호").setWidth(0);
				table.getColumn("관광지 번호").setMinWidth(0);
				table.getColumn("관광지 번호").setMaxWidth(0);
				// dtm.addRow(datas);
				ButtonColumn buttoncolumn = new ButtonColumn(table, pop, 5);

				name4 = main.uservo.getM_ID();

				table.setSize(800, 300);
				// table.getColumn("Check").setCellRenderer(dcr);

				review_jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				review_jsp.setPreferredSize(new Dimension(500, 220));
				view_jp.add(review_jsp, BorderLayout.CENTER);

				add1_jp.add(wr_lb, BorderLayout.CENTER);
				add1_jp.add(combo_jp);
				add1_jp.add(review1_jp);
				add2_jp.add(viewlb_jp);
				add2_jp.add(view_jp);

				jp_center.add(add1_jp);
				jp_center.add(add2_jp);

				jp.add(jp_center);
			}

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
		}
		left_allreview_bt.addActionListener(this); // 전체후기
		left_myreview_bt.addActionListener(this); // 내후기
		search_bt.addActionListener(this); // 작성
		delete_bt.addActionListener(this); // 삭제
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jbMyInfo.addActionListener(this);
		jbLogOut.addActionListener(this);
		search1.addActionListener(this);
		search2.addActionListener(this);
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
			} else if (obj == left_myreview_bt) { // 내후기 보기
				try {
					Protocol p = new Protocol();
					p.setName(name4);
					p.setCmd(424);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				try {
					search1.removeAllItems();
					search2.removeAllItems();
					search1.addItem("플래너 선택");
					Protocol p = new Protocol();
					
					p.setName(main.uservo.getM_ID());
					p.setCmd(208);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				main.cardLayout.show(main.cardJPanel, "myReview");
			} else if (obj == search_bt) {
				// 작성하는 버튼 기능
				if (search1.getSelectedIndex() == 0 || search2.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "후기를 작성할 여행을 선택해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				} else if (review_jtf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "여행후기를 작성해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
					review_jtf.requestFocus();
				} else {
					try {
						Protocol p = new Protocol();
						Place_Review_VO reviewvo = new Place_Review_VO();
						for (Planner_VO vo : main.planList) {
							if (search1.getSelectedItem().toString().equals(vo.getPLAN_TITLE())) {
								reviewvo.setPLAN_NUM(vo.getPLAN_NUM());
							}
						}
						for (Place_All_VO vo2 : main.list211) {
							if (search2.getSelectedItem().toString().equals(vo2.getPA_NAME())) {
								reviewvo.setPA_NUM(vo2.getPA_NUM());
							}
						}
						reviewvo.setM_ID(main.uservo.getM_ID());
						reviewvo.setPR_CON(review_jtf.getText().toString());
						p.setReviewVo2(reviewvo);
						p.setCmd(212);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						System.out.println(e2);
					}

					try {
						Protocol p = new Protocol();
						p.setName(name4);
						p.setCmd(424);
						main.out.writeObject(p);
						main.out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else if (obj == delete_bt) {
				// 삭제하는 버튼 기능
//				boolean isSelected = box.isSelected();
//				if (isSelected == true) {
//					int result = JOptionPane.showConfirmDialog(null, "작성한 후기를 삭제하시겠습니까?", "Confirm",
//							JOptionPane.YES_NO_OPTION);
//					if (result == JOptionPane.YES_OPTION) {
//						JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.", "Confirm", JOptionPane.PLAIN_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "삭제할 후기를 선택해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
//				}
			}
		} else if (obj instanceof JComboBox) {
			if (obj == search1) {
				if (search1.getSelectedIndex() == 0) {
					search2.removeAllItems();
					search2.addItem("관광지 선택");
				} else {
					try {
						search2.removeAllItems();
						search2.addItem("관광지 선택");
						Protocol p = new Protocol();
						p.setName(main.uservo.getM_ID());
						p.setCmd(210);
						main.out.writeObject(p);
						main.out.flush();

					} catch (Exception e2) {
						System.out.println(e);
					}
				}
			}
		}
	}

	public MyReview() {
		// TODO Auto-generated constructor stub
	}

}