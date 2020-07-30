package EinkauflisteApp;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

	private TableView<Eintraege> table = new TableView<Eintraege>();

	private Eintragsverwaltung ev = Eintragsverwaltung.getInstance();

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Einkaufsliste");
		stage.setWidth(350);
		stage.setHeight(550);

		final HBox buttonBox = new HBox();
		buttonBox.setSpacing(110);
		final VBox vbox = new VBox();
		final HBox hbox = new HBox();
		
		ArrayList<Eintraege> listEintraege = ev.getEintraege();
		ObservableList<Eintraege> data = FXCollections.observableArrayList(listEintraege);

		table.setEditable(true);
		
		TableColumn<Eintraege, String> prodCol = new TableColumn<Eintraege, String>("Produkt");
		prodCol.setEditable(true);
		prodCol.setMinWidth(100);
		prodCol.setCellFactory(TextFieldTableCell.forTableColumn());
		prodCol.setCellValueFactory(new PropertyValueFactory<>("bezeichner"));
		 
		TableColumn<Eintraege, String> nrCol = new TableColumn<Eintraege, String>("Menge");
		nrCol.setEditable(true);
		nrCol.setMinWidth(100);
		nrCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nrCol.setCellValueFactory(new PropertyValueFactory<>("menge"));

		table.setItems(data);
		table.getColumns().add(prodCol);
		table.getColumns().add(nrCol);

		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		
		
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		final TextField addProd = new TextField();
		addProd.setPromptText("Produkt");
		addProd.setPrefWidth(150);
		final TextField addNr = new TextField();
		addNr.setPrefWidth(130);
		addNr.setPromptText("Menge");

		final Button addButton = new Button("Add");
		addButton.setOnAction(e -> {

			try {
				Eintraege ein = ev.eintragHinzu(addProd.getText(), addNr.getText());
				data.add(ein);
				addProd.clear();
				addNr.clear();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

		//final Button loadButton = new Button("Liste laden");
		//loadButton.setOnAction(e -> {
		//});
		
		
		final Button saveButton = new Button("Liste speichern");
		saveButton.setPadding(new Insets(5));
		saveButton.setOnAction(e -> {
			ev.speichern();
			Alert bestaetigung = new Alert(AlertType.INFORMATION);
			bestaetigung.setTitle("Information");
			bestaetigung.setContentText("Liste erfolgreich gespeichert!");
			bestaetigung.showAndWait();
		});
		final Button clearButton = new Button("Liste löschen");
		clearButton.setOnAction(e -> {
			ev.loescheEintraege();
			data.clear();
		});
		
		vbox.getChildren().add(buttonBox);
		buttonBox.getChildren().addAll(saveButton,clearButton);
		vbox.getChildren().add(table);
		vbox.getChildren().add(hbox);
		
		
		
		hbox.getChildren().addAll(addProd, addNr, addButton);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
