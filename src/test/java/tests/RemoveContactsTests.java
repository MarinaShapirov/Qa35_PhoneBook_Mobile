package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver).login(User.builder().email("sonya@gmail.com").psw("Ssonya12345$").build())
                .isContactListActivityPresent();
    }
    @BeforeMethod
    public void contactsProvider() {
        new ContactListScreen(driver)
                .contactProvider();
    }

    @Test
    public void removeOneContactSuccess(){
        new ContactListScreen(driver)
                .removeOneContact()
                .IsListSizeDecremented();

    }

    @Test
    public void removeAllContactSuccess(){
        new ContactListScreen(driver)
                .removeAllContact()
                .IsNoContactctHere();

    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logOut();

    }
}
