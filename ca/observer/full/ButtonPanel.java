package ca.observer.full;

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
	
	public ButtonPanel(NumberList model) {
		this.model = model;
		
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
	}

	private void clearList() {
		model.clear();
	}
	

}
