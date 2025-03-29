package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(linkText = "Admin")
    private WebElement adminTab;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/button[1]")
    private WebElement addButton;

    @FindBy(css = ".oxd-select-text")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@class=\"oxd-select-option\"] [contains(., 'Admin')]")
    private WebElement adminRoleOption;

    By employeeNameField=By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]");

    By employeeName=By.cssSelector("div.oxd-layout.orangehrm-upgrade-layout div.oxd-layout-navigation header.oxd-topbar div.oxd-topbar-header div.oxd-topbar-header-userarea ul:nth-child(1) li.oxd-userdropdown span.oxd-userdropdown-tab > p.oxd-userdropdown-name");

    By selectEmployee=By.xpath("(//div[@role=\"option\"])[1]");

    @FindBy(xpath = "//div[contains(text(),'-- Select --')]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//div[@class=\"oxd-select-option\"] [contains(., 'Enabled')]")
    private WebElement enabledOption;


    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/input[1]")
    private WebElement usernameField;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]")
    private WebElement passwordField;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/input[1]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/button[2]")
    private WebElement saveButton;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]")
    private WebElement searchField;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]")
    private WebElement searchButton;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/button[1]/i[1]")
    private WebElement deleteButton;

    @FindBy(xpath = "//body/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/button[2]")
    private WebElement confirmDeleteButton;

    // Locator for the records found label
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")
    private WebElement recordsFoundLabel;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToAdminTab() {
        wait.until(ExpectedConditions.elementToBeClickable(adminTab)).click();
    }
    public String getemployeename() {
        return driver.findElement(employeeName).getText();
     }

    public void addUser(String username, String password, String employeeName, String role, String status) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(adminRoleOption)).click();


        // Search and select employee
        wait.until(ExpectedConditions.elementToBeClickable(employeeNameField)).sendKeys(getemployeename());
        Thread.sleep(2000); // Wait for the dropdown to populate
        driver.findElement(employeeNameField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(employeeNameField).sendKeys(Keys.ENTER);

        // Select status
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(enabledOption)).click();
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void searchUser(String username) {
        wait.until(ExpectedConditions.visibilityOf(searchField)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void deleteUser() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }


    public int getRecordCount() {
        // Wait for the records found label to be visible
        String text = wait.until(ExpectedConditions.visibilityOf(recordsFoundLabel)).getText();
        // Extract the number from the text, e.g., "(3) Records Found"
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}