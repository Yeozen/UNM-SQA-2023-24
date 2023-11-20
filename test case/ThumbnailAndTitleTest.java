import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThumbnailAndTitleTest {

    private WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\leebo\\Documents\\Degree\\Degree Year 3\\Software Quality Assurance\\SeleniumTest\\TestCase\\src\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///C:/Users/leebo/Documents/Degree/Degree%20Year%203/Software%20Quality%20Assurance/UNM-SQA-2023-24-main/UNM-SQA-2023-24-main/dashboard.html");

        // Wait for the videos to load
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testThumbnailAndTitle() {
    // Get a list of all video elements
    List<WebElement> videoElements = driver.findElements(By.className("video-container"));

    // Verify the presence and proper display of thumbnails for each video
    for (WebElement videoElement : videoElements) {
        WebElement thumbnailElement = videoElement.findElement(By.className("plyr__poster"));
        Assert.assertTrue("Thumbnail element not found", thumbnailElement.isDisplayed());

        String thumbnailSrc = thumbnailElement.getCssValue("background-image");
        // Extract the image URL from the background-image property
        String imageUrl = thumbnailSrc.substring(5, thumbnailSrc.length() - 2);
        Assert.assertNotNull("Thumbnail source attribute is empty", imageUrl);
        Assert.assertTrue("Thumbnail source is not an image", imageUrl.endsWith(".jpg") || imageUrl.endsWith(".png") || imageUrl.endsWith(".gif"));

        // Verify the thumbnail link
        String thumbnailLink = thumbnailElement.getAttribute("href");
        Assert.assertNotNull("Thumbnail link attribute is empty", thumbnailLink);
        Assert.assertTrue("Thumbnail link is not a valid URL", thumbnailLink.startsWith("http") || thumbnailLink.startsWith("www"));

        // Check thumbnail width and height
        int thumbnailWidth = thumbnailElement.getSize().getWidth();
        int thumbnailHeight = thumbnailElement.getSize().getHeight();
        Assert.assertTrue("Thumbnail width is too small", thumbnailWidth >= 100);
        Assert.assertTrue("Thumbnail height is too small", thumbnailHeight >= 60);
    }

    // Check the presence and proper formatting of titles for each video
    for (WebElement videoElement : videoElements) {
        WebElement titleElement = videoElement.findElement(By.className("video-title"));
        Assert.assertTrue("Title element not found", titleElement.isDisplayed());

        String titleText = titleElement.getText();
        Assert.assertNotNull("Title text is empty", titleText);
        Assert.assertFalse("Title text is too short", titleText.length() < 5);
        Assert.assertFalse("Title text is too long", titleText.length() > 100);

        // Verify the title link
        String titleLink = titleElement.getAttribute("href");
        Assert.assertNotNull("Title link attribute is empty", titleLink);
        Assert.assertTrue("Title link is not a valid URL", titleLink.startsWith("http") || titleLink.startsWith("www"));
    }
}

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
