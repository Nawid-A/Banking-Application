package bankapp;

import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;  
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    
    private final String directoryPath = "src/bankapp/Files"; // Folder name within the project directory
    
    public FileManager() {
        // Ensure the directory exists when FileManager is instantiated
        new File(directoryPath).mkdirs();
    } 
     
    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();  
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                // Assuming the file's name is the customer's username and contains balance info.
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    double balance = 0; // Example: Initialize variables to store data. 
                    String password="";
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Balance: ")) {
                            balance = Double.parseDouble(line.replace("Balance: ", ""));
                        } 
                        if (line.startsWith("Password: ")){ 
                            password+= line.replace("Password: ", "");
                        }
                        // Parse other details similarly.
                    }
                    // Assuming the filename or content gives you username and password, adjust as needed.
                    String username = fileName.replace(".txt", ""); // Example if filename is the username.
                    customers.add(new Customer(username, password, balance)); // Modify according to actual data.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }
    
    // Method to create a new file in the specific directory
    public void createFile(String fileName) {  
        try {
            File myObj = new File(directoryPath + File.separator + fileName + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getPath());
            } else { 
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 
     
    // Method to write to a file in the specific directory
    public void writeFile(String fileName, Customer customer) { 
        try {
            FileWriter myWriter = new FileWriter(directoryPath + File.separator + fileName + ".txt");
            myWriter.write(customer.toString()); // Assuming Customer class has a properly overridden toString method
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 
     
    // Method to delete a file from the specific directory
    public void deleteFile(String fileName) { 
        File myObj = new File(directoryPath + File.separator + fileName + ".txt"); 
        if (myObj.delete()) { 
            System.out.println("Deleted the file: " + myObj.getPath());
        } else {
            System.out.println("Failed to delete the file. Make sure the file exists and the path is correct.");
        } 
    }
}
