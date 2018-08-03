package Tests;

import Helper.DriverClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import Helper.Util;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SignUp {
    /**
     * Yiğithan KADIOĞLU
     */

    DriverClass driverClass = new DriverClass();
    Util utilclass = new Util();
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = driverClass.setUpDriver();
    }

    @Test
    @Parameters({"email","password"})
    public void Login(String email,String password) throws InterruptedException {
        String baseUrl = "https://www.apsiyon.com/";
        String expectedTitle = "Apsiyon - Site Yönetim Uygulaması, Site Yönetim Programı";
        String actualTitle = "";

        //Direct url address
        System.out.println("Loading url...");
        driver.get(baseUrl);

        utilclass.Maximize(driver);

        System.out.println("Waiting...");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"logo svg-logo\"]")));

        actualTitle = driver.getTitle();

        //Verifying with using title of website
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
        //Clicking SignIn button
        System.out.println("Clicking SignIn button");
        driver.findElement(By.cssSelector("[class=\"login-button primary-action\"]")).click();
        driver.findElement(By.cssSelector("[href=\"/yonetici-girisi\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"main-sections-inner section-header\"]")));
        String SignInPageHeader = driver.findElement(By.cssSelector("[class=\"main-sections-inner section-header\"]")).getText();
        Assert.assertEquals("Yönetici Girişi",SignInPageHeader);
        //Entering email and password
        System.out.println("Entering email and password");
        driver.findElement(By.cssSelector("[name=\"mail\"]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=\"pass\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("[id=\"loginsubmit\"]")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[alt=\"Apsiyon Apsis\"]")));
        String ApsisPage = driver.findElement(By.cssSelector("[class=\"profile-name\"]")).getText();
        Assert.assertEquals("Test User",ApsisPage);

        TimeUnit.SECONDS.sleep(10);
        //Clicking Kişiler
        System.out.println("Clicking Kişiler");
        driver.findElement(By.cssSelector("[id=\"kisiler\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"title ng-star-inserted\"]")));
        String UsersPage = driver.findElements(By.cssSelector("[class=\"title ng-star-inserted\"]")).get(0).getText();
        Assert.assertEquals("Kişiler",UsersPage);

        //Clicking Yeni button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"table-button mat-raised-button mat-primary ng-star-inserted\"]")));
        System.out.println("Clicking Yeni button");
        driver.findElement(By.cssSelector("[class=\"table-button mat-raised-button mat-primary ng-star-inserted\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"form-title ng-star-inserted\"]")));
        String AddUserPopUp = driver.findElement(By.cssSelector("[class=\"form-title ng-star-inserted\"]")).getText();
        Assert.assertEquals("Kişi Ekle",AddUserPopUp);

        //Filling given form
        System.out.println("Filling given form");
        driver.findElement(By.cssSelector("[placeholder=\"Adı Soyadı\"]")).sendKeys("Yiğithan Kadıoğlu");
        driver.findElement(By.cssSelector("[placeholder=\"TC kimlik no\"]")).sendKeys("11111111111");
        driver.findElement(By.cssSelector("[placeholder=\"Telefon numarası (Varsayılan)\"]")).sendKeys("5301112233");
        driver.findElement(By.cssSelector("[placeholder=\"Telefon numarası (İkincil)\"]")).sendKeys("5301112233");
        driver.findElement(By.cssSelector("[placeholder=\"E-Posta (Varsayılan)\"]")).sendKeys("kadiogluyigithan@gmail.com");
        driver.findElement(By.cssSelector("[placeholder=\"E-Posta (İkincil)\"]")).sendKeys("kadiogluyigithan@gmail.com");
        driver.findElement(By.cssSelector("[placeholder=\"Adres\"]")).sendKeys("Apsiyon-Maltepe");
        driver.findElement(By.cssSelector("[placeholder=\"Araç Plakası\"]")).sendKeys("34YK34");

        WebElement Element = driver.findElement(By.cssSelector("[class=\"mat-checkbox-label\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);

        driver.findElement(By.cssSelector("[placeholder=\"Meslek\"]")).sendKeys("SoftwareEngineer");

        //driver.findElement(By.cssSelector("[class=\"mat-select-placeholder ng-tns-c34-76 ng-star-inserted\"]")).click();
        //driver.findElements(By.cssSelector("[class=\"mat-option ng-star-inserted\"]")).get(4).click();
        driver.findElement(By.cssSelector("[class=\"mat-radio-outer-circle\"]")).click();
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
        TimeUnit.SECONDS.sleep(10);

        //Verification to login process
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"ng-star-inserted\"]")));
        String Character = driver.findElement(By.cssSelector("[class=\"page-info\"]")).getText().substring(7,8);
        int ElementNumber = Integer.parseInt(Character);
        String NewUserCheck = driver.findElements(By.cssSelector("[class=\"mat-cell cdk-column-fullName mat-column-fullName ng-star-inserted\"]")).get(ElementNumber).getText();
        System.out.println(NewUserCheck);
        Assert.assertEquals("Yiğithan Kadıoğlu",NewUserCheck);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
