package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputEmail']")
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement eMailEditText;
    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputPassword']")
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement passwdEditText;
    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/loginBtn']")
    //@FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    @FindBy(xpath = "//*[@text = 'LOGIN']")
    AndroidElement loginBtn;
    //@FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/regBtn']")
    //@FindBy(id = "com.sheygam.contactapp:id/regBtn")
    @FindBy(xpath = "//*[@text = 'REGISTRATION']")
    AndroidElement regstrBtn;


    @FindBy(id = "android:id/message")
    AndroidElement errTextView;
    //@FindBy(id = "android:id/button1")
    @FindBy(xpath = "//*[@text = 'OK']")
    AndroidElement errOkBtn;



    public AuthenticationScreen fillEmail(String eMail){
        should(eMailEditText, 7);
        type(eMailEditText, eMail);
        return this;
    }

    public AuthenticationScreen fillPsw(String psw){
        type(passwdEditText, psw);
        return this;
    }
    public ContactListScreen submitLogin(){
        loginBtn.click();
        return new ContactListScreen(driver);
    }
    public ContactListScreen login(User user){
        if (isShouldHave(activityViewText, "Authentication", 10)) {
            should(eMailEditText, 7);
            type(eMailEditText, user.getEmail());
            type(passwdEditText, user.getPsw());
            loginBtn.click();
        }
        return new ContactListScreen(driver);
    }

    public ContactListScreen registration(User user){
        should(eMailEditText, 7);
        type(eMailEditText, user.getEmail());
        type(passwdEditText, user.getPsw());
        regstrBtn.click();
        return new ContactListScreen(driver);
    }

    public ContactListScreen submitRegistration(){
        regstrBtn.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen submitRegistrNegative(){
        regstrBtn.click();
        return this;
    }

    public AuthenticationScreen registrationNegative(User user){
        should(eMailEditText, 7);
        type(eMailEditText, user.getEmail());
        type(passwdEditText, user.getPsw());
        regstrBtn.click();
        return this;
    }

    public AuthenticationScreen isErrorMsgContainsText(String s) {
        pause(2000);
        Assert.assertTrue(errTextView.getText().contains(s));
        errOkBtn.click();
        return this;
    }

    public AuthenticationScreen isAlertErorrMessageContaisText(String text){
        Alert alert = new WebDriverWait(driver,10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

        return this;
    }

    public AuthenticationScreen submitLoginNegative() {
        loginBtn.click();
        return this;

    }
}
