package pl.project.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "No such movie")
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(){
        super(String.format("Film nie istnieje!"));
    }
    public MovieNotFoundException(Integer id){
        super(String.format("Film o id %d nie istnieje!", id));
    }
}

