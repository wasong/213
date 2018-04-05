package ca.no_observer.full;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * GUI panel to display all the elements in a NumberList as text.
 */
@SuppressWarnings("serial")
public class ListDisplayPanel extends JPanel {
	
	private NumberList model;
	private JTextArea listDisplay;

	public ListDisplayPanel(NumberList model) {
		this.model = model;
		
		setLayout(new BorderLayout());
		add(new JLabel("List contents:"), BorderLayout.NORTH);
		add(makeTextArea(), BorderLayout.CENTER);
	}

	private JTextArea makeTextArea() {
		listDisplay = new JTextArea();
		listDisplay.setWrapStyleWord(true);
		listDisplay.setColumns(20);
		listDisplay.setRows(10);
		listDisplay.setLineWrap(true);
		return listDisplay;
	}
	
	public void updateTextList() {
		String message = "";
		for (Integer value : model) {
			message += value + ", ";
		}
		listDisplay.setText(message);
	}
}
