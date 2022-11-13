package tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LaunchTest extends AppiumConfig {
    @Test(enabled = false)
    public void launch(){

        String version = new SplashScreen(driver).getCurrentVer();
        Assert.assertTrue(version.contains("1.0.0"));

    }

}
