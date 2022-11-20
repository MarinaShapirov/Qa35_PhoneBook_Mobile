package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DatePickerTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver).login(User.builder().email("sonya@gmail.com").psw("Ssonya12345$").build())
                .isContactListActivityPresent();
    }

    @Test(enabled = false)
    public void selectDateCurrentMonth(){
        new ContactListScreen(driver)
                .openDatePickerScreen()
                .openCalendar()
                .selectDate("15 December 2022")
                .isDateChanged("15 December 2022");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logOut();

    }


}
