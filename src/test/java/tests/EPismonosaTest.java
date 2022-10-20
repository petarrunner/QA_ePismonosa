package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Inception.*;

public class EPismonosaTest extends BaseTest {
    public static final String USERNAME = "petar.vlatkovic";
    public static final String PASSWORD = "Petar123";
    public static final String OPTION = "Račun";
    public static final String INCOME = "income";
    public static final String OUTCOME = "outcome";
    public static final String PAYMENT_ORDERS = "payment-orders";
    public static final String CREATE_PAYMENT_ORDERS = "create";
    public static final String GROUPS_PAYMENT_ORDERS = "groups";


    @Test
    public void createAndDeleteInvoice() {
        LoginPage loginPage = new LoginPage(driver);
        SideNavbarMenuPage mainPage = new SideNavbarMenuPage(driver);
        ListPage listPage = new ListPage(driver);
        InvoiceCreatePage addInvoicesPage = new InvoiceCreatePage(driver);
        InvoiceSinglePage singleInvoicesPage = new InvoiceSinglePage(driver);

        String invoice_type = OUTCOME;
        String option = OPTION;

        // Login
        loginPage.validLogin(USERNAME, PASSWORD);
        softAssert.assertTrue(mainPage.checkIfUserBoxIsDisplayed(), "There isn't field with users information.");

        // Create new invoice
        mainPage.publicClickInvoiceBill(false, invoice_type, option);
        Assert.assertTrue(driver.getCurrentUrl().contains(invoice_type), "Wrong page is opened. Current url is wrong.");
        listPage.publicClickButtonCreateInvoice();
        addInvoicesPage.publicAddInvoice();
        singleInvoicesPage.publicGetDocumentNumber();

        // Go to list and check the last invoice
        mainPage.publicClickInvoiceBill(true, invoice_type, option);
        listPage.publicGetNumberLastDocument();
        Assert.assertEquals(singleInvoicesPage.numberInvoice, listPage.lastNumberInvoiceFromList, "Created invoice doesn't exist in the list of invoices.");

        // Delete last invoice, go back on first window and check last invoice in the list after delete
        singleInvoicesPage.moveToCurrentTabClickActionAndSelectOption("Brisanje fakture");
        driver.getWindowHandle();
        listPage.publicGetNumberLastDocument();
        Assert.assertNotEquals(listPage.lastNumberInvoiceFromList, singleInvoicesPage.numberInvoice, "Created invoice isn't deleted and there is still in the list.");
    }

    @Test
    public void createPaymentOrders() {
        LoginPage loginPage = new LoginPage(driver);
        SideNavbarMenuPage mainPage = new SideNavbarMenuPage(driver);
        loginPage.validLogin(USERNAME, PASSWORD);
        softAssert.assertTrue(mainPage.checkIfUserBoxIsDisplayed(), "There isn't field with users information.");
        mainPage.publicClickInvoicePaymentOrders(GROUPS_PAYMENT_ORDERS);
        softAssert.assertTrue(driver.getCurrentUrl().contains("create"), "Wrong current URL");
    }

    @Test
    public void downloadPDFOutcomeInvoice() {
        LoginPage loginPage = new LoginPage(driver);
        SideNavbarMenuPage mainPage = new SideNavbarMenuPage(driver);
        ListPage listPage = new ListPage(driver);
        InvoiceCreatePage addInvoicesPage = new InvoiceCreatePage(driver);
        InvoiceSinglePage singleInvoicesPage = new InvoiceSinglePage(driver);

        String invoice_type = OUTCOME;
        String option = OPTION;

        // Go to last outcome invoice
        loginPage.validLogin(USERNAME, PASSWORD);
        softAssert.assertTrue(mainPage.checkIfUserBoxIsDisplayed(), "There isn't field with users information.");

        // Select Invoice and create new one
        mainPage.publicClickInvoiceBill(false, invoice_type, option);
        Assert.assertTrue(driver.getCurrentUrl().contains(OUTCOME), "Wrong page is opened. Current url is wrong.");

        // Click button Create
        listPage.publicClickButtonCreateInvoice();

        // Create new invoice
        addInvoicesPage.publicAddInvoice();

        // Save number
        singleInvoicesPage.publicGetDocumentNumber();
        waitMessageToDisappear();

        // Click on Action button, select PDF download, click "Preuzmi PDF vizualizaciju"
        singleInvoicesPage.clickActionAndSelectOption("Preuzmi PDF vizualizaciju");


        // Check if file is downloaded
        singleInvoicesPage.publicCheckDownloadedFile();

        // Delete invoice
        singleInvoicesPage.clickActionAndSelectOption("Brisanje fakture");

        // Check last outcome invoice in list to confirm invoice is deleted
        driver.getWindowHandle();
        listPage.publicGetNumberLastDocument();
        Assert.assertNotEquals(listPage.lastNumberInvoiceFromList, singleInvoicesPage.numberInvoice, "Created invoice isn't deleted and there is still in the list.");
    }

    @Test
    public void deleteLastInvoice() {
        LoginPage loginPage = new LoginPage(driver);
        SideNavbarMenuPage mainPage = new SideNavbarMenuPage(driver);
        ListPage listPage = new ListPage(driver);
        InvoiceSinglePage singleInvoicesPage = new InvoiceSinglePage(driver);

        String invoice_type = INCOME;
        String option = OPTION;

        // Login
        loginPage.validLogin(USERNAME, PASSWORD);
        softAssert.assertTrue(mainPage.checkIfUserBoxIsDisplayed(), "There isn't field with users information.");

        // Go to list, click on last and delete
        for (int i = 0; i < 1; i++) {
            mainPage.publicClickInvoiceBill(false, invoice_type, option);
            Assert.assertTrue(driver.getCurrentUrl().contains(invoice_type), "Wrong page is opened. Current url is wrong.");
            listPage.publicGetNumberLastDocument();
            singleInvoicesPage.moveToCurrentTabClickActionAndSelectOption("Brisanje fakture");
            driver.getWindowHandle();
            listPage.publicGetNumberLastDocument();
            Assert.assertNotEquals(listPage.lastNumberInvoiceFromList, singleInvoicesPage.numberInvoice, "Created invoice isn't deleted and there is still in the list.");
        }
    }
}
