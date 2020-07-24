package EinkauflisteApp;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainView extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception  {
		 primaryStage.setTitle("Meine Einkaufsliste");
	        Button btn = new Button();
	        btn.setText("Neuer Eintrag");
	        btn.setOnAction(e -> {
	        	System.out.println("Neuer Eintrag! bla");
	        });
	        
	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	        primaryStage.setScene(new Scene(root, 300, 250));
	        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}
