package backend.service;

import entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface IQLDepartment {
    List<Department> getAllDepartments() throws SQLException;
    Department findDepartmentByName(String name) throws SQLException;
    boolean addDepartment(Department department) throws SQLException;
    boolean deleteDepartmentById(int id) throws SQLException;
    boolean updateDepartmentNameById(int id, String name) throws SQLException;
}
