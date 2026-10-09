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
    public Department findDepartmentByName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) return null;
        return departmentRepository.findByName(name.trim());
    }

    @Override
    public boolean addDepartment(Department department) throws SQLException {
        if (department == null || department.getName() == null
                || department.getName().trim().isEmpty()) return false;
        department.setName(department.getName().trim());
        return departmentRepository.insert(department);
    }

    @Override
    public boolean deleteDepartmentById(int id) throws SQLException {
        return id > 0 && departmentRepository.deleteById(id);
    }

    @Override
    public boolean updateDepartmentNameById(int id, String name) throws SQLException {
        if (id <= 0 || name == null || name.trim().isEmpty()) return false;
        return departmentRepository.updateNameById(id, name.trim());
    }
}
