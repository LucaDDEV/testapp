package Steps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class AddRecipeSteps {

    //poin
    PointOption pointOptionStart, pointOptionEnd;

    private IOSDriver driver;
    @Given("I am in the app main")
    public void iAmInTheAppMain() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "iPad Pro (11-inch)");
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("appium:platformVersion", "15.5");
        desiredCapabilities.setCapability("appium:app", "/Users/luca/Downloads/Fruta-fnjxsnyhnxyxmrfjpxwhjbbhnxps/Build/Products/Debug-iphonesimulator/Fruta iOS.app");
        desiredCapabilities.setCapability("bundleId", "eu.testapp.com");
        desiredCapabilities.setCapability("appium:newCommandTimeout", 30);
        desiredCapabilities.setCapability("noReset", false);
        desiredCapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
        //desiredCapabilities.setCapability("appium:usePrebuiltWDA", true);
        //desiredCapabilities.setCapability("useNewWDA", true);
        desiredCapabilities.setCapability("udid", "E5002DE4-1DB8-448B-862E-C298D412A88A");
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

    // ciao da runner 
    @And("Tap on Favourite Button")
    public void tapOnFavouriteButton() {
        driver.findElementByAccessibilityId("Add to Favorites").click();
    }

    @And("Go to Main menu")
    public void goToMainMenu() {
        driver.findElementByAccessibilityId("BackButton").click();
        driver.findElementByXPath("(//XCUIElementTypeButton[@name=\"BackButton\"])[2]").click();
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Favorites\"]").click();

    }

    @And("I should see {string} into favorite section")
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

    @Then("Swipe to delete")
    public void swipeToDelete() throws InterruptedException {
        Thread.sleep(5000);
        pointOptionStart = PointOption.point(600, 157);
        pointOptionEnd = PointOption.point(400,157);


        new TouchAction(driver)

                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                    .moveTo(pointOptionEnd)
                    .release().perform();

    }

    @And("Tap on ApplePay")
    public void tapOnApplePay() {
        driver.findElementByAccessibilityId("Buy with Apple Pay").click();
    }

    @Then("Swipe a caso")
    public void swipeACaso() throws InterruptedException {
        Thread.sleep(2000);
        pointOptionStart = PointOption.point(406, 173);
        pointOptionEnd = PointOption.point(466,800);


        new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
                    .moveTo(pointOptionEnd)
                    .release().perform();

    }
}
