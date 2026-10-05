package backend;

import entity.Account;
import java.util.List;

public interface IQLAccount {
    List<Account> getAll();
    Account findByUsername(String username);
    boolean add(Account account);
    boolean deleteByUsername(String username);
    boolean updateFullName(String username, String fullName);
}
