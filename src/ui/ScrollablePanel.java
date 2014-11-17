package ui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.xml.ws.handler.MessageContext.Scope;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.Dimension;

public class ScrollablePanel extends JPanel {

	protected JPanel containerPanel;
	
	/**
	 * Create the panel.
	 */
	public ScrollablePanel() {
		setPreferredSize(new Dimension(100, 100));
		setBackground(new Color(255, 255, 204));
		setLayout(null);
		
		containerPanel = new JPanel();
		containerPanel.setPreferredSize(new Dimension(80, 200));
		containerPanel.setBackground(new Color(204, 255, 204));
		containerPanel.setBounds(0, 11, 104, 221);
		add(containerPanel);

		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent mwe) {
				Point p = containerPanel.getLocation();
				int verticalMove = (int) (mwe.getPreciseWheelRotation() * -10);
				//System.out.println("Scroll: " + verticalMove);
				containerPanel.setLocation(p.x, p.y + verticalMove);
			}
		});
		
		addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
			@Override
			public void ancestorResized(HierarchyEvent evt) {
				adaptContainer();
			}
		});
	}
	
	protected void adaptContainer(){
		
	}
}
