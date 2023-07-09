package project_java;

import java.awt.BorderLayout;
import java.awt.Button;
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
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import DB_Planner.Planner_VO;
import Server.Protocol;

public class Planner_Create extends JPanel implements ActionListener {
	Main main;
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel_new;
	JTextField jtf_name, jtf_date, jtf_days;
	JButton jb_create, jb_back, jb_calc;
	String[] city = { "City" }; // 시
	JComboBox<String> jcom = new JComboBox<>(city);
	String[] town = { "Town" }; // 동읍리
	JComboBox<String> jcom2 = new JComboBox<>(town);
	JPanel jp_main, jp_checkbox;
	String a;

	public Planner_Create(Main main) {
		this.main = main;
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

//		변수 생성
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
			jb1 = new JButton("새 일정 만들기");
		}
//        TextPrompt tp = new TextPrompt(" ", jtf1);     //<외부 클래스>
//        tp.setForeground(Color.gray);
//        tp.changeAlpha(0.5f);
//        tp.changeStyle(Font.BOLD + Font.ITALIC);     
		{
			jb1.setPreferredSize(new Dimension(120, 30));
			jb2 = new JButton("내 일정 조회");
			jb2.setPreferredSize(new Dimension(120, 30));
			jb3 = new JButton("여행 후기");
			jb3.setPreferredSize(new Dimension(120, 30));
			jb4 = new JButton("마이페이지");
			jb4.setPreferredSize(new Dimension(120, 30));
//		jb4.setPreferredSize(new Dimension(80, 40));
			jbName = new JButton(main.p.getVo().getM_NAME() + "님");
			jbMyInfo = new JButton("내 정보");
			jbLogOut = new JButton("로그아웃");
		}

//		font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel2 = new JLabel("Trip Name");
		jLabel3 = new JLabel("City / Town");
		jLabel4 = new JLabel("Start Date");
		jLabel5 = new JLabel("Days");
		jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));
		jbName.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jbMyInfo.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jbLogOut.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb1.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb2.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb3.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb4.setFont(new Font("Jalnan", Font.PLAIN, 12));

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
		// jb1.setBackground(Color.decode("#98b4d4"));
		{
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
			jp_buttons.setBackground(Color.decode("#D4B8E8"));
			jp_west.setBackground(Color.decode("#D4B8E8"));
			jp_east.setBackground(Color.decode("#D4B8E8"));
			jp_south.setBackground(Color.decode("#D4B8E8"));
			jp.setBackground(Color.decode("#eee3f6"));
			jb1.setBackground(Color.decode("#eee3f6"));
			jb2.setBackground(Color.decode("#eee3f6"));
			jb3.setBackground(Color.decode("#eee3f6"));
			jb4.setBackground(Color.decode("#eee3f6"));

		}

//		레이아웃
		{
			jp_buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSub.setLayout(new GridLayout(0, 2));
			jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
//		jp_headerMain.setLayout(new GridLayout(2,0));
			// jp_headerSub.setLayout(new FlowLayout(FlowLayout.LEFT));

		}

