package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -1700922085295001317L;
	Square[][] squares = new Square[8][8];

	public BoardPanel() {
		// Create Layout
		this.initLayout();
	}

	private void initLayout() {
		this.setLayout(new GridLayout(8, 8));
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++) {
				Color color;
				if ((x + y) % 2 == 1)
					color = new Color(139, 69, 19);
				else
					color = new Color(245, 222, 189);
				squares[x][y] = new Square(x, y, color);
				this.add(squares[x][y]);
			}
	}

	public void setPieceListener(ActionListener listener) {
		for (Square[] outer : squares)
			for (Square inner : outer) {
				inner.addActionListener(listener);
			}
	}
}
