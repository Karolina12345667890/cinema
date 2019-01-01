package pl.project.project.cofig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.project.project.models.*;
import pl.project.project.repositories.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * klasa inicjalizująca dane w bd
 */
@Configuration
public class RepositoriesIntializer {

    @Autowired
    private TypeMovieRepository typeMovieRepository;

    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private HallRepository hallRepository;

    @Bean
    InitializingBean init(){
        return () -> {
            if(typeMovieRepository.findAll().isEmpty()){
                TypeMovie g1 = typeMovieRepository.save(new TypeMovie("komedia"));
                TypeMovie g2 = typeMovieRepository.save(new TypeMovie("dramat"));
                TypeMovie g3 = typeMovieRepository.save(new TypeMovie("dokument"));
                TypeMovie g4 = typeMovieRepository.save(new TypeMovie("horror"));
                TypeMovie g5 = typeMovieRepository.save(new TypeMovie("akcji"));
                TypeMovie g6 = typeMovieRepository.save(new TypeMovie("sensacyjny"));
                TypeMovie g7 = typeMovieRepository.save(new TypeMovie("musical"));
                TypeMovie g8 = typeMovieRepository.save(new TypeMovie("fantastyczny"));


                TypeTicket t1 = typeTicketRepository.save(new TypeTicket("studencki"));
                TypeTicket t2 = typeTicketRepository.save(new TypeTicket("szkolny"));
                TypeTicket t3 = typeTicketRepository.save(new TypeTicket("normalny"));
                TypeTicket t4 = typeTicketRepository.save(new TypeTicket("dla seniora"));


                Price p1 = priceRepository.save(new Price(12.50f,t1));
                Price p2 = priceRepository.save(new Price(13.50f,t2));
                Price p3 = priceRepository.save(new Price(14.50f,t3));
                Price p4 = priceRepository.save(new Price(15.50f,t4));


                Movie m1 = movieRepository.save( new Movie(
                        "Iron Man",
                        "Jon Favreau",
                        "https://1.allegroimg.com/s512/0352e3/3d20b15844268687254be8553ed1",
                        new HashSet<>(),
                        "Tony Stark buduje supernowoczesną zbroję. Multimiliarder postanawia walczyć ze złem jako Iron Man."));

                Movie m2 = movieRepository.save( new Movie(
                        "Avatar",
                        " James Cameron",
                        "https://ocdn.eu/pulscms-transforms/1/-huktkpTURBXy8yZGJhOWQxYmE3MTYyMjk2YWE3NWYzNDMyM2JmZTIzZC5qcGeSlQLNA8AAwsOVAgDNA8DCww",
                        new HashSet<>(),
                        "Jake, sparaliżowany były komandos, zostaje wysłany na planetę Pandora, gdzie zaprzyjaźnia się z lokalną społecznością i postanawia jej pomóc. "));

                Movie m3 = movieRepository.save( new Movie(
                        "Aquaman",
                        "James Wan",
                        "https://ssl-gfx.filmweb.pl/po/90/85/549085/7867964.6.jpg",
                        new HashSet<>(),
                        "Arthur Curry niechętnie staje na czele ludu podwodnego królestwa Atlantydy. "));

                Movie m4 = movieRepository.save( new Movie(
                        "Spider-Man Uniwersum",
                        "Bob Persichetti",
                        "https://ssl-gfx.filmweb.pl/po/32/33/743233/7853929.3.jpg",
                        new HashSet<>(),
                        "Miles Morales po ukąszeniu przez radioaktywnego pająka staje do walki z nikczemnym Kingpinem. Pomogą mu w tym Spider-Menowie z innych wymiarów."));

                Movie m5 = movieRepository.save( new Movie(
                        "Narodziny gwiazdy",
                        "Bradley Cooper",
                        "https://i1.fdbimg.pl/74ot3my1/540x755_pf5p2y.jpg",
                        new HashSet<>(),
                        "Płomienny romans między dogasającą gwiazdą muzyki country a nieznaną piosenkarką zmienia ich życie na zawsze. "));


                Hall h1 = hallRepository.save(new Hall("1", 100, new HashSet<>(), new HashSet<>()));
                Hall h2 = hallRepository.save(new Hall("2", 100, new HashSet<>(), new HashSet<>()));
                Hall h3 = hallRepository.save(new Hall("3", 100, new HashSet<>(), new HashSet<>()));
                Hall h4 = hallRepository.save(new Hall("4", 100, new HashSet<>(), new HashSet<>()));
                Hall h5 = hallRepository.save(new Hall("5", 100, new HashSet<>(), new HashSet<>()));
                Hall h6 = hallRepository.save(new Hall("6", 100, new HashSet<>(), new HashSet<>()));

                Calendar c= Calendar.getInstance();
                Calendar c1= Calendar.getInstance();
                Calendar c2= Calendar.getInstance();
                c1.set(2019,5,6,12,58);
                Show s1 = new Show();
                s1.setDate(c1);
                s1.setHall(h1);
                s1.setMovie(m1);
                s1.setReservationSeats(new HashSet<>());
                showRepository.save(s1);


                //c= Calendar.getInstance();
                c.set(2019,4,15,15,34);
                Show s2 = showRepository.save(new Show(c,m1,h1,new HashSet<>()));
                c2.set(2019,12,16,15,45);
                Show s3 = showRepository.save(new Show(c2,m1,h1,new HashSet<>()));


            }
        };
    }


}
