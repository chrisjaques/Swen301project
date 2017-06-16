package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NavigationalHomeScreen extends JFrame{

	private JPanel userFocusPanel;
	private String currentUser;
	private String userFocus;
	private JPanel newOrderPanel;
	private JPanel newClerkPanel;
	private JPanel newRoutePanel;
	private JPanel updatePricePanel;
	private JPanel viewFiguresPanel;

	/**
	 * Create the frame.
	 */
	public NavigationalHomeScreen(String focus, String user) {
		//Is user a manager or clerk? 
		this.currentUser = user;
		//set initial focus
		this.userFocus = focus;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 400);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		addNavigationPanel(contentPane);
		addUserFocusPanelComponents(contentPane);


	}
	
	public void addNavigationPanel(JPanel parent){
		JPanel navigationPanel = new JPanel();
		GridBagConstraints gbc_navigationPanel = new GridBagConstraints();
		gbc_navigationPanel.insets = new Insets(0, 0, 0, 5);
		gbc_navigationPanel.fill = GridBagConstraints.BOTH;
		gbc_navigationPanel.gridx = 0;
		gbc_navigationPanel.gridy = 0;
		parent.add(navigationPanel, gbc_navigationPanel);
		navigationPanel.setLayout(new GridLayout(6, 0, 0, 0));

		ButtonListener listener = new ButtonListener();

		JButton newOrderButton = new JButton("New Order");
		navigationPanel.add(newOrderButton);
		newOrderButton.addActionListener(listener);

		JButton newRouteButton = new JButton("New Route");
		navigationPanel.add(newRouteButton);
		newRouteButton.addActionListener(listener);


		JButton discontinueRouteButton = new JButton("Discontinue Route");
		navigationPanel.add(discontinueRouteButton);
		discontinueRouteButton.addActionListener(listener);


		JButton updatePriceButton = new JButton("Update Pricing");
		navigationPanel.add(updatePriceButton);
		updatePriceButton.addActionListener(listener);


		JButton createNewUserButton = new JButton("Create New User");
		navigationPanel.add(createNewUserButton);
		createNewUserButton.addActionListener(listener);
		if(currentUser == "CLERK"){
			createNewUserButton.setVisible(false);
		}

		JButton businessFiguresButton = new JButton("View Business Figures");
		navigationPanel.add(businessFiguresButton);
		businessFiguresButton.addActionListener(listener);
		if(currentUser == "CLERK"){
			businessFiguresButton.setVisible(false);
		}
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
		userFocusPanel.add(newClerkPanel, "Create New Clerk");
		this.newRoutePanel = new NewRoutePanel();
		userFocusPanel.add(newRoutePanel, "New Route");
		this.updatePricePanel = new UpdatePricePanel();
		userFocusPanel.add(updatePricePanel, "Update Pricing");
		this.viewFiguresPanel = new ViewFiguresPanel();
		userFocusPanel.add(viewFiguresPanel, "View Figures");
		DiscontinueRoutePanel p = new DiscontinueRoutePanel();
		userFocusPanel.add(p,  "Discontinue Route");
		panel.add(userFocusPanel, gbc_userFocusPanel);
		
		//set initial focus
		System.out.println("Showing "+this.userFocus);
		layout.show(userFocusPanel, this.userFocus);
		
	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			userFocus = e.getActionCommand();
			CardLayout layout = (CardLayout)userFocusPanel.getLayout();
			layout.show(userFocusPanel, e.getActionCommand());
		}

	}
}
