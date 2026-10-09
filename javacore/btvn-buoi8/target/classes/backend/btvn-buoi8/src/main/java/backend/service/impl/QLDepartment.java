package backend.service.impl;

import backend.repository.IDepartmentRepository;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.service.IQLDepartment;
import entity.Department;

import java.sql.SQLException;
import java.util.List;

public class QLDepartment implements IQLDepartment {

    private final IDepartmentRepository departmentRepository;

    public QLDepartment() {
        departmentRepository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> getAllDepartments() throws SQLException {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> searchDepartmentByName(String name)
            throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            return departmentRepository.findAll();
        }

        return departmentRepository.findByName(name.trim());
    }

    @Override
    public boolean addDepartment(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        return departmentRepository.insert(name.trim());
    }

    @Override
    public boolean deleteDepartmentById(int id) throws SQLException {
        if (id <= 0) {
            return false;
        }

        return departmentRepository.deleteById(id);
    }

    @Override
    public boolean updateDepartmentNameById(int id, String name)
            throws SQLException {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            return false;
        }

        return departmentRepository.updateNameById(id, name.trim());
    }
}