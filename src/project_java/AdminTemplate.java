package project_java;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AdminTemplate extends JFrame {

	JPanel jp_header, jp_footer, jp_center, jp_centerWelcome, jp_centerButtons,jp_centerTextArea;
	JTextArea jta;
	JScrollPane jsp;
	JButton jb1_1, jb1_2, jb1_3, jb1_4; /* jb1_n (��� ��ư) , jb2_n(�ϴ� ��ư) */
	JButton jb2_1, jb2_2, jb2_3, jb2_4;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	LocalDateTime now = LocalDateTime.now();  
	String todayDate = dtf.format(now);
	String adminName = "양동근";
	String welcome = String.format("어서오세요, %s님!      %s", adminName, todayDate);
	
	public AdminTemplate() {
		super("ADMIN");

		// JPanel 변수선언
		jp_header = new JPanel();
		jp_footer = new JPanel();
		jp_center = new JPanel();
		jp_centerWelcome = new JPanel();
		jp_centerButtons = new JPanel();
		jp_centerTextArea = new JPanel();

		// 해더
		jb1_1 = new JButton("관리자 홈");
		jb1_2 = new JButton("관광지");
		jb1_3 = new JButton("사용자");
		jb1_4 = new JButton("플래너");
//		jp_header.add(jb1_1);
//		jp_header.add(jb1_2);
//		jp_header.add(jb1_3);
//		jp_header.add(jb1_4);
		jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

		// JTA JSP / jp_center
		jta = new JTextArea();
		jta.setRows(32);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jta.setFont(new Font("굴림", Font.PLAIN, 15));
		jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setBounds(50, 50, 900, 600);
//		jp_header.add(jb1_1);
		jp_centerWelcome.add(new JLabel(welcome));
		jp_centerButtons.add(jb1_2);
		jp_centerButtons.add(jb1_3);
		jp_centerButtons.add(jb1_4);
		jp_centerTextArea.add(jsp);
		jp_center.add(jp_centerWelcome);
		jp_center.add(jp_centerButtons);
		jp_center.add(jp_centerTextArea);
		jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
		
		// �ϴ�
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
					new AdminTemplate();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
