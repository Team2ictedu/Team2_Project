package admin2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;



public class AdminTemplate1 extends JPanel{
	AdminMain main;
	JPanel jpWest, jpEast;
	/*jpWest Panels*/ JPanel jpAdminHome, jpPlaceEdit, jpUserEdit, jpReviewEdit, jpWestTop, jpLogOut;
	/*jpWest Buttons */ JButton adminHomeBtn,placeEditBtn , userEditBtn, reviewEditBtn, logOutBtn ;
	/*jpEast Panels*/ JPanel jpEastHeadMain, jpEastHeadNorth, jpEastHeadSouth, jpEastFootMain, jpEastFootNorth, jpEastFootCenter;
	/*jpEastHead Buttons*/ JButton searchBtn, searchClearBtn;
	/*jpEastHead ArrayList*/ List<String> selectionList;
	/*jpEastHead ComboBox & TextField*/ JComboBox<String> eastHeadComboBox; JTextField eastHeadTextField;
	/*jpEastJTA*/ JTextArea jta;
	/*jpEastFootTable*/ JTable placeTable; 
 	/*jpEastFoot Buttons*/ 
	
	
	
	public AdminTemplate1(AdminMain main) {
		this.main = main;
		//font 
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
		jpEast.setBackground(Color.decode("#393646"));
		jpWest.setBackground(Color.decode("#393646"));
		
		// jpWest panels
		jpAdminHome 	= new JPanel();
		jpPlaceEdit 	= new JPanel();
		jpUserEdit 		= new JPanel();
		jpReviewEdit 	= new JPanel();
		jpWestTop 		= new JPanel();
		jpLogOut 		= new JPanel();
		jpAdminHome.setBackground(Color.decode("#393646"));
		jpPlaceEdit.setBackground(Color.decode("#393646"));
		jpUserEdit.setBackground(Color.decode("#393646"));
		jpLogOut.setBackground(Color.decode("#393646"));
		jpWestTop.setBackground(Color.decode("#393646"));
		jpReviewEdit.setBackground(Color.decode("#393646"));
		
		// jpWest Buttons naming + decoration
		adminHomeBtn  	= new JButton("관리자 홈");
		placeEditBtn 	= new JButton("관광지 수정");  
		userEditBtn 	 = new JButton("유저 수정");
		reviewEditBtn	 = new JButton("후기 보기/ 삭제");
		logOutBtn		= new JButton("로그아웃");
//		adminHomeBtn.setBackground(Color.decode("#393646"));
//		placeEditBtn.setBackground(Color.decode("#393646")); 
//		userEditBtn.setBackground(Color.decode("#393646")); 	
//		reviewEditBtn.setBackground(Color.decode("#393646"));
		adminHomeBtn.setOpaque(false); 
		adminHomeBtn.setContentAreaFilled(false);
		adminHomeBtn.setBorderPainted(false);
		adminHomeBtn.setPreferredSize(new Dimension(200,50));
		adminHomeBtn.setForeground(Color.decode("#C07F00"));
		adminHomeBtn.setFont(new Font("Jalnan",Font.PLAIN,18));
		placeEditBtn.setOpaque(false); 
		placeEditBtn.setContentAreaFilled(false);
		placeEditBtn.setBorderPainted(false);
		placeEditBtn.setPreferredSize(new Dimension(200,50));
		placeEditBtn.setForeground(Color.decode("#C07F00"));
		placeEditBtn.setFont(new Font("Jalnan",Font.PLAIN,18));
		userEditBtn.setOpaque(false); 
		userEditBtn.setContentAreaFilled(false);
		userEditBtn.setBorderPainted(false);
		userEditBtn.setPreferredSize(new Dimension(200,50));
		userEditBtn.setForeground(Color.decode("#C07F00"));
		userEditBtn.setFont(new Font("Jalnan",Font.PLAIN,18));
		reviewEditBtn.setOpaque(false); 
		reviewEditBtn.setContentAreaFilled(false);
		reviewEditBtn.setBorderPainted(false);
		reviewEditBtn.setPreferredSize(new Dimension(200,50));
		reviewEditBtn.setForeground(Color.decode("#C07F00"));
		reviewEditBtn.setFont(new Font("Jalnan",Font.PLAIN,18));
		logOutBtn.setOpaque(false); 
		logOutBtn.setContentAreaFilled(false);
		logOutBtn.setBorderPainted(false);
		logOutBtn.setPreferredSize(new Dimension(200,50));
		logOutBtn.setForeground(Color.decode("#C07F00"));
		logOutBtn.setFont(new Font("Jalnan",Font.PLAIN,18));
		jpAdminHome.setBackground(Color.decode("#393646")); 
		jpPlaceEdit.setBackground(Color.decode("#393646")); 
		jpUserEdit.setBackground(Color.decode("#393646")); 	
		jpReviewEdit.setBackground(Color.decode("#393646"));
		
		
		// jpWest panel.add(button)
		jpAdminHome.add(adminHomeBtn);
		jpPlaceEdit.add(placeEditBtn);
		jpUserEdit.add(userEditBtn);
		jpReviewEdit.add(reviewEditBtn);
		
		//jpWest.add(panel)
		jpWest.setLayout(new BorderLayout());
		jpWestTop.add(jpAdminHome);
		jpWestTop.add(jpPlaceEdit);
		jpWestTop.add(jpUserEdit);
		jpWestTop.add(jpReviewEdit);
		jpWest.add(jpWestTop, BorderLayout.CENTER);
		jpLogOut.add(logOutBtn);
		jpWest.add(jpLogOut, BorderLayout.SOUTH);
		
		
		//jpEast border
		Border newBorder = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.decode("#4F4557"));
		jpEast.setBorder(newBorder);
		jpEast.setOpaque(true);
		
