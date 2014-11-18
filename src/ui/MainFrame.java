package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 219, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ScrollablePanel scrollablePanel = new ScrollablePanel();
		scrollablePanel.containerPanel.setBounds(0, 0, 193, 300);
		
		Adapter adapter_1 = new Adapter();
		scrollablePanel.containerPanel.add(adapter_1);
		
		Adapter adapter_2 = new Adapter();
		scrollablePanel.containerPanel.add(adapter_2);
		
		Adapter adapter = new Adapter();
		scrollablePanel.containerPanel.add(adapter);
		contentPane.add(scrollablePanel, BorderLayout.CENTER);
		

	}
}
