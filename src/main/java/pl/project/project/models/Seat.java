package pl.project.project.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;
    private int seat;
    private int row;
    @OneToMany(mappedBy = "seat")
    private Set<ReservationSeat> reservationSeats;

    public Seat() {
    }

    public Seat(Hall hall, int seat, int row, Set<ReservationSeat> reservationSeats) {
        this.hall = hall;
        this.seat = seat;
        this.row = row;
        this.reservationSeats = reservationSeats;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Set<ReservationSeat> getReservationSeats() {
        return reservationSeats;
    }

    public void setReservationSeats(Set<ReservationSeat> reservationSeats) {
        this.reservationSeats = reservationSeats;
    }
}
