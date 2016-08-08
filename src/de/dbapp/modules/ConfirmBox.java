package de.dbapp.modules;

import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;
	
	public static void main(String[] args) {

	}

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		

		/*
		 * während dieses Fenster offen ist, können keine Fenster im Hintergrund
		 * bearbeitet werden Dieses Fenster muss erst geschlossen werden, damit
		 * man andere wieder benutzen kann
		 */
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);

		Label l1 = new Label();
		l1.setText(message);
		Button closeButton = new Button();
		closeButton.setText("Close window");
		closeButton.setOnAction(e -> window.close());

		Button yesButton = new Button();
		yesButton.setText("Yes");
		
		Button noButton = new Button();
		noButton.setText("No");
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		
		BorderPane mainMenuLayout = new BorderPane();
		mainMenuLayout.setPadding(new Insets(20, 20, 20, 20));
		
		HBox layout = new HBox(20);
		layout.getChildren().addAll(l1);
		HBox layout2 = new HBox();
		layout2.getChildren().addAll(yesButton, noButton);
		mainMenuLayout.setTop(layout);
		mainMenuLayout.setCenter(layout2);
		

		Scene scene1 = new Scene(mainMenuLayout);
		window.setScene(scene1);

		/*
		 * Aktuelles Fenster muss geschlossen werden, damit man wieder zum
		 * Caller zurückkehren kann
		 */
		window.showAndWait();
		
		return answer;
	}

}
