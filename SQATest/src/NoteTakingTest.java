import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTakingTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kssoh\\OneDrive\\Desktop\\Tools\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("C:\\Users\\kssoh\\OneDrive\\Desktop\\Work\\VS\\UNM-SQA-2023-24-main\\UNM-SQA-2023-24-main/dashboard.html");
    }

    @Test
    public void noteTaking () throws Exception {
        sleep(1000);

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
        
    }

    @AfterEach
    public void finish() {
        if (driver != null) {
            driver.quit();
        }
    }

}
