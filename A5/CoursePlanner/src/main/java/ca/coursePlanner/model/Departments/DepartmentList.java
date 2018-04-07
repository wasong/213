package ca.coursePlanner.model.Departments;

import java.util.ArrayList;
import java.util.List;

public class DepartmentList {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Department doesDeptExist(String name) {
        for(Department d : departments) {
            if (d.getName().equals(name)) return d;
        }
        return null;
    }
}
