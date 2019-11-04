package utils;

public class Position {
	private int X;
	private int Y;
	
	public static Position add(Position pos1, Position pos2) {
		return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
	}
	
	public static Position scalar(Position pos1, int scalar) {
		return new Position(pos1.getX()* scalar, pos1.getY() * scalar);
	}
	
	
	public Position(int x, int y){
		X = x;
		Y = y;
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	
	public Boolean insideSquare(Position upperLeftCorner, int len) {
		if(X > upperLeftCorner.getX() &&  X < upperLeftCorner.getX() + len) {
			if(Y > upperLeftCorner.getY() &&  Y < upperLeftCorner.getY() + len) {
				return true;
			}
		}
		return false;
	}
}
