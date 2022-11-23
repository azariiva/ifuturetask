package ru.spbifuture.account.server.businesslogic;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spbifuture.account.server.persistance.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getAmount(Integer id) {
        var account = accountRepository.findById(id);
        if (account.isEmpty()) {
            return 0L;
        }
        return account.get().getAmount();
    }

    @Transactional
    @Override
    public void addAmount(Integer id, Long value) {
        accountRepository.findById(id).ifPresentOrElse(account -> {
            account.setAmount(account.getAmount() + value);
            accountRepository.save(account);
        }, () -> accountRepository.save(new Account(id, value)));
    }
}
