package bankapp; 

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Welcome extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main layout pane
        BorderPane borderPane = new BorderPane();

        // Top section with bank logo
        Label lblBankLogo = new Label("BANKING APPLICATION");
        HBox topPane = new HBox(lblBankLogo);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(10));
        borderPane.setTop(topPane);

        // Center section with welcome message and login button
        Label lblWelcome = new Label("WELCOME");
        lblWelcome.setStyle("-fx-font-size: 24px;");

        Button btnLogin = new Button("Login"); 
        btnLogin.setOnAction(e->{ 
            new LoginOps().display(); // Open the login type selection window
            primaryStage.close(); // Close the current (welcome) window
                });
        VBox centerPane = new VBox(20, lblWelcome, btnLogin);
        centerPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(centerPane);


        // Set the scene and stage
        Scene scene = new Scene(borderPane, 300, 250);
        primaryStage.setTitle("Banking App Design");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

