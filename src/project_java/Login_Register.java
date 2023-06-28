package project_java;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Login_Register extends JPanel implements ActionListener {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton join_bt, cancel_bt;
	JLabel jLabel1;
	Font customFont;
	JTextField tf_id, tf_pw, tf_pw2, tf_mail, tf_name, tf_birth, tf_phone;
	JTextArea ta_TermsofUse;
	JCheckBox cb_TermsofUse;
	JScrollPane jsp;
	Border newBorder;
	Main main;

	JPanel lb_jp, jp_center, id_jp, pw_jp, pwcheck_jp, mail_jp, name_jp, birth_jp, phone_jp, Termsofuse_jp, under_bt_jp,
			terms_jp;
	JLabel lb;

	public Login_Register(Main main) {
		this.main = main;
//		FONT
// 		민지
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
			jp = new JPanel();
			jp_headerMain = new JPanel();
			jp_headerSub = new JPanel();
			jp_headerSubLeft = new JPanel();
			jp_headerSubRight = new JPanel();
			jp_center = new JPanel();
			newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);

//		font 입력
			jLabel1 = new JLabel(" PERSONAL PLANNER");
			jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));

//		색 바꾸기
			// jb1.setBackground(Color.decode("#98b4d4"));
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
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
			jp_center.setBorder(new EmptyBorder(0, 0, 0, 0)); // 상, 좌, 하, 우 여백
			jp.setLayout(new BorderLayout());

// 		센터 상단 텍스트필드
			// 패널
			lb_jp = new JPanel();
			lb_jp.setBackground(Color.WHITE);
			id_jp = new JPanel();
			id_jp.setOpaque(false);
			pw_jp = new JPanel();
			pw_jp.setOpaque(false);
			pwcheck_jp = new JPanel();
			pwcheck_jp.setOpaque(false);
			mail_jp = new JPanel();
			mail_jp.setOpaque(false);
			name_jp = new JPanel();
			name_jp.setOpaque(false);
			birth_jp = new JPanel();
			birth_jp.setOpaque(false);
			phone_jp = new JPanel();
			phone_jp.setOpaque(false);
			Termsofuse_jp = new JPanel();
			Termsofuse_jp.setOpaque(false);
			under_bt_jp = new JPanel();
			under_bt_jp.setOpaque(false);

			// 붙일 패널
			jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));

			// 회원가입 라벨
			lb = new JLabel("회원 가입");
			lb.setFont(new Font("Jalnan", Font.PLAIN, 26));
			lb_jp.add(lb);
			lb_jp.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

			// 아이디(NN) / 아이콘 + 텍스트필드
			ImageIcon userIcon = new ImageIcon("src/icons/user.png");
			Image idImage = userIcon.getImage();
			Image newIdImg = idImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			userIcon = new ImageIcon(newIdImg);

			tf_id = new JTextField(23);
			tf_id.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_user = new TextPrompt("아이디 ", tf_id); // <외부 클래스>
			tp_user.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_user.setForeground(Color.gray);
			tp_user.changeAlpha(0.5f); // <<여기까지 JTF + 꾸미기>>
			tp_user.changeStyle(Font.BOLD + Font.ITALIC);
			tf_id.setBorder(newBorder);
			tf_id.setOpaque(false);
			id_jp.add(new JLabel(userIcon));
			id_jp.add(tf_id);

			// 비밀번호(NN) / 아이콘 + 텍스트필드
			ImageIcon pwIcon = new ImageIcon("src/icons/pw.png");
			Image pwImage = pwIcon.getImage();
			Image newpwImg = pwImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			pwIcon = new ImageIcon(newpwImg);

			tf_pw = new JTextField(23);
			tf_pw.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_pw = new TextPrompt("비밀번호 ", tf_pw);
			tp_pw.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_pw.setForeground(Color.gray);
			tp_pw.changeAlpha(0.5f);
			tp_pw.changeStyle(Font.BOLD + Font.ITALIC);
			tf_pw.setBorder(newBorder);
			tf_pw.setOpaque(false);
			pw_jp.add(new JLabel(pwIcon));
			pw_jp.add(tf_pw);

			// 비밀번호확인(NN) / 아이콘 + 텍스트필드
			tf_pw2 = new JTextField(23);
			tf_pw2.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_pw2 = new TextPrompt("비밀번호 확인", tf_pw2);
			tp_pw2.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_pw2.setForeground(Color.gray);
			tp_pw2.changeAlpha(0.5f);
			tp_pw2.changeStyle(Font.BOLD + Font.ITALIC);
			tf_pw2.setBorder(newBorder);
			tf_pw2.setOpaque(false);
			pwcheck_jp.add(new JLabel(pwIcon));
			pwcheck_jp.add(tf_pw2);

			// 메일주소 / 아이콘 + 텍스트필드
			ImageIcon mailIcon = new ImageIcon("src/icons/mail.png");
			Image mailImage = mailIcon.getImage();
			Image newmailImg = mailImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			mailIcon = new ImageIcon(newmailImg);
			tf_mail = new JTextField(23);
			tf_mail.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_mail = new TextPrompt("비밀번호 분실 시 확인용 이메일[선택] ", tf_mail);
			tp_mail.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_mail.setForeground(Color.gray);
			tp_mail.changeAlpha(0.5f);
			tp_mail.changeStyle(Font.BOLD + Font.ITALIC);
			tf_mail.setBorder(newBorder);
			tf_mail.setOpaque(false);
			mail_jp.add(new JLabel(mailIcon));
			mail_jp.add(tf_mail);

