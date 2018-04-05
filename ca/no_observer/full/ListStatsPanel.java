package ca.no_observer.full;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI panel to display simple statistics about a NumberList.
 */
@SuppressWarnings("serial")
public class ListStatsPanel extends JPanel {

	private NumberList model;
	private JLabel min;
	private JLabel max;
	private JLabel sum;
	private JLabel size;

	public ListStatsPanel(NumberList model) {
		this.model = model;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(new JLabel("List Statistics"));
		add(min = new JLabel());
		add(max = new JLabel());
		add(sum = new JLabel());
		add(size = new JLabel());
		
		setPreferredSize(new Dimension(200, 200));
		updateStats();
	}

	public void updateStats() {
		if (model.size() > 0) {
			min.setText("Min: " + model.min());
			max.setText("Max: " + model.max());
		} else {
			min.setText("Min: ---");
			max.setText("Max: ---");
		}
		sum.setText("Sum: " + model.sum());
		size.setText("Size: " + model.size());
	}
}
