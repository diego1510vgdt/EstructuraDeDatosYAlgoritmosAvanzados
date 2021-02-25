package byo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class Arreglo{
    //Atributos de registro
    //Atributo para la cantidad de numeros aleatorios a generar
    int cantidad;
    //El valor mínimo del rango
    int min;
    //El valor máximo del rango
    int max;
    //Creación de lista dinámica
    List <Integer> m;
    //Variable para generar números aleatorios
    int r;
    
    //Constructor, se inicializa con cantidad, min, max
    public Arreglo(int a, int b, int c) {
        this.cantidad = a;
        this.min = b;
        this.max = c;
        m = new ArrayList<>();
        this.r = 0;
    }
    
    //Funciones que regresan los atributos (cantidad, minimo, máximo) del objeto arreglo
    public int getCantidad() {
        return cantidad;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    
    //Funciones que asignan valores a atributos (cantidad, minimo, máximo) del objeto arreglo 
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }
    
    //Función que asigna valores aleatorios al arreglo dependiendo de la entrada de: cantidad, minimo y máximo
    public void creaArreglo(){
        //Ciclo de llenado
        for(int i=0;i<cantidad;i++){
            //Asigna un valor random en el rango dado a r
            r = (int)(Math.random()*(max-min)+min);
            //Se agrega el elemento r a la lista
            m.add(r);
        }
    }
    
    //Función que elimina todos los elementos de la lista en el objeto arreglo
    public void destruye(){
        //limpiar el objeto
        m.clear();
    }
    
    //Funcion que recorre la lista escribiendo un elemento-un enter-un elemento etc.
    public void escribe(){
        JOptionPane.showMessageDialog(null, Arrays.toString(m.toArray()) + "\n" + "Size = " + m.size());
    }
    
    //Ordenamiento por el método burbuja
    public void ordenaBurbuja(){
        //Variable para cambiar de lugar los elementos de la lista
        int k;
        //Elementos para ciclar la lista
        int i, j;
        
        //Cuclo externo
        for(i=0;i<m.size()-1;i++){
            //Ciclo interno
            for(j=0;j<m.size()-1;j++){
                //Condición a cumplir para ordenar
                if(m.get(j) > m.get(j+1)){
                    k=m.get(j);
                    m.set(j, m.get(j+1));
                    m.set(j+1, k);
                }
            }
        }
    }
    
    //Ordenamiento por el método burbuja
    public void ordenaBurbujaBandera(){
        //Elementos para ciclar la lista
        int i,j;
        
        //Variable para cambiar de lugar los elementos de la lista
        int temp;

        //Elemento para finalizar el ordenamiento si es que ya
        //se terminó de ordenar y "debería" recorrer todo el ciclo
        boolean bandera;
        
        //Valores iniciales
        i=1;
        bandera=false;
        
        //Coclo externo
        while(i<m.size() && !bandera){
            j=m.size()-1;
            bandera=true;
            while(i<j){
                if(m.get(j) > m.get(j-1)){
                    temp=m.get(j);
                    m.set(j, m.get(j-1));
                    m.set(j-1, temp);
                    bandera=false;
                }
                j--;
            }
            i++;
        }
        
    }
    
    //Ordenamiento por el método sacudida
    public void ordenaSacudida(){
        int i, izq,dcha,k;
        int temp;
        izq=0;
        //k=m.size()-1;
        dcha=m.size()-1;
        do{
            i=dcha;
            while(izq<i){
                if(m.get(i-1) > m.get(i)){
                    temp=m.get(i-1);
                    m.set(i-1, m.get(i));
                    m.set(i, temp);
                }
                i--;
            }
            izq++;
            i=izq;
            while(i<=dcha){
                if(m.get(i-1) > m.get(i)){
                    temp=m.get(i-1);
                    m.set(i-1, m.get(i));
                    m.set(i, temp);
                }
                i++;
            }
            dcha--;
        }while(izq<=dcha);
    }
    
    //Ordenamiento por el método quicksort
    public void ordenaQuicksort(int izq, int dcha){
        
        int i=izq,j=dcha;
        int pivote, temp;
        int div = (izq+dcha)/2;
        
        pivote=m.get(div);
        
        while(i<j){
            while(m.get(i) < pivote){
                i++;
            }
            while(pivote < m.get(j)){
                j--;
            }
            if(i<j){
                temp=m.get(i);
                m.set(i, m.get(j));
                m.set(j, temp);
                if(i<dcha){
                    i++;
                }
                if(izq<j){
                    j--;
                }
            }
            
        }
        if((i==j) && (i==div)){
            i++;
            j--;
        }
        if(izq<j)
            ordenaQuicksort(izq,j);
        if(i<dcha)
            ordenaQuicksort(i,dcha);
    }
    
    //Búsqueda por método secuencial
    public int busquedaSecuencial(int num){
        int h = -1;
        for(int i=0;i<m.size();i++){
            if(m.get(i).equals(num)){
                h=i;
            }
        }
        return h;
    }
    
    //Búsqueda por método secuencial con centinela
    public int busquedaSecuencialCentinela(int num){
        int h = -1;
        int i;
        i=0;
        while(m.get(i)<num && i<m.size()){
            i++;
        }
        if(m.get(i)==num && i<m.size()){
            h=i;
        }
        return h;
    }
    
    //Búsqueda por método binario
    public int busquedaBinaria(int num){
        int posicion = -1, arriba = 0, abajo = m.size()-1, medio;
        boolean encontrado=false;
        
        while(!encontrado && arriba<abajo){
            medio = (arriba+abajo)/2;
            if(m.get(medio)==num){
                posicion=medio;
                encontrado=true;
            }
            else{
                if(m.get(medio)>num){
                    abajo=medio-1;
                }
                else{
                    arriba=medio+1;
                }
            }
        }
        return posicion;
    }
    
    //Búsqueda por método interpolación
    public int busquedaInterpolacion(int num){
        int izq,der,presunto;
        izq=1;
        der=m.size()-1;
        while(m.get(der) >= num && m.get(izq) < num){
            presunto=(num-m.get(izq))/(m.get(der)-m.get(izq))*(der-izq)+izq;
            if(num>m.get(presunto)){
                izq=presunto+1;
            }
            else{
                if(num<m.get(presunto)){
                    der=presunto-1;
                }
                else{
                    izq=presunto;
                }
            }
        }
        if(m.get(izq)==num){
            return izq;
        }
        else{
            return -1;
        }
    }
    
    //
    public long calculaPromedio(List <Long> tempo){
        long promedio, suma=0;
        
        for(int i=0;i<tempo.size();i++){   
            suma = suma + tempo.get(i);
        }
        promedio=suma/20;
        
        return promedio;
    }
    
    //
    public void escribeTiempos(List <Long> tempo){
        
        for(int i=0;i<20;i++){  
            System.out.print("\n");
            System.out.print(tempo.get(i));
        }
        System.out.print("\n");
    }
    
    
    
}