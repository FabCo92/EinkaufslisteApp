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
	private ObservableList<Eintraege> data;
	private Eintragsverwaltung ev = Eintragsverwaltung.getInstance();
	private HBox hbox;
	private HBox buttonBox;
	private VBox vbox;

	@Override
	public void start(Stage stage) {
		// Das Fenster bauen
		Scene scene = new Scene(new Group());
		stage.setTitle("Einkaufsliste");
		stage.setWidth(350);
		stage.setHeight(550);

		// Rahmen bauen
		buttonBox = new HBox();
		buttonBox.setSpacing(110);
		vbox = new VBox();
		hbox = new HBox();

		// Dateneinträge initialisieren
		ArrayList<Eintraege> listEintraege = ev.getEintraege();
		data = FXCollections.observableArrayList(listEintraege);

		// Elemente der GUI bauen
		buttonLeiste();
		tabelleBauen();
		textFieldLeiste();

		// Schönheitsanpassungen
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));

		// Einzelne Kompenente vertikal anordnen
		vbox.getChildren().add(buttonBox);
		vbox.getChildren().add(table);
		vbox.getChildren().add(hbox);
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		// Zeige das Fenster
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Methode, um die Tabelle zu erstellen 
	 */
	private void tabelleBauen() {
		//damit Tabelle editierbar ist
		table.setEditable(true);

		//Spalte für Produktnamen
		TableColumn<Eintraege, String> prodCol = new TableColumn<Eintraege, String>("Produkt");
		prodCol.setEditable(true);
		prodCol.setMinWidth(100);
		prodCol.setCellFactory(TextFieldTableCell.forTableColumn());
		prodCol.setCellValueFactory(new PropertyValueFactory<>("bezeichner"));
		
		// Ändert den jeweiligen Eintrag mittels Verwaltungsklasse
		prodCol.setOnEditCommit(e -> {
			ev.eintragEdit(table.getSelectionModel().getSelectedItem(), e.getNewValue(),
					table.getSelectionModel().getSelectedItem().getMenge());
		});

		//Spalte für Mengen
		TableColumn<Eintraege, String> nrCol = new TableColumn<Eintraege, String>("Menge");
		nrCol.setEditable(true);
		nrCol.setMinWidth(100);
		nrCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nrCol.setCellValueFactory(new PropertyValueFactory<>("menge"));
		nrCol.setOnEditCommit(e -> {
			ev.eintragEdit(table.getSelectionModel().getSelectedItem(),
					table.getSelectionModel().getSelectedItem().getBezeichner(), e.getNewValue());
		});

		//Füllt Tabelle mit Einträgen
		table.setItems(data);
		
		//Füllt Tabelle mit den Spalten
		table.getColumns().add(prodCol);
		table.getColumns().add(nrCol);

	}

	/**
	 * Methode um das Textfield zu erstellen, mit dem neue Einträge hinzugefügt werden können 
	 */
	private void textFieldLeiste() {
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

		hbox.getChildren().addAll(addProd, addNr, addButton);

	}

	/**
	 * Methode um die beiden Buttons über der Tabelle zu generieren
	 */
	private void buttonLeiste() {
		// Save Button soll Elemente die derzeit in der Liste sind speichern
		final Button saveButton = new Button("Liste speichern");
		
		saveButton.setOnAction(e -> {
			//hier erfolgt Speicheraufruf
			ev.speichern();
			//Gib dann dem Nutzer Bestätigung heraus
			Alert bestaetigung = new Alert(AlertType.INFORMATION);
			bestaetigung.setTitle("Information");
			bestaetigung.setContentText("Liste erfolgreich gespeichert!");
			bestaetigung.showAndWait();
		});
		
		// Clear Button soll alle Elemente löschen
		final Button clearButton = new Button("Liste löschen");
		clearButton.setOnAction(e -> {
			//hier löscht er die Einträge, die im in der Steuerungsklasse drin sind
			ev.loescheEintraege();
			//hier löscht er die in der Tabelle erscheinenden Elemente
			data.clear();
		});
		
		//Button Style
		saveButton.setStyle("-fx-background-color: #00FF00");
		clearButton.setStyle("-fx-background-color: #FF0000");
		
		//Buttons werden hier horizontal angeordnet
		buttonBox.getChildren().addAll(saveButton, clearButton);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
