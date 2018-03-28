package softuni.usersystem.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String pattern = "^[a-zA-Z0-9]+([\\.\\-_]*[a-zA-Z0-9]+)*@[a-zA-Z]+(\\.[a-zA-Z]+)+$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);

        return m.matches();
    }
}
