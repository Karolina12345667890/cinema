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
//-------------------------- gatunki filmowe -----------------------------------------
                TypeMovie g1 = typeMovieRepository.save(new TypeMovie("komedia"));
                TypeMovie g2 = typeMovieRepository.save(new TypeMovie("dramat"));
                TypeMovie g3 = typeMovieRepository.save(new TypeMovie("dokument"));
                TypeMovie g4 = typeMovieRepository.save(new TypeMovie("horror"));
                TypeMovie g5 = typeMovieRepository.save(new TypeMovie("akcji"));
                TypeMovie g6 = typeMovieRepository.save(new TypeMovie("sensacyjny"));
                TypeMovie g7 = typeMovieRepository.save(new TypeMovie("musical"));
                TypeMovie g8 = typeMovieRepository.save(new TypeMovie("fantastyczny"));

//----------------------------- rodzaje biletów --------------------------------------
                TypeTicket t1 = typeTicketRepository.save(new TypeTicket("studencki"));
                TypeTicket t2 = typeTicketRepository.save(new TypeTicket("szkolny"));
                TypeTicket t3 = typeTicketRepository.save(new TypeTicket("normalny"));
                TypeTicket t4 = typeTicketRepository.save(new TypeTicket("dla seniora"));

//------------------------- ceny biletów --------------------------------------------------
                Price p1 = priceRepository.save(new Price(12,t1));
                Price p2 = priceRepository.save(new Price(13,t2));
                Price p3 = priceRepository.save(new Price(14,t3));
                Price p4 = priceRepository.save(new Price(15,t4));

//--------------------------------- filmy --------------------------------------------------------
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
                        "https://www.filmweb.pl/Avatar",
                        new HashSet<>(),
                        "Jake, sparaliżowany były komandos, zostaje wysłany na planetę Pandora, gdzie zaprzyjaźnia się z lokalną społecznością i postanawia jej pomóc. ",
                        new HashSet<>()));

                Movie m3 = movieRepository.save( new Movie(
                        "Aquaman",
                        "James Wan",
                        "https://ssl-gfx.filmweb.pl/po/90/85/549085/7867964.6.jpg",
                        "Polska",
                        "https://www.filmweb.pl/film/Aquaman-2018-549085",
                        new HashSet<>(),
                        "Arthur Curry niechętnie staje na czele ludu podwodnego królestwa Atlantydy. ",
                        new HashSet<>()));

                Movie m4 = movieRepository.save( new Movie(
                        "Spider-Man Uniwersum",
                        "Bob Persichetti",
                        "https://ssl-gfx.filmweb.pl/po/32/33/743233/7853929.3.jpg",
                        "Grecja",
                        "https://www.filmweb.pl/film/Spider-Man+Uniwersum-2018-743233",
                        new HashSet<>(),
                        "Miles Morales po ukąszeniu przez radioaktywnego pająka staje do walki z nikczemnym Kingpinem. Pomogą mu w tym Spider-Menowie z innych wymiarów.",
                        new HashSet<>()));

                Movie m5 = movieRepository.save( new Movie(
                        "Narodziny gwiazdy",
                        "Bradley Cooper",
                        "https://i1.fdbimg.pl/74ot3my1/540x755_pf5p2y.jpg",
                        "Polska",
                        "https://www.filmweb.pl/film/Narodziny+gwiazdy-2018-542576",
                        new HashSet<>(),
                        "Płomienny romans między dogasającą gwiazdą muzyki country a nieznaną piosenkarką zmienia ich życie na zawsze. ",
                        new HashSet<>()));

//---------------------------------- sale --------------------------------------------------
                Hall h1 = hallRepository.save(new Hall("1", 25, new HashSet<>(), new HashSet<>()));
                Hall h2 = hallRepository.save(new Hall("2", 25, new HashSet<>(), new HashSet<>()));
                Hall h3 = hallRepository.save(new Hall("3", 25, new HashSet<>(), new HashSet<>()));

//------------------------------------- seanse ------------------------------------------------------
                Show s1 = new Show();
                s1.setTimeShow(new Date(119,Calendar.JANUARY,29,12,58));
                s1.setHall(h1);
                s1.setMovie(m1);
                s1.setReservationSeats(new HashSet<>());
                showRepository.save(s1);
                Show s2 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,29,11,34),m2,h1,new HashSet<>()));
                Show s3 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,30,16,45),m2,h2,new HashSet<>()));
                Show s4 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,30,11,34),m3,h3,new HashSet<>()));
                Show s5 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,30,8,24),m4,h1,new HashSet<>()));
                Show s6 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,30,20,46),m5,h2,new HashSet<>()));
                Show s7 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,31,17,34),m1,h3,new HashSet<>()));
                Show s8 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,31,14,23),m3,h1,new HashSet<>()));
                Show s9 = showRepository.save(new Show(new Date(119,Calendar.JANUARY,31,15,52),m4,h2,new HashSet<>()));
                Show s10 = showRepository.save(new Show(new Date(119,Calendar.FEBRUARY,1,12,32),m1,h3,new HashSet<>()));
                Show s11 = showRepository.save(new Show(new Date(119,Calendar.FEBRUARY,1,12,23),m5,h2,new HashSet<>()));


