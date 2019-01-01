package pl.project.project.models;

import javax.persistence.*;
import java.util.Set;


    @Entity
    @Table(name = "halls")
    public class Hall {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private int seatNumber;
        @OneToMany(mappedBy = "hall")
        private Set<Show> shows;
        @OneToMany(mappedBy = "hall")
        private Set<Seat> seats;

        public Hall(String name, int seatNumber, Set<Show> shows, Set<Seat> seats) {
            this.name = name;
            this.seatNumber = seatNumber;
            this.shows = shows;
            this.seats = seats;
        }

        public Hall() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(int seatNumber) {
            this.seatNumber = seatNumber;
        }

        public Set<Show> getShows() {
            return shows;
        }

        public void setShows(Set<Show> shows) {
            this.shows = shows;
        }

        public Set<Seat> getSeats() {
            return seats;
        }

        public void setSeats(Set<Seat> seats) {
            this.seats = seats;
        }
    }
