package app;

import app.models.Account;
import app.models.User;
import app.services.AccountService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... strings) throws Exception {

        User example = new User();
        example.setUsername("example");
        example.setAge(20);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));
        example.getAccounts().add(account);
        this.userService.registerUser(example);

        this.accountService.withdrawMoney(new BigDecimal(20000), account.getId());
        this.accountService.transferMoney(new BigDecimal(2000), account.getId());

    }
}
