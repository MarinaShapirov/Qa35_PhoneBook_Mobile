package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class EditContactTests extends AppiumConfig {
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
    public void editFirstContactSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        String s = "nameTest" + i;
        new ContactListScreen(driver)
                .openEditForm()
                .editNameOnly(s)
                .updateChanges()
                .isContactUpdatedBySpecificFieldOnly("name", s);

    }

    @Test
    public void editFirstContactSuccessByLastName(){
        int i = new Random().nextInt(1000) + 1000;
        String s = "lastNameTest" + i;
        new ContactListScreen(driver)
                .openEditForm()
                .editContactField("lastName", s)
                .updateChanges()
                .isContactUpdatedByLastNameOnly(s);

    }
    @Test
    public void editFirstContactSuccessByPhone(){
        int i = new Random().nextInt(1000) + 1000;
        String s = "1111111111" + i;
        new ContactListScreen(driver)
                .openEditForm()
                .editContactField("phone", s)
                .updateChanges()
                .isContactAddedByPhone(s);

    }

    @Test
    public void editFirstContactSuccessByEmail(){
        int i = new Random().nextInt(1000) + 1000;
        String s = "emailTest"+ i+"@gmail.com";
        new ContactListScreen(driver)
                .openEditForm()
                .editContactField("email", s)
                .updateChanges()
                .isContactUpdatedBySpecificFieldOnly("email", s);
   }

    @Test
    public void editFirstContactSuccessByAddress() {
        int i = new Random().nextInt(1000) + 1000;
        String s = "addressTest" + i;
        new ContactListScreen(driver)
                .openEditForm()
                .editContactField("address", s)
                .updateChanges()
                .isContactUpdatedBySpecificFieldOnly("address", s);
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logOut();

    }




}

