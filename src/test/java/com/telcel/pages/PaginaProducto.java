package com.telcel.pages;

import com.telcel.utils.Celular;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaProducto {
    public WebDriver driver;
    public WebDriverWait wait;

    public PaginaProducto(WebDriver we) {
        this.driver = we;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css=".ecommerce-ficha-tecnica-opciones-compra-titulo #ecommerce-ficha-tecnica-modelo")
    WebElement marca_ModeloCelular; //

    @FindBy(css=".ecommerce-ficha-tecnica-opciones-compra-titulo h1#ecommerce-ficha-tecnica-nombre")
    WebElement nombreCelular; //

    @FindBy(css=".ecommerce-ficha-tecnica-precio-pagos.ng-scope #ecommerce-ficha-tecnica-precio-obj")
    WebElement precioFinal; //

    @FindBy(css="[ng-repeat='cap in capacidades'] .ecommerce-ficha-tecnica-opciones-compra-capacidad")
    WebElement capacidadCelular; //

    public void verificarPagina() {
        wait.until(ExpectedConditions.visibilityOf(marca_ModeloCelular));
        wait.until(ExpectedConditions.visibilityOf(nombreCelular));
        wait.until(ExpectedConditions.visibilityOf(precioFinal));
        wait.until(ExpectedConditions.visibilityOf(capacidadCelular));

    }

    public void verificarInformacionCelular(Celular cel) {
        String mm = marca_ModeloCelular.getText(); //guarda el elemento en una variable
        // compara los datos de los celulares
        if (cel.getMarcaModelo().equals(mm)) {
            System.out.println("Marca y modelo son correctos");
        } else
        {
            System.out.println("Marca y modelo son distintos");
        }


        String nombreEquipo = nombreCelular.getText(); //guarda el elemento en una variable
        if (cel.getNombre().equals(nombreEquipo)){
            System.out.println("El nombre del equipo es correcto");
        }
        else
        {
            System.out.println("El nombre del equipo no es correcto");
        }



        String precioEquipo = precioFinal.getText(); //guarda el elemento en una variable
        precioEquipo = precioEquipo.replace(",", ""); //reemplaza la coma del precio por un nada
        precioEquipo = precioEquipo.replace("$", ""); //reemplaza el $ del precio por un nada
        double pe = Double.parseDouble(precioEquipo); //convierte el precio a double y lo guarda en una variable
        if (cel.getPrecio() == pe)
        {
            System.out.println("El precio es correcto");
        }
        else
        {
            System.out.println("El precio no es correcto");
        }



        String capacidadEquipo = capacidadCelular.getText();// guarda el elemento en una variable
        String[] datos = capacidadEquipo.split(" "); //separa la capacidad por el espacio y lo guarda en un string
        String capacidadString = datos[0]; //la variable guarda los datos de la posicion cero del arreglo
        int numGigas = Integer.parseInt(capacidadString); //convierte el valor a int y lo guarda en una variable
        if (cel.getCapacidadGb() == numGigas)
        {
            System.out.println("La capacidad es correcta");
        }
        else
        {
            System.out.println("La capacidad no es correcta");
        }

    }
}