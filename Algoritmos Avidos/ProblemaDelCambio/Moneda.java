package ProblemaDelCambio;
/**
 *
 * @author diego
 */
public class Moneda {
    //Atrubitos
    //El valor monetario por entidad
    double valor;
    //La cantidad de entidades existentes en el sistema
    int cantidad;

    //Constructor, recibe los dos valores atributos de Moneda
    public Moneda(double valor) {
        this.valor = valor;
        this.cantidad = 0;
    }

    //Getters de los atributos
    public double getValor() {
        return valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    //Setters de los atributos
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
