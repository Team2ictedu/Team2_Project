package project_admin;

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

import project_java.Main;

public class AdminMain extends JPanel{
	CardLayout cardLayout;
	JPanel cardPanel;
	Main main;
	public AdminReview adminReview;
	public AdminUsers adminUsers;
	public AdminPlaces adminPlaces;
	public AdminHome adminHome;
	
	
	public AdminMain(Main main) {
		this.main = main;
	
//		cardPanel = new JPanel();
//		cardLayout = new CardLayout();
//		cardPanel.setLayout(cardLayout);
		
		adminHome= new AdminHome(this);
		adminPlaces = new AdminPlaces(this);
		adminUsers = new AdminUsers(this);
		adminReview = new AdminReview(this);
		
		
//		cardPanel.add("greeting",adminHome);
//		cardPanel.add("places", adminPlaces);
//		cardPanel.add("users",adminUsers);
//		cardPanel.add("reviews",adminReview);
//		main.cardJPanel.add("admin_greeting",adminHome);
//		main.c
//		add(cardPanel);
		
//		cardLayout.show(cardPanel, "greeting");
		
//		pack();
//		setSize(1000, 700);
//		setVisible(true);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//	public static void main(String[] args) {
//		
//		
//		try {
//			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//			SwingUtilities.invokeLater(new Runnable() {
//
//				@Override
//				public void run() {
//					new AdminMain();
//				}
//			});
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		
//	}
//	
}
