package ca.coursePlanner.model.Departments;

public class Department_ {
    private String name;
    private long deptId;

    public Department_(String name, long deptId) {
        this.name = name;
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public long getDeptId() {
        return deptId;
    }
}
