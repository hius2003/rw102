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
    public List<Position> getAllPositions() throws SQLException {
        return accountRepository.findAllPositions();
    }

    @Override
    public Account findAccountByUsername(String username)
            throws SQLException {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        return accountRepository.findByUsername(username.trim());
    }

    @Override
    public boolean isValidUsername(String username) throws SQLException {
        return hasValidLength(username)
                && !accountRepository.existsByUsername(username.trim());
    }

    @Override
    public boolean isValidEmail(String email) throws SQLException {
        if (!hasValidLength(email)) {
            return false;
        }

        String emailPattern =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.trim().matches(emailPattern)
                && !accountRepository.existsByEmail(email.trim());
    }

    @Override
    public boolean addAccount(Account account) throws SQLException {
        if (account == null
                || !hasValidLength(account.getUsername())
                || !hasValidLength(account.getEmail())
                || !hasValidEmailFormat(account.getEmail())
                || !hasValidLength(account.getFullName())
                || account.getPosition() == null
                || account.getDepartment() == null) {
            return false;
        }

        String username = account.getUsername().trim();
        String email = account.getEmail().trim();

        if (accountRepository.existsByUsername(username)
                || accountRepository.existsByEmail(email)) {
            return false;
        }

        account.setUsername(username);
        account.setEmail(email);
        account.setFullName(account.getFullName().trim());

        return accountRepository.insert(account);
    }

    @Override
    public boolean isAccountIdExists(int id) throws SQLException {
        return id > 0 && accountRepository.existsById(id);
    }

    @Override
    public boolean updateUsernameById(int id, String username)
            throws SQLException {
        if (!isAccountIdExists(id) || !hasValidLength(username)) {
            return false;
        }

        String newUsername = username.trim();

        if (accountRepository.existsUsernameForOtherId(newUsername, id)) {
            return false;
        }

        return accountRepository.updateUsernameById(id, newUsername);
    }

    @Override
    public boolean deleteAccountById(int id) throws SQLException {
        if (!isAccountIdExists(id)) {
            return false;
        }

        return accountRepository.deleteById(id);
    }

    private boolean hasValidLength(String value) {
        return value != null
                && value.trim().length() >= 5
                && value.trim().length() <= 50;
    }

    private boolean hasValidEmailFormat(String email) {
        if (email == null) {
            return false;
        }

        String emailPattern =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.trim().matches(emailPattern);
    }
}