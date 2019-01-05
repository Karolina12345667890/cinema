package pl.project.project.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservatins")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "reservation")
    private Set<ReservationSeat> reservationSeats;
    @Transient
    private List<String> listOfReservedSeats;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Reservation(Set<ReservationSeat> reservationSeats) {
        this.reservationSeats = reservationSeats;
    }

    public Reservation() {
    }

    public Reservation(Set<ReservationSeat> reservationSeats, User user) {
        this.reservationSeats = reservationSeats;
        this.user = user;
    }

    public List<String> getListOfReservedSeats() {
        return listOfReservedSeats;
    }

    public void setListOfReservedSeats(List<String> listOfReservedSeats) {
        this.listOfReservedSeats = listOfReservedSeats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
