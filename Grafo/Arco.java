/**
 *
 * @author diego
 */
public class Arco {
    int u;
    int v;
    int w;
    int set;

    public Arco(int u, int v, int w, int set) {
        this.u = u;
        this.v = v;
        this.w = w;
        this.set = set;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public int getSet() {
        return set;
    }

    public void setU(int u) {
        this.u = u;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setSet(int set) {
        this.set = set;
    }
    
}
