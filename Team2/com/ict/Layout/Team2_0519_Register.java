package Team2.com.ict.Layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Team2_0519_Register extends JFrame {
	public Team2_0519_Register() {
		super("회원가입");

		JPanel jp1 = new JPanel(new GridLayout(6, 0));
		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jp7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel jl1 = new JLabel("이름: ");
		JTextField jtf1 = new JTextField(10);

		JLabel jl5 = new JLabel("성별: ");
		JRadioButton jrb1 = new JRadioButton("남");
		JRadioButton jrb2 = new JRadioButton("여");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);

		JLabel jl2 = new JLabel("생년월일: ");
		JTextField jtf2 = new JTextField(7);

		JLabel jl3 = new JLabel("주소: ");
		JTextField jtf3 = new JTextField(10);


		JLabel jl6 = new JLabel("부서명: ");
		String[] select = {"부서", "인사", "총무", "복지", "생산"};
		JComboBox<String> jcomb1 = new JComboBox<>(select);

		JLabel jl4 = new JLabel("취미: ");
		JCheckBox jcb1 = new JCheckBox("운동");
		JCheckBox jcb2 = new JCheckBox("영화");
		JCheckBox jcb3 = new JCheckBox("독서");
		JCheckBox jcb4 = new JCheckBox("컴퓨터");
		
		
		JLabel jl7 = new JLabel("자 기 소 개 ");
		
		jp1.add(jp2);
		jp1.add(jp3);
		jp1.add(jp4);
		jp1.add(jp5);
		jp1.add(jp6);
		jp1.add(jp7);
		
		jp2.add(jl1);
		jp2.add(jtf1);
		jp2.add(jl5);
		jp2.add(jrb1);
		jp2.add(jrb2);
		
		jp3.add(jl2);
		jp3.add(jtf2);
		
		jp4.add(jl3);
		jp4.add(jtf3);
		
		jp5.add(jl6);
		jp5.add(jcomb1);
		
		jp6.add(jl4);
		jp6.add(jcb1);
		jp6.add(jcb2);
		jp6.add(jcb3);
		jp6.add(jcb4);
		
		jp7.add(jl7);
		add(jp1, BorderLayout.NORTH);

		JTextArea jta = new JTextArea(5, 20);
		jta.setLineWrap(true);
		JScrollPane jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, // 세로
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER // 가로
		);
		
		jta.setEditable(true);
		add(jsp, BorderLayout.CENTER);
		JPanel sp = new JPanel();
		JButton jbt1 = new JButton("계 산");
		JButton jbt2 = new JButton("종 료");
		JButton jbt3 = new JButton("취 소");
		sp.add(jbt1);
		sp.add(jbt2);
		sp.add(jbt3);
		add(sp, BorderLayout.SOUTH);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width / 2 - 350, ds.height / 2 - 350, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	
	}

	public static void main(String[] args) {
		new Team2_0519_Register();
	}
}
