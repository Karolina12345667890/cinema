package pl.project.project.cofig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.project.project.models.*;
import pl.project.project.repositories.*;

import java.util.Arrays;
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
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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


                Price p1 = priceRepository.save(new Price(12,t1));
                Price p2 = priceRepository.save(new Price(13,t2));
                Price p3 = priceRepository.save(new Price(14,t3));
                Price p4 = priceRepository.save(new Price(15,t4));


                Movie m1 = movieRepository.save( new Movie(
                        "Iron Man",
                        "Jon Favreau",
                        "https://1.allegroimg.com/s512/0352e3/3d20b15844268687254be8553ed1",
                        "USA",
                        "https://www.filmweb.pl/Iron.Man",
                        new HashSet<>(),
                        "Tony Stark buduje supernowoczesną zbroję. Multimiliarder postanawia walczyć ze złem jako Iron Man.",
                        new HashSet<>()));

                Movie m2 = movieRepository.save( new Movie(
                        "Avatar",
                        " James Cameron",
                        "https://ocdn.eu/pulscms-transforms/1/-huktkpTURBXy8yZGJhOWQxYmE3MTYyMjk2YWE3NWYzNDMyM2JmZTIzZC5qcGeSlQLNA8AAwsOVAgDNA8DCww",
                        "USA",
                        "aaa",
                        new HashSet<>(),
                        "Jake, sparaliżowany były komandos, zostaje wysłany na planetę Pandora, gdzie zaprzyjaźnia się z lokalną społecznością i postanawia jej pomóc. ",
                        new HashSet<>()));

                Movie m3 = movieRepository.save( new Movie(
                        "Aquaman",
                        "James Wan",
                        "https://ssl-gfx.filmweb.pl/po/90/85/549085/7867964.6.jpg",
                        "Polska",
                        "aaa",
                        new HashSet<>(),
                        "Arthur Curry niechętnie staje na czele ludu podwodnego królestwa Atlantydy. ",
                        new HashSet<>()));

                Movie m4 = movieRepository.save( new Movie(
                        "Spider-Man Uniwersum",
                        "Bob Persichetti",
                        "https://ssl-gfx.filmweb.pl/po/32/33/743233/7853929.3.jpg",
                        "Grecja",
                        "aaa",
                        new HashSet<>(),
                        "Miles Morales po ukąszeniu przez radioaktywnego pająka staje do walki z nikczemnym Kingpinem. Pomogą mu w tym Spider-Menowie z innych wymiarów.",
                        new HashSet<>()));

                Movie m5 = movieRepository.save( new Movie(
                        "Narodziny gwiazdy",
                        "Bradley Cooper",
                        "https://i1.fdbimg.pl/74ot3my1/540x755_pf5p2y.jpg",
                        "Polska",
                        "aaa",
                        new HashSet<>(),
                        "Płomienny romans między dogasającą gwiazdą muzyki country a nieznaną piosenkarką zmienia ich życie na zawsze. ",
                        new HashSet<>()));


                Hall h1 = hallRepository.save(new Hall("1", 25, new HashSet<>(), new HashSet<>()));
                Hall h2 = hallRepository.save(new Hall("2", 25, new HashSet<>(), new HashSet<>()));
                Hall h3 = hallRepository.save(new Hall("3", 25, new HashSet<>(), new HashSet<>()));

//                Calendar c= Calendar.getInstance();
//                Calendar c1= Calendar.getInstance();
//                Calendar c2= Calendar.getInstance();
//                c1.set(2019,5,6,12,58);
                Show s1 = new Show();
                s1.setTimeShow(new Date(119,1,9,12,58));
                s1.setHall(h1);
                s1.setMovie(m1);
                s1.setReservationSeats(new HashSet<>());
                showRepository.save(s1);


                //c= Calendar.getInstance();
//                c.set(2019,4,15,15,34);
                Show s2 = showRepository.save(new Show(new Date(119,1,9,11,34),m1,h1,new HashSet<>()));
//                c2.set(2019,12,16,15,45);
                Show s3 = showRepository.save(new Show(new Date(119,1,9,15,45),m1,h1,new HashSet<>()));


                Seat ss1 = seatRepository.save(new Seat(h1,1,1,new HashSet<>()));
                Seat ss2 = seatRepository.save(new Seat(h1,2,1,new HashSet<>()));
                Seat ss3 = seatRepository.save(new Seat(h1,3,1,new HashSet<>()));
                Seat ss4 = seatRepository.save(new Seat(h1,4,1,new HashSet<>()));
                Seat ss5 = seatRepository.save(new Seat(h1,5,1,new HashSet<>()));

                Seat ss6 = seatRepository.save(new Seat(h1,1,2,new HashSet<>()));
                Seat ss7 = seatRepository.save(new Seat(h1,2,2,new HashSet<>()));
                Seat ss8 = seatRepository.save(new Seat(h1,3,2,new HashSet<>()));
                Seat ss9 = seatRepository.save(new Seat(h1,4,2,new HashSet<>()));
                Seat ss10 = seatRepository.save(new Seat(h1,5,2,new HashSet<>()));

                Seat ss11 = seatRepository.save(new Seat(h1,1,3,new HashSet<>()));
                Seat ss12 = seatRepository.save(new Seat(h1,2,3,new HashSet<>()));
                Seat ss13 = seatRepository.save(new Seat(h1,3,3,new HashSet<>()));
                Seat ss14 = seatRepository.save(new Seat(h1,4,3,new HashSet<>()));
                Seat ss15 = seatRepository.save(new Seat(h1,5,3,new HashSet<>()));

                Seat ss16 = seatRepository.save(new Seat(h1,1,4,new HashSet<>()));
                Seat ss17 = seatRepository.save(new Seat(h1,2,4,new HashSet<>()));
                Seat ss18 = seatRepository.save(new Seat(h1,3,4,new HashSet<>()));
                Seat ss19 = seatRepository.save(new Seat(h1,4,4,new HashSet<>()));
                Seat ss20 = seatRepository.save(new Seat(h1,5,4,new HashSet<>()));

                Seat ss21 = seatRepository.save(new Seat(h1,1,5,new HashSet<>()));
                Seat ss22 = seatRepository.save(new Seat(h1,2,5,new HashSet<>()));
                Seat ss23 = seatRepository.save(new Seat(h1,3,5,new HashSet<>()));
                Seat ss24 = seatRepository.save(new Seat(h1,4,5,new HashSet<>()));
                Seat ss25 = seatRepository.save(new Seat(h1,5,5,new HashSet<>()));




            }
            if(roleRepository.findAll().isEmpty()){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("useradmin", true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    test.setPassword(passwordEncoder.encode("useradmin"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
    }


}
