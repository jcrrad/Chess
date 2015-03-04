package core.client.game;

import java.awt.Color;
import java.util.ArrayList;
import core.client.Coordinate;

public class Board {

	Piece[][] pieces = new Piece[8][8];

	public Board() {
		// Create Pawns
		this.initPawns();
		// Create White Pieces
		this.initWhitePieces();
		// Create Black Pieces
		this.initBlackPieces();
		// Create Empty Pieces
		this.initEmptyPieces();
	}

	public Board(String board) {
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++) {
				switch (board.substring(0, 1)) {
				// CAPITAL IS BLACK
				// lower is white
				case "x":
					pieces[x][y] = new EmptyPiece(this, Color.BLUE,
							new Coordinate(x, y));
					break;
				case "K":
					pieces[x][y] = new King(this, Color.BLACK, new Coordinate(
							x, y));
					break;
				case "k":
					pieces[x][y] = new King(this, Color.WHITE, new Coordinate(
							x, y));
					break;
				case "Q":
					pieces[x][y] = new Queen(this, Color.BLACK, new Coordinate(
							x, y));
					break;
				case "q":
					pieces[x][y] = new Queen(this, Color.WHITE, new Coordinate(
							x, y));
					break;
				case "P":
					pieces[x][y] = new Pawn(this, Color.BLACK, new Coordinate(
							x, y));
					break;
				case "p":
					pieces[x][y] = new Pawn(this, Color.WHITE, new Coordinate(
							x, y));
					break;
				case "R":
					pieces[x][y] = new Rook(this, Color.BLACK, new Coordinate(
							x, y));
					break;
				case "r":
					pieces[x][y] = new Rook(this, Color.WHITE, new Coordinate(
							x, y));
					break;
				case "N":
					pieces[x][y] = new Knight(this, Color.BLACK,
							new Coordinate(x, y));
					break;
				case "n":
					pieces[x][y] = new Knight(this, Color.WHITE,
							new Coordinate(x, y));
					break;
				case "B":
					pieces[x][y] = new Bishop(this, Color.BLACK,
							new Coordinate(x, y));
					break;
				case "b":
					pieces[x][y] = new Bishop(this, Color.WHITE,
							new Coordinate(x, y));
					break;
				}
			}
	}

	private void initPawns() {
		for (int x = 0; x < 8; x++) {
			pieces[x][6] = new Pawn(this, Color.BLACK, new Coordinate(x, 6));
			pieces[x][1] = new Pawn(this, Color.WHITE, new Coordinate(x, 1));

		}
	}

	private void initWhitePieces() {
		pieces[0][0] = new Rook(this, Color.WHITE, new Coordinate(0, 0));
		pieces[1][0] = new Knight(this, Color.WHITE, new Coordinate(1, 0));
		pieces[2][0] = new Bishop(this, Color.WHITE, new Coordinate(2, 0));
		pieces[3][0] = new Queen(this, Color.WHITE, new Coordinate(3, 0));
		pieces[4][0] = new King(this, Color.WHITE, new Coordinate(4, 0));
		pieces[5][0] = new Bishop(this, Color.WHITE, new Coordinate(5, 0));
		pieces[6][0] = new Knight(this, Color.WHITE, new Coordinate(6, 0));
		pieces[7][0] = new Rook(this, Color.WHITE, new Coordinate(7, 0));
	}

	private void initBlackPieces() {
		pieces[0][7] = new Rook(this, Color.BLACK, new Coordinate(0, 7));
		pieces[1][7] = new Knight(this, Color.BLACK, new Coordinate(1, 7));
		pieces[2][7] = new Bishop(this, Color.BLACK, new Coordinate(2, 7));
		pieces[3][7] = new King(this, Color.BLACK, new Coordinate(3, 7));
		pieces[4][7] = new Queen(this, Color.BLACK, new Coordinate(4, 7));
		pieces[5][7] = new Bishop(this, Color.BLACK, new Coordinate(5, 7));
		pieces[6][7] = new Knight(this, Color.BLACK, new Coordinate(6, 7));
		pieces[7][7] = new Rook(this, Color.BLACK, new Coordinate(7, 7));

	}

	private void initEmptyPieces() {
		for (int y = 2; y < 6; y++) {
			for (int x = 0; x < 8; x++) {
				pieces[x][y] = new EmptyPiece(this, Color.BLUE, new Coordinate(
						x, y));
			}
		}
	}

	public Piece getPiece(Coordinate location) {
		return pieces[location.getX()][location.getY()];
	}

	public void setPiece(Piece piece, Coordinate location) {
		pieces[location.getX()][location.getY()] = piece;
		piece.setLocation(location);
	}

	public void removePiece(Coordinate location) {
		pieces[location.getX()][location.getY()] = new EmptyPiece(this,
				Color.BLUE, location);
	}

	public boolean movePiece(Coordinate location1, Coordinate location2) {
		Piece piece1, piece2;
		piece1 = this.getPiece(location1);
		piece2 = this.getPiece(location2);

		if ((piece2.getName().equals("") || !(piece1.getColor().equals(piece2
				.getColor()))) && (piece1.moveable(location2))) {
			// Set Piece in new location
			this.setPiece(piece1, location2);
			piece1.setMoved();
			// Clear Piece in old location
			this.removePiece(location1);
			return true;
		}
		return false;
	}

	public boolean isInCheck(Color playerColor) {
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		King king = null;
		Piece temp;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp = this.getPiece(new Coordinate(x, y));
				if (temp != null) {
					if (!(temp.equals(playerColor))) {
						opponentPieces.add(temp);
					}
					if (temp.equals(playerColor))
						if (temp.getColor().equals(playerColor)
								&& temp.getName().equals("KING"))
							king = (King) temp;
				}
			}
		}
		for (int z = 0; z < opponentPieces.size(); z++) {
			if (opponentPieces.get(z).moveable(king.getLocation()))
				return true;
		}
		return false;
	}

	public boolean isInCheckmate(Color playerColor) {
		Board tmpBoard = new Board();
		ArrayList<Piece> friendlyPieces = new ArrayList<Piece>();
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		Piece king = null, tmpPiece, friend, opponent;
		Coordinate friendLocation, opponentLocation = new Coordinate(0, 0);
		int x, y, z;

		// Get all Pieces in play
		for (y = 0; y < 8; y++) {
			for (x = 0; x < 8; x++) {
				tmpPiece = this.getPiece(new Coordinate(x, y));
				if (tmpPiece != null) {
					if (tmpPiece.equals(playerColor)) {
						if (tmpPiece.getName().equals("KING")) {
							king = (King) tmpPiece;
						}
						friendlyPieces.add(this.getPiece(new Coordinate(x, y)));
					} else {
						opponentPieces.add(this.getPiece(new Coordinate(x, y)));
					}
				}
			}
		}

		// Can the King move out of the way?
		friendLocation = king.getLocation();
		int[] moves = { 0, 1, 1, 0, 0, -1, -1, 0, 1, 1, 1, -1, -1, 1, -1, -1 };
		for (x = 0; x < 8; x++) {
			opponentLocation = new Coordinate(friendLocation.getX()
					+ moves[2 * x], friendLocation.getY() + moves[2 * x + 1]);
			if (!((0 <= opponentLocation.getX())
					&& (opponentLocation.getX() <= 7)
					&& (0 <= opponentLocation.getY()) && (opponentLocation
					.getY() <= 7))) {
				// Move out of bounds
				continue;
			}
			if (king.moveable(opponentLocation)) {
				tmpBoard.pieces = this.pieces.clone();
				tmpPiece = tmpBoard.getPiece(friendLocation); // Get tmp King
				tmpBoard.setPiece(tmpPiece, opponentLocation);
				tmpBoard.removePiece(friendLocation);
				if (!tmpBoard.isInCheck(king.getColor())) {
					return false;
				}
			}
		}

		// Can anyone kill the Assassin

		for (x = 0; x < friendlyPieces.size(); x++) {
			friend = friendlyPieces.get(x);
			friendLocation = friend.getLocation();
			for (y = 0; y < opponentPieces.size(); y++) {
				opponent = opponentPieces.get(y);
				opponentLocation = opponent.getLocation();
				if (friend.moveable(opponent.getLocation())) {
					tmpBoard.pieces = this.pieces.clone();
					tmpPiece = tmpBoard.getPiece(friendLocation); // Get tmp
																	// Friendly
																	// Piece
					tmpBoard.setPiece(tmpPiece, opponentLocation);
					tmpBoard.removePiece(friendLocation);
					if (!tmpBoard.isInCheck(friend.getColor())) {
						return false;
					}

				}
			}
		}

		// Can anyone intercept the Assassin
		for (z = 0; z < friendlyPieces.size(); z++) {
			friend = friendlyPieces.get(z);
			friendLocation = friend.getLocation();
			for (y = 0; y < 8; y++) {
				for (x = 0; x < 8; x++) {
					opponentLocation = new Coordinate(x, y);
					if (this.getPiece(opponentLocation) == null) {
						if (friend.moveable(opponentLocation)) {
							tmpBoard.pieces = this.pieces.clone();
							tmpPiece = tmpBoard.getPiece(friendLocation);
							tmpBoard.setPiece(tmpPiece, opponentLocation);
							tmpBoard.removePiece(friendLocation);
							if (!tmpBoard.isInCheck(friend.getColor())) {
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

	public boolean walk(Coordinate location1, Coordinate location2) {
		int x1 = location1.getX(), y1 = location1.getY();
		int x2 = location2.getX(), y2 = location2.getY();

		int xDirection, yDirection;
		if (y1 == y2) {
			yDirection = 0;
		} else {
			if (y1 > y2)
				yDirection = -1;
			else
				yDirection = 1;
		}
		if (x1 == x2) {
			xDirection = 0;
		} else {
			if (x1 > x2)
				xDirection = -1;
			else
				xDirection = 1;
		}

		int xDiff = Math.abs(x1 - x2);
		int yDiff = Math.abs(y1 - y2);

		int diff;
		if (xDiff > yDiff) {
			diff = xDiff;
		} else {
			diff = yDiff;
		}

		int x = x1;
		int y = y1;
		for (int z = 0; z < diff; z++) {
			int X = x + (z * xDirection);
			int Y = y + (z * yDirection);
			if (this.getPiece(new Coordinate(X, Y)) != null) {
				System.out.println(X + "," + Y + "\tFailed");
				return false;
			}
		}
		return true;
	}

	public String toString() {
		String ans = "";
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++) {
				ans += pieces[x][y].getSymbol();
			}
		return ans;
	}
}
