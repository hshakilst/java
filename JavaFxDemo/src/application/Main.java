package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Parent;


public class Main extends Application {
	@FXML
	private TextField textfield;
	@FXML
	private Button button;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root,312,175);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Demo App");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onSubmit(ActionEvent e){
		if(textfield.getText().length() < 1){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Enter your name!");
			alert.showAndWait();
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Name");
			alert.setContentText("Hello " + textfield.getText() + "!");
			alert.showAndWait();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
