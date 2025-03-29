package org.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.example.Pages.AdminPage;
import org.example.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

public class StepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminPage adminPage;
    private int initialRecordCount;
    private int finalRecordCount;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    @When("I log in with {string} and {string}")
    public void i_log_in_with_and(String username, String password) {
        loginPage.login(username, password);
    }

    @When("I navigate to the Admin tab")
    public void i_navigate_to_the_admin_tab() {
        adminPage = new AdminPage(driver);
        adminPage.navigateToAdminTab();
    }



    @When("I add a new user with username {string}, password {string}, employee name {string}, role {string}, and status {string}")
    public void i_add_a_new_user_with_details(String username, String password, String employeeName, String role, String status) {
        // Get the initial record count before adding a new user
        initialRecordCount = adminPage.getRecordCount();

        // Add the user with the provided details
        try {
            adminPage.addUser(username, password, employeeName, role, status);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the number of records should increase by {int}")
    public void the_number_of_records_should_increase_by(Integer increment) {
        finalRecordCount = adminPage.getRecordCount(); // Implement this method in AdminPage
        Assert.assertEquals(initialRecordCount + increment, finalRecordCount);
    }

    @When("I search for {string}")
    public void i_search_for(String username) {
        adminPage.searchUser(username);
    }

    @When("I delete the user")
    public void i_delete_the_user() {
        adminPage.deleteUser();
    }

    @Then("the number of records should decrease by {int}")
    public void the_number_of_records_should_decrease_by(Integer decrement) {
        finalRecordCount = adminPage.getRecordCount(); // Implement this method in AdminPage
        Assert.assertEquals(initialRecordCount - decrement, finalRecordCount);
    }
}