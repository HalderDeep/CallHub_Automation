package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {
    private By createCampaignButton = By.className("newCreateCampaignButton");
    public DashboardPage() {
        super();
    }
    public void goToCampaignCreation() {
        driver.findElement(createCampaignButton).click();
    }
}
