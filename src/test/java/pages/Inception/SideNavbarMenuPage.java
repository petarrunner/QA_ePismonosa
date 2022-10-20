package pages.Inception;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SideNavbarMenuPage extends BaseHelper {
    WebDriver driver;

    public SideNavbarMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "flex-grow")
    WebElement SIDE_NAVBAR_MENU;
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div[2]/div[1]/ul/div[4]/div/div")
    WebElement INVOICES_BOX;

    private void clickProfileIcon() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("user-box"))).click();
    }

    private void clickLogOutButton() {
        driver.findElement(By.xpath("//*[text()='Odjavi se']")).click();
    }

    private void clickInvoice() {
        driver.findElement(By.xpath("//*[text()='Fakture']")).click();
        sleep(2);
    }

    private void clickSearchedInvoices(String invoiceType, String option) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Izlazna dokumenta']")));
        List<WebElement> listLinks = driver.findElements(By.tagName("a"));
        listLinks.removeIf(n -> !n.getAttribute("href").contains(invoiceType));
        listLinks.removeIf(n -> !n.findElement(By.tagName("p")).getText().contains(option));
        System.out.println("Links size => " + listLinks.size());
        System.out.println("Links size => " + listLinks.get(0).getAttribute("href"));
        listLinks.get(0).click();
    }

    private void selectPaymentOrders(String optionPaymentOrders) {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div[1]/ul/div[4]/div/div/div[3]/a[1]")));
        SIDE_NAVBAR_MENU.findElement(By.cssSelector("[title='" + optionPaymentOrders + "']")).click();
    }

    public void publicClickInvoiceBill(boolean invoicesOpened, String invoiceType, String option) {
        if (!invoicesOpened) clickInvoice();
        clickSearchedInvoices(invoiceType, option);
    }

    public void publicClickInvoicePaymentOrders(String optionPaymentOrders) {
        clickInvoice();
        selectPaymentOrders(optionPaymentOrders);
    }

    public boolean checkIfUserBoxIsDisplayed() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("user-box")));
        return driver.findElement(By.className("user-box")).isDisplayed();
    }

    public void logOut() {
        clickProfileIcon();
        clickLogOutButton();
    }
}
