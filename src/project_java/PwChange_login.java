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

public class PwChange_login extends JFrame{
	JPanel pwCh_jp, add_jp, lb_jp, pw1_jp, pw2_jp, ok_jp;
	JLabel lb;
	JTextField pwck1_jtf, pwck2_jtf;
	JButton ok_bt;
	Border newBorder;
	public PwChange_login() {
		super("비밀번호 재설정");
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
			
			// 합칠 패널들 레이아웃 box로 설정
			add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));
			
			//비밀번호 재설정 라벨
			lb = new JLabel("비밀번호 재설정");
			lb.setFont(new Font("Jalnan",Font.PLAIN,20));
			// 위치조정
			lb.setBorder(BorderFactory.createEmptyBorder(35,0,0,0));
			lb_jp.add(lb);
			
			// 비밀번호 jtf_1, jtf_2
			pwck1_jtf = new JTextField(25);
			pwck2_jtf = new JTextField(25);
			
			// jtf_1
			TextPrompt tp1 = new TextPrompt("비밀번호", pwck1_jtf);
			tp1.setForeground(Color.gray);
			tp1.changeAlpha(0.5f);
			tp1.changeStyle(Font.BOLD + Font.ITALIC); 
			pwck1_jtf.setBorder(newBorder);
			pwck1_jtf.setBorder(BorderFactory.createEmptyBorder(3,0,3,0));
			pw1_jp.add(pwck1_jtf);
			// 위치조정
			pw1_jp.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
			
			// jtf_2
			tp1 = new TextPrompt("비밀번호 확인", pwck2_jtf);
			tp1.setForeground(Color.gray);
			tp1.changeAlpha(0.5f);
			tp1.changeStyle(Font.BOLD + Font.ITALIC); 
			pwck2_jtf.setBorder(newBorder);
			pwck2_jtf.setBorder(BorderFactory.createEmptyBorder(3,0,3,0));
			pw2_jp.add(pwck2_jtf);
			// 위치조정
			pw2_jp.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
			
			//확인 버튼
			ok_bt.setFont(new Font("Jalnan",Font.PLAIN,14));
			// 위치조정
			ok_jp.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
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
		
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		

	}
	public static void main(String[] args) {
		new PwChange_login();
	}
}
