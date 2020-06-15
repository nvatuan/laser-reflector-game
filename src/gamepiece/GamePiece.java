package gamepiece;

import gameplay.LaserDirection;

public abstract class GamePiece {
	public static final int ORIENT_UP = 0, ORIENT_RIGHT = 1, ORIENT_DOWN = 2, ORIENT_LEFT = 3;
	public static final int ORIENT_COUNT = 4;
	
	public static final String[] PIECE_NAME = new String[] {
		"Projector", "Receiver", "Mirror", "Wall", "Empty"
	};
	
	public static final int PIECE_PROJECTOR = 0, PIECE_RECEIVER = 1, PIECE_MIRROR = 2, PIECE_WALL = 3, PIECE_EMPTY = 4;
	public static final int PIECE_COUNT = 5;
	
	int pieceID;
	boolean rotatable;
	int		orientation;
	GamePiece() {
		this.rotatable = false;
		this.orientation = GamePiece.ORIENT_UP;
		this.pieceID = GamePiece.PIECE_EMPTY;
	}
	GamePiece(int piece, boolean rot, int orient) {
		this.rotatable = rot;
		assert( (0 <= orient) && (orient <= GamePiece.ORIENT_COUNT) );
		this.orientation = orient;
		assert( (0 <= piece) && (piece <= GamePiece.PIECE_COUNT));
		this.pieceID = piece;
	}
	GamePiece(GamePiece ref) {
		this.pieceID = ref.pieceID;
		this.rotatable = ref.rotatable;
		this.orientation = ref.orientation;
	}
	
	// -- getters
	public int getPieceID() { return this.pieceID; }
	public boolean getRotatable() { return this.rotatable; }
	public int getOrientation() { return this.orientation; }
	
	// -- hash generate function
	public Integer getHash() {
		int hash = 0;
		int piece = this.getPieceID();
		int orient = this.getOrientation();
		boolean rot = this.getRotatable();
		
		// -- generate hash
		hash = piece;
		if (piece == GamePiece.PIECE_WALL || piece == GamePiece.PIECE_EMPTY) {
			return hash;
		} else {
			hash *= 10;
			hash += (rot ? 1 : 0);
			hash *= 10;
			hash += orient;
			return hash;
		}
	}
	
	// -- methods
	public void rotateAnyway() {
		this.orientation = (this.orientation + 1) % GamePiece.ORIENT_COUNT;
	}
	
	public String name() {
		return GamePiece.PIECE_NAME[this.pieceID] + ", rotatable = " + this.rotatable; 
	}
	
	// -- abstract methods
	abstract public LaserDirection bounce(LaserDirection arrived);
	abstract public boolean rotate();
	abstract public GamePiece clone();
}
