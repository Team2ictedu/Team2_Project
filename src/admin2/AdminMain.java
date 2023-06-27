package admin2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AdminMain extends JFrame{
	CardLayout cardLayout;
	JPanel cardPanel;
	
	public AdminMain() {
		super("Admin");
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Jalnan.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Doodly.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		AdminHome adminHome= new AdminHome(this);
		AdminTemplate1 adminPlaces = new AdminTemplate1(this);
//		AdminUsers adminUsers = new AdminUsers(this);
		
		
		cardPanel.add("greeting",adminHome);
		cardPanel.add("places", adminPlaces);
		add(cardPanel);
		
		cardLayout.show(cardPanel, "places");
		
//		pack();
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
					new AdminMain();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
