package project_admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import project_server.ProjectProtocol;

public class AdminReview extends JPanel {
	AdminMain main;
	JPanel jpWest, jpEast;
	/* jpWest Panels */ JPanel jpAdminHome, jpPlaceEdit, jpUserEdit, jpReviewEdit, jpWestTop, jpLogOut, jpWestHeader;
	/* jpWest Buttons */ JButton adminHomeBtn, placeEditBtn, userEditBtn, reviewEditBtn, logOutBtn;
	/* jpEast Panels */ JPanel jpEastHeadMain, jpEastHeadNorth, jpEastHeadSouth, jpEastFootMain, jpEastFootNorth,
			jpEastFootCenter;
	/* jpEastHead Buttons */ JButton searchBtn, searchClearBtn;
	/* jpEastHead ArrayList */ List<String> selectionList;
	/* jpEastHead ComboBox & TextField */ JComboBox<String> eastHeadComboBox;
	JTextField eastHeadTextField;
	/* jpEastJTA */ JTextArea jta;
	public DefaultTableModel model = new DefaultTableModel();
	/*jpEastFootTable*/ JTable placeTable = new JTable(model);
	
	/* jpEastFoot Buttons */
	public JLabel adminLabel; // 양동근

	public AdminReview(AdminMain main) {
		this.main = main;
		// font
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// base jpanels
		jpWest = new JPanel();
		jpEast = new JPanel();
		jpEast.setBackground(Color.decode("#2e2e2e"));
		jpWest.setBackground(Color.decode("#2e2e2e"));

		// jpWest panels
		jpAdminHome = new JPanel();
		jpPlaceEdit = new JPanel();
		jpUserEdit = new JPanel();
		jpReviewEdit = new JPanel();
		jpWestTop = new JPanel();
		jpLogOut = new JPanel();
		jpWestHeader = new JPanel();
		jpAdminHome.setBackground(Color.decode("#242320"));
		jpPlaceEdit.setBackground(Color.decode("#242320"));
		jpUserEdit.setBackground(Color.decode("#242320"));
		jpLogOut.setBackground(Color.decode("#242320"));
		jpWestTop.setBackground(Color.decode("#242320"));
		jpReviewEdit.setBackground(Color.decode("#242320"));

		// button icons
		ImageIcon icon1 = new ImageIcon("src/icons/adminhouse.png");
		Image image1 = icon1.getImage();
		Image newimg1 = image1.getScaledInstance(25, 25, java.awt.Image.SCALE_DEFAULT);
		icon1 = new ImageIcon(newimg1);
		ImageIcon icon2 = new ImageIcon("src/icons/admincamera.png");
		Image image2 = icon2.getImage();
		Image newimg2 = image2.getScaledInstance(25, 25, java.awt.Image.SCALE_DEFAULT);
		icon2 = new ImageIcon(newimg2);
		ImageIcon icon3 = new ImageIcon("src/icons/adminuser.png");
		Image image3 = icon3.getImage();
		Image newimg3 = image3.getScaledInstance(25, 25, java.awt.Image.SCALE_DEFAULT);
		icon3 = new ImageIcon(newimg3);
		ImageIcon icon4 = new ImageIcon("src/icons/adminreview.png");
		Image image4 = icon4.getImage();
		Image newimg4 = image4.getScaledInstance(25, 25, java.awt.Image.SCALE_DEFAULT);
		icon4 = new ImageIcon(newimg4);

		// jpWest Buttons naming + decoration
		adminHomeBtn = new JButton("관리자 홈");
		adminHomeBtn.add(new JLabel(icon1));
		placeEditBtn = new JButton("관광지 수정");
		placeEditBtn.add(new JLabel(icon2));
		userEditBtn = new JButton("유저 수정");
		userEditBtn.add(new JLabel(icon3));
		reviewEditBtn = new JButton("후기 보기/ 삭제");
		reviewEditBtn.add(new JLabel(icon4));
		logOutBtn = new JButton("로그아웃");
//		adminHomeBtn.setBackground(Color.decode("#393646"));
//		placeEditBtn.setBackground(Color.decode("#393646")); 
//		userEditBtn.setBackground(Color.decode("#393646")); 	
//		reviewEditBtn.setBackground(Color.decode("#393646"));
		adminHomeBtn.setOpaque(false);
		adminHomeBtn.setContentAreaFilled(false);
		adminHomeBtn.setBorderPainted(false);
		adminHomeBtn.setPreferredSize(new Dimension(200, 50));
		adminHomeBtn.setForeground(Color.decode("#dbd8cc"));
		adminHomeBtn.setFont(new Font("Jalnan", Font.PLAIN, 12));
		placeEditBtn.setOpaque(false);
		placeEditBtn.setContentAreaFilled(false);
		placeEditBtn.setBorderPainted(false);
		placeEditBtn.setPreferredSize(new Dimension(200, 50));
		placeEditBtn.setForeground(Color.decode("#dbd8cc"));
		placeEditBtn.setFont(new Font("Jalnan", Font.PLAIN, 12));
		userEditBtn.setOpaque(false);
		userEditBtn.setContentAreaFilled(false);
		userEditBtn.setBorderPainted(false);
		userEditBtn.setPreferredSize(new Dimension(200, 50));
		userEditBtn.setForeground(Color.decode("#dbd8cc"));
		userEditBtn.setFont(new Font("Jalnan", Font.PLAIN, 12));
		reviewEditBtn.setOpaque(false);
		reviewEditBtn.setContentAreaFilled(false);
		reviewEditBtn.setBorderPainted(false);
		reviewEditBtn.setPreferredSize(new Dimension(200, 50));
		reviewEditBtn.setForeground(Color.decode("#dbd8cc"));
		reviewEditBtn.setFont(new Font("Jalnan", Font.PLAIN, 12));
		logOutBtn.setOpaque(false);
		logOutBtn.setContentAreaFilled(false);
		logOutBtn.setBorderPainted(false);
		logOutBtn.setPreferredSize(new Dimension(200, 50));
		logOutBtn.setForeground(Color.decode("#dbd8cc"));
		logOutBtn.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jpAdminHome.setBackground(Color.decode("#242320"));
		jpPlaceEdit.setBackground(Color.decode("#242320"));
		jpUserEdit.setBackground(Color.decode("#242320"));
		jpReviewEdit.setBackground(Color.decode("#242320"));

		// jpWestHeader
		jpWestHeader.setPreferredSize(new Dimension(200, 100));
		jpWestHeader.setBackground(Color.decode("#242320"));
		ImageIcon icon = new ImageIcon("src/icons/donaldtrump.png");
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		AdminHome home = new AdminHome(main);
		adminLabel = new JLabel(home.adminName);
		adminLabel.setFont(new Font("Jalnan", Font.PLAIN, 20));
		adminLabel.setForeground(Color.decode("#dbd8cc"));
		jpWestHeader.setLayout(new GridBagLayout());
		jpWestHeader.add(new JLabel(icon));
		jpWestHeader.add(adminLabel);

		// jpWest panel.add(button)
		jpAdminHome.add(adminHomeBtn);
		jpPlaceEdit.add(placeEditBtn);
		jpUserEdit.add(userEditBtn);
		jpReviewEdit.add(reviewEditBtn);

		// jpWest.add(panel)
		jpWest.setLayout(new BorderLayout());
		jpWestTop.add(jpWestHeader);
		jpWestTop.add(jpAdminHome);
		jpWestTop.add(jpPlaceEdit);
		jpWestTop.add(jpUserEdit);
		jpWestTop.add(jpReviewEdit);
		jpWest.add(jpWestTop, BorderLayout.CENTER);
		jpLogOut.add(logOutBtn);
		jpWest.add(jpLogOut, BorderLayout.SOUTH);

		// jpEast border
		Border newBorder = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.decode("#d6d6d6"));
		jpWest.setBorder(newBorder);
		jpWest.setOpaque(true);
		Border newBorder2 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#d6d6d6"));
		jpWestHeader.setBorder(newBorder2);
		jpWestHeader.setOpaque(true);

