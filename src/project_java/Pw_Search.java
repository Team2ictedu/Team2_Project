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

import Server.Protocol;
import UserDB.UserVO;

public class Pw_Search extends JPanel implements ActionListener {
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1, lb;
	TextPrompt tp1, tp2;
	Border newBorder;

	JPanel im_jp, log_im, lb_jp, idCg_jp, name_jp, em_jp, logMv_jp, btBt_jp, add_jp;
	JTextField idCg_jtf, name_jtf, em_jtf;
	JButton logMv_bt, join_bt, idFin_bt, pwCk_bt;
	Main main;

	public Pw_Search(Main main) {
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
			idCg_jp = new JPanel();
			idCg_jp.setOpaque(false);
			name_jp = new JPanel();
			name_jp.setOpaque(false);
			logMv_jp = new JPanel();
			logMv_jp.setOpaque(false);
			add_jp = new JPanel();
			add_jp.setOpaque(false);
			btBt_jp = new JPanel();
			btBt_jp.setOpaque(false);
			em_jp = new JPanel();
			em_jp.setOpaque(false);
			logMv_bt = new JButton(" 로그인 페이지로 ");
			join_bt = new JButton("회원가입");
			idFin_bt = new JButton("아이디 찾기");
			pwCk_bt = new JButton("확인");

			// 버튼 투명도
			join_bt.setOpaque(false);
			join_bt.setContentAreaFilled(false);
			join_bt.setBorderPainted(false);
			idFin_bt.setOpaque(false);
			idFin_bt.setContentAreaFilled(false);
			idFin_bt.setBorderPainted(false);

			// 색상
			logMv_bt.setBackground(Color.decode("#D4B8E8"));

			// font
			logMv_bt.setFont(new Font("Jalnan", Font.PLAIN, 16));
			join_bt.setFont(new Font("Jalnan", Font.PLAIN, 16));
			idFin_bt.setFont(new Font("Jalnan", Font.PLAIN, 16));
			pwCk_bt.setFont(new Font("Jalnan", Font.PLAIN, 12));
			join_bt.setForeground(Color.GRAY);
			idFin_bt.setForeground(Color.GRAY);
			logMv_bt.setForeground(Color.white);
			pwCk_bt.setForeground(Color.WHITE);

			// 모든 panel들 합칠 panel의 레이아웃 box로 설정
			add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));

			// jp1 / 이미지
			log_im = new JPanel();
			log_im.add(new JLabel(imagelogo));
			log_im.setOpaque(false);
			im_jp.add(log_im);

			// jp2 / 로그인label
			lb = new JLabel("비밀번호 찾기");
			lb.setFont(new Font("Jalnan", Font.PLAIN, 26));
			lb_jp.add(lb);

			// jp3, jp4 / textfield+textprompt
			idCg_jtf = new JTextField(30);
			name_jtf = new JTextField(30);
			em_jtf = new JTextField(27);

			// 아이디 입력
			TextPrompt textprompt = new TextPrompt("아이디 입력", idCg_jtf);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.5f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);
			idCg_jtf.setBorder(newBorder);
			idCg_jtf.setOpaque(false);
			idCg_jp.add(idCg_jtf);
			// 위치조정
			idCg_jp.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

			// 이름 입력
			textprompt = new TextPrompt("이름 입력", name_jtf);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.5f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);
			name_jtf.setBorder(newBorder);
			name_jtf.setOpaque(false);
			name_jp.add(name_jtf);
			// 위치조정
			name_jp.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

			// 이메일 입력
			textprompt = new TextPrompt("이메일 입력", em_jtf);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.5f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);
			em_jtf.setBorder(newBorder);
			em_jtf.setOpaque(false);
			em_jp.add(em_jtf);
			pwCk_bt.setPreferredSize(new Dimension(50, 25));
			em_jp.add(pwCk_bt);
			// 위치조정
			em_jp.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

			// jp5 로그인이동 버튼
			logMv_jp.add(logMv_bt);
			// 로그인버튼 크기지정
			logMv_bt.setPreferredSize(new Dimension(150, 40));
			// 위치조정
			logMv_jp.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

			// 하단 jp7 버튼 패널에 붙이기
			btBt_jp.add(join_bt);
			btBt_jp.add(idFin_bt);

			// 위치조정
			btBt_jp.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));

			// 모두 jp6에 집어넣기
			add_jp.add(im_jp);
			add_jp.add(lb_jp);
			add_jp.add(idCg_jp);
			add_jp.add(name_jp);
			add_jp.add(em_jp);
			add_jp.add(logMv_jp);
			add_jp.add(btBt_jp);

			// jp6 jp에 넣기
			jp.add(add_jp);
		}
		logMv_bt.addActionListener(this);
		join_bt.addActionListener(this);
		idFin_bt.addActionListener(this);
		pwCk_bt.addActionListener(this);

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
		JButton obj3 = (JButton) e.getSource();
		if (obj3 == logMv_bt) { // 로그인버튼
			idCg_jtf.setText("");
			name_jtf.setText("");
			em_jtf.setText("");
			main.cardLayout.show(main.cardJPanel, "login_Main");
		} else if (obj3 == join_bt) { // 회원가입으로 이동
			idCg_jtf.setText("");
			name_jtf.setText("");
			em_jtf.setText("");
			main.cardLayout.show(main.cardJPanel, "login_Register");
		} else if (obj3 == idFin_bt) { // 아이디찾기로 이동
			idCg_jtf.setText("");
			name_jtf.setText("");
			em_jtf.setText("");
			main.cardLayout.show(main.cardJPanel, "id_Search");
		} else if (obj3 == pwCk_bt) { // 계정확인버튼
			if (idCg_jtf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				idCg_jtf.requestFocus();
			} else if (name_jtf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				name_jtf.requestFocus();
			} else if (em_jtf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				em_jtf.requestFocus();
			} else {
				try {
					Protocol p = new Protocol();
					UserVO vo = new UserVO();
					vo.setM_ID(idCg_jtf.getText().trim());
					vo.setM_NAME(name_jtf.getText().trim());
					vo.setM_EMAIL(em_jtf.getText().trim());
					p.setVo(vo);
					p.setCmd(402);
					main.out.writeObject(p);
					main.out.flush();
					System.out.println(1);
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
	}

	public Pw_Search() {
		// TODO Auto-generated constructor stub
	}

}