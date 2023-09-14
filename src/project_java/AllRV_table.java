package project_java;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import DB_Travel_Location.Travel_Location_VO;
import Server.Protocol;

public class AllRV_table extends JFrame implements ActionListener{
	JPanel jp, add_jp, alllb_jp, tb_jp, bt_jp;
	JLabel lb;
	
	DefaultTableModel model;
	JTable review_jtb;
	JScrollPane review_jsp;
	JButton ok_bt;
	
	Border newboBorder;
	Main main;
	AllReview allReview;
	Travel_Location_VO vo;
 public AllRV_table(AllReview allReview) {
	 super("리뷰 보기");
	 this.allReview = allReview; 
	 main = new Main();
	 main.setVisible(false);
	// 글꼴
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			try {
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts//Doodly.ttf")));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 
// 선언
		jp = new JPanel();
		add_jp = new JPanel();
		alllb_jp = new JPanel();
		tb_jp = new JPanel();
		bt_jp = new JPanel();
		model = new DefaultTableModel();
		ok_bt = new JButton("닫기");
		// 확인버튼 크기지정
		ok_bt.setPreferredSize(new Dimension(100, 30));
		// 합칠 패널들 레이아웃 box로 설정
		add_jp.setLayout(new BoxLayout(add_jp, BoxLayout.Y_AXIS));

//글꼴 및 꾸미기		
		lb = new JLabel("후기");
		lb.setFont(new Font("Jalnan", Font.PLAIN, 20));
		ok_bt.setBackground(Color.decode("#D4B8E8"));
		alllb_jp.setBackground(Color.decode("#ffffff"));
		bt_jp.setBackground(Color.decode("#ffffff"));
		
		// 테이블 담자
		model.addColumn("번호");
		model.addColumn("후기");
		model.addColumn("아이디");
		
		review_jtb = new JTable(model);
		review_jsp = new JScrollPane(review_jtb, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
// 붙이자
		alllb_jp.add(lb);
		alllb_jp.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
		tb_jp.add(review_jsp);
		bt_jp.add(ok_bt);
		
		add_jp.add(alllb_jp);
		add_jp.add(review_jsp);
		add_jp.add(bt_jp);
		
		add(add_jp);
		
		setSize(700, 400);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
//액션이벤트
		ok_bt.addActionListener(this);
		
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton) e.getSource();
		if (obj == ok_bt) {
			setVisible(false);
			
		}
	}
	
/*	public static void main(String[] args) {
		new AllRV_table();
	}*/
	
}
