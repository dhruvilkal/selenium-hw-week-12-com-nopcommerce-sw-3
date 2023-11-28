package computer;
/**
 * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
 *      1.1 Click on Computer Menu.
 *      1.2 Click on Desktop
 *      1.3 Select Sort By position "Name: Z to A"
 *      1.4 Verify the Product will arrange in Descending order.
 * 2.Test name verifyProductAddedToShoppingCartSuccessFully()
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite  extends Utility {

    // Declare baseUrl
    String baseUrl = "https://demo.nopcommerce.com/";

    // Open Browser
    @Before
    public void setUP(){
        openBrowser(baseUrl);
    }
    @Test
  public void   testNameVerifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        // Click on Computer Menu
        mouseHoverOnElementAndClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));

        // click on Desktop
        mouseHoverOnElementAndClick(By.xpath("//li[@class='active last']//a[normalize-space()='Desktops']"));

        // Select Sort By position "Name: Z to A"
        selectByValueFromDropdown(By.xpath("//select[@id='products-orderby']"),"6");
        Thread.sleep(2000);
     // Capture the product prices
        List<WebElement> productPriceElements = driver.findElements(By.cssSelector(".product-price"));
        List<Double> productPrices = new ArrayList<>();
        for (WebElement element : productPriceElements){
            //  // Extract the price from the element and convert it to a double
            double price =Double.parseDouble(element.getText().replace("$",""));
            productPrices.add(price);
        }
           // Check if the product prices are in descending order
        List<Double>sortedPrices = new ArrayList<>(productPrices);
        Collections.sort(sortedPrices,Collections.reverseOrder());
        Assert.assertEquals(productPrices,sortedPrices);

        // Sleep added for visualization, you might want to replace this with a wait strategy
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    // Method name verifyProductAddedToShoppingCartSuccessFully
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException{
        // click on computer menu
        mouseHoverOnElementAndClick(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));

        // click pn desktop

        mouseHoverOnElementAndClick(By.xpath("//li[@class='active last']//a[normalize-space()='Desktops']"));

        // Select Sort By position "Name: A to Z"
        selectByValueFromDropdown(By.xpath("//select[@id='products-orderby']"),"5");
        Thread.sleep(2000);

        //  Click on "Add To Cart"
        mouseHoverOnElementAndClick(By.xpath("(//div[@class='buttons'])[1]"));
         // Verify the Text "Build your own computer"
         String expectedText = "Build your own computer";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='Build your own computer']"));
            Assert.assertEquals(expectedText,actualText);

        // Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByValueFromDropdown(By.xpath("//select[@id='product_attribute_1']"),"1");

        //Select "8GB [+$60.00]" using Select class
        selectByValueFromDropdown(By.xpath("//select[@id='product_attribute_2']"), "5");

        //Select  HDD radio "400 GB [+$100.00]"
        mouseHoverOnElementAndClick(By.xpath("//input[@value='7']"));

        //Select OS radio "Vista Premium [+$60.00]"
        mouseHoverOnElementAndClick(By.xpath("//input[@id='product_attribute_4_9']"));
        Thread.sleep(2000);

        // Check Two Check boxes "Total Commander [+$5.00]"
        mouseHoverOnElementAndClick(By.xpath("//input[@id='product_attribute_5_12']"));


        // Check Two Check boxes "Microsoft Office [+$50.00]"
        List<WebElement> selectCheckBox = driver.findElements(By.xpath("//dd[@id='product_attribute_input_5']//li[1]"));
        for(WebElement element : selectCheckBox){
            if (element.getText().equalsIgnoreCase("Microsoft Office [+$50.00]"));
            element.click();
            break;
        }
        // Verify the price "$1,475.00"
        String expectedText1 = "$1,475.00";
        String actualText1 = getTextFromElement(By.xpath("//span[text() = '$1,475.00']"));
        Assert.assertEquals(expectedText1,actualText1);

        //Click on "Add To Cart"
        mouseHoverOnElementAndClick(By.id("add-to-cart-button-1"));

        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedText2 = "The product has been added to your shopping cart on Top green Bar";
        String actualText2 = getTextFromElement(By.xpath("//p[@class='content']"));
        Assert.assertEquals(expectedText2,actualText2);
        //Close the bar by clicking on the cross button
        mouseHoverOnElementAndClick(By.xpath("//span[@title='Close'])"));

        //  Then MouseHover on "Shopping cart".
        mouseHoverToElement(By.xpath("//li[@id='topcartlink'])"));

        //Click on "GO TO CART" button.
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='Go to cart'])"));

        // Verify the message "Shopping cart"
        String expectedText3 = "Shopping cart";
        String actualText3 = getTextFromElement(By.xpath("//h1[text() = 'Shopping cart'])"));

        //Change the Qty to "2" and Click on "Update shopping cart
        mouseHoverOnElementAndClick(By.xpath("//td[@class='quantity']//input)"));
        Thread.sleep(2000);

        //clear input value in field
        clearInput(By.xpath("//td[@class='quantity']//input"));
        sendTextToElement(By.xpath("//td[@class='quantity']//input"),"2");
        mouseHoverOnElementAndClick(By.xpath("//button[@id='updatecart']"));

        //Verify the Total"$2,950.00"
        String expectedText4 = "$2,950.00";
        String actualText4 = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')])"));
        Assert.assertEquals(expectedText4,actualText4);

        // click on checkbox “I agree with the terms of service”
        mouseHoverOnElementAndClick(By.xpath("//input[@id='termsofservice'])"));

        // Click on “CHECKOUT”
        mouseHoverOnElementAndClick(By.xpath("//button[@id='checkout'])"));

        //Verify the Text “Welcome, Please Sign In!”
        String expectedText5 = "Welcome, Please Sign In!";
        String actualText5 = "//h1[text()= 'Welcome, Please Sign In!'])";
        Assert.assertEquals(expectedText5,actualText5);

        //Click on "CHECKOUT AS GUEST” Tab"
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        // Fill all the details
        // Cardholder name
        sendTextToElement(By.xpath("//input[@id='CardholderName']"),"Manish Kakadiya");

        // card number
        sendTextToElement(By.xpath("//input[@id='CardNumber']"),"5555,3285,6545,3678");

        //select expira date
        selectByValueFromDropdown(By.xpath("//select[@id='ExpireMonth']"),"10");

        selectByValueFromDropdown(By.xpath("//select[@id='ExpireYear']"),"2025");

        // code
        sendTextToElement(By.xpath("//input[@id='CardCode']"),"325");

        // click on “CONTINUE”
        mouseHoverOnElementAndClick(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //Verify “Payment Method” is “Credit Card”
        String expectedText6 = "Payment Method: Credit Card";

        String actualText6= getTextFromElement(By.xpath("//li[@class='payment-method']"));
        Assert.assertEquals(expectedText6,actualText6);

        //Verify “Shipping Method” is “Next Day Air”
        String expectedText7 = "Shipping Method” is “Next Day Air";
        String actualTextText7 = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
        Assert.assertEquals(expectedText7,actualTextText7);

        // Verify Total is “$2,950.00”
        String expectedText8 = "$2,950.00";
        String actualText8 = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]"));
        Assert.assertEquals(expectedText8,actualText8);

        // Click on “CONFIRM”
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='Confirm']"));

        //Verify the Text “Thank You”
        String expectedText9 = "Thank You";
        String actualText9 =getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']"));
        Assert.assertEquals(expectedText9,actualText9);

        //Verify the message “Your order has been successfully processed!”
        String expectedText10 = "Your order has been successfully processed!";
        String actualText10 =getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
        Assert.assertEquals(expectedText10,actualText10);

        //Click on “CONTINUE”
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='Continue']"));

        //Verify the text “Welcome to our store”
        String expectedText11 = "Welcome to our store";
        String actualText11 = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        Assert.assertEquals(expectedText11,actualText11);

    }
    @After
    public void tearDown() {
        closeBrowser();

    }
}
