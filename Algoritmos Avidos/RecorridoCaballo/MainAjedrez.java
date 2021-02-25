package RecorridoCaballo;

import java.util.Scanner;
/**
 *
 * @author diego
 */
public class MainAjedrez {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int tamaño,x,y;
        boolean b;
        Caballo c = new Caballo();
        Caballo a = new Caballo();
        System.out.print("Inserta el tamaño del tablero: ");
        tamaño = entrada.nextInt();
        int t[][]=new int[tamaño][tamaño];
        System.out.print("Inserta x: ");
        x=entrada.nextInt();
        a.setX(x);
        System.out.print("Inserta y: ");
        y=entrada.nextInt();
        a.setY(y);
        b=caballo(t,a);
        if(b){
            System.out.print("Camino posible en: " + x + "," + y + "\n");
            imprime(t);
            System.out.print("\n\n");
        }
        else{
            System.out.print("No tiene solución\n");
        }
    }
    
    private static void inicia_cero(int t[][]){
        int i=0,j;
        while(i<t.length){
            j=0;
            while(j<t.length){
                t[i][j]=0;
                j++;
            }
            i++;
        }
    }
    
    private static void imprime(int t[][]){
        int i=0,j;
        while(i<t.length){
            j=0;
            while(j<t.length){
                System.out.print("tabla[" + i+ "][" + j + "]: " + t[i][j] + "\t");
                j++;
            }
            System.out.print("\n");
            i++;
        }
    }
    
    private static boolean salto(int t[][],int i, Caballo a, Caballo c){
        switch(i){
            case 1:
                c.x=a.x-2;
                c.y=a.y+1;
                break;
            case 2:
                c.x=a.x-1;
                c.y=a.y+2;
                break;
            case 3:
                c.x=a.x+1;
                c.y=a.y+2;
                break;
            case 4:
                c.x=a.x+2;
                c.y=a.y+1;
                break;
            case 5:
                c.x=a.x+2;
                c.y=a.y-1;
                break;
            case 6:
                c.x=a.x+1;
                c.y=a.y-2;
                break;
            case 7:
                c.x=a.x-1;
                c.y=a.y-2;
                break;
            case 8:
                c.x=a.x-2; 
                c.y=a.y-1;
                break;
        }
       return (0<=c.x) && (c.x<t.length) && (0<=c.y) && (c.y<t.length)&&(t[c.x][c.y]==0);
    }
    
    private static int cuenta(int t[][], Caballo a){
        int acc=0,i=1;
        Caballo c = new Caballo();
        while(i<=8){
            if(salto(t,i,a,c))
                acc++;
            i++;
        }
        return acc;
    }
    
    private static boolean nuevo_mov(int t[][], Caballo a){
        int accesibles, minaccesibles=9;
        int i=1,solx=a.x,soly=a.y;
        boolean b;
        Caballo c = new Caballo();
        while(i<=8){
            b=salto(t,i,a,c);
            if(salto(t,i,a,c)){
                accesibles=cuenta(t,c);
                if(0<=accesibles && accesibles<minaccesibles){
                    minaccesibles=accesibles;
                    solx=c.x;
                    soly=c.y;
                }
            }
            i++;
        }
        a.setX(solx);
        a.setY(soly);
        return(minaccesibles<9);
    }
    
    private static boolean caballo(int t[][], Caballo a){
        int i,n;
        inicia_cero(t);
        imprime(t);
        i=1;
        n = t.length*t.length;
        while(i<=n){
            t[a.x][a.y]=i;
            if(!nuevo_mov(t,a) && i<n-1)
                return false;
            i++;
        }
        return true;
    }
    
}

