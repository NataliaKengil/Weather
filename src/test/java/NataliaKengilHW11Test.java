import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class NataliaKengilHW11Test {

    /*TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
     * и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test

    public void testGuideUrlAndTitle() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Documents\\Chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement guideElementInMenu = driver.findElement(
                By.xpath("//a[@href='/guide']")
        );
        guideElementInMenu.click();

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();
        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);


        driver.quit();
    }

    /*TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test

    public void testImperialTemperatureInFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Documents\\Chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String fahrenheitSymbol = "°F";
        String expectedResult = "°F";

        driver.get(url);
        //    driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuImperial = driver.findElement(
                By.xpath("//div[@class = 'option'][text()='Imperial: °F, mph']")
        );
        menuImperial.click();
        Thread.sleep(5000);

        WebElement tempInFahrenheit = driver.findElement(
                By.xpath("//div[@class='current-temp']/span[@class='heading']")
        );

        String tempInF = tempInFahrenheit.getText();

        Thread.sleep(5000);
        String actualResult = tempInF.substring((tempInF.length() - 2)); //метод Стринг,кот. выводит кусочек
        // желаемого текста -20°F 85°F
        Assert.assertTrue(tempInFahrenheit.getText().contains(fahrenheitSymbol));
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /*TC_11_03
     * 1. Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
     *    We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     *    You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test

    public void testCookiesAndApproveTwoButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Documents\\Chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data collected is anonymised." +
                " You can allow all cookies or manage them individually.";
        String button1Text = "Allow all";
        String button2Text = "Manage cookies";

        driver.get(url);
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement(
                By.className("stick-footer-panel__container")).isDisplayed());

        WebElement cookiesDescription = driver.findElement(
                By.className("stick-footer-panel__description"));

        String actualResult = cookiesDescription.getText();

        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(driver.findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).size(), 2);

        //  Assert.assertTrue(
        //          driver.findElement(
        //          By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text()='"+
        //                  button1Text+"']")
        //          .isDisplayed()
        //  ));
//
        //  Assert.assertTrue(
        //          driver.findElement(
        //          By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text()='"+
        //                  button2Text+"']")
        //          .isDisplayed()
        //          ));
//
        driver.quit();
    }

    /*TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test
    public void testThreeSubMenusAreDisplayedInMenuSupport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Documents\\Chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultSubMenu1 = "FAQ";
        String expectedResulSubMenu2 = "How to start";
        String expectedResultSubMenu3 = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportMenu = driver.findElement(By.xpath("//div[@id = 'support-dropdown']"));

        supportMenu.click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElements(By.xpath("//ul [@id ='support-dropdown-menu']/li")).size(),3);

        String actualResultSubmenu1 = driver.findElement(By.xpath("//a [@href ='/faq']")).getText();
        String actualResultSubmenu2 = driver.findElement(By.xpath("//a [@href ='/appid']")).getText();
        String actualResultTextSubmenu3 = driver.findElement(
                By.xpath("//a [@href ='https://home.openweathermap.org/questions']")).getText();

        Assert.assertEquals(actualResultSubmenu1, expectedResultSubMenu1);
        Assert.assertEquals(actualResultSubmenu2, expectedResulSubMenu2);
        Assert.assertEquals(actualResultTextSubmenu3, expectedResultSubMenu3);

        driver.quit();
    }

    /* TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testAskAQuestionInSupportMenuErrorShownWhenClickSubmitWithoutСonfirmingCAPTCHA() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Documents\\Chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
//        String subMenu = "Ask a question";
        String eMail = "google@gmail.com";
        String subject = "Other";
        String message = "Test message";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportMenu = driver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        supportMenu.click();
        Thread.sleep(5000);

        WebElement subMenuAskAQuestion = driver.findElement(By.xpath("//a[@href='https://home.openweathermap.org/questions']"));
        subMenuAskAQuestion.click();

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        WebElement eMailField = driver.findElement(By.xpath("//input[@class='form-control string email required']"));
        eMailField.click();
        eMailField.sendKeys(eMail);

        Thread.sleep(5000);

        WebElement subjectField = driver.findElement(By.xpath("//select[@class='form-control select required']"));
        subjectField.click();
        subjectField.sendKeys(subject);

        Thread.sleep(5000);

        WebElement messageField = driver.findElement(By.xpath("//textarea[@id='question_form_message']"));
        messageField.click();
        messageField.sendKeys(message);

        Thread.sleep(5000);

        WebElement submitButton = driver.findElement(By.xpath("//input[@value= 'Submit']"));
        submitButton.click();

        Thread.sleep(5000);

        WebElement reCaptchaField = driver.findElement(By.xpath("//div[@class='help-block']"));
        String actualResult = reCaptchaField.getText();


        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }





}