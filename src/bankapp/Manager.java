/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;
import java.util.ArrayList;  
import java.util.List;


/**
 *
 * @author nawid
 */
public class Manager {
    private String username="admin"; 
    private String password="admin"; 
    public ArrayList<Customer> customers;    
    private FileManager files;
    private boolean access;  
    
    public Manager(){ 
        customers= new ArrayList<>();  
        Customer temp=new Customer("John","john",100);
        customers.add(temp);
        files= new FileManager(); 
        files.createFile(temp.getUsername()); 
        files.writeFile(temp.getUsername(), temp); 
        loadCustomersFromFile();

    }   
    
    private void loadCustomersFromFile() {
        List<Customer> loadedCustomers = files.loadCustomers();
        for (Customer customer : loadedCustomers) {
            buildCustomers(customer.getUsername(), customer.getPassword(), customer.getBankAccount().getBalance());
        } 
    }
     
    public void buildCustomers(String username, String password, double balance){ 
        Customer temp= new Customer(username, password, balance);
        customers.add(temp);
    } 
    
    public String getUsername(){ 
        return username;
    } 
    
    public String getPassword(){ 
        return password;
    } 
    
    public FileManager getFile(){ 
        return files;
    }
    
    public boolean Login(String usernameTry, String passwordTry){ 
     if(usernameTry.equals(getUsername()) && passwordTry.equals(getPassword())){  
        System.out.println("Login successful. Access to bank account granted."); 
        access=true;
        } 
     else{ 
        System.out.println("Password or username incorrect. Login unsuccessful.");  
        access=false;
     } 
     return(access);
    } 
     
    public boolean Logout(){ 
        access=false;
        return(access);
    } 
    
    public void addCustomer(String C_username, String C_password){ 
        if(access==false){  
            System.out.println("Login must be authenticated to have access to adding customers."); 
            return;
        }    
        boolean taken=false;
        
        for(Customer i: customers){  
            if(i.getUsername().equals(C_username)){
                taken=true;
            }
        } 
        
        if(taken==true){ 
        System.out.println("Username already taken, try something else");  
            return;
        }
          
        else{ 
        Customer temp= new Customer(C_username, C_password, 100);
        customers.add(temp);  
        files.createFile(C_username); 
        files.writeFile(C_username, temp);
        //Create a new file with customer details  
            } 
        }
      
    
    public void removeCustomer(String user){
    if(access==false){  
            System.out.println("Login must be authenticated to have access to removing customers."); 
            return;
    }   
     
    boolean exists=false;  
    
    for(Customer i: customers){  
            if(i.getUsername().equals(user)){
                exists=true;
            }
        } 
        
    if(exists==false){ 
        System.out.println("Customer does not exist or has already been deleted from the database");  
            return;
        }
    
    else{
    Customer temp=null; 
    int j=0;
    
    for (int i=0; i<customers.size(); i++){ 
        if(customers.get(i).getUsername().equals(user)){  
            j=i;  
            temp=customers.get(i);
            break; 
        }  
    }
        
        if (temp!=null){
        customers.remove(j);   
        files.deleteFile(temp.getUsername()); 
        System.out.println("Customer removed and file deleted.");
        }
        //Remove file related to deleted customer 
        //maybe include a condition for if the specified user doesn't exist in the arraylist 
        else{ 
        System.out.println("Customer could not be found");
        }
    
    }
    }
}