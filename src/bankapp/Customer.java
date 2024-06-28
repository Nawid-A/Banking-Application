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
public class Customer {
    private String username; 
    private String password;   
    private BankAccount account; 
    private boolean access; 
    private FileManager fileUpdater;
     
    public Customer(String username, String password, double balance){ 
     
        this.username=username; 
        this.password=password; 
        account= new BankAccount(balance); 
        fileUpdater= new FileManager();
    }  
    
    public String getUsername(){ 
        return username;
    } 
    
    public String getPassword(){ 
        return password;
    }
    
    public boolean Login(String usernameTry, String passwordTry){ 
     if(usernameTry.equals(getUsername()) && passwordTry.equals(getPassword())){  
        access=true;
        } 
     else{ 
        access=false;
     } 
     return(access);
    } 
     
    public boolean Logout(){ 
        access=false;
        return(access);
    }  
    
    public BankAccount getBankAccount(){ 
        return(account);
    }
    
    public double withdraw(double amount){  
        if(access==false){ 
            System.out.println("Not logged in, access not granted."); 
            return(0);
        } 
        double result=getBankAccount().withdraw(amount);   
        fileUpdater.writeFile(this.getUsername(), this); 
        return(result);
   
    } 
    
    public void deposit(double amount){  
        if(access==false){ 
            System.out.println("Not logged in, access not granted."); 
            return;
        }
        getBankAccount().deposit(amount); 
        fileUpdater.writeFile(this.getUsername(), this);
    }  
    
    public void purchase(double price){ 
        account.purchase(price); 
        fileUpdater.writeFile(this.getUsername(), this);
    }
    
    @Override 
    public String toString(){ 
        return("Username: "+getUsername()+ "\nPassword: "+ getPassword()+"\n"+account.toString());
    }
}
