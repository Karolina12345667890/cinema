package pl.project.project.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Length(max = 80)
    private String name;
    @Length(min = 5, max = 50)
    private String director;

    @Length(min=3, max=50000)
    private String poster;

    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name="type_movie_id", nullable = false)
    private Set<TypeMovie> typeMovie;
    @NotBlank
    private String description;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Show> shows;
    @NotBlank
    @Length(max = 30)
    private String country;
    @NotBlank
    //@Length(max = 50)
    private String adnotation;

    public Movie(@NotBlank @Length(max = 80) String name, @Length(min = 5, max = 50) String director, @Length(min = 3, max = 50000) String poster, String country,String adnotation,  Set<TypeMovie> typeMovie, @NotBlank String description, Set<Show> shows) {
        this.name = name;
        this.director = director;
        this.poster = poster;
        this.typeMovie = typeMovie;
        this.description = description;
        this.shows = shows;
        this.country = country;
        this.adnotation = adnotation;
    }

//    public Movie(@NotBlank @Length(max = 80) String name, @Length(min = 5, max = 50) String director, String poster, Set<TypeMovie> typeMovie, @NotBlank String description) {
//        this.name = name;
//        this.director = director;
//        this.poster = poster;
//        this.typeMovie = typeMovie;
//        this.description = description;
//    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdnotation() {
        return adnotation;
    }

    public void setAdnotation(String adnotation) {
        this.adnotation = adnotation;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }

    public Movie()
    {
        typeMovie = new HashSet<>();
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Set<TypeMovie> getTypeMovie() {
        return typeMovie;
    }

    public void setTypeMovie(Set<TypeMovie> typeMovie) {
        this.typeMovie = typeMovie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

