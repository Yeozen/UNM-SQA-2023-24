import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDeselectTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kssoh\\OneDrive\\Desktop\\Tools\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("C:\\Users\\kssoh\\OneDrive\\Desktop\\Work\\VS\\UNM-SQA-2023-24-main\\UNM-SQA-2023-24-main/dashboard.html");
    }

    @Test
    public void DeleteCategoryTest() throws Exception {

        WebElement categoryElement = driver.findElement(By.className("select2"));

        sleep(6000);
        Select select1 = new Select(categoryElement);

        select1.selectByValue("Classics");
        sleep(6000);

        WebElement deleteCategory = driver.findElement(By.className("select2"));

        Select deselect = new Select(deleteCategory);
        deselect.deselectByVisibleText("Classics");
        sleep(6000);

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