//		add image 
		ImageIcon imageIcon = new ImageIcon("src/images/only_logo.png");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back

		//

		// 소연작업
		{
			// 새일정 만들기 타이틀
			JPanel jp_title = new JPanel();
			jLabel_new = new JLabel("새 일정 만들기");
			jp_title.setLayout(new FlowLayout());
			jp_title.add(jLabel_new);
			jp_title.setBackground(Color.decode("#B19CCB"));
			jp_title.setPreferredSize(new Dimension(70, 30));

			// Font
			jLabel2.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel3.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel4.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel5.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel_new.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jLabel_new.setForeground(Color.white);

			// 주요 내용들
			jp_main = new JPanel();
			jtf_name = new JTextField();
			jtf_date = new JTextField();
			jtf_date.setEditable(false);
			jb_calc = new JButton("SELECT DATE");
			jb_calc.setFont(new Font("Jalnan", Font.PLAIN, 10));
			JPanel jp_date = new JPanel();
			jp_date.setLayout(new GridLayout(0, 2));
			jp_date.setBackground(Color.WHITE);
			jp_date.add(jtf_date);
			jp_date.add(jb_calc);
			jb_calc.setBackground(Color.decode("#F083BA"));
			jb_calc.setForeground(Color.white);
			jtf_days = new JTextField();
			jb_create = new JButton("CREATE");
			jb_back = new JButton("CANCLE");
			jb_create.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jb_back.setFont(new Font("Jalnan", Font.PLAIN, 12));
			jp_checkbox = new JPanel();
			jp_checkbox.add(jcom);
			jp_checkbox.add(jcom2);
			jp_main.setBackground(Color.WHITE);
			jp_main.setLayout(new GridLayout(0, 1));
			jp_checkbox.setLayout(new GridLayout(0, 2));
			jp_main.add(jLabel2);

			// textfield 안 글자
			TextPrompt tp = new TextPrompt("여행제목 ", jtf_name); // 외부클래스
			tp.setForeground(Color.gray);
			tp.changeAlpha(0.5f);
			tp.setFont(new Font("Jalnan", Font.PLAIN, 12));
			TextPrompt tp2 = new TextPrompt("시작날짜 ", jtf_date); // 외부클래스
			tp2.setForeground(Color.gray);
			tp2.changeAlpha(0.5f);
			tp2.setFont(new Font("Jalnan", Font.PLAIN, 12));
			TextPrompt tp3 = new TextPrompt("기간입력 ", jtf_days); // 외부클래스
			tp3.setForeground(Color.gray);
			tp3.changeAlpha(0.5f);
			tp3.setFont(new Font("Jalnan", Font.PLAIN, 12));

			jp_main.add(jtf_name);
			jp_main.add(jLabel3);
			jp_main.add(jp_checkbox);
			jp_main.add(jLabel4);
			jp_main.add(jp_date);
			jp_main.add(jLabel5);
			jp_main.add(jtf_days);
			jp_main.add(new JLabel(""));
			jp_main.add(jb_create);
			jp_main.add(jb_back);
			jb_create.setBackground(Color.decode("#F083BA"));
			jb_create.setForeground(Color.white);
			jb_back.setBackground(Color.decode("#ffffff"));
			jb_back.setForeground(Color.BLACK);
			jp_main.setBorder(BorderFactory.createEmptyBorder(50, 350, 100, 350));

			jp.setLayout(new BorderLayout());
			jp.add(jp_title, BorderLayout.NORTH);
			jp.add(jp_main, BorderLayout.CENTER);

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
			setBackground(Color.decode("#D4B8E8"));
		}
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb_calc.addActionListener(this);
		jbMyInfo.addActionListener(this);
		jbLogOut.addActionListener(this);
		jb_create.addActionListener(this);
		jb_back.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String days = jtf_days.getText();
		int days2 = Integer.parseInt(days);
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
		} else if (obj == jb_create) {
			if (jtf_name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "제목을 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jtf_name.requestFocus(); // 커서위치 조절
			} else if (jtf_date.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "시작날짜를 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
			} else if (jtf_days.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "여행기간을 입력해주세요.", "Confirm", JOptionPane.ERROR_MESSAGE);
				jtf_days.requestFocus();
			} else if (days2 > 7 || days2 < 1) {
				JOptionPane.showMessageDialog(null, "여행기간은 1~7일 입력가능합니다.", "Confirm", JOptionPane.ERROR_MESSAGE);
			} else { // 성공시
				try {
					Protocol p1 = new Protocol();
					Planner_VO planVo = new Planner_VO();
					planVo.setPLAN_TITLE(jtf_name.getText());
					planVo.setPLAN_DATE(jtf_date.getText());
					planVo.setPLAN_DAYS(days2);
					planVo.setM_ID(main.p.getVo().getM_ID());
					planVo.setTL_NUM("1"); // 임시로 한거임
					p1.setPlanvo(planVo);
					p1.setCmd(100);
					main.out.writeObject(p1);
					main.out.flush();
				} catch (IOException e1) {

					// e1.printStackTrace();
				}
			}
		} else if (obj == jb_back) {
			// 취소할때 문자에 길이가 있으면 페이지 이동할건지 여부 확인
			if (jtf_name.getText().length() > 0 || jtf_date.getText().length() > 0 || jtf_days.getText().length() > 0) {
				int result = JOptionPane.showConfirmDialog(null, "작성한 내용을 취소하고 이동하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				// 예 클릭시, 내용 초기화하고 이동시켜주고 아니오 클릭시, 이동취소
				if (result == JOptionPane.YES_OPTION) {
					jtf_name.setText("");
					jtf_date.setText("");
					jtf_days.setText("");
					main.cardLayout.show(main.cardJPanel, "planner_Select");
				}
			} else { // 취소할때 문자길이가 없으면 바로 이동
				main.cardLayout.show(main.cardJPanel, "planner_Select");
			}
		} else if (obj == jb_calc) {
			Calendarmain calendarmain = new Calendarmain(Planner_Create.this);
			calendarmain.setVisible(true);
		}
	}

	public Planner_Create() {
		// TODO Auto-generated constructor stub
	}

}