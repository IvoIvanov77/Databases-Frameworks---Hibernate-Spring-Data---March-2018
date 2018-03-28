package app.services;

import app.models.Account;
import app.repositories.AccountRepository;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {

        Account account = this.accountRepository.findOne(id);
        if(account == null){
            throw new NullPointerException("no such account");
        }
        account.setBalance(account.getBalance().subtract(amount));
        this.accountRepository.save(account);


    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {
        if(amount.doubleValue() < 0){
            throw new IllegalArgumentException("balance can't be negative");
        }
        Account account = this.accountRepository.findOne(id);
        if(account == null){
            throw new NullPointerException("no such account");
        }
        account.setBalance(account.getBalance().add(amount));
        this.accountRepository.save(account);
    }
}
