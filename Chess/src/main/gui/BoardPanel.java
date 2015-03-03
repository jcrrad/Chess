package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.GameWindowController;
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

	public BoardPanel() {
		// Init Layout
		this.initLayout();
		// Init Pawns
		this.initPawns();
		// Init White Pieces
		this.initWhitePieces();
		// Init Black Pieces
		this.initBlackPieces();

	}
	
	private void initLayout()
	{
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
	
	private void initPawns()
	{
		for (int x = 0; x < 8; x++) {
			squares[x][6].setPiece(new Pawn(Color.BLACK, squares[x][6]));
			squares[x][1].setPiece(new Pawn(Color.WHITE, squares[x][1]));
		}
	}
	
	private void initWhitePieces()
	{
		squares[0][0].setPiece(new Rook(Color.WHITE, squares[0][0]));
		squares[1][0].setPiece(new Knight(Color.WHITE, squares[1][0]));
		squares[2][0].setPiece(new Bishop(Color.WHITE, squares[2][0]));
		squares[3][0].setPiece(new Queen(Color.WHITE, squares[3][0]));
		squares[4][0].setPiece(new King(Color.WHITE, squares[4][0]));
		squares[5][0].setPiece(new Bishop(Color.WHITE, squares[5][0]));
		squares[6][0].setPiece(new Knight(Color.WHITE, squares[6][0]));
		squares[7][0].setPiece(new Rook(Color.WHITE, squares[7][0]));
	}
	
	private void initBlackPieces()
	{
		squares[0][7].setPiece(new Rook(Color.BLACK, squares[0][7]));
		squares[1][7].setPiece(new Knight(Color.BLACK, squares[1][7]));
		squares[2][7].setPiece(new Bishop(Color.BLACK, squares[2][7]));
		squares[3][7].setPiece(new King(Color.BLACK, squares[3][7]));
		squares[4][7].setPiece(new Queen(Color.BLACK, squares[4][7]));
		squares[5][7].setPiece(new Bishop(Color.BLACK, squares[5][7]));
		squares[6][7].setPiece(new Knight(Color.BLACK, squares[6][7]));
		squares[7][7].setPiece(new Rook(Color.BLACK, squares[7][7]));
	}
	
	public void setPiece(Piece newPiece, int x, int y)
	{
		this.squares[x][y].setPiece(newPiece);
	}
	
	public Piece getPiece(int x, int y)
	{
		return this.squares[x][y].getPiece();
	}
	
	public Square getSquare(int x, int y)
	{
		return this.squares[x][y];
	}
	
	public void removePiece(int x, int y)
	{
		this.squares[x][y].removePiece();
	}
	
	public boolean check(Color playerColor)
	{
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		King king = null;
		Piece temp;
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++) 
			{
				temp = this.getPiece(x, y);
				if (temp != null) 
				{
					if (!(temp.equals(playerColor))) 
					{
						opponentPieces.add(temp);
					}
					if (temp.equals(playerColor))
						if (temp.getColor().equals(playerColor) && temp.getName().equals("KING"))
							king = (King) temp;
				}
			}
		}
		for (int z = 0; z < opponentPieces.size(); z++) 
		{
			if (opponentPieces.get(z).moveable(king.getSquare()))
				return true;
		}
		return false;
	}
	
	public boolean checkmate(Color playerColor){
		BoardPanel tmpBoard = new BoardPanel();
		ArrayList<Piece> friendlyPieces = new ArrayList<Piece>();
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		Piece king = null, tmpPiece, friend, opponent;
		int friendx, friendy, opponentx, opponenty, x, y, z;
		
		// Get all Pieces in play
		for (y = 0; y < 8; y++)
		{
			for (x = 0; x < 8; x++)
			{
				tmpPiece = this.getPiece(x, y);
				if (tmpPiece != null)
				{
					if (tmpPiece.equals(playerColor))
					{
						if (tmpPiece.getName().equals("KING"))
						{
							king = (King) tmpPiece;
						}
						friendlyPieces.add(this.getPiece(x, y));
					}
					else
					{
						opponentPieces.add(this.getPiece(x, y));
					}
				}
			}
		}
		
		// Can the King move out of the way?
		friendx = king.getSquare().getColumn();
		friendy = king.getSquare().getRow();
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
			if (king.moveable(this.getSquare(opponentx, opponenty)))
			{
				tmpBoard.squares = this.squares.clone();
				tmpPiece = tmpBoard.getPiece(friendx, friendy); // Get tmp King
				tmpBoard.setPiece(tmpPiece, opponentx, opponenty);
				tmpBoard.removePiece(friendx, friendy);
				if (!tmpBoard.check(king.getColor()))
				{
					return false;
				}
			}
		}
		
		
		// Can anyone kill the Assassin

		for (x = 0; x < friendlyPieces.size(); x++)
		{
			friend = friendlyPieces.get(x);
			for (y = 0; y < opponentPieces.size(); y++)
			{
				opponent = opponentPieces.get(y);
				if (friend.moveable(opponent.getSquare()))
				{
					tmpBoard.squares = this.squares.clone();
					friendx = friend.getSquare().getColumn();
					friendy = friend.getSquare().getRow();
					opponentx = opponent.getSquare().getColumn();
					opponenty = opponent.getSquare().getRow();
					tmpPiece = tmpBoard.getPiece(friendx, friendy); // Get tmp Friendly Piece
					tmpBoard.setPiece(tmpPiece, opponentx, opponenty);
					tmpBoard.removePiece(friendx, friendy);
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
					if (this.getSquare(x, y) == null)
					{
						friendx = friend.getSquare().getColumn();
						friendy = friend.getSquare().getRow();
						if (friend.moveable(this.getSquare(x, y)))
						{
							tmpBoard.squares = this.squares.clone();
							tmpPiece = tmpBoard.getPiece(friendx, friendy);
							tmpBoard.setPiece(tmpPiece, x, y);
							tmpBoard.removePiece(friendx, friendy);
							if (!tmpBoard.check(friend.getColor()))
							{
								return false;
							}
						}
					}
				}
			}
		}
		
		// Else, Must be Check Mate
		return true;
	}
	
	public boolean walk(int x1, int y1, int x2, int y2) {
		Square square1 = this.getSquare(x1, y1);
		Square square2 = this.getSquare(x2, y2);
		
		int xDirection, yDirection;
		if (square1.getRow() == square2.getRow()) {
			yDirection = 0;
		} else {
			if (square1.getRow() > square2.getRow())
				yDirection = -1;
			else
				yDirection = 1;
		}
		if (square1.getColumn() == square2.getColumn()) {
			xDirection = 0;
		} else {
			if (square1.getColumn() > square2.getColumn())
				xDirection = -1;
			else
				xDirection = 1;
		}

		int xDiff = Math.abs(square1.getColumn() - square2.getColumn());
		int yDiff = Math.abs(square1.getRow() - square2.getRow());

		int diff;
		if ( xDiff > yDiff)
		{
			diff = xDiff;
		}
		else
		{
			diff = yDiff;
		}

		int x = square1.getColumn();
		int y = square1.getRow();
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
	
	public void setPieceListener(ActionListener listener)
	{
		for(Square[] outer : squares)
			for(Square inner : outer)
			{
				inner.addActionListener(listener);
			}
	}
}
