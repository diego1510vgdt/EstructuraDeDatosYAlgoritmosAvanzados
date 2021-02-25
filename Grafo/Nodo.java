public class Nodo {
    int nodo;
    int peso;
    int key;
    int pi;
    
    public Nodo(int nodo, int peso) {
        this.nodo = nodo;
        this.peso = peso;
        this.key = 9999999;
        this.pi = -1;
    }

    public int getNodo() {
        return nodo;
    }

    public int getPeso() {
        return peso;
    }

    public int getKey() {
        return key;
    }

    public int getPi() {
        return pi;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setPi(int pi) {
        this.pi = pi;
    }
}
