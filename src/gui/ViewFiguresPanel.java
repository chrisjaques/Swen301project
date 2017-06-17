package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class ViewFiguresPanel extends JPanel implements ActionListener{

	JTextPane textPane;
	
	/**
	 * Create the panel.
	 */
	public ViewFiguresPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton allEventsButton = new JButton("View All Events");
		GridBagConstraints gbc_allEventsButton = new GridBagConstraints();
		gbc_allEventsButton.insets = new Insets(0, 0, 5, 5);
		gbc_allEventsButton.gridx = 0;
		gbc_allEventsButton.gridy = 0;
		add(allEventsButton, gbc_allEventsButton);
		
		JButton totalRevenueButton = new JButton("Total Revenue");
		GridBagConstraints gbc_totalRevenueButton = new GridBagConstraints();
		gbc_totalRevenueButton.insets = new Insets(0, 0, 5, 5);
		gbc_totalRevenueButton.gridx = 1;
		gbc_totalRevenueButton.gridy = 0;
		add(totalRevenueButton, gbc_totalRevenueButton);
		
		JButton numEventsButton = new JButton("Number of Events");
		GridBagConstraints gbc_numEventsButton = new GridBagConstraints();
		gbc_numEventsButton.insets = new Insets(0, 0, 5, 5);
		gbc_numEventsButton.gridx = 2;
		gbc_numEventsButton.gridy = 0;
		add(numEventsButton, gbc_numEventsButton);
		
		JButton mailAnalysisButton = new JButton("Mail Analysis");
		GridBagConstraints gbc_mailAnalysisButton = new GridBagConstraints();
		gbc_mailAnalysisButton.insets = new Insets(0, 0, 5, 0);
		gbc_mailAnalysisButton.gridx = 3;
		gbc_mailAnalysisButton.gridy = 0;
		add(mailAnalysisButton, gbc_mailAnalysisButton);
		
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane jsp = new JScrollPane(textPane);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 4;
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 1;
		add(jsp, gbc_textPane);

	}
	
	public void updateTextPane(String content){
		this.textPane.setText(content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonPressed = e.getActionCommand();
		//get controller to retreive xml and then update the text area
		
	}
	
	

}
