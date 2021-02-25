package ProblemaDelCambio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author diego
 */

public class MainCambio {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        //Vector que regresa el cambio desglozado en monedas
        List <Moneda> cambio = new ArrayList<>();
        //Variable en la que se ingresa la cantidad de dinero a dar cambio
        double cantidad;
        
        int i;
        
        SistemaMonetario s = new SistemaMonetario();
        s.genera();
        i=0;
        while(i<s.denominaciones.size()){
            System.out.print("Valor de la moneda: " + s.denominaciones.get(i).valor +
                    "\tCantidad de monedas/Billetes disponibles: " + s.denominaciones.get(i).cantidad + "\n");
            i++;
        }
        
        System.out.print("Inserta la cantidad de dinero (debe se menor a " + s.total + "): ");
        cantidad = entrada.nextDouble();
        if(cantidad>s.total){
            System.out.print("La cantidad de dinero debe se menor a " + s.total);
        }
        else{
            s.pega(cambio);
            i=0;
            while(i<s.denominaciones.size()){
                System.out.print("Valor de la moneda: " + s.denominaciones.get(i).valor +
                        "\tCantidad de monedas/Billetes disponibles: " + s.denominaciones.get(i).cantidad + "\n");
                i++;
            }
            System.out.print(s.total + "\n");
        
            s.Cambio(cantidad, cambio);
        
            i=0;
            while(i<cambio.size()){
                System.out.print("Valor de la moneda: " + cambio.get(i).valor +
                        "\tCantidad de monedas/Billetes disponibles: " + cambio.get(i).cantidad + "\n");
                i++;
            }
        
            i=0;
            while(i<s.denominaciones.size()){
                System.out.print("Valor de la moneda: " + s.denominaciones.get(i).valor +
                        "\tCantidad de monedas/Billetes disponibles: " + s.denominaciones.get(i).cantidad + "\n");
                i++;
            }
        
            System.out.print(s.total + "\n");
           }
    }
    
}
