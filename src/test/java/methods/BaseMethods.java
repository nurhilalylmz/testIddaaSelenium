package methods;

import jdk.jfr.Description;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.BasePage;

import java.time.Duration;


public class BaseMethods extends BasePage{
    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    public BaseMethods(WebDriver webDriver) {
        this.driver=webDriver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    @Description("İlgili element tıklanabilir olana kadar bekler.")
    protected void waitElementToClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Description("Console'a mesaj yazdırılmasını sağlar.")
    protected void logMessage(String text) {
        System.out.println(text);
    }

    @Description("İlgili elemente tıklanmasını,eğer tıklanamazsa uyarı çıkması sağlanır.")
    protected void clickElement(WebElement element) {
        try {
            element.click();
            waitSeconds(1);
            logMessage(element.getText() + " elementine tıklandı.");
        } catch (Exception e) {
            logMessage("Elemente tıklanamadı. Element: " + element + " Hata: " + e.getMessage());
        }

    }

    @Description("İlgili elemente string ifade girilmesini sağlar.")
    protected void writeText(WebElement element, String text) {
        try {
            if (element.getText().equals("")) {
                element.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
            }
            element.sendKeys(text);

        } catch (Exception e) {
            logMessage("İlgili elemente yazı yazılırken hata oluştu. Element:"+ element +"Hata: " + e.getMessage());
        }
    }

    @Description("Sayfada belirtilen saniye kadar beklmesini sağlar.")
    protected void waitSeconds(int seconds) {
        try {
            logMessage(seconds + " saniye kadar bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logMessage(e.getMessage());
        }
    }

    @Description("Sayfayı belirli bir element yüklenene kadar beklemesini sağlar.")
    protected void waitForPageLoad(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}

