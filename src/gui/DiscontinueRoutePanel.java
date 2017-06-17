package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.KPSmartController;
import logic.RouteService;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class DiscontinueRoutePanel extends JPanel implements ActionListener {

	private KPSmartController controller;
	private JComboBox originDropDownBox;
	private JComboBox destinationDropDownBox;
	private JComboBox priorityDropDownBox;
	
	/**
	 * Create the panel.
	 */
	public DiscontinueRoutePanel(KPSmartController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel discontinueRouteLabel = new JLabel("Discontinue Route");
		discontinueRouteLabel.setForeground(new Color(0, 51, 102));
		discontinueRouteLabel.setBorder(new EmptyBorder(10, 0, 20, 0));

		discontinueRouteLabel.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_discontinueRouteLabel = new GridBagConstraints();
		gbc_discontinueRouteLabel.gridwidth = 3;
		gbc_discontinueRouteLabel.insets = new Insets(0, 0, 5, 0);
		gbc_discontinueRouteLabel.gridx = 0;
		gbc_discontinueRouteLabel.gridy = 0;
		add(discontinueRouteLabel, gbc_discontinueRouteLabel);
		
		JLabel originLabel = new JLabel("Origin");
		GridBagConstraints gbc_originLabel = new GridBagConstraints();
		gbc_originLabel.insets = new Insets(0, 0, 5, 5);
		gbc_originLabel.gridx = 0;
		gbc_originLabel.gridy = 1;
		add(originLabel, gbc_originLabel);
		
		JLabel destinationLabel = new JLabel("Destination");
		GridBagConstraints gbc_destinationLabel = new GridBagConstraints();
		gbc_destinationLabel.insets = new Insets(0, 0, 5, 0);
		gbc_destinationLabel.gridx = 2;
		gbc_destinationLabel.gridy = 1;
		add(destinationLabel, gbc_destinationLabel);
		
		this.originDropDownBox = new JComboBox();
		ArrayList<String> origins = RouteService.getOrigins();
		originDropDownBox.setModel(new DefaultComboBoxModel(origins.toArray()));
		originDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_originDropDownBox = new GridBagConstraints();
		gbc_originDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_originDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_originDropDownBox.gridx = 0;
		gbc_originDropDownBox.gridy = 2;
		add(originDropDownBox, gbc_originDropDownBox);
		
		this.destinationDropDownBox = new JComboBox();
		ArrayList<String> destinations = RouteService.getDestinations();
		destinationDropDownBox.setModel(new DefaultComboBoxModel(destinations.toArray()));
		destinationDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_destinationDropDownBox = new GridBagConstraints();
		gbc_destinationDropDownBox.insets = new Insets(0, 0, 5, 0);
		gbc_destinationDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationDropDownBox.gridx = 2;
		gbc_destinationDropDownBox.gridy = 2;
		add(destinationDropDownBox, gbc_destinationDropDownBox);
		
		JLabel lblPriority = new JLabel("Priority");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 3;
		add(lblPriority, gbc_lblPriority);
		
		this.priorityDropDownBox = new JComboBox();
		priorityDropDownBox.setModel(new DefaultComboBoxModel(new String[] {"Air", "Land", "Sea"}));
		priorityDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 4;
		add(priorityDropDownBox, gbc_comboBox);
		
		JButton doneButton = new JButton("Delete Route");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 5;
		add(doneButton, gbc_doneButton);
		doneButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.originDropDownBox.getSelectedItem() != null
				&& this.destinationDropDownBox.getSelectedItem() != null
				&& this.priorityDropDownBox.getSelectedItem() != null) {
			String createAttempt = this.controller.discontinueRoute(this.originDropDownBox.getSelectedItem().toString(),
					this.destinationDropDownBox.getSelectedItem().toString(), this.priorityDropDownBox.getSelectedItem().toString());
			if (!createAttempt.equals("Success")) {
				JOptionPane.showMessageDialog(new JFrame(), createAttempt, "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				this.controller.getKPSmartFrame().changeFocus("Home Screen");
			}
			
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Please fill out all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
