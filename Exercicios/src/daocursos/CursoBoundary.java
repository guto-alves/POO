package daocursos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CursoBoundary extends Application {
	private TextField nomeTextField;
	private TextArea descricaoTextArea;
	private CheckBox ativoCheckBox;
	private DatePicker inicioDatePicker;
	private DatePicker terminoDatePicker;
	
	private CursoControl control = new CursoControl();
	
	@Override
	public void start(Stage stage) throws Exception { 
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(16));
		
		nomeTextField = new TextField();
		descricaoTextArea = new TextArea();
		descricaoTextArea.setPrefHeight(50);
		descricaoTextArea.setWrapText(true);
		ativoCheckBox = new CheckBox();
		inicioDatePicker = new DatePicker();
		terminoDatePicker = new DatePicker();
		
		Button adicionarButton = new Button("Adicionar");
		adicionarButton.setOnAction(event -> {
			control.adicionar(boundaryToEnity());
		});
		
		Button pesquisarButton = new Button("Pesquisar por Nome");
		pesquisarButton.setOnAction(event -> {
			control.pesquisarPorNome(nomeTextField.getText());
		});
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(8));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		BorderPane.setAlignment(gridPane, Pos.CENTER);
		gridPane.addRow(0, new Label("Nome"), nomeTextField);
		gridPane.addRow(1, new Label("Descrição"), descricaoTextArea);
		gridPane.addRow(2, new Label("Ativo"), ativoCheckBox);
		gridPane.addRow(3, new Label("Início"), inicioDatePicker);
		gridPane.addRow(4, new Label("Término"), terminoDatePicker);
		gridPane.add(new HBox(16, pesquisarButton, adicionarButton), 1, 5);
		root.setTop(gridPane);
		
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
			inicioColumn.setPrefWidth(width * 0.15);
			terminoColumn.setPrefWidth(width * 0.15);
		});
		
		tableView.setItems(control.getCursos());
		
		root.setBottom(tableView);
		
		Scene scene = new Scene(root, 800, 500);
		stage.setTitle("Cursos");
		stage.setScene(scene);
		stage.show();
	}

	private Curso boundaryToEnity() {
		return new Curso(nomeTextField.getText(), 
				descricaoTextArea.getText(), ativoCheckBox.isSelected(), 
				inicioDatePicker.getEditor().getText(), 
				terminoDatePicker.getEditor().getText());
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
}
