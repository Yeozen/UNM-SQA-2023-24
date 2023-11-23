import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class PlayButtonTest {
    private WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        // Set up the WebDriver (Assuming you have ChromeDriver installed)
        //System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\yeozen\\Desktop\\YR 3 AUTUMN\\Software Quality Assurance\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe\"");
        driver = new ChromeDriver();
        // Open the website with the search functionality
        driver.get("C:\\Users\\yeozen\\Downloads\\UNM-SQA-2023-24-main\\UNM-SQA-2023-24-main\\dashboard.html");
        driver.manage().window().fullscreen();
        // Add a delay (in milliseconds) to wait for the page to load
        Thread.sleep(5000);  // Adjust the time as needed

    }

    @Test
    public void testPlayFunctionality() throws InterruptedException {
        // Now you should be able to interact with elements inside the iframe
        // For example, locate the play button and click it
        WebElement playButton = driver.findElement(By.className("plyr__control--overlaid"));
        playButton.click();

        // Wait for the video to play (you might need to adjust the wait time)
        Thread.sleep(5000); // Wait for 5 seconds (adjust as needed)

        // Find the video player element
        WebElement videoPlayer = driver.findElement(By.cssSelector(".plyr"));

        // Check if the plyr--playing class is present, indicating the video is playing
        assertTrue("The video is not playing", videoPlayer.getAttribute("class").contains("plyr--playing"));
        }


    @After
    public void tearDown() {
        // Close the WebDriver after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
