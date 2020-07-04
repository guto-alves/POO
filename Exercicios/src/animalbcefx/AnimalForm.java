package animalbcefx;

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

public class AnimalForm extends Application {
	private TextField idTextField;
	private TextField pesoTextField;
	private TextField nomeTextField;
	private DatePicker nascimentoTextField;

	private AnimalControl control = new AnimalControl();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();

		idTextField = new TextField();
		pesoTextField = new TextField();
		nomeTextField = new TextField(); 
		nascimentoTextField = new DatePicker();

		Button adicionarButton = new Button("Adicionar");
		adicionarButton.setOnAction(event -> control.adicionar(boundaryToEnity()));
		
		Button pesquisarButton = new Button("Pesquisar");
		pesquisarButton.setOnAction(event -> {
			Animal animal = control.pesquisarPorNome(nomeTextField.getText());
			entityToBoundary(animal);
		});

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(16));
		gridPane.setPadding(new Insets(8));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		BorderPane.setAlignment(gridPane, Pos.CENTER);
		gridPane.addRow(0, new Label("Id:"), idTextField);
		gridPane.addRow(1, new Label("Nome do Animal:"), nomeTextField);
		gridPane.addRow(2, new Label("Data de Nascimento:"), nascimentoTextField);
		gridPane.addRow(3, new Label("Peso:"), pesoTextField);
		gridPane.add(new HBox(16, adicionarButton, pesquisarButton), 1, 4);
		root.setTop(gridPane);

		TableView<Animal> tableView = new TableView<Animal>();

		TableColumn<Animal, String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Animal, String> nomeColumn = new TableColumn<>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Animal, String> nascimentoColumn = new TableColumn<>("Nascimento");
		nascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("nascimento"));

		TableColumn<Animal, String> pesoColumn = new TableColumn<>("Peso");
		pesoColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));

		tableView.getColumns().addAll(idColumn, nomeColumn, 
				nascimentoColumn, pesoColumn);

		tableView.widthProperty().addListener((observale, oldValue, newValue) -> {
			double width = newValue.doubleValue();
			idColumn.setPrefWidth(width * 0.10);
			nomeColumn.setPrefWidth(width * 0.40);
			nascimentoColumn.setPrefWidth(width * 0.25);
			pesoColumn.setPrefWidth(width * 0.25);
		});

		tableView.setItems(control.getAlunos());

		root.setBottom(tableView);

		Scene scene = new Scene(root, 800, 500);
		stage.setTitle("Registro de Animais do Petshop");
		stage.setScene(scene);
		stage.show();
	}

	private Animal boundaryToEnity() {
		return new Animal(
				Long.parseLong(idTextField.getText()), 
				nomeTextField.getText(),
				nascimentoTextField.getValue(),
				Float.parseFloat(pesoTextField.getText()));
	}

	public void entityToBoundary(Animal animal) {
		idTextField.setText(String.valueOf(animal.getId()));
		nomeTextField.setText(animal.getNome());
		nascimentoTextField.setValue(animal.getNascimento());
		pesoTextField.setText(String.valueOf(animal.getPeso()));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
