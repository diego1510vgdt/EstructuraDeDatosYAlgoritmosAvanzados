import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Grafo {
    List <List <Nodo>> lineas = new ArrayList<>();
    int tamaño;
    
    //Función para leer un archivo txt que contiene la conectividad de un grafo
    public void leer(){
        //Variables para iterar y div identifica si es atributo peso o nodo
        int i,j,div;
        
        //Variables para asignar al constructor
        int num=0,num1;
        
        //Ciclo para leer todo el archivo
        try {
            try ( //Selecciona el archivo con la dirección
                    Scanner input = new Scanner(
                            new File("D:\\universidad\\Cuatrimestre 4\\Estructuras "
                                    + "2\\Codigos\\GrafoVersiones\\Grafo\\src\\ejemplo1.txt"))) {
                j=0;
                while (input.hasNextLine()) {
                    //Lees una linea del archivo de texto
                    String line = input.nextLine();
                    //Lista de tipo Nodo en la que se asigna la conectividad de un nodo
                    List <Nodo> nodos = new ArrayList<>();
                    System.out.println(line);
                    if(j==0){
                        //Asigna el numero de listas de tipo nodo
                        tamaño=Integer.parseInt(line);
                        j++;
                    }
                    else{
                        //Separar el texto para evitar los espacios " "
                        StringTokenizer st = new StringTokenizer(line," ");
                        i=0;
                        while(st.hasMoreTokens()){
                            // dividir el texto en números
                            String key = st.nextToken();
                            div=i%2;
                            if(div==0){
                                num=Integer.parseInt(key);
                            }
                            else{
                                num1=Integer.parseInt(key);
                                //Constructor de un nodo
                                Nodo nodo = new Nodo(num,num1);
                                //Agrega el nodo a una Lista de tipo nodo en la Lista general
                                nodos.add(nodo);
                            }
                            
                            i++;
                        }
                        //Se agrega la lista de tipo nodo a una Lista general
                        lineas.add(nodos);
                    }
                }
            }
        }catch (FileNotFoundException ex) {
        }
    }
    
    
    
    ////////////////////Inicia BREADTH FIRST SEARCH///////////////////////////
    //Busca los caminos posibles en un grafo a partir de un nodo con BFS
    public void breadthfirstsearch(int s){
        //Cola para guardar temporalmente los nodos
        LinkedList<Integer> cola = new LinkedList<>();
        //Arreglo con verificadores para saber si un nodo fue visitado
        String[] colores = new String [lineas.size()];
        //Arreglo que guarda el número de arcos que se recorren desde raíz hasta un nodo
        int[] d = new int [lineas.size()];
        //Arreglo de padres
        int[] pi = new int [lineas.size()];
        
        //Ciclo para inicializar los vectores
        for(int i=0;i<lineas.size();i++){
            //Todos en color blanco
            colores[i]="B";
            //Todas las distancias a números grandes
            d[i]=999999999;
            //Todos los padres a NULL
            pi[i]=-1;
        }
        
        //Nodo raíz en Gris o "Posicionado"
        colores[s-1]="G";
        //Distancia a 0
        d[s-1]=0;
        //Padre de raíz a NULL
        pi[s-1]=-1;
        //Añadir nodo a la cola
        cola.offer(s);
        
        while(!cola.isEmpty()){
            //Extraer el nodo de la cola
            int x = (int) cola.poll();
            //Para cada nodo adyacente a el nodo X extraído
            for(int i=0;i<lineas.get(x-1).size();i++){
                //Si el nodo no ha sido visitado
                if( "B".equals(colores[lineas.get(x-1).get(i).getNodo()-1]) ){
                    //Posicionarlo
                    colores[lineas.get(x-1).get(i).getNodo()-1]="G";
                    //Actializa distancia desde X hasta ese nodo
                    d[lineas.get(x-1).get(i).getNodo()-1]=d[x-1]+1;
                    //Asignar el nodo X como padre del nodo
                    pi[lineas.get(x-1).get(i).getNodo()-1]=x;
                    //Añadir a la cola todas las adyaciencias de X
                    cola.offer(lineas.get(x-1).get(i).nodo);
                }
            }
            //Cambiar color de X a Visitado
            colores[x-1]="N";
        }
        
        //Ciclo para integrar los caminos e imprimirlos
        for(int i=1;i<=pi.length;i++){
            recuperaCamino(i,s,pi);
            System.out.print("\n");
        }
        
    }
    ////////////////////Termina BREADTH FIRST SEARCH//////////////////////////
    
    
    
    ////////////////////Inicia DIJKSTRA////////////////////
    //Extrae el nodo con el camino mínimo hacia el nodo seleccionado anteriormente 
    public int extraeMinDijkstra(int[] d ,LinkedList<Nodo> ns, int s, LinkedList<Integer> S,LinkedList<Integer> Q){
        //Variable que regresa la posición a extraer de Q
        int retornar;
        //Variables para insertar nodos en ns
        int num,num1;
        //Validar si se puede añadir un nodo a ns
        int b;
        
        //Agregar los nodos de Q a ns que no están en S
        for(int i=0;i<lineas.get(s-1).size();i++){
            b=buscarQ(Q,lineas.get(s-1).get(i).nodo);
            if(b==1){
                num=lineas.get(s-1).get(i).nodo;
                num1=lineas.get(s-1).get(i).peso + d[s-1];
                Nodo nodo = new Nodo(num,num1);
                ns.offer(nodo);
            } 
        }
        
        int izq=0;
        int dcha=ns.size()-1;
        //Ordenar pila ns por peso
        ordenaQuicksort(ns,s,izq,dcha);
        
        //Se retorna el nodo
        retornar=ns.get(0).nodo;
        //Elimina nodo a retornar
        ns.remove(0);
        
        //Retorna la posición del nodo
        return retornar-1;
    }
    
    //busca los nodos que se encuentran en Q para saber si se puede tomar en cuenta
    public int buscarQ(LinkedList<Integer> Q,int n){
        //Variable para retornar la validación
        int r=0;
        
        //Verifica si el nodo está en Q
        for(int i=0;i<Q.size();i++){
            if(n==Q.get(i)){
                r=1;
            }
        }
        
        return r;
    }
    
    //Ordena la pila que se crea para saber que nodo extraer y agregarlo al árbol
    public void ordenaQuicksort(LinkedList<Nodo> ns, int s, int izq, int dcha){
        //Nodo para poder cambiar los valores de nodos en intercambios
        Nodo nodo1 = new Nodo(0,0);
        //Variables de iteración
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        int pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        //Extrae el valor medio y lo vuelve pivote
        pivote=ns.get(div).peso;
        
        //Ciclo de ordenamiento
        while(i<j){
            while(ns.get(i).peso < pivote){
                i++;
            }
            while(pivote < ns.get(j).peso){
                j--;
            }
            if(i<j){
                nodo1.setNodo(ns.get(i).nodo);
                nodo1.setPeso(ns.get(i).peso);
                ns.set(i, ns.get(j));
                ns.set(j, nodo1);
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
            ordenaQuicksort(ns,s,izq,j);
        if(i<dcha)
            ordenaQuicksort(ns,s,i,dcha);
    }
    
    //Algoritmo que busca los caminos mínimos a partir de un nodo hacia los demás
    public void dijkstra(int s){
        //Cola a la que se agregan todos los nodos menos raíz
        LinkedList<Integer> Q = new LinkedList<>();
        //Cola para verificar si ya se visitó un nodo
        LinkedList<Integer> S = new LinkedList<>();
        //Cola de prioridad para almacenamiento temporal y para ordenar por peso según el nodo a analizar
        LinkedList<Nodo> ns = new LinkedList<>();
        //Arreglo que almacena el peso que toma ir de raíz a un nodo en el grafo
        int[] d = new int [lineas.size()];
        //Arreglo de padres
        int[] pi = new int [lineas.size()];
        
        //Variables de utileria
        int r,k;
        //Posición del nodo raíz
        s--;
        
        //Inicializa los arreglos estáticos y la cola Q
        for(int i=0;i<lineas.size();i++){
            //Agregar todos los nodos menos el raíz a cola Q
            if(i!=s){
                Q.offer(i+1);
            }
            //Pesos a "infinito"
            d[i]=99999999;
            //Padres a NULL
            pi[i]=-1;
        }
        
        //Distancia de raíz a 0
        d[s]=0;
        //Agregar nodo raíz a visitado
        S.offer(s+1);
        k=s+1;
        
        while(!Q.isEmpty()){
            //Extrae posición del nodo con el peso mínimo en ns
            r=extraeMinDijkstra(d,ns,s+1,S,Q);
            //Ciclo para exrtaer el nodo con posición r de Q
            r++;
            for(int j=0;j<Q.size();j++){
                if(Q.get(j)==r){
                    Q.remove(j);
                }
            }
            //Agregar r a Visitados
            S.offer(r);
            r--;
            
            //Operación de relajación
            for(int i=0;i<lineas.get(s).size();i++){
                //Actualizar distancias y padres
                if(d[lineas.get(s).get(i).nodo-1] > d[s]+lineas.get(s).get(i).peso){
                    d[lineas.get(s).get(i).nodo-1] = d[s]+lineas.get(s).get(i).peso;
                    pi[lineas.get(s).get(i).nodo-1]=s+1;
                    
                }
            }
            
            s=r;
        }
        
        //Ciclo para integrar los caminos e imprimirlos
        for(int i=1;i<=pi.length;i++){
            recuperaCamino(i,k,pi);
            System.out.print("\n");
        }
    }
    ////////////////////Termina DIJKSTRA////////////////////
    
    
    
    ////////////////////Inicia KRUSKAL////////////////////
    //Inicia lista de arcos
    public  void inicia_listaArcos(List <Arco> arcos){
        //Variables de utilería
        int i,j,k;
        
        //Ciclo para iterar en filas
        i=0;
        while(i<lineas.size()){
            //ciclo para iterar e columnas
            j=0;
            while(j<lineas.get(i).size()){
                //variable k que indica el nodo "cabeza" de la conexión
                k=i+1;
                if(lineas.get(i).get(j).nodo >= k){
                    //Añade un arco a la lista de arcos "arcos" con info de u,v,w,set
                    Arco a = new Arco(k,lineas.get(i).get(j).nodo,lineas.get(i).get(j).peso,i);
                    arcos.add(a);
                }
                j++;
            }
            i++;
        }
    }
    
    //
    public void ordenaArcos(List<Arco> ns, int izq, int dcha){
        //Arco para poder cambiar los valores de nodos en intercambios
        Arco a = new Arco(0,0,0,0);
        //Variables de iteración
        int i=izq,j=dcha;
        //Variable para tomar el pivote
        int pivote;
        //Valor medio
        int div = (izq+dcha)/2;
        
        //Extrae el valor medio y lo vuelve pivote
        pivote=ns.get(div).w;
        
        //Ciclo de ordenamiento
        while(i<j){
            while(ns.get(i).w < pivote){
                i++;
            }
            while(pivote < ns.get(j).w){
                j--;
            }
            if(i<j){
                a.setU(ns.get(i).u);
                a.setV(ns.get(i).v);
                a.setW(ns.get(i).w);
                ns.get(i).setU(ns.get(j).u);
                ns.get(i).setV(ns.get(j).v);
                ns.get(i).setW(ns.get(j).w);
                ns.get(j).setU(a.u);
                ns.get(j).setV(a.v);
                ns.get(j).setW(a.w);
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
            ordenaArcos(ns,izq,j);
        if(i<dcha)
            ordenaArcos(ns,i,dcha);
    }
    
    //Llega un grafo de utilería, rellena el grafo con un nodo distinto en cabeza de cada lista en una lista contenedora
    public void MakeSet(List<List<Nodo>> ns){
        //Variables de utilería
        int i=0,h;
        //Ciclo que itera el numero de veces como existencia de nodos en el grafo
        while(i<lineas.size()){
            //h guarda el número de nodo "cabeza" en la fila
            h=i+1;
            //Se declara nueva lista
            List <Nodo> nodos = new ArrayList<>();
            //Se declara nuevo nodo de utilería
            Nodo e = new Nodo(h,0);
            //Se añade nodo a la lista declarada en esta función (nodos)
            nodos.add(e);
            //Se añade la lista (nodos) a la lista de listas (ns)
            ns.add(nodos);
            i++;
        }
    }
    
    //Llega un numero de nodo y el grafo de utilería, encuentra al nodo y regresa la posición de la lista en la lista
    public int FindSet(int x,  List<List<Nodo>> set){
        //Variables de utilería
        int b=0,i=0,j,p=-1;
        //Ciclo que itera tantas veces como existan listas en set
        while(i<set.size()){
            j=0;
            //Variable que itera cada elemento de la lista seleccionada en set
            while(j<set.get(i).size()){
                //Si x está en la lista, b = la posición de la lista
                if(set.get(i).get(j).nodo==x){
                    b=i;
                }
                j++;
            }
            i++;
        }
        //Regresa la posición de la lista
        return b;
    }
    
    //función que pega una lista con otra, llegan las cabezas de las listas y el grafo de utilería
    public void Union(int x, int y, List<List<Nodo>> set){
        //Variables de utilería
        int i=0;
        //Se detecta cuál lista es mayor en tamaño y en ella se pega la de menor tamañp
        if(set.get(x).size()>=set.get(y).size()){
            //Ciclo para transferir los nodos
            while(i<set.get(y).size()){
                set.get(x).add(set.get(y).get(i));
                i++;
            }
            //Vaciar la lista pequeña
            set.get(y).clear();
        }
        else{
            //Ciclo para transferir los nodos
            while(i<set.get(x).size()){
                set.get(y).add(set.get(x).get(i));
                i++;
            }
            //Vaciar la lista pequeña
            set.get(x).clear();
        }
    }
    
    //Kruskal
    public void Kruskal(){
        //Lista de tipo Arco que se retorna de esta función
        List<Arco> A = new ArrayList<>();
        //Lista de arcos de utilería
        List<Arco> arcos = new ArrayList<>();
        //Se inicia la lista de arcos, guarda los arcos distintos (no dirigidos)
        inicia_listaArcos(arcos);
        //Grafo de utilería
        List<List<Nodo>> set = new ArrayList<>(); 
        //Asigna al grafo de listas, una lista que tiene un nodo distitno en cabeza de cada lista
        MakeSet(set);
        //Variables para usar la función de ordenamiento de arcos
        int izq = 0; int dcha = arcos.size()-1;
        //función que ordena la lista de arcos
        ordenaArcos(arcos,izq,dcha);
        //Variable de utilería
        int i=0,l=0;
        //Ciclo que itera al mismo numero de veces como nodos distintos en el grafo
        while(i<arcos.size()){
            //Si los nodos pertenecen a listas distintas, se añade el arco a A y se realiza la unión de listas
            if(FindSet(arcos.get(i).u,set)!=FindSet(arcos.get(i).v,set)){
                //Añadir un arco de la lista arcos a la lista A
                A.add(arcos.get(i));
                //Pegar una lista de nodos con otra
                Union(FindSet(arcos.get(i).u,set),FindSet(arcos.get(i).v,set),set);
            }
            i++;
        }
        
        i=0;
        //Ciclo para verificar cuantas listas no están vacías
        while(i<set.size()){
            //Si una lista no es vacia, se incrementa l
            if(!set.get(i).isEmpty()){
                l++;
            }
            i++;
        }
        
        //Si el grafo tiene una y solo una lista no vacía (es conexo), escribir
        if(l==1){
            //escribir los arcos almacenados en A
            escribeArcos(A);
        }
    }
    ////////////////////Termina KRUSKAL////////////////////
    
    
    
    ////////////////////Inicia Prim////////////////////
    //Función que extrae el valor entero del nodo con el menor peso asignado
    //Entra lista de arcos final A (árbol de expansión mínima) y cola de prioridad Q 
    public int extraeMin(List<Arco> A, LinkedList<Nodo> Q){
        //Variables para añadir el arco
        int p=999999999,k=0,j=0;
        //Ciclo para buscar el menor en Q
        for(int i=0; i<Q.size();i++){
            //Comparador de menores encontrados
            //Sl último registro  Encontrado es el número menor que está en Q
            if(Q.get(i).key<p){
                p=Q.get(i).key;
                k=Q.get(i).nodo;
                j=i;
            }
        }
        //Declara objeti tipo Arco
        Arco a = new Arco(Q.get(j).nodo,Q.get(j).pi,p,0);
        //añade arco a A
        A.add(a);
        //Elimina al nodo de la cola de prioridad Q
        Q.remove(j);
        //Regresa el valor entero del nodo
        return k;
    }
    
    //Función que busca un nodo en Q y regresa su posición en Q
    public int buscanenq(int n,LinkedList<Nodo> Q){
        //Variable que almacena la posición en Q, si no está, regresa un "NULL" (-1)
        int b=-1;
        //Ciclo para recorrer cada elemento de Q buscando el nodo n
        for(int i=0;i<Q.size();i++){
            //Si el nodo n está en Q, iguala b a la posición en Q
            if(Q.get(i).nodo==n){
                 b=i;
            }
        }
        //Regresa la posición (encontrada) o -1
        return b;
    }
    
    //Función que busca en la lista de arcos (arcos) la informacipon del arco que une a u y v
    //Entra la lista de TODOS los arcos distintos del grafo y dos valores de nodos, uno que está en Q y el otro no
    public int encuentraArco(List<Arco> arcos, int u, int v){
        //Variable para almacenar el peso en el arco, si no lo encuentra, regresa peso 0
        int w=0;
        //Recorre todos los arcos y verifica si existe un arco (no dirigido) entre los nodos
        for(int i=0; i<arcos.size();i++)
            //Si existe el arco, almacena el peso en w
            if((arcos.get(i).u==u && arcos.get(i).v==v) || (arcos.get(i).u==v && arcos.get(i).v==u))
                w=arcos.get(i).w;
        //Regresa el peso del arco (encontrado) o 0
        return w;
    }
    
    
    //Algoritmo de prim, llega el nodo raíz (y en todo caso, el grafo)
    public void Prim(int r){
        //Variables de utilería
        int b,e;
        //Cola de prioridad
        LinkedList<Nodo> Q = new LinkedList<>();
        //Árbol de expansión mínima
        List<Arco> A = new ArrayList<>();
        //Lista que contiene todos los arcos distintos en el grafo
        List<Arco> arcos = new ArrayList<>();
        //Se inicia la lista de arcos, guarda los arcos distintos (no dirigidos)
        inicia_listaArcos(arcos);
        //Ciclo de inicialización de Q (por default ya tiene atributos key=99999 [infinito] y pi=-1 [NULL o NILL])
        //Se agrega un nodo en Q por cada nodo en el grafo
        for(int i=1;i<=lineas.size();i++){
            Nodo nodo = new Nodo(i,0);
            Q.offer(nodo);
        }
        
        //El nodo raíz no tiene peso ni padre, key=0 y pi=-1 (se queda en valor default)
        Q.get(r-1).setKey(0);
        
        //Ciclo que itera mientrass Q tenga nodos almacenados
        while(!Q.isEmpty()){
            //Variable enera en la que se almacena el valor del nodo con menor peso extraído en extraeMin
            int u = extraeMin(A,Q);
            //Ciclo que recorre todos los nodos adyacentes del valor u extraído
            for(int i=0;i<lineas.get(u-1).size();i++){
                //Se almacena el peso entre ambos nodos
                e=encuentraArco(arcos,u,lineas.get(u-1).get(i).nodo);
                //Se almacena la posición del nodo que está en Q (el otro ya salió)
                b=buscanenq(lineas.get(u-1).get(i).nodo,Q);
                //Si el nodo "v", adyacente a u, está en Q y El peso del arco es menor al peso almacenado en v.key
                //Se actualiza el peso y se asigna a u como padre de v dentro de la cola de prioridad Q
                if(b!=-1 && e<Q.get(b).key){
                    Q.get(b).setKey(e);
                    Q.get(b).setPi(u);
                }
            }
        }
        //escribir los arcos almacenados en A
        escribeArcos(A);
    }
    ////////////////////Termina Prim////////////////////
    
    
    
    ////////////////////Inicia algoritmo viajero 1////////////////////
    //
    /*public int buscaViajero1(int vertice,List<Integer> arcos){
        int min=9999999,n=-1;
        boolean pill=false;
        
        
        for(int i=0;i<=lineas.get(vertice-1).size();i++){
            for(int j=0;j<=arcos.size();j++){
                if(lineas.get(vertice-1).get(i).nodo==arcos.get(j)){
                    pill=true;
                }
            }
            if(lineas.get(vertice-1).get(i).nodo!=vertice && !pill && lineas.get(vertice-1).get(i).peso<min){
                min=lineas.get(vertice-1).get(i).peso;
            }
        }
        return 0;
    }*/
    ////////////////////Termina algoritmo viajero 1////////////////////
    
    ////////////////////Inicia algoritmo viajero 2////////////////////
    ////////////////////Termina algoritmo viajero 2////////////////////
    
    //Funciones varias
    //Reconstruye árbol desde pilas
    public void recuperaCamino(int v, int k, int[] pi){
        int anterior = pi[v-1];
        if (v!=k){
            System.out.print(v + " -> ");
            recuperaCamino(anterior,k,pi); // vuelve al último del último
        }
        else{
            System.out.print(k);
        }
    }
    
    //Escribe los arcos que surgen de kruscal
    public void escribeArcos(List<Arco> A){
        System.out.print("\n¡¡¡Grafo conexo!!!\n");
        int i=0;
        System.out.print("Estructura de impresión\n");
        System.out.print("\nNodo u  ---- Peso w ----  Nodo v\n\n");
        while(i<A.size()){
            System.out.print(A.get(i).u + "  ----" + A.get(i).w + "----  " + A.get(i).v + "\n");
            i++;
        }
        System.out.print("\nTermina escribe arcos\n\n");
    }
    
    //Escribe el grafo de utilería
    public void escribeSet(List<List<Nodo>> set){
        int i,j;
        i=0;
        while(i<set.size()){
            System.out.print("Grupo " + i + ":\n");
            j=0;
            while(j<set.get(i).size()){
                System.out.print(set.get(i).get(j).nodo + "  ");
                j++;
            }
            System.out.print("\n");
            i++;
        }
    }
    
    //Escribe el grafo por nodo y su conectividad
    public void escribir(){
        int i,j;
        i=0;
        System.out.print("Tamaño de grafo: " + lineas.size() + "\n");
        while(i<lineas.size()){
            j=0;
            System.out.print("Tamaño de linea: " + lineas.get(i).size() + "\n");
            while(j<lineas.get(i).size()){
                System.out.print("Nodo: " + lineas.get(i).get(j).nodo
                        + "\t");
                System.out.print("Peso: " + lineas.get(i).get(j).peso
                        + "\n");
                j++;
            }
            i++;
        }
    }
    
    //Calcula el grado total del grafo
    public int calculaGrado(){
        List <Integer> gradosSalida = new ArrayList();
        List <Integer> gradosEntrada = new ArrayList();
        int gradoS=0,gradoE=0,gradoT,entrada;
        int i,j,k;
        
        //Cliclos para encontrar el grado de entrada
        i=0;
        while(i<lineas.size()){
            j=0;
            while(j<lineas.get(i).size()){
                j++;
            }
            gradosSalida.add(j);
            i++;
        }
        
        //Ciclo para obtener el grado de salida
        i=0;
        while(i<lineas.size()){
            j=0;
            entrada=0;
            while(j<lineas.size()){
                k=0;
                while(k<lineas.get(i).size()){
                    if(lineas.get(i).get(k).nodo==j+1){
                        entrada++;
                    }
                    k++;
                }
                j++;
            }
            gradosEntrada.add(entrada);
            i++;
        }
        
        i=0;
        while(i<gradosSalida.size()){
            System.out.print("Nodo: " + i + "\tSalen: "
                    + gradosSalida.get(i) + "\tEntran: " + gradosEntrada.get(i) + "\n");
            gradoS=gradoS+gradosSalida.get(i);
            gradoE=gradoE+gradosEntrada.get(i);
            i++;
        }
        //Grado total del grafo
        gradoT=gradoS+gradoE;
       
        return gradoT;
    }
    
}