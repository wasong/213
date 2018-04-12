package ca.coursePlanner.model.Departments;

import java.util.ArrayList;
import java.util.List;

public class DepartmentList_ {
    private List<Department_> departments = new ArrayList<>();

    public void addDepartment(Department_ department) {
        this.departments.add(department);
    }

    public List<Department_> getDepartments() {
        return departments;
    }

    public Department_ findDepartmentById(long deptId) {
        for(Department_ d : departments) {
            if (d.getDeptId() == deptId) return d;
        }
        return null;
    }

    public Department_ doesDeptExist(String name) {
        for(Department_ d : departments) {
            if (d.getName().equals(name)) return d;
        }
        return null;
    }
}
