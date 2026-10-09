package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IQLAccount;
import entity.Account;
import entity.Position;

import java.sql.SQLException;
import java.util.List;

public class QLAccount implements IQLAccount {
    private final IAccountRepository accountRepository;

    public QLAccount() {
        accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountByUsername(String username) throws SQLException {
        if (username == null || username.trim().isEmpty()) return null;
        return accountRepository.findByUsername(username.trim());
    }

    @Override
    public List<Position> getAllPositions() throws SQLException {
        return accountRepository.findAllPositions();
    }

    @Override
    public boolean isValidUsername(String username) throws SQLException {
        if (username == null) return false;
        String value = username.trim();
        return value.length() >= 5 && value.length() <= 50
                && !accountRepository.existsByUsername(value);
    }

    @Override
    public boolean isValidEmail(String email) throws SQLException {
        if (email == null) return false;
        String value = email.trim();
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return value.length() >= 5 && value.length() <= 50
                && value.matches(regex)
                && !accountRepository.existsByEmail(value);
    }

    @Override
    public boolean isAccountIdExists(int id) throws SQLException {
        return id > 0 && accountRepository.existsById(id);
    }

    @Override
    public boolean addAccount(Account account) throws SQLException {
        if (account == null || account.getUsername() == null
                || account.getEmail() == null || account.getFullname() == null
                || account.getDepartment() == null || account.getPosition() == null) {
            return false;
        }
        String username = account.getUsername().trim();
        String email = account.getEmail().trim();
        String fullname = account.getFullname().trim();
        if (fullname.length() < 5 || fullname.length() > 50
                || !isValidUsername(username) || !isValidEmail(email)) {
            return false;
        }
        account.setUsername(username);
        account.setEmail(email);
        account.setFullname(fullname);
        return accountRepository.insert(account);
    }

    @Override
    public boolean updateUsernameById(int id, String username) throws SQLException {
        if (id <= 0 || username == null) return false;
        String value = username.trim();
        if (value.length() < 5 || value.length() > 50
                || !accountRepository.existsById(id)
                || accountRepository.existsUsernameForOtherId(id, value)) {
            return false;
        }
        return accountRepository.updateUsernameById(id, value);
    }

    @Override
    public boolean deleteAccountById(int id) throws SQLException {
        if (id <= 0 || !accountRepository.existsById(id)) return false;
        return accountRepository.deleteById(id);
    }
}
