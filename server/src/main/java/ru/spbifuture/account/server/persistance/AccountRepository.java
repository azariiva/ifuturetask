package ru.spbifuture.account.server.persistance;

import org.springframework.data.repository.CrudRepository;
import ru.spbifuture.account.server.businesslogic.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
