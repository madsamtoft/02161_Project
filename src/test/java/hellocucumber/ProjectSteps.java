package hellocucumber;

import app.*;
import java.util.*;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {
    private final SystemApp systemApp = new SystemApp();
    private String errorMessage;
    private final String someProjectName = "proj1";
    private final String someEmployeeName = "abcd";
    @SuppressWarnings("FieldCanBeLocal")
    private final String otherEmployeeName = "huba";
    private final String someActivityName = "act1";
    private List<String> availableEmployeeNames = new ArrayList<>();

    private static final String DEFAULT_ACTIVITY_NAME = "act";

    private double calcHours(int hours, int minutes) {
        return Math.ceil((hours + (minutes/60.))*2) / 2.;
    }

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
            systemApp.createProject(someProjectName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("an employee")
    public void anEmployee() {
        try {
            systemApp.registerEmployee(someEmployeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("{string} exists as employee")
    public void existsAsEmployee(String employeeName) {
        try {
            systemApp.registerEmployee(employeeName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("these employees registered in the app")
    public void theseEmployeesRegisteredInTheApp(List<List<String>> dataTable) {
        List<String> employeeNames = dataTable.getFirst();
        for (String employeeName : employeeNames) {
            try {
                systemApp.registerEmployee(employeeName);
            } catch (SystemAppException e) {
                errorMessage = e.getMessage();
            }
        }
    }

    @When("setting employee as project leader")
    public void settingEmployeeAsProjectLeader() {
        try {
            systemApp.assignProjectLeader("", someProjectName, someEmployeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the project leader is employee")
    public void theProjectLeaderIsEmployee() {
        String projectLeader = "";
        try {
            projectLeader = systemApp.getProjectLeader(someProjectName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(someEmployeeName, projectLeader);
    }

    @When("the start date is set to day {int}, month {int}, and year {int}")
    public void theStartDateIsSetToDayMonthAndYear(int day, int month, int year) {
        Calendar startDate = Calendar.getInstance();
        startDate.clear();
        startDate.set(Calendar.DATE, day);
        startDate.set(Calendar.MONTH, month-1);
        startDate.set(Calendar.YEAR, year);
        try {
            systemApp.changeProjectStartDate(someEmployeeName, someProjectName, startDate);
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
            systemApp.changeProjectEndDate(someEmployeeName, someProjectName, endDate);
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
            setDate = systemApp.getProjectStartDate(someProjectName);
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
            setDate = systemApp.getProjectEndDate(someProjectName);
//            setDate = systemApp.getProject(someProject).getEndDate();
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(endDate, setDate);
    }

    @Given("employee is the leader of the project")
    public void employeeIsTheLeaderOfTheProject() {
        try {
            systemApp.assignProjectLeader(someEmployeeName, someProjectName, someEmployeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting project name to {string}")
    public void settingProjectNameTo(String string) {
        try {
            systemApp.changeProjectName(someEmployeeName, someProjectName, string);
//            systemApp.getProject(someProject).setName(someEmployee, string);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("project name is {string}")
    public void projectNameIs(String string) {
        try {
            assertEquals(string, systemApp.getProjectName(someProjectName));
//            assertEquals(string, systemApp.getProject(someProject).getName());
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("employee is not the leader of the project")
    public void isNotTheLeaderOfTheProject() {
        try {
            systemApp.assignProjectLeader(someEmployeeName, someProjectName, otherEmployeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting project customer to {string}")
    public void settingProjectCustomerTo(String customer) {
        try {
            systemApp.changeProjectCustomer(someEmployeeName, someProjectName, customer);
//            systemApp.getProject(someProject).setCustomer(someEmployee, customer);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("project customer is {string}")
    public void projectCustomerIs(String customer) {
        try {
            assertEquals(customer, systemApp.getProjectCustomer(someProjectName));
//            assertEquals(customer, systemApp.getProject(someProject).getCustomer());
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    //// ACTIVITY STEPS
    @Given("it has {int} activities")
    public void itHasAnActivity(int activityAmount) {
        try {
            for(int i = 0; i < activityAmount; i++) {
                systemApp.createActivity(someEmployeeName, someProjectName, DEFAULT_ACTIVITY_NAME+(i+1));
            }
//            systemApp.getProject(someProject).createActivity(someEmployee, someActivity);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the name of the activity is changed to {string}")
    public void theNameOfTheActivityIsChangedTo(String newActivityName) {
        try {
            systemApp.setActivityName(someEmployeeName, someProjectName, someActivityName, newActivityName);
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
            oldExists = systemApp.activityExists(someProjectName, someActivityName);
            newExists = systemApp.activityExists(someProjectName, activityName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertFalse(oldExists);
        assertTrue(newExists);
    }

    @When("the start week is set to {int} in year {int}")
    public void theStartWeekIsSetTo(int startWeek, int startYear) {
        try {
            systemApp.setActivityStartWeek(someEmployeeName, someProjectName, someActivityName, startWeek, startYear);
//            systemApp.getProject(someProject).getActivity(someActivity).setStartWeek(someEmployee, startDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the end week is set to {int} in year {int}")
    public void theEndWeekIsSetTo(int endWeek, int endYear) {
        try {
            systemApp.setActivityEndWeek(someEmployeeName, someProjectName, someActivityName, endWeek, endYear);
//            systemApp.getProject(someProject).getActivity(someActivity).setEndWeek(someEmployee, endDate);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("setting the estimated hours of an activity to {int}")
    public void settingTheEstimatedHoursOfAnActivityTo(int hours) {
        try {
            systemApp.setActivityEstimatedHours(someEmployeeName, someProjectName, someActivityName, hours);
//            systemApp.getProject(someProject).getActivity(someActivity).setEstimatedHours(someEmployee, hours);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the start week is {int} in year {int}")
    public void theStartWeekIs(int startWeek, int startYear) {
        try {
            Calendar startDate = CalendarConverter.getCalendar(startWeek, startYear);
            Calendar actualStartDate = systemApp.getActivityStartWeek(someProjectName, someActivityName);
            assertEquals(startDate.getTimeInMillis(), actualStartDate.getTimeInMillis());
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
        }

    }

    @Then("the end week is {int} in year {int}")
    public void theEndWeekIs(int endWeek, int endYear) {
        try {
            Calendar endDate = CalendarConverter.getCalendar(endWeek, endYear);
            Calendar actualEndDate = systemApp.getActivityEndWeek(someProjectName, someActivityName);
            assertEquals(endDate.getTimeInMillis(), actualEndDate.getTimeInMillis());
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
        }
    }

    @Then("the estimated hours of the activity should be {int}")
    public void theEstimatedHoursOfTheActivityShouldBe(int hours) {
        try {
            int actualHours = systemApp.getActivityEstimatedHours(someProjectName, someActivityName);
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
            systemApp.createActivity(someEmployeeName, someProjectName, activityName);
//            systemApp.getProject(someProject).createActivity(someEmployee, activityName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the activity with name {string} is a part of the project")
    public void theActivityWithNameIsAPartOfTheProject(String activityName) {
        try {
            assertTrue(systemApp.hasActivity(someProjectName, activityName));
//            assertTrue(systemApp.getProject(someProject).hasActivity(activityName));
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
        }
    }

    @When("employee tries to register daily time to {int}:{int} for activity")
    public void triesToRegisterDailyTimeToForActivity(int fullHours, int minutes) {
        try {
            systemApp.registerTimeDaily(someProjectName, someActivityName, someEmployeeName, fullHours, minutes);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @Then("{int}:{int} hours have been registered to the activity by employee")
    public void hoursHaveBeenRegisteredToTheActivity(int fullHours, int minutes) {
        double hours = calcHours(fullHours, minutes);
        try {
            assertEquals(hours, systemApp.checkRegisteredTimeDaily(someProjectName, someActivityName, someEmployeeName));
        } catch (Exception e){
            errorMessage = e.getMessage();
            fail();
        }
    }

    // ASSIGN EMPLOYEE
    @Given("the employee is assigned to {int} activities")
    public void isAssignedToActivities(Integer activityCount) {
        try{
            for (int i = 0; i < activityCount; i++) {
                systemApp.assignEmployeeToActivity(someEmployeeName, someProjectName, DEFAULT_ACTIVITY_NAME+(i+1), someEmployeeName);
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("the employee is not assigned to the activity in the project")
    public void isNotAssignedToTheActivityInTheProject() {

    }

    @When("the employee is assigned to the activity in the project")
    public void isAssignedToTheActivityInTheProject() {
        try {
            systemApp.assignEmployeeToActivity(someEmployeeName, someProjectName, someActivityName, someEmployeeName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the employee with name {string} is assigned to the activity in the project")
    public void theEmployeeWithNameIsAssignedToTheActivityInTheProject(String employeeName) {
        try {
            systemApp.assignEmployeeToActivity(employeeName, someProjectName, someActivityName, employeeName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the employee is assigned to activity {string}")
    public void isAssignedToTheActivity(String activityName) {
        try {
            systemApp.assignEmployeeToActivity(someEmployeeName, someProjectName, activityName, someEmployeeName);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the employee is successfully assigned to the activity in the project")
    public void isSuccessfullyAssignedToTheActivityInTheProject() {
        try {
            assertTrue(systemApp.hasEmployeeAssignedToActivity(someProjectName, someActivityName, someEmployeeName));
        } catch (Exception e) {
            errorMessage = e.getMessage();
            fail();
        }

    }

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
        assertTrue(systemApp.firmActivityExists(activityName));
    }

    @When("{string} registers {int} hours and {int} minutes to day {int}, month {int} and year {int} to firm Activity {string}")
    public void registersHoursAndMinutesToDayMonthAndYearToFirmActivity(String employeeName, int hours, int minutes, int day, int month, int year, String firmActivityName) {
        try {
            systemApp.registerTimeFirmActivity(employeeName, firmActivityName, hours, minutes, day, month, year);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
    }

    @Then("{string} has registered {int} hours and {int} minutes to day {int}, month {int}, and year {int} to firm Activity {string}")
    public void hasRegisteredHoursAndMinutesToDayMonthAndYearToFirmActivity(String employeeName, int fullHours, int minutes, int day, int month, int year, String firmActivityName) {
        double roundHours = calcHours(fullHours, minutes);
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredFirmActivity(employeeName, firmActivityName, day, month, year);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(roundHours,checkHours);
    }

    @When("{string} registers {int} hours and {int} minutes to day {int}, month {int} and year {int} to Activity {string}")
    public void registersHoursAndMinutesToDayMonthAndYearToActivity(String employeeName, int fullHours, int minutes, int day, int month, int year, String activity) {
        try {
            systemApp.registerTimeActivity(employeeName, someProjectName,activity,fullHours,minutes,day,month,year);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @And("the employee has already registered {int} hours to a activity")
    public void theEmployeeHasAlreadyRegisteredHoursToAActivity(int arg0) {
        try {
            systemApp.registerTimeDaily(someProjectName, someActivityName, someEmployeeName, arg0,0);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("an employee checks daily registered hours")
    public void anEmployeeChecksDailyRegisteredHours() {
        try {
            systemApp.checkRegisteredTime(someProjectName, someEmployeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }
    @Then("{int} hours is returned")
    public void hoursIsReturned(int hours) {
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredTime(someProjectName, someEmployeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(hours,checkHours);
    }



    @Then("{string} has registered {int} hours and {int} minutes to day {int}, month {int}, and year {int} to Activity {string}")
    public void hasRegisteredHoursAndMinutesToDayMonthAndYearToActivity(String employeeName, int fullHours, int minutes , int day, int month, int year, String activity){
        double roundHours = calcHours(fullHours, minutes);
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredActivity(employeeName, someProjectName, activity, day, month, year);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals(roundHours,checkHours);

    }

    @Then("{string} has registered {int} hours and {int} minutes in total to Activity {string}")
    public void hasRegisteredHoursAndMinutesInTotalToActivity(String employeeName, int fullHours, int minutes, String activity) {
        double hours = calcHours(fullHours, minutes);
        double checkHours = -1;
        try {
            checkHours = systemApp.checkRegisteredTotalActivity(someProjectName, activity, employeeName);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(hours, checkHours);
    }

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



    @And("it has two activities")
    public void itHasTwoActivities() {
        try {
            systemApp.createActivity(someEmployeeName, someProjectName, "activity1");
            systemApp.createActivity(someEmployeeName, someProjectName, "activity2");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the employee registers {int} hours to {string}")
    public void theEmployeeRegistersHoursTo(int hours, String activityName) {
        try {
            systemApp.registerTimeDaily(someProjectName,activityName, someEmployeeName, hours,0);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the id of {string} is <currentYear> {int}")
    public void theIdCounterOfIs(String project, int id) {
        String actualName = "";
        int actualId = -1;
        try {
            actualName = systemApp.getProjectName(project);
            actualId = systemApp.getProjectId(project);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        int checkId = (CalendarConverter.getCurrentYear() % 100) * 1000 + id;
        assertEquals(project, actualName);
        assertEquals(checkId, actualId);
    }

    @Given("activity {int} to {int} starts week {int} in year {int}")
    public void activityToStartsWeekInYear(Integer actStart, Integer actEnd, Integer startWeek, Integer startYear) {
        for(int i = actStart; i <= actEnd; i++) {
            try{
                systemApp.setActivityStartWeek(someEmployeeName, someProjectName, DEFAULT_ACTIVITY_NAME+i, startWeek, startYear);
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }
    }

    @Given("activity {int} to {int} ends week {int} in year {int}")
    public void activityToEndsWeekInYear(Integer actStart, Integer actEnd, Integer endWeek, Integer endYear) {
        for(int i = actStart; i <= actEnd; i++) {
            try{
                systemApp.setActivityEndWeek(someEmployeeName, someProjectName, DEFAULT_ACTIVITY_NAME+i, endWeek, endYear);
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }
    }

    @When("searching for available employees")
    public void searchingForAvailableEmployees() {
        availableEmployeeNames = systemApp.getAvailableEmployees();
    }

    @Then("employees found are")
    public void employeesFoundAre(List<List<String>> dataTable) {
        List<String> employeeNames = dataTable.getFirst();
        ArrayList<String> matching = new ArrayList<>(employeeNames);
        matching.retainAll(availableEmployeeNames);
        assertEquals(employeeNames.size(), matching.size());
        assertEquals(matching.size(), availableEmployeeNames.size());
    }

    @Then("no employees are found")
    public void noEmployeesAreFound() {
        assertEquals(0, availableEmployeeNames.size());
    }
}