package model;

public class Room implements IRoom {

    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;
    private final boolean free;

    public Room(String roomNumber, Double price, RoomType enumeration, boolean free){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.free = free;
    }

    public String getRoomNumber() {
        return roomNumber;
    }



    public Double getRoomPrice() {
        return price;
    }



    public RoomType getRoomType() {
        return enumeration;
    }



    public boolean isFree(){
        return free;
    }


    @Override
    public String toString() {
        StringBuilder roomSummary = new StringBuilder("\nRoom Info");

        roomSummary.append("\n----------------------------\n");
        roomSummary.append("Room Number: ").append(getRoomNumber()).append("\n");
        roomSummary.append("Room Type: ").append(getRoomType() == RoomType.SINGLE ? "Single Bed Room" : "Double Bed Room").append("\n");
        roomSummary.append("Price: $").append(!isFree() ? getRoomPrice() : "0").append("\n");

        return roomSummary.toString();
    }
}
