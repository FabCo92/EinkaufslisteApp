package EinkauflisteApp;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

	private TableView<Eintraege> table = new TableView<Eintraege>();

	private Eintragsverwaltung ev = Eintragsverwaltung.getInstance();
	// Testeinträge
	@SuppressWarnings("unused")
	private Eintraege ein1 = ev.eintragHinzu("Eier", "2 Packungen");
	@SuppressWarnings("unused")
	private Eintraege ein2 = ev.eintragHinzu("Brot", "1 Packung");

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Einkaufsliste");
		stage.setWidth(300);
		stage.setHeight(500);

		table.setEditable(true);

		ArrayList<Eintraege> listEintraege = ev.getEintraege();
		ObservableList<Eintraege> data = FXCollections.observableArrayList(listEintraege);

		TableColumn<Eintraege, String> prodCol = new TableColumn<Eintraege, String>("Produkt");
		prodCol.setCellValueFactory(new PropertyValueFactory<>("bezeichner"));

		TableColumn<Eintraege, Integer> nrCol = new TableColumn<Eintraege, Integer>("Menge");
		nrCol.setCellValueFactory(new PropertyValueFactory<>("menge"));

		table.setItems(data);
		table.getColumns().add(prodCol);
		table.getColumns().add(nrCol);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		final HBox hbox = new HBox();
		vbox.getChildren().add(hbox);
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		final TextField addProd = new TextField();
		addProd.setPromptText("Produkt");
		addProd.setMaxWidth(prodCol.getPrefWidth());
		final TextField addNr = new TextField();
		addNr.setMaxWidth(nrCol.getPrefWidth());
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
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