// 		센터 하단 텍스트필드
//		이름(NN), 생년월일 8자리(NN), 휴대전화번호(NN), 약관내용, 개인정보 조회 및 수집 이용 제공동의(NN)
			// 이름(NN) / 아이콘 + 텍스트필드
			tf_name = new JTextField(23);
			tf_name.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_name = new TextPrompt("이름 ", tf_name); // <외부 클래스>
			tp_name.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_name.setForeground(Color.gray);
			tp_name.changeAlpha(0.5f);
			tp_name.changeStyle(Font.BOLD + Font.ITALIC);
			tf_name.setBorder(newBorder);
			tf_name.setOpaque(false);
			name_jp.add(new JLabel(pwIcon));
			name_jp.add(tf_name);

			// 생년월일 8자리(NN) / 아이콘 + 텍스트필드
			ImageIcon calendarIcon = new ImageIcon("src/icons/calendar.png");
			Image calendarImage = calendarIcon.getImage();
			Image newcalendarImg = calendarImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			calendarIcon = new ImageIcon(newcalendarImg);
			tf_birth = new JTextField(23);
			tf_birth.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_birth = new TextPrompt("생년월일(8자리) ", tf_birth);
			tp_birth.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_birth.setForeground(Color.gray);
			tp_birth.changeAlpha(0.5f);
			tp_birth.changeStyle(Font.BOLD + Font.ITALIC);
			tf_birth.setBorder(newBorder);
			tf_birth.setOpaque(false);
			birth_jp.add(new JLabel(calendarIcon));
			birth_jp.add(tf_birth);

			// 휴대전화번호(NN) / 아이콘 + 텍스트필드
			ImageIcon phoneIcon = new ImageIcon("src/icons/phone.png");
			Image phoneImage = phoneIcon.getImage();
			Image newphoneImg = phoneImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			phoneIcon = new ImageIcon(newphoneImg);
			tf_phone = new JTextField(23);
			tf_phone.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_phone = new TextPrompt("휴대전화번호 ", tf_phone);
			tp_phone.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_phone.setForeground(Color.gray);
			tp_phone.changeAlpha(0.5f);
			tp_phone.changeStyle(Font.BOLD + Font.ITALIC);
			tf_phone.setBorder(newBorder);
			tf_phone.setOpaque(false);
			phone_jp.add(new JLabel(phoneIcon));
			phone_jp.add(tf_phone);

