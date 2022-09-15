package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.HashMap;

public class AdminResource {
    private static AdminResource adminEvent = new AdminResource();
    private CustomerService customerService = CustomerService.newObject();
    private ReservationService reservationService = ReservationService.newObject();

    private AdminResource() {}

    public static AdminResource getInstance() {
        return adminEvent;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(HashMap<String, IRoom> rooms) {
        reservationService.addRoom(rooms);
    }

    public HashMap<String, IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return  customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
