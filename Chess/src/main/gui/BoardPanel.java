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
	
	public BoardPanel(BoardPanel boardpanel){
		try {
			this.squares = (Square[][]) boardpanel.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
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
	
	public boolean checkmate(Square king, Square assassin){
		BoardPanel board = (BoardPanel) king.getParent();
		BoardPanel tmpBoard;
		ArrayList<Piece> friendlyPieces = new ArrayList<Piece>();
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		Piece tmpPiece, friend, opponent;
		int friendx, friendy, opponentx, opponenty, x, y, z;
		
		// Can the King move out of the way?
		friendx = king.getColumn();
		friendy = king.getRow();
		int[] moves = {0,1, 1,0, 0,-1, -1,0, 1,1, 1,-1, -1,1, -1,-1};
		for (x = 0; x < 8; x++)
		{
			opponentx = friendx + moves[2*x];
			opponenty = friendy + moves[2*x+1];
			if (!((0 <= opponentx) && (opponentx <= 7) && (0 <= opponenty) && (opponenty <= 7)))
			{
				// Move out of bounds
				continue;
			}
			if (king.getPiece().canMove(squares[opponentx][opponenty]) && king.getPiece().moveable(squares[opponentx][opponenty]))
			{
				tmpBoard = new BoardPanel(board);
				tmpPiece = tmpBoard.squares[friendx][friendy].getPiece(); // get tmp king
				tmpBoard.squares[friendx][friendy].removePiece();
				tmpBoard.squares[opponentx][opponenty].setPiece(tmpPiece);
				if (!tmpBoard.check(king.getPiece().getColor()))
				{
					return false;
				}
			}
		}
		
		
		// Get Pieces in play
		if (king.getPiece().equals(Color.WHITE))
		{
			friendlyPieces = getColorPieces(Color.WHITE);
			opponentPieces = getColorPieces(Color.BLACK);
		}
		else
		{
			friendlyPieces = getColorPieces(Color.BLACK);
			opponentPieces = getColorPieces(Color.WHITE);
		}
		
		
		// Can anyone kill the Assassin

		for (x = 0; x < friendlyPieces.size(); x++)
		{
			friend = friendlyPieces.get(x);
			for (y = 0; y < opponentPieces.size(); y++)
			{
				opponent = opponentPieces.get(y);
				if (friend.canMove(opponent.getSquare()) && friend.moveable(opponent.getSquare()))
				{
					tmpBoard = new BoardPanel(board);
					friendx = friend.getSquare().getColumn();
					friendy = friend.getSquare().getRow();
					opponentx = opponent.getSquare().getColumn();
					opponenty = opponent.getSquare().getRow();
					tmpPiece = tmpBoard.squares[friendx][friendy].getPiece(); // Get tmp fiendly peice
					tmpBoard.squares[friendx][friendy].removePiece();
					tmpBoard.squares[opponentx][opponenty].setPiece(tmpPiece);
					if (!tmpBoard.check(friend.getColor()))
					{
						return false;
					}
					
				}
			}
		}
		
		// Can anyone intercept the Assassin
		for (z = 0; z < friendlyPieces.size(); z++)
		{
			friend = friendlyPieces.get(z);
			for (y = 0; y < 8; y++)
			{
				for (x = 0; x < 8; x++)
				{
					if (squares[x][y].getPiece() == null)
					{
						tmpBoard = new BoardPanel(board);
						friendx = friend.getSquare().getColumn();
						friendy = friend.getSquare().getRow();
						tmpPiece = tmpBoard.squares[friendx][friendy].getPiece();
						tmpBoard.squares[friendx][friendy].removePiece();
						tmpBoard.squares[x][y].setPiece(tmpPiece);
						if (!tmpBoard.check(friend.getColor()))
						{
							return false;
						}
					}
				}
			}
		}
		
		// Else, Must be Check Mate
		return true;
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

		int diff;
		if ( xDiff > yDiff)
		{
			diff = xDiff;
		}
		else
		{
			diff = yDiff;
		}

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
	
	public ArrayList<Piece> getColorPieces(Color color){
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		Piece tmpPiece;
		
		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				tmpPiece = squares[x][y].getPiece();
				if (tmpPiece != null){
					if (tmpPiece.equals(color)){
						pieces.add(squares[x][y].getPiece());
					}
				}
			}
		}
		
		return pieces;
	}
}
