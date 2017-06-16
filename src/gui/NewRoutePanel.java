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

public class NewRoutePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public NewRoutePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblKpsmartCreate = new JLabel("KPSmart - Create New Route");
		lblKpsmartCreate.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_lblKpsmartCreate = new GridBagConstraints();
		gbc_lblKpsmartCreate.gridwidth = 3;
		gbc_lblKpsmartCreate.insets = new Insets(0, 0, 5, 0);
		gbc_lblKpsmartCreate.gridx = 0;
		gbc_lblKpsmartCreate.gridy = 0;
		add(lblKpsmartCreate, gbc_lblKpsmartCreate);
		
		JLabel lblOrigin = new JLabel("Origin");
		GridBagConstraints gbc_lblOrigin = new GridBagConstraints();
		gbc_lblOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigin.gridx = 0;
		gbc_lblOrigin.gridy = 2;
		add(lblOrigin, gbc_lblOrigin);
		
		JLabel lblDestination = new JLabel("Destination");
		GridBagConstraints gbc_lblDestination = new GridBagConstraints();
		gbc_lblDestination.insets = new Insets(0, 0, 5, 0);
		gbc_lblDestination.gridx = 2;
		gbc_lblDestination.gridy = 2;
		add(lblDestination, gbc_lblDestination);
		
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
		
		JLabel lblPriority = new JLabel("Priority");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 4;
		add(lblPriority, gbc_lblPriority);
		
		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 2;
		gbc_lblPrice.gridy = 4;
		add(lblPrice, gbc_lblPrice);
		
		JComboBox priorityDropDownBox = new JComboBox();
		priorityDropDownBox.setModel(new DefaultComboBoxModel(new String[] {"Air", "Land", "Sea"}));
		priorityDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_priorityDropDownBox = new GridBagConstraints();
		gbc_priorityDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_priorityDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_priorityDropDownBox.gridx = 0;
		gbc_priorityDropDownBox.gridy = 5;
		add(priorityDropDownBox, gbc_priorityDropDownBox);
		
		
		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 5;
		add(spinner, gbc_spinner);
		
		JButton btnDone = new JButton("Done");
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.insets = new Insets(0, 0, 0, 5);
		gbc_btnDone.gridx = 1;
		gbc_btnDone.gridy = 6;
		add(btnDone, gbc_btnDone);

	}

}
