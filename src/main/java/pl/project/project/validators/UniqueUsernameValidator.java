package pl.project.project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.project.project.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired(required = false)
    private UserService userService;
    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return userService == null || (login != null && userService.isUniqueLogin(login));
    }
}
