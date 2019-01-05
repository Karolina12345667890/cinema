package pl.project.project.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm")
    @Future
    private Date timeShow;
    @ManyToOne
    @JoinColumn(name = "movies_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;
    @OneToMany(mappedBy = "show")
    private Set<ReservationSeat> reservationSeats;
    @Transient
    private List<String> listOfReservedSeats;

    public Show() {
    }

    public Show( Date timeShow, Movie movie, Hall hall, Set<ReservationSeat> reservationSeats) {
        this.timeShow = timeShow;
        this.movie = movie;
        this.hall = hall;
        this.reservationSeats = reservationSeats;
    }

    public List<String> getListOfReservedSeats() {
        return listOfReservedSeats;
    }

    public void setListOfReservedSeats(List<String> listOfReservedSeats) {
        this.listOfReservedSeats = listOfReservedSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Set<ReservationSeat> getReservationSeats() {
        return reservationSeats;
    }

    public void setReservationSeats(Set<ReservationSeat> reservationSeats) {
        this.reservationSeats = reservationSeats;
    }

    public Date getTimeShow() {
        return timeShow;
    }

    public void setTimeShow(Date timeShow) {
        this.timeShow = timeShow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
