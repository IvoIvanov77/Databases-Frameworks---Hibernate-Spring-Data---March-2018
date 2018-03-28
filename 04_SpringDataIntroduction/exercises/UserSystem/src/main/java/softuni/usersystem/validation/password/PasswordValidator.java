package softuni.usersystem.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;

    private int maxLength;

    private boolean containLowerCaseLetter;

    private boolean containUpperCaseLetter;

    private boolean containDigit;

    private boolean containSymbol;



    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containLowerCaseLetter = password.containLowerCaseLetter();
        this.containUpperCaseLetter = password.containUpperCaseLetter();
        this.containDigit = password.containDigit();
        this.containSymbol = password.containSymbol();

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean containLowerCaseLetter = checkForLowerCaseLetter(password);
        boolean containUpperCaseLetter = checkForUpperCaseLetter(password);
        boolean containDigit = checkForDigit(password);
        boolean containSymbol = checkForSpecialSymbol(password);
        int length = password.length();

        return     length >= this.minLength
                && length <= this.maxLength
                && (containLowerCaseLetter || !this.containLowerCaseLetter)
                && (containUpperCaseLetter || !this.containUpperCaseLetter)
                && (containDigit || !this.containDigit)
                && (containSymbol || !this.containSymbol);
    }

    private boolean checkForSpecialSymbol(String password) {

        return password.matches(".*[!@#$%^&*()_+<>?]+.*");
    }

    private boolean checkForDigit(String password) {
        return password.matches(".*[0-9]+.*");
    }

    private boolean checkForUpperCaseLetter(String password) {
        return password.matches(".*[A-Z]+.*");
    }

    private boolean checkForLowerCaseLetter(String password) {
        return password.matches(".*[A-Z]+.*");
    }
}
