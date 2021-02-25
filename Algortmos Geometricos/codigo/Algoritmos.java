package algortmos.geometricos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author diego
 */
public class Algoritmos{
    //Atributos
    //Lista de Puntos
    public List <Punto> lista = new ArrayList<>();
    //
    public Stack <Integer> pila = new Stack<>();
    
    public Stack <Punto> pilaPuntos = new Stack<>();
    
    //Tamaño del conjunto de datos
    public int tamaño;
    
    //Métodos
    //Constructor
    public Algoritmos(int t){ this.tamaño = t; }
    
    //Inician las funciones para algoritmo de Graham
    //Ordena los elementos con el valor en eje y
    public void menorY(int izq, int dcha){
        Punto punto1 = new Punto(0,0);
        //Variables de iteración
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        int pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        pivote=lista.get(div).y;
        
        while(i<j){
            while(lista.get(i).y<pivote){
                i++;
            }
            while(lista.get(j).y>pivote){
                j--;
            }
            if(i<j){
                punto1.setX(lista.get(i).x);
                punto1.setY(lista.get(i).y);
                lista.get(i).setX(lista.get(j).x);
                lista.get(i).setY(lista.get(j).y);
                lista.get(j).setX(punto1.x);
                lista.get(j).setY(punto1.y);
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
            menorY(izq,j);
        if(i<dcha)
            menorY(i,dcha);
    }
    
    //
    public void calculaAngulo(){
        lista.get(0).angulo=0;
        double m,teta,xd,yd;
        int i=1;
        while(i<lista.size()){
            xd=lista.get(i).x-lista.get(0).x;
            yd=lista.get(i).y-lista.get(0).y;
            m=yd/xd;
            teta=Math.atan(m);
            teta=Math.toDegrees(teta);
            if(teta<0){
                teta=/*Math.PI*/180+teta;
            }
            lista.get(i).setAngulo(teta);
            i++;
        }
    }
    
    //
    public void ordenaAngularmente(int izq, int dcha){
        Punto punto1 = new Punto(0,0);
        //Variables de iteración
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        double pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        pivote = lista.get(div).angulo;
        
        while(i<j){
            while(lista.get(i).angulo < pivote){
                i++;
            }
            while(lista.get(j).angulo > pivote){
                j--;
            }
            if(i<j){
                punto1.setX(lista.get(i).x);
                punto1.setY(lista.get(i).y);
                punto1.setAngulo(lista.get(i).angulo);
                lista.get(i).setX(lista.get(j).x);
                lista.get(i).setY(lista.get(j).y);
                lista.get(i).setAngulo(lista.get(j).angulo);
                lista.get(j).setX(punto1.x);
                lista.get(j).setY(punto1.y);
                lista.get(j).setAngulo(punto1.angulo);
                
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
            ordenaAngularmente(izq,j);
        if(i<dcha)
            ordenaAngularmente(i,dcha);
    }
    
    //
    public double determinante(Punto a,Punto b,Punto c){//También se usa en quickhull
        double v;
        v=(b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
        return v;
    }
    
    //Algoritmo de Graham
    public void Graham(){
        int izq,dcha,i,j,num;
        int k;
        double pos;
        
        izq=0;dcha=lista.size()-1;
        menorY(izq,dcha);
        
        calculaAngulo();
        izq=1;dcha=lista.size()-1;
        ordenaAngularmente(izq,dcha);
        
        i=0;
        while(i<lista.size()){
            lista.get(i).setN(i);
            i++;
        }
        
        pila.push(lista.get(0).n);
        pila.push(lista.get(1).n);
        pila.push(lista.get(2).n);
        
        i=3;
        while(i<lista.size()){
            num = pila.pop();
            k=pila.pop();
            pila.push(k);
            pos = determinante(lista.get(k),lista.get(num),lista.get(i));
            if(0<=pos){
                pila.push(num);
                pila.push(lista.get(i).n);
                i++;
            }
        }
    }
    //Terminan las funciones para algoritmo de Graham
    
    //Inician funciones para Quickhull
    //
    public void menorX(int izq, int dcha){
        Punto punto1 = new Punto(0,0);
        //Variables de iteración
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        int pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        pivote=lista.get(div).x;
        
        while(i<j){
            while(lista.get(i).x<pivote){
                i++;
            }
            while(lista.get(j).x>pivote){
                j--;
            }
            if(i<j){
                punto1.setX(lista.get(i).x);
                punto1.setY(lista.get(i).y);
                lista.get(i).setX(lista.get(j).x);
                lista.get(i).setY(lista.get(j).y);
                lista.get(j).setX(punto1.x);
                lista.get(j).setY(punto1.y);
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
            menorX(izq,j);
        if(i<dcha)
            menorX(i,dcha);
    }
    
    //
    public double distanciaPuntoRecta(Punto a,Punto b,Punto c){
        double a1,b1;
        
        a1=b.y-a.y;
        b1=b.x-a.x;
        
        return Math.abs((a1*c.x - b1*c.y + b.x*a.y - b.y*a.x)/Math.sqrt(a1*a1+b1*b1));
    }
    
    
    //
    public void Quickhull(Punto a, Punto b, List<Punto>S){
        Punto c = new Punto(0,0);
        List <Punto> A = new ArrayList<>();
        List <Punto> B = new ArrayList<>();
        int i;
        double pos,distancia, d=0;
        
        if(S.size()!=0){
            i=0;
            while(i<S.size()){
                distancia=distanciaPuntoRecta(a,b,S.get(i));
                if(d<distancia){
                    d=distancia;
                    c.setX(S.get(i).getX());
                    c.setY(S.get(i).getY());
                    c.setN(S.get(i).getN());
                }
                i++;
            }
            
            i=0;
            while(i<S.size()){
                pos = determinante(a,c,S.get(i));
                if(pos<0){
                    A.add(S.get(i));
                }
                i++;
            }
            
            i=0;
            while(i<S.size()){
                pos = determinante(c,b,S.get(i));
                if(pos<0){
                    B.add(S.get(i));
                }
                i++;
            }
            pilaPuntos.add(c);
            Quickhull(a,c,A);
            Quickhull(a,b,B);
        }
    }
    //Terminan funciones para Quickhull
    
    //Funciones Generales
    //
    public void pega(List<Punto>listap){
        lista.clear();
        int i=0;
        while(i<listap.size()){
            lista.add(listap.get(i));
            i++;
        }
    }
    
    //
    public void escribeLitsa(){
        int i=0;
        System.out.print("\n");
        while(i<lista.size()){
            System.out.print(lista.get(i).n + "    " +
                    lista.get(i).x + "    " + lista.get(i).y +
                    "    " + lista.get(i).angulo + "\n");
            i++;
        }
    }
    //
    public void ordenaPila(int izq, int dcha){
        //
        int p;
        //
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        int pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        pivote=pila.get(div);
        
        while(i<j){
            while(pila.get(i)<pivote){
                i++;
            }
            while(pila.get(j)>pivote){
                j--;
            }
            if(i<j){
                p=pila.get(i);
                pila.set(i,pila.get(j));
                pila.set(j,p);
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
            ordenaPila(izq,j);
        if(i<dcha)
            ordenaPila(i,dcha);
    }
}
