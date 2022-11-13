package tests;

import config.AppiumConfig;
import models.Contact;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
      new AuthenticationScreen(driver).login(User.builder().email("sonya@gmail.com").psw("Ssonya12345$").build());
}

    @Test
    public void addNewContactSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("n"+ i)
                .lastname("l"+i)
                .email("email"+ i+ "@gmail.com")
                .phone("1234567890"+ i)
                .address("a")
                .descrp("d").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact()
                .isContactAddedByName(contact.getName(), contact.getLastname())
                .isContactAddedByPhone(contact.getPhone());

    }
    @Test
    public void addNewContactNegativeWrongEmail(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("n")
                .lastname("l")
                .email("email"+ i+ "gmail.com")
                .phone("1234567890")
                .address("a")
                .descrp("d").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactNegative()
                .isErrorMsgContainsText("must be a well-formed email address");
    }

    @Test
    public void addNewContactNegativeWrongPhone(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("n")
                .lastname("l")
                .email("email"+ i+ "@gmail.com")
                .phone("12345678")
                .address("a")
                .descrp("d").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactNegative()
                .isErrorMsgContainsText("Phone number must contain");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logOut();

    }
}
