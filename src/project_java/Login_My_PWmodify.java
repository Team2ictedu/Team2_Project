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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DB_User.UserDAO;
import DB_User.UserVO;
import Server.Protocol;

public class Login_My_PWmodify extends JPanel implements ActionListener {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south,
			jp_center, jp_pw, jp_south2, jp_west2;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4, ok_bt, cancel_bt, mypage_bt, update_bt, pw_update_bt,
			withdraw_bt;
	Font customFont;
	JLabel jLabel1, jl_pw, jl_newPw1, jl_newPw2;
	JTextArea jta;
	JScrollPane jsp;
	JTextField tf_phone, tf_pwchk, tf_birth;
	JPasswordField jpf_pw, jpf_newPw1, jpf_newPw2;
	Main main;

	public Login_My_PWmodify(Main main) {

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

				jp_pw = new JPanel(new GridLayout(0, 2));

				jb1 = new JButton("새 일정 만들기");
				jb1.setPreferredSize(new Dimension(120, 30));
				jb2 = new JButton("내 일정 조회");
				jb2.setPreferredSize(new Dimension(120, 30));
				jb3 = new JButton("여행 후기");
				jb3.setPreferredSize(new Dimension(120, 30));
				jb4 = new JButton("마이페이지");
				jb4.setPreferredSize(new Dimension(120, 30));

				ok_bt = new JButton("확 인");
				cancel_bt = new JButton("취 소");

				jpf_pw = new JPasswordField(20);
				jpf_newPw1 = new JPasswordField(20);
				jpf_newPw2 = new JPasswordField(20);
				jl_pw = new JLabel("현재 비밀번호 : ", JLabel.CENTER);
				jl_newPw1 = new JLabel("새 비밀번호 : ", JLabel.CENTER);
				jl_newPw2 = new JLabel("새 비밀번호 확인 : ", JLabel.CENTER);

				jp_pw.setBackground(Color.WHITE);
//회원정보수정			
				jp.setLayout(new BorderLayout());
				JPanel jp_title = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel jl_my_info = new JLabel("비밀번호 변경");
				jp_title.add(jl_my_info, BorderLayout.NORTH);
				jp_title.setBackground(Color.decode("#B19CCB"));
				jl_my_info.setFont(new Font("Jalnan", Font.PLAIN, 12));
				jl_my_info.setForeground(Color.WHITE);
				jp_title.setPreferredSize(new Dimension(70, 30));

				jp_pw.add(jl_pw);
				jl_pw.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_pw.add(jpf_pw);

				jp_pw.add(jl_newPw1);
				jl_newPw1.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_pw.add(jpf_newPw1);

				jp_pw.add(jl_newPw2);
				jl_newPw2.setFont(new Font("Jalnan", Font.BOLD, 15));
				jp_pw.add(jpf_newPw2);

				jp_center.add(jp_pw);

				jp_south2.add(ok_bt);
				jp_south2.add(cancel_bt);
				jp_south2.setBackground(Color.WHITE);

				jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
				// jp_center.setLayout(new GridLayout(5,0));
				jp_center.setBorder(new EmptyBorder(200, 10, 200, 100));
				JPanel jp_center2 = new JPanel();
				jp.add(jp_center2, BorderLayout.CENTER);
				jp_center2.setLayout(new BorderLayout());
				jp_center2.add(jp_title, BorderLayout.NORTH);
				jp_center2.add(jp_center, BorderLayout.CENTER);
				jp_center2.add(jp_south2, BorderLayout.SOUTH);

//	jb4.setPreferredSize(new Dimension(80, 40));
				jbName = new JButton(main.p.getVo().getM_NAME() + "님");
				jbMyInfo = new JButton("내 정보");
				jbLogOut = new JButton("로그아웃");
				mypage_bt = new JButton("마이페이지");
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
				ok_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
				cancel_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
				mypage_bt.setFont(new Font("Jalnan", Font.BOLD, 20));
				update_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));
				pw_update_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));
				withdraw_bt.setFont(new Font("Jalnan", Font.PLAIN, 10));

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

				jb1.addActionListener(this);
				jb2.addActionListener(this);
				jb3.addActionListener(this);
				jb4.addActionListener(this);
				ok_bt.addActionListener(this);
				cancel_bt.addActionListener(this);
				jbMyInfo.addActionListener(this);
				jbLogOut.addActionListener(this);
				pw_update_bt.addActionListener(this);
				update_bt.addActionListener(this);
				withdraw_bt.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton) e.getSource();
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
		} else if (obj == pw_update_bt) {
			main.cardLayout.show(main.cardJPanel, "login_My_PWmodify");
		} else if (obj == update_bt) {
			main.cardLayout.show(main.cardJPanel, "login_My_Infomodify");
		} else if (obj == withdraw_bt) {
			main.cardLayout.show(main.cardJPanel, "login_Withdrawal");
		} else if (obj == ok_bt) {
			String pass = new String(jpf_pw.getPassword());
			String newPass1 = new String(jpf_newPw1.getPassword());
			String newPass2 = new String(jpf_newPw2.getPassword());
			if (pass.length() == 0) {
				JOptionPane.showMessageDialog(null, "현재 비밀번호를 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jpf_pw.requestFocus();
			} else if (pass.equals(main.uservo.getM_PW())) {
				if (newPass1.length() == 0) {
					JOptionPane.showMessageDialog(null, "새 비밀번호를 입력해주세요", "Confirm", JOptionPane.ERROR_MESSAGE);
					jpf_newPw1.requestFocus();
				} else if (newPass2.length() == 0) {
					JOptionPane.showMessageDialog(null, "새 비밀번호 확인을 입력해주세요", "Confirm", JOptionPane.ERROR_MESSAGE);
					jpf_newPw2.requestFocus();
				} else if (!newPass1.equals(newPass2)) {
					JOptionPane.showMessageDialog(null, "새 비밀번호가 일치하지 않습니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
				} else {
						try {
							Protocol p = new Protocol();
							UserVO vo = new UserVO();
							vo.setM_ID(main.uservo.getM_ID());
							vo.setM_PW(newPass1);
							p.setVo(vo);
							p.setCmd(202);
							main.out.writeObject(p);
							main.out.flush();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
			} else {
				JOptionPane.showMessageDialog(null, "현재 비밀번호가 틀렸습니다.", "Confirm", JOptionPane.ERROR_MESSAGE);	
				jpf_pw.requestFocus();
			}
		} else if (obj == cancel_bt) {
			if (jpf_pw.getPassword().length > 0 || jpf_newPw1.getPassword().length > 0
					|| jpf_newPw2.getPassword().length > 0) {
				int result = JOptionPane.showConfirmDialog(null, "비밀번호 변경을 취소하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					main.cardLayout.show(main.cardJPanel, "planner_Select");
					jpf_pw.setText("");
					jpf_newPw1.setText("");
					jpf_newPw2.setText("");
				}
			} else {
				main.cardLayout.show(main.cardJPanel, "login_My_PWmodify");
			}
		}
	}

	public Login_My_PWmodify() {
		// TODO Auto-generated constructor stub
	}
}