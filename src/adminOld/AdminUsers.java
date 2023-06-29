package adminOld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class AdminUsers extends JPanel{
	AdminMain main;
	JPanel jp_headerMain, jp_headerSub, jp_headerLeft, jp_headerCenter,jp_headerRight,jp_headerText, jp_headerButtons, jp_footer, jp_center, jp_centerLeft, jp_centerRight;
	JTextField emptyTextField;
	JTextArea jta;
	JScrollPane jsp;
	JButton jb0_1;
	JButton jb1_1, jb1_2, /*jb1_3,*/ jb1_4; /* jb1_n (��� ��ư) , jb2_n(�ϴ� ��ư) */
	JButton jb2_1, jb2_2, jb2_3, jb2_4, jb2_5;

	public AdminUsers(AdminMain main) {
		this.main = main;
		// JPanel 변수선언
		jp_headerCenter = new JPanel();
		jp_headerLeft = new JPanel();
		jp_headerRight = new JPanel();
		jp_headerMain = new JPanel();
		jp_headerSub = new JPanel();
		jp_headerText = new JPanel();
		jp_headerButtons = new JPanel();
		jp_footer = new JPanel();
		jp_center = new JPanel(new BorderLayout());
		jp_centerRight = new JPanel(new BorderLayout());
		jp_centerLeft = new JPanel();


		// 해더
		jb0_1 = new JButton("로그아웃");
		jb1_1 = new JButton("관리자 홈");
		jb1_2 = new JButton("관광지");
//		jb1_3 = new JButton("사용자");
		jb1_4 = new JButton("플래너");
		jp_headerButtons.add(jb1_1);
		jp_headerButtons.add(jb1_2);
//		jp_headerButtons.add(jb1_3);
		jp_headerButtons.add(jb1_4);
		jp_headerText.add(new JLabel("사용자 수정"));

		jp_headerLeft.add(new JLabel(""));
		jp_headerRight.add(jb0_1);
		jb0_1.setOpaque(false);
		jb0_1.setContentAreaFilled(false);
		jb0_1.setBorderPainted(false);
		emptyTextField = new JTextField(20);
		emptyTextField.setOpaque(false);
		Border noborder = BorderFactory.createMatteBorder(0,0,0,0,Color.black);
		emptyTextField.setEditable(false);
		emptyTextField.setBorder(noborder);
		jp_headerCenter.add(new JLabel("사용자 수정"));
		jp_headerLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_headerLeft.add(emptyTextField);
		jp_headerRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_headerCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));
		jp_headerSub.setLayout(new GridLayout(0,3));
		jp_headerSub.add(jp_headerLeft);
		jp_headerSub.add(jp_headerCenter);
		jp_headerSub.add(jp_headerRight);
		jp_headerMain.add(jp_headerSub);
		jp_headerMain.add(jp_headerButtons);
		
		

		// JTA JSP (centerRight)
		jta = new JTextArea();
		jta.setRows(30);
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
		jb2_5 = new JButton("전체보기");
		jp_footer.add(jb2_1);
		jp_footer.add(jb2_2);
		jp_footer.add(jb2_3);
		jp_footer.add(jb2_4);
		jp_footer.add(jb2_5);
		
		// etc
		add(jp_headerMain, BorderLayout.NORTH);
		add(jp_center, BorderLayout.CENTER);
		add(jp_footer, BorderLayout.SOUTH);

		
		jb1_1.addActionListener(new ActionListener() { // 관리자 홈 으로 가기
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.cardPanel,"greeting");
				
			}
		});
		
		jb1_2.addActionListener(new ActionListener() {  // 관광지 수정 으로 가기
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.cardPanel,"places");				
			}
		});
		
		jb2_1.addActionListener(new ActionListener() { //missing/SELECT 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jb2_2.addActionListener(new ActionListener() { //missing/EDIT
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jb2_3.addActionListener(new ActionListener() { ///missing/DELETE
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jb2_4.addActionListener(new ActionListener() { //missing/INSERT
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jb2_5.addActionListener(new ActionListener() { ///missing/VIEW ALL
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 ArrayList<UserVO> list = UsersDAO.getInstance().getSelectAll(); 
				 for (UserVO k : list) {
					
					 jta.append(k.getUserid()+"\t");
					 jta.append(k.getUserpwd()+"\t");
					 jta.append(k.getUsername()+"\t");
					 jta.append(k.getUserbirthday()+"\t");
					 jta.append(k.getUserphone()+"\t");
					 jta.append(k.getUserterms()+"\n");
				}
			}
		});
		
		jb0_1.addActionListener(new ActionListener() {  //missing/로그아웃
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
	}
}
