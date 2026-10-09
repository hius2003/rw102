package backend.controller;

import backend.service.IQLDepartment;
import backend.service.impl.QLDepartment;
import entity.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentController {
    private final IQLDepartment departmentService;

    public DepartmentController() {
        departmentService = new QLDepartment();
    }

    public List<Department> getAllDepartments() throws SQLException {
        return departmentService.getAllDepartments();
    }

    public Department findDepartmentByName(String name) throws SQLException {
        return departmentService.findDepartmentByName(name);
    }

    public boolean addDepartment(Department department) throws SQLException {
        return departmentService.addDepartment(department);
    }

    public boolean deleteDepartmentById(int id) throws SQLException {
        return departmentService.deleteDepartmentById(id);
    }

    public boolean updateDepartmentNameById(int id, String name) throws SQLException {
        return departmentService.updateDepartmentNameById(id, name);
    }
}
