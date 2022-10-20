package pages.Inception;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InvoiceCreatePage extends BaseHelper {
    WebDriver driver;

    public InvoiceCreatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void addSender() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-ackcql")));
        WebElement inputSender = driver.findElement(By.className("css-ackcql")).findElement(By.tagName("input"));
        inputSender.sendKeys("Ime");
        sleep(1);
        // wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div[1]/div/div[1]/div[2]/div/div/input")));
        inputSender.sendKeys(Keys.RETURN);
    }

    private void addAccountNumber() {
        WebElement input = driver.findElement(By.className("css-ackcql")).findElement(By.tagName("input"));
        input.click();
        //wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-26l3qy-menu")));
        sleep(1);
        input.sendKeys(Keys.RETURN);
    }

    private void clickTabListArticles() {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("MuiTab-labelIcon")));
        List<WebElement> list = driver.findElements(By.className("MuiTab-labelIcon"));
        list.get(1).click();
    }

    private void addArticleToList() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("on-focus")));
        WebElement input = driver.findElement(By.id("on-focus"));
        input.click();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-26l3qy-menu")));
        input.sendKeys(Keys.RETURN);
        WebElement inputNumber = driver.findElement(By.className("MuiOutlinedInput-inputMarginDense"));
        inputNumber.sendKeys("10");
        inputNumber.sendKeys(Keys.RETURN);
    }

    private void clickConfirm() {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("ml-3"))).click();
    }

    public void publicAddInvoice() {
        addSender();
        if (driver.getCurrentUrl().contains("outcome")) addAccountNumber();
        clickTabListArticles();
        addArticleToList();
        clickConfirm();
    }
}
