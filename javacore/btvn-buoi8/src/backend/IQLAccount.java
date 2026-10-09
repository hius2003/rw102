package backend.service;

import entity.Account;
import entity.Position;

import java.sql.SQLException;
import java.util.List;

public interface IQLAccount {
    List<Account> getAllAccounts() throws SQLException;
    Account findAccountByUsername(String username) throws SQLException;
    List<Position> getAllPositions() throws SQLException;
    boolean isValidUsername(String username) throws SQLException;
    boolean isValidEmail(String email) throws SQLException;
    boolean isAccountIdExists(int id) throws SQLException;
    boolean addAccount(Account account) throws SQLException;
    boolean updateUsernameById(int id, String username) throws SQLException;
    boolean deleteAccountById(int id) throws SQLException;
}
