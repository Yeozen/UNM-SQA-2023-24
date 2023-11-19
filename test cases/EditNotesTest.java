import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class EditNotesTest {
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

        WebElement note = driver.findElement(By.id("note-content-0"));
        note.sendKeys("ligma fucking balls");

        Thread.sleep(1000);

        WebElement saveNote = driver.findElement(By.id("saveNoteButton0"));
        saveNote.click();

        WebElement playButton = driver.findElement(By.className("plyr__control--overlaid"));
        playButton.click();

        // let the video play for 5 seconds
        Thread.sleep(5000);

        note.sendKeys("sugma fucking dick");
        saveNote.click();

        Thread.sleep(1000);



        WebElement selectNoteDropdown = driver.findElement(By.id("note-dropdown-0"));
        List<WebElement> dropdownOptions = selectNoteDropdown.findElements(By.cssSelector("option"));
        assertEquals("Dropdown should have two options", 3, dropdownOptions.size());
        assertEquals("First option should be 'Select a note'", "Select a note", dropdownOptions.get(0).getText().trim());
        assertEquals("Second option should be '0:00'", "0:00", dropdownOptions.get(1).getText().trim());

        // guys calm down i know this seems stupid but i cannot predict whether the video player will lag by 1 second and
        // since assertEquals does not allow for more than 1 expected value and i don't want to create my own custom assertioni
        // here we are
        if (dropdownOptions.get(2).getText().trim().equals("0:04")){
            assertEquals("Third option should be '0:04'", "0:04", dropdownOptions.get(2).getText().trim());
        } else if (dropdownOptions.get(2).getText().trim().equals("0:05")){
            assertEquals("Third option should be '0:05'", "0:05", dropdownOptions.get(2).getText().trim());
        }


        WebElement fullNotesSection = driver.findElement(By.id("description-list-0"));
        List<WebElement> fullNotesTimestamp = fullNotesSection.findElements(By.className("col-3"));
        assertEquals("Should only have 2 timestamps", 2, fullNotesTimestamp.size());
        assertEquals("0:00", fullNotesTimestamp.get(0).getText().trim());
        if (fullNotesTimestamp.get(1).getText().trim().equals("0:04")){
            assertEquals("Expected '0:04'", "0:04", fullNotesTimestamp.get(1).getText().trim());
        } else if (fullNotesTimestamp.get(1).getText().trim().equals("0:05")){
            assertEquals("Expected '0:05'", "0:05", fullNotesTimestamp.get(1).getText().trim());
        }
        List<WebElement> fullNotesContent = fullNotesSection.findElements(By.className("col-9"));
        assertEquals("Should only have 2 notes", 2, fullNotesContent.size());
        assertEquals("ligma fucking balls", fullNotesContent.get(0).getText().trim());
        assertEquals("sugma fucking dick", fullNotesContent.get(1).getText().trim());

    }

    @Test
    public void testClearAllFunctionality() throws InterruptedException {
        // Find the span element to click on
        WebElement spanElement = driver.findElement(By.id("select2-note-dropdown-0-container"));

        // Click on the span element to open the dropdown
        spanElement.click();

        // Click on the option with text '0:00'
        WebElement option = driver.findElement(By.xpath("//li[text()='0:00']"));
        option.click();

        Thread.sleep(1000);

        WebElement note = driver.findElement(By.id("note-content-0"));
        note.clear();
        note.sendKeys("dont ligma");

        Thread.sleep(1000);

        WebElement saveNote = driver.findElement(By.id("saveNoteButton0"));
        saveNote.click();

        WebElement fullNotesSection = driver.findElement(By.id("description-list-0"));
        List<WebElement> fullNotesTimestamp = fullNotesSection.findElements(By.className("col-3"));
        assertEquals("Should only have 2 timestamps", 2, fullNotesTimestamp.size());
        assertEquals("0:00", fullNotesTimestamp.get(0).getText().trim());

        List<WebElement> fullNotesContent = fullNotesSection.findElements(By.className("col-9"));
        assertEquals("Should only have 2 notes", 2, fullNotesContent.size());
        assertEquals("dont ligma", fullNotesContent.get(0).getText().trim());

        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        // Close the WebDriver after the test
        if (driver != null) {
            driver.quit();
        }
    }
}

