package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver).login(User.builder().email("sonya@gmail.com").psw("Ssonya12345$").build())
                .isContactListActivityPresent();
    }

    @Test
    public void removeOneContactSuccess(){
        new ContactListScreen(driver)
                .removeOneContact();

    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logOut();

    }
}
