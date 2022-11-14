package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/add_contact_btn']")
    AndroidElement addBtn;

    @FindBy(xpath= "//*[@content-desc='More options']")
    AndroidElement moreOptions;

    @FindBy(id = "com.sheygam.contactapp:id/title")
    AndroidElement logOutBtn;

    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    List<AndroidElement> contactNameList;
    @FindBy(id = "com.sheygam.contactapp:id/rowPhone")
    List<AndroidElement> contactPhoneList;

    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    List<AndroidElement> contactList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;

    @FindBy(id = "android:id/button2")
    AndroidElement cancelBtn;

    public ContactListScreen removeOneContact(){
        //shouldHave(activityViewText, "Contact list", 5);
        AndroidElement contact = contactList.get(0);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension.getHeight());
        System.out.println(dimension.getWidth());

        Rectangle rect = contact.getRect();
        int x1 = 0, x2 = 0, y=0;
        x1 = rect.getX() + rect.getWidth()/8;
        y  = rect.getY() + rect.getHeight()/2; //rect.getX() + rect.getWidth()*0.8;  80 percent
        x2 = x1+ (int)(rect.getWidth()*0.8);

        TouchAction<?> ta = new TouchAction<>(driver);
        ta.longPress(PointOption.point(x1, y))
                .moveTo(PointOption.point(x2, y))
                .release().perform();

        should(yesBtn, 6);
        return this;
    }

    public boolean isContactListActivityPresent(){
        should(addBtn, 10);
        return isShouldHave(activityViewText, "Contact list", 10);
    }

    public  AuthenticationScreen logOut()
    {
        if(isDisplayedOnScreen(moreOptions))
        {
            moreOptions.click();
            logOutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logOut2() {

        if (activityViewText.getText().equals("Contact list")) {
            moreOptions.click();
            logOutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AddNewContactScreen openContactForm(){
        if(isContactListScreenActive())
        {
            should(addBtn, 3);
            addBtn.click();
        }

        return new AddNewContactScreen(driver);
    }
    private boolean isContactListScreenActive(){
        int cnt = 0;
        boolean iaActive = false;
        do{
            if(activityViewText.getText().equals("Contact list") == true)
                iaActive = true;
            else
                pause(500);
        }while((iaActive==false) && (++cnt<7));

        return iaActive;
    }

    private void check(List<AndroidElement> list, String text){
        boolean isFound = false;
        for(AndroidElement el:list)
        {
            System.out.println(el.getText() + "(expected: " + text + ")");
            if(el.getText().contains(text))
            {
                isFound = true;
                break;
            }
        }
        Assert.assertTrue(isFound);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName){
        check(contactNameList, name + " " +  lastName);
        return this;
    }

    public ContactListScreen isContactAddedByPhone(String phone){
        check(contactPhoneList, phone);
        return this;
    }
}
