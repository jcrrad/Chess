package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JPanel {
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	ChatPanel chatSection = new ChatPanel();
	RecordPanel recordSection = new RecordPanel();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TabbedPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));

		this.add(tabbedPane);
		tabbedPane.add("Chat", chatSection);
		tabbedPane.add("Record", recordSection);
	}
}
