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
        driver = ShareDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    // This method closes the WebDriver after all tests are completed.
    @AfterAll
    public static void classTearDown() {
        ShareDriver.closeDriver(); // Close the WebDriver
    }

    // This method navigates back to the Facebook homepage after each test to ensure a clean state.
    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);// Return to the homepage
    }

    // This test verifies the creation of a new Facebook account by filling out the sign-up form.
    @Test
    public void createNewAccount() throws InterruptedException {
        WebElement createNewAccount = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(createNewAccount);
        createNewAccount.click();

        Thread.sleep(2000);

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys("Iana");
        String firstNameValue = firstNameElement.getAttribute("value");
        assertEquals("Iana", firstNameValue);

        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);
        lastNameElement.sendKeys("Fitzgerald");
        String lastNameValue = lastNameElement.getAttribute("value");
        assertEquals("Fitzgerald", lastNameValue);

        WebElement emailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(emailElement);
        emailElement.sendKeys("kenibeze@gmail.com");
        String emailValue = emailElement.getAttribute("value");
        assertEquals("kenibeze@gmail.com", emailValue);

        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        assertNotNull(passwordElement);
        passwordElement.sendKeys("12Qwaszx");
        String passwordValue = passwordElement.getAttribute("value");
        assertEquals("12Qwaszx", passwordValue);

        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButton);
        signUpButton.click();
    }

    @Test
    public void loginScreenTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement);
        emailElement.sendKeys("kenibeze@gmail.com");
        String emailValue = emailElement.getAttribute("value");
        assertEquals("kenibeze@gmail.com", emailValue);

        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal-pass']"));
        assertNotNull(passwordElement);
        passwordElement.sendKeys("12Qwaszx");
        String passwordValue = passwordElement.getAttribute("value");
        assertEquals("12Qwaszx", passwordValue);

        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);
        loginButtonElement.click();
    }

    public void longTextTest() throws InterruptedException {
        WebElement createNewAccount = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(createNewAccount);
        createNewAccount.click();

        Thread.sleep(2000);

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys("IanaIanaIanaIanaIanaIanaIanaIanaIanavIanaIanavIanavvvvIanavvvIanaIanaIanaIanaIanaIanaIanaIanavvIanavIanaIanaIana");
        assertEquals("Iana", firstNameElement);
    }

    @Test
    public void specialCharacterTest() throws InterruptedException {
        WebElement createNewAccount = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(createNewAccount);
        createNewAccount.click();

        Thread.sleep(2000);

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys("!@#$%^&*&*");
        assertEquals("Iana", firstNameElement);
    }

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
            assertNotNull(createNewAccount);
            createNewAccount.click();
            Thread.sleep(2000);

            WebElement emailField = driver.findElement(By.name("reg_email__"));
            emailField.clear();
            emailField.sendKeys(invalidEmail);

            System.out.printf("InvalidEmail: '%s'%n", invalidEmail);
        } catch (Exception e) {
            fail("Error during testing: " + e.getMessage());
        }
    }

    @Test
    public void sighUpTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
    }

    @Test
    public void gendersTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(2000);

        //verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        //verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);
    }

    @Test
    public void errorMessageTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@aria-label='Mobile number or email']")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(error);
    }

    @Test
    public void errorMessageTest2() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@aria-label='First name']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'name')]"));
        assertNotNull(error);

    }

    @Test
    public void errorMessageTest3() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@aria-label='Last name']")).click();
        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'name')]"));
        assertNotNull(error);
    }

    @Test
    public void yearTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        driver.findElement(By.xpath("//*[@title = 'Year']")).click();
        driver.findElement(By.xpath("//*[text() = '1990']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title = 'Year']")).getAttribute("value");
        assertEquals("1990", yearValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearParameterizedTest(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(2000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        driver.findElement(By.xpath("//*[@title = 'Year']")).click();
        driver.findElement(By.xpath("//*[text() = '" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title = 'Year']")).getAttribute("value");
        assertEquals(yearInput, yearValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"May", "Jul"})
    public void monthParameterizedTest(String monthInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(3000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));

        driver.findElement(By.xpath("//*[@id = 'month']")).click();
        driver.findElement(By.xpath("//*[text() = '" + monthInput + "']")).click();
        String selectedMonth = new Select(driver.findElement(By.id("month"))).getFirstSelectedOption().getText();
        assertEquals(monthInput, selectedMonth);
    }

    @Test
    public void dataPolicyTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        Thread.sleep(3000);
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        driver.findElement(By.id("privacy-link")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();
    }
}





