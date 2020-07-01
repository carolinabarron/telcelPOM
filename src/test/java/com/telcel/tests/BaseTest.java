package com.telcel.tests;

import com.telcel.pages.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landing;
    public PaginaProducto producto;
    public PaginaResultados resultados;
    public ModalSeleccionEstado modalEstado;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        landing = new LandingPage(driver);
        producto = new PaginaProducto(driver);
        resultados = new PaginaResultados(driver);
        modalEstado = new ModalSeleccionEstado(driver);

    }

    @After
    public void after() {
        driver.quit();
    }
}