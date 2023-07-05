package adminOld_dontuse;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AdminMain extends JFrame{
	CardLayout cardLayout;
	JPanel cardPanel;
	
	public AdminMain() {
		super("Admin");
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		AdminGreeting adminGreeting = new AdminGreeting(this);
		AdminPlaces adminPlaces = new AdminPlaces(this);
		AdminUsers adminUsers = new AdminUsers(this);
		
		
		cardPanel.add("greeting",adminGreeting);
		cardPanel.add("places", adminPlaces);
		cardPanel.add("users", adminUsers);
		add(cardPanel);
		
		cardLayout.show(cardPanel, "greeting");
		
		
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
