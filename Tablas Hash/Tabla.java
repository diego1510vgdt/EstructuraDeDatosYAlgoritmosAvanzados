package tablas.hash;

import java.util.ArrayList;
import java.util.List;

public class Tabla {
    //Atributos
    //El tamaño de la lisa o array
    int tamaño;
    //Las incidencias
    int count;
    //Lista de Cajones
    List <List <Cajon>> lista = new ArrayList<>();
    //Lista de enteros
    List <Integer> array = new ArrayList<>();
    
    //Constructor
    public Tabla(int tamaño) {
        this.tamaño = tamaño;
        this.count = 0;
        int i=0;
        while(i<tamaño){
            array.add(0);
            List <Cajon> cajones = new ArrayList<>();
            lista.add(cajones);
            i++;
        }
    }
    
    //Función Hash versión 1
    //Regresa una posición según el módulo del número y el tamaño
    public int hash1(int r){
        int h=r%tamaño;
        return h;
    }
    
    //Insertar en un areglo
    public void insertaArray(int r){
        int h;
        
        //Función Hash
        h=hash1(r);
        
        //
        if(array.get(h)==0){
            array.set(h,r);
        }
        else if(array.get(h)!=r){
            count++;
        }
    }
    
    public void insertaLista(int r){
        int bandera=0;
        int h;
        
        h=hash1(r);
        
        int i=0;
        while(i<lista.get(h).size()){
            if(lista.get(h).get(i).dato==r){
                bandera=1;
            }
            i++;
        }
        if(bandera!=1){
            Cajon cajon = new Cajon(h,r);
            lista.get(h).add(cajon);
        }
    }
    
    public int buscaLista(int n){
        int encontrado=-1,h=hash1(n);
        
        if(!lista.get(h).isEmpty()){
            int i=0;
            while(i<lista.get(h).size()){
                if(n==lista.get(h).get(i).dato)
                    encontrado=i;
                i++;
            }
        }
        
        return encontrado;
    }
    
}
