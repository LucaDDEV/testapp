package Steps;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.File;
import java.io.IOException;

public class StartingSteps {

    // I metodi start & stop mi permettono di avviare o stoppare il server appium in modo programmatico.
    private AppiumDriverLocalService appiumService;
    private IOSDriver driver;

    // @Before viene eseguito prima di uno scenario
    @Before
    public void startAppiumServer() throws IOException {
        appiumService = AppiumDriverLocalService.buildDefaultService();
    }

    // @After viene eseguito al termine di uno scenario
    @After
    public void stopAppiumServer() {
        appiumService.stop();
    }
}