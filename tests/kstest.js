const {Builder, By} = require("selenium-webdriver");
const {Select} = require('selenium-webdriver');

async function testcase() {

    let driver = await new Builder().forBrowser("chrome").build();


    await driver.get("http://127.0.0.1:5500/UNM-SQA-2023-24-main/dashboard.html")
    await driver.sleep(3 * 1000);

    await driver.executeScript('window.scrollTo(0, document.body.scrollHeight);');
    await driver.sleep(3 * 1000);

    await driver.executeScript('window.scrollTo(0, 0);');
    await driver.sleep(1 * 1000);

    await driver.findElement(By.id("keyword-search")).sendKeys("Lilac");
    await driver.findElement(By.id("keyword-search-button")).click();
    await driver.sleep(6 * 1000);

    await driver.executeScript('window.scrollTo(0, document.body.scrollHeight);');
    await driver.sleep(3 * 1000);

    await driver.executeScript('window.scrollTo(0, 0);');
    await driver.sleep(1 * 1000);

    await driver.findElement(By.className("select2-selection__rendered")).click();

    const dropDownElement = await driver.findElement(By.className("select2 form-select select2-hidden-accessible"));
    const select = new Select(dropDownElement);

    select.selectByValue("Music");
    await driver.sleep(6 * 1000);

    select.selectByValue("Sports");
    await driver.sleep(6 * 1000);

    await driver.executeScript('window.scrollTo(0, document.body.scrollHeight);');
    await driver.sleep(3 * 1000);

    await driver.executeScript('window.scrollTo(0, 0);');
    await driver.sleep(3 * 1000);

    select.deselectAll();
    await driver.sleep(6 * 1000);

    await driver.findElement(By.id("note-content-0")).sendKeys("test");
    await driver.findElement(By.id("saveNoteButton0")).click();
}

testcase()