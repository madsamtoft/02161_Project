package hellocucumber;

import app.*;
import io.cucumber.java.an.E;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {
    private SystemApp systemApp = new SystemApp();
    private String errorMessage;
    private Project someProject;
    private Employee someEmployee;
    private Employee someProjectLeader;
    private List<Employee> someEmployees = new ArrayList<>();
    private List<Employee> availableEmployees;

    private static final String DEFAULT_ACTIVITY_NAME = "chjk";

    @When("creating a new project named {string}")
    public void creatingANewProjectNamed(String string) {
        try {
            systemApp.createProject(string);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("error message {string} is given")
    public void errorMessageIsGiven(String string) {
        assertEquals(string, errorMessage);
    }

    @Then("{int} unique project\\(s) with name {string} exist\\(s)")
    public void uniqueProjectSWithNameExistS(int amount, String name) {
        // Check exists
        List<Integer> ids = systemApp.getProjectIds(name);
        assertEquals(amount, ids.size());

        // Check duplicates
        Set<Integer> idSet = new HashSet<>(ids);
        assertEquals(ids.size(), idSet.size());
    }

    @Given("a project")
    public void aProject() {
        try {
            someProject = new Project("p", 0);
        } catch (SystemAppException e) {

        }
    }

    @Given("{string} exists as employee")
    public void existsAsEmployee(String string) {
        try {
            someEmployee = new Employee(string);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

    }

    @When("setting employee as project leader")
    public void settingEmployeeAsProjectLeader() {
        try {
            someProject.assignProjectLeader(null, someEmployee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the project leader is employee")
    public void theProjectLeaderIsEmployee() {
        Employee projectLeader = someProject.getProjectLeader();
        assertEquals(someEmployee, projectLeader);
    }

    @When("the start date is set to day {int}, month {int}, and year {int}")
    public void theStartDateIsSetToDayMonthAndYear(int day, int month, int year) {
        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.DATE, day);
        startDate.set(Calendar.MONTH, month-1);
        startDate.set(Calendar.YEAR, year);

        try {
            someProject.setStartDate(someEmployee, startDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the end date is set to day {int}, month {int}, and year {int}")
    public void theEndDateIsSetToDayMonthAndYear(int day, int month, int year) {
        Calendar endDate = Calendar.getInstance();
        endDate.clear();
        endDate.set(Calendar.DATE, day);
        endDate.set(Calendar.MONTH, month-1);
        endDate.set(Calendar.YEAR, year);

        try {
            someProject.setEndDate(someEmployee, endDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("the start date is day {int}, month {int}, and year {int}")
    public void theStartDateIsDayMonthAndYear(int day, int month, int year) {
        Calendar setDate = someProject.getStartDate();

        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.DATE, day);
        startDate.set(Calendar.MONTH, month-1);
        startDate.set(Calendar.YEAR, year);

        assertEquals(startDate, setDate);
    }
    @Then("the end date is day {int}, month {int}, and year {int}")
    public void theEndDateIsDayMonthAndYear(int day, int month, int year) {
        Calendar setDate = someProject.getEndDate();

        Calendar endDate = Calendar.getInstance();
        endDate.clear();
        endDate.set(Calendar.DATE, day);
        endDate.set(Calendar.MONTH, month-1);
        endDate.set(Calendar.YEAR, year);

        assertEquals(endDate, setDate);
    }

    @Given("employee is the leader of the project")
    public void theUserIsTheLeaderOfTheProject() {
        try {
            someProjectLeader = someEmployee;
            someProject.assignProjectLeader(null, someEmployee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting project name to {string}")
    public void settingProjectNameTo(String string) {
        try {
            someProject.setName(someEmployee, string);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("project name is {string}")
    public void projectNameIs(String string) {
        assertEquals(string, someProject.getName());
    }

    @Given("employee is not the leader of the project")
    public void isNotTheLeaderOfTheProject() {
        try {
            someProject.assignProjectLeader(null, new Employee("chjk"));
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
//        assertNotEquals(someEmployee, someProject.getProjectLeader());
    }

    @When("setting project customer to {string}")
    public void settingProjectCustomerTo(String customer) {
        try {
            someProject.setCustomer(someEmployee, customer);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("project customer is {string}")
    public void projectCustomerIs(String customer) {
        assertEquals(customer, someProject.getCustomer());
    }

    //// ACTIVITY STEPS
    @Given("it has an activity")
    public void itHasAnActivity() {
        try {
            someProject.createActivity(someProjectLeader, DEFAULT_ACTIVITY_NAME);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("a project leader")
    public void aProjectLeader() {
        someProjectLeader = new Employee("leader");
        someProject.assignProjectLeader(someProjectLeader);
    }

    @When("the start week is set to {int} in year {int}")
    public void theStartWeekIsSetTo(int startWeek, int startYear) {
        try {
            Calendar startDate = Calendar.getInstance();
            startDate.clear();
            startDate.set(Calendar.WEEK_OF_YEAR, startWeek);
            startDate.set(Calendar.YEAR, startYear);
            someProject.getActivity(DEFAULT_ACTIVITY_NAME).setStartWeek(startDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the end week is set to {int} in year {int}")
    public void theEndWeekIsSetTo(int endWeek, int endYear) {
        try {
            Calendar endDate = Calendar.getInstance();
            endDate.clear();
            endDate.set(Calendar.WEEK_OF_YEAR, endWeek);
            endDate.set(Calendar.YEAR, endYear);
            someProject.getActivity(DEFAULT_ACTIVITY_NAME).setEndWeek(endDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the start week is {int} in year {int}")
    public void theStartWeekIs(int startWeek, int startYear) {
        try {
            Calendar startDate = Calendar.getInstance();
            Calendar actualStartDate = someProject.getActivity(DEFAULT_ACTIVITY_NAME).getStartWeek();
            startDate.clear();
            startDate.set(Calendar.WEEK_OF_YEAR, startWeek);
            startDate.set(Calendar.YEAR, startYear);
            assertEquals(startDate, actualStartDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the end week is {int} in year {int}")
    public void theEndWeekIs(int endWeek, int endYear) {
        try {
            Calendar endDate = Calendar.getInstance();
            Calendar actualEndDate = someProject.getActivity(DEFAULT_ACTIVITY_NAME).getEndWeek();
            endDate.clear();
            endDate.set(Calendar.WEEK_OF_YEAR, endWeek);
            endDate.set(Calendar.YEAR, endYear);
            assertEquals(endDate, actualEndDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting the estimated hours of an activity to {int}")
    public void settingTheEstimatedHoursOfAnActivityTo(int hours) {
        try {
            someProject.getActivity(DEFAULT_ACTIVITY_NAME).setEstimatedHours(hours);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the estimated hours of the activity should be {int}")
    public void theEstimatedHoursOfTheActivityShouldBe(int hours) {
        try {
            int actualHours = someProject.getActivity(DEFAULT_ACTIVITY_NAME).getEstimatedHours();
            assertEquals(hours, actualHours);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("creating a new activity {string} in the project")
    public void creatingANewActivityInTheProject(String activityName) {
        try {
            someProject.createActivity(someEmployee, activityName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the activity with name {string} is a part of the project")
    public void theActivityWithNameIsAPartOfTheProject(String activityName) {
        try {
            someProject.getActivity(activityName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("activity {string} already exists in the project")
    public void activityAlreadyExistsInTheProject(String activityName) {
        try {
            someProject.createActivity(someEmployee, activityName);
        } catch (Exception e) {
            // blank?
        }
    }

//    @When("{string} tries to register daily time to {double} for activity")
//    public void triesToRegisterDailyTimeToForActivity(String employeeName, double hours) {
//        try {
//            someProject.registerTimeDaily(someActivity, someEmployee, hours);
//        } catch (Exception e){
//            errorMessage = e.getMessage();
//        }
//    }
//
//    @Then("{int} hours have been registered to the activity")
//    public void hoursHaveBeenRegisteredToTheActivity(double hours) {
//        assertEquals(hours, someProject.checkRegisteredDaily(someActivity, someEmployee));
//    }

    @When("employee tries to register daily time to {int}:{int} for activity")
    public void triesToRegisterDailyTimeToForActivity(int fullHours, int minutes) {
        double hours = fullHours + (minutes/60.);
        try {
            someProject.registerTimeDaily(DEFAULT_ACTIVITY_NAME, someEmployee, hours);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @Then("{int}:{int} hours have been registered to the activity by employee")
    public void hoursHaveBeenRegisteredToTheActivity(int fullHours, int minutes) {
        double hours = fullHours + (minutes/60.);
        try {
            assertEquals(hours, someProject.checkRegisteredDaily(DEFAULT_ACTIVITY_NAME, someEmployee));
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @And("{string}, {string}, {string} exist as employees")
    public void existAsEmployees(String empl1, String empl2, String empl3) {
        someEmployees.add(new Employee(empl1));
        someEmployees.add(new Employee(empl2));
        someEmployees.add(new Employee(empl3));
    }

    // ASSIGN EMPLOYEE
    @Given("{string} is assigned to {int} activities")
    public void isAssignedToActivities(String employeeName, Integer activityCount) {

    }

    @Given("{string} is not assigned to the activity in the project")
    public void isNotAssignedToTheActivityInTheProject(String employeeName) {

    }

    @When("{string} is assigned to the activity in the project")
    public void isAssignedToTheActivityInTheProject(String employeeName) {
        try {
            someProject.assignEmployeeToActivity(DEFAULT_ACTIVITY_NAME, someEmployee);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("{string} is successfully assigned to the activity in the project")
    public void isSuccessfullyAssignedToTheActivityInTheProject(String employeeName) {
        try {
            boolean assigned = someProject.getActivity(DEFAULT_ACTIVITY_NAME).employeeAssigned(someEmployee);
            assertTrue(assigned);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

    }
    // FIRM ACTIVITY

    @When("creating a new firm activity {string}")
    public void creatingANewFirmActivity(String activityName) {
        try {
            systemApp.createFirmActivity(activityName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the firm activity with name {string} exists")
    public void theFirmActivityWithNameExists(String activityName) {
        try {
            systemApp.getFirmActivity(activityName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("{string} registers {double} hours to day {int}, month {int} and year {int}")
    public void registersHoursToDayMonthAndYear(String employee, int hours, int minutes, int day, int month, int year) {
        //someFirmActivity.registerTime(employee,hours,day,month,year);
    }

    @And("there exists a firm activity")
    public void thereExistsAFirmActivity() {
        try {
            someFirmActivity = new Activity("løbehjuls konkurrence");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

//    // FIND AVAILABLE EMPLOYEES
//    @Given("these employees registered in the app")
//    public void theseEmployeesRegisteredInTheApp(List<String> employeeNames) {
//        for(String employeeName : employeeNames) {
//            someEmployees.add(new Employee(employeeName));
//        }
//
//    }
//
//    @When("searching for available employees")
//    public void searchingForAvailableEmployees() {
//        systemApp.getAvailableEmployees();
//    }
//
//    @Then("employees found are")
//    public void employeesFoundAre(List<String> employeeNames) {
//        // Write code here that turns the phrase above into concrete actions
//        // For automatic transformation, change DataTable to one of
//        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//        // Double, Byte, Short, Long, BigInteger or BigDecimal.
//        //
//        // For other transformations you can register a DataTableType.
//        throw new io.cucumber.java.PendingException();
//    }
}