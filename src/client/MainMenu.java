package client;

import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class MainMenu {
    private HotelResource hotelResource = HotelResource.getInstance();
    private AdminResource adminResource = AdminResource.getInstance();
    Scanner scanner = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public MainMenu() {}

    public void initialize() {
        int menuSelection = 0;

        while (true) {
            displayMainMenu();
            menuSelection = scanner.nextInt();
            featureSelect(menuSelection);
        }
    }

    public void featureSelect(int menuItem) {
        switch (menuItem){
            case 1:
                findAndReserveARoom();
                break;
            case 2:
                seeMyReservations();
                break;
            case 3:
                createAnAccount();
                break;
            case 4:
                openAdminMenu();
                break;
            case 5:
                exitApplication();
                break;
            default:
                System.out.println("No Option Selected");
                break;
        }
    }

    public void displayMainMenu() {
        StringBuilder menuSB = new StringBuilder("\nWelcome to the Hotel Reservation Application\n");
        menuSB.append("1. Find and reserve a room\n");
        menuSB.append("2. See my reservations\n");
        menuSB.append("3. Create an account\n");
        menuSB.append("4. Admin\n");
        menuSB.append("5. Exit");
        menuSB.append("\n---------------------------------------------\n");
        menuSB.append("Please select an option");

        System.out.println(menuSB);
    }

    public void findAndReserveARoom() {
        // Get the Check In and Check Out dates.
        Date[] checkInCheckOut = null;

        while (checkInCheckOut == null) {
            checkInCheckOut = getCheckInAndCheckOut();
        }
        Calendar newCheckIn = Calendar.getInstance();
        Calendar newCheckOut = Calendar.getInstance();

        // Display available rooms.
        boolean checkForAvailableRooms = true;
        int numberOfDaysOut = 0;

        do {
            Collection<IRoom> rooms = hotelResource.findARoom(checkInCheckOut[0], checkInCheckOut[1]);
            if (rooms.size() == 0) {
                System.out.println("No Rooms in those dates");
                System.out.println("Do you want to check with different dates? y/n");
                checkForAvailableRooms = scanner.next().equals("y") ? true : false;
                if (checkForAvailableRooms) {
                    System.out.println("How many days out would you like to check?");
                    numberOfDaysOut = scanner.nextInt();
                    newCheckIn.setTime(checkInCheckOut[0]);
                    newCheckIn.roll(Calendar.DATE, numberOfDaysOut);
                    checkInCheckOut[0] = newCheckIn.getTime();

                    newCheckOut.setTime(checkInCheckOut[1]);
                    newCheckOut.roll(Calendar.DATE, numberOfDaysOut);
                    checkInCheckOut[1] = newCheckOut.getTime();
                }
            }
            else {
                System.out.println("\nAvailable Free Rooms:");
                rooms.forEach(room -> {
                    if (room.isFree()) {
                        System.out.println(room);
                    }
                });

                System.out.println("\nAvailable Rooms:");
                rooms.forEach(room -> {
                    if (!room.isFree()) {
                        System.out.println(room);
                    }
                });
                break;
            }
        } while (checkForAvailableRooms);

        boolean bookRoom = isBookRoom();
        boolean haveAccount = false;

        if(bookRoom) {
            haveAccount = isHaveAccount();
            // If user has account, book reservation.
            if(haveAccount) {
                bookRoom(checkInCheckOut[0], checkInCheckOut[1]);
            }
            else {
                createAnAccount();
                bookRoom(checkInCheckOut[0], checkInCheckOut[1]);
            }
        }
    }

    public Date[] getCheckInAndCheckOut() {
        Date[] checkInCheckOutDates = new Date[2];

        System.out.println("Enter Check Date:");
        Date checkIn = new Date();
        try {
            String[] dateInput = scanner.next().split("/");
            calendar.set(Integer.parseInt(dateInput[2]), Integer.parseInt(dateInput[0]) - 1, Integer.parseInt(dateInput[1]));
            checkIn = calendar.getTime();
        }
        catch (Exception ex) {
            System.out.println("Invalid Check In date entered.");
            return null;
        }

        System.out.println("Enter Check Out Date mm/dd/yyyy");
        Date checkOut = new Date();
        try {
            String[] dateOutput = scanner.next().split("/");
            calendar.set(Integer.parseInt(dateOutput[2]), Integer.parseInt(dateOutput[0]) - 1, Integer.parseInt(dateOutput[1]));
            checkOut = calendar.getTime();
        }
        catch (Exception ex) {
            System.out.println("Wrong Date Format entered, you have to enter mm/dd/yyyy format");
            return null;
        }

        checkInCheckOutDates[0] = checkIn;
        checkInCheckOutDates[1] = checkOut;

        return checkInCheckOutDates;
    }

    public boolean isBookRoom() {
        String bookRoom = "";
        while (true) {
            System.out.println("Do you want to book a room? y/n");
            bookRoom = scanner.next().toLowerCase(Locale.ROOT);
            if(bookRoom.equals("y")) {
                return true;
            }
            else if(bookRoom.equals("n")) {
                return false;
            }
        }
    }

    public boolean isHaveAccount() {
        String haveAccount = "";
        while (true) {
            System.out.println("Do you have an account with us? y/n");
            haveAccount = scanner.next().toLowerCase(Locale.ROOT);
            if(haveAccount.equals("y")) {
                return true;
            }
            else if(haveAccount.equals("n")) {
                return false;
            }
        }
    }

    public void bookRoom(Date checkInDate, Date checkOutDate) {
        String email = "";
        String roomNumber = "";

        System.out.println("Enter Email:");
        email = scanner.next();
        System.out.println("Enter Room Number:");
        roomNumber = scanner.next();
        Reservation bookedRoom = hotelResource.bookARoom(email, hotelResource.getRoom(roomNumber), checkInDate, checkOutDate);

        if (bookedRoom != null) {
            System.out.println(bookedRoom);
        }
    }

    public void seeMyReservations() {
        System.out.println("Enter Email:");
        String email = scanner.next();

        Collection<Reservation> customerReservations = hotelResource.getCustomerReservations(email);
        if (customerReservations.size() > 0) {
            for (Reservation reservation : customerReservations) {
                System.out.println(reservation);
            }
        }
    }

    public void createAnAccount() {
        System.out.println("Enter Full Name");
        String firstName = scanner.next();
        String lastName = scanner.next();
        System.out.println("Enter Email");
        String email = scanner.next();
        hotelResource.createACustomer(email, firstName, lastName);
    }

    public void openAdminMenu() {
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.initialize();
    }

    public void exitApplication() {
        System.exit(0);
    }

}
