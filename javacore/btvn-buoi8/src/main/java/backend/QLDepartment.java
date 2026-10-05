package backend;

import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment implements IQLDepartment {
    @Override
    public List<Department> getAll() {
        return query("SELECT id, name FROM departments ORDER BY id", null);
    }

    @Override
    public List<Department> findByName(String name) {
        return query("SELECT id, name FROM departments WHERE name LIKE ? ORDER BY id", "%" + name + "%");
    }

    private List<Department> query(String sql, String name) {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (name != null) statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) departments.add(new Department(result.getInt("id"), result.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Không thể lấy department: " + e.getMessage());
        }
        return departments;
    }

    @Override
    public boolean add(String name) {
        String sql = "INSERT INTO departments (name) VALUES (?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Không thể thêm department: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        return update("DELETE FROM departments WHERE id = ?", id, null);
    }

    @Override
    public boolean updateName(int id, String name) {
        return update("UPDATE departments SET name = ? WHERE id = ?", id, name);
    }

    private boolean update(String sql, int id, String name) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (name == null) {
                statement.setInt(1, id);
            } else {
                statement.setString(1, name);
                statement.setInt(2, id);
            }
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Không thể thực hiện thao tác department: " + e.getMessage());
            return false;
        }
    }
}
