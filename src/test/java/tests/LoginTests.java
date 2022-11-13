package tests;

import config.AppiumConfig;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {
    @Test
    public void loginSuccess(){
        //boolean res = new SplashScreen(driver)
        //            .checkVersion("1.0.0")
        boolean res = new AuthenticationScreen(driver)
                    .fillEmail("sonya@gmail.com")
                    .fillPsw("Ssonya12345$")
                    .submitLogin()
                    .isContactListActivityPresent();
        Assert.assertTrue(res);

        //new ContactListScreen(driver).logOut();
    }

    @Test
    public void loginSuccessModel(){
        //boolean res = new SplashScreen(driver)
        //            .checkVersion("1.0.0")
        boolean res = new AuthenticationScreen(driver)
                .login(User.builder().email("sonya@gmail.com").psw("Ssonya12345$").build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);

        //new ContactListScreen(driver).logOut();
    }

    @Test
    public void loginNegativeWrongEmail(){
        //boolean res = new SplashScreen(driver)
        //            .checkVersion("1.0.0")
        new AuthenticationScreen(driver)
                .fillEmail("sonyagmail.com")
                .fillPsw("Ssonya12345$")
                .submitLoginNegative()
                .isErrorMsgContainsText("Login or Password incorrect");

        //new ContactListScreen(driver).logOut();
    }
    @AfterMethod
    public void logOut(){
        new ContactListScreen(driver).logOut();
    }

}
