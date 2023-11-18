import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQATestClass {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kssoh\\OneDrive\\Desktop\\Tools\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5500/UNM-SQA-2023-24-main/dashboard.html");
    }

    @Test
    public void yourTestMethod() {

        try {

            WebElement noteInput = driver.findElement(By.id("keyword-search"));
            noteInput.sendKeys("Software");

            sleep(6000);

            WebElement buttonSave = driver.findElement(By.id("keyword-search-button"));
            buttonSave.click();

            sleep(6000);

            WebElement videoTitle = driver.findElement(By.id("videoTitle0"));

            // Verify that the video title contains the expected text
            String expectedTitle = "Software";
            String actualTitle = videoTitle.getText();

            assertTrue(actualTitle.contains(expectedTitle));

            WebElement categoryElement = driver.findElement(By.className("select2"));

            Select select1 = new Select(categoryElement);

            // Select option with value "Music"
            select1.selectByValue("Music");

            sleep(6000);
            WebElement videoTitle2 = driver.findElement(By.id("videoTitle0"));

            // Verify that the video title contains the expected text
            String expectedTitle2 = "Music";
            String actualTitle2 = videoTitle2.getText();

            assertTrue(actualTitle2.contains(expectedTitle2));

            sleep(6000);

            WebElement inputText = driver.findElement(By.id("note-content-0"));
            inputText.sendKeys("Test");

            WebElement inputButton = driver.findElement(By.id("saveNoteButton0"));
            inputButton.click();

            sleep(2000);

            WebElement noteSaved = driver.findElement(By.id("note-dropdown-0"));
            Select select2 = new Select(noteSaved);
            select2.selectByIndex(1);

            inputText = driver.findElement(By.id("note-content-0"));
            String expectedResult = "Test";
            String actualText = inputText.getAttribute("value");

            assertEquals(expectedResult, actualText);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void finish() {
        if (driver != null) {
            driver.quit();
        }
    }
}
