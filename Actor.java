import org.newdawn.slick.geom.*;

public class Actor {
	protected Shape shape;
	protected float xPos=0, yPos=0, xVel = 0, yVel = 0, mass = 10;
	protected boolean falling = true;
	
	public Actor(Shape s) {
		shape = s;
		xPos = shape.getX();
		yPos = shape.getY();
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public double getX() {
		return xPos;
	}
	public double getY() {
		return yPos;
	}
	public void forces() {
		if(xPos <= 0) {
			xPos = 0;
			xVel = 0;
		}
		if(xPos >= 750) {
			xPos = 750;
			xVel = 0;
		}
		if(yPos >= 550) { // normal
			yVel = 0;
			yPos = 550;
			falling = false;
		}
		else {
			yVel += (9.8 * mass) / 75; // Gravity
		}
	}
	public void move(Vector2f v) {
		forces();
		if(!falling) {
			yVel -= v.getY() / 75;
			yPos -= v.getY();
		}
		yPos += yVel;
		xPos += v.getX() + xVel;
		update();
	}
	private void update() {
		shape.setX(xPos);
		shape.setY(yPos);
	}
	
}
