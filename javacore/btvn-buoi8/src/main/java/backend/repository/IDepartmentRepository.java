package backend.repository;

import entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll() throws SQLException;

    List<Department> findByName(String name) throws SQLException;

    boolean insert(String name) throws SQLException;

    boolean deleteById(int id) throws SQLException;

    boolean updateNameById(int id, String name) throws SQLException;
}