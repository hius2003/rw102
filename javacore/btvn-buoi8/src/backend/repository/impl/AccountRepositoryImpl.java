package backend.repository.impl;

import backend.repository.IAccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {

    @Override
    public List<Account> findAll() throws SQLException {
        String sql = "SELECT a.id AS account_id, a.username, a.email, a.fullname, "
                + "d.id AS department_id, d.name AS department_name, "
                + "p.id AS position_id, p.name AS position_name "
                + "FROM account a "
                + "LEFT JOIN department d ON a.department_id = d.id "
                + "LEFT JOIN `position` p ON a.position_id = p.id "
                + "ORDER BY a.id";
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                accounts.add(mapAccount(result));
            }
        }
        return accounts;
    }

    @Override
    public Account findByUsername(String username) throws SQLException {
        String sql = "SELECT a.id AS account_id, a.username, a.email, a.fullname, "
                + "d.id AS department_id, d.name AS department_name, "
                + "p.id AS position_id, p.name AS position_name "
                + "FROM account a "
                + "LEFT JOIN department d ON a.department_id = d.id "
                + "LEFT JOIN `position` p ON a.position_id = p.id "
                + "WHERE a.username = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? mapAccount(result) : null;
            }
        }
    }

    @Override
    public List<Position> findAllPositions() throws SQLException {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT id, name FROM `position` ORDER BY id";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                positions.add(new Position(result.getInt("id"), result.getString("name")));
            }
        }
        return positions;
    }

    @Override
    public boolean existsById(int id) throws SQLException {
        return exists("SELECT 1 FROM account WHERE id = ?", id);
    }

    @Override
    public boolean existsByUsername(String username) throws SQLException {
        return existsString("SELECT 1 FROM account WHERE username = ?", username);
    }

    @Override
    public boolean existsByEmail(String email) throws SQLException {
        return existsString("SELECT 1 FROM account WHERE email = ?", email);
    }

    @Override
    public boolean existsUsernameForOtherId(int id, String username) throws SQLException {
        String sql = "SELECT 1 FROM account WHERE username = ? AND id <> ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, id);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    @Override
    public boolean insert(Account account) throws SQLException {
        String sql = "INSERT INTO account "
                + "(username, email, fullname, department_id, position_id) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getFullname());
            statement.setInt(4, account.getDepartment().getId());
            statement.setInt(5, account.getPosition().getId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateUsernameById(int id, String username) throws SQLException {
        String sql = "UPDATE account SET username = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM account WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private boolean exists(String sql, int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    private boolean existsString(String sql, String value) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    private Account mapAccount(ResultSet result) throws SQLException {
        Department department = new Department(
                result.getInt("department_id"), result.getString("department_name"));
        Position position = new Position(
                result.getInt("position_id"), result.getString("position_name"));
        return new Account(
                result.getInt("account_id"), result.getString("username"),
                result.getString("email"), result.getString("fullname"),
                department, position);
    }
}