		Border titledBorderEastHead = BorderFactory.createTitledBorder("검색창");
		Border titledBorderEastFoot = BorderFactory.createTitledBorder("내용");
		((TitledBorder) titledBorderEastHead).setTitleColor(Color.decode("#dbd8cc"));
		((TitledBorder) titledBorderEastFoot).setTitleColor(Color.decode("#dbd8cc"));
		repaint();

		// jpEast panels naming + colors
		jpEastHeadMain = new JPanel();
		jpEastHeadNorth = new JPanel();
		jpEastHeadSouth = new JPanel();
		jpEastHeadMain.setBackground(Color.decode("#2e2e2e"));
		jpEastHeadNorth.setBackground(Color.decode("#2e2e2e"));
		jpEastHeadSouth.setBackground(Color.decode("#2e2e2e"));
		jpEastFootMain = new JPanel();
		jpEastFootNorth = new JPanel();
		jpEastFootCenter = new JPanel();

		// jpEastHead comboBox & textField
		selectionList = new ArrayList<String>();
		selectionList.add("전체보기");
		selectionList.add("아이디");
		selectionList.add("관광지");
		
		eastHeadComboBox = new JComboBox<String>(selectionList.toArray(new String[0]));
		eastHeadTextField = new JTextField(20);

		// jpEastHeadNorth.add(Checkbox + jtf)
		jpEastHeadNorth.add(eastHeadComboBox);
		jpEastHeadNorth.add(eastHeadTextField);

