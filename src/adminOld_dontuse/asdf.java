package adminOld_dontuse;

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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Server.Protocol;
import Server.VO;
import UserDB.DAO;
import project_java.Main;
import project_java.TextPrompt;

public class asdf extends JPanel implements ActionListener, Runnable {

	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton join_bt, cancel_bt;
	JLabel jLabel1;
	Font customFont;
	JTextField tf_id, tf_mail, tf_name, tf_birth, tf_phone;
	JPasswordField jpf_pw, jpf_pwchk;
	JTextArea ta_TermsofUse;
	JCheckBox cb_TermsofUse;
	JScrollPane jsp;
	Border newBorder;
	Main main;
	String pass1, pass2;
	JPanel lb_jp, jp_center, id_jp, pw_jp, pwcheck_jp, mail_jp, name_jp, birth_jp, phone_jp, Termsofuse_jp, under_bt_jp,
			terms_jp;
	JLabel lb;
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;
	int result;
	boolean idCheck;

	public asdf(Main main) {
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
			tp_user.changeStyle(Font.BOLD);
			tf_id.setBorder(newBorder);
			tf_id.setOpaque(false);
			id_jp.add(new JLabel(userIcon));
			id_jp.add(tf_id);

			// 비밀번호(NN) / 아이콘 + 텍스트필드
			ImageIcon pwIcon = new ImageIcon("src/icons/pw.png");
			Image pwImage = pwIcon.getImage();
			Image newpwImg = pwImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			pwIcon = new ImageIcon(newpwImg);

			jpf_pw = new JPasswordField(23);
			jpf_pw.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_pw = new TextPrompt("비밀번호(18자리 이내) ", jpf_pw);
			tp_pw.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_pw.setForeground(Color.gray);
			tp_pw.changeAlpha(0.5f);
			tp_pw.changeStyle(Font.BOLD);
			jpf_pw.setBorder(newBorder);
			jpf_pw.setOpaque(false);
			pw_jp.add(new JLabel(pwIcon));
			pw_jp.add(jpf_pw);

			// 비밀번호확인(NN) / 아이콘 + 텍스트필드
			jpf_pwchk = new JPasswordField(23);
			jpf_pwchk.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_pw2 = new TextPrompt("비밀번호 확인(18자리 이내) ", jpf_pwchk);
			tp_pw2.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_pw2.setForeground(Color.gray);
			tp_pw2.changeAlpha(0.5f);
			tp_pw2.changeStyle(Font.BOLD);
			jpf_pwchk.setBorder(newBorder);
			jpf_pwchk.setOpaque(false);
			pwcheck_jp.add(new JLabel(pwIcon));
			pwcheck_jp.add(jpf_pwchk);

			// 메일주소(NN) / 아이콘 + 텍스트필드
			ImageIcon mailIcon = new ImageIcon("src/icons/mail.png");
			Image mailImage = mailIcon.getImage();
			Image newmailImg = mailImage.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
			mailIcon = new ImageIcon(newmailImg);
			tf_mail = new JTextField(23);
			tf_mail.setPreferredSize(new Dimension(50, 30));
			TextPrompt tp_mail = new TextPrompt("비밀번호 분실 시 확인용 이메일(xxx@xxx.com)", tf_mail);
			tp_mail.setFont(new Font("Jalnan", Font.PLAIN, 10));
			tp_mail.setForeground(Color.gray);
			tp_mail.changeAlpha(0.5f);
			tp_mail.changeStyle(Font.BOLD);
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
			tp_name.changeStyle(Font.BOLD);
			tf_name.setBorder(newBorder);
			tf_name.setOpaque(false);
			name_jp.add(new JLabel(userIcon));
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
			tp_birth.changeStyle(Font.BOLD);
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
			tp_phone.changeStyle(Font.BOLD);
			tf_phone.setBorder(newBorder);
			tf_phone.setOpaque(false);
			phone_jp.add(new JLabel(phoneIcon));
			phone_jp.add(tf_phone);

//			 약관
			ta_TermsofUse = new JTextArea(3, 10);
			jsp = new JScrollPane(ta_TermsofUse, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMinimum());
			jsp.setPreferredSize(new Dimension(50, 30));
			ta_TermsofUse.setLineWrap(true);
			ta_TermsofUse.setEditable(false);
			ta_TermsofUse.setFont(new Font("Jalnan", Font.PLAIN, 10));
			ta_TermsofUse.setBackground(Color.WHITE);
			ta_TermsofUse.setForeground(Color.BLACK);
			ta_TermsofUse.setText(
					"개인정보 약관\n개인정보보호법에 따라 퍼스널플래너에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.\r\n"
							+ "\r\n" + "1. 수집하는 개인정보\r\n"
							+ "이용자가 여행일정 만들기 등 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 퍼스널플래너는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.\r\n"
							+ "\r\n" + "회원가입 시점에 퍼스널플래너가 이용자로부터 수집하는 개인정보는 아래와 같습니다.\r\n"
							+ "- 회원 가입 시 필수항목으로 아이디, 비밀번호, 이름, 생년월일, 휴대전화번호, 본인확인 이메일주소를 수집합니다. 동일인 식별정보(CI), 중복가입 확인정보(DI) 함께 수집합니다.\r\n"
							+ "서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.\r\n"
							+ "- 회원정보 또는 개별 서비스에서 프로필 정보를 설정할 수 있습니다.\r\n"
							+ "- 퍼스널플래너 내의 개별 서비스 이용시 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.\r\n"
							+ "\r\n" + "서비스 이용 과정에서 서비스 이용 기록이 생성되어 수집될 수 있습니다.\r\n"
							+ "구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나, 2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다.\r\n"
							+ "이와 같이 수집된 정보는 개인정보와의 연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.\r\n"
							+ "2. 수집한 개인정보의 이용\r\n"
							+ "퍼스널플래너의 회원관리, 서비스 개발・제공 및 향상, 안전한 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.\r\n" + "\r\n"
							+ "- 회원 가입 의사의 확인, 연령 확인 및 이용자의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.\r\n"
							+ "- 법령 및 퍼스널플래너 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.\r\n"
							+ "- 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.\r\n"
							+ "3. 개인정보의 보관기간\r\n" + "퍼스널플래너는 원칙적으로 이용자의 개인정보를 회원 탈퇴 7일 후 파기하고 있습니다.\r\n"
							+ "단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다.\r\n"
							+ "이용자에게 개인정보 보관기간에 대해 회원가입 시 또는 서비스 가입 시 동의를 얻은 경우는 아래와 같습니다.\r\n" + "- 부정 가입 및 이용 방지\r\n"
							+ "부정 이용자의 정보 : 탈퇴일로부터 7일 보관\r\n"
							+ "탈퇴한 이용자의 휴대전화번호(복호화가 불가능한 일방향 암호화(해시처리)) : 탈퇴일로부터 7일 보관\r\n"
							+ "전자상거래 등에서의 소비자 보호에 관한 법률, 전자문서 및 전자거래 기본법, 통신비밀보호법 등 법령에서 일정기간 정보의 보관을 규정하는 경우는 아래와 같습니다. 네이버는 이 기간 동안 법령의 규정에 따라 개인정보를 보관하며, 본 정보를 다른 목적으로는 절대 이용하지 않습니다.\r\n"
							+ "- 전자상거래 등에서 소비자 보호에 관한 법률\r\n" + "\r\n" + "4. 개인정보 수집 및 이용 동의를 거부할 권리\r\n"
							+ "이용자는 개인정보의 수집 및 이용 동의를 거부할 권리가 있습니다. 회원가입 시 수집하는 최소한의 개인정보, 즉, 필수 항목에 대한 수집 및 이용 동의를 거부하실 경우, 회원가입이 어려울 수 있습니다.");

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
		// 접속
		connected();

	}

