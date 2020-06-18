package test;

import methods.BaseMethods;
import methods.LoginMethods;
import org.testng.annotations.Test;
import pages.BasePage;

public class LoginMethodsTest extends BasePage {
    LoginMethods loginPage ;

    @Test(description = "Başarılı login işlemi gerçekleşmesi beklenir.")
    public void successLogin() {
        loginPage= new LoginMethods(driver);
        loginPage
                .login("05349199918", "qwerty1")
                .closePopup()
                .checkUserMainPage("https://test.iddaa.com/");
    }
}
