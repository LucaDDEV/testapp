package Steps;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class AddRecipeSteps {

    private IOSDriver driver;
    @Given("I am in the app main")
    public void iAmInTheAppMain() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "iPad Pro (11-inch)");
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("appium:platformVersion", "15.5");
        desiredCapabilities.setCapability("appium:app", "/Users/bcsoft/Downloads/Fruta-fnjxsnyhnxyxmrfjpxwhjbbhnxps/Build/Products/Debug-iphonesimulator/Fruta iOS.app");
        desiredCapabilities.setCapability("bundleId", "eu.testapp.com");
        desiredCapabilities.setCapability("appium:newCommandTimeout", 30);
        desiredCapabilities.setCapability("noReset", false);
        desiredCapabilities.setCapability("appium:usePrebuiltWDA", true);
        desiredCapabilities.setCapability("useNewWDA", false);
        desiredCapabilities.setCapability("udid", "A1A8DB09-A7D3-4CD0-82A2-3E1F8BF7D5A0");
        desiredCapabilities.setCapability("clearSystemFiles", true);
        URL remoteUrl = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new IOSDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Start Recording
        driver.startRecordingScreen();
        driver.findElementByAccessibilityId("BackButton").click();
        driver.findElementByXPath("(//XCUIElementTypeButton[@name=\"BackButton\"])[2]").click();
    }

    // Ciao.
    @And("Tap on recipe {string}")
    public void tapOnRecipe(String arg0) {
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"" + arg0 + "\"]").click();
    }

    @And("Tap on Favourite Button")
    public void tapOnFavouriteButton() {
        driver.findElementByAccessibilityId("Add to Favorites").click();
    }

    @And("Go to Main menu")
    public void goToMainMenu() {
        //driver.findElementByAccessibilityId("BackButton").click();
        driver.findElementByXPath("(//XCUIElementTypeButton[@name=\"BackButton\"])[2]").click();
        driver.findElementByXPath("//XCUIElementTypeCell[@name=\"Favorites\"]/XCUIElementTypeOther[1]").click();
    }

    @Then("I should see {string} into favorite section")
    public void iShouldSeeIntoFavoriteSection(String arg0) {
        driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"Kiwi Cutie\"])[2]").click();
        //Stop Recording
        String video = ((CanRecordScreen)driver).stopRecordingScreen();
        byte[] decodedVideo = Base64.getMimeDecoder().decode(video);

        try {
            Path testVideoDir = Paths.get(System.getProperty("user.dir") + "/videos");
            Files.createDirectories(testVideoDir);
            // test-date.mp4
            Path testVideoFileLocation = Paths.get(testVideoDir.toString(), String.format("%s-%d.%s", "testAddRecipe", System.currentTimeMillis(),"mp4"));
            Files.write(testVideoFileLocation, decodedVideo);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
