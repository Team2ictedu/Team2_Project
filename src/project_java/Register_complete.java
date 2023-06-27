package project_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Register_complete extends JFrame implements ActionListener{
	JPanel pwCh_jp, add_jp, lb_jp, pw1_jp, pw2_jp, ok_jp;
	JLabel lb;
	//JTextField pwck1_jtf, pwck2_jtf;
	JButton ok_bt, no_bt;
	Border newBorder;
	Login_Register login_Register;
	public Register_complete(Login_Register login_Register) {
		super("회원가입");
		this.login_Register = login_Register;
		//글꼴
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts//Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//신실 작업 툴
		
		{
			// 변수선언 및 투명도
			pwCh_jp = new JPanel();
			add_jp = new JPanel();
			lb_jp = new JPanel();
			pw1_jp = new JPanel();
			pw2_jp = new JPanel();
			ok_jp = new JPanel();
			ok_bt = new JButton("확인");
			no_bt = new JButton("취소");
			
			// 합칠 패널들 레이아웃 box로 설정
			add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));
			
			//비밀번호 재설정 라벨
			lb = new JLabel("회원가입이 완료되었습니다.");
			lb.setFont(new Font("Jalnan",Font.PLAIN,20));
			// 위치조정
			lb.setBorder(BorderFactory.createEmptyBorder(35,0,0,0));
			lb_jp.add(lb);
			
				
			//확인 버튼
			ok_bt.setFont(new Font("Jalnan",Font.PLAIN,14));
			no_bt.setFont(new Font("Jalnan",Font.PLAIN,14));
			// 위치조정
			//ok_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
			ok_jp.add(ok_bt);
			
			// 붙이기
			add_jp.add(lb_jp);
			add_jp.add(pw1_jp);
			add_jp.add(pw2_jp);
			add_jp.add(ok_jp);
			
			//화면패널에 붙이기
			pwCh_jp.add(add_jp);
			
			add(pwCh_jp);
		}
		
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		ok_bt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton) e.getSource();
		if (obj == ok_bt) {
			setVisible(false);
			login_Register.main.cardLayout.show(login_Register.main.cardJPanel, "login_Main");
		}
	}
	
}
