package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;

import logic.RouteService;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;

public class UpdatePricePanel extends JPanel {
	private JTextField newPriceTextField;

	/**
	 * Create the panel.
	 */
	public UpdatePricePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblKpsmartCreate = new JLabel("KPSmart - Update Route Price");
		lblKpsmartCreate.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_lblKpsmartCreate = new GridBagConstraints();
		gbc_lblKpsmartCreate.gridwidth = 3;
		gbc_lblKpsmartCreate.insets = new Insets(0, 0, 5, 0);
		gbc_lblKpsmartCreate.gridx = 0;
		gbc_lblKpsmartCreate.gridy = 0;
		add(lblKpsmartCreate, gbc_lblKpsmartCreate);
		
		JLabel originLabel = new JLabel("Origin");
		GridBagConstraints gbc_originLabel = new GridBagConstraints();
		gbc_originLabel.insets = new Insets(0, 0, 5, 5);
		gbc_originLabel.gridx = 0;
		gbc_originLabel.gridy = 2;
		add(originLabel, gbc_originLabel);
		
		JLabel destinationLabel = new JLabel("Destination");
		GridBagConstraints gbc_destinationLabel = new GridBagConstraints();
		gbc_destinationLabel.insets = new Insets(0, 0, 5, 0);
		gbc_destinationLabel.gridx = 2;
		gbc_destinationLabel.gridy = 2;
		add(destinationLabel, gbc_destinationLabel);
		
		JComboBox originDropDownBox = new JComboBox();
		ArrayList<String> origins = RouteService.getOrigins();
		originDropDownBox.setModel(new DefaultComboBoxModel(origins.toArray()));
		originDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_originDropDownBox = new GridBagConstraints();
		gbc_originDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_originDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_originDropDownBox.gridx = 0;
		gbc_originDropDownBox.gridy = 3;
		add(originDropDownBox, gbc_originDropDownBox);
		
		JComboBox destinationDropDownBox = new JComboBox();
		ArrayList<String> destinations = RouteService.getDestinations();
		destinationDropDownBox.setModel(new DefaultComboBoxModel(destinations.toArray()));
		destinationDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_destinationDropDownBox = new GridBagConstraints();
		gbc_destinationDropDownBox.insets = new Insets(0, 0, 5, 0);
		gbc_destinationDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationDropDownBox.gridx = 2;
		gbc_destinationDropDownBox.gridy = 3;
		add(destinationDropDownBox, gbc_destinationDropDownBox);
		
		JLabel priorityLabel = new JLabel("Priority");
		GridBagConstraints gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 4;
		add(priorityLabel, gbc_priorityLabel);
		
		JLabel newPriceLabel = new JLabel("New Price");
		GridBagConstraints gbc_newPriceLabel = new GridBagConstraints();
		gbc_newPriceLabel.insets = new Insets(0, 0, 5, 0);
		gbc_newPriceLabel.gridx = 2;
		gbc_newPriceLabel.gridy = 4;
		add(newPriceLabel, gbc_newPriceLabel);
		
		JComboBox priorityDropDownBox = new JComboBox();
		priorityDropDownBox.setModel(new DefaultComboBoxModel(new String[] {"Air", "Land", "Sea"}));
		priorityDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_priorityDropDownBox = new GridBagConstraints();
		gbc_priorityDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_priorityDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_priorityDropDownBox.gridx = 0;
		gbc_priorityDropDownBox.gridy = 5;
		add(priorityDropDownBox, gbc_priorityDropDownBox);
		
		newPriceTextField = new JTextField();
		GridBagConstraints gbc_newPriceTextField = new GridBagConstraints();
		gbc_newPriceTextField.insets = new Insets(0, 0, 5, 0);
		gbc_newPriceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPriceTextField.gridx = 2;
		gbc_newPriceTextField.gridy = 5;
		add(newPriceTextField, gbc_newPriceTextField);
		newPriceTextField.setColumns(10);
		
		JButton doneButton = new JButton("Done");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 6;
		add(doneButton, gbc_doneButton);

	}

}