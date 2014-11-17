package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class Adapter extends JPanel {

	/**
	 * Create the panel.
	 */
	public Adapter() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setPreferredSize(new Dimension(150, 80));
		setBackground(new Color(204, 255, 204));
		setLayout(null);
		
		JProgressBar adapterActionProgress = new JProgressBar();
		adapterActionProgress.setBounds(10, 50, 120, 15);
		add(adapterActionProgress);
		
		JLabel adapterStatusLabel = new JLabel("AdapterStatus");
		adapterStatusLabel.setBounds(10, 30, 120, 15);
		add(adapterStatusLabel);
		
		JLabel lblAdaptername = new JLabel("AdapterName");
		lblAdaptername.setBounds(10, 10, 120, 15);
		add(lblAdaptername);

	}
}
