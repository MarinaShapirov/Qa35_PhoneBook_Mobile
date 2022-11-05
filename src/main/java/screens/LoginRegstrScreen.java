package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class LoginRegstrScreen extends BaseScreen{
    public LoginRegstrScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputEmail'")
    AndroidElement eMailEditText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputPassword'")
    AndroidElement passwdEditText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/regBtn'")
    AndroidElement loginBtn;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/loginBtn'")
    AndroidElement regstrBtn;
}