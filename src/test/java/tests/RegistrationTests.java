package tests;

import config.AppiumConfig;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void regstrSuccess(){
        //boolean res = new SplashScreen(driver)
        //            .checkVersion("1.0.0")
        int i  = new Random().nextInt(1000)+1000;
        boolean res = new AuthenticationScreen(driver)
                .fillEmail("sonya"+ i + "@gmail.com")
                .fillPsw("Ssonya12345$")
                .submitRegistration()
                .isContactListActivityPresent();
        Assert.assertTrue(res);

        //new ContactListScreen(driver).logOut();
    }

    @Test
    public void registrSuccessModel(){
        //boolean res = new SplashScreen(driver)
        //            .checkVersion("1.0.0")
        int i  = new Random().nextInt(1000)+1000;
        boolean res = new AuthenticationScreen(driver)
                .registration(User.builder().email("sonya"+ i + "@gmail.com").psw("Ssonya12345$").build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);

        //new ContactListScreen(driver).logOut();
    }
    @Test
    public void registrNegativeWrongEmail(){
        new AuthenticationScreen(driver)
                .fillEmail("sonyagmail.com")
                .fillPsw("Ssonya12345$")
                .submitRegistrNegative()
                .isErrorMsgContainsText("must be a well-formed email address");

    }

    @Test
    public void registrNegativeWrongPsw(){
        int i = new Random().nextInt(1000)+1000;
        new AuthenticationScreen(driver)
                .fillEmail("sonya"+ i+ "@gmail.com")
                .fillPsw("sonya12345")
                .submitRegistrNegative()
                .isErrorMsgContainsText("At least 8 character");

    }
    @AfterMethod
    public void logOut(){
        new ContactListScreen(driver).logOut2();
    }
}
