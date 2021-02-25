package tablas.hash;

import java.util.Scanner;

public class TablasHash {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Tabla tabla1;
        int tam=0,itera;
        int i,j;
        int r, max=100000, min=1;
        int busca,indice=0;
        int op;
        
        System.out.print("Inserta el tamaño del conjunto de datos a generar: ");
        tam= entrada.nextInt();
        itera=primo(tam);
        tabla1=new Tabla(itera);
        
        /*i=0;
        while(i<tam){
            r = (int)(Math.random()*(max-min)+min);
            tabla1.insertaArray(r);
            i++;
        }
        i=0;
        while(i<tabla1.array.size()){
            System.out.print("array[" + i + "]= " + tabla1.array.get(i) + "\n");
            i++;
        }
        System.out.print("\nNumero de colisiones: " + tabla1.count + "\n");
        System.out.print("\n");*/
        
        i=0;
        while(i<tam){
            r = (int)(Math.random()*(max-min)+min);
            tabla1.insertaLista(r);
            i++;
        }
        
        i=0;
        while(i<tabla1.lista.size()){
            j=0;
            if(!tabla1.lista.get(i).isEmpty())
                System.out.print("\n");
            while(j<tabla1.lista.get(i).size()){
                if(!tabla1.lista.get(i).isEmpty())
                    System.out.print("lista[" + i + "]= " + tabla1.lista.get(i).get(j).dato + "\t");
                j++;
            }
        i++;
        }
        System.out.print("\n");
        
        System.out.print("\nNumero (n) a buscar: ");
        busca= entrada.nextInt();
        indice=tabla1.hash1(busca);
        busca=tabla1.buscaLista(busca);
        
        if(busca!=-1){
            System.out.print("\nEncontrado!!!!!\nPosicion en Lista(Listas): " + indice + "\n");
            System.out.print("\nEncontrado!!!!!\nPosicion en Lista(Cajones): " + busca + "\n");
        }
        else{
            System.out.print("\nNo se ecnontró el numero\n");
        }
        
        /*do{
            op=menu();
            switch(op){
                case 1:
                    i=0;
                    while(i<tam){
                        r = (int)(Math.random()*(max-min)+min);
                        tabla1.insertaArray(r);
                        i++;
                    }
                    i=0;
                    while(i<tabla1.array.size()){
                        System.out.print("array[" + i + "]= " + tabla1.array.get(i) + "\n");
                        i++;
                    }
                    System.out.print("\nNumero de colisiones: " + tabla1.count + "\n");
                    System.out.print("\n");
                    break;
                case 2:
                    i=0;
                    while(i<tam){
                        r = (int)(Math.random()*(max-min)+min);
                        tabla1.insertaLista(r);
                        i++;
                    }
                    i=0;
                    while(i<tabla1.lista.size()){
                        j=0;
                        if(!tabla1.lista.get(i).isEmpty())
                            System.out.print("\n");
                        while(j<tabla1.lista.get(i).size()){
                            if(!tabla1.lista.get(i).isEmpty())
                                System.out.print("lista[" + i + "]= " + tabla1.lista.get(i).get(j).dato + "\t");
                            j++;
                        }
                        i++;
                    }
                    System.out.print("\n");
                    break;
                case 3:
                    System.out.print("\nNumero (n) a buscar: ");
                    busca= entrada.nextInt();
                    indice=tabla1.hash1(busca);
                    busca=tabla1.buscaLista(busca);
        
                    if(busca!=-1){
                        System.out.print("\nEncontrado!!!!!\nPosicion en Lista(Listas): " + indice + "\n");
                        System.out.print("\nEncontrado!!!!!\nPosicion en Lista(Cajones): " + busca + "\n");
                    }
                    else{
                        System.out.print("\nNo se ecnontró el numero\n");
                    }
                    break;
            }
        }while(op!=4);*/
    }
    
    public static int primo(int numero){
        int cant=numero*9/5;
        numero=numero*2;
        boolean esPrimo;
        int r=0;
        
        for (int i = cant; i <= numero; i++) {
            esPrimo=true;
            for (int j=2; j<=Math.sqrt(i) && esPrimo;j++) {
                if (i%j == 0)
                    esPrimo=false;   
            }
            if (esPrimo){
                r=i;
            }
        }
        return r;
    }
    
    public static int menu(){
        Scanner input = new Scanner( System.in );
        int opcion;
        
        System.out.print( "\tMenu\n" );
        System.out.print( "1) Crear arreglo\n" );
        System.out.print( "2) Crear lista de listas\n" );
        System.out.print( "3) Buscar en lista de listas\n" );
        /*System.out.print( "\t4) Ordenar por quicksort\n" );
        System.out.print( "\t5) Busqueda secuencial\n" );
        System.out.print( "\t6) Busqueda secuencial con centinela\n" );
        System.out.print( "\t7) Busqueda binaria\n" );
        System.out.print( "\t8) Busqueda interpolaciÃ³n\n" );
        System.out.print( "\t9) Menu de pruebas\n" );
        System.out.print( "\t10) Escribir\n" );*/
        System.out.print( "4) SALIR\n" );
        System.out.print( "Opcion: " );
        opcion = input.nextInt();
        
        return opcion;
    }
}