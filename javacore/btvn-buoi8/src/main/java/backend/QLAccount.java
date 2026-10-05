package backend;

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

public class QLAccount implements IQLAccount {
    private static final String SELECT_ACCOUNTS = "SELECT a.id, a.username, a.full_name, a.email, "
            + "d.id AS department_id, d.name AS department_name, "
            + "p.id AS position_id, p.name AS position_name "
            + "FROM accounts a JOIN departments d ON a.department_id = d.id "
            + "JOIN positions p ON a.position_id = p.id ";

    private Account readAccount(ResultSet result) throws SQLException {
        Department department = new Department(result.getInt("department_id"), result.getString("department_name"));
        Position position = new Position(result.getInt("position_id"), result.getString("position_name"));
        return new Account(result.getInt("id"), result.getString("username"),
                result.getString("full_name"), result.getString("email"), department, position);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNTS + "ORDER BY a.id");
             ResultSet result = statement.executeQuery()) {
            while (result.next()) accounts.add(readAccount(result));
        } catch (SQLException e) {
            System.out.println("Không thể lấy danh sách account: " + e.getMessage());
        }
        return accounts;
    }

    @Override
    public Account findByUsername(String username) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNTS + "WHERE a.username = ?")) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) return readAccount(result);
            }
        } catch (SQLException e) {
            System.out.println("Không thể tìm account: " + e.getMessage());
        }
        return null;
    }
    @Override
    public boolean add(Account account) {
        String sql = "INSERT INTO accounts (username, full_name, email, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getFullName());
            statement.setString(3, account.getEmail());
            statement.setInt(4, account.getDepartment().getId());
            statement.setInt(5, account.getPosition().getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Không thể thêm account: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByUsername(String username) {
        return executeUpdate("DELETE FROM accounts WHERE username = ?", username);
    }

    @Override
    public boolean updateFullName(String username, String fullName) {
        String sql = "UPDATE accounts SET full_name = ? WHERE username = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fullName);
            statement.setString(2, username);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Không thể cập nhật account: " + e.getMessage());
            return false;
        }
    }

    private boolean executeUpdate(String sql, String value) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Không thể thực hiện thao tác: " + e.getMessage());
            return false;
        }
    }
}
