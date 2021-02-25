package ProblemaDelCambio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class SistemaMonetario {
    //Atributos
    /*Arreglo con las distintas denominaciones
    de moneda en el sistema*/
    List <Moneda> denominaciones = new ArrayList<>();
    //La cantidad de dinero total en el sistema
    double total;
    //
    List <Double> d = new ArrayList<>();
    
    /*Cosnrtuctor
    Asigna las monedas y cantidades que se le asignen
    de forma previa al sistema*/
    public SistemaMonetario(){
        total=0;
        deno();
    }
    
    private void deno(){
        d.add(1000.0);
        d.add(500.0);
        d.add(200.0);
        d.add(100.0);
        d.add(50.0);
        d.add(20.0);
        d.add(10.0);
        d.add(5.0);
        d.add(2.0);
        d.add(1.0);
        d.add(0.5);
        d.add(0.2);
        d.add(0.1);
    }
    
    public void pega(List <Moneda> cambio){
        int i=0;
        while(i<denominaciones.size()){
            Moneda m = new Moneda(d.get(i));
            cambio.add(m);
            i++;
        }
    }
    
    public void genera(){
        int r,min=0,max=501;
        
        for (int i=0;i<13;i++) {
            Moneda m = new Moneda(d.get(i));
            denominaciones.add(m);
            r = (int)(Math.random()*(max-min)+min);
            denominaciones.get(i).setCantidad(r);
            total = total + (denominaciones.get(i).cantidad * denominaciones.get(i).valor);
        }
    }
    
    public void Cambio(double cant,List <Moneda> cambio){
        int j,i;
        j=0;
        while(j<denominaciones.size()){
            while(denominaciones.get(j).valor<=cant){
                if(denominaciones.get(j).cantidad>0){
                    cant=cant-denominaciones.get(j).valor;
                    denominaciones.get(j).setCantidad(denominaciones.get(j).cantidad-1);
                    cambio.get(j).setCantidad(cambio.get(j).cantidad+1);
                    total=total-denominaciones.get(j).valor;
                }
                else{
                    j++;
                    cant=cant-denominaciones.get(j).valor;
                    denominaciones.get(j).setCantidad(denominaciones.get(j).cantidad-1);
                    cambio.get(j).setCantidad(cambio.get(j).cantidad+1);
                    total=total-denominaciones.get(j).valor;
                }
            }
            j++;
        }
        
    }
}
