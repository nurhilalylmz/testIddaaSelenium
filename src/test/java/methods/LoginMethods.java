package methods;

import contants.ContantsLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginMethods extends BaseMethods{
    ContantsLoginPage loginPage = PageFactory.initElements(driver, ContantsLoginPage.class);

    public LoginMethods(WebDriver webDriver) {
        super(webDriver);
    }


    public LoginMethods login(String phoneNumber, String password) {
        waitForPageLoad(loginPage.copyrightText);
        writeText(loginPage.phoneNumber, phoneNumber);
        waitSeconds(1);
        writeText(loginPage.password, password);
        waitSeconds(1);
        clickElement(loginPage.buttonLogin);
        waitSeconds(1);
        return new LoginMethods(driver);
    }
    public LoginMethods checkUserMainPage(String expectingUrl) {
        waitForPageLoad(loginPage.copyrightText);
        Assert.assertEquals(driver.getCurrentUrl(), expectingUrl, "İstenen sayfada olunmadığı görüldü");
        return new LoginMethods(driver);
    }
    public LoginMethods closePopup() {
        waitElementToClickable(loginPage.buttonClosePopup);
        clickElement(loginPage.buttonClosePopup);
        return new LoginMethods(driver);
    }

}
