package javafxversion;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

public class TowerOfHanoiController {
	private Canvas canvas;

	private Peg[] pegs = new Peg[3];

	private Disk diskSelected;
	private int towerSelected;
	private double w, h;
	private boolean dragging = false;

	private int numberOfDisks = 2;

	private int moves;

	public TowerOfHanoiController(Canvas canvas) {
		this.canvas = canvas;
		initialize();
		init(numberOfDisks);
		draw();
	}

	private void initialize() {
		canvas.setOnMousePressed(event -> {
			Point2D point = new Point2D(event.getX(), event.getY());

			int tower = currentTower(point);

			Stack<Disk> disks = pegs[tower].getDisks();

			if (!disks.empty()) {
				diskSelected = disks.peek();

				if (diskSelected.getRectangle().contains(point)) {
					disks.pop();

					w = point.getX() - diskSelected.getX();
					h = point.getY() - diskSelected.getY();

					towerSelected = tower;
					dragging = true;
				} else {
					diskSelected = null;
					dragging = false;
				}
			}
		});

		canvas.setOnMouseDragged(event -> {
			if (diskSelected != null && dragging == true) {
				diskSelected.setLocation(event.getX() - w, event.getY() - h);
				draw();
			}
		});

		canvas.setOnMouseReleased(event -> {
			if (diskSelected != null && dragging == true) {
				int currentTower = currentTower(new Point2D(event.getX(), event.getY()));

				if (towerSelected == currentTower || currentTower == -1) {
					currentTower = towerSelected;
					moves--;
				}

				double x, y;

				Stack<Disk> disks = pegs[currentTower].getDisks();

				if (disks.empty()) {
					y = canvas.getHeight() - 44.8;
				} else if (diskSelected.getWidth() < disks.peek().getWidth()) {
					y = disks.peek().getY() - 20;
				} else {
					moves--;

					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Tower of Hanoi");
					alert.setContentText("Wrong Move!");
					alert.show();

					currentTower = towerSelected;
					disks = pegs[currentTower].getDisks();

					if (!disks.empty()) {
						y = disks.peek().getY() - 20;
					} else {
						y = canvas.getHeight() - 44.8;
					}
				}

				x = (int) (canvas.getWidth() / 6 + (canvas.getWidth() / 3) * currentTower
						- diskSelected.getWidth() / 2);

				diskSelected.setLocation(x, y);
				disks.push(diskSelected);

				moves++;
				diskSelected = null;
				dragging = false;
				draw();

				if (currentTower == 2 && disks.size() == numberOfDisks) {
					Alert alert = new Alert(AlertType.NONE);
					alert.setTitle("Tower of Hanoi");

					if (moves == Math.pow(2, numberOfDisks) - 1) {
						alert.setHeaderText("Perfect!");
						alert.setContentText("Moves: " + moves + " (optimal solution)");
					} else {
						alert.setHeaderText("Wel Done!");
						alert.setContentText("Moves: " + moves);
					}

					ButtonType newGameButtonType = new ButtonType("New Game");
					ButtonType resetGameButtonType = new ButtonType("Reset Game");
					alert.getButtonTypes().setAll(newGameButtonType, resetGameButtonType);

					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == newGameButtonType) {
						newGame();
					} else {
						resetGame();
					}
				}
			}
		});
	}

	private int currentTower(Point2D point) {
		Rectangle tower1 = new Rectangle(0, 0, canvas.getWidth() / 3, canvas.getHeight());
		Rectangle tower2 = new Rectangle(canvas.getWidth() / 3, 0, canvas.getWidth() / 3, canvas.getHeight());
		Rectangle tower3 = new Rectangle(2 * canvas.getWidth() / 3, 0, canvas.getWidth() / 3, canvas.getHeight());

		if (tower1.contains(point)) {
			return 0;
		} else if (tower2.contains(point)) {
			return 1;
		} else if (tower3.contains(point)) {
			return 2;
		}

		return -1;
	}

	public void newGame() {
		ChoiceDialog<Integer> choiceDialog = new ChoiceDialog<Integer>(numberOfDisks,
				List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
		choiceDialog.setTitle("Tower of Hanoi");
		choiceDialog.setHeaderText(null);
		choiceDialog.setContentText("Number of Disks:");
		Optional<Integer> result = choiceDialog.showAndWait();

		if (result.isPresent()) {
			numberOfDisks = result.get();
			resetGame();
		}
	}

	public void resetGame() {
		moves = 0;
		init(numberOfDisks);
		draw();
	}

	private void init(int disks) {
		Color colors[] = { Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.ORANGE, 
				Color.LIGHTSKYBLUE, Color.AQUA, Color.PURPLE, Color.MEDIUMVIOLETRED};

		pegs[0] = new Peg();
		pegs[1] = new Peg();
		pegs[2] = new Peg();

		for (int i = 0; i < disks; i++) {
			double x = canvas.getWidth() / 6;
			x = (x == 0) ? 109 : x;

			double rectangleWidth = disks * 25 - 20 * i;
			double rectangleHeight = 20;

			Rectangle rectangle = new Rectangle(x - rectangleWidth / 2, (canvas.getHeight() - 44.8) - i * 20,
					rectangleWidth, rectangleHeight);

			pegs[0].getDisks().push(new Disk(rectangle, colors[i]));
		}

		diskSelected = null;
		w = 0.0;
		h = 0.0;
		dragging = false;
	}

	private void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		int xHolder = (int) (canvas.getWidth() / 6);
		int y1Holder = (int) (canvas.getHeight() - 220);
		int y2Holder = (int) (canvas.getHeight());

		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setLineWidth(7);
		gc.setStroke(new LinearGradient(1, 0, 1, 1, true,
				CycleMethod.REFLECT, new Stop(0, Color.rgb(222, 233, 236)), new Stop(1, Color.rgb(93, 162, 176))));

		gc.strokeLine(xHolder, y1Holder, xHolder, y2Holder);
		gc.strokeLine(3 * xHolder, y1Holder, 3 * xHolder, y2Holder);
		gc.strokeLine(5 * xHolder, y1Holder, 5 * xHolder, y2Holder);
		
		gc.setLineWidth(50);
		gc.setStroke(new LinearGradient(canvas.getWidth(), 50, canvas.getWidth(), 50 + 100, false,
				CycleMethod.REFLECT, new Stop(0, Color.rgb(250, 236, 210)), new Stop(1, Color.rgb(231, 215, 179))));
		gc.strokeLine(0, y2Holder, canvas.getWidth(), y2Holder);

		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Serif", 18));
		gc.fillText("Moves: " + moves, 15, 20);

		for (int tower = 0; tower < 3; tower++) {
			Peg peg = pegs[tower];

			if (!peg.getDisks().empty()) {
				int totalDisks = peg.getDisks().size();

				for (int i = 0; i < totalDisks; i++) {
					peg.getDisks().get(i).draw(gc);
				}
			}
		}
		
		if (dragging == true && diskSelected != null) {
			diskSelected.draw(gc);
		}
	}

}
