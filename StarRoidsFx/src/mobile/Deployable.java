package mobile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Deployable {
	private Image image;
	private ImageView view;
	private double x;
	private double y;
	
	public Deployable(){
		
	}
	public void move(double m){
	
	}
	public void turn(double t){
		
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageView getView() {
		return view;
	}

	public void setImageView(ImageView view) {
		this.view = view;
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
