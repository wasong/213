package ca.observer.full;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * Structured GUI demonstrating how to use a model and 
 * observers in multiple panels.
 * 
 * Demos:
 * - Add more ListDisplayPanel() objects
 * - Add new button "fill" that adds 10 numbers
 */
@SuppressWarnings("serial")
public class MainUI extends JFrame {

	private NumberList list = new NumberList();

	public MainUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		add(new ListDisplayPanel(list));
		add(new ButtonPanel(list));
		add(new ListStatsPanel(list));

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainUI();
	}
}
