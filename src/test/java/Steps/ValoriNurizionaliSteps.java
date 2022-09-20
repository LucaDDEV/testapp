package Steps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ValoriNurizionaliSteps {

    private IOSDriver driver;
    @Given("I am in the app main page")
    public void iAmInTheAppMainPage() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "iPad Pro (11-inch)");
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("appium:platformVersion", "15.5");
        desiredCapabilities.setCapability("bundleId", "eu.testapp.com");
        desiredCapabilities.setCapability("appium:app", "/Users/luca/Downloads/Fruta-fnjxsnyhnxyxmrfjpxwhjbbhnxps/Build/Products/Debug-iphonesimulator/Fruta iOS.app");
        desiredCapabilities.setCapability("appium:newCommandTimeout", 30);
        //desiredCapabilities.setCapability("noReset", false);
        desiredCapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
        //desiredCapabilities.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
        //desiredCapabilities.setCapability("appium:useNewWDA", true);
        //desiredCapabilities.setCapability("appium:usePrebuiltWDA", true);
        //desiredCapabilities.setCapability("useNewWDA", true);
        desiredCapabilities.setCapability("udid", "68D04343-700D-456B-95B9-891CEFF3503B");
        //desiredCapabilities.setCapability("clearSystemFiles", true);
        URL remoteUrl = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new IOSDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Start Recording
        driver.startRecordingScreen();
        driver.findElementByAccessibilityId("BackButton").click();
        driver.findElementByXPath("(//XCUIElementTypeButton[@name=\"BackButton\"])[2]").click();
    }

    @And("Tap on top recipe {string}")
    public void tapOnTopRecipe(String arg0) {
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"" + arg0 + "\"]").click();
    }

    @And("Tap on ingredient {string}")
    public void tapOnIngredient(String arg0) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //(//XCUIElementTypeImage[@name="Avocado"])[3]
        //driver.findElementsById("6D010000-0000-0000-BB16-010000000000");
        //driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"Spinach\"])[3]").click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//XCUIElementTypeImage[@name=\"Spinach\"])[3]")));
        TouchAction touchAction = new TouchAction(driver);
        Thread.sleep(1000);
        touchAction.longPress(new PointOption().withCoordinates(90, 548)).perform();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text1")));
    }

    @And("Tap on info")
    public void tapOnInfo() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        Thread.sleep(1000);
        touchAction.longPress(new PointOption().withCoordinates(577, 864)).perform();
    }

    @Then("I can see nutritional value")
    public void iCanSeeNutritionalValueNome(DataTable table) {
        Integer failure = 0;

        List<List<String>> dati = table.asLists();
        for (int i = 1; i < dati.size(); i++) {
            //System.out.println(dati.get(i).get(0));

            // Nome
            WebElement elemento = driver.findElementByAccessibilityId(dati.get(i).get(0));
            String attributoIndex = elemento.getAttribute("index");
            String attributoNome = elemento.getAttribute("name");

            // Valore
            WebElement valore = driver.findElementByAccessibilityId(dati.get(i).get(1));
            String valoreIndex = valore.getAttribute("index");
            String valoreNome = valore.getAttribute("name");

            System.out.println("Attributo Nome:" + attributoNome + " " + "Attributo Index:" + attributoIndex + " | " + "Valore Nome:" + valoreNome + " " + "Valore Index:" + valoreIndex + "\n *******");

            // Index
            String indexValoreTabella = dati.get(i).get(2);

            try {
                Assertions.assertEquals(indexValoreTabella, valoreIndex);
            } catch (AssertionError e) {
                failure ++;
                System.out.println(e.getMessage());
                System.out.println(failure);
            }
        }

        if (failure >= 1) {
            Assertions.assertEquals(1, 0);
        } else {
            System.out.println("ok");
        }

    }

}
