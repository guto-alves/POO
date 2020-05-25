package javafxversion;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TowerOfHanoi extends Application {

	@Override
	public <T> void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();

		Pane wrapperPane = new Pane();
		Canvas canvas = new Canvas(660, 220);
		wrapperPane.getChildren().add(canvas);

		TowerOfHanoiController controller = new TowerOfHanoiController(canvas);

		MenuBar menuBar = new MenuBar();

		Menu gameMenu = new Menu("Game");

		MenuItem newGameItem = new MenuItem("New Game");
		newGameItem.setOnAction(event -> controller.newGame());
		MenuItem resetGameItem = new MenuItem("Reset Game");
		resetGameItem.setOnAction(event -> controller.resetGame());

		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(event -> System.exit(0));

		gameMenu.getItems().addAll(newGameItem, resetGameItem, new SeparatorMenuItem(), exitItem);

		Menu helpMenu = new Menu("Help");
		helpMenu.getItems().add(new MenuItem("About..."));

		menuBar.getMenus().addAll(gameMenu, helpMenu);

		root.setTop(menuBar);
		root.setCenter(wrapperPane);

		Scene scene = new Scene(root);

		stage.setTitle("Tower of Hanoi");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
