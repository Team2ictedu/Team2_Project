package adminOld_dontuse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class AdminGreeting extends JPanel{
	AdminMain main;
	JPanel jp_headerMain,jp_headerSub, jp_headerLeft,jp_headerCenter,jp_headerRight, jp_footer, jp_center, jp_centerWelcome, jp_centerButtons,jp_centerTextArea;
	JTextArea jta;
	JTextField emptyTextField;
	JScrollPane jsp;
	JButton jb0_1;
	JButton jb1_1, jb1_2, jb1_3, jb1_4; /* jb1_n (��� ��ư) , jb2_n(�ϴ� ��ư) */
	JButton jb2_1, jb2_2, jb2_3, jb2_4;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	LocalDateTime now = LocalDateTime.now();  
	String todayDate = dtf.format(now);
	String adminName = "양동근";
	String welcome = String.format("어서오세요, %s님!      %s", adminName, todayDate);
	
	public AdminGreeting(AdminMain main) {
		this.main = main;

		// JPanel 변수선언
		jp_headerCenter = new JPanel();
		jp_headerLeft = new JPanel();
		jp_headerRight = new JPanel();
		jp_headerMain = new JPanel();
		jp_headerSub = new JPanel();
		jp_footer = new JPanel();
		jp_center = new JPanel();
		jp_centerWelcome = new JPanel();
		jp_centerButtons = new JPanel();
		jp_centerTextArea = new JPanel();

		// 해더
//		jb1_1 = new JButton("관리자 홈");
		jb0_1 = new JButton("로그아웃");
		jb1_2 = new JButton("관광지");
		jb1_3 = new JButton("사용자");
		jb1_4 = new JButton("플래너");
//		jp_header.add(jb1_1);
//		jp_header.add(jb1_2);
//		jp_header.add(jb1_3);
//		jp_header.add(jb1_4);
		jp_headerLeft.add(new JLabel(""));
		jp_headerRight.add(jb0_1);
		jb0_1.setOpaque(false);
		jb0_1.setContentAreaFilled(false);
		jb0_1.setBorderPainted(false);
		jp_headerCenter.add(new JLabel(welcome));
		emptyTextField = new JTextField(20);
		emptyTextField.setOpaque(false);
		Border noborder = BorderFactory.createMatteBorder(0,0,0,0,Color.black);
		emptyTextField.setEditable(false);
		emptyTextField.setBorder(noborder);
		jp_headerLeft.add(emptyTextField);
		jp_headerLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_headerRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_headerCenter.setLayout(new FlowLayout());
		jp_headerSub.setLayout(new GridLayout(0,3));
		jp_headerSub.add(jp_headerLeft);
		jp_headerSub.add(jp_headerCenter);
		jp_headerSub.add(jp_headerRight);
		jp_headerMain.add(jp_headerSub);
		
		// JTA JSP / jp_center
		jta = new JTextArea();
		jta.setText(String.format(""));
		jta.setRows(28);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jta.setFont(new Font("굴림", Font.PLAIN, 15));
		jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setBounds(50, 50, 900, 600);
//		jp_header.add(jb1_1);
//		jp_centerWelcome.add(new JLabel(welcome));
		jp_centerButtons.add(jb1_2);
		jp_centerButtons.add(jb1_3);
		jp_centerButtons.add(jb1_4);
		jp_centerTextArea.add(jsp);
		jp_center.add(jp_centerWelcome);
		jp_center.add(jp_centerButtons);
		jp_center.add(jp_centerTextArea);
		jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
		
		// 푸터
//		jb2_1 = new JButton("선택");
//		jb2_2 = new JButton("수정");
//		jb2_3 = new JButton("삭제");
//		jb2_4 = new JButton("등록");
//		jp_footer.add(jb2_1);
//		jp_footer.add(jb2_2);
//		jp_footer.add(jb2_3);
//		jp_footer.add(jb2_4);
		
		
		// etc
		add(jp_headerMain, BorderLayout.NORTH);
		add(jp_center, BorderLayout.CENTER);
		add(jp_footer, BorderLayout.SOUTH);
	
		 ArrayList<PlaceVO> listPlace = PlacesDAO.getInstance().getSelectAll(); 
		 for (PlaceVO k : listPlace) {
			
			 jta.append("\t\t"+k.getPlacename()+"\t");
			 jta.append(k.getPlacenumber()+"\t");
			 jta.append(k.getPlacelocation()+"\t");
			 jta.append(k.getPlacedescription()+"\t");
			 jta.append(k.getPlaceprice()+"\t");
			 jta.append(k.getPlacereview()+"\n");
		}
		
		jb1_2.addActionListener(new ActionListener() {   //관광지 수정으로 가기
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.cardPanel, "places");
				
			}
		});
		
		jb1_3.addActionListener(new ActionListener() {	//유저 수정으로 가기
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardLayout.show(main.cardPanel, "users");
			}
		});
		
		jb0_1.addActionListener(new ActionListener() {  //missing/로그아웃
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
}
