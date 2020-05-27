package javafxversion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Disk {
	public static final Color COLORS[] = { Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE,
			Color.LIGHTSKYBLUE, Color.AQUA, Color.PURPLE, Color.MEDIUMVIOLETRED };
	
	public static final int HEIGHT = 20;
	
	private Rectangle rectangle;

	public Disk(Rectangle rectangle, Paint fill) {
		this.rectangle = rectangle;
		this.rectangle.setFill(fill);
	}

	public void setLocation(double x, double y) {
		rectangle.setX(x);
		rectangle.setY(y);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public double getX() {
		return rectangle.getX();
	}

	public double getY() {
		return rectangle.getY();
	}

	public double getWidth() {
		return rectangle.getWidth();
	}

	public double getHeight() {
		return rectangle.getHeight();
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(rectangle.getFill());
		gc.fillRoundRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), 
				rectangle.getHeight(), 20, 20);
	}

}
