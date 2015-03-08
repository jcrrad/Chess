package gui;

import java.awt.Color;
import javax.swing.JButton;

public class Square extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617862454207390583L;
	Color color;
	int rowIndex, colIndex;

	public Square(int x, int y, Color color) 
	{
		this.colIndex = x;
		this.rowIndex = y;
		this.color = color;
		this.setVisible(true);
		this.setBackground(color);
		this.setOpaque(true);
	}

	public int getRow() 
	{
		return rowIndex;
	}

	public void setRow(int row) 
	{
		this.rowIndex = row;
	}

	public int getColumn() 
	{
		return colIndex;
	}
	
	public void setColumn(int column) 
	{
		this.colIndex = column;
	}
}