		// jpEastHeadSouth buttons
		searchBtn = new JButton("검색");
		searchClearBtn = new JButton("검색 초기화");

		// jpEastHeadSouth.add(jpEastButton)
		jpEastHeadSouth.add(searchBtn);
		jpEastHeadSouth.add(searchClearBtn);

		// jpEastHeadMain.add(panels)
		jpEastHeadMain.add(jpEastHeadNorth);
		jpEastHeadMain.add(jpEastHeadSouth);
		jpEastHeadMain.setLayout(new BoxLayout(jpEastHeadMain, BoxLayout.Y_AXIS));

		// jpEastFoot panels + colors
		jpEastFootMain = new JPanel();
		jpEastFootNorth = new JPanel();
		jpEastFootCenter = new JPanel();
		jpEastFootMain.setBackground(Color.decode("#2e2e2e"));

		// jpEastFootNorth createBtn

		// jpEastFootCenter table
		model.addColumn("작성자 아이디");
		model.addColumn("관광지");
		model.addColumn("후기 내용");
		model.addColumn("삭제");


		placeTable = new JTable(model);
		placeTable.setShowGrid(false);
		placeTable.setShowHorizontalLines(false);
		placeTable.setShowVerticalLines(false);
		placeTable.setRowMargin(0);
		placeTable.setIntercellSpacing(new Dimension(0, 0));
		placeTable.setFillsViewportHeight(true);
//		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
//		placeTable.setRowSorter(sorter);

		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// 리뷰 삭제
				JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        System.out.println("AdminReview selected model row is :"+modelRow);
		        AdminReviewVO vo = new AdminReviewVO();
		        vo.setM_id(table.getValueAt(modelRow, 0).toString());
		        vo.setPr_con(table.getValueAt(modelRow, 2).toString());
				try {
					ProjectProtocol p = new ProjectProtocol();
					p.setCmd(94); 
					p.setReviewvo(vo);
					main.main.out.writeObject(p);
					main.main.out.flush();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		};

		ButtonColumn buttonColumn2 = new ButtonColumn(placeTable, delete, 3);

		JScrollPane placeTableSP = new JScrollPane(placeTable);
		placeTableSP.setPreferredSize(new Dimension(600, 400));
		jpEastFootCenter.add(placeTableSP);

		// jpEastFootSouth btns

		// jpEastFootMain.add()
		jpEastFootMain.setPreferredSize(new Dimension(700, 500));
		jpEastFootMain.add(jpEastFootCenter);

		// jpEast.add(panels)
		jpEast.add(jpEastHeadMain);
		jpEastHeadMain.setBorder(titledBorderEastHead);
		jpEast.add(jpEastFootMain);
		jpEastFootMain.setBorder(titledBorderEastFoot);
		jpReviewEdit.setBackground(Color.decode("#2e2e2e"));

		jpEast.setPreferredSize(new Dimension(800, 700));

		setLayout(new BorderLayout());
		add(jpWest, BorderLayout.CENTER);
		add(jpEast, BorderLayout.EAST);

//		setSize(1000,700);
//		setResizable(false);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);

		adminHomeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.main.cardLayout.show(main.main.cardJPanel, "admin_greeting");
			}
		});
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((eastHeadComboBox.getSelectedItem().toString()).equalsIgnoreCase("전체보기")) {
					try {
						ProjectProtocol p = new ProjectProtocol();
						p.setCmd(91);
						main.main.out.writeObject(p);
						main.main.out.flush();
						System.out.println("sending cmd 91	");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					try {
						ProjectProtocol p = new ProjectProtocol();
						p.setCmd(93);
						p.setMsg(eastHeadComboBox.getSelectedItem().toString());
						p.setMsg2(eastHeadTextField.getText());
						main.main.out.writeObject(p);
						main.main.out.flush();
					} catch (Exception e2) {
					}

				}
				
				
				
				
			}
		});
		placeEditBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.main.cardLayout.show(main.main.cardJPanel, "admin_places");
			}
		});
		userEditBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.main.cardLayout.show(main.main.cardJPanel, "admin_users");
			}
		});
		reviewEditBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.main.cardLayout.show(main.main.cardJPanel, "admin_reviews");
			}
		});
		
		searchClearBtn.addActionListener(new ActionListener() {// 검색 초기화 JButton

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);

			}
		});

		logOutBtn.addActionListener(new ActionListener() { // 로그아웃

			@Override
			public void actionPerformed(ActionEvent e) {
				main.main.cardLayout.show(main.main.cardJPanel, "login_Main");
			}
		});
	}

}
