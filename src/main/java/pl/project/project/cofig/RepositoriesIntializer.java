package pl.project.project.cofig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.project.project.models.Movie;
import pl.project.project.models.Price;
import pl.project.project.models.TypeMovie;
import pl.project.project.models.TypeTicket;
import pl.project.project.repositories.*;

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
//
//    @Autowired
//    private HallRepository hallRepository;
//
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


                movieRepository.save( new Movie("Iron Man", "director", "poster", new HashSet<>(), "descripton"));

               // hallRepository.save(new Hall("1",2, 100, new HashSet<>(), new HashSet<>()));


            }
        };
    }


}
