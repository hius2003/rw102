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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {

    private static final String SELECT_ACCOUNT =
            "SELECT a.id, a.username, a.email, a.fullname, " +
                    "p.id AS position_id, p.name AS position_name, " +
                    "d.id AS department_id, d.name AS department_name " +
                    "FROM account a " +
                    "LEFT JOIN `position` p ON a.position_id = p.id " +
                    "LEFT JOIN department d ON a.department_id = d.id ";

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = SELECT_ACCOUNT + "ORDER BY a.id";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                accounts.add(readAccount(resultSet));
            }
        }

        return accounts;
    }

    @Override
    public Account findByUsername(String username) throws SQLException {
        String sql = SELECT_ACCOUNT + "WHERE a.username = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return readAccount(resultSet);
                }
            }
        }

        return null;
    }

    @Override
    public List<Position> findAllPositions() throws SQLException {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT id, name FROM `position` ORDER BY id";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt("id"));
                position.setName(resultSet.getString("name"));
                positions.add(position);
            }
        }

        return positions;
    }

    @Override
    public boolean existsById(int id) throws SQLException {
        String sql = "SELECT id FROM account WHERE id = ?";
        return exists(sql, id);
    }

    @Override
    public boolean existsByUsername(String username) throws SQLException {
        String sql = "SELECT id FROM account WHERE username = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT id FROM account WHERE email = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public boolean existsUsernameForOtherId(String username, int id)
            throws SQLException {
        String sql =
                "SELECT id FROM account WHERE username = ? AND id <> ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            statement.setInt(2, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public boolean insert(Account account) throws SQLException {
        String sql =
                "INSERT INTO account " +
                        "(username, email, fullname, position_id, department_id) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getFullName());
            statement.setInt(4, account.getPosition().getId());
            statement.setInt(5, account.getDepartment().getId());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateUsernameById(int id, String username)
            throws SQLException {
        String sql = "UPDATE account SET username = ? WHERE id = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM account WHERE id = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private boolean exists(String sql, int id) throws SQLException {
        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private Account readAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setEmail(resultSet.getString("email"));
        account.setFullName(resultSet.getString("fullname"));

        int positionId = resultSet.getInt("position_id");
        if (!resultSet.wasNull()) {
            Position position = new Position();
            position.setId(positionId);
            position.setName(resultSet.getString("position_name"));
            account.setPosition(position);
        }

        int departmentId = resultSet.getInt("department_id");
        if (!resultSet.wasNull()) {
            Department department = new Department();
            department.setId(departmentId);
            department.setName(resultSet.getString("department_name"));
            account.setDepartment(department);
        }

        return account;
    }
}