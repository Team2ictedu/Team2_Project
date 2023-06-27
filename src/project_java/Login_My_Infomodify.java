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
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login_My_Infomodify extends JPanel {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south,
			jp_center, jp_name, jp_email, jp_phone, jp_birth, jp_south2, jp_west2;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4, jb5, jb6, mypage_bt, information_bt, update_bt,
			pw_update_bt, withdraw_bt;
	Font customFont;
	JLabel jLabel1, jl_name, jl_email, jl_birth, jl_phone;
	JTextArea jta;
	JScrollPane jsp;
	JTextField tf_phone, tf_name, tf_email, tf_birth;
	Main main;
	
	
	public Login_My_Infomodify(Main main) {

		this.main = main;
		// 여기서부터
		{

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
				jp_south2 = new JPanel();
				jp_west2 = new JPanel();

				jp_name = new JPanel(new GridLayout(0, 2));
				jp_email = new JPanel(new GridLayout(0, 2));
				jp_phone = new JPanel(new GridLayout(0, 2));
				jp_birth = new JPanel(new GridLayout(0, 2));

				jb1 = new JButton("새 일정 만들기");

				jb1.setPreferredSize(new Dimension(120, 30));
				jb2 = new JButton("내 일정 조회");
				jb2.setPreferredSize(new Dimension(120, 30));
				jb3 = new JButton("여행 후기");
				jb3.setPreferredSize(new Dimension(120, 30));
				jb4 = new JButton("마이페이지");
				jb4.setPreferredSize(new Dimension(120, 30));

				jb5 = new JButton("확 인");
				jb6 = new JButton("취 소");

				tf_email = new JTextField(20);
				tf_birth = new JTextField(20);
				tf_phone = new JTextField(20);
				tf_name = new JTextField(20);
				jl_name = new JLabel("이름 : ", JLabel.CENTER);
				jl_email = new JLabel("e-mail : ", JLabel.CENTER);
				jl_birth = new JLabel("생년월일 : ", JLabel.CENTER);
				jl_phone = new JLabel("전화번호 : ", JLabel.CENTER);

				jp_name.setBackground(Color.WHITE);
				jp_email.setBackground(Color.WHITE);
				jp_birth.setBackground(Color.WHITE);
				jp_phone.setBackground(Color.WHITE);

//회원정보수정			
				jp.setLayout(new BorderLayout());
				JPanel jp_title = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel jl_my_info = new JLabel("내 정보 수정");
				jp_title.add(jl_my_info, BorderLayout.NORTH);
				jp_title.setBackground(Color.decode("#B19CCB"));
				jl_my_info.setFont(new Font("Jalnan", Font.PLAIN, 12));
				jl_my_info.setForeground(Color.WHITE);
				jp_title.setPreferredSize(new Dimension(70, 30));

				jp_name.add(jl_name);
				jl_name.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_name.add(tf_name);
				tf_name.setText("김지수");
				tf_name.setEditable(false);

				jp_email.add(jl_email);
				jl_email.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_email.add(tf_email);

				jp_birth.add(jl_birth);
				jl_birth.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_birth.add(tf_birth);

				jp_phone.add(jl_phone);
				jl_phone.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_phone.add(tf_phone);

				jp_center.add(jp_name);
				jp_center.add(jp_email);
				jp_center.add(jp_birth);
				jp_center.add(jp_phone);

				jp_south2.add(jb5);
				jp_south2.add(jb6);
				jp_south2.setBackground(Color.WHITE);

				jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
				// jp_center.setLayout(new GridLayout(5,0));
				jp_center.setBorder(new EmptyBorder(100, 100, 200, 100));
				JPanel jp_center2 = new JPanel();
				jp.add(jp_center2, BorderLayout.CENTER);
				jp_center2.setLayout(new BorderLayout());
				jp_center2.add(jp_title, BorderLayout.NORTH);
				jp_center2.add(jp_center, BorderLayout.CENTER);
				jp_center2.add(jp_south2, BorderLayout.SOUTH);

//	jb4.setPreferredSize(new Dimension(80, 40));
				jbName = new JButton("이름");
				jbMyInfo = new JButton("내 정보");
				jbLogOut = new JButton("로그아웃");
				mypage_bt = new JButton("마이페이지");
				information_bt = new JButton("개인 정보");
				update_bt = new JButton("회원 정보 수정");
				pw_update_bt = new JButton("비밀번호 변경");
				withdraw_bt = new JButton("회원탈퇴");

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
				jb5.setFont(new Font("Jalnan", Font.PLAIN, 12));
				jb6.setFont(new Font("Jalnan", Font.PLAIN, 12));
				mypage_bt.setFont(new Font("Jalnan", Font.BOLD, 20));
				information_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));
				update_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));
				pw_update_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));
				withdraw_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));
				tf_email.setFont(new Font("Jalnan", Font.PLAIN, 12));
				tf_birth.setFont(new Font("Jalnan", Font.PLAIN, 12));
				tf_phone.setFont(new Font("Jalnan", Font.PLAIN, 12));
				tf_name.setFont(new Font("Jalnan", Font.PLAIN, 12));

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
				mypage_bt.setOpaque(false);
				mypage_bt.setContentAreaFilled(false);
				mypage_bt.setBorderPainted(false);
				information_bt.setOpaque(false);
				information_bt.setContentAreaFilled(false);
				information_bt.setBorderPainted(false);
				update_bt.setOpaque(false);
				update_bt.setContentAreaFilled(false);
				update_bt.setBorderPainted(false);
				pw_update_bt.setOpaque(false);
				pw_update_bt.setContentAreaFilled(false);
				pw_update_bt.setBorderPainted(false);
				withdraw_bt.setOpaque(false);
				withdraw_bt.setContentAreaFilled(false);
				withdraw_bt.setBorderPainted(false);

//	색 바꾸기
				// jb1.setBackground(Color.decode("#98b4d4"));

				jp_headerMain.setBackground(Color.decode("#D4B8E8"));
				jp_headerSub.setBackground(Color.decode("#D4B8E8"));
				jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
				jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
				jp_buttons.setBackground(Color.decode("#D4B8E8"));
				jp_west.setBackground(Color.decode("#D4B8E8"));
				jp_west2.setBackground(Color.decode("#D4B8E8"));
				jp_east.setBackground(Color.decode("#D4B8E8"));
				jp_south.setBackground(Color.decode("#D4B8E8"));
				jp_center.setBackground(Color.WHITE);
				jp.setBackground(Color.decode("#eee3f6"));
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
				jp_test.add(jp_west2, BorderLayout.CENTER);
				jp_test.setBackground(Color.decode("#D4B8E8"));

				jp_west2.add(mypage_bt);
				jp_west2.add(information_bt);
				jp_west2.add(update_bt);
				jp_west2.add(pw_update_bt);
				jp_west2.add(withdraw_bt);
				jp.add(jp_test, BorderLayout.WEST);

//	ADD 

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
		}
	}

	public Login_My_Infomodify() {
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
					new Login_My_Infomodify();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}