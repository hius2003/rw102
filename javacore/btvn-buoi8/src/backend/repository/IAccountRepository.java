package backend.repository;

import entity.Account;
import entity.Position;

import java.sql.SQLException;
import java.util.List;

public interface IAccountRepository {
    List<Account> findAll() throws SQLException;
    Account findByUsername(String username) throws SQLException;
    List<Position> findAllPositions() throws SQLException;
    boolean existsById(int id) throws SQLException;
    boolean existsByUsername(String username) throws SQLException;
    boolean existsByEmail(String email) throws SQLException;
    boolean existsUsernameForOtherId(int id, String username) throws SQLException;
    boolean insert(Account account) throws SQLException;
    boolean updateUsernameById(int id, String username) throws SQLException;
    boolean deleteById(int id) throws SQLException;
}
