package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JPanel {
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	ChatPanel chatSection;
	RecordPanel recordSection = new RecordPanel();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	View parentView;

	public TabbedPanel(View parent) {
		parentView = parent;
		setLayout(new GridLayout(0, 1, 0, 0));

		this.add(tabbedPane);
		tabbedPane.add("Chat", chatSection = new ChatPanel(parentView));
		tabbedPane.add("Record", recordSection);
	}
}