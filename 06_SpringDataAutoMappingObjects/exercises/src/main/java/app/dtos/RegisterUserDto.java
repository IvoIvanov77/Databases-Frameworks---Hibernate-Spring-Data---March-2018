package app.dtos;

public class RegisterUserDto {

//    •	RegisterUser|<email>|<password>|<confirmPassword>|<fullName>

    private String email;

    private String fullName;

    private String password;

    private String confirmPassword;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String email, String password, String confirmPassword,  String fullName) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
