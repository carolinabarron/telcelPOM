package com.telcel.pages;

import com.telcel.utils.Celular;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class PaginaResultados {

    public WebDriver driver;
    public WebDriverWait wait;

    public PaginaResultados(WebDriver we) {
        this.driver = we;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".comp-telcel-mosaico-equipos li")
    List<WebElement> listaResultados; //

    @FindBy(css="p.telcel-mosaico-equipos-marca")
    WebElement marca_ModeloCelular; //

    @FindBy( css="p.telcel-mosaico-equipos-nombre-equipo")
    WebElement nombreCelular; //

    @FindBy(css="div.ng-scope > p.telcel-mosaico-equipos-precio")
    WebElement precioFinal; //

    @FindBy(css=".telcel-mosaico-equipos-capacidad-numero")
    WebElement capacidadCelular; //

    //public void verificarPagina() {
     //    }

    public void verificarResultados() {

        wait.until(ExpectedConditions.visibilityOfAllElements(listaResultados));
       if(listaResultados.size()>1) {
            System.out.println("La lista se desplegó correctamente");
           wait.until(ExpectedConditions.visibilityOf(marca_ModeloCelular));
           wait.until(ExpectedConditions.visibilityOf(nombreCelular));
           wait.until(ExpectedConditions.visibilityOf(precioFinal));
           wait.until(ExpectedConditions.visibilityOf(capacidadCelular));
        }
       else{
           System.out.println("No se cargó la lista de celulares");
           System.exit(-1);
       }

    }

    public Celular capturarInformacionCelular(int numCelular) {
        //wait.until(ExpectedConditions.visibilityOf(marca_ModeloCelular));
        String mm = marca_ModeloCelular.getText(); //guarda el elemento en una variable

        //wait.until(ExpectedConditions.visibilityOf(nombreCelular));
        String nombreEquipo = nombreCelular.getText(); //guarda el elemento en una variable


        //wait.until(ExpectedConditions.visibilityOf(precioFinal));
        String precioEquipo = precioFinal.getText(); //guarda el elemento en una variable
        precioEquipo = precioEquipo.replace(",", ""); //reemplaza la coma del precio por un nada
        precioEquipo = precioEquipo.replace("$", ""); //reemplaza el $ del precio por un nada
        double pe = Double.parseDouble(precioEquipo); //convierte el precio a double y lo guarda en una variable


        //wait.until(ExpectedConditions.visibilityOf(capacidadCelular));
        String capacidadEquipo = capacidadCelular.getText();// guarda el elemento en una variable
        String[] datos = capacidadEquipo.split(" "); //separa la capacidad por el espacio y lo guarda en un string
        String capacidadString = datos[0]; //la variable guarda los datos de la posicion cero del arreglo
        int numGigas = Integer.parseInt(capacidadString); //convierte el valor a int y lo guarda en una variable


        return new Celular(mm, nombreEquipo, pe, numGigas); //regresa los valores
    }

    public void seleccionarCelular(int numCelular) {
        WebElement celular = listaResultados.get(numCelular - 1); //nos posicionamos en el elemento marcado en la variable numeroCelular
        celular.click(); //da click y selecciona el celular correcto

    }

}