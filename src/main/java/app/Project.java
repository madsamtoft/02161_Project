package app;

import java.util.Calendar;

public class Project {
    private String name;
    private int id; // Read only
    private Calendar startDate;
    private Calendar endDate;
    private String customer;
    private Employee projectLeader;

    public Project(String name, int id) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        }
        this.name = name;
        this.id = id;
//        this.startDate = null;
//        this.endDate = null;
//        this.customer = null;
    }

    private boolean checkProjectLeader(Employee employee) {
        return employee.equals(this.projectLeader);
    }

    public void assignProjectLeader(Employee employee) {
        this.projectLeader = employee;
    }

    public String getName() {
        return this.name;
    }
    public void setName(Employee employee, String name) throws SystemAppException {
        if (!checkProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        } else {
            this.name = name;
        }
    }
    public int getId() {
        return this.id;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public void setStartDate(Employee employee, Calendar startDate) throws SystemAppException {
        if (!checkProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.startDate = startDate;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public void setEndDate(Employee employee, Calendar endDate) throws SystemAppException {
        if (!checkProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.endDate = endDate;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(Employee employee, String customer) throws SystemAppException {
        if (!checkProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.customer = customer;
    }
    public Employee getProjectLeader() {
        return projectLeader;
    }
//    public void setProjectLeader(Employee projectLeader) {
//        this.projectLeader = projectLeader;
//    }
}
