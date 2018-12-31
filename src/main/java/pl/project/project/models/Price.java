package pl.project.project.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Positive
    @Max(1000000)
    private float price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_ticket_id", nullable = false)
    private TypeTicket typeTicket;

    public Price(@Positive @Max(1000000) float price, TypeTicket typeTicket) {
        this.price = price;
        this.typeTicket = typeTicket;
    }

    public Price()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }
}
