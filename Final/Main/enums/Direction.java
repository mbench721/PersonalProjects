package enums;

public enum Direction {
	LEFT(0),RIGHT(180),TOP(270),DIAGONAL_LEFT(146),DIAGONAL_RIGHT(34),SPINNER(5);
	private int rotation;

	private Direction(int rotation){
		this.rotation = rotation;
	}
	public int getRotation(){
		return rotation;
	}

}
