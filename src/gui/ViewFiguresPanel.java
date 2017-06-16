package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ViewFiguresPanel extends JPanel {

	
	private int eventCount;
	private int totalCost;
	
	/**
	 * Create the panel.
	 */
	public ViewFiguresPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		setEventCount(5);
		JLabel eventCountLabel = new JLabel("Event Count: "+getEventCount());
		GridBagConstraints gbc_eventCountLabel = new GridBagConstraints();
		gbc_eventCountLabel.insets = new Insets(0, 0, 5, 5);
		gbc_eventCountLabel.gridx = 0;
		gbc_eventCountLabel.gridy = 0;
		add(eventCountLabel, gbc_eventCountLabel);
		
		setTotalCost(15);
		JLabel totalIncomeLabel = new JLabel("Total Cost: $"+getTotalCost());
		GridBagConstraints gbc_totalIncomeLabel = new GridBagConstraints();
		gbc_totalIncomeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_totalIncomeLabel.gridx = 1;
		gbc_totalIncomeLabel.gridy = 0;
		add(totalIncomeLabel, gbc_totalIncomeLabel);
		
		
		JTextPane textPane = new JTextPane();
		JScrollPane jsp = new JScrollPane(textPane);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 2;
		gbc_textPane.insets = new Insets(0, 0, 0, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 1;
		add(jsp, gbc_textPane);

	}
	
	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	

}
