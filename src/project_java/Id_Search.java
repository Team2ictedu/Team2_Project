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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class Id_Search extends JPanel implements ActionListener {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1, lb;
	TextPrompt tp1, tp2;
	Border newBorder;
	Main main;
	JPanel im_jp, log_im, lb_jp, name_jp, pw_jp, logMv_jp, btBt_jp, add_jp;
	JTextField jtf_name, jtf_em;
	JButton logMv_bt, join_bt, pwFin_bt, idCk_bt;

	public Id_Search(Main main) {
		this.main = main;
//		FONT
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
			jp_east = new JPanel();
			jp_west = new JPanel();
			jp_south = new JPanel();
			newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
		}

//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));

//		색 바꾸기
		{
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
			jp_west.setBackground(Color.decode("#D4B8E8"));
			jp_east.setBackground(Color.decode("#D4B8E8"));
			jp_south.setBackground(Color.decode("#D4B8E8"));
			jp.setBackground(Color.decode("#FFFFFF"));

		}

//		레이아웃
		{
			jp_headerSub.setLayout(new GridLayout(0, 2));
			jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
		}

//		add image 
		ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
		ImageIcon imagelogo = new ImageIcon("src/images/text_logo.png");
		Image image = imageIcon.getImage(); // transform it
		Image image2 = imagelogo.getImage();
		Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
		Image newimg2 = image2.getScaledInstance(300, 200, java.awt.Image.SCALE_DEFAULT);
		imageIcon = new ImageIcon(newimg); // transform it back
		imagelogo = new ImageIcon(newimg2);

		// 신실 작업툴
		{ // panel선언
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

			// 버튼 투명도
			join_bt.setOpaque(false);
			join_bt.setContentAreaFilled(false);
			join_bt.setBorderPainted(false);
			pwFin_bt.setOpaque(false);
			pwFin_bt.setContentAreaFilled(false);
			pwFin_bt.setBorderPainted(false);

			// 색상
			logMv_bt.setBackground(Color.decode("#D4B8E8"));

			// font
			logMv_bt.setFont(new Font("Jalnan", Font.PLAIN, 16));
			join_bt.setFont(new Font("Jalnan", Font.PLAIN, 16));
			pwFin_bt.setFont(new Font("Jalnan", Font.PLAIN, 16));
			idCk_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
			join_bt.setForeground(Color.GRAY);
			pwFin_bt.setForeground(Color.GRAY);
			logMv_bt.setForeground(Color.white);
			idCk_bt.setForeground(Color.WHITE);

			// 모든 panel들 합칠 panel의 레이아웃 box로 설정
			add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));

			// jp1 / 이미지
			log_im = new JPanel();
			log_im.add(new JLabel(imagelogo));
			log_im.setOpaque(false);
			im_jp.add(log_im);

			// jp2 / 로그인label
			lb = new JLabel("아이디 찾기");
			lb.setFont(new Font("Jalnan", Font.PLAIN, 26));
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
			name_jp.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

			textprompt = new TextPrompt("이메일", jtf_em);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.5f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);
			jtf_em.setBorder(newBorder);
			jtf_em.setOpaque(false);
			idCk_bt.setPreferredSize(new Dimension(75, 25));
			pw_jp.add(jtf_em);
			pw_jp.add(idCk_bt);
			// 위치조정
			pw_jp.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

			// jp5 로그인이동 버튼
			logMv_jp.add(logMv_bt);
			// 로그인버튼 크기지정
			logMv_bt.setPreferredSize(new Dimension(150, 40));
			// 위치조정
			logMv_jp.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

			// 하단 jp7 버튼 패널에 붙이기
			btBt_jp.add(join_bt);
			btBt_jp.add(pwFin_bt);

			// 위치조정
			btBt_jp.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));

			// 모두 jp6에 집어넣기
			add_jp.add(im_jp);
			add_jp.add(lb_jp);
			add_jp.add(name_jp);
			add_jp.add(pw_jp);
			add_jp.add(logMv_jp);
			add_jp.add(btBt_jp);

			// jp6 jp에 넣기
			jp.add(add_jp);
		}
		logMv_bt.addActionListener(this);
		join_bt.addActionListener(this);
		pwFin_bt.addActionListener(this);
		idCk_bt.addActionListener(this);

//		ADD 
		{
			jp_headerSubLeft.add(new JLabel(imageIcon));
			jp_headerSubLeft.add(jLabel1);
			jp_headerSub.add(jp_headerSubLeft);
			jp_headerSub.add(jp_headerSubRight);
			jp_headerMain.add(jp_headerSub);

			setLayout(new BorderLayout());
			add(jp_headerMain, BorderLayout.NORTH);
			add(jp, BorderLayout.CENTER);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 임의의 id와 em 생성 나중에 db연결하고 바꿀예정
		String id = "root";
		String name = "root";
		String em = "root";
		JButton obj = (JButton) e.getSource();
		if (obj == logMv_bt) { // 로그인이동버튼
			jtf_name.setText("");
			jtf_em.setText("");
			main.cardLayout.show(main.cardJPanel, "login_Main");
		} else if (obj == join_bt) { // 회원가입으로 이동
			jtf_name.setText("");
			jtf_em.setText("");
			main.cardLayout.show(main.cardJPanel, "login_Register");
		} else if (obj == pwFin_bt) { // 비밀번호 찾기로 이동
			jtf_name.setText("");
			jtf_em.setText("");
			main.cardLayout.show(main.cardJPanel, "pw_Search");
		} else if (obj == idCk_bt) { // 계정 확인버튼 알림창
			if (jtf_name.getText().length() == 0) { // 입력값이 없을때
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jtf_name.requestFocus();
			} else if (jtf_em.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jtf_em.requestFocus();
			} else if (jtf_name.getText().equals(name) && jtf_em.getText().equals(em)) { //입력정보 일치
				jtf_name.setText("");
				jtf_em.setText("");
				int result = JOptionPane.showConfirmDialog(null, "아이디는 " + id + "입니다.\n 비밀번호도 찾으시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					main.cardLayout.show(main.cardJPanel, "pw_Search");
				} else {
					main.cardLayout.show(main.cardJPanel, "login_Main");
				}
			} else {
				JOptionPane.showMessageDialog(null, "입력된 정보가 없습니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public Id_Search() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			// Select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new Id_Search();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
