package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.piece.*;
import gui.ChessFrame;
import gui.GameView;
import core.client.Model;
import core.client.Model.STATE;

public class GameWindowController extends Controller{

	public GameWindowController(Model model, GameView view) {
		super(model,view);
		this.initBoard();
		view.setButtonPanelQuitListener(new ButtonPanelQuitListener());
		view.setChatPanelSubmitListener(new ChatPanelSubmitListener());
		view.setButtonPanelStalemateListener(new ButtonPanelStalemateListener());
		view.setBoardPieceListener(new BoardPieceListener());
	}

	public void sendMessage(String text) {  
		/*
		 * TODO: This will be the method to send messages out to the server,
		 * this controller will also receive messages, we should keep the null
		 * check as this will keep us from sending blank messages back and forth
		 * and wasting resources
		 */
		if (text.length() != 0)
			System.out.println(text);
		// model.sendMessage();
	}

	public void handleQuit() {
		/*
		 * TODO: This will be the method called when a player quits, the player
		 * who does will be returned to the login screen, but the opposing
		 * player needs to be alerted.
		 */
		System.out.println("Quit Requested");
	}

	public void offerStalemate() {
		// TODO: This will be called when one person offers a stalemate
		System.out.println("Stalemate Offered");
	}

	public void killWindow() {
		//view.dispose();
	}

	@Override
	public void update() 
	{
		System.out.println("Checking ingame");
		if(model.getState() == STATE.INGAME)
		{
			System.out.println("INGAME");
			view.update();
		}
	}
	
	public void initBoard()
	{
		// Place Pawns
		for (int x = 0; x < 8; x++)
		{
			this.model.setPiece(new Pawn(Color.WHITE, this.model.getSquare(x, 1)), x, 1);
			this.model.setPiece(new Pawn(Color.BLACK, this.model.getSquare(x, 6)), x, 6);
		}
		
		// Place White Pieces
		this.model.setPiece(new Rook(Color.WHITE, this.model.getSquare(0, 0)), 0, 0);
		this.model.setPiece(new Knight(Color.WHITE, this.model.getSquare(1, 0)), 1, 0);
		this.model.setPiece(new Bishop(Color.WHITE, this.model.getSquare(2, 0)), 2, 0);
		this.model.setPiece(new Queen(Color.WHITE, this.model.getSquare(3, 0)), 3, 0);
		this.model.setPiece(new King(Color.WHITE, this.model.getSquare(4, 0)), 4, 0);
		this.model.setPiece(new Bishop(Color.WHITE, this.model.getSquare(5, 0)), 5, 0);
		this.model.setPiece(new Knight(Color.WHITE, this.model.getSquare(6, 0)), 6, 0);
		this.model.setPiece(new Rook(Color.WHITE, this.model.getSquare(7, 0)), 7, 0);
		
		// Place Black Pieces
		this.model.setPiece(new Rook(Color.BLACK, this.model.getSquare(0, 7)), 0, 7);
		this.model.setPiece(new Knight(Color.BLACK, this.model.getSquare(1, 7)), 1, 7);
		this.model.setPiece(new Bishop(Color.BLACK, this.model.getSquare(2, 7)), 2, 7);
		this.model.setPiece(new King(Color.BLACK, this.model.getSquare(3, 7)), 3, 7);
		this.model.setPiece(new Queen(Color.BLACK, this.model.getSquare(4, 7)), 4, 7);
		this.model.setPiece(new Bishop(Color.BLACK, this.model.getSquare(5, 7)), 5, 7);
		this.model.setPiece(new Knight(Color.BLACK, this.model.getSquare(6, 7)), 6, 7);
		this.model.setPiece(new Rook(Color.BLACK, this.model.getSquare(7, 7)), 7, 7);
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
				temp = this.model.getPiece(x, y);
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
			if (opponentPieces.get(z).canAttack(king.getSquare()))
				return true;
		}
		return false;
	}
	
	public boolean checkTmp(Color playerColor)
	{
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		King king = null;
		Piece temp;
		for (int y = 0; y < 8; y++)
		{
			for (int x = 0; x < 8; x++)
			{
				temp = this.model.getTmpPiece(x, y);
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
			if (opponentPieces.get(z).canAttack(king.getSquare()))
				return true;
		}
		return false;
		
	}

	public boolean checkmate(Color playerColor){
		ArrayList<Piece> friendlyPieces = new ArrayList<Piece>();
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		Piece king = null, tmpPiece, friend, opponent;
		int friendx, friendy, opponentx, opponenty, x, y, z;
		
		// Get all Pieces in play
		for (y = 0; y < 8; y++)
		{
			for (x = 0; x < 8; x++)
			{
				tmpPiece = this.model.getPiece(x, y);
				if (tmpPiece != null)
				{
					if (tmpPiece.equals(playerColor))
					{
						if (tmpPiece.getName().equals("KING"))
						{
							king = (King) tmpPiece;
						}
						friendlyPieces.add(this.model.getPiece(x, y));
					}
					else
					{
						opponentPieces.add(this.model.getPiece(x, y));
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
			if (king.canMove(this.model.getSquare(opponentx, opponenty)) 
					&& king.moveable(this.model.getSquare(opponentx, opponenty)))
			{
				this.model.updateTmpSquares();
				tmpPiece = this.model.getTmpPiece(friendx, friendy); // Get tmp King
				this.model.setTmpPiece(tmpPiece, opponentx, opponenty);
				this.model.removeTmpPiece(friendx, friendy);
				if (!checkTmp(king.getColor()))
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
				if (friend.canMove(opponent.getSquare()) 
						&& friend.moveable(opponent.getSquare()))
				{
					this.model.updateTmpSquares();
					friendx = friend.getSquare().getColumn();
					friendy = friend.getSquare().getRow();
					opponentx = opponent.getSquare().getColumn();
					opponenty = opponent.getSquare().getRow();
					tmpPiece = this.model.getTmpPiece(friendx, friendy); // Get tmp Friendly Piece
					this.model.setTmpPiece(tmpPiece, opponentx, opponenty);
					this.model.removeTmpPiece(friendx, friendy);
					if (!checkTmp(friend.getColor()))
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
					if (this.model.getSquare(x, y) == null)
					{
						this.model.updateTmpSquares();
						friendx = friend.getSquare().getColumn();
						friendy = friend.getSquare().getRow();
						tmpPiece = this.model.getTmpPiece(friendx, friendy);
						this.model.setTmpPiece(tmpPiece, x, y);
						this.model.removeTmpPiece(friendx, friendy);
						if (!checkTmp(friend.getColor()))
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
	
	class ButtonPanelQuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			model.setState(STATE.QUIT);
		}	
	}
	
	class ChatPanelSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Sending Text");
		}
	}
	
	class ButtonPanelStalemateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Offering Stalemate");
		}
	}
	
	class BoardPieceListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			System.out.println("A board piece moved");
		}
		
	}
}