//			 약관
			ta_TermsofUse = new JTextArea(3, 10);
			jsp = new JScrollPane(ta_TermsofUse, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.setPreferredSize(new Dimension(50, 30));
			ta_TermsofUse.setLineWrap(true);
			ta_TermsofUse.setEditable(false);
			ta_TermsofUse.setFont(new Font("Jalnan", Font.PLAIN, 10));
			ta_TermsofUse.setBackground(Color.WHITE);
			ta_TermsofUse.setForeground(Color.BLACK);
			ta_TermsofUse.setText("개인정보 약관\n약관내용~~~~~~~");

//			약관 체크박스(NN)
			cb_TermsofUse = new JCheckBox("개인정보조회 및 수집, 이용, 제공 동의");
			cb_TermsofUse.setBackground(new Color(255, 255, 255));
			terms_jp = new JPanel(new FlowLayout());
			terms_jp.setBackground(Color.WHITE);
			terms_jp.add(cb_TermsofUse);
//			cb_TermsofUse.setBounds(0, 0, 0, 0);
			Termsofuse_jp.setLayout(new BorderLayout());
			Termsofuse_jp.setBackground(Color.WHITE);
			Termsofuse_jp.add(jsp, BorderLayout.CENTER);
			Termsofuse_jp.add(terms_jp, BorderLayout.SOUTH);

//     		가입하기버튼
			join_bt = new JButton("가입하기");
			join_bt.setPreferredSize(new Dimension(150, 40));
			join_bt.setBackground(Color.decode("#D4B8E8"));
			join_bt.setFont(new Font("Jalnan", Font.PLAIN, 15));
			join_bt.setForeground(Color.WHITE);
			under_bt_jp.add(join_bt);
			under_bt_jp.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

//			취소하기 버튼
			cancel_bt = new JButton("취소");
			cancel_bt.setPreferredSize(new Dimension(150, 40));
			cancel_bt.setBackground(Color.decode("#D4B8E8"));
			cancel_bt.setFont(new Font("Jalnan", Font.PLAIN, 15));
			cancel_bt.setForeground(Color.WHITE);
			under_bt_jp.add(cancel_bt);
			under_bt_jp.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

//			jp_center에 ADD
			jp_center.add(lb_jp);
			jp_center.add(id_jp);
			jp_center.add(pw_jp);
			jp_center.add(pwcheck_jp);
			jp_center.add(mail_jp);
			jp_center.add(name_jp);
			jp_center.add(birth_jp);
			jp_center.add(phone_jp);
			jp_center.add(Termsofuse_jp);
			jp_center.add(under_bt_jp);

//			ADD 
			jp_headerSubLeft.add(new JLabel(imageIcon));
			jp_headerSubLeft.add(jLabel1);
			jp_headerSub.add(jp_headerSubLeft);
			jp_headerSub.add(jp_headerSubRight);
			jp_headerMain.add(jp_headerSub);
			jp.add(jp_center);

			setLayout(new BorderLayout());
			add(jp_headerMain, BorderLayout.NORTH);
			add(jp, BorderLayout.CENTER);
		}
		join_bt.addActionListener(this);
		cancel_bt.addActionListener(this);

	}

	public Login_Register() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton) e.getSource();
		if (obj == join_bt) { // 가입하기 버튼
			int ok = 0;
			if(ok ==1) { // 정상적으로 입력시
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", " Confirm", JOptionPane.INFORMATION_MESSAGE);
				main.cardLayout.show(main.cardJPanel, "login_Main");
			} else if(ok==0) { // 누락된 정보 있을시

				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요!", "Confirm",JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == cancel_bt) {
			main.cardLayout.show(main.cardJPanel, "login_Main");
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
					new Login_Register();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}