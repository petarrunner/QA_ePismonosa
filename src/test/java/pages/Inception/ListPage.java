package pages.Inception;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListPage extends BaseHelper {
    WebDriver driver;

    public ListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String lastNumberInvoiceFromList = "";

    private void clickButtonCreateInvoice() {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Kreiraj']")));
        jsClick(driver.findElement(By.xpath("//*[text()='Kreiraj']")));
    }

    private void getNumberLastDocument() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("clickable-column")));
        lastNumberInvoiceFromList = driver.findElement(By.className("MuiTableRow-root")).findElement(By.className("clickable-column")).getText();
        System.out.println("Number last document from the List => " + lastNumberInvoiceFromList);
    }

    private void clickLastCreatedInvoice() {
        driver.findElement(By.className("MuiTableRow-root")).findElement(By.className("clickable-column")).click();
    }

    public void publicGetNumberLastDocument() {
        getNumberLastDocument();
        clickLastCreatedInvoice();
    }

    public void publicClickButtonCreateInvoice() {
        clickButtonCreateInvoice();
    }

}
