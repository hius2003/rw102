package backend.repository.impl;

import backend.repository.IDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {

    @Override
    public List<Department> findAll() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT id, name FROM departments ORDER BY id";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                departments.add(department);
            }
        }

        return departments;
    }

    @Override
    public List<Department> findByName(String name) throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT id, name FROM department WHERE name LIKE ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Department department = new Department();
                    department.setId(resultSet.getInt("id"));
                    department.setName(resultSet.getString("name"));
                    departments.add(department);
                }
            }
        }

        return departments;
    }

    @Override
    public boolean insert(String name) throws SQLException {
        String sql = "INSERT INTO department (name) VALUES (?)";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM department WHERE id = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateNameById(int id, String name) throws SQLException {
        String sql = "UPDATE department SET name = ? WHERE id = ?";

        try (
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }
}