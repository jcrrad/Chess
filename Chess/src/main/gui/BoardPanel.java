package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.piece.Bishop;
import controller.piece.King;
import controller.piece.Knight;
import controller.piece.Pawn;
import controller.piece.Piece;
import controller.piece.Queen;
import controller.piece.Rook;

public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1700922085295001317L;
	Square[][] squares = new Square[8][8];
	View parentView;

	public BoardPanel(View parent) {
		parentView = parent;
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
		for (int x = 0; x < 8; x++) {
			squares[x][6].setPiece(new Pawn(Color.BLACK, squares[x][6]));
			squares[x][1].setPiece(new Pawn(Color.WHITE, squares[x][1]));
		}
		// white Pieces
		squares[0][0].setPiece(new Rook(Color.WHITE, squares[0][0]));
		squares[1][0].setPiece(new Knight(Color.WHITE, squares[1][0]));
		squares[2][0].setPiece(new Bishop(Color.WHITE, squares[2][0]));
		squares[3][0].setPiece(new Queen(Color.WHITE, squares[3][0]));
		squares[4][0].setPiece(new King(Color.WHITE, squares[4][0]));
		squares[5][0].setPiece(new Bishop(Color.WHITE, squares[5][0]));
		squares[6][0].setPiece(new Knight(Color.WHITE, squares[6][0]));
		squares[7][0].setPiece(new Rook(Color.WHITE, squares[7][0]));

		// black Pieces
		squares[0][7].setPiece(new Rook(Color.BLACK, squares[0][7]));
		squares[1][7].setPiece(new Knight(Color.BLACK, squares[1][7]));
		squares[2][7].setPiece(new Bishop(Color.BLACK, squares[2][7]));
		squares[3][7].setPiece(new King(Color.BLACK, squares[3][7]));
		squares[4][7].setPiece(new Queen(Color.BLACK, squares[4][7]));
		squares[5][7].setPiece(new Bishop(Color.BLACK, squares[5][7]));
		squares[6][7].setPiece(new Knight(Color.BLACK, squares[6][7]));
		squares[7][7].setPiece(new Rook(Color.BLACK, squares[7][7]));

	}

	public boolean check(Color playerColor) {
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		King king = null;
		Piece temp;
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++) {
				temp = squares[x][y].getPiece();
				if (temp != null) {
					if (!(temp.equals(playerColor))) {
						opponentPieces.add(squares[x][y].getPiece());
					}
					if (temp.equals(playerColor))
						if (temp.getColor().equals(playerColor) && temp.getName().equals("KING"))
							king = (King) temp;
				}
			}
		for (int z = 0; z < opponentPieces.size(); z++) {
			if (opponentPieces.get(z).canAttack(king.getSquare()))
				return true;
		}
		return false;
	}

	public boolean walk(Square square, Square square2) {
		int xDirection, yDirection;
		if (square.getRow() == square2.getRow()) {
			yDirection = 0;
		} else {
			if (square.getRow() > square2.getRow())
				yDirection = -1;
			else
				yDirection = 1;
		}
		if (square.getColumn() == square2.getColumn()) {
			xDirection = 0;
		} else {
			if (square.getColumn() > square2.getColumn())
				xDirection = -1;
			else
				xDirection = 1;
		}

		int xDiff = Math.abs(square.getColumn() - square2.getColumn());
		int yDiff = Math.abs(square.getRow() - square2.getRow());

		int diff = Integer.max(xDiff, yDiff);

		int x = square.getColumn();
		int y = square.getRow();
		for (int z = 0; z < diff; z++) {
			int X = x + (z * xDirection);
			int Y = y + (z * yDirection);
			System.out.println(X + "," + Y);
			if (squares[X][Y].getPiece() != null) {
				System.out.println(X + "," + Y + "\tFailed");
				return false;
			}
		}
		return true;
	}
}
