package gui;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class LoadingView extends View {

	private static final long serialVersionUID = 1L;

	public LoadingView(ChessFrame frame) {
		super(frame);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{300, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JLabel label = new JLabel("Trying to Connect to Server");
		
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.fill = GridBagConstraints.VERTICAL;
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				this.add(label, gbc_label);
	}
}
