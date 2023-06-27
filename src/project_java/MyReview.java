package project_java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MyReview extends JPanel{
	JPanel jp, jp_headerMain, jp_headerSub, jp_headerSubLeft, jp_headerSubRight, jp_buttons, jp_east, jp_west, jp_south, 
			jp_south2,jp_west2;
	JButton jbName, jbMyInfo, jbLogOut, jb1, jb2, jb3, jb4;
	Font customFont;
	JLabel jLabel1;
	
	JPanel lb_jp, bt1_jp, bt2_jp, review1_jp, review2_jp, wr_jp, viewlb_jp, combo_jp, view_jp, jtf1_jp, jtf2_jp,
	  viewjp, writejp, add1_jp, add2_jp, jp_center;
	JLabel re_lb, wr_lb, view_lb;
	JTextField review_jtf;
	//JTextArea review_jta;
	JScrollPane review_jsp;
	JButton re1_bt, re2_bt, re3_bt, re4_bt;
	JComboBox<String> serch1, serch2;
	//GridBagConstraints gbc;
	Border newBorder;
	Object [] colNames = {"Check","글번호","제목","내용"};
	Object [][] datas = {{false,"1","가평캠핑","불멍 물멍 힐링했어요루룰"},{true,"2","일본 탐방","오사카 난바 더웠지만 재밌어요"}};
	JTable table;
	JCheckBox box;
	DefaultTableModel dtm;
	Main main;
	
	public MyReview(Main main) {

	this.main = main;
//	FONT
//	Font font = Font.loadFont("src/homework/fonts/Jalnan.ttf");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/team/login/logo_e/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/team/login/logo_e/Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//콤보박스 메서드
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer(){
			public Component getTableCellRendererComponent  // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				JCheckBox box= new JCheckBox();
				box.setSelected(((Boolean)value).booleanValue());  
				box.setHorizontalAlignment(JLabel.CENTER);
				return box;
			}
		};
		/*
		{//test구간
			jp.setLayout(new GridBagLayout());

			// wr_lb를 jp의 가운데에 정렬하기 위한 GridBagConstraints 설정
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.CENTER;
			
		}*/
		
//	변수 생성
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
			jp_center = new JPanel();
			jp_center.setOpaque(false);
			jp_south2 = new JPanel();
			jp_west2 = new JPanel();

			jb1 = new JButton("새 일정 만들기");
			jb1.setPreferredSize(new Dimension(120, 30));
			jb2 = new JButton("내 일정 조회");
			jb2.setPreferredSize(new Dimension(120, 30));
			jb3 = new JButton("여행 후기");
			jb3.setPreferredSize(new Dimension(120, 30));
			jb4 = new JButton("마이페이지");
			jb4.setPreferredSize(new Dimension(120, 30));
				
			
//회원정보수정			
			jp.setLayout(new BorderLayout());
			/*JPanel jp_title = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel jl_my_info = new JLabel("내 정보 수정");
			jp_title.add(jl_my_info, BorderLayout.NORTH);
			jp_title.setBackground(Color.decode("#B19CCB"));*/
			
			//jp_south2.setBackground(Color.WHITE);
			
			jp_center.setLayout(new BoxLayout(jp_center, BoxLayout.Y_AXIS));
			//jp_center.setLayout(new GridLayout(4,2));
			jp_center.setBorder(new EmptyBorder(50, 100, 50, 100));
			//JPanel jp_center2 = new JPanel();
			//jp.add(jp_center2, BorderLayout.CENTER);
			//jp_center2.setLayout(new BorderLayout());
			//jp_center2.add(jp_title, BorderLayout.NORTH);
			//jp_center2.add(jp_center, BorderLayout.CENTER);
			//jp_center2.add(jp_south2,BorderLayout.SOUTH);
			
//	jb4.setPreferredSize(new Dimension(80, 40));
			jbName = new JButton("이름");
			jbMyInfo = new JButton("내 정보");
			jbLogOut = new JButton("로그아웃");
					

//	font 입력
		jLabel1 = new JLabel(" PERSONAL PLANNER");
		jLabel1.setFont(new Font("Doodly", Font.BOLD, 40));
		jbName.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jbMyInfo.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jbLogOut.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb1.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb2.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb3.setFont(new Font("Jalnan", Font.PLAIN, 12));
		jb4.setFont(new Font("Jalnan", Font.PLAIN, 12));
			
		
//	jbName,jbMyInfo,jbLogout 투명하게 하기
		
			jbName.setOpaque(false);
			jbName.setContentAreaFilled(false);
			jbName.setBorderPainted(false);
			jbMyInfo.setOpaque(false);
			jbMyInfo.setContentAreaFilled(false);
			jbMyInfo.setBorderPainted(false);
			jbLogOut.setOpaque(false);
			jbLogOut.setContentAreaFilled(false);
			jbLogOut.setBorderPainted(false);
				

//	색 바꾸기
		// jb1.setBackground(Color.decode("#98b4d4"));
		
			jp_headerMain.setBackground(Color.decode("#D4B8E8"));
			jp_headerSub.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubLeft.setBackground(Color.decode("#D4B8E8"));
			jp_headerSubRight.setBackground(Color.decode("#D4B8E8"));
			jp_buttons.setBackground(Color.decode("#D4B8E8"));
			jp_west.setBackground(Color.decode("#D4B8E8"));
			jp_east.setBackground(Color.decode("#D4B8E8"));
			jp_south.setBackground(Color.decode("#D4B8E8"));
			//jp_center.setBackground(Color.WHITE);
			jp.setBackground(Color.decode("#ffffff"));
			jb1.setBackground(Color.decode("#eee3f6"));
			jb2.setBackground(Color.decode("#eee3f6"));
			jb3.setBackground(Color.decode("#eee3f6"));
			jb4.setBackground(Color.decode("#eee3f6"));
			

//	레이아웃
		
			jp_buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSub.setLayout(new GridLayout(0, 2));
			jp_headerSubLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_headerSubRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp_headerMain.setLayout(new BoxLayout(jp_headerMain, BoxLayout.Y_AXIS));

		

//	add image 
		ImageIcon imageIcon = new ImageIcon("src/team/login/logo_e/logo3.png");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_DEFAULT); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back


//		 센터 좌측
		jp_west2.setPreferredSize(new Dimension(150, 700));
		jp_west2.setBackground(Color.decode("#F7C0DC"));
		JPanel jp_test = new JPanel();
		
		//신실 작업 툴
		{
			// 변수 선언
			re_lb = new JLabel(" 여행후기 ");
			wr_lb = new JLabel("후기작성");
			view_lb = new JLabel("작성한 후기");
			lb_jp = new JPanel();
			lb_jp.setOpaque(false);
			add1_jp = new JPanel();
			add1_jp.setOpaque(false);
			add2_jp = new JPanel();
			add2_jp.setOpaque(false);
			wr_jp = new JPanel();
			wr_jp.setOpaque(false);
			viewlb_jp = new JPanel();
			viewlb_jp.setOpaque(false);
			view_jp = new JPanel();
			view_jp.setOpaque(false);
			jtf1_jp = new JPanel();
			jtf1_jp.setOpaque(false);
			jtf2_jp = new JPanel();
			jtf2_jp.setOpaque(false);
			bt1_jp = new JPanel();
			bt1_jp.setOpaque(false);
			bt2_jp = new JPanel();
			bt2_jp.setOpaque(false);
			review1_jp = new JPanel();
			review1_jp.setOpaque(false);
			review2_jp = new JPanel();
			review2_jp.setOpaque(false);
			review_jtf = new JTextField(50);
			/*review_jta = new JTextArea();
			review_jsp = new JScrollPane(review_jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
								ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			review_jta.setLineWrap(true);
			review_jta.setEditable(false);*/
			re1_bt = new JButton("전체 후기");
			re2_bt = new JButton("후기 작성");
			re3_bt = new JButton("작성");
			re4_bt = new JButton("삭제");
			combo_jp = new JPanel();
			combo_jp.setOpaque(false);
			
			String[] ser1 = {"플래너번호", "1", "2", "3", "4", "5", "6"};
			String[] ser2 = {"다녀온관광지", "1", "2", "3", "4", "5", "6"};
			
			serch1 = new JComboBox<>(ser1);
			serch2 = new JComboBox<>(ser2);
			//모든 panel들 합칠 panel의 레이아웃 box로 설정
			add1_jp.setLayout(new BoxLayout(add1_jp, BoxLayout.Y_AXIS));
			
			//font 및 버튼 꾸미기
			re_lb.setFont(new Font("Jalnan",Font.PLAIN,20));
			wr_lb.setFont(new Font("Jalnan",Font.PLAIN,20));
			view_lb.setFont(new Font("Jalnan",Font.PLAIN,20));
			re1_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
			re2_bt.setFont(new Font("Jalnan",Font.PLAIN,16));
			re3_bt.setFont(new Font("Jalnan",Font.PLAIN,12));
			re4_bt.setFont(new Font("Jalnan",Font.PLAIN,12));
			re1_bt.setOpaque(false);
			re1_bt.setContentAreaFilled(false);
			re2_bt.setOpaque(false);
			re2_bt.setContentAreaFilled(false);
			re3_bt.setBackground(Color.decode("#D4B8E8"));
			re4_bt.setBackground(Color.decode("#D4B8E8"));
			
			// 패널에 붙이기
			//1. 왼쪽 라벨
			lb_jp.add(re_lb);
			// 위치조정
			lb_jp.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
			jp_west2.add(lb_jp);
			
			// 전체후기, 후기작성 버튼 붙이기
			bt1_jp.add(re1_bt);
			bt2_jp.add(re2_bt);
			jp_west2.add(bt1_jp);
			jp_west2.add(bt2_jp);
			
			//콤보박스 붙이기
			combo_jp.setLayout(new BoxLayout(combo_jp, BoxLayout.Y_AXIS));
			combo_jp.add(serch1);
			combo_jp.add(serch2);
			
			
			// 2. 리뷰 작성, 보기 패널 붙이기
			//wr_jp.add(wr_lb);
			// 위치조정
			wr_lb.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
			review1_jp.add(serch1);
			review1_jp.add(serch2);
			TextPrompt textprompt = new TextPrompt("여행후기를 작성해주세요", review_jtf);
			textprompt.setForeground(Color.gray);
			textprompt.changeAlpha(0.7f);
			textprompt.changeStyle(Font.BOLD + Font.ITALIC);
			review1_jp.add(review_jtf);
			
			
			//크기조정
			review_jtf.setPreferredSize(new Dimension(150,50));
			review1_jp.add(re3_bt);
			//크기조정
			//review1_jp.setPreferredSize(new Dimension(150,0));
			
			
			//모든 panel들 합칠 panel의 레이아웃 box로 설정
			viewjp = new JPanel();
			viewjp.setLayout(new FlowLayout());
			viewjp.add(view_lb);
			viewjp.setBackground(Color.WHITE);
			view_jp.setLayout(new BorderLayout());
			view_jp.add(viewjp, BorderLayout.NORTH);
			//view_lb.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
			//view_jp.add(review_jsp);
			//viewlb_jp.setBorder(new EmptyBorder(0, 500, 0, 0));
			//크기조정
			//review_jsp.setPreferredSize(new Dimension(600,100));
			Object [] colNames = {"Check","글번호","제목","내용"};
			Object [][] datas = {{false,"1","가평캠핑","불멍 물멍 힐링했어요루룰"},{true,"2","일본 탐방","오사카 난바 더웠지만 재밌어요"}};
			dtm = new DefaultTableModel(datas, colNames);
			table = new JTable(dtm);
			table.getColumn("Check").setCellRenderer(dcr);
			box = new JCheckBox();
			box.setHorizontalAlignment(JLabel.CENTER);
			table.getColumn("Check").setCellEditor(new DefaultCellEditor(box));
			review_jsp = new JScrollPane(table);
			review_jsp.setPreferredSize(new Dimension(500,220));
			//add2_jp.setLayout(new BoxLayout(add2_jp, BoxLayout.Y_AXIS));
			view_jp.add(review_jsp, BorderLayout.CENTER);
			view_jp.add(re4_bt, BorderLayout.SOUTH);
			
			add1_jp.add(wr_lb, BorderLayout.CENTER);
			add1_jp.add(combo_jp);
			add1_jp.add(review1_jp);
			add2_jp.add(viewlb_jp);
			add2_jp.add(view_jp);
			
			jp_center.add(add1_jp);
			jp_center.add(add2_jp);
			
			jp.add(jp_center);
		}
		
		
//	ADD 
		jp_test.add(jp_west2, BorderLayout.CENTER);
		jp_test.setBackground(Color.decode("#D4B8E8"));
		
		jp.add(jp_test, BorderLayout.WEST);
		
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
	  }
	}
	public MyReview() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {

		try {
			// Select the Look and Feel
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					// Start the application
////                    BaseSampleFrame app = new BaseSampleFrame("BaseSampleFrame");
//                    app.setSize(800, 600);
//                    app.setLocationRelativeTo(null);
//                    app.setVisible(true);
					new MyReview();
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
