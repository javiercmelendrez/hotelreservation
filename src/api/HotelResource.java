package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelEvent = new HotelResource();
    private CustomerService customerService = CustomerService.newObject();
    private ReservationService reservationService = ReservationService.newObject();

    private HotelResource() {}

    public static HotelResource getInstance() {
        return hotelEvent;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        return reservationService.getCustomersReservation(customerService.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return (Collection<IRoom>) reservationService.findRooms(checkIn, checkOut);
    }
}
