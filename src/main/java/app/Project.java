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
        this.setName(name);
        this.id = id;
//        this.startDate = null;
//        this.endDate = null;
//        this.customer = null;
    }

    public void assignProjectLeader(Employee employee) {
        this.projectLeader = employee;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) throws SystemAppException {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            throw new SystemAppException("Project Name cannot be empty");
        }
    }
    public int getId() {
        return this.id;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public Employee getProjectLeader() {
        return projectLeader;
    }
    public void setProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }
}
