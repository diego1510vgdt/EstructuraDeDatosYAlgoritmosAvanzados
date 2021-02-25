/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algortmos.geometricos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JPanel;

/**
 *
 * @author diego
 */
public class pintar extends JPanel{
    public List <Punto> lista = new ArrayList<>();
    public Stack <Integer> pila = new Stack<>();
    public Stack <Punto> pilaPuntos = new Stack<>();
    private static final long serialVersionUID = 1L;
    
    public void peg(List<Punto>listap){
        lista.clear();
        int i=0;
        while(i<listap.size()){
            lista.add(listap.get(i));
            i++;
        }
    }
    
    public void pegPila(Stack <Integer> pilap){
        pila.clear();
        int i=0;
        while(i<pilap.size()){
            pila.push(pilap.get(i));
            System.out.print(pilap.get(i) + "\n");
            i++;
        }
    }
    
    public void pegPilaPunto(Stack <Punto> pilap){
        pilaPuntos.clear();
        int i=0;
        while(i<pilap.size()){
            pilaPuntos.push(pilap.get(i));
            i++;
        }
    }
    
    public void menorY(int izq, int dcha){
        Punto punto1 = new Punto(0,0);
        //Variables de iteración
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        int pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        pivote=pilaPuntos.get(div).y;
        
        while(i<j){
            while(pilaPuntos.get(i).y<pivote){
                i++;
            }
            while(pilaPuntos.get(j).y>pivote){
                j--;
            }
            if(i<j){
                punto1.setX(pilaPuntos.get(i).x);
                punto1.setY(pilaPuntos.get(i).y);
                pilaPuntos.get(i).setX(pilaPuntos.get(j).x);
                pilaPuntos.get(i).setY(pilaPuntos.get(j).y);
                pilaPuntos.get(j).setX(punto1.x);
                pilaPuntos.get(j).setY(punto1.y);
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
        pilaPuntos.get(0).angulo=0;
        double m,teta,xd,yd;
        int i=1;
        while(i<pilaPuntos.size()){
            xd=pilaPuntos.get(i).x-pilaPuntos.get(0).x;
            yd=pilaPuntos.get(i).y-pilaPuntos.get(0).y;
            m=yd/xd;
            teta=Math.atan(m);
            teta=Math.toDegrees(teta);
            if(teta<0){
                teta=/*Math.PI*/180+teta;
            }
            pilaPuntos.get(i).setAngulo(teta);
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
        
        pivote = pilaPuntos.get(div).angulo;
        
        while(i<j){
            while(pilaPuntos.get(i).angulo < pivote){
                i++;
            }
            while(pilaPuntos.get(j).angulo > pivote){
                j--;
            }
            if(i<j){
                punto1.setX(pilaPuntos.get(i).x);
                punto1.setY(pilaPuntos.get(i).y);
                punto1.setAngulo(pilaPuntos.get(i).angulo);
                pilaPuntos.get(i).setX(pilaPuntos.get(j).x);
                pilaPuntos.get(i).setY(pilaPuntos.get(j).y);
                pilaPuntos.get(i).setAngulo(pilaPuntos.get(j).angulo);
                pilaPuntos.get(j).setX(punto1.x);
                pilaPuntos.get(j).setY(punto1.y);
                pilaPuntos.get(j).setAngulo(punto1.angulo);
                
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
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int i,j,k=0,k1=0;
        double d,d1=9999999;
        Punto c = new Punto(0,0);
        
        if(!lista.isEmpty()){
            i=0;
            while(i<lista.size()){
                g.setColor(Color.RED);
                g.fillOval(lista.get(i).x,lista.get(i).y, 7, 7);
                i++;
            }
        }
        if(!pila.isEmpty()){
            i=0;j=1;
            while(i<pila.size()){
                g.drawLine(lista.get(pila.get(i)).x,
                        lista.get(pila.get(i)).y,
                        lista.get(pila.get(j)).x,
                        lista.get(pila.get(j)).y);
                i++;j++;
                if(j>=pila.size()){
                    j=0;
                }
            }
            pila.clear();
        }
        
        
        else if(!pilaPuntos.isEmpty()){
            int izq,dcha,num;
            
            izq=0;dcha=pilaPuntos.size()-1;
            menorY(izq,dcha);
            
            calculaAngulo();
            
            izq=0;dcha=pilaPuntos.size()-1;
            ordenaAngularmente(izq,dcha);
            
            i=0;j=1;
            while(i<pilaPuntos.size()){
                g.drawLine(pilaPuntos.get(i).x,
                        pilaPuntos.get(i).y,
                        pilaPuntos.get(j).x,
                        pilaPuntos.get(j).y);
                i++;j++;
                if(j>=pilaPuntos.size()){
                    j=0;
                }
            }
            pilaPuntos.clear();
        }
        
    }
    
    
}
