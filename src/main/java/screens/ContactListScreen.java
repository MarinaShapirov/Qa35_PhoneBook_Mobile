package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

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
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement emptyTextView;
    @FindBy(xpath = "//*[@text = 'Date picker']")
    AndroidElement datePickerBtn;


    @FindBy(id = "com.sheygam.contactapp:id/nameTxt")
    AndroidElement contactNameTxt;
    @FindBy(id = "com.sheygam.contactapp:id/emailTxt")
    AndroidElement contactEmailTxt;
    @FindBy(id = "com.sheygam.contactapp:id/addressTxt")
    AndroidElement contactAddressTxt;
    @FindBy(id = "com.sheygam.contactapp:id/descTxt")
    AndroidElement contactDescTxt;




    int countBefore, countAfter;

    public ContactListScreen removeOneContact(){
        //shouldHave(activityViewText, "Contact list", 5);
        countBefore = contactList.size();
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
        yesBtn.click();
        pause(1000);

        countAfter = contactList.size();

        return this;
    }

    public ContactListScreen IsListSizeDecremented(){
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }

    public ContactListScreen removeAllContact(){
        while(contactList.size()!=0)
            removeOneContact();
        return this;
    }

    public ContactListScreen IsNoContactctHere(){
        Assert.assertTrue(isShouldHave(emptyTextView, "No Contacts. Add One more!", 10));
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

    public ContactListScreen isContactUpdatedByNameOnly(String name){
        check(contactNameList, name);
        return this;
    }

    public ContactListScreen isContactAddedByPhone(String phone){
        check(contactPhoneList, phone);
        return this;
    }

    public ContactListScreen isContactUpdatedByLastNameOnly(String text){
        check(contactNameList, text);
        return this;
    }

    private String getContactSpecificField(String field, int idx){
        String str="";
        AndroidElement contact = contactList.get(idx);
        contact.click();
        switch(field) {
            case "name":
                str = contactNameTxt.getText();
                break;
            case "email":
                str = contactEmailTxt.getText();
                break;
            case "address":
                str = contactAddressTxt.getText();
                break;
            case "descrp":
                str = contactDescTxt.getText();
                break;
        }
        driver.navigate().back();
        shouldHave(activityViewText, "Contact list", 5);

        return str;

    }

    public ContactListScreen isContactUpdatedBySpecificFieldOnly(String field, String text){
        int cnt = contactList.size();
        boolean found = false;
        for(int i=0; i<cnt; i++) {
            if (getContactSpecificField(field, i).equals(text)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found);
        return this;
    }

    public void addContact(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("nTest"+ i)
                .lastname("lTest"+i)
                .email("emailTest"+ i+ "@gmail.com")
                .phone("1234567890"+ i)
                .address("a")
                .descrp("d").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact();

    }

    public ContactListScreen contactProvider() {
        if(contactList.size()<2){
            for(int i=0; i<2; i++)
                addContact();
            }
        return this;
    }

    public EditContactScreen openEditForm() {
        AndroidElement contact = contactList.get(0);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension.getHeight());
        System.out.println(dimension.getWidth());

        Rectangle rect = contact.getRect();
        int x1 = 0, x2 = 0, y=0;
        x2 = rect.getX() + rect.getWidth()/8;
        y  = rect.getY() + rect.getHeight()/2; //rect.getX() + rect.getWidth()*0.8;  80 percent
        x1 = x1+ (int)(rect.getWidth()*0.8);

        TouchAction<?> ta = new TouchAction<>(driver);
        ta.longPress(PointOption.point(x1, y))
                .moveTo(PointOption.point(x2, y))
                .release().perform();

        return new EditContactScreen(driver);
    }

    public DatePickerExampleScreen openDatePickerScreen() {
        should(moreOptions, 5);
        moreOptions.click();
        datePickerBtn.click();
        return new DatePickerExampleScreen(driver);
    }


}
