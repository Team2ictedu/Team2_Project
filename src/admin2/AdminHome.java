package admin2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;



public class AdminHome extends JFrame{
	JPanel jpWest, jpEast;
	/*jpWest Panels*/ JPanel jpAdminHome, jpPlaceEdit, jpUserEdit, jpReviewEdit, jpWestTop, jpLogOut;
	/*jpWest Buttons */ JButton adminHomeBtn,placeEditBtn , userEditBtn, reviewEditBtn, logOutBtn ;
	/*jpEast Panels*/ JPanel jpEastHeadMain, jpEastHeadNorth, jpEastHeadSouth, jpEastFootMain, jpEastFootNorth, jpEastFootCenter;
	/*jpEastJTA*/ JTextArea jta;
	/*jpEastJSP*/ JScrollPane jsp;
	/*jpEastJLABEL*/ JLabel welcomeLabel;
	
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	LocalDateTime now = LocalDateTime.now();  
	String todayDate = dtf.format(now);
	String adminName = "양동근";
	String welcome = String.format("어서오세요, %s님!      %s", adminName, todayDate);
	
	
	
	public AdminHome() {
		super("Admin");

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
		
		//jpEast jta
		jta = new JTextArea();
		jta.setText(String.format(""));
		jta.setRows(18);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jta.setFont(new Font("굴림", Font.PLAIN, 15));
		jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setBounds(50, 50, 600,200);
		
		//jpEastWelcome
		welcomeLabel = new JLabel(welcome);
		welcomeLabel.setFont(new Font("Jalnan",Font.PLAIN,18));
		jpEastHeadNorth.add(welcomeLabel);
		
		//jpEastHeadMain.add(North + South)
		jpEastHeadSouth.add(jsp);
		jpEastHeadMain.add(jpEastHeadNorth);
		jpEastHeadMain.add(jpEastHeadSouth);
		
		//jpEastHeadMain layout
		jpEastHeadMain.setLayout(new BoxLayout(jpEastHeadMain, BoxLayout.Y_AXIS));
		jpEastHeadNorth.setLocation(200	, 200);
		
		//jpEast.add(panels)
		jpEast.setLayout(new BoxLayout(jpEast,BoxLayout.Y_AXIS));
		jpEast.add(Box.createVerticalGlue());
		jpEast.add(jpEastHeadMain);
		jpEast.add(Box.createVerticalGlue());
//		jpEastHeadSouth.setBorder(titledBorderEastHead);
		
		jpAdminHome.setBackground(Color.decode("#424050"));
		
		
		
		
		jpEast.setPreferredSize(new Dimension(800,700));
		add(jpEast, BorderLayout.EAST);
		add(jpWest, BorderLayout.CENTER);
		
		setSize(1000,700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

			
	}
	
	 public static void main(String[] args) {
		
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new AdminHome();
					}
				});
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		 
	}
}
