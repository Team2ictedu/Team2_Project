package templates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
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
public class Template2 extends JFrame{
	JPanel jp, jp1,log_im, jp2, jp3, jp4, jp5, jp6, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight,jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3 ,jb4, log_bt;
	Font customFont;
	JLabel jLabel1, lb;
	JTextField jtf_id, jtf_pw;
	TextPrompt tp1, tp2;
	Border newBorder;
	
	public Template2() {
		super("PERPL");
//		FONT
//		Font font = Font.loadFont("src/homework/fonts/Jalnan.ttf");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/homework/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/homework/fonts/Doodly.ttf")));
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
		jbName = new JButton("이름");
		jbMyInfo = new JButton("내 정보");
		jbLogOut = new JButton("로그아웃");
		log_bt = new JButton(" 로그인 ");
		newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
		}
		
		
//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel1.setFont(new Font("Doodly",Font.BOLD,40));
		jbName.setFont(new Font("Jalnan",Font.PLAIN,12));
		jbMyInfo.setFont(new Font("Jalnan",Font.PLAIN,12));
		jbLogOut.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb1.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb2.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb3.setFont(new Font("Jalnan",Font.PLAIN,12));
		//jb4.setFont(new Font("Jalnan",Font.PLAIN,12));

		
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
		//jb1.setBackground(Color.decode("#98b4d4"));
		{
		jp_headerMain.setBackground(Color.decode("#D4B8E8"));
		jp_headerSub.setBackground(Color.decode("#D4B8E8"));
		jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
		jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
//		jp_buttons.setBackground(Color.decode("#D4B8E8"));
		jp_west.setBackground(Color.decode("#D4B8E8"));
		jp_east.setBackground(Color.decode("#D4B8E8"));
		jp_south.setBackground(Color.decode("#D4B8E8"));
//		jp.setBackground(Color.decode("#FFFFFF"));
		jp.setBackground(Color.decode("#eee3f6"));
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
		ImageIcon imageIcon = new ImageIcon("src/homework/images/logo3.png");
		ImageIcon imagelogo = new ImageIcon("src/homework/images/logo1.png");
		Image image = imageIcon.getImage(); // transform it 
		Image image2 = imagelogo.getImage();
		Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
		Image newimg2 = image2.getScaledInstance(564, 183, java.awt.Image.SCALE_DEFAULT);
		imageIcon = new ImageIcon(newimg);  // transform it back
		imagelogo = new ImageIcon(newimg2);
		
		// 신실 작업툴
		{	//panel선언
			jp1 = new JPanel();
			jp1.setOpaque(false);
			jp2 = new JPanel();
			jp2.setOpaque(false);
			jp3 = new JPanel();
			jp3.setOpaque(false);
			jp4 = new JPanel();
			jp4.setOpaque(false);
			jp5 = new JPanel();
			jp5.setOpaque(false);
			jp6 = new JPanel();
			jp6.setOpaque(false);
			
			//모든 panel들 합칠 panel의 레이아웃 box로 설정
			jp6.setLayout(new BoxLayout(jp6, BoxLayout.Y_AXIS));
			
			//jp1 / 이미지
			log_im = new JPanel();
			log_im.add(new JLabel(imagelogo));
			log_im.setOpaque(false);
			jp1.add(log_im);
			
			// jp2 / 로그인label
			lb = new JLabel("로 그 인");
			lb.setFont(new Font("Jalnan",Font.PLAIN,30));
			jp2.add(lb);
			
			
			// jp3, jp4 / textfield+textprompt
			jtf_id = new JTextField(15);
			jtf_pw = new JTextField(15);
	
			TextPrompt textprompt = new TextPrompt("아이디", jtf_id);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.5f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);           
			jtf_id.setBorder(newBorder);          
			jtf_id.setOpaque(false);  
			jp3.add(jtf_id);
			
			textprompt = new TextPrompt("비밀번호", jtf_pw);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.5f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);   
			jtf_pw.setBorder(newBorder);          
			jtf_pw.setOpaque(false);  
			jp4.add(jtf_pw);
			
			// jp5 로그인 버튼
			jp5.add(log_bt);
			
			
			//모두 jp6에 집어넣기
			jp6.add(jp1);
			jp6.add(jp2);
			jp6.add(jp3);
			jp6.add(jp4);
			jp6.add(jp5);
			
			//jp6 jp에 넣기
			jp.add(jp6);
			
			
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
		jp_headerSub.add(jp_headerSubLeft);
		jp_headerSub.add(jp_headerSubRight);
		jp_headerMain.add(jp_headerSub);
		
		
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
	            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

	            SwingUtilities.invokeLater(new Runnable() {

	                @Override
	                public void run() {
	                	new Template2();
	                }
	            });
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}