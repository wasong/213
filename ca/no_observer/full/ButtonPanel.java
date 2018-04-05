package ca.no_observer.full;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel of buttons allowing UI to control contents on a number list.
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	private NumberList model;
	
	// !! Without observers, any code which changes the model 
	//    must update rest of UI directly.
	private ListDisplayPanel listDisplayPanel;
	private ListStatsPanel listStatsPanel;
	
	public ButtonPanel(NumberList model, 
			ListDisplayPanel listDisplayPanel, 
			ListStatsPanel listStatsPanel) {
		this.model = model;
		
		// !! Without observers, we need to directly know about other parts of the UI.
		this.listDisplayPanel = listDisplayPanel;
		this.listStatsPanel = listStatsPanel;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(100, 100));

		JButton addBtn = new JButton("Add");
		addBtn.addActionListener( 
				event -> addNumberToList());
		add(addBtn);

		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener( 
				event -> clearList());
		add(clearBtn);
	}

	private void addNumberToList() {
		model.insert((int)(Math.random() * 100));
		
		// !! Without observers, any code which changes the model 
		//    must update rest of UI directly.
		listDisplayPanel.updateTextList();
		listStatsPanel.updateStats();
	}

	private void clearList() {
		model.clear();

		// !! Without observers, any code which changes the model 
		//    must update rest of UI directly.
		listDisplayPanel.updateTextList();
		listStatsPanel.updateStats();
	}
	

}
