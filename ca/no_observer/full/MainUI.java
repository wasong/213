package ca.no_observer.full;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * Structured GUI demonstrating how to use a model and 
 * observers in multiple panels.
 * 
 * Demos:
 * - Show in a complex UI how it's not as easy for the UI to update itself
 */
@SuppressWarnings("serial")
public class MainUI extends JFrame {

	private NumberList list = new NumberList();

	public MainUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		 
		ListDisplayPanel listDisplayPanel = new ListDisplayPanel(list);
		ListStatsPanel listStatsPanel = new ListStatsPanel(list);

		add(listDisplayPanel);
		// !! Without observers, must hand button panel a reference
		//    to all other parts of UI which need to be updated.
		add(new ButtonPanel(list, listDisplayPanel, listStatsPanel));
		add(listStatsPanel);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainUI();
	}
}
