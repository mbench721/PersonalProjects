package environment;

public class SpawnPoint {
	
private double x;
private double y;

public SpawnPoint(double x ,double y){
	this.x = x;
	this.setY(y);
}
public double getX() {
	return x;
}
public void setX(double x) {
	this.x = x;
}
public double getY() {
	return y;
}
public void setY(double y) {
	this.y = y;
}
}
