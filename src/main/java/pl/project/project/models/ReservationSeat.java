package pl.project.project.models;

import javax.persistence.*;

@Entity
@Table(name = "reservationSeats")
public class ReservationSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "reservations_id")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name= "price_id")
    private Price price;


    public ReservationSeat(Show show, Seat seat, Reservation reservation, Price price) {
        this.show = show;
        this.seat = seat;
        this.reservation = reservation;
        this.price = price;
    }

    public ReservationSeat() {
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
