
package main;

import java.awt.EventQueue;

import ui.MainFrame;

public class Main {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
}
