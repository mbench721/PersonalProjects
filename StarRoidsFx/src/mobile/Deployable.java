package mobile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Deployable {
	protected Image image;
	private ImageView view;
	protected double x;
	protected double y;
	double dx;
    double dy;
    double r;
    double health;
    double speed;
    double damage;
	boolean removable = false;
	boolean canMove = true;
	double w;
    double h;
	
	public Deployable(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double damage){
		
		this.x = x;
        this.y = y;
		this.dx = dx;
        this.dy = dy;
        this.r = r;
        this.health = health;
        this.damage = damage;
        this.image = image;
        this.view = new ImageView(image);
        this.view.relocate(x, y);
        this.view.setRotate(r);
        this.w = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = image.getHeight(); // imageView.getBoundsInParent().getHeight();
	}
	public void move() {

        if( !canMove)
            return;

        x += dx;
        y += dy;
        //r += dr;

    }
	public void move(double m){
	
	}
	public void turn(double t){
		
	}

	public Image getImage() {
		return image;
	}

//	public void setImage(Image image) {
//		this.image = image;
//	}

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
	public abstract void checkRemovability();
		
		
	

}
