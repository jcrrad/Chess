import java.awt.Color;

public class Pawn extends Piece {
	public Pawn(Color color, Square square) {
		super(square);
		this.name = "PAWN";
		this.color = color;
	}
}
