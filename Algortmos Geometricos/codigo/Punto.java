package algortmos.geometricos;
/**
 *
 * @author diego
 */
public class Punto {
    //Atributos
    //Numerado según el ángulo
    public int n;
    //Componente x del punto en el plano
    public int x;
    //Componente y del punto en el palno
    public int y;
    //El ángulo entre el 
    public double angulo;
    
    //Constructor
    public Punto(int x, int y) {
        this.n = 0;
        this.x = x;
        this.y = y;
        this.angulo = 0;
    }
    
    //Getters
    public int getN() { return n; }

    public int getX() { return x; }

    public int getY() { return y; }

    public double getAngulo() { return angulo; }
    
    //Setters
    public void setN(int n) { this.n = n; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setAngulo(double angulo) { this.angulo = angulo; }
    
}
