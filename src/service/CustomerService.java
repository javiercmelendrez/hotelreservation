package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private final static CustomerService customerService = new CustomerService();
    private Map<String, Customer> customers = new HashMap<String, Customer>();

    public CustomerService(){}

    public static CustomerService newObject(){
        return customerService;
    }
    public void addCustomer(String email, String firstName, String lastName)
    {
        customers.put(email, new Customer(firstName,lastName,email));
    }

    public Customer getCustomer(String customerEmail)
    {
        if(customers.get(customerEmail) == null){
            System.out.println("This emails does not exists : " + customerEmail);
            return null;
        }

        return customers.get(customerEmail);
    }

    /*
    public Collection<Customer> getAllCustomers(){
        if(customers.size() == 0){
            System.out.println("No customers");
        }

        List<Customer> customerList = new ArrayList<Customer>(customers.values());
        return customerList;
    }
    */


    public HashMap<String, Customer> getAllCustomers() {
        if (customers.size() == 0) {
            System.out.println("There are currently no customers");
        }

        return (HashMap<String, Customer>) customers;
    }

}
