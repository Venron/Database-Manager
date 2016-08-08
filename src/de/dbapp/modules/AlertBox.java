package de.dbapp.modules;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import de.webapp.model.*;

import de.webapp.dao.UserDao;
import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	static String selectedTable = "";

	public static void main(String[] args) {

	}

	public static void display(String title, String message) {
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

		VBox layout = new VBox(20);
		layout.getChildren().addAll(l1, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout);
		window.setScene(scene1);

		/*
		 * Aktuelles Fenster muss geschlossen werden, damit man wieder zum
		 * Caller zurückkehren kann
		 */
		window.showAndWait();
	}

	public static void displayDeleteWindow() {
		Stage window = new Stage();

		/*
		 * während dieses Fenster offen ist, können keine Fenster im Hintergrund
		 * bearbeitet werden Dieses Fenster muss erst geschlossen werden, damit
		 * man andere wieder benutzen kann
		 */
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Delete from table");
		window.setMinWidth(300);
		window.setMinHeight(80);

		Button closeButton = new Button();
		closeButton.setText("Close window");
		closeButton.setOnAction(e -> window.close());

		Label set = new Label("DELETE");
		Label where = new Label("WHERE PRIMKEY");
		TextField valueField = new TextField();
		valueField.setPromptText("Value");
		TextField primKeyField = new TextField();
		primKeyField.setPromptText("Primary Key");
		Button submitButton = new Button();
		submitButton.setText("Submit");
		submitButton.setOnAction(e -> deleteFromTable(selectedTable, valueField.getText(), primKeyField.getText()));

		Label deleteLabel = new Label("Table");
		ChoiceBox<String> deleteTable = new ChoiceBox<>();
		deleteTable.getItems().add("User");
		deleteTable.getItems().add("Profile");
		deleteTable.getItems().add("Nachricht");
		deleteTable.getItems().add("Adresse");
		deleteTable.getItems().add("Aktie");
		deleteTable.getItems().add("Branche");
		deleteTable.getItems().add("Fonds");
		deleteTable.getItems().add("Fondsanteil");
		deleteTable.getItems().add("Handelsplatz");
		deleteTable.getItems().add("Rente");
		deleteTable.getItems().add("Unternehmensdaten");
		deleteTable.getItems().add("Wertpapier");
		deleteTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			selectedTable = newValue;
			System.out.println(newValue);
		});

		deleteTable.setValue("User");

		HBox layout = new HBox(20);
		layout.setSpacing(20);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(deleteTable, where, primKeyField, submitButton);
		

		Scene scene1 = new Scene(layout);
		window.setScene(scene1);

		/*
		 * Aktuelles Fenster muss geschlossen werden, damit man wieder zum
		 * Caller zurückkehren kann
		 */
		window.showAndWait();

	}

	private static void deleteFromTable(String table, String value, String primKey) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
		EntityManager em = emf.createEntityManager();
		if (table.equals("User")) {
			UserDao userdao = new UserDao(em);
			User user = userdao.findOne(Integer.parseInt(primKey));
			try {
				if(user != null) {
					em.getTransaction().begin();
					userdao.delete(user);
					em.flush();
					em.getTransaction().commit();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (table.equals("Profile")) {

		} else if (table.equals("Nachricht")) {

		} else if (table.equals("Adresse")) {

		} else if (table.equals("Aktie")) {

		} else if (table.equals("Branche")) {

		} else if (table.equals("Fonds")) {

		} else if (table.equals("Fondsanteil")) {

		} else if (table.equals("Handelsplatz")) {

		} else if (table.equals("Rente")) {

		} else if (table.equals("Unternehmensdaten")) {

		} else if (table.equals("Wertpapier")) {

		}
	}
}
