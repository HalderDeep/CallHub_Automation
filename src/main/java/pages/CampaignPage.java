package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UIHelper;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class CampaignPage extends BasePage {
    private UIHelper uiHelper;
    private By campaignNameField = By.id("campaignName");

    private By campaignTypeDropdown = By.id("campaignType");
    private By transferOptionButton = By.id("add-multi-transfer");
    private By transferDigitField = By.name("transfer-digit");
    private By transferPhoneNumberField = By.id("id_transfer-phone_number");
    private By createCampaignButton = By.id("createCampaign");

    private By headerText = By.tagName("h2");
    private By audioDropdown = By.id("select2-live-audio-select-container");
    private By audiotransferDropdown2 = By.id("select2-audio-select-container");

    private By hangUpRadioButton = By.id("id_transfer-action_type_3");
    private By addTransferButton = By.id("transfer-add-btn");
    private By nextButton = By.id("wizard-next");
    private By successAlert = By.cssSelector("div.alert.alert-success.custom-notification-sucesss");
    private By finishButton = By.xpath("//button[@data-last='Finish']");

    private By selectClassDropdown = By.xpath("//ul[@class='select2-selection__rendered']");
    public CampaignPage() {
        super();
        this.uiHelper = new UIHelper(driver);
    }

    public void selectCampaignType(String campaignType) {
        uiHelper.selectByVisibleText(campaignTypeDropdown, campaignType);
    }


    public void selectContactList(String contactList) {

        driver.findElement(selectClassDropdown).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-results__option")));
        WebElement option = driver.findElement(By.xpath("//li[contains(@class, 'select2-results__option') and contains(text(), '" + contactList + "')]"));
        option.click();
    }

    public void configureTransferOption(String digit, String phoneNumber) throws InterruptedException {
        driver.findElement(transferOptionButton).click();
        uiHelper.selectByValue(transferDigitField,digit);
        Thread.sleep(500);
        selectAudioOptionTransfer("sample-audio-voice-broadcast");
        Thread.sleep(500);
        driver.findElement(transferPhoneNumberField).sendKeys(phoneNumber);
        uiHelper.click(addTransferButton);

    }

    public void assertHeader(String expectedHeader) {
        String actualHeader = driver.findElement(headerText).getText().trim();
        assertEquals("Header text did not match.", expectedHeader, actualHeader);
    }

    public void selectAudioOption(String audioOption) {
        driver.findElement(audioDropdown).click();
        WebElement audioElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(), '" + audioOption + "')]")));
        audioElement.click();
    }

    public void selectAudioOptionTransfer(String audioOption) {
        driver.findElement(audiotransferDropdown2).click();
        WebElement audioElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(), '" + audioOption + "')]")));
        audioElement.click();
    }


    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    public void goNext() {
        driver.findElement(createCampaignButton).click();
    }

    public void finishCampaignSetup() {
       uiHelper.click(finishButton);
    }

    public void verifySuccessAlert() {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
        String alertText = alert.getText().trim();

        String expectedPattern = "Alert : \"Voice Broadcast, \\w+ \\d{2}, \\d{2}:\\d{2}\" added\\.";
        Pattern pattern = Pattern.compile(expectedPattern);
        Matcher matcher = pattern.matcher(alertText);

        // Assert that the alert message matches the pattern
        if (matcher.matches()) {
            System.out.println("Alert notification matched successfully.");
        } else {
            throw new AssertionError("Alert notification did not match. Actual: " + alertText);
        }
    }
}

