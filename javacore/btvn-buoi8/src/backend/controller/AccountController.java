package backend.controller;

import backend.service.IQLAccount;
import backend.service.impl.QLAccount;
import entity.Account;
import entity.Position;

import java.sql.SQLException;
import java.util.List;

public class AccountController {
    private final IQLAccount accountService;

    public AccountController() {
        accountService = new QLAccount();
    }

    public List<Account> getAllAccounts() throws SQLException {
        return accountService.getAllAccounts();
    }

    public Account findAccountByUsername(String username) throws SQLException {
        return accountService.findAccountByUsername(username);
    }

    public List<Position> getAllPositions() throws SQLException {
        return accountService.getAllPositions();
    }

    public boolean isValidUsername(String username) throws SQLException {
        return accountService.isValidUsername(username);
    }

    public boolean isValidEmail(String email) throws SQLException {
        return accountService.isValidEmail(email);
    }

    public boolean isAccountIdExists(int id) throws SQLException {
        return accountService.isAccountIdExists(id);
    }

    public boolean addAccount(Account account) throws SQLException {
        return accountService.addAccount(account);
    }

    public boolean updateUsernameById(int id, String username) throws SQLException {
        return accountService.updateUsernameById(id, username);
    }

    public boolean deleteAccountById(int id) throws SQLException {
        return accountService.deleteAccountById(id);
    }
}
