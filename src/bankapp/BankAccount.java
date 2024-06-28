/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

/**
 *
 * @author nawid
 */
/**
 * Represents a mutable bank account with balance and account level. 
 * The account level is determined by the current balance: Silver (less than $10,000), 
 * Gold ($10,000 to $19,999), or Platinum ($20,000 and above).
 *
 * Abstraction Function: A bank account can be abstracted by its balance and account level, where 
 * the balance represents the current amount of money in the account and the level is a categorization 
 * based on the balance amount.
 *
 * Representation Invariant:
 * - Balance must be >= 0.
 * - Level must be one of "Silver", "Gold", or "Platinum" depending on the balance.
 */
public class BankAccount {
    private String level; 
    private double balance; 
    
    /**
     * Initializes a new BankAccount instance with the specified initial balance.
     * @param balance the initial balance of the account.
     * Requires: balance >= 0.
     * Effects: Sets the account's balance to the given value. If the balance is below 0, throws IllegalArgumentException.
     */
    public BankAccount(double balance){  
        if(balance<0){ 
            throw new IllegalArgumentException("Balance can't be lower than 0");
        }
        this.balance=balance;
    }
    
    /**
     * Sets the balance of this bank account to the specified amount.
     * @param balance the new balance of the account.
     * Modifies: this.balance
     * Effects: Changes the account balance to the given amount.
     */
    public void setBalance(double balance){ 
        this.balance=balance;
    }
    
    /**
     * Returns the current balance of the bank account.
     * @return The current balance.
     * Effects: Returns the current account balance.
     */
    public double getBalance(){ 
        return(balance);
    }
    
    /**
     * Checks and returns the account level based on the current balance.
     * @return The account level as a String.
     * Effects: Determines the account level based on the balance and returns it.
     */
    public String levelCheck(){ 
        if(balance<10000){
            level="Silver"; 
        } 
        else if(balance>=10000 && balance<20000){  
            level="Gold"; 
        } 
        else if(balance>=20000){ 
            level="Platinum"; 
        }  
        
        return(level);
    } 
    
    /**
     * Withdraws a specified amount from the bank account.
     * @param amount The amount to withdraw.
     * @return The new balance after withdrawal.
     * Requires: amount > 0 and amount <= balance.
     * Modifies: this.balance
     * Effects: Decreases the account balance by the specified amount if conditions are met, otherwise prints an error message.
     */
    public double withdraw(double amount){ 
        if(amount>getBalance()){
            System.out.println("Not enough money in account to make this transaction, please select a lower amount."); 
            return(balance); 
        } 
        else if(amount<=0){ 
            System.out.println("Amount to be withdrawn can't be zero or lower"); 
            return(balance);
        } 
        balance-=amount;
        return(balance);
    } 
    
    /**
     * Deposits a specified amount into the bank account.
     * @param amount The amount to deposit.
     * @return The new balance after deposit.
     * Requires: amount >= 0.
     * Modifies: this.balance
     * Effects: Increases the account balance by the specified amount if the amount is positive, otherwise prints an error message.
     */
    public double deposit(double amount){ 
        if(amount<0){ 
            System.out.println("Amount to be deposited can't be less than 0"); 
            return(balance); 
        } 
        balance+=amount; 
        return(balance);
    }  
    
    /**
     * Processes a purchase, adjusting the account balance based on the account level and the price of the item.
     * @param price The price of the item being purchased.
     * @return The new balance after purchase.
     * Requires: price >= 50.
     * Modifies: this.balance
     * Effects: Decreases the account balance by the item's price plus any applicable fees based on the account level.
     */
    public double purchase(double price){   
        level=levelCheck();
        
        if(price<50){ 
            System.out.println("Item must be above $50 to make an online purchase"); 
            return(0);
        } 
        
        if(level.equals("Silver")){  
        balance -= price + 20; // Silver members pay an additional $20 fee
        } else if (level.equals("Gold")) {
            balance -= price + 10; // Gold members pay an additional $10 fee
        } else if (level.equals("Platinum")) {
            balance -= price; // Platinum members do not pay an additional fee
        }
        return balance;
    }
    
    /**
     * Checks if the representation invariant holds for this BankAccount.
     * @return true if the representation invariant holds, otherwise false.
     * Effects: Validates that the balance is non-negative.
     */
    public boolean repOk(){ 
        return balance >= 0;
    }
    
    /**
     * Returns a string representation of this BankAccount, showing the account level and balance.
     * @return A string representation of the bank account.
     * Effects: Provides a textual representation of the bank account, including its level and balance.
     */
    @Override 
    public String toString(){ 
        return "Level: " + levelCheck() + "\nBalance: " + getBalance();
    }
}    
        