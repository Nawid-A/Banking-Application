package bankapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerLogin {
    private Manager manager;
    private Stage stage;

    public CustomerLogin() {
        this.manager = new Manager(); // Assume Manager class has a no-args constructor
        this.stage = new Stage(); // Create the login window stage
    }

    public void display() {
        stage.setScene(new Scene(getView())); // Set the scene
        stage.setTitle("Login");
        stage.showAndWait(); // Show the window and wait for it to be closed
    }

    public Parent getView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label loginStatus = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean loginSuccess = false;

                // Iterate through customers to find a match
            for (Customer c : manager.customers) { // Ensure manager has a getter for customers if it's private
                if (c.Login(username, password)) {
                    loginStatus.setText("Customer Login successful");
                    new CustomerOps(c).display();
                    loginSuccess = true;
                    break; // Found the customer, exit loop
                    }
                }

            // Reset login fields
            usernameField.clear();
            passwordField.clear();

            if (!loginSuccess) {
                loginStatus.setText("Login failed.");
            }
        });

        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, loginStatus);
        return layout;
    }
}

