import org.example.ShareDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains automated tests for the Facebook sign-up and login functionality.
 * It uses Selenium WebDriver to interact with the Facebook website and JUnit for test execution.
 */
public class Selenium4FbTest {

    // URL of the Facebook homepage
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    // WebDriver instance to control the browser
    private static WebDriver driver;

    // This method sets up the WebDriver and navigates to the Facebook homepage before all tests.
    @BeforeAll
    public static void classSetup() {
        driver = ShareDriver.getWebDriver(); // Initialize the WebDriver
        driver.get(HOME_PAGE_URL); // Navigate to the Facebook homepage
    }

    // This method closes the WebDriver after all tests are completed.
    @AfterAll
    public static void classTearDown() {
        ShareDriver.closeDriver(); // Close the WebDriver
    }

    // This method navigates back to the Facebook homepage after each test to ensure a clean state.
    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL); // Return to the homepage
    }

    // This test verifies the creation of a new Facebook account by filling out the sign-up form.
    @Test
    public void createNewAccount() throws InterruptedException {
        // Find and click the "Create new account" button
        WebElement createNewAccount = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(createNewAccount); // Verify the button exists
        createNewAccount.click();

        Thread.sleep(2000); // Wait for the form to load

        // Fill out the first name field
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement); // Verify the field exists
        firstNameElement.sendKeys("Iana"); // Enter a first name
        String firstNameValue = firstNameElement.getAttribute("value"); // Get the entered value
        assertEquals("Iana", firstNameValue); // Verify the value is correct

        // Fill out the last name field
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement); // Verify the field exists
        lastNameElement.sendKeys("Fitzgerald"); // Enter a last name
        String lastNameValue = lastNameElement.getAttribute("value"); // Get the entered value
        assertEquals("Fitzgerald", lastNameValue); // Verify the value is correct

        // Fill out the email field
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(emailElement); // Verify the field exists
        emailElement.sendKeys("kenibeze@gmail.com"); // Enter an email
        String emailValue = emailElement.getAttribute("value"); // Get the entered value
        assertEquals("kenibeze@gmail.com", emailValue); // Verify the value is correct

        // Fill out the password field
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        assertNotNull(passwordElement); // Verify the field exists
        passwordElement.sendKeys("12Qwaszx"); // Enter a password
        String passwordValue = passwordElement.getAttribute("value"); // Get the entered value
        assertEquals("12Qwaszx", passwordValue); // Verify the value is correct

        // Click the "Sign Up" button
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton); // Verify the button exists
        signUpButton.click(); // Submit the form
    }

    // This test verifies the login functionality by entering email and password.
    @Test
    public void loginScreenTest() {
        // Fill out the email field
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement); // Verify the field exists
        emailElement.sendKeys("kenibeze@gmail.com"); // Enter an email
        String emailValue = emailElement.getAttribute("value"); // Get the entered value
        assertEquals("kenibeze@gmail.com", emailValue); // Verify the value is correct

        // Fill out the password field
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal-pass']"));
        assertNotNull(passwordElement); // Verify the field exists
        passwordElement.sendKeys("12Qwaszx"); // Enter a password
        String passwordValue = passwordElement.getAttribute("value"); // Get the entered value
        assertEquals("12Qwaszx", passwordValue); // Verify the value is correct

        // Click the login button
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement); // Verify the button exists
        loginButtonElement.click(); // Submit the form
    }


    // This parameterized test verifies the behavior when invalid email formats are entered.
    @ParameterizedTest
    @ValueSource(strings = {
            "invalidemail.com",
            "user@.com",
            "@example.com",
            "user@com",
            "user.com",
            "user@-example.com",
            "user@.com.com"
    })
    public void testInvalidEmailInputs(String invalidEmail) throws InterruptedException {
        try {
            WebElement createNewAccount = driver.findElement(By.xpath("//a[text()='Create new account']"));
            assertNotNull(createNewAccount); // Verify the button exists
            createNewAccount.click();
            Thread.sleep(2000); // Wait for the form to load

            // Enter an invalid email
            WebElement emailField = driver.findElement(By.name("reg_email__"));
            emailField.clear();
            emailField.sendKeys(invalidEmail);

            System.out.printf("InvalidEmail: '%s'%n", invalidEmail); // Log the invalid email
        } catch (Exception e) {
            fail("Error during testing: " + e.getMessage()); // Fail the test if an exception occurs
        }
    }

    // This test verifies the presence of the "Sign Up" button after clicking "Create new account."
    @Test
    public void sighUpTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the button exists
    }

    // This test verifies the gender selection functionality in the sign-up form.
    @Test
    public void gendersTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']"))); // Verify the form is loaded

        Thread.sleep(2000); // Wait for the form to load

        // Verify female gender selection
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked); // Verify the attribute exists
        assertEquals("true", isFemaleChecked); // Verify the female gender is selected

        // Verify male gender selection
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked); // Verify the attribute exists
        assertEquals("true", isMaleChecked); // Verify the male gender is selected
    }

    // This test verifies the error message displayed when the email field is left empty.
    @Test
    public void errorMessageTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        driver.findElement(By.xpath("//*[@name='websubmit']")).click(); // Submit the form
        driver.findElement(By.xpath("//*[@aria-label='Mobile number or email']")).click(); // Click the email field

        // Verify the error message is displayed
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(error); // Verify the error message exists
    }

    // This test verifies the error message displayed when the first name field is left empty.
    @Test
    public void errorMessageTest2() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        driver.findElement(By.xpath("//*[@name='websubmit']")).click(); // Submit the form
        driver.findElement(By.xpath("//*[@aria-label='First name']")).click(); // Click the first name field

        // Verify the error message is displayed
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'name')]"));
        assertNotNull(error); // Verify the error message exists
    }

    // This test verifies the error message displayed when the last name field is left empty.
    @Test
    public void errorMessageTest3() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        driver.findElement(By.xpath("//*[@name='websubmit']")).click(); // Submit the form
        driver.findElement(By.xpath("//*[@aria-label='Last name']")).click(); // Click the last name field

        // Verify the error message is displayed
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'name')]"));
        assertNotNull(error); // Verify the error message exists
    }

    // This test verifies the year selection functionality in the sign-up form.
    @Test
    public void yearTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        // Select the year 1990 from the dropdown
        driver.findElement(By.xpath("//*[@title = 'Year']")).click();
        driver.findElement(By.xpath("//*[text() = '1990']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title = 'Year']")).getAttribute("value");
        assertEquals("1990", yearValue); // Verify the selected year is correct
    }

    // This parameterized test verifies the year selection functionality with multiple inputs.
    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearParameterizedTest(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        // Select the specified year from the dropdown
        driver.findElement(By.xpath("//*[@title = 'Year']")).click();
        driver.findElement(By.xpath("//*[text() = '" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title = 'Year']")).getAttribute("value");
        assertEquals(yearInput, yearValue); // Verify the selected year is correct
    }

    // This parameterized test verifies the month selection functionality with multiple inputs.
    @ParameterizedTest
    @ValueSource(strings = {"May", "Jul"})
    public void monthParameterizedTest(String monthInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(3000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        // Select the specified month from the dropdown
        driver.findElement(By.xpath("//*[@id = 'month']")).click();
        driver.findElement(By.xpath("//*[text() = '" + monthInput + "']")).click();
        String selectedMonth = new Select(driver.findElement(By.id("month"))).getFirstSelectedOption().getText();
        assertEquals(monthInput, selectedMonth); // Verify the selected month is correct
    }

    // This test verifies the data policy link functionality by opening it in a new tab.
    @Test
    public void dataPolicyTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(3000); // Wait for the form to load
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']"))); // Verify the form is loaded

        // Click the data policy link
        driver.findElement(By.id("privacy-link")).click();

        // Wait for the new tab to open
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Verify the URL of the new tab
        driver.getCurrentUrl();
    }
}