package bankapp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerOps {
    private Stage stage;
    private Manager manager;

    public ManagerOps(Manager manager) {
        this.manager = manager;
        stage = new Stage();

        VBox layout = new VBox(10);
        
        // Input fields and buttons for adding a customer
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password"); 
        TextField balanceField = new TextField(); 
        Button addButton = new Button("Add Customer"); 
        addButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();             
            manager.addCustomer(username, password);  
        });
        
        // Input field and button for deleting a customer
        TextField deleteUserField = new TextField();
        deleteUserField.setPromptText("Username to delete");
        Button deleteButton = new Button("Delete Customer");
        deleteButton.setOnAction(e -> {
            String username = deleteUserField.getText();
            manager.removeCustomer(username);
        });

        // Logout button to return to the login screen
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            stage.close(); // Close the current window
        });

        layout.getChildren().addAll(new Label("Add New Customer"), usernameField, passwordField, addButton, new Label("Delete Customer"), deleteUserField, deleteButton, logoutButton);

        Scene scene = new Scene(layout, 300, 300); // Adjusted the height to accommodate the logout button
        stage.setScene(scene);
        stage.setTitle("Manager Operations");
    }

    public void display() {
        stage.showAndWait();
    }
}


