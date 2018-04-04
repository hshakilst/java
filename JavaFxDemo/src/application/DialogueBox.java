package application;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class DialogueBox {
	
	public static void error(Exception e){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText("Something went wrong!");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exception = sw.toString();
		TextArea text = new TextArea(exception);
		text.setEditable(false);
		text.setWrapText(true);
		text.setMaxWidth(Double.MAX_VALUE);
		text.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(text, Priority.ALWAYS);
		GridPane.setHgrow(text, Priority.ALWAYS);
		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		Label lbl = new Label("The exception stack trace was:");
		expContent.add(lbl, 0, 0);
		expContent.add(text, 0, 1);
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}
}
