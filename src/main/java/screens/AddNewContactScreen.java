package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputName'")
    AndroidElement nameEditText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputLastName'")
    AndroidElement lastNameEditText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputEmail'")
    AndroidElement eMailEdittext;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputAddress'")
    AndroidElement phoneEditText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputPassword'")
    AndroidElement addressEditText;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/inputDesc'")
    AndroidElement descrpEdittext;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/createBtn'")
    AndroidElement createBtn;
}
