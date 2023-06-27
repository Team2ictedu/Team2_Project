package project_java;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class Login_Main extends JPanel{
	Main main;
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_buttons, jp_east, jp_west, jp_south;
	JButton jb1, jb2, jb3 ,jb4;
	Font customFont;
	JLabel jLabel1, lb;
	TextPrompt tp1, tp2;
	Border newBorder;
	
	JPanel im_jp,log_im, lb_jp, id_jp, pw_jp, logBt_jp, btBt_jp, add_jp;
	JTextField jtf_id, jtf_pw;
	JButton log_bt, join_bt, idFin_bt, pwFin_bt;

	
	public Login_Main(Main main) {
		this.main = main;
//		FONT
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts//Doodly.ttf")));
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
		newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
		}
		
		
//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel1.setFont(new Font("Doodly",Font.BOLD,40));
		
//		색 바꾸기
		//jb1.setBackground(Color.decode("#98b4d4"));
		{
		jp_headerMain.setBackground(Color.decode("#D4B8E8"));
		jp_headerSub.setBackground(Color.decode("#D4B8E8"));
		jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
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
		jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
//		jp_headerMain.setLayout(new GridLayout(2,0));
		//jp_headerSub.setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		
//		add image 
		ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
		ImageIcon imagelogo = new ImageIcon("src/images/text_logo.png");
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
				id_jp = new JPanel();
				id_jp.setOpaque(false);
				pw_jp = new JPanel();
				pw_jp.setOpaque(false);
				logBt_jp = new JPanel();
				logBt_jp.setOpaque(false);
				add_jp = new JPanel();
				add_jp.setOpaque(false);
				btBt_jp = new JPanel();
				btBt_jp.setOpaque(false);
				log_bt = new JButton(" 로그인 ");
				join_bt = new JButton("회원가입");
				idFin_bt = new JButton("아이디 찾기");
				pwFin_bt = new JButton("비밀번호 찾기");
				
				//버튼 투명도
				join_bt.setOpaque(false);
				join_bt.setContentAreaFilled(false);
				join_bt.setBorderPainted(false);
				idFin_bt.setOpaque(false);
				idFin_bt.setContentAreaFilled(false);
				idFin_bt.setBorderPainted(false);
				pwFin_bt.setOpaque(false);
				pwFin_bt.setContentAreaFilled(false);
				pwFin_bt.setBorderPainted(false);
				
				// 로그인버튼 크기지정
				log_bt.setPreferredSize(new Dimension(120,40));
				
				//색상
				log_bt.setBackground(Color.decode("#D4B8E8"));
				
				//font 
				log_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				join_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				idFin_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				pwFin_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
				join_bt.setForeground(Color.GRAY);
				idFin_bt.setForeground(Color.GRAY);
				pwFin_bt.setForeground(Color.GRAY);
				log_bt.setForeground(Color.white);
			
				
				//모든 panel들 합칠 panel의 레이아웃 box로 설정
				add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));
				
				//jp1 / 이미지
				log_im = new JPanel();
				log_im.add(new JLabel(imagelogo));
				log_im.setOpaque(false);
				im_jp.add(log_im);
				
				// jp2 / 로그인label
				lb = new JLabel("로 그 인");
				lb.setFont(new Font("Jalnan",Font.PLAIN,30));
				lb_jp.add(lb);
				
				// jp3, jp4 / textfield+textprompt
				jtf_id = new JTextField(30);
				jtf_pw = new JTextField(30);
		
				TextPrompt textprompt = new TextPrompt("아이디", jtf_id);
				textprompt.setForeground(Color.gray);
				textprompt.changeAlpha(0.5f);
				textprompt.changeStyle(Font.BOLD + Font.ITALIC);           
				jtf_id.setBorder(newBorder);          
				jtf_id.setOpaque(false);  
				id_jp.add(jtf_id);
				// 위치조정
				id_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
				
				textprompt = new TextPrompt("비밀번호", jtf_pw);
				textprompt.setForeground(Color.gray);
				textprompt.changeAlpha(0.5f);
				textprompt.changeStyle(Font.BOLD + Font.ITALIC);   
				jtf_pw.setBorder(newBorder);          
				jtf_pw.setOpaque(false);  
				pw_jp.add(jtf_pw);
				// 위치조정
				pw_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
				
				// jp5 로그인 버튼
				logBt_jp.add(log_bt);
				// 위치조정
				logBt_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
				
				//하단 jp7 버튼 패널에 붙이기
				btBt_jp.add(join_bt);
				btBt_jp.add(idFin_bt);
				btBt_jp.add(pwFin_bt);
				// 위치조정
				btBt_jp.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
				
				//모두 jp6에 집어넣기
				add_jp.add(im_jp);
				add_jp.add(lb_jp);
				add_jp.add(id_jp);
				add_jp.add(pw_jp);
				add_jp.add(logBt_jp);
				add_jp.add(btBt_jp);
				
				//jp6 jp에 넣기
				jp.add(add_jp);
		}
		
		
		
//		ADD 
		{
		jp_headerSubLeft.add(new JLabel(imageIcon));
		jp_headerSubLeft.add(jLabel1);
		//jp_buttons.add(jb1);6
		//jp_buttons.add(jb2);
		//jp_buttons.add(jb3);
		//jp_buttons.add(jb4);
		jp_headerSub.add(jp_headerSubLeft);
		jp_headerMain.add(jp_headerSub);
		//jp_headerMain.add(jp_buttons);
		
		setLayout(new BorderLayout());
		add(jp_headerMain, BorderLayout.NORTH);
		add(jp,BorderLayout.CENTER);
		}
		
	}
	public Login_Main() {
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
	                	new Login_Main();
	                }
	            });
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
