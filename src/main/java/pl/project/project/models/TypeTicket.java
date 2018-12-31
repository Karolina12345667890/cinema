package pl.project.project.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="typeTickets")
public class TypeTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Length(min = 2, max = 35)
    private String name;

    public TypeTicket(@NotBlank @Length(min = 2, max = 35) String name) {
        this.name = name;
    }

    public TypeTicket()
    {

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
}
