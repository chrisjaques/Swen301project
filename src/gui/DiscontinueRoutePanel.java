package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class DiscontinueRoutePanel extends JPanel {
	private JTextField destinationTextField;
	private JTextField originTextField;

	/**
	 * Create the panel.
	 */
	public DiscontinueRoutePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblKpsmartDiscontinue = new JLabel("KPSmart - Discontinue Route");
		lblKpsmartDiscontinue.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_lblKpsmartDiscontinue = new GridBagConstraints();
		gbc_lblKpsmartDiscontinue.gridwidth = 3;
		gbc_lblKpsmartDiscontinue.insets = new Insets(0, 0, 5, 5);
		gbc_lblKpsmartDiscontinue.gridx = 0;
		gbc_lblKpsmartDiscontinue.gridy = 0;
		add(lblKpsmartDiscontinue, gbc_lblKpsmartDiscontinue);
		
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
		
		originTextField = new JTextField();
		GridBagConstraints gbc_originTextField = new GridBagConstraints();
		gbc_originTextField.insets = new Insets(0, 0, 5, 5);
		gbc_originTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_originTextField.gridx = 0;
		gbc_originTextField.gridy = 2;
		add(originTextField, gbc_originTextField);
		originTextField.setColumns(10);
		
		destinationTextField = new JTextField();
		GridBagConstraints gbc_destinationTextField = new GridBagConstraints();
		gbc_destinationTextField.insets = new Insets(0, 0, 5, 0);
		gbc_destinationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationTextField.gridx = 2;
		gbc_destinationTextField.gridy = 2;
		add(destinationTextField, gbc_destinationTextField);
		destinationTextField.setColumns(10);
		
		JLabel lblPriority = new JLabel("Priority");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 3;
		add(lblPriority, gbc_lblPriority);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);
		
		JButton doneButton = new JButton("Done");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 5;
		add(doneButton, gbc_doneButton);

	}

}