//-------------------- sala 1------------------------------------------------
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

//-----------------------------------sala 2 -------------------------------------------
                Seat sss1 = seatRepository.save(new Seat(h2,1,1,new HashSet<>()));
                Seat sss2 = seatRepository.save(new Seat(h2,2,1,new HashSet<>()));
                Seat sss3 = seatRepository.save(new Seat(h2,3,1,new HashSet<>()));
                Seat sss4 = seatRepository.save(new Seat(h2,4,1,new HashSet<>()));
                Seat sss5 = seatRepository.save(new Seat(h2,5,1,new HashSet<>()));

                Seat sss6 = seatRepository.save(new Seat(h2,1,2,new HashSet<>()));
                Seat sss7 = seatRepository.save(new Seat(h2,2,2,new HashSet<>()));
                Seat sss8 = seatRepository.save(new Seat(h2,3,2,new HashSet<>()));
                Seat sss9 = seatRepository.save(new Seat(h2,4,2,new HashSet<>()));
                Seat sss10 = seatRepository.save(new Seat(h2,5,2,new HashSet<>()));

                Seat sss11 = seatRepository.save(new Seat(h2,1,3,new HashSet<>()));
                Seat sss12 = seatRepository.save(new Seat(h2,2,3,new HashSet<>()));
                Seat sss13 = seatRepository.save(new Seat(h2,3,3,new HashSet<>()));
                Seat sss14 = seatRepository.save(new Seat(h2,4,3,new HashSet<>()));
                Seat sss15 = seatRepository.save(new Seat(h2,5,3,new HashSet<>()));

                Seat sss16 = seatRepository.save(new Seat(h2,1,4,new HashSet<>()));
                Seat sss17 = seatRepository.save(new Seat(h2,2,4,new HashSet<>()));
                Seat sss18 = seatRepository.save(new Seat(h2,3,4,new HashSet<>()));
                Seat sss19 = seatRepository.save(new Seat(h2,4,4,new HashSet<>()));
                Seat sss20 = seatRepository.save(new Seat(h2,5,4,new HashSet<>()));

                Seat sss21 = seatRepository.save(new Seat(h2,1,5,new HashSet<>()));
                Seat sss22 = seatRepository.save(new Seat(h2,2,5,new HashSet<>()));
                Seat sss23 = seatRepository.save(new Seat(h2,3,5,new HashSet<>()));
                Seat sss24 = seatRepository.save(new Seat(h2,4,5,new HashSet<>()));
                Seat sss25 = seatRepository.save(new Seat(h2,5,5,new HashSet<>()));

//---------------------------------sala 3---------------------------------------------------------
                Seat ssss1 = seatRepository.save(new Seat(h3,1,1,new HashSet<>()));
                Seat ssss2 = seatRepository.save(new Seat(h3,2,1,new HashSet<>()));
                Seat ssss3 = seatRepository.save(new Seat(h3,3,1,new HashSet<>()));
                Seat ssss4 = seatRepository.save(new Seat(h3,4,1,new HashSet<>()));
                Seat ssss5 = seatRepository.save(new Seat(h3,5,1,new HashSet<>()));

                Seat ssss6 = seatRepository.save(new Seat(h3,1,2,new HashSet<>()));
                Seat ssss7 = seatRepository.save(new Seat(h3,2,2,new HashSet<>()));
                Seat ssss8 = seatRepository.save(new Seat(h3,3,2,new HashSet<>()));
                Seat ssss9 = seatRepository.save(new Seat(h3,4,2,new HashSet<>()));
                Seat ssss10 = seatRepository.save(new Seat(h3,5,2,new HashSet<>()));

                Seat ssss11 = seatRepository.save(new Seat(h3,1,3,new HashSet<>()));
                Seat ssss12 = seatRepository.save(new Seat(h3,2,3,new HashSet<>()));
                Seat ssss13 = seatRepository.save(new Seat(h3,3,3,new HashSet<>()));
                Seat ssss14 = seatRepository.save(new Seat(h3,4,3,new HashSet<>()));
                Seat ssss15 = seatRepository.save(new Seat(h3,5,3,new HashSet<>()));

                Seat ssss16 = seatRepository.save(new Seat(h3,1,4,new HashSet<>()));
                Seat ssss17 = seatRepository.save(new Seat(h3,2,4,new HashSet<>()));
                Seat ssss18 = seatRepository.save(new Seat(h3,3,4,new HashSet<>()));
                Seat ssss19 = seatRepository.save(new Seat(h3,4,4,new HashSet<>()));
                Seat ssss20 = seatRepository.save(new Seat(h3,5,4,new HashSet<>()));

                Seat ssss21 = seatRepository.save(new Seat(h3,1,5,new HashSet<>()));
                Seat ssss22 = seatRepository.save(new Seat(h3,2,5,new HashSet<>()));
                Seat ssss23 = seatRepository.save(new Seat(h3,3,5,new HashSet<>()));
                Seat ssss24 = seatRepository.save(new Seat(h3,4,5,new HashSet<>()));
                Seat ssss25 = seatRepository.save(new Seat(h3,5,5,new HashSet<>()));


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

                    User karolina = new User("karolina", true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    test.setPassword(passwordEncoder.encode("karolina"));

                    userRepository.save(karolina);
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
