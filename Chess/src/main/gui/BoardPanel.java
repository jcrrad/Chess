package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JPanel;

import controller.GameWindowController;
import controller.piece.Bishop;
import controller.piece.King;
import controller.piece.Knight;
import controller.piece.Pawn;
import controller.piece.Queen;
import controller.piece.Rook;

public class BoardPanel extends JPanel
	implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1700922085295001317L;
	Square[][] squares = new Square[8][8];

	public BoardPanel(GameWindowController gameWindowController) {
		this.setLayout(new GridLayout(8, 8));
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++) {
				Color color;
				if ((x + y) % 2 == 1)
					color = new Color(139, 69, 19);
				else
					color = new Color(245, 222, 189);
				squares[x][y] = new Square(x, y, color, gameWindowController);
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

}