	public asdf() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton) e.getSource();
		pass1 = new String(jpf_pw.getPassword());
		pass2 = new String(jpf_pwchk.getPassword());
		if (obj == cancel_bt) {
			if (tf_id.getText().length() > 0 || new String(jpf_pw.getPassword()).length() > 0
					|| new String(jpf_pwchk.getPassword()).length() > 0 || tf_mail.getText().length() > 0
					|| tf_name.getText().length() > 0 || tf_birth.getText().length() > 0
					|| tf_phone.getText().length() > 0) {
				int result = JOptionPane.showConfirmDialog(null, "회원가입을 취소하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				// 예 클릭시, 내용 초기화하고 이동시켜주고 아니오 클릭시, 이동취소
				if (result == JOptionPane.YES_OPTION) {
					tf_id.setText("");
					jpf_pw.setText("");
					jpf_pwchk.setText("");
					tf_mail.setText("");
					tf_name.setText("");
					tf_birth.setText("");
					tf_phone.setText("");
					cb_TermsofUse.setSelected(false);
					main.cardLayout.show(main.cardJPanel, "login_Main");
				}
			} else {
				main.cardLayout.show(main.cardJPanel, "login_Main");
			}
		} else if (obj == join_bt) { // 가입하기 버튼
			// 누락된 정보 있을시
			if (tf_id.getText().equals("")) { // 아이디 공란일경우
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_id.requestFocus();// 커서위치 조절
			} else if (tf_id.getText().length() > 15) { // 15자리 이상일경우
				JOptionPane.showMessageDialog(null, "아이디를 15자리 이내로 입력해주세요", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_id.requestFocus();// 커서위치 조절
			} else if (new String(jpf_pw.getPassword()).length() > 18) {
				JOptionPane.showMessageDialog(null, "비밀번호를 18자리 이내로 입력해주세요", "Confirm", JOptionPane.ERROR_MESSAGE);
				jpf_pw.requestFocus();
			} else if (new String(jpf_pw.getPassword()).equals("")) { // 비밀번호 공란일경우
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				jpf_pw.requestFocus();
			} else if (new String(jpf_pwchk.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				jpf_pwchk.requestFocus();
			} else if (pass1.length() != pass2.length()) { // 비밀번호 일치하지 않을때
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
				jpf_pw.requestFocus();
			} else if (!pass1.equals(pass2)) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
				jpf_pw.requestFocus();
			} else if (tf_mail.getText().equals("")) { // 이메일 공란일경우
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_mail.requestFocus();
			} else if (!isValidEmail(tf_mail.getText())) { // 이메일 형식이 올바르지 않을 경우
				JOptionPane.showMessageDialog(null, "올바른 이메일 형식으로 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_mail.requestFocus();
			} else if (tf_name.getText().equals("")) { // 이름 공란일경우
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_name.requestFocus();
			} else if (tf_name.getText().length() > 10) {
				JOptionPane.showMessageDialog(null, "이름을 10자리 이내로 입력해주세요", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_name.requestFocus();
			} else if (tf_birth.getText().equals("")) { // 생년월일 공란일경우
				JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_birth.requestFocus();
			} else if (tf_birth.getText().length() != 8) {
				JOptionPane.showMessageDialog(null, "생년월일은 8글자로 입력해주세요\n예)20230101", "Confirm",
						JOptionPane.ERROR_MESSAGE);
				tf_birth.requestFocus();
			} else if (tf_phone.getText().equals("")) { // 휴대전화 공란일경우
				JOptionPane.showMessageDialog(null, "휴대전화 번호를 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_phone.requestFocus();
			} else if (tf_phone.getText().contains("-")) {
				JOptionPane.showMessageDialog(null, "특수기호 '-'를 제외한 번호를 입력해주세요!", "Confirm", JOptionPane.ERROR_MESSAGE);
				tf_phone.requestFocus();
			} else if (!cb_TermsofUse.isSelected()) { // 약관동의 체크박스
				JOptionPane.showMessageDialog(this, "약관에 동의해야 회원가입이 가능합니다.", "약관 동의 필요", JOptionPane.WARNING_MESSAGE);
				cb_TermsofUse.requestFocus();
			} else {
				VO vo = new VO();
				vo.setM_ID(tf_id.getText());
				vo.setM_PW(pass1);
				vo.setM_EMAIL(tf_mail.getText());
				vo.setM_NAME(tf_name.getText());
				vo.setM_BIRTH(tf_birth.getText());
				vo.setM_PHONE(tf_phone.getText());
				vo.setM_TERMS("동의");
				vo.setM_CLASS("1");
				try {
					Protocol p = new Protocol();
					p.setVo(vo);
					p.setCmd(1); // 아이디 중복체크
					out.writeObject(p);
					out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (idCheck) {
						Protocol p = new Protocol();
						p.setCmd(2); // 회원가입
						out.writeObject(p);
						out.flush();
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", " Confirm",
								JOptionPane.INFORMATION_MESSAGE);
						tf_id.setText("");
						jpf_pw.setText("");
						jpf_pwchk.setText("");
						tf_mail.setText("");
						tf_name.setText("");
						tf_birth.setText("");
						tf_phone.setText("");
						cb_TermsofUse.setSelected(false);
						main.cardLayout.show(main.cardJPanel, "login_Main");
					} else {
						JOptionPane.showMessageDialog(null, "같은 아이디가 존재합니다.", " Confirm",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e2) {
				}
			}
		}
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(emailRegex);
	}

	// 접속
	public void connected() {
		try {
			s = new Socket("192.168.0.44", 7780);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 활동내용
	@Override
	public void run() {
		esc: while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					Protocol p = (Protocol) obj;
					VO vo = p.getVo();
					switch (p.getCmd()) {
					case 0:
						break esc;
					case 1: // 아이디 중복확인
						idCheck = DAO.getIdCheck(tf_id.getText());
						break;
					case 2: // 회원가입
						result = DAO.getInsert(vo);
						if (result == 1) {
							System.out.println("'" + p.getVo().getM_ID() + "' 계정 생성 완료");
						}
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("회원가입 오류발생: " + e);
			}
		}
		closed();
	}

	// 끝내기
	public void closed() {
		try {
			out.close();
			in.close();
			s.close();
			System.exit(0);
		} catch (Exception e) {
		}
	}

}