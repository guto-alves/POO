package fxeventos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InterfaceGraficaSimples extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		
		Label label = new Label("Olá ... !!!");
		BorderPane.setAlignment(label, Pos.CENTER_LEFT);
		root.setCenter(label);
		
		Button button = new Button("Sair");
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(event -> System.exit(0));
		root.setBottom(button);
		
		Scene scene = new Scene(root, 250, 200);
		stage.setTitle("Janela de Teste");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
