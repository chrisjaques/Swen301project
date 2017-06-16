package gui;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import logic.KPSmartController;

public class NavigationalHomeScreenPanel extends JPanel implements ActionListener {

	private JPanel userFocusPanel;
	private String currentUser;
	private String userFocus;
	private JPanel newOrderPanel;
	private JPanel newClerkPanel;
	private JPanel newRoutePanel;
	private JPanel updatePricePanel;
	private JPanel viewFiguresPanel;
	KPSmartController controller;
	/**
	 * Create the panel.
	 */
	public NavigationalHomeScreenPanel(String focus, String user, KPSmartController controller) {
		this.currentUser = user;
		this.userFocus = focus;
		this.controller = controller;

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.setLayout(gbl_contentPane);

		addNavigationPanel(this);
		addUserFocusPanelComponents(this);
	}
	public void addNavigationPanel(JPanel parent){
		JPanel navigationPanel = new JPanel();
		GridBagConstraints gbc_navigationPanel = new GridBagConstraints();
		gbc_navigationPanel.insets = new Insets(0, 0, 0, 5);
		gbc_navigationPanel.fill = GridBagConstraints.BOTH;
		gbc_navigationPanel.gridx = 0;
		gbc_navigationPanel.gridy = 0;
		parent.add(navigationPanel, gbc_navigationPanel);
		navigationPanel.setLayout(new GridLayout(7, 0, 0, 0));

		JButton newOrderButton = new JButton("New Order");
		navigationPanel.add(newOrderButton);
		newOrderButton.addActionListener(this);

		JButton newRouteButton = new JButton("New Route");
		navigationPanel.add(newRouteButton);
		newRouteButton.addActionListener(this);


		JButton discontinueRouteButton = new JButton("Discontinue Route");
		navigationPanel.add(discontinueRouteButton);
		discontinueRouteButton.addActionListener(this);


		JButton updatePriceButton = new JButton("Update Pricing");
		navigationPanel.add(updatePriceButton);
		updatePriceButton.addActionListener(this);
		
		if(currentUser == "MANAGER"){
			JButton createNewUserButton = new JButton("Create New User");
			navigationPanel.add(createNewUserButton);
			createNewUserButton.addActionListener(this);


			JButton businessFiguresButton = new JButton("View Business Figures");
			navigationPanel.add(businessFiguresButton);
			businessFiguresButton.addActionListener(this);
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getKPSmartFrame().changeFocus("Home Screen");
			}
		});
		navigationPanel.add(btnBack);


	}

	public void addUserFocusPanelComponents(JPanel panel){
		JPanel userFocusPanel = new JPanel();
		GridBagConstraints gbc_userFocusPanel = new GridBagConstraints();
		gbc_userFocusPanel.gridwidth = 7;
		gbc_userFocusPanel.fill = GridBagConstraints.BOTH;
		gbc_userFocusPanel.gridx = 1;
		gbc_userFocusPanel.gridy = 0;
		this.userFocusPanel = userFocusPanel;
		CardLayout layout = new CardLayout(0,0);
		userFocusPanel.setLayout(layout);
		this.newOrderPanel = new NewOrderPanel();
		userFocusPanel.add(newOrderPanel, "New Order");
		this.newClerkPanel = new CreateNewUser();
		userFocusPanel.add(newClerkPanel, "Create New User");
		this.newRoutePanel = new NewRoutePanel();
		userFocusPanel.add(newRoutePanel, "New Route");
		this.updatePricePanel = new UpdatePricePanel();
		userFocusPanel.add(updatePricePanel, "Update Pricing");
		this.viewFiguresPanel = new ViewFiguresPanel();
		userFocusPanel.add(viewFiguresPanel, "View Business Figures");
		DiscontinueRoutePanel p = new DiscontinueRoutePanel();
		userFocusPanel.add(p,  "Discontinue Route");
		panel.add(userFocusPanel, gbc_userFocusPanel);

		//set initial focus
		layout.show(userFocusPanel, this.userFocus);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		userFocus = e.getActionCommand();
		CardLayout layout = (CardLayout)userFocusPanel.getLayout();
		layout.show(userFocusPanel, userFocus);		
	}
}

