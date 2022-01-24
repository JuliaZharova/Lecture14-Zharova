import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClassForMethods {
    public void test1() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://rozetka.com.ua");
            WebElement searchField = driver.findElement(By.xpath("//input[@type=\"text\"]"));
            searchField.click();
            searchField.sendKeys("Автономная солнечная станция Risen 5 кВт/час");
            System.out.println("Search field is displayed " + searchField.isDisplayed());
            searchField.sendKeys(Keys.ENTER);

            System.out.println("Current URL is " + driver.getCurrentUrl());
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"buy-button button button_with_icon button_color_green button_size_large ng-star-inserted\"]")));

            WebElement buyButton = driver.findElement(By.xpath("//button[@class=\"buy-button button button_with_icon button_color_green button_size_large ng-star-inserted\"]"));
            System.out.println("Search button is displayed " + buyButton.isDisplayed());
            buyButton.click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class=\"modal__heading\"]")));
            WebElement shopCart = driver.findElement(By.xpath("//h3[@class=\"modal__heading\"]"));
            System.out.println("Shopping cart is displayed " + shopCart.isDisplayed());
            System.out.println(shopCart.getText());

            WebElement productInShopCart = driver.findElement(By.xpath("//a [@class=\"cart-product__title\"]"));
            System.out.println(productInShopCart.getText());

            if (shopCart.getText().equals("Корзина") && productInShopCart.getText().equals("Автономная солнечная станция Risen 5 кВт/час"))
                System.out.println("Test 1 passed successfully :)");
        } finally {
            driver.quit();
        }
    }

    public void test2() throws StaleElementReferenceException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://rozetka.com.ua");

            WebElement catalogButton = driver.findElement(By.xpath("// button[@class=\"button button--medium button--with-icon menu__toggle ng-star-inserted\"]"));
            System.out.println("Search field is displayed " + catalogButton.isDisplayed());
            catalogButton.click();

            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.linkText("Мониторы")));
            WebElement category = driver.findElement(By.linkText("Мониторы"));
            System.out.println("Search category is displayed " + category.isDisplayed());
            category.click();
            try {
                new WebDriverWait(driver, Duration.ofSeconds(30))
                        . until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-id=\"Rozetka\"]")));
                WebElement sellerRozetka = driver.findElement(By.xpath("//a[@data-id=\"Rozetka\"]"));
                sellerRozetka.click();
            } catch (StaleElementReferenceException elementReferenceException) {
                System.out.println("An irrelevant link to an element causes an exception.");
            }

            WebElement searchResult = driver.findElement(By.xpath("//span[@class=\"goods-tile__title\"]"));
            System.out.println(searchResult.getText());
            String textSearchResult = searchResult.getText();
            searchResult.click();
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class=\"product__title\"]")));
            WebElement selectedItem = driver.findElement(By.xpath("//h1[@class=\"product__title\"]"));
            System.out.println("Selected item is displayed " + selectedItem.isDisplayed());
            String textSelectedItem = selectedItem.getText();

            WebElement rozetkaSeller = driver.findElement(By.xpath("//*[@id=\"#scrollArea\"]//rz-marketplace-link/strong"));
            System.out.println("Seller is " + rozetkaSeller.getText());
            String textRozetkaSeller = rozetkaSeller.getText();

            if (textSelectedItem.equals(textSearchResult) || textRozetkaSeller.equals("Rozetka"))
                System.out.println("Test 2 passed successfully :)");
        } finally {
         driver.quit();
        }

    }
}
