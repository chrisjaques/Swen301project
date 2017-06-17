package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.KPSmartController;
import logic.User;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

public class HomeScreenPanel extends JPanel implements ActionListener {

	private JPanel contentPane;
	private JTextField txtLoading;
	private KPSmartController controller;
	private JTextField txtLoading_1;
	private KPSmartFrame frame;
	/**
	 * Create the panel.
	 */
	public HomeScreenPanel(KPSmartController controller, KPSmartFrame frame) {
		this.controller = controller;
		setLayout(new BorderLayout(0, 0));
		this.frame = frame;
		JLabel lblHome = new JLabel("Home");
		lblHome.setForeground(new Color(0, 51, 102));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		add(lblHome, BorderLayout.NORTH);

		txtLoading_1 = new JTextField();
		txtLoading_1.setForeground(Color.LIGHT_GRAY);
		txtLoading_1.setText("Loading...");
		add(txtLoading_1, BorderLayout.SOUTH);
		txtLoading_1.setColumns(10);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		addButtonsToPanel(panel);

	}

	public void addButtonsToPanel(JPanel panel){
		int padding = panel.getHeight()/4;
		panel.setBorder(new EmptyBorder(80, 0, 80, 0));


		JButton newOrderButton = new JButton("New Order");
		panel.add(newOrderButton);
		ImageIcon newOrderIcon = new ImageIcon("assets/New Order.jpg");
		addIcon(newOrderIcon, newOrderButton);
		newOrderButton.addActionListener(this);

		JButton newRouteButton = new JButton("New Route");
		panel.add(newRouteButton);
		ImageIcon newRouteIcon = new ImageIcon("assets/New Route.jpg");
		addIcon(newRouteIcon, newRouteButton);
		newRouteButton.addActionListener(this);

		JButton discontinueRouteButton = new JButton("Discontinue Route");
		panel.add(discontinueRouteButton);
		ImageIcon discontinueRouteIcon = new ImageIcon("assets/Discontinue Route.jpg");
		addIcon(discontinueRouteIcon, discontinueRouteButton);
		discontinueRouteButton.addActionListener(this);

		JButton updatePriceButton = new JButton("Update Pricing");
		ImageIcon updatePricingIcon = new ImageIcon("assets/New Order.jpg");
		addIcon(updatePricingIcon, updatePriceButton);
		panel.add(updatePriceButton);
		
		updatePriceButton.addActionListener(this);

		if (this.controller.getCurrentUser().getRole().equals(User.UserType.MANAGER)) {
			JButton createNewUserButton = new JButton("Create New User");
			panel.add(createNewUserButton);
			ImageIcon newUserIcon = new ImageIcon("assets/New User.jpg");
			addIcon(newUserIcon, createNewUserButton);
			createNewUserButton.addActionListener(this);

			JButton viewBusinessFiguresButton = new JButton("View Business Figures");
			panel.add(viewBusinessFiguresButton);
			ImageIcon viewBusinessFiguresIcon = new ImageIcon("assets/View Business Figures.jpg");
			addIcon(viewBusinessFiguresIcon, viewBusinessFiguresButton);
			viewBusinessFiguresButton.addActionListener(this);
		}

		JButton logoutButton = new JButton("Logout");
		panel.add(logoutButton);
		ImageIcon logoutIcon = new ImageIcon("assets/Logout.jpg");
		addIcon(logoutIcon, logoutButton);
		logoutButton.addActionListener(this);		
	}
	
	public void addIcon(ImageIcon icon, JButton button){
		int imgWidth = 40;
		int imgHeight = 40;
		Image iconAsImage = icon.getImage();
		Image scaledImage = iconAsImage.getScaledInstance(imgWidth, imgHeight,  java.awt.Image.SCALE_SMOOTH ) ;  
		ImageIcon buttonIcon = new ImageIcon(scaledImage);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setIcon(buttonIcon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Logout")) {
			// set current user to null to indicate no user has been logged in.
			controller.setCurrentUser(null);			
			controller.getKPSmartFrame().changeFocus("Login Screen");
		} else {
			frame.changeToNavigationalHomeScreen(e.getActionCommand());				
		}

	}

}
