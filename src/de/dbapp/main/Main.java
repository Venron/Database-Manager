package de.dbapp.main;

import java.sql.Date;
import java.util.List;
import de.dbapp.modules.*;
import de.dbapp.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.text.LayoutQueue;

import org.hibernate.Query;

import javafx.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Observable;

import de.webapp.dao.*;
import de.webapp.model.*;

public class Main extends Application {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
	EntityManager em = emf.createEntityManager();
	EntityManager em1 = emf.createEntityManager();
	Stage window;
	Scene loginScene, mainMenuScene;
	TextField usernameInput;
	PasswordField passwordInput;
	TableView<Branche> brancheTable;
	TableView<Adresse> adresseTable;
	TableView<Aktie> aktieTable;
	TableView<Fonds> fondsTable;
	TableView<Fondsanteil> fondsanteilTable;
	TableView<Handelsplatz> handelsplatzTable;
	TableView<Nachricht> nachrichtTable;
	TableView<Profile> profileTable;
	TableView<Rente> renteTable;
	TableView<Unternehmensdaten> unternehmensdatenTable;
	TableView<User> userTable;
	TableView<Wertpapier> wertpapierTable;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Datenbank Manager");

		/*
		 * Login
		 */

		// Scene: login
		// Layout
		GridPane loginLayout = new GridPane();
		loginLayout.setPadding(new Insets(10, 10, 10, 10));
		loginLayout.setVgap(8);
		loginLayout.setHgap(10);

		// Scene: login
		// Elements
		Label uLabel = new Label("Username");
		loginLayout.setConstraints(uLabel, 0, 0);
		usernameInput = new TextField();
		usernameInput.setPromptText("Username");
		loginLayout.setConstraints(usernameInput, 1, 0);
		Label pLabel = new Label("Passwort");
		loginLayout.setConstraints(pLabel, 0, 1);
		passwordInput = new PasswordField();
		passwordInput.setPromptText("Passwort");
		loginLayout.setConstraints(passwordInput, 1, 1);
		Button loginButton = new Button("Login");
		loginButton.setOnAction(e -> login(usernameInput.getText(), passwordInput.getText()));
		loginLayout.setConstraints(loginButton, 0, 2);

		// Add to layout
		loginLayout.getChildren().addAll(uLabel, usernameInput, pLabel, passwordInput, loginButton);

		loginScene = new Scene(loginLayout, 450, 200);

		/*
		 * Login End
		 */

		/*
		 * Main Menu
		 */

		// Scene: main menu
		// Layout
		BorderPane mainMenuLayout = new BorderPane();

		TabPane allTablesTabPan = new TabPane();
		allTablesTabPan.setPadding(new Insets(20, 20, 20, 20));

		// Top menu

		// File menu
		Menu fileMenu = new Menu("_File");
		Menu editMenu = new Menu("_Edit");
		Menu refreshMenu = new Menu("_Refresh");
		Menu helpMenu = new Menu("_Help");

		MenuItem exitFile = new MenuItem("Exit");
		exitFile.setOnAction(e -> closeWindow());
		window.setOnCloseRequest(e -> {
			e.consume();
			closeWindow();
		});
		fileMenu.getItems().addAll(exitFile);

		// Edit menu
		MenuItem editFile = new MenuItem("Edit...");
		editFile.setOnAction(e -> AlertBox.displayDeleteWindow());
		editMenu.getItems().addAll(editFile);

		// Refresh menu
		MenuItem refreshFile = new MenuItem("Refresh table...");
		refreshFile.setOnAction(e -> refreshAll());
		refreshMenu.getItems().add(refreshFile);

		// Menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(editMenu);
		menuBar.getMenus().add(refreshMenu);

		// Branche Table

		// Layout
		VBox brancheTableLayout = new VBox(20);
		brancheTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Branche, Integer> brancheBidColumn = new TableColumn<>("BID");
		brancheBidColumn.setMinWidth(200);
		brancheBidColumn.setCellValueFactory(new PropertyValueFactory<>("bid"));

		TableColumn<Branche, String> brancheNameColumn = new TableColumn<>("Name");
		brancheNameColumn.setMinWidth(100);
		brancheNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		brancheTable = new TableView<Branche>();
		brancheTable.getColumns().addAll(brancheBidColumn, brancheNameColumn);
		brancheTable.setItems(getBranche());
		brancheTableLayout.getChildren().add(brancheTable);
		Tab brancheTab = new Tab("Branche");
		brancheTab.setContent(brancheTableLayout);

