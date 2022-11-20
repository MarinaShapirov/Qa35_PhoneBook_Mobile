package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerExampleScreen extends BaseScreen{
    public DatePickerExampleScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
    @FindBy(id = "com.sheygam.contactapp:id/dateTxt")
    AndroidElement dateText;
    @FindBy(id = "com.sheygam.contactapp:id/dateBtn")
    AndroidElement chDateBtn;
    @FindBy(id = "android:id/date_picker_header_date")
    AndroidElement datePickerHeaderDate;
    @FindBy(id = "android:id/button1")
    AndroidElement okBtn;
    @FindBy(id = "android:id/button2")
    AndroidElement cancelBtn;
    @FindBy(xpath = "//*[@content-desc = '15 December 2022']")
    AndroidElement currentDate;

    public DatePickerExampleScreen openCalendar() {
        shouldHave(activityViewText, "Date picker example", 5);
        chDateBtn.click();
        return this;
    }

    public DatePickerExampleScreen selectDate(String date) {
        pause(500);
        String locator = String.format("//*[@content-desc = '%s']", date);
        driver.findElement(By.xpath(locator)).click();
        okBtn.click();
        return this;
    }

    public DatePickerExampleScreen isDateChanged(String date) {
        String dateOut = dateText.getText();
        LocalDate dateFromElement;
        LocalDate ldDateIn = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd LLLL yyyy"));
        LocalDate ldDateOut   = LocalDate.parse(dateOut, DateTimeFormatter.ofPattern("d/M/yyyy"));
        System.out.println(ldDateOut + "  " + ldDateIn);
        Assert.assertEquals(ldDateIn, ldDateOut);

        //back to Contact list screen
        driver.navigate().back();
        shouldHave(activityViewText, "Contact list", 5);

        return this;
    }

}
