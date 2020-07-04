package exerciciocomplementar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CursoBoundary extends Application {
	private TextField nomeTextField;
	private TextArea descricaoTextArea;
	private CheckBox ativoCheckBox;
	private DatePicker inicioTextField;
	private DatePicker terminoTextField;
	
	private CursoControl control = new CursoControl(); 
	
	@Override
	public void start(Stage stage) throws Exception { 
		nomeTextField = new TextField();
		descricaoTextArea = new TextArea(); 
		descricaoTextArea.setPrefWidth(250);
		descricaoTextArea.setPrefHeight(50);
		descricaoTextArea.setWrapText(true);
		ativoCheckBox = new CheckBox();
		inicioTextField = new DatePicker();
		terminoTextField = new DatePicker();
		
		Button adicionarButton = new Button("Adicionar");
		adicionarButton.setOnAction(event -> {
			control.adicionar(boundaryToEnity());
		});
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(8));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.addRow(0, new Label("Nome"), nomeTextField);
		gridPane.addRow(1, new Label("Descrição"), descricaoTextArea);
		gridPane.addRow(2, new Label("Ativo"), ativoCheckBox);
		gridPane.addRow(3, new Label("Início"), inicioTextField);
		gridPane.addRow(4, new Label("Término"), terminoTextField);
		gridPane.add(adicionarButton, 1, 5);
		
		TableView<Curso> tableView = new TableView<Curso>();
		
		TableColumn<Curso, String> nomeColumn = new TableColumn<>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Curso, String> descricaoColumn = new TableColumn<>("Descrição");
		descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		TableColumn<Curso, String> ativoColumn = new TableColumn<>("Ativo");
		ativoColumn.setCellValueFactory(new PropertyValueFactory<>("ativo"));
		
		TableColumn<Curso, String> inicioColumn = new TableColumn<>("Início");
		inicioColumn.setCellValueFactory(new PropertyValueFactory<>("inicio"));
		
		TableColumn<Curso, String> terminoColumn = new TableColumn<>("Término");
		terminoColumn.setCellValueFactory(new PropertyValueFactory<>("termino"));
		
		tableView.getColumns().addAll(nomeColumn, descricaoColumn, 
				ativoColumn, inicioColumn, terminoColumn);
		
		tableView.widthProperty().addListener((observale, oldValue, newValue) -> {
			double width = newValue.doubleValue();
			nomeColumn.setPrefWidth(width * 0.4);
			descricaoColumn.setPrefWidth(width * 0.15); 
			ativoColumn.setPrefWidth(width * 0.15);
			inicioColumn.setPrefWidth(width * 0.14);
			terminoColumn.setPrefWidth(width * 0.16); 
		});
		
		tableView.setItems(control.getCursos());
		
		MenuItem removeMenuItem = new MenuItem("Deletar");
		removeMenuItem.setOnAction(event -> {
			control.deletar(tableView.getSelectionModel().getSelectedItem());
		});
		tableView.setContextMenu(new ContextMenu(removeMenuItem));
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(16));
		borderPane.setCenter(tableView);
		
		Scene scene = new Scene(
				new SplitPane(borderPane, gridPane), 850, 500);
		stage.setTitle("Cursos");
		stage.setScene(scene);
		stage.show();
	}

	private Curso boundaryToEnity() {
		return new Curso(nomeTextField.getText(), 
				descricaoTextArea.getText(), ativoCheckBox.isSelected(), 
				inicioTextField.getEditor().getText(), 
				terminoTextField.getEditor().getText());
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
}
