package pl.project.project.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "reservatins")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "reservation")
    private Set<ReservationSeat> reservationSeats;

    public Reservation(Set<ReservationSeat> reservationSeats) {
        this.reservationSeats = reservationSeats;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<ReservationSeat> getReservationSeats() {
        return reservationSeats;
    }

    public void setReservationSeats(Set<ReservationSeat> reservationSeats) {
        this.reservationSeats = reservationSeats;
    }
}
