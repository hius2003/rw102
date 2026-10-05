package backend;

import entity.Department;
import java.util.List;

public interface IQLDepartment {
    List<Department> getAll();
    List<Department> findByName(String name);
    boolean add(String name);
    boolean deleteById(int id);
    boolean updateName(int id, String name);
}
