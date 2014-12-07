package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
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
		containerPanel.setBounds(0, 0, 104, 221);
		add(containerPanel);
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent mwe) {
				Point p = containerPanel.getLocation();
				int verticalMove = (int) (mwe.getPreciseWheelRotation() * -10);
				int newLoc = p.y + verticalMove;
				int diff = containerPanel.getHeight() - getHeight();
				
				if(newLoc < -diff)
					newLoc = -diff;
				
				if(newLoc > 0)
					newLoc = 0;
				
				containerPanel.setLocation(p.x, newLoc);
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
		containerPanel.setSize(this.getWidth(), containerPanel.getHeight());
	}
}