		// Adresse Table

		// Layout
		VBox adresseTableLayout = new VBox(20);
		adresseTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Adresse, Integer> adresseAidColumn = new TableColumn<>("AID");
		adresseAidColumn.setMinWidth(200);
		adresseAidColumn.setCellValueFactory(new PropertyValueFactory<>("aid"));

		TableColumn<Adresse, Integer> adressePlzColumn = new TableColumn<>("Postleitzahl");
		adressePlzColumn.setMinWidth(100);
		adressePlzColumn.setCellValueFactory(new PropertyValueFactory<>("plz"));

		TableColumn<Adresse, String> adresseOrtColumn = new TableColumn<>("Ort");
		adresseOrtColumn.setMinWidth(100);
		adresseOrtColumn.setCellValueFactory(new PropertyValueFactory<>("ort"));

		TableColumn<Adresse, String> adresseStrasseColumn = new TableColumn<>("Strasse");
		adresseStrasseColumn.setMinWidth(100);
		adresseStrasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));

		TableColumn<Adresse, String> adresseHausnummerColumn = new TableColumn<>("Hausnummer");
		adresseHausnummerColumn.setMinWidth(100);
		adresseHausnummerColumn.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));

		adresseTable = new TableView<Adresse>();
		adresseTable.getColumns().addAll(adresseAidColumn, adressePlzColumn, adresseOrtColumn, adresseStrasseColumn,
				adresseHausnummerColumn);
		adresseTable.setItems(getAdresse());
		adresseTableLayout.getChildren().add(adresseTable);
		Tab adresseTab = new Tab("Adresse");
		adresseTab.setContent(adresseTableLayout);

		// Aktie Table

		// Layout
		VBox aktieTableLayout = new VBox(20);
		aktieTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Aktie, Integer> aktieWknColumn = new TableColumn<>("WKN");
		aktieWknColumn.setMinWidth(200);
		aktieWknColumn.setCellValueFactory(new PropertyValueFactory<>("wkn"));

		TableColumn<Aktie, Integer> aktieAidColumn = new TableColumn<>("AID");
		aktieAidColumn.setMinWidth(100);
		aktieAidColumn.setCellValueFactory(new PropertyValueFactory<>("aid"));

		TableColumn<Aktie, Integer> aktieBidColumn = new TableColumn<>("BID");
		aktieBidColumn.setMinWidth(100);
		aktieBidColumn.setCellValueFactory(new PropertyValueFactory<>("bid"));

		TableColumn<Aktie, Double> aktieStreubesitzColumn = new TableColumn<>("Streubesitz");
		aktieStreubesitzColumn.setMinWidth(100);
		aktieStreubesitzColumn.setCellValueFactory(new PropertyValueFactory<>("streubesitz"));

		aktieTable = new TableView<Aktie>();
		aktieTable.getColumns().addAll(aktieWknColumn, aktieAidColumn, aktieBidColumn, aktieStreubesitzColumn);
		aktieTable.setItems(getAktie());
		aktieTableLayout.getChildren().add(aktieTable);
		Tab aktieTab = new Tab("Aktie");
		aktieTab.setContent(aktieTableLayout);

		// Fonds Table

		// Layout
		VBox fondsTableLayout = new VBox(20);
		fondsTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Fonds, Integer> fondsWknColumn = new TableColumn<>("WKN");
		fondsWknColumn.setMinWidth(200);
		fondsWknColumn.setCellValueFactory(new PropertyValueFactory<>("wkn"));

		TableColumn<Fonds, String> fondsTypColumn = new TableColumn<>("TYP");
		fondsTypColumn.setMinWidth(100);
		fondsTypColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));

		TableColumn<Fonds, Double> fondsMaxanteilColumn = new TableColumn<>("MAX ANTEIL");
		fondsMaxanteilColumn.setMinWidth(100);
		fondsMaxanteilColumn.setCellValueFactory(new PropertyValueFactory<>("maxanteil"));

		fondsTable = new TableView<Fonds>();
		fondsTable.getColumns().addAll(fondsWknColumn, fondsTypColumn, fondsMaxanteilColumn);
		fondsTable.setItems(getFonds());
		fondsTableLayout.getChildren().add(fondsTable);
		Tab fondsTab = new Tab("Fonds");
		fondsTab.setContent(fondsTableLayout);

		// Fondsanteil Table

		// Layout
		VBox fondsanteilTableLayout = new VBox(20);
		fondsanteilTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Fondsanteil, Integer> fondsanteilWknColumn = new TableColumn<>("FONDSWKN");
		fondsanteilWknColumn.setMinWidth(200);
		fondsanteilWknColumn.setCellValueFactory(new PropertyValueFactory<>("fondswkn"));

		TableColumn<Fondsanteil, Integer> fondsanteilPapierwknColumn = new TableColumn<>("PAPIERWKN");
		fondsanteilPapierwknColumn.setMinWidth(100);
		fondsanteilPapierwknColumn.setCellValueFactory(new PropertyValueFactory<>("papierwkn"));

		TableColumn<Fondsanteil, Long> fondsanteilStueckzahlColumn = new TableColumn<>("STUECKZAHL");
		fondsanteilStueckzahlColumn.setMinWidth(100);
		fondsanteilStueckzahlColumn.setCellValueFactory(new PropertyValueFactory<>("stueckzahl"));

		fondsanteilTable = new TableView<Fondsanteil>();
		fondsanteilTable.getColumns().addAll(fondsanteilWknColumn, fondsanteilPapierwknColumn,
				fondsanteilStueckzahlColumn);
		fondsanteilTable.setItems(getFondsanteil());
		fondsanteilTableLayout.getChildren().add(fondsanteilTable);
		Tab fondsanteilTab = new Tab("Fondsanteil");
		fondsanteilTab.setContent(fondsanteilTableLayout);

		// Handelsplatz Table

		// Layout
		VBox hpTableLayout = new VBox(20);
		hpTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Handelsplatz, Integer> hpHidColumn = new TableColumn<>("HID");
		hpHidColumn.setMinWidth(200);
		hpHidColumn.setCellValueFactory(new PropertyValueFactory<>("hid"));

		TableColumn<Handelsplatz, String> hpNameColumn = new TableColumn<>("NAME");
		hpNameColumn.setMinWidth(100);
		hpNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		handelsplatzTable = new TableView<Handelsplatz>();
		handelsplatzTable.getColumns().addAll(hpHidColumn, hpNameColumn);
		handelsplatzTable.setItems(getHandelsplatz());
		hpTableLayout.getChildren().add(handelsplatzTable);
		Tab handelsplatzTab = new Tab("Handelsplatz");
		handelsplatzTab.setContent(hpTableLayout);

		// Kurs Tabelle zu groﬂ, deswegen nicht angezeigt

		// Nachricht Table

		// Layout
		VBox nachrichtTableLayout = new VBox(20);
		nachrichtTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Nachricht, Integer> nachrichtMsgidColumn = new TableColumn<>("MSG ID");
		nachrichtMsgidColumn.setMinWidth(200);
		nachrichtMsgidColumn.setCellValueFactory(new PropertyValueFactory<>("msg_id"));

		TableColumn<Nachricht, String> nachrichtSenderColumn = new TableColumn<>("SENDER");
		nachrichtSenderColumn.setMinWidth(100);
		nachrichtSenderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));

		TableColumn<Nachricht, String> nachrichtEmpfaengerColumn = new TableColumn<>("EMPFAENGER");
		nachrichtEmpfaengerColumn.setMinWidth(100);
		nachrichtEmpfaengerColumn.setCellValueFactory(new PropertyValueFactory<>("empfaenger"));

		TableColumn<Nachricht, String> nachrichtTextColumn = new TableColumn<>("TEXT");
		nachrichtTextColumn.setMinWidth(100);
		nachrichtTextColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

		nachrichtTable = new TableView<Nachricht>();
		nachrichtTable.getColumns().addAll(nachrichtMsgidColumn, nachrichtSenderColumn, nachrichtEmpfaengerColumn,
				nachrichtTextColumn);
		nachrichtTable.setItems(getNachricht());
		nachrichtTableLayout.getChildren().add(nachrichtTable);
		Tab nachrichtTab = new Tab("Nachricht");
		nachrichtTab.setContent(nachrichtTableLayout);

		// Profile Table

		// Layout
		VBox profileTableLayout = new VBox(20);
		profileTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Profile, Integer> profilePidColumn = new TableColumn<>("PROFILE ID");
		profilePidColumn.setMinWidth(200);
		profilePidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));

		TableColumn<Profile, String> profileWunschmieteColumn = new TableColumn<>("WUNSCHMIETE");
		profileWunschmieteColumn.setMinWidth(100);
		profileWunschmieteColumn.setCellValueFactory(new PropertyValueFactory<>("wunschmiete"));

		TableColumn<Profile, String> profileWunschgroesseColumn = new TableColumn<>("WUNSCHGROESSE");
		profileWunschgroesseColumn.setMinWidth(100);
		profileWunschgroesseColumn.setCellValueFactory(new PropertyValueFactory<>("wunschgroesse"));

		TableColumn<Profile, String> profileWunschlageColumn = new TableColumn<>("WUNSCHLAGE");
		profileWunschlageColumn.setMinWidth(100);
		profileWunschlageColumn.setCellValueFactory(new PropertyValueFactory<>("wunschlage"));

		TableColumn<Profile, String> profileInteressenColumn = new TableColumn<>("Interessen");
		profileInteressenColumn.setMinWidth(100);
		profileInteressenColumn.setCellValueFactory(new PropertyValueFactory<>("interessen"));

		TableColumn<Profile, String> profileTaetigkeitColumn = new TableColumn<>("TAETIGKEIT");
		profileTaetigkeitColumn.setMinWidth(100);
		profileTaetigkeitColumn.setCellValueFactory(new PropertyValueFactory<>("taetigkeit"));

		TableColumn<Profile, String> profileRaucherColumn = new TableColumn<>("RAUCHER");
		profileRaucherColumn.setMinWidth(100);
		profileRaucherColumn.setCellValueFactory(new PropertyValueFactory<>("raucher"));

		TableColumn<Profile, String> profileSonderwuenscheColumn = new TableColumn<>("SONDERWUENSCHE");
		profileSonderwuenscheColumn.setMinWidth(100);
		profileSonderwuenscheColumn.setCellValueFactory(new PropertyValueFactory<>("sonderwuensche"));

		TableColumn<Profile, String> profileEmailColumn = new TableColumn<>("EMAIL");
		profileEmailColumn.setMinWidth(100);
		profileEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		profileTable = new TableView<Profile>();
		profileTable.getColumns().addAll(profilePidColumn, profileWunschmieteColumn, profileWunschgroesseColumn,
				profileWunschlageColumn, profileInteressenColumn, profileTaetigkeitColumn, profileRaucherColumn,
				profileSonderwuenscheColumn, profileEmailColumn);
		profileTable.setItems(getProfile());
		profileTableLayout.getChildren().add(profileTable);
		Tab profileTab = new Tab("Profile");
		profileTab.setContent(profileTableLayout);

		// Rente Table

		// Layout
		VBox renteTableLayout = new VBox(20);
		renteTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Rente, Integer> renteWknColumn = new TableColumn<>("WKN");
		renteWknColumn.setMinWidth(200);
		renteWknColumn.setCellValueFactory(new PropertyValueFactory<>("wkn"));

		TableColumn<Rente, String> renteTypColumn = new TableColumn<>("TYP");
		renteTypColumn.setMinWidth(100);
		renteTypColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));

		TableColumn<Rente, Date> renteFaelligkeitsdatumColumn = new TableColumn<>("FAELLIGKEITSDATUM");
		renteFaelligkeitsdatumColumn.setMinWidth(100);
		renteFaelligkeitsdatumColumn.setCellValueFactory(new PropertyValueFactory<>("faelligkeitsdatum"));

		renteTable = new TableView<Rente>();
		renteTable.getColumns().addAll(renteWknColumn, renteTypColumn, renteFaelligkeitsdatumColumn);
		renteTable.setItems(getRente());
		renteTableLayout.getChildren().add(renteTable);
		Tab renteTab = new Tab("Rente");
		renteTab.setContent(renteTableLayout);

		// Unternehmensdaten Table

		// Layout
		VBox udTableLayout = new VBox(20);
		udTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Unternehmensdaten, Integer> udWknColumn = new TableColumn<>("WKN");
		udWknColumn.setMinWidth(200);
		udWknColumn.setCellValueFactory(new PropertyValueFactory<>("wkn"));

		TableColumn<Unternehmensdaten, Integer> udJahrColumn = new TableColumn<>("JAHR");
		udJahrColumn.setMinWidth(100);
		udJahrColumn.setCellValueFactory(new PropertyValueFactory<>("jahr"));

		TableColumn<Unternehmensdaten, Long> udBilanzsummeColumn = new TableColumn<>("BILANZSUMME");
		udBilanzsummeColumn.setMinWidth(100);
		udBilanzsummeColumn.setCellValueFactory(new PropertyValueFactory<>("bilanzsumme"));

		TableColumn<Unternehmensdaten, Long> udAusschuettungColumn = new TableColumn<>("AUSSCHUETTUNG");
		udAusschuettungColumn.setMinWidth(100);
		udAusschuettungColumn.setCellValueFactory(new PropertyValueFactory<>("ausschuettung"));

		TableColumn<Unternehmensdaten, Integer> udAnzahlaktienColumn = new TableColumn<>("ANZAHL AKTIEN");
		udAnzahlaktienColumn.setMinWidth(100);
		udAnzahlaktienColumn.setCellValueFactory(new PropertyValueFactory<>("anzahlaktien"));

		unternehmensdatenTable = new TableView<Unternehmensdaten>();
		unternehmensdatenTable.getColumns().addAll(udWknColumn, udJahrColumn, udBilanzsummeColumn,
				udAusschuettungColumn, udAnzahlaktienColumn);
		unternehmensdatenTable.setItems(getUnternehmensdaten());
		udTableLayout.getChildren().add(unternehmensdatenTable);
		Tab unternehmensdatenTab = new Tab("Unternehmensdaten");
		unternehmensdatenTab.setContent(udTableLayout);

		// User Table

		// Layout
		VBox userTableLayout = new VBox(20);
		userTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<User, Integer> userUseridColumn = new TableColumn<>("USER ID");
		userUseridColumn.setMinWidth(200);
		userUseridColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));

		TableColumn<User, String> userEmailColumn = new TableColumn<>("EMAIL");
		userEmailColumn.setMinWidth(100);
		userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<User, String> userPasswordColumn = new TableColumn<>("PASSWORD");
		userPasswordColumn.setMinWidth(250);
		userPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

		TableColumn<User, String> userPrenameColumn = new TableColumn<>("PRENAME");
		userPrenameColumn.setMinWidth(100);
		userPrenameColumn.setCellValueFactory(new PropertyValueFactory<>("prename"));

		TableColumn<User, String> userSurnameColumn = new TableColumn<>("SURNAME");
		userSurnameColumn.setMinWidth(100);
		userSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

		userTable = new TableView<User>();
		userTable.getColumns().addAll(userUseridColumn, userEmailColumn, userPasswordColumn, userPrenameColumn,
				userSurnameColumn);
		userTable.setItems(getUser());
		userTableLayout.getChildren().add(userTable);
		Tab userTab = new Tab("User");
		userTab.setContent(userTableLayout);

		// Wertpapier Table

		// Layout
		VBox wpTableLayout = new VBox(20);
		wpTableLayout.setPadding(new Insets(20, 20, 20, 20));

		// Elements
		TableColumn<Wertpapier, Integer> wpWknColumn = new TableColumn<>("WKN");
		wpWknColumn.setMinWidth(200);
		wpWknColumn.setCellValueFactory(new PropertyValueFactory<>("wkn"));

		TableColumn<Wertpapier, String> wpNameColumn = new TableColumn<>("NAME");
		wpNameColumn.setMinWidth(100);
		wpNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Wertpapier, String> wpTypColumn = new TableColumn<>("TYP");
		wpTypColumn.setMinWidth(100);
		wpTypColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));

		TableColumn<Wertpapier, Integer> wpStueckzahlColumn = new TableColumn<>("STUECKZAHL");
		wpStueckzahlColumn.setMinWidth(100);
		wpStueckzahlColumn.setCellValueFactory(new PropertyValueFactory<>("stueckzahl"));

		TableColumn<Wertpapier, Date> wpAusgabedatumColumn = new TableColumn<>("AUSGABEDATUM");
		wpAusgabedatumColumn.setMinWidth(100);
		wpAusgabedatumColumn.setCellValueFactory(new PropertyValueFactory<>("ausgabedatum"));

		TableColumn<Wertpapier, Double> wpAusgabekursColumn = new TableColumn<>("AUSGABEKURS");
		wpAusgabekursColumn.setMinWidth(100);
		wpAusgabekursColumn.setCellValueFactory(new PropertyValueFactory<>("ausgabekurs"));

		wertpapierTable = new TableView<Wertpapier>();
		wertpapierTable.getColumns().addAll(wpWknColumn, wpNameColumn, wpTypColumn, wpStueckzahlColumn,
				wpAusgabedatumColumn, wpAusgabekursColumn);
		wertpapierTable.setItems(getWertpapier());
		wpTableLayout.getChildren().add(wertpapierTable);
		Tab wertpapierTab = new Tab("Wertpapier");
		wertpapierTab.setContent(wpTableLayout);

		/*
		 * Main Menu End
		 */

		// must have
		allTablesTabPan.getTabs().addAll(userTab, profileTab, nachrichtTab, brancheTab, adresseTab, aktieTab, fondsTab,
				fondsanteilTab, handelsplatzTab, renteTab, unternehmensdatenTab, wertpapierTab);

		mainMenuLayout.setCenter(allTablesTabPan);
		mainMenuLayout.setTop(menuBar);

		mainMenuScene = new Scene(mainMenuLayout, 850, 600);
		window.setScene(loginScene);
		window.show();
	}

	private void closeWindow() {
		boolean answer = ConfirmBox.display("Close window", "Are you sure you want to exit?");
		if (answer == true) {
			window.close();
		}
	}

	private void login(String username, String password) {
		if (username.equals("demo") && password.equals("demo")) {
			window.setScene(mainMenuScene);
		} else {
			AlertBox.display("Login failed", "Login failed, please try again");
			usernameInput.clear();
			passwordInput.clear();
		}
	}

	private void refreshAll() {
		userTable.getItems().clear();
		userTable.getItems().addAll(getUser());

		adresseTable.getItems().clear();
		adresseTable.getItems().addAll(getAdresse());

		aktieTable.getItems().clear();
		aktieTable.getItems().addAll(getAktie());

		brancheTable.getItems().clear();
		brancheTable.getItems().addAll(getBranche());

		fondsTable.getItems().clear();
		fondsTable.getItems().addAll(getFonds());

		fondsanteilTable.getItems().clear();
		fondsanteilTable.getItems().addAll(getFondsanteil());

		handelsplatzTable.getItems().clear();
		handelsplatzTable.getItems().addAll(getHandelsplatz());

		nachrichtTable.getItems().clear();
		nachrichtTable.getItems().addAll(getNachricht());

		profileTable.getItems().clear();
		profileTable.getItems().addAll(getProfile());

		renteTable.getItems().clear();
		renteTable.getItems().addAll(getRente());

		unternehmensdatenTable.getItems().clear();
		unternehmensdatenTable.getItems().addAll(getUnternehmensdaten());

		wertpapierTable.getItems().clear();
		wertpapierTable.getItems().addAll(getWertpapier());
	}

	private ObservableList<Branche> getBranche() {
		ObservableList<Branche> allBranche = FXCollections.observableArrayList();
		em.getTransaction().begin();
		BrancheDao branchedao = new BrancheDao(em);
		em.getTransaction().commit();
		List<Branche> brancheResults = branchedao.findAll();
		for (Branche b : brancheResults) {
			allBranche.add(b);
		}
		return allBranche;
	}

	private ObservableList<Adresse> getAdresse() {
		ObservableList<Adresse> allAdresse = FXCollections.observableArrayList();
		AdresseDao adressedao = new AdresseDao(em);
		em.getTransaction().begin();
		List<Adresse> adresseResults = adressedao.findAll();
		em.getTransaction().commit();
		for (Adresse a : adresseResults) {
			allAdresse.add(a);
		}
		return allAdresse;
	}

	private ObservableList<Aktie> getAktie() {
		ObservableList<Aktie> allAktie = FXCollections.observableArrayList();
		AktieDao aktiedao = new AktieDao(em);
		em.getTransaction().begin();
		List<Aktie> aktieResults = aktiedao.findAll();
		em.getTransaction().commit();
		for (Aktie a : aktieResults) {
			allAktie.add(a);
		}
		return allAktie;
	}

	private ObservableList<Fonds> getFonds() {
		ObservableList<Fonds> allFonds = FXCollections.observableArrayList();
		FondsDao fondsdao = new FondsDao(em);
		em.getTransaction().begin();
		List<Fonds> fondsResults = fondsdao.findAll();
		em.getTransaction().commit();
		for (Fonds f : fondsResults) {
			allFonds.add(f);
		}
		return allFonds;
	}

	private ObservableList<Fondsanteil> getFondsanteil() {
		ObservableList<Fondsanteil> allFondsanteil = FXCollections.observableArrayList();
		FondsanteilDao fondsanteildao = new FondsanteilDao(em);
		em.getTransaction().begin();
		List<Fondsanteil> fondsanteilResults = fondsanteildao.findAll();
		em.getTransaction().commit();
		for (Fondsanteil f : fondsanteilResults) {
			allFondsanteil.add(f);
		}
		return allFondsanteil;
	}

	private ObservableList<Handelsplatz> getHandelsplatz() {
		ObservableList<Handelsplatz> allHandelsplatz = FXCollections.observableArrayList();
		HandelsplatzDao handelsplatzdao = new HandelsplatzDao(em);
		em.getTransaction().begin();
		List<Handelsplatz> handelsplatzResults = handelsplatzdao.findAll();
		em.getTransaction().commit();
		for (Handelsplatz h : handelsplatzResults) {
			allHandelsplatz.add(h);
		}
		return allHandelsplatz;
	}

	private ObservableList<Kurs> getKurs() {
		ObservableList<Kurs> allKurs = FXCollections.observableArrayList();
		KursDao kursdao = new KursDao(em);
		em.getTransaction().begin();
		List<Kurs> kursResults = kursdao.findAll();
		em.getTransaction().commit();
		for (Kurs k : kursResults) {
			allKurs.add(k);
		}
		return allKurs;
	}

	private ObservableList<Nachricht> getNachricht() {
		ObservableList<Nachricht> allNachricht = FXCollections.observableArrayList();
		NachrichtDao nachrichtdao = new NachrichtDao(em);
		em.getTransaction().begin();
		List<Nachricht> nachrichtResults = nachrichtdao.findAll();
		em.getTransaction().commit();
		for (Nachricht n : nachrichtResults) {
			allNachricht.add(n);
		}
		return allNachricht;
	}

	private ObservableList<Profile> getProfile() {
		ObservableList<Profile> allProfile = FXCollections.observableArrayList();
		ProfileDao profiledao = new ProfileDao(em);
		em.getTransaction().begin();
		List<Profile> profileResults = profiledao.findAll();
		em.getTransaction().commit();
		for (Profile p : profileResults) {
			allProfile.add(p);
		}
		return allProfile;
	}

	private ObservableList<Rente> getRente() {
		ObservableList<Rente> allRente = FXCollections.observableArrayList();
		RenteDao rentedao = new RenteDao(em);
		em.getTransaction().begin();
		List<Rente> renteResults = rentedao.findAll();
		em.getTransaction().commit();
		for (Rente r : renteResults) {
			allRente.add(r);
		}
		return allRente;
	}

	private ObservableList<Unternehmensdaten> getUnternehmensdaten() {
		ObservableList<Unternehmensdaten> allUd = FXCollections.observableArrayList();
		UnternehmensdatenDao uddao = new UnternehmensdatenDao(em);
		em.getTransaction().begin();
		List<Unternehmensdaten> udResults = uddao.findAll();
		em.getTransaction().commit();
		for (Unternehmensdaten u : udResults) {
			allUd.add(u);
		}
		return allUd;
	}

	private ObservableList<User> getUser() {
		ObservableList<User> allUser = FXCollections.observableArrayList();
		UserDao userdao = new UserDao(em);
		em.getTransaction().begin();
		List<User> userResults = userdao.findAll();
		em.getTransaction().commit();
		for (User u : userResults) {
			allUser.add(u);
		}
		return allUser;
	}

	private ObservableList<Wertpapier> getWertpapier() {
		ObservableList<Wertpapier> allWp = FXCollections.observableArrayList();
		WertpapierDao wpdao = new WertpapierDao(em);
		em.getTransaction().begin();
		List<Wertpapier> wpResults = wpdao.findAll();
		em.getTransaction().commit();
		for (Wertpapier w : wpResults) {
			allWp.add(w);
		}
		return allWp;
	}
}
