package pl.project.project.models;

import javax.persistence.*;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="price_id", nullable = false)
    private Price priceId;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="show_id", nullable = false)
   // private Show showId;

    public Ticket(int id, Price priceId ) {
        this.id = id;
        this.priceId = priceId;
       // this.showId = showId;
    }

    public Ticket()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Price getPriceId() {
        return priceId;
    }

    public void setPriceId(Price priceId) {
        this.priceId = priceId;
    }

//    public Show getShowId() {
//        return showId;
//    }
//
//    public void setShowId(Show showId) {
//        this.showId = showId;
//    }
}
