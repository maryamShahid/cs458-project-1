import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Test {

    private static final String chromeDriverPath = "C:\\Users\\murat\\Desktop\\chromedriver_win32\\chromedriver.exe";


    private final String netflixURL = "http://localhost:8080";
    private final String facebookURL = "http://localhost:3000";

    private final String loginButtonXPath = "//button[text()='Sign In']";
    private final String errorMessage1XPath = "//div[@class='error-text' and text()='Please enter a valid email or phone number.']";
    private final String errorMessage2XPath = "//div[@class='error-text' and text()='Your password must contain between 4 and 60 characters.']";

    private final String idInputXPath = "//input[@placeholder='Email or phone number']";
    private final String passwordInputXPath = "//input[@placeholder='Password']";

    private final String validPhoneNumber = "05527856126";
    private final String validPassword = "1234";

    private final String invalidPhoneNumber = "123456789";
    private final String invalidPassword = "asd";


    private final String successMessageXPath = "//h1[text()='Successful Login']";

    private final String errorMessageXPath = "//div[@class='error']";
    private final String errorMessageContent = "Sorry, we can't find an account with this email address. Please try again or create a new account.";

    private final String facebookButton = "//*[@id='app']/div/main/div/div/div/div/div[1]/div[4]/button[3]";



    private final String placeholderValueForId = "Email or phone number";
    private final String placeholderValueForPassword = "Password";

    static {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @org.junit.Test
    public void applicationResponsesMultipleUsersSimultaneously() {
        IntStream.range(0, 30).parallel().forEach(el -> {
            ChromeDriver driver = createInvisibleChromeDriver();
            driver.get(netflixURL);

            WebElement loginButton = driver.findElement(By.xpath(loginButtonXPath));

            assertThat(loginButton.isDisplayed()).isTrue();
        });
    }

    @org.junit.Test
    public void applicationGivesRelevantFeedbackWhenInputsAreEmpty() {
        ChromeDriver driver = createInvisibleChromeDriver();
        driver.get(netflixURL);

        driver.findElement(By.xpath(loginButtonXPath)).click();

        WebElement errorMessage1 = driver.findElement(By.xpath(errorMessage1XPath));
        WebElement errorMessage2 = driver.findElement(By.xpath(errorMessage2XPath));

        assertThat(errorMessage1.isDisplayed()).isTrue();
        assertThat(errorMessage1.getText()).isEqualTo("Please enter a valid email or phone number.");

        assertThat(errorMessage2.isDisplayed()).isTrue();
        assertThat(errorMessage2.getText()).isEqualTo("Your password must contain between 4 and 60 characters.");
    }

    @org.junit.Test
    public void applicationSuccessfullyLogsIn() {
        ChromeDriver driver = createInvisibleChromeDriver();

        driver.get(netflixURL);

        driver.findElement(By.xpath(idInputXPath)).sendKeys(validPhoneNumber);
        driver.findElement(By.xpath(passwordInputXPath)).sendKeys(validPassword);

        driver.findElement(By.xpath(loginButtonXPath)).click();

        assertThat(driver.findElement(By.xpath(successMessageXPath)).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath(successMessageXPath)).getText()).isEqualTo("Successful Login");
    }

    @org.junit.Test
    public void applicationShowsErrorWhenInvalidInputsAreProvided() {
        ChromeDriver driver = createInvisibleChromeDriver();

        driver.get(netflixURL);

        driver.findElement(By.xpath(idInputXPath)).sendKeys(invalidPhoneNumber);
        driver.findElement(By.xpath(passwordInputXPath)).sendKeys(invalidPassword);

        driver.findElement(By.xpath(loginButtonXPath)).click();

        assertThat(driver.findElement(By.xpath(errorMessageXPath)).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath(errorMessageXPath)).getText()).isEqualTo(errorMessageContent);
    }

    @org.junit.Test
    public void facebookSuccessfulLogin() throws InterruptedException {
        ChromeDriver driver =  createInvisibleChromeDriver();
        driver.get(facebookURL);
        Thread.sleep(500);

        driver.findElement(By.xpath(facebookButton)).click();
    }

    private ChromeDriver createInvisibleChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }
}
