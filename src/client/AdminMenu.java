package client;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class AdminMenu {
    private HotelResource hotelResource = HotelResource.getInstance();
    private AdminResource adminResource = AdminResource.getInstance();
    Scanner scanner = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public AdminMenu() {}

    public void displayAdminMenu() {
        StringBuilder menuSB = new StringBuilder("\nAdmin Menu");
        menuSB.append("\n---------------------------------------------\n");
        menuSB.append("1. See all Customers\n");
        menuSB.append("2. See all Rooms\n");
        menuSB.append("3. See all Reservations\n");
        menuSB.append("4. Add a Room\n");
        menuSB.append("5. Back to Main Menu\n");
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
        HashMap<String, Customer> customers = adminResource.getAllCustomers();

        for(String email : customers.keySet()) {
            System.out.println(customers.get(email));
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
