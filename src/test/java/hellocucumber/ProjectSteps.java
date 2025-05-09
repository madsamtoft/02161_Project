package hellocucumber;

import app.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {
    private SystemApp systemApp = new SystemApp();
    private String errorMessage;
//    private  someProjectOld;
    private final String someProject = "proj1";
//    private Employee someEmployee;
    private final String someEmployee = "abcd";
    private final String otherEmployee = "huba";
    private final String someActivity = "act1";
//    private final String someProjectLeader = "b";
//    private Employee someProjectLeader;
//    private List<Employee> someEmployees = new ArrayList<>();
//    private List<Employee> availableEmployees;

    private static final String DEFAULT_ACTIVITY_NAME = "chjk";

    @When("creating a new project named {string}")
    public void creatingANewProjectNamed(String string) {
        try {
            systemApp.createProject(string);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("there is no error message")
    public void thereIsNoErrorMessage() {
        errorMessage = null;
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
            systemApp.createProject(someProject);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("{string} exists as employee")
    public void existsAsEmployee(String string) {
        try {
            systemApp.registerEmployee(someEmployee);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

    }

    @When("setting employee as project leader")
    public void settingEmployeeAsProjectLeader() {
        try {
            systemApp.assignProjectLeader(someEmployee, someProject, someEmployee);
//            Employee employee = systemApp.getEmployee(someEmployee);
//            systemApp.getProject(someProject).assignProjectLeader(someEmployee, employee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the project leader is employee")
    public void theProjectLeaderIsEmployee() {
        try {
            String projectLeader = systemApp.getProjectLeader(someProject);
//            String projectLeader = systemApp.getProject(someProject).getProjectLeader().name();
//            assertEquals(someEmployee, projectLeader);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the start date is set to day {int}, month {int}, and year {int}")
    public void theStartDateIsSetToDayMonthAndYear(int day, int month, int year) {
        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.DATE, day);
        startDate.set(Calendar.MONTH, month-1);
        startDate.set(Calendar.YEAR, year);
        try {
            systemApp.changeProjectStartDate(someEmployee, someProject, startDate);
//            systemApp.getProject(someProject).setStartDate(someEmployee, startDate);
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
            systemApp.changeProjectEndDate(someEmployee, someProject, endDate);
//            systemApp.getProject(someProject).setEndDate(someEmployee, endDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("the start date is day {int}, month {int}, and year {int}")
    public void theStartDateIsDayMonthAndYear(int day, int month, int year) {
        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.DATE, day);
        startDate.set(Calendar.MONTH, month-1);
        startDate.set(Calendar.YEAR, year);

        Calendar setDate = null;
        try {
            setDate = systemApp.getProjectStartDate(someProject);
//            setDate = systemApp.getProject(someProject).getStartDate();
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(startDate, setDate);
    }
    @Then("the end date is day {int}, month {int}, and year {int}")
    public void theEndDateIsDayMonthAndYear(int day, int month, int year) {
        Calendar endDate = Calendar.getInstance();
        endDate.clear();
        endDate.set(Calendar.DATE, day);
        endDate.set(Calendar.MONTH, month-1);
        endDate.set(Calendar.YEAR, year);

        Calendar setDate = null;
        try {
            setDate = systemApp.getProjectEndDate(someProject);
//            setDate = systemApp.getProject(someProject).getEndDate();
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(endDate, setDate);
    }

    @Given("employee is the leader of the project")
    public void theUserIsTheLeaderOfTheProject() {
        try {
            systemApp.assignProjectLeader(someEmployee, someProject, someEmployee);
//            Employee employee = systemApp.getEmployee(someEmployee);
//            systemApp.getProject(someProject).assignProjectLeader(someEmployee, employee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting project name to {string}")
    public void settingProjectNameTo(String string) {
        try {
            systemApp.changeProjectName(someEmployee, someProject, string);
//            systemApp.getProject(someProject).setName(someEmployee, string);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("project name is {string}")
    public void projectNameIs(String string) {
        try {
            assertEquals(string, systemApp.getProjectName(someProject));
//            assertEquals(string, systemApp.getProject(someProject).getName());
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("employee is not the leader of the project")
    public void isNotTheLeaderOfTheProject() {
        try {
            systemApp.assignProjectLeader(someEmployee, someProject, "huba");
//            Employee employee = systemApp.getEmployee(otherEmployee);
//            systemApp.getProject(someProject).assignProjectLeader(otherEmployee, employee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting project customer to {string}")
    public void settingProjectCustomerTo(String customer) {
        try {
            systemApp.changeProjectCustomer(someEmployee, someProject, customer);
//            systemApp.getProject(someProject).setCustomer(someEmployee, customer);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("project customer is {string}")
    public void projectCustomerIs(String customer) {
        try {
            assertEquals(customer, systemApp.getProjectCustomer(someProject));
//            assertEquals(customer, systemApp.getProject(someProject).getCustomer());
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    //// ACTIVITY STEPS
    @Given("it has an activity")
    public void itHasAnActivity() {
        try {
            systemApp.createActivity(someEmployee, someProject, someActivity);
//            systemApp.getProject(someProject).createActivity(someEmployee, someActivity);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the name of the activity is changed to {string}")
    public void theNameOfTheActivityIsChangedTo(String newActivityName) {
        try {
            systemApp.setActivityName(someEmployee, someProject, someActivity, newActivityName);
//            systemApp.getProject(someProject).getActivity(someActivity).setName(someEmployee, newActivityName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the name of the activity is {string}")
    public void theNameOfTheActivityIs(String activityName) {
        boolean oldExists = true;
        boolean newExists = false;
        try {
            oldExists = systemApp.activityExists(someProject, someActivity);
            newExists = systemApp.activityExists(someProject, activityName);
//            oldExists = systemApp.getProject(someProject).activityExists(someActivity);
//            newExists = systemApp.getProject(someProject).activityExists(activityName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertFalse(oldExists);
        assertTrue(newExists);
    }

    @When("the start week is set to {int} in year {int}")
    public void theStartWeekIsSetTo(int startWeek, int startYear) {
        try {
            Calendar startDate = Calendar.getInstance();
            startDate.clear();
            startDate.set(Calendar.WEEK_OF_YEAR, startWeek);
            startDate.set(Calendar.YEAR, startYear);
            systemApp.setActivityStartWeek(someEmployee, someProject, someActivity, startDate);
//            systemApp.getProject(someProject).getActivity(someActivity).setStartWeek(someEmployee, startDate);
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
            systemApp.setActivityEndWeek(someEmployee, someProject, someActivity, endDate);
//            systemApp.getProject(someProject).getActivity(someActivity).setEndWeek(someEmployee, endDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting the estimated hours of an activity to {int}")
    public void settingTheEstimatedHoursOfAnActivityTo(int hours) {
        try {
            systemApp.setActivityEstimatedHours(someEmployee, someProject, someActivity, hours);
//            systemApp.getProject(someProject).getActivity(someActivity).setEstimatedHours(someEmployee, hours);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the start week is {int} in year {int}")
    public void theStartWeekIs(int startWeek, int startYear) {
        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.WEEK_OF_YEAR, startWeek);
        startDate.set(Calendar.YEAR, startYear);

        Calendar actualStartDate = null;
        try {
            actualStartDate = systemApp.getActivityStartWeek(someProject, someActivity);
//            actualStartDate = systemApp.getProject(someProject).getActivity(someActivity).getStartWeek();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals(startDate, actualStartDate);
    }

    @Then("the end week is {int} in year {int}")
    public void theEndWeekIs(int endWeek, int endYear) {
        Calendar endDate = Calendar.getInstance();
        endDate.clear();
        endDate.set(Calendar.WEEK_OF_YEAR, endWeek);
        endDate.set(Calendar.YEAR, endYear);

        Calendar actualEndDate = null;
        try {
            actualEndDate = systemApp.getActivityEndWeek(someProject, someActivity);
//            actualEndDate = systemApp.getProject(someProject).getActivity(someActivity).getEndWeek();
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertEquals(endDate, actualEndDate);
    }

    @Then("the estimated hours of the activity should be {int}")
    public void theEstimatedHoursOfTheActivityShouldBe(int hours) {
        try {
            int actualHours = systemApp.getActivityEstimatedHours(someProject, someActivity);
//            int actualHours = systemApp.getProject(someProject).getActivity(someActivity).getEstimatedHours();
            assertEquals(hours, actualHours);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
        }
    }

    @When("creating a new activity {string} in the project")
    public void creatingANewActivityInTheProject(String activityName) {
        try {
            systemApp.createActivity(someEmployee, someProject, activityName);
//            systemApp.getProject(someProject).createActivity(someEmployee, activityName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the activity with name {string} is a part of the project")
    public void theActivityWithNameIsAPartOfTheProject(String activityName) {
        try {
            assertTrue(systemApp.hasActivity(someProject, activityName));
//            assertTrue(systemApp.getProject(someProject).hasActivity(activityName));
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
        }
    }

//    @Given("activity {string} already exists in the project")
//    public void activityAlreadyExistsInTheProject(String activityName) {
//        try {
//            someProject.createActivity(someEmployee.name(), activityName);
//        } catch (Exception e) {
//            // blank?
//        }
//    }

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
        try {
            systemApp.registerTimeDaily(someProject, someActivity, someEmployee, fullHours, minutes);
//            Employee employee = systemApp.getEmployee(someEmployee);
//            systemApp.getProject(someProject).getActivity(someActivity).registerTimeDaily(employee, fullHours, minutes);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @Then("{int}:{int} hours have been registered to the activity by employee")
    public void hoursHaveBeenRegisteredToTheActivity(int fullHours, int minutes) {
        double hours = fullHours + (minutes/60.);
        try {
            assertEquals(hours, systemApp.checkRegisteredTimeDaily(someProject, someActivity, someEmployee));
//            Employee employee = systemApp.getEmployee(someEmployee);
//            assertEquals(hours, systemApp.getProject(someProject).getActivity(someActivity).checkRegisteredDaily(employee));
        } catch (Exception e){
            errorMessage = e.getMessage();
            fail();
        }
    }

    @And("{string}, {string}, {string} exist as employees")
    public void existAsEmployees(String empl1, String empl2, String empl3) {
        try {
            systemApp.registerEmployee(empl1);
            systemApp.registerEmployee(empl2);
            systemApp.registerEmployee(empl3);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    // ASSIGN EMPLOYEE
    @Given("{string} is assigned to {int} activities")
    public void isAssignedToActivities(String employeeName, Integer activityCount) {
        //for (int i = 0; i < activityCount; i++) {
            //systemApp.assignEmployeeToActivity(someProject, someActivity+(i+1), employeeName);
        //}

    }

    @Given("{string} is not assigned to the activity in the project")
    public void isNotAssignedToTheActivityInTheProject(String employeeName) {

    }

    @When("{string} is assigned to the activity in the project")
    public void isAssignedToTheActivityInTheProject(String employeeName) {
        try {
            systemApp.assignEmployeeToActivity(employeeName, someProject, someActivity, employeeName);
//            Employee employee = systemApp.getEmployee(someEmployee);
//            systemApp.getProject(someProject).getActivity(someActivity).assignEmployee(employee);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("{string} is successfully assigned to the activity in the project")
    public void isSuccessfullyAssignedToTheActivityInTheProject(String employeeName) {
        try {
            assertTrue(systemApp.hasEmployeeAssignedToActivity(someProject, someActivity, someEmployee));
//            Employee employee = systemApp.getEmployee(someEmployee);
//            assertTrue(systemApp.getProject(someProject).getActivity(someActivity).employeeAssigned(employee));
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
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
//        try {
//            systemApp.getFirmActivity(activityName);
//        } catch (Exception e) {
//            errorMessage = e.getMessage();
//        }
        assertTrue(systemApp.firmActivityExists(activityName));
    }

    @When("{string} registers {int} hours and {int} minutes to day {int}, month {int} and year {int} to firm Activity {string}")
    public void registersHoursAndMinutesToDayMonthAndYearToFirmActivity(String employeeName, int hours, int minutes, int day, int month, int year, String firmActivityName) {
        try {
            systemApp.registerTimeFirmActivity(employeeName, firmActivityName, hours, minutes, day, month, year);
//            Employee employee = systemApp.getEmployee(employeeName);
//            systemApp.getFirmActivity(firmActivityName).registerTime(employee, hours, minutes, day, month, year);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @Then("{string} has registered {int} hours and {int} minutes to day {int}, month {int}, and year {int} to firm Activity {string}")
    public void hasRegisteredHoursAndMinutesToDayMonthAndYearToFirmActivity(String employeeName, int fullHours, int minutes, int day, int month, int year, String firmActivityName) {
        double hours = fullHours + (minutes/60.);
        double roundHours = Math.ceil(hours*2) / 2.;
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredFirmActivity(employeeName, firmActivityName, day, month, year);
//            Employee employee = systemApp.getEmployee(employeeName);
//            checkHours = systemApp.getFirmActivity(firmActivityName).checkRegistered(employee, day, month, year);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(roundHours,checkHours);
    }

    @When("{string} registers {int} hours and {int} minutes to day {int}, month {int} and year {int} to Activity {string}")
    public void registersHoursAndMinutesToDayMonthAndYearToActivity(String employeeName, int fullHours,int minutes, int day, int month, int year, String activity ) {
        try {
            systemApp.registerTimeActivity(employeeName,someProject,activity,fullHours,minutes,day,month,year);
//            Employee employee = systemApp.getEmployee(employeeName);
//            systemApp.getProject(someProject).getActivity(activity).registerTime(employee, fullHours, minutes, day, month, year);
        }catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @And("the employee has already registered {int} hours to a activity")
    public void theEmployeeHasAlreadyRegisteredHoursToAActivity(int arg0) {
        try {
            systemApp.registerTimeActivity(someEmployee,someProject,DEFAULT_ACTIVITY_NAME,arg0,0,1,1,2023);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("an employee checks daily registered hours")
    public void anEmployeeChecksDailyRegisteredHours() {
        try {
            systemApp.checkRegisteredTimeDaily(someProject, DEFAULT_ACTIVITY_NAME, someEmployee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("{int} hours is returned")
    public void hoursIsReturned(int hours) {
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredTimeDaily(someProject, DEFAULT_ACTIVITY_NAME, someEmployee);
            assertEquals(hours,checkHours);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
            fail();
        }

    }


    @Then("{string} has registered {int} hours and {int} minutes to day {int}, month {int}, and year {int} to Activity {string}")
    public void hasRegisteredHoursAndMinutesToDayMonthAndYearToActivity(String employeeName, int fullHours, int minutes , int day, int month, int year, String activity){
        double hours = fullHours + (minutes/60.);
        double roundHours = Math.ceil(hours*2) / 2.;
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredActivity(employeeName,someProject,activity,day,month,year);
//            Employee employee = systemApp.getEmployee(employeeName);
//            checkHours = systemApp.getProject(someProject).getActivity(activity).checkRegistered(employee,day,month,year);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(roundHours,checkHours);

    }

    @Then("{string} has registered {int} hours and {int} minutes in total to Activity {string}")
    public void hasRegisteredHoursAndMinutesInTotalToActivity(String employeeName, int fullHours, int minutes, String activity) {
        double hours = Math.ceil((fullHours + (minutes/60.))*2) / 2.;
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredTotalActivity(someProject, activity, employeeName);

//            Employee employee = systemApp.getEmployee(employeeName);
//            checkHours = systemApp.getProject(someProject).getActivity(activity).checkRegisteredTotal(employee);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(hours, checkHours);
    }

// EMPLOYEE STEPS
    @When("registering an employee with identifier {string}")
    public void registeringAnEmployeeWithIdentifier(String employeeID) {
        try {
            systemApp.registerEmployee(employeeID);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("{string} is a registered employee")
    public void isARegisteredEmployee(String employeeID) {
        assertTrue(systemApp.employeeExists(employeeID));
    }




//    @And("there exists a firm activity")
//    public void thereExistsAFirmActivity() {
//        try {
//            someProject.createActivity(someEmployee.name(), "løbehjuls konkurrence");
//        } catch (Exception e) {
//            errorMessage = e.getMessage();
//        }
//    }

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