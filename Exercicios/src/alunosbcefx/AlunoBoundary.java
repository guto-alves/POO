package alunosbcefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlunoBoundary extends Application { 
	private TextField idTextField;
	private TextField raTextField;
	private TextField nomeTextField;
	private DatePicker nascimentoTextField;

	private AlunoControl control = new AlunoControl();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();

		idTextField = new TextField();
		raTextField = new TextField();
		nomeTextField = new TextField();
		nascimentoTextField = new DatePicker();

		Button adicionarButton = new Button("Adicionar");
		adicionarButton.setOnAction(event -> control.adicionar(boundaryToEnity()));
		
		Button pesquisarButton = new Button("Pesquisar");
		pesquisarButton.setOnAction(event -> {
			Aluno aluno = control.pesquisarPorNome(nomeTextField.getText());
			entityToBoundary(aluno);
		});

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(16));
		gridPane.setPadding(new Insets(8));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		BorderPane.setAlignment(gridPane, Pos.CENTER);
		gridPane.addRow(0, new Label("Id:"), idTextField);
		gridPane.addRow(1, new Label("Ra:"), raTextField);
		gridPane.addRow(2, new Label("Nome:"), nomeTextField);
		gridPane.addRow(3, new Label("Nascimento:"), nascimentoTextField);
		gridPane.add(new HBox(16, pesquisarButton, adicionarButton), 1, 4);
		root.setTop(gridPane);

		TableView<Aluno> tableView = new TableView<Aluno>();

		TableColumn<Aluno, String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Aluno, String> raColumn = new TableColumn<>("RA");
		raColumn.setCellValueFactory(new PropertyValueFactory<>("ra"));

		TableColumn<Aluno, String> nomeColumn = new TableColumn<>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Aluno, String> nascimentoColumn = new TableColumn<>("Nascimento");
		nascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("nascimento"));

		tableView.getColumns().addAll(idColumn, raColumn, nomeColumn, nascimentoColumn);

		tableView.widthProperty().addListener((observale, oldValue, newValue) -> {
			double width = newValue.doubleValue();
			idColumn.setPrefWidth(width * 0.10);
			raColumn.setPrefWidth(width * 0.25);
			nomeColumn.setPrefWidth(width * 0.40);
			nascimentoColumn.setPrefWidth(width * 0.25);
		});

		tableView.setItems(control.getAlunos());

		root.setBottom(tableView);

		Scene scene = new Scene(root, 800, 500);
		stage.setTitle("Alunos");
		stage.setScene(scene);
		stage.show();
	}

	private Aluno boundaryToEnity() {
		return new Aluno(Long.parseLong(idTextField.getText()),
				raTextField.getText(), nomeTextField.getText(),
				nascimentoTextField.getValue());
	}

	public void entityToBoundary(Aluno aluno) {
		idTextField.setText(String.valueOf(aluno.getId()));
		raTextField.setText(aluno.getRa());
		nomeTextField.setText(aluno.getNome());
		nascimentoTextField.setValue(aluno.getNascimento());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
