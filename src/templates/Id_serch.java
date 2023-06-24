package team.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class Id_serch extends JFrame{
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight,jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3 ,jb4;
	Font customFont;
	JLabel jLabel1, lb;
	TextPrompt tp1, tp2;
	Border newBorder;
	
	JPanel im_jp,log_im, lb_jp, name_jp, pw_jp, logMv_jp, btBt_jp, add_jp;
	JTextField jtf_name, jtf_em;
	JButton logMv_bt, join_bt, pwFin_bt,idCk_bt;
	
	public Id_serch() {
		super("아이디 찾기");
//		FONT
//		Font font = Font.loadFont("src/homework/fonts/Jalnan.ttf");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/team/login/logo_e/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/team/login/logo_e/Doodly.ttf")));
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
		//jp_buttons = new JPanel();
		jp_east = new JPanel();
		jp_west = new JPanel();
		jp_south = new JPanel();
		//jb1 = new JButton("새 일정 만들기");
		//jb1.setPreferredSize(new Dimension(120,30));
		//jb2 = new JButton("내 일정 조회");
		//jb2.setPreferredSize(new Dimension(120,30));
		//jb3 = new JButton("여행 후기");
		//jb3.setPreferredSize(new Dimension(120,30));
		//jb4 = new JButton("마이페이지");
		//jb4.setPreferredSize(new Dimension(120,30));
//		jb4.setPreferredSize(new Dimension(80, 40));
		//jbName = new JButton("이름");
		//jbMyInfo = new JButton("내 정보");
		//jbLogOut = new JButton("로그아웃");
		newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
		}
		
		
//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel1.setFont(new Font("Doodly",Font.BOLD,40));
		//jbName.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jbMyInfo.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jbLogOut.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb1.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb2.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb3.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb4.setFont(new Font("Jalnan",Font.PLAIN,12));

		
//		jbName,jbMyInfo,jbLogout 투명하게 하기
		/*
		 * { jbName.setOpaque(false); jbName.setContentAreaFilled(false);
		 * jbName.setBorderPainted(false); jbMyInfo.setOpaque(false);
		 * jbMyInfo.setContentAreaFilled(false); jbMyInfo.setBorderPainted(false);
		 * jbLogOut.setOpaque(false); jbLogOut.setContentAreaFilled(false);
		 * jbLogOut.setBorderPainted(false);
		 * 
		 * }
		 */
		
		

		
		
//		색 바꾸기
		//jb1.setBackground(Color.decode("#98b4d4"));
		{
		jp_headerMain.setBackground(Color.decode("#D4B8E8"));
		jp_headerSub.setBackground(Color.decode("#D4B8E8"));
		jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
		jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
		//jp_buttons.setBackground(Color.decode("#D4B8E8"));
		jp_west.setBackground(Color.decode("#D4B8E8"));
		jp_east.setBackground(Color.decode("#D4B8E8"));
		jp_south.setBackground(Color.decode("#D4B8E8"));
		jp.setBackground(Color.decode("#FFFFFF"));

		}
		
		
	
//		레이아웃
		{
		//jp_buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_headerSub.setLayout(new GridLayout(0,2));
		jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
//		jp_headerMain.setLayout(new GridLayout(2,0));
		//jp_headerSub.setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		
//		add image 
		ImageIcon imageIcon = new ImageIcon("src/team/login/logo_e/logo3.png");
		ImageIcon imagelogo = new ImageIcon("src/team/login/logo_e/Logo2.png");
		Image image = imageIcon.getImage(); // transform it 
		Image image2 = imagelogo.getImage();
		Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
		Image newimg2 = image2.getScaledInstance(300, 200, java.awt.Image.SCALE_DEFAULT);
		imageIcon = new ImageIcon(newimg);  // transform it back
		imagelogo = new ImageIcon(newimg2);
		
		// 신실 작업툴
			{	//panel선언
				im_jp = new JPanel();
				im_jp.setOpaque(false);
				lb_jp = new JPanel();
				lb_jp.setOpaque(false);
				name_jp = new JPanel();
				name_jp.setOpaque(false);
				pw_jp = new JPanel();
				pw_jp.setOpaque(false);
				logMv_jp = new JPanel();
				logMv_jp.setOpaque(false);
				add_jp = new JPanel();
				add_jp.setOpaque(false);
				btBt_jp = new JPanel();
				btBt_jp.setOpaque(false);
				logMv_bt = new JButton(" 로그인 페이지로 ");
				join_bt = new JButton("회원가입");
				pwFin_bt = new JButton("비밀번호 찾기");
				idCk_bt = new JButton("계정확인");
				
				//버튼 투명도
				join_bt.setOpaque(false);
				join_bt.setContentAreaFilled(false);
				join_bt.setBorderPainted(false);
				pwFin_bt.setOpaque(false);
				pwFin_bt.setContentAreaFilled(false);
				pwFin_bt.setBorderPainted(false);
				
				
				//색상
				logMv_bt.setBackground(Color.decode("#D4B8E8"));
				
				//font 
				logMv_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				join_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				pwFin_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				idCk_bt.setFont(new Font("Jalnan",Font.PLAIN,12));
				join_bt.setForeground(Color.GRAY);
				pwFin_bt.setForeground(Color.GRAY);
				logMv_bt.setForeground(Color.white);
				idCk_bt.setForeground(Color.WHITE);
			
				
				//모든 panel들 합칠 panel의 레이아웃 box로 설정
				add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));
				
				//jp1 / 이미지
				log_im = new JPanel();
				log_im.add(new JLabel(imagelogo));
				log_im.setOpaque(false);
				im_jp.add(log_im);
				
				// jp2 / 로그인label
				lb = new JLabel("아이디 찾기");
				lb.setFont(new Font("Jalnan",Font.PLAIN,26));
				lb_jp.add(lb);
				
				// jp3, jp4 / textfield+textprompt
				jtf_name = new JTextField(30);
				jtf_em = new JTextField(25);
		
				TextPrompt textprompt = new TextPrompt("이름", jtf_name);
				textprompt.setForeground(Color.gray);
				textprompt.changeAlpha(0.5f);
				textprompt.changeStyle(Font.BOLD + Font.ITALIC);           
				jtf_name.setBorder(newBorder);          
				jtf_name.setOpaque(false);  
				name_jp.add(jtf_name);
				// 위치조정
				name_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
				
				textprompt = new TextPrompt("이메일", jtf_em);
				textprompt.setForeground(Color.gray);
				textprompt.changeAlpha(0.5f);
				textprompt.changeStyle(Font.BOLD + Font.ITALIC);   
				jtf_em.setBorder(newBorder);          
				jtf_em.setOpaque(false);
				idCk_bt.setPreferredSize(new Dimension(75,25));
				pw_jp.add(jtf_em);
				pw_jp.add(idCk_bt);
				// 위치조정
				pw_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
				
				// jp5 로그인이동 버튼
				logMv_jp.add(logMv_bt);
				// 로그인버튼 크기지정
				logMv_bt.setPreferredSize(new Dimension(150,40));
				// 위치조정
				logMv_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
				
				//하단 jp7 버튼 패널에 붙이기
				btBt_jp.add(join_bt);
				btBt_jp.add(pwFin_bt);
				
				// 위치조정
				btBt_jp.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
				
				//모두 jp6에 집어넣기
				add_jp.add(im_jp);
				add_jp.add(lb_jp);
				add_jp.add(name_jp);
				add_jp.add(pw_jp);
				add_jp.add(logMv_jp);
				add_jp.add(btBt_jp);
				
				//jp6 jp에 넣기
				jp.add(add_jp);
		}
		
		
		
//		ADD 
		{
		jp_headerSubLeft.add(new JLabel(imageIcon));
		jp_headerSubLeft.add(jLabel1);
		//jp_headerSubRight.add(jbName);
		//jp_headerSubRight.add(new JLabel(" | "));
		//jp_headerSubRight.add(jbMyInfo);
		//jp_headerSubRight.add(new JLabel(" | "));
		//jp_headerSubRight.add(jbLogOut);
		//jp_buttons.add(jb1);6
		//jp_buttons.add(jb2);
		//jp_buttons.add(jb3);
		//jp_buttons.add(jb4);
		jp_headerSub.add(jp_headerSubLeft);
		jp_headerSub.add(jp_headerSubRight);
		jp_headerMain.add(jp_headerSub);
		//jp_headerMain.add(jp_buttons);
		
		
		add(jp_east,BorderLayout.EAST);
		add(jp_headerMain, BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		add(jp_west,BorderLayout.WEST);
		add(jp_south,BorderLayout.SOUTH);
		}
		
		setSize(1000,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
	                	new Id_serch();
	                }
	            });
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
