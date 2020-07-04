package fxeventos;

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
	private static final String[] BUTTON_TEXTS = 
		{ "1", "2", "3", "+",
		"4", "5", "6", "-",
		"7", "8", "9", "*", 
		".", "0", "=", "/" };
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefWidth(250);
		root.setPadding(new Insets(8));

		TextField textField = new TextField();
		textField.maxWidth(Double.MAX_VALUE);
		textField.setPrefHeight(42);
		textField.setEditable(false);
		HBox.setHgrow(textField, Priority.ALWAYS);
		Button delButton = new Button("CE");
		delButton.setOnAction(event -> textField.setText(""));
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

		Button[] buttons = new Button[16];

		int row = 0;

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(BUTTON_TEXTS[i]);
			buttons[i].setPrefHeight(42);
			buttons[i].setMaxWidth(Double.MAX_VALUE);
			buttons[i].setOnAction(event -> {
				Button button = (Button) event.getSource();
				String input = button.getText();

				textField.setText(textField.getText() + input);
			});

			if (i % 4 == 0) {
				row++;
			}

			buttonsGridPane.addRow(row, buttons[i]);
		}

		root.setCenter(buttonsGridPane);

		Scene scene = new Scene(root);
		stage.setTitle("Calculator");
		stage.setScene(scene);
		stage.setMinWidth(250);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
