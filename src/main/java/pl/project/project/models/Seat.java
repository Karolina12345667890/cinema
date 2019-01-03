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
    private int col;
    private boolean reserved;
    @OneToMany
    private Set<ReservationSeat> reservationSeats;
    @ManyToOne
    private User user;

    public Seat() {
    }

    public Seat(Hall hall, int seat, int row, int column, boolean reservation, Set<ReservationSeat> reservationSeats) {
        this.hall = hall;
        this.seat = seat;
        this.row = row;
        this.col = column;
        this.reserved = reservation;
        this.reservationSeats = reservationSeats;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
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
