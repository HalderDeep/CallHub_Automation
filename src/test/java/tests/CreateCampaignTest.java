package tests;

import base.BasePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.DashboardPage;
import pages.CampaignPage;
import utils.ConfigReader;

public class CreateCampaignTest extends BasePage {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CampaignPage campaignPage;
    ConfigReader config;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        campaignPage = new CampaignPage();
        config = new ConfigReader("config.properties");

        openUrl(config.getProperty("url"));
    }

    @Test
    public void createVoiceBroadcastCampaign() throws InterruptedException {
        // Login
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        // Navigate to Campaign Creation
        dashboardPage.goToCampaignCreation();

        // Set up Campaign Details
        campaignPage.selectCampaignType("Voice Broadcasting");
        campaignPage.goNext();
        campaignPage.assertHeader("New Voice Broadcast Campaign");
        campaignPage.selectAudioOption("sample-audio-voice-broadcast");
        // Set up transfer Details
        campaignPage.configureTransferOption("1","+919706192745");
        campaignPage.clickNext();

        campaignPage.selectContactList("Deep Test");
        campaignPage.clickNext();

        campaignPage.clickNext();
        campaignPage.finishCampaignSetup(); // Complete setup

        campaignPage.verifySuccessAlert();

        Thread.sleep(4000);
    }



    @After
    public void tearDown() {
        closeBrowser();
    }
}
