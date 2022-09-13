package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService();
    List<Reservation> reservations = new ArrayList<>();
    Map<String, IRoom> rooms = new HashMap<>();

    public static ReservationService newObject(){
        return reservationService;
    }

    public void addRoom(IRoom room){
        Room newRoom = new Room();
        Scanner scanner = new Scanner(System.in);
        String roomNumber = "";
        double roomPrice = 0.0;
        RoomType roomType;
        int roomTypeInput = 0;
        boolean anotherRoom = true;

        while(anotherRoom) {
            System.out.println("Enter room number");
            roomNumber = scanner.next();

            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            roomTypeInput = scanner.nextInt();
            roomType = getRoomType(roomTypeInput);
            if (roomType == null) {
                System.out.println("Invalid room type");
                break;
            }

            System.out.println("Is this a free room? y/n");
            String freeRoomInput = scanner.next();
            if(freeRoomInput.equals("y")) {
                newRoom = new FreeRoom(roomNumber, roomPrice, roomType=roomTypeInput == 1 ? RoomType.SINGLE : RoomType.DOUBLE);
            }
            else {
                System.out.println("Enter price per night");
                roomPrice = scanner.nextDouble();
                newRoom = new Room(roomNumber, roomPrice, roomType = roomTypeInput == 1 ? RoomType.SINGLE : RoomType.DOUBLE, false);
            }
            rooms.put(roomNumber, newRoom);

            System.out.println("Would you like to add another room? y/n");
            String anotherRoomInput = scanner.next();
            anotherRoom = anotherRoomInput.equals("y") ? true : false;
        }
    }

    public Room getARoom(String roomId){
        
    }

    public Reservation reserveARoom(Customer customer, Room room, Date checkInDate, Date checkOutDate){

    }

    public Collection<Room> findRooms(Date checkInDate, Date checkOutDate){

    }

    public Collection<Reservation> getCustomersReservation(Customer customer){

    }

    public void printAllReservation(){}

    private RoomType getRoomType(int roomTypeAsInt) {
        RoomType roomType = null;

        if(roomTypeAsInt == 1) {
            roomType = RoomType.SINGLE;
        }
        else if (roomTypeAsInt == 2) {
            roomType = RoomType.DOUBLE;
        }

        return roomType;
    }



}
