package jdbcalunos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlunoBoundary extends Application {
	private TextField raTextField;
	private TextField nomeTextField; 
	private TextField idadeTextField;
	private DatePicker nascimentoDatePicker;
	private TableView<Aluno> tableView;
	
	private AlunoControl control = new AlunoControl();
	
	@Override
	public void start(Stage stage) throws Exception { 
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(16));
		
		raTextField = new TextField();
		nomeTextField = new TextField();
		idadeTextField = new TextField();
		nascimentoDatePicker = new DatePicker();
		
		Button adicionarButton = new Button("Adicionar");
		adicionarButton.setOnAction(event -> {
			control.adicionar(boundaryToEnity());
		});
		
		Button atualizarButton = new Button("Atualizar");
		atualizarButton.setOnAction(event -> {
			Aluno aluno = tableView.getSelectionModel().getSelectedItem();
			
			if (aluno == null) {
				return;
			}
			
			control.atualizarAluno(boundaryToEnity(), aluno.getRa());
		});
		
		Button pesquisarButton = new Button("Pesquisar por RA");
		pesquisarButton.setOnAction(event -> { 
			Aluno aluno = control.selecionarAlunoPorRa(raTextField.getText());
			entityToBoundary(aluno);
		});
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(8));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		BorderPane.setAlignment(gridPane, Pos.CENTER);
		gridPane.addRow(0, new Label("RA"), raTextField);
		gridPane.addRow(1, new Label("Nome"), nomeTextField);
		gridPane.addRow(2, new Label("Idade"), idadeTextField);
		gridPane.addRow(3, new Label("Nascimento"), nascimentoDatePicker);
		gridPane.add(new HBox(16, pesquisarButton, atualizarButton, adicionarButton), 1, 4);
		root.setTop(gridPane);
		
		tableView = new TableView<Aluno>();
		
		TableColumn<Aluno, String> raColumn = new TableColumn<>("RA");
		raColumn.setCellValueFactory(new PropertyValueFactory<>("ra"));
		
		TableColumn<Aluno, String> nomeColumn = new TableColumn<>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Aluno, String> idadeColumn = new TableColumn<>("Idade");
		idadeColumn.setCellValueFactory(new PropertyValueFactory<>("idade"));
		
		TableColumn<Aluno, String> nascimentoColumn = new TableColumn<>("Nascimento");
		nascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
		
		tableView.getColumns().addAll(raColumn, nomeColumn, 
				idadeColumn, nascimentoColumn);
		
		tableView.widthProperty().addListener((observale, oldValue, newValue) -> {
			double width = newValue.doubleValue();
			raColumn.setPrefWidth(width * 0.20);
			nomeColumn.setPrefWidth(width * 0.45);
			idadeColumn.setPrefWidth(width * 0.15);
			nascimentoColumn.setPrefWidth(width * 0.20);
		});
		
		tableView.getSelectionModel().selectedItemProperty()
			.addListener((observable, oldValue, newValue) -> {
				entityToBoundary(newValue);
			});
		
		tableView.setItems(control.getAlunos());
		MenuItem deletarMenuItem = new MenuItem("Deletar");
		deletarMenuItem.setOnAction(event -> 
			control.deletarAluno(tableView.getSelectionModel().getSelectedItem())
		);
		tableView.setContextMenu(new ContextMenu(deletarMenuItem));
		
		root.setBottom(tableView);
		
		Scene scene = new Scene(root, 800, 500);
		stage.setTitle("Registro de Alunos");
		stage.setScene(scene);
		stage.show();
	}

	private Aluno boundaryToEnity() {
		return new Aluno(raTextField.getText(), nomeTextField.getText(),
				Integer.parseInt(idadeTextField.getText()),
				nascimentoDatePicker.getEditor().getText());
	} 
	
	public void entityToBoundary(Aluno aluno) { 
		if (aluno == null) {
			return;
		}
		
		raTextField.setText(aluno.getRa());
		nomeTextField.setText(aluno.getNome());
		idadeTextField.setText(String.valueOf(aluno.getIdade()));
		nascimentoDatePicker.getEditor().setText(aluno.getNascimento());
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
}
