package Test2;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CompletarCampos {

    private WebDriver driver;
    private JavascriptExecutor js;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        driver.get("https://www.saucedemo.com/");
    }

    private void pausa(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void miPrimeraPrueba() {

        pausa(2);

        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        pausa(2);

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        pausa(2);

        WebElement botonLogin = driver.findElement(By.id("login-button"));
        js.executeScript("arguments[0].scrollIntoView(true);", botonLogin);

        pausa(2);

        js.executeScript("arguments[0].click();", botonLogin);

        pausa(3);

        WebElement inventario = driver.findElement(By.id("inventory_container"));
        assertTrue(inventario.getText().contains("Sauce Labs Backpack"));

        System.out.println("Test ejecutado correctamente.");
    }

    @After
    public void tearDown() {
        pausa(5);
        if (driver != null) {
            driver.quit();
        }
    }
}
