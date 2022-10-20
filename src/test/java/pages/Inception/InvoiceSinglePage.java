package pages.Inception;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.util.Set;

public class InvoiceSinglePage extends BaseHelper {
    WebDriver driver;

    public InvoiceSinglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String numberInvoice = "";


    private void getDocumentNumber() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Zavodni broj']")));
        numberInvoice = driver.findElement(By.className("page-header")).findElements(By.tagName("p")).get(2).getText();
        System.out.println("Number document => " + numberInvoice);
    }

    private void moveToNextWindowsTab() {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
    }


    private void clickActions() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Zavodni broj']")));
        //wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Akcije']")));
        WebElement buttonAction = driver.findElement(By.className("page-header")).findElement(By.tagName("button"));
        buttonAction.click();
        System.out.println("Click button Actions");
    }


    private void clickDeleteInvoice(String option) {
        System.out.println("Click list Actions" + option);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("nav-link-icon")));
        jsClick(driver.findElement(By.xpath("//*[text()='" + option + "']")));
        if (option == "Brisanje fakture") {
            System.out.println("Brisanje fakture" + option);
            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("dialog-actions")));
            WebElement buttonConfirmDelete = driver.findElement(By.className("dialog-actions")).findElements(By.tagName("button")).get(1);
            jsClick(buttonConfirmDelete);
        }
    }

    private void checkDownloadedFile() {
        String pathDownloadedFile = "C:/Users/chouw/Downloads/" + numberInvoice.replace("/", "_") + ".pdf";
        File f = new File(pathDownloadedFile);
        fluentWait.until(x -> f.length() > 0);
        Assert.assertTrue(f.exists());
        System.out.println("F => " + f);
        System.out.println("Boolean exist F => " + f.exists());
        System.out.println("F.length => " + f.length());
    }

    public void publicCheckDownloadedFile() {
        checkDownloadedFile();
    }

    public void publicGetDocumentNumber() {
        getDocumentNumber();
    }

    public void clickActionAndSelectOption(String option) {
        clickActions();
        clickDeleteInvoice(option);
    }

    public void moveToCurrentTabClickActionAndSelectOption(String option) {
        moveToNextWindowsTab();
        clickActions();
        clickDeleteInvoice(option);
    }
}
