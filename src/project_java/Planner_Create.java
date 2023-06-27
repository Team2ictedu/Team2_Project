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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Main.Main;

public class Planner_Create extends JPanel implements ActionListener {
	Main main;
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel_new;
	JTextField jtf_name, jtf_date, jtf_days;
	JButton jb_create, jb_back ;
	String[] city = { "City" }; // 시
	JComboBox<String> jcom = new JComboBox<>(city);
	String[] town = { "Town" }; // 동읍리
	JComboBox<String> jcom2 = new JComboBox<>(town);
	JPanel jp_main, jp_checkbox;

	public Planner_Create(Main main) {
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
		}
//        TextPrompt tp = new TextPrompt(" ", jtf1);     //<외부 클래스>
//        tp.setForeground(Color.gray);
//        tp.changeAlpha(0.5f);
//        tp.changeStyle(Font.BOLD + Font.ITALIC);     
		{
			jb1.setPreferredSize(new Dimension(120, 30));
			jb2 = new JButton("내 일정 조회");
			jb2.setPreferredSize(new Dimension(120, 30));
			jb3 = new JButton("여행 후기");
			jb3.setPreferredSize(new Dimension(120, 30));
			jb4 = new JButton("마이페이지");
			jb4.setPreferredSize(new Dimension(120, 30));
//		jb4.setPreferredSize(new Dimension(80, 40));
			jbName = new JButton("이름");
			jbMyInfo = new JButton("내 정보");
			jbLogOut = new JButton("로그아웃");
		}

//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel2 = new JLabel("Trip Name");
		jLabel3 = new JLabel("City / Town");
		jLabel4 = new JLabel("Start Date");
		jLabel5 = new JLabel("Days");
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

		//

		// 소연작업
		{
			// 새일정 만들기 타이틀
			JPanel jp_title = new JPanel();
			jLabel_new = new JLabel("새 일정 만들기");
			jp_title.setLayout(new FlowLayout());
			jp_title.add(jLabel_new);
			jp_title.setBackground(Color.decode("#B19CCB"));
			jp_title.setPreferredSize(new Dimension(70,30));
			
			
			// Font
			jLabel2.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel3.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel4.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel5.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel_new.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel_new.setForeground(Color.white);
			
			// 주요 내용들
			jp_main = new JPanel();
			jtf_name = new JTextField();
			jtf_date = new JTextField();
			jtf_days = new JTextField();
			jb_create = new JButton("CREATE");
			jb_back = new JButton("BACK");
			jb_create.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jb_back.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jp_checkbox = new JPanel();
			jp_checkbox.add(jcom);
			jp_checkbox.add(jcom2);
			jp_main.setBackground(Color.WHITE);
			jp_main.setLayout(new GridLayout(0, 1));
			jp_checkbox.setLayout(new GridLayout(0, 2));
			jp_main.add(jLabel2);
			
			//textfield 안 글자
			TextPrompt tp = new TextPrompt("여행제목 ", jtf_name);   //외부클래스
			tp.setForeground(Color.gray);
			tp.changeAlpha(0.5f);
			tp.setFont(new Font("Jalnan", Font.PLAIN, 12));
			TextPrompt tp2 = new TextPrompt("시작날짜 ", jtf_date);   //외부클래스
			tp2.setForeground(Color.gray);
			tp2.changeAlpha(0.5f);
			tp2.setFont(new Font("Jalnan", Font.PLAIN, 12));
			TextPrompt tp3 = new TextPrompt("기간입력 ", jtf_days);   //외부클래스
			tp3.setForeground(Color.gray);
			tp3.changeAlpha(0.5f);
			tp3.setFont(new Font("Jalnan", Font.PLAIN, 12));
			
			

			jp_main.add(jtf_name);
			jp_main.add(jLabel3);
			jp_main.add(jp_checkbox);
			jp_main.add(jLabel4);
			jp_main.add(jtf_date);
			jp_main.add(jLabel5);
			jp_main.add(jtf_days);
			jp_main.add(new JLabel(""));
			jp_main.add(jb_create);
			jp_main.add(jb_back);
			jb_create.setBackground(Color.decode("#F083BA"));
			jb_create.setForeground(Color.white);
			jb_back.setBackground(Color.decode("#ffffff"));
			jb_back.setForeground(Color.BLACK);
			jp_main.setBorder(BorderFactory.createEmptyBorder(50, 350, 100, 350));
  
			jp.setLayout(new BorderLayout());
			jp.add(jp_title, BorderLayout.NORTH);
			jp.add(jp_main, BorderLayout.CENTER);
			
		}
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
			add(jp,BorderLayout.CENTER);
			setBackground(Color.decode("#D4B8E8"));
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Planner_Create() {
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
					new Planner_Create();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}