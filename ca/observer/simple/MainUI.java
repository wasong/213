package ca.observer.simple;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Simple GUI to add elements to a NumberList and register
 * as an observer for the list.
 */
@SuppressWarnings("serial")
public class MainUI extends JFrame {

	private NumberList list = new NumberList();
	private JTextArea listDisplay;

	public MainUI() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		add(makeListDisplay());
		add(makeAddButton());
		registerAsObserver();

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private Component makeListDisplay() {
		listDisplay = new JTextArea();
		listDisplay.setWrapStyleWord(true);
		listDisplay.setColumns(20);
		listDisplay.setRows(10);
		listDisplay.setLineWrap(true);
		return listDisplay;
	}

	private Component makeAddButton() {
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list.insert((int)(Math.random()*100));
			}
		});
//				e -> list.insert((int)(Math.random()*100)));
		return addBtn;
	}

	private void registerAsObserver() {
		list.addObserver(new NumberListObserver() {
			@Override
			public void stateChanged() {
				updateTextList();
			}
		});

		// Or, using Lambda Expressions:
		/*
		list.addObserver( 
				() -> updateTextList());
		*/
	}

	private void updateTextList() {
		String message = "";
		for (Integer value : list) {
			message += value + ", ";
		}
		listDisplay.setText(message);
	}

	public static void main(String[] args) {
		new MainUI();
	}

}
