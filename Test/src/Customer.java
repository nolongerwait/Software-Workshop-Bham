import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Customer {
    private String customerName;
    private String address;
    private String telephoneNumber;

    public Customer(String customerName, String address, String telephoneNumber) {
        this.customerName = customerName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public static ArrayList<Customer> findLocalCustomers(ArrayList<Customer> allCustomer, String areaCode) {
        ArrayList<Customer> localCustomers = new ArrayList<Customer>();

        for(Customer itor:allCustomer) {
            if(itor.getTelephoneNumber().startsWith(areaCode)) {
                localCustomers.add(itor);
            }
        }

        return localCustomers;
    }

    public void generateWelcomeEmails(ArrayList<Customer> localCustomers) throws IOException {
        for(Customer itor:localCustomers) {
            File checkFile = new File(itor.getCustomerName() + ".java");
            FileWriter writer = null;
            try {
                if(!checkFile.exists()) {
                    checkFile.createNewFile();
                }
                writer = new FileWriter(checkFile, true);

                String contentofEmail = "To: " + itor.getAddress() + "\n" 
                + "From: ACME\n" 
                + "Subject: Welcome to new local service\n" 
                + "--------\n" 
                + "Dear" + itor.getCustomerName() + ",\n" 
                + "Welcome to our new local service.";

                writer.append(contentofEmail);
                writer.flush();
            }
            catch(IOException exception) {
                exception.printStackTrace();
            }
            finally {
                if (null != writer) {
                    writer.close();
                }
            }
        }
    }
}