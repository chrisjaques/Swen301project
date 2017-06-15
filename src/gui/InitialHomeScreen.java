package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class InitialHomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtLoading;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialHomeScreen frame = new InitialHomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InitialHomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		txtLoading = new JTextField();
		txtLoading.setForeground(Color.LIGHT_GRAY);
		txtLoading.setText("Loading...");
		contentPane.add(txtLoading, BorderLayout.SOUTH);
		txtLoading.setColumns(10);
		
		addButtonsToPanel(panel);

		JLabel lblKpsmartHome = new JLabel("KPSmart - Home");
		lblKpsmartHome.setFont(new Font("Lucida Grande", Font.BOLD, 29));
		lblKpsmartHome.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblKpsmartHome, BorderLayout.NORTH);
	}

	public void addButtonsToPanel(JPanel panel){
		ButtonListener l = new ButtonListener();
		JButton newOrderButton = new JButton("New Order");
		panel.add(newOrderButton);
		newOrderButton.addActionListener(l);
		
		JButton newRouteButton = new JButton("New Route");
		panel.add(newRouteButton);
		newRouteButton.addActionListener(l);

		JButton discontinueRouteButton = new JButton("Discontinue Route");
		panel.add(discontinueRouteButton);
		discontinueRouteButton.addActionListener(l);

		JButton updatePriceButton = new JButton("Update Pricing");
		panel.add(updatePriceButton);
		updatePriceButton.addActionListener(l);

		JButton createNewClerkButton = new JButton("Create New Clerk");
		panel.add(createNewClerkButton);
		createNewClerkButton.addActionListener(l);

		JButton viewBusinessFiguresButton = new JButton("View Business Figures");
		panel.add(viewBusinessFiguresButton);
		viewBusinessFiguresButton.addActionListener(l);

	}

	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			NavigationalHomeScreen frame = new NavigationalHomeScreen(e.getActionCommand(), "MANAGER");
			frame.setVisible(true);
		}

	}

}
