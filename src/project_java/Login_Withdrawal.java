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
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Login_Withdrawal extends JFrame {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_center, jp_east, jp_west;
	JButton jbName, jbMyInfo, jbLogOut;
	Font customFont;
	JLabel jLabel1;
	JTextField tf_pw, tf_withdraw;
	Border newBorder;
	
	JPanel lb_jp, pw_jp, reason_jp, under_bt_jp;
	JButton withdraw_bt, cancel_bt;
	JLabel lb;

	public Login_Withdrawal() {
		super("회원 탈퇴");

//		민지
		{
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
			jp_east = new JPanel();
			jp_west = new JPanel();
			jp = new JPanel();
			jp_headerMain = new JPanel();
			jp_headerSub = new JPanel();
			jp_headerSubLeft = new JPanel();
			jp_headerSubRight = new JPanel();
			jp_center = new JPanel();
			jbName = new JButton("이름");
			jbMyInfo = new JButton("내 정보");
			jbLogOut = new JButton("로그아웃");
			newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);


//		font 입력
			jLabel1 = new JLabel(" PERSONAL PLANNER");
			jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));
			jbName.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jbMyInfo.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jbLogOut.setFont(new Font("Jalnan", Font.PLAIN, 12));


//		jbName, jbMyInfo, jbLogout 투명하게 하기
			jbName.setOpaque(false);
			jbName.setContentAreaFilled(false);
			jbName.setBorderPainted(false);
			jbMyInfo.setOpaque(false);
			jbMyInfo.setContentAreaFilled(false);
			jbMyInfo.setBorderPainted(false);
			jbLogOut.setOpaque(false);
			jbLogOut.setContentAreaFilled(false);
			jbLogOut.setBorderPainted(false);

//		색 바꾸기
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
			jp_west.setBackground(Color.decode("#D4B8E8"));
			jp_east.setBackground(Color.decode("#D4B8E8"));
			jp_center.setBackground(Color.WHITE);

//		Logo 
			ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back

//		레이아웃
			jp_headerSub.setLayout(new GridLayout(0, 2));
			jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
			jp_center.setBorder(new EmptyBorder(50, 340, 170, 340)); // 상, 좌, 하, 우 여백

// 		센터
//		비밀번호(NN), 탈퇴사유
			// 패널
			lb_jp = new JPanel();
			lb_jp.setBackground(Color.WHITE);
			pw_jp = new JPanel();
			pw_jp.setOpaque(false);
			reason_jp = new JPanel();
			reason_jp.setOpaque(false);
			under_bt_jp = new JPanel();
			under_bt_jp.setOpaque(false);
			
			// 붙일 패널 
			jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
			
			// 회원탈퇴 라벨
			lb = new JLabel("회원 탈퇴");
			lb.setFont(new Font("Jalnan",Font.PLAIN,26));
			lb_jp.add(lb);
			lb_jp.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
			
			
			// 아이콘 + 텍스트필드
			// 비밀번호
			ImageIcon pwIcon = new ImageIcon("src/icons/pw.png");
			Image pwImage = pwIcon.getImage();
			Image newpwImg = pwImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			pwIcon = new ImageIcon(newpwImg);

			tf_pw = new JTextField(25);
			tf_pw.setPreferredSize(new Dimension(50, 40));
			TextPrompt tp_pw = new TextPrompt("비밀번호 ", tf_pw);
			tp_pw.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_pw.setForeground(Color.gray);
			tp_pw.changeAlpha(0.5f);
			tp_pw.changeStyle(Font.BOLD + Font.ITALIC);           
			tf_pw.setBorder(newBorder);          
			tf_pw.setOpaque(false); 
			pw_jp.add(new JLabel(pwIcon));
			pw_jp.add(tf_pw);
			pw_jp.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
			
			// 탈퇴사유
			ImageIcon withdrawIcon = new ImageIcon("src/icons/withdraw.png");
			Image withdrawImage = withdrawIcon.getImage();
			Image newwithdrawImg = withdrawImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			withdrawIcon = new ImageIcon(newwithdrawImg);
			jp.setBackground(Color.decode("#D4B8E8"));

			tf_withdraw = new JTextField(25);
			tf_withdraw.setPreferredSize(new Dimension(50, 40));
			TextPrompt tp_withdraw = new TextPrompt("탈퇴사유 ", tf_withdraw);
			tp_withdraw.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_withdraw.setForeground(Color.gray);
			tp_withdraw.changeAlpha(0.5f);
			tp_withdraw.changeStyle(Font.BOLD + Font.ITALIC);           
			tf_withdraw.setBorder(newBorder);          
			tf_withdraw.setOpaque(false); 
			reason_jp.add(new JLabel(withdrawIcon));
			reason_jp.add(tf_withdraw);
			reason_jp.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
			
// 			회원탈퇴 버튼
			withdraw_bt = new JButton("회원탈퇴");
			withdraw_bt.setPreferredSize(new Dimension(150, 40));
			withdraw_bt.setBackground(Color.decode("#D4B8E8"));
			withdraw_bt.setFont(new Font("Jalnan", Font.PLAIN, 15));
			withdraw_bt.setForeground(Color.WHITE);
			under_bt_jp.add(withdraw_bt);
			under_bt_jp.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
			
//			취소하기 버튼
			cancel_bt = new JButton("취소");
			cancel_bt.setPreferredSize(new Dimension(150, 40));
			cancel_bt.setBackground(Color.decode("#D4B8E8"));
			cancel_bt.setFont(new Font("Jalnan", Font.PLAIN, 15));
			cancel_bt.setForeground(Color.WHITE);
			under_bt_jp.add(cancel_bt);
			under_bt_jp.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
			
//			jp_center에 ADD
			jp_center.add(lb_jp);
			jp_center.add(pw_jp);
			jp_center.add(reason_jp);
			jp_center.add(under_bt_jp);
			
//			ADD(상단)
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
			jp.add(jp_center);

			add(jp_east,BorderLayout.EAST);
			add(jp, BorderLayout.CENTER);
			add(jp_west,BorderLayout.WEST);
		
			getContentPane().add(jp_headerMain, BorderLayout.NORTH);
			getContentPane().add(jp, BorderLayout.CENTER);

			setSize(1000, 700);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
	}

	public static void main(String[] args) {
		try {
			// Select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// Start the application
//	                    BaseSampleFrame app = new BaseSampleFrame("BaseSampleFrame");
//	                    app.setSize(800, 600);
//	                    app.setLocationRelativeTo(null);
//	                    app.setVisible(true);
					new Login_Withdrawal();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
