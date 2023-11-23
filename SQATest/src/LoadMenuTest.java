import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadMenuTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kssoh\\OneDrive\\Desktop\\Tools\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("C:\\Users\\kssoh\\OneDrive\\Desktop\\Work\\VS\\UNM-SQA-2023-24-main\\UNM-SQA-2023-24-main/dashboard.html");
    }

    @Test
    public void loadMenuTest() throws Exception {

        sleep(1000);
        List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
        assertEquals(12, iframeElements.size());
        sleep(1000);

    }

    @AfterEach
    public void finish() {
        if (driver != null) {
            driver.quit();
        }
    }

}
