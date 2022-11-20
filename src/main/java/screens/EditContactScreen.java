package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class EditContactScreen extends BaseScreen{
    public EditContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityViewText;
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
    @FindBy(id = "com.sheygam.contactapp:id/updateBtn")
    AndroidElement updateBtn;

    public EditContactScreen editNameOnly(String s) {
        shouldHave(activityViewText, "Edit contact", 5);
        type(nameEditText, s);
        return this;
    }

    public EditContactScreen editContactField(String field, String text){
        switch(field){
            case "name":
                type(nameEditText, text);
                break;
            case "lastName":
                type(lastNameEditText, text);
                break;
            case "phone":
                type(phoneEditText, text);
                break;
            case "email":
                type(eMailEdittext, text);
                break;
            case "address":
                type(addressEditText, text);
                break;
            case "descrp":
                type(descrpEdittext, text);
                break;
        }


        return this;
    }

    public ContactListScreen updateChanges(){
        updateBtn.click();
        return new ContactListScreen(driver);
    }


}
