package gui;

import gui.piece.Piece;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends JButton implements ActionListener {
	Piece piece;
	Color color;
	int row, column;
	JLabel label;

	public Square(int x, int y, Color color) {
		this.row = x;
		this.column = y;
		this.color = color;
		this.setBackground(color);
		this.addActionListener(this);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		this.setForeground(piece.getColor());
		this.setText(piece.getName());
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void onClick(ActionEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(piece.toString());

	}
}
