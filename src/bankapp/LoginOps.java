/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 
import javafx.scene.control.Label;  
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 


public class LoginOps {
     
public void display() {
        Stage window = new Stage();
        window.setTitle("Login Type");
        window.setMinWidth(250); 
        window.setMinHeight(200);

        Button customerButton = new Button("Customer");
        Button managerButton = new Button("Manager");

        // Button actions
        customerButton.setOnAction(e -> {
            // Handle Customer login here 
            Stage loginStage = new Stage();
            Scene scene = new Scene(new CustomerLogin().getView());
            loginStage.setScene(scene);
            loginStage.setTitle("Customer Login");
            loginStage.show();
            // Close the previous window
            window.close();
            window.close();
        });

        managerButton.setOnAction(e -> {
            // Handle Manager login here 
            Stage loginStage = new Stage();
            Scene scene = new Scene(new ManagerLogin().getView());
            loginStage.setScene(scene);
            loginStage.setTitle("Manager Login");
            loginStage.show();
            // Close the previous window
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(customerButton, managerButton);  
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
}
