package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen {
    protected static AppiumDriver<AndroidElement> driver;

    public BaseScreen(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void type(AndroidElement el, String text){
        if(text != null){
            el.click();
            el.clear();
            el.sendKeys(text);
        }
        driver.hideKeyboard();
    }
    public void pause (int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void should(AndroidElement el, int time){
        new WebDriverWait(driver, time)
                .until(ExpectedConditions.visibilityOf(el));
    }

    public boolean isShouldHave(AndroidElement el, String text, int time){
        return
                new WebDriverWait(driver, time)
                .until(ExpectedConditions.textToBePresentInElement(el, text));
    }

    public boolean isDisplayedOnScreen(AndroidElement el){
        try {
            should(el, 5);
            return el.isDisplayed();
        }catch(Exception ex){
            return false;
        }

    }
}
