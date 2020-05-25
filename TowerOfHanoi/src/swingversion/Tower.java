package swingversion;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class Tower extends JPanel implements MouseListener, MouseMotionListener {
	private Stack<Rectangle2D.Double> pegs[] = new Stack[3];
	private Stack<Color> diskColor[] = new Stack[3];

	private Rectangle2D.Double topPeg = null;
	private Color topColor = null;
	private double ax, ay, w, h;
	private boolean draggable = false;

	public Tower() {
		init(4);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void init(int disks) {
		Color colors[] = { Color.YELLOW, Color.RED, Color.BLUE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.GREEN,
				Color.ORANGE, Color.LIGHT_GRAY };

		pegs[0] = new Stack<Rectangle2D.Double>();
		pegs[1] = new Stack<Rectangle2D.Double>();
		pegs[2] = new Stack<Rectangle2D.Double>();

		diskColor[0] = new Stack<Color>();
		diskColor[1] = new Stack<Color>();
		diskColor[2] = new Stack<Color>();

		for (int i = 0; i < disks; i++) {
			Rectangle2D.Double rectangle = new Rectangle2D.Double();

			double x = getWidth() / 6;
			x = (x == 0) ? 109 : x;

			double rectangleWidth = disks * 25 - 20 * i;
			double rectangleHeight = 20;

			rectangle.setFrame(x - rectangleWidth / 2, 178.5 - i * 20, rectangleWidth, rectangleHeight);

			pegs[0].push(rectangle);
			diskColor[0].push(colors[i]);
		}

		topPeg = null;
		topColor = null;
		ax = 0.0;
		ay = 0.0;
		w = 0.0;
		h = 0.0;
		draggable = false;
		repaint();
	}

	public void mouseClicked(MouseEvent ev) {
	}

	public void mousePressed(MouseEvent ev) {
		Point point = ev.getPoint();
		int n = currentTower(point);
		if (!pegs[n].empty()) {
			topPeg = pegs[n].peek();
			if (topPeg.contains(point)) {
				topPeg = pegs[n].pop();
				topColor = diskColor[n].pop();
				ax = topPeg.getX();
				ay = topPeg.getY();
				w = point.getX() - ax;
				h = point.getY() - ay;
				draggable = true;
			} else {
				topPeg = null;
				topColor = Color.black;
				draggable = false;
			}
		}
	}

	public void mouseReleased(MouseEvent ev) {
		if (topPeg != null && draggable == true) {
			int tower = currentTower(ev.getPoint());
			double x, y;
			if (!pegs[tower].empty()) {
				if (pegs[tower].peek().getWidth() > topPeg.getWidth())
					y = pegs[tower].peek().getY() - 20;
				else {
					JOptionPane.showMessageDialog(this, "Wrong Move", "Tower Of Hanoi", JOptionPane.ERROR_MESSAGE);
					tower = currentTower(new Point((int) ax, (int) ay));
					if (!pegs[tower].empty()) {
						y = pegs[tower].peek().getY() - 20;
					} else {
						y = getHeight() - 40;
					}
				}
			} else
				y = getHeight() - 40;

			x = (int) (getWidth() / 6 + (getWidth() / 3) * tower - topPeg.getWidth() / 2);
			topPeg.setFrame(x, y, topPeg.getWidth(), topPeg.getHeight());
			pegs[tower].push(topPeg);
			diskColor[tower].push(topColor);

			topPeg = null;
			topColor = Color.black;
			draggable = false;
			repaint();
		}
	}

	public void mouseEntered(MouseEvent ev) {
	}

	public void mouseExited(MouseEvent ev) {
	}

	public void mouseMoved(MouseEvent ev) {
	}

	public void mouseDragged(MouseEvent ev) {
		int cx = ev.getX();
		int cy = ev.getY();
		if (topPeg != null && draggable == true) {
			topPeg.setFrame(cx - w, cy - h, topPeg.getWidth(), topPeg.getHeight());
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		int holder_x = getWidth() / 6;
		int holder_y1 = getHeight() - 10 * 20;
		int holder_y2 = getHeight() - 20;

		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(holder_x, holder_y1, holder_x, holder_y2);
		g2d.drawLine(3 * holder_x, holder_y1, 3 * holder_x, holder_y2);
		g2d.drawLine(5 * holder_x, holder_y1, 5 * holder_x, holder_y2);
		g2d.drawLine(0, holder_y2, getWidth(), holder_y2);

		g2d.setStroke(new BasicStroke(1));

		g2d.setColor(topColor);

		if (draggable == true && topPeg != null) {
			g2d.fill(topPeg);
		}

		drawTower(g2d, 0);
		drawTower(g2d, 1);
		drawTower(g2d, 2);
	}

	private void drawTower(Graphics2D g2d, int pegNumber) {
		if (!pegs[pegNumber].empty()) {
			for (int i = 0; i < pegs[pegNumber].size(); i++) {
				g2d.setColor(diskColor[pegNumber].get(i));
				g2d.fill(pegs[pegNumber].get(i));
			}
		}
	}

	private int currentTower(Point p) {
		Rectangle2D.Double rA = new Rectangle2D.Double();
		Rectangle2D.Double rB = new Rectangle2D.Double();
		Rectangle2D.Double rC = new Rectangle2D.Double();

		rA.setFrame(0, 0, getWidth() / 3, getHeight());
		rB.setFrame(getWidth() / 3, 0, getWidth() / 3, getHeight());
		rC.setFrame(2 * getWidth() / 3, 0, getWidth() / 3, getHeight());

		if (rA.contains(p))
			return 0;
		else if (rB.contains(p))
			return 1;
		else if (rC.contains(p))
			return 2;
		else
			return -1;
	}
}
