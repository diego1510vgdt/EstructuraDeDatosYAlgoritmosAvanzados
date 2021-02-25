import java.util.Scanner;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int n,opcion;
        
        Grafo grafo = new Grafo();
        grafo.leer();
        System.out.print("\n\n");
        
        do{
            opcion=menu();
            switch(opcion){
                case 1:
                    System.out.print("\n");
                    System.out.print("Insterta el nodo inicial: ");
                    n= entrada.nextInt();
                    System.out.print("Caminos de Breadth First Search:\n");
                    grafo.breadthfirstsearch(n);
                    break;
                case 2:
                    System.out.print("\n");
                    System.out.print("Insterta el nodo inicial: ");
                    n= entrada.nextInt();
                    System.out.print("Caminos de Breadth First Search:\n");
                    grafo.breadthfirstsearch(n);
                    break;
                case 3:
                    System.out.print("\n");
                    System.out.print("Árboles de expansión mínima (Kruscal)\n");
                    grafo.Kruskal();
                    break;
                case 4:
                    System.out.print("\n");
                    System.out.print("Insterta el nodo inicial: ");
                    n= entrada.nextInt();
                    grafo.Prim(n);
                    break;
            }
            System.out.print("\n");
        }while(opcion!=5);
    }
    
    //Menú para selección de algoritmos
    public static int menu(){
        Scanner entrada = new Scanner(System.in);
        int o;
        
        System.out.print("\tMenú principal de algoritmos de grafos\n");
        System.out.print(" 1) Algoritmo de Breadth First Search\n");
        System.out.print(" 2) Algoritmo de Dijkstra\n");
        System.out.print(" 3) Algoritmo de Kruskal\n");
        System.out.print(" 4) Algoritmo de Prim\n");
        System.out.print(" 5) Salir\n");
        System.out.print("Insterta tu selección: ");
        o= entrada.nextInt();
        
        return o;
    }
}
