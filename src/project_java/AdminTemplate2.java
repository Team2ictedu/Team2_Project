package project_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AdminTemplate2 extends JFrame {

	JPanel jp_header, jp_footer, jp_center, jp_centerLeft, jp_centerRight;
	JTextArea jta;
	JScrollPane jsp;
	JButton jb1_1, jb1_2, jb1_3, jb1_4; /* jb1_n (��� ��ư) , jb2_n(�ϴ� ��ư) */
	JButton jb2_1, jb2_2, jb2_3, jb2_4;

	public AdminTemplate2() {
		super("ADMIN");

		// JPanel 변수선언
		jp_header = new JPanel();
		jp_footer = new JPanel();
		jp_center = new JPanel(new BorderLayout());
		jp_centerRight = new JPanel(new BorderLayout());
		jp_centerLeft = new JPanel();

		// 해더
		jb1_1 = new JButton("관리자 홈");
		jb1_2 = new JButton("관광지");
		jb1_3 = new JButton("사용자");
		jb1_4 = new JButton("플래너");
		jp_header.add(jb1_1);
		jp_header.add(jb1_2);
		jp_header.add(jb1_3);
		jp_header.add(jb1_4);
		jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

		// JTA JSP (centerRight)
		jta = new JTextArea();
		jta.setRows(32);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jta.setFont(new Font("굴림", Font.PLAIN, 15));
		jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setPreferredSize(new Dimension(500,600));
		jp_centerRight.add(jsp);
		

		// centerLeft
//		jp_centerLeft.setLayout(new FlowLayout());
//		jp_centerLeft.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		JPanel jp_centerLeftContainer = new JPanel();
		JPanel jp_centerLeft1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp_centerLeft1.add(new JLabel("TEXT 1"));
		jp_centerLeft1.add(new JTextField(20));
		jp_centerLeftContainer.add(jp_centerLeft1);
		JPanel jp_centerLeft2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp_centerLeft2.add(new JLabel("TEXT 2"));
		jp_centerLeft2.add(new JTextField(20));
		jp_centerLeftContainer.add(jp_centerLeft2);
		JPanel jp_centerLeft3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp_centerLeft3.add(new JLabel("TEXT 3"));
		jp_centerLeft3.add(new JTextField(20));
		jp_centerLeftContainer.add(jp_centerLeft3);
		jp_centerLeftContainer.setLayout(new BoxLayout(jp_centerLeftContainer, BoxLayout.Y_AXIS));
		jp_centerLeft.add(jp_centerLeftContainer, BorderLayout.NORTH);
		// center
		
		jp_center.add(jp_centerRight,BorderLayout.CENTER);
		jp_center.add(jp_centerLeft,BorderLayout.WEST);
		
		// 푸터 버튼
		jb2_1 = new JButton("선택");
		jb2_2 = new JButton("수정");
		jb2_3 = new JButton("삭제");
		jb2_4 = new JButton("등록");
		jp_footer.add(jb2_1);
		jp_footer.add(jb2_2);
		jp_footer.add(jb2_3);
		jp_footer.add(jb2_4);
		
		// etc
		add(jp_header, BorderLayout.NORTH);
		add(jp_center, BorderLayout.CENTER);
		add(jp_footer, BorderLayout.SOUTH);

		setSize(1000, 700);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new AdminTemplate2();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
