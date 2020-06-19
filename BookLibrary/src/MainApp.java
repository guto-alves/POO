import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AnotationsView;
import view.AuthorView;
import view.PublisherView;
import view.EmployeeView;
import view.LoanView;
import view.LoginView;
import view.BookView;
import view.CustomerView;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		 
		MenuItem newTextFile = new MenuItem("Novo Arquivo");
		newTextFile.setOnAction(event -> {
			
		});
		MenuItem openTextFile = new MenuItem("Abrir Arquivo");
		MenuItem exitMenuItem = new MenuItem("Sair");
		exitMenuItem.setOnAction(event -> {
			System.exit(0);
		});
		Menu fileMenu = new Menu("Arquivo");
		fileMenu.getItems().addAll(newTextFile, openTextFile, 
				new SeparatorMenuItem(), exitMenuItem);
		
		Menu settingsMenu = new Menu("Configurações");
		Menu helpMenu = new Menu("Ajuda");
		MenuBar menuBar = new MenuBar(fileMenu, settingsMenu, helpMenu);
		
		VBox vBox = new VBox(8);
		TitledPane booksTitledPane = new TitledPane("Funcionários", new Button(""));
		vBox.getChildren().add(booksTitledPane);
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);

		Tab livrosTab = new Tab("Livros");
		livrosTab.setContent(new BookView().getRoot());

		Tab employeesTab = new Tab("Funcionários");
		employeesTab.setContent(new EmployeeView().getRoot());

		Tab authorsTab = new Tab("Autores");
		authorsTab.setContent(new AuthorView().getRoot());

		Tab publishers = new Tab("Editoras");
		publishers.setContent(new PublisherView().getRoot());

		Tab customersTab = new Tab("Clientes");
		customersTab.setContent(new CustomerView().getRoot());

		Tab loanTab = new Tab("Empréstimo");
		loanTab.setContent(new LoanView().getRoot());
		
		Tab anotationsTab = new Tab("Anotações");
		anotationsTab.setContent(new AnotationsView().getRoot());

		tabPane.getTabs().addAll(livrosTab, employeesTab, authorsTab, 
				publishers, customersTab, loanTab, anotationsTab);
		
		SplitPane splitPane = new SplitPane(vBox, tabPane);
		splitPane.setDividerPositions(0.10, 0.90);
		
		root.setTop(menuBar);
		root.setCenter(splitPane);

		Scene scene = new Scene(root); 
		stage.setMaximized(true);
		stage.setTitle("Sistema de Gerenciamento de Biblioteca");
		stage.setScene(scene);
		stage.show();
		
//		new LoginView();
	} 

	public static void main(String[] args) {
		launch(args);
	}

}
