package bankapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class CustomerOps {
    private Stage stage;
    private Customer customer;
    private Label balanceLabel;

    public CustomerOps(Customer customer) {
        this.customer = customer;
        this.stage = new Stage();

        // Initialize UI components
        Label welcomeLabel = new Label("Welcome, " + customer.getUsername());
        balanceLabel = new Label("Balance: " + customer.getBankAccount().getBalance());

        Button withdrawButton = new Button("Withdraw");
        Button depositButton = new Button("Deposit");
        Button purchaseItemButton = new Button("Shop");
        Button balanceButton = new Button("Get Balance");
        Button logoutButton = new Button("Logout");

        // Define button actions
        withdrawButton.setOnAction(e -> handleWithdraw());
        depositButton.setOnAction(e -> handleDeposit());
        purchaseItemButton.setOnAction(e -> handlePurchase());
        balanceButton.setOnAction(e -> handleBalance());
        logoutButton.setOnAction(e -> stage.close()); // Close the account window

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomeLabel, balanceLabel, withdrawButton, depositButton, purchaseItemButton, balanceButton, logoutButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    public void display() {
        stage.showAndWait();
    }

    private void handleWithdraw() {
        TextInputDialog withdrawDialog = new TextInputDialog();
        withdrawDialog.setTitle("Withdrawal");
        withdrawDialog.setHeaderText("Withdraw Money");
        withdrawDialog.setContentText("Please enter the amount to withdraw:");
        Optional<String> result = withdrawDialog.showAndWait();

        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                customer.withdraw(amount);
                updateBalance();
            } catch (NumberFormatException ex) {
                showAlert("Invalid input", "Please enter a valid number.");
            }
        });
    }

    private void handleDeposit() {
        TextInputDialog depositDialog = new TextInputDialog();
        depositDialog.setTitle("Deposit");
        depositDialog.setHeaderText("Deposit Money");
        depositDialog.setContentText("Please enter the amount to deposit:");
        Optional<String> result = depositDialog.showAndWait();

        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                customer.deposit(amount);
                updateBalance();
            } catch (NumberFormatException ex) {
                showAlert("Invalid input", "Please enter a valid number.");
            }
        });
    }

    private void handlePurchase() {
        TextInputDialog purchaseDialog = new TextInputDialog();
        purchaseDialog.setTitle("Purchase Item");
        purchaseDialog.setHeaderText("Enter the Price of the Item to Purchase");
        purchaseDialog.setContentText("Price:");
        Optional<String> result = purchaseDialog.showAndWait();

        result.ifPresent(priceStr -> {
            try {
                double price = Double.parseDouble(priceStr);
                if (price >= 50) { // Assuming the minimum price for a purchase is $50
                    customer.purchase(price);
                    updateBalance();
                    showAlert("Purchase Successful", "Item purchased for: $" + price);
                } else {
                    showAlert("Purchase Error", "Minimum price for a purchase must be at least $50.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number for the price.");
            }
        });
    }

    private void handleBalance() {
        showAlert("Balance", "Your current balance is: " + customer.getBankAccount().getBalance());
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: " + customer.getBankAccount().getBalance());
    }

    private void showAlert(String title, String message) {
        // Implementation placeholder - use JavaFX Alert for real application
        System.out.println(title + ": " + message);
    }
}

