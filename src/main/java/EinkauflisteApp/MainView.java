package EinkauflisteApp;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

	private TableView<Eintraege> table = new TableView<Eintraege>();
	
	@Override
	 public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Einkaufsliste");
        stage.setWidth(300);
        stage.setHeight(500);
 
        table.setEditable(true);
 
        TableColumn<Eintraege, String> prodCol = new TableColumn<Eintraege, String>("Produkt");
        TableColumn<Eintraege,Integer> nrCol = new TableColumn<Eintraege, Integer>("Anzahl");

        ArrayList<Eintraege> listEintraege = Eintragsverwaltung.getInstance().getEintraege();
        final ObservableList<Eintraege> data = FXCollections.observableArrayList(listEintraege);
        
        table.setItems(data);
        table.getColumns().add(prodCol);
        table.getColumns().add(nrCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        final TextField addProd = new TextField();
        addProd.setPromptText("Produkt");
        addProd.setMaxWidth(prodCol.getPrefWidth());
        final TextField addNr = new TextField();
        addNr.setMaxWidth(nrCol.getPrefWidth());
        addNr.setPromptText("Anzahl");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            
                try {
                	Eintragsverwaltung.getInstance().eintragHinzu(addProd.getText(), Integer.parseInt(addNr.getText()));
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                }
            
        });
        
        vbox.getChildren().addAll(addProd, addNr, addButton);
        stage.setScene(scene);
        stage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}


}
