package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BaseHelper {
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    protected static FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(25)).pollingEvery(Duration.ofMillis(100));
    protected static JavascriptExecutor js = (JavascriptExecutor) driver;
    protected static SoftAssert softAssert = new SoftAssert();
    protected static boolean firstTest = true;

    public void sleep(int TIME_SEC) {
        try {
            Thread.sleep(TIME_SEC * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitMessageToDisappear() {
        wdWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("Toastify--animate-icon")));
    }

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}
