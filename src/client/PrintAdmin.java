package client;

import Resources.AdminEvents;
import Resources.HotelEvents;
import model.Customer;
import model.IRoom;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class PrintAdmin {
    private HotelEvents hotelResource = HotelEvents.getInstance();
    private AdminEvents adminResource = AdminEvents.getInstance();
    Scanner scanner = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public PrintAdmin() {}

    public void displayAdminMenu() {
        StringBuilder menuSB = new StringBuilder("\nAdmin Menu");
        menuSB.append("\n---------------------------------------------\n");
        menuSB.append("1. See all Customers\n");
        menuSB.append("2. See all Rooms\n");
        menuSB.append("3. See all Reservations\n");
        menuSB.append("4. Add a Room\n");
        menuSB.append("5. Back to Main Menu\n");
        menuSB.append("6. Populate with test data");
        menuSB.append("\n---------------------------------------------\n");
        menuSB.append("Please select a number for the menu option");

        System.out.println(menuSB);
    }

    public void initialize() {
        int menuSelection = 0;

        while (true) {
            displayAdminMenu();
            menuSelection = scanner.nextInt();
            if(!featureSelect(menuSelection)) {
                break;
            }
        }
    }

    public boolean featureSelect(int menuItem) {
        switch (menuItem){
            case 1:
                displayAllCustomers();
                break;
            case 2:
                displayAllRooms();
                break;
            case 3:
                displayAllReservations();
                break;
            case 4:
                addARoom();
                break;
            case 5:
                return returnToMainMenu();

            default:
                System.out.println("No selected option");
                break;
        }

        return true;
    }

    public void displayAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        for(Customer customer : customers) {
            System.out.println(customer.getEmail());
        }
    }

    public void displayAllRooms() {
        HashMap<String, IRoom> rooms = adminResource.getAllRooms();

        if (rooms.size() < 1) {
            System.out.println("There are no rooms to display.");
        }

        for(String roomId : rooms.keySet()) {
            System.out.println(rooms.get(roomId));
        }
    }

    public void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    public void addARoom()
    {
        HashMap<String, IRoom> rooms = adminResource.getAllRooms();
        adminResource.addRoom(rooms);
    }

    public boolean returnToMainMenu() {
        return false;
    }
}
