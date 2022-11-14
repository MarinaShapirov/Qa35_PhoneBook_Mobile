package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import models.User;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    AndroidElement nameEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    AndroidElement lastNameEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement eMailEdittext;

    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    AndroidElement phoneEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    AndroidElement addressEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    AndroidElement descrpEdittext;

    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    AndroidElement createBtn;

    @FindBy(id = "android:id/message")
    AndroidElement errTextView;
    //@FindBy(id = "android:id/button1")
    @FindBy(xpath = "//*[@text = 'OK']")
    AndroidElement errOkBtn;


       public AddNewContactScreen fillContactForm(Contact contact){
        should(nameEditText, 10);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastname());
        type(eMailEdittext, contact.getEmail());
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descrpEdittext, contact.getDescrp());
        return this;
    }

    public ContactListScreen submitContact(){
        createBtn.click();
        pause(5000);
        return new ContactListScreen(driver);

    }

    public AddNewContactScreen submitContactNegative(){
        createBtn.click();
        return this;
    }

    public AddNewContactScreen isErrorMsgContainsText(String s) {
        pause(2000);
        Assert.assertTrue(errTextView.getText().contains(s));
        errOkBtn.click();
        return this;
    }


}