		Border titledBorderEastHead = BorderFactory.createTitledBorder("검색창");
		Border titledBorderEastFoot = BorderFactory.createTitledBorder("내용");
		
		//jpEast panels naming + colors
		jpEastHeadMain = new JPanel();
		jpEastHeadNorth = new JPanel();
		jpEastHeadSouth = new JPanel();
		jpEastHeadMain.setBackground(Color.decode("#393646"));
		jpEastHeadNorth.setBackground(Color.decode("#393646"));
		jpEastHeadSouth.setBackground(Color.decode("#393646"));
		jpEastFootMain = new JPanel();
		jpEastFootNorth = new JPanel();
		jpEastFootCenter = new JPanel();
		
		//jpEastHead comboBox & textField
		selectionList = new ArrayList<String>();
		selectionList.add("전체보기");
		selectionList.add("World");
		eastHeadComboBox = new JComboBox<String>(selectionList.toArray(new String[0]));
		eastHeadTextField = new JTextField(20);
		
		
		//jpEastHeadNorth.add(Checkbox + jtf)          
		jpEastHeadNorth.add(eastHeadComboBox);
		jpEastHeadNorth.add(eastHeadTextField);
		
		//jpEastHeadSouth buttons
		searchBtn = new JButton("검색");
		searchClearBtn = new JButton("검색 초기화");

		//jpEastHeadSouth.add(jpEastButton)
		jpEastHeadSouth.add(searchBtn);
		jpEastHeadSouth.add(searchClearBtn);
		
		//jpEastHeadMain.add(panels)
		jpEastHeadMain.add(jpEastHeadNorth);
		jpEastHeadMain.add(jpEastHeadSouth);
		jpEastHeadMain.setLayout(new BoxLayout(jpEastHeadMain, BoxLayout.Y_AXIS));
		
		//jpEastFoot panels + colors
		jpEastFootMain = new JPanel();
		jpEastFootNorth = new JPanel();
		jpEastFootCenter = new JPanel();
		jpEastFootMain.setBackground(Color.decode("#393646"));
		
		//jpEastFootNorth createBtn
		
		//jpEastFootCenter table
		ArrayList<PlaceVO> list = PlacesDAO.getInstance().getSelectAll(); 
		PlaceTableModel model = new PlaceTableModel(list);
		
		placeTable = new JTable(model);
		placeTable.setShowGrid(false);
		placeTable.setShowHorizontalLines(false);
		placeTable.setShowVerticalLines(false);
		placeTable.setRowMargin(0);
		placeTable.setIntercellSpacing(new Dimension(0, 0));
		placeTable.setFillsViewportHeight(true);
		TableRowSorter<PlaceTableModel> sorter = new TableRowSorter<>(model);
		placeTable.setRowSorter(sorter);
		
		
		
		
		Action delet2e = new AbstractAction() 
				{
					public void actionPerformed(ActionEvent e) {
						System.out.println("s");
					}
			
				};
		ButtonColumn buttonColumn = new ButtonColumn(placeTable, delet2e, 4);
		

		
		
		JScrollPane placeTableSP = new JScrollPane(placeTable);
		placeTableSP.setPreferredSize(new Dimension(600,400));
		jpEastFootCenter.add(placeTable);
		
		
		//jpEastFootSouth btns
		
		
		//jpEastFootMain.add()
		jpEastFootMain.setPreferredSize(new Dimension(700,500));
		jpEastFootMain.add(jpEastFootCenter);
		
		//jpEast.add(panels)
		jpEast.add(jpEastHeadMain);
		jpEastHeadMain.setBorder(titledBorderEastHead);
		jpEast.add(jpEastFootMain);
		jpEastFootMain.setBorder(titledBorderEastFoot);
		jpPlaceEdit.setBackground(Color.decode("#424050"));
		
		
		
		
		jpEast.setPreferredSize(new Dimension(800,700));
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
				main.cardLayout.show(main.cardPanel, "greeting");				
			}
		});
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("S");
			}
		});
	}
	
}
