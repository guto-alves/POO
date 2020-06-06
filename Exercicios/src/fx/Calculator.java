package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Calculator extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefWidth(250);
		root.setPadding(new Insets(8));

		TextField textField = new TextField("0");
		textField.maxWidth(Double.MAX_VALUE);
		textField.setPrefHeight(42);
		textField.setEditable(false);
		HBox.setHgrow(textField, Priority.ALWAYS);
		Button delButton = new Button("CE");
		delButton.setPrefHeight(42);

		HBox hBox = new HBox();
		hBox.setSpacing(8);
		hBox.getChildren().addAll(textField, delButton);
		root.setTop(hBox);

		GridPane buttonsGridPane = new GridPane();
		BorderPane.setMargin(buttonsGridPane, new Insets(16, 0, 0, 0));

		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setFillWidth(true);
		columnConstraints.setHgrow(Priority.SOMETIMES);
		buttonsGridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints,
				columnConstraints);

		Button button1 = new Button("1");
		button1.setPrefHeight(42);
		button1.setMaxWidth(Double.MAX_VALUE);
		Button button2 = new Button("2");
		button2.setPrefHeight(42);
		button2.setMaxWidth(Double.MAX_VALUE);
		Button button3 = new Button("3");
		button3.setPrefHeight(42);
		button3.setMaxWidth(Double.MAX_VALUE);
		Button addButton = new Button("+");
		addButton.setPrefHeight(42);
		addButton.setMaxWidth(Double.MAX_VALUE);

		Button button4 = new Button("4");
		button4.setMaxWidth(Double.MAX_VALUE);
		button4.setPrefHeight(42);
		Button button5 = new Button("5");
		button5.setMaxWidth(Double.MAX_VALUE);
		button5.setPrefHeight(42);
		Button button6 = new Button("6");
		button6.setMaxWidth(Double.MAX_VALUE);
		button6.setPrefHeight(42);
		Button subButton = new Button("-");
		subButton.setMaxWidth(Double.MAX_VALUE);
		subButton.setPrefHeight(42);

		Button button7 = new Button("7");
		button7.setMaxWidth(Double.MAX_VALUE);
		button7.setPrefHeight(42);
		Button button8 = new Button("8");
		button8.setMaxWidth(Double.MAX_VALUE);
		button8.setPrefHeight(42);
		Button button9 = new Button("9");
		button9.setMaxWidth(Double.MAX_VALUE);
		button9.setPrefHeight(42);
		Button mulButton = new Button("*");
		mulButton.setMaxWidth(Double.MAX_VALUE);
		mulButton.setPrefHeight(42);

		Button dotButton = new Button(".");
		dotButton.setMaxWidth(Double.MAX_VALUE);
		dotButton.setPrefHeight(42);
		Button button0 = new Button("0");
		button0.setMaxWidth(Double.MAX_VALUE);
		button0.setPrefHeight(42);
		Button resultButton = new Button("=");
		resultButton.setMaxWidth(Double.MAX_VALUE);
		resultButton.setPrefHeight(42);
		Button divButton = new Button("/");
		divButton.setMaxWidth(Double.MAX_VALUE);
		divButton.setPrefHeight(42);

		buttonsGridPane.addRow(0, button1, button2, button3, addButton);
		buttonsGridPane.addRow(1, button4, button5, button6, subButton);
		buttonsGridPane.addRow(2, button7, button8, button9, mulButton);
		buttonsGridPane.addRow(3, dotButton, button0, resultButton, divButton);

		root.setCenter(buttonsGridPane);

		Scene scene = new Scene(root);
		stage.setTitle("Calculator");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
