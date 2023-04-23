import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Deposito{
    private ArrayList<Bebida> bebidas;

    public Deposito(){
        bebidas = new ArrayList<Bebida>();
    }

    public void addBebida(Bebida beb){
        bebidas.add(beb);
    }

    public Bebida getBebida(){
        if (bebidas.size() == 0){
            return null;
        }
        else{
            Bebida ret = bebidas.get(0);
            bebidas.remove(0);
            return ret;
        }
    }
}

class DepositoM{
    private ArrayList<Moneda> monedas;
    public DepositoM(){
        monedas = new ArrayList<Moneda>();
    }

    public void addMoneda(Moneda mon){
        monedas.add(mon);
    }

    public Moneda getMoneda(){
        if (monedas.size() == 0){
            return null;
        }
        else{
            Moneda ret = monedas.get(0);
            monedas.remove(0);
            return ret;
        }
    }

}


abstract class Bebida{
    private int serie;

    public Bebida(int s){
        serie = s;
    }

    public int getSerie(){
        return serie;
    }

    public abstract String beber();
}

class Sprite extends Bebida{

    public Sprite(int s){
        super(s);
    }
    public String beber(){
        return "sprite";
    }
}

class CocaCola extends Bebida{
    public CocaCola(int s){
        super(s);
    }

    public String beber(){
        return "cocacola";
    }
}
class Expendedor{

    public static final int COCA = 1;

    public static final int SPRITE = 2;

    private int precio;
    private Deposito dCoca;
    private Deposito dSprite;

    private DepositoM dVuelto;
    public Expendedor(int n, int precio){
        this.precio = precio;
        this.dVuelto = new DepositoM();
        dCoca = new Deposito();
        dSprite = new Deposito();
        for(int i = 0; i < n; i++){
            dCoca.addBebida(new CocaCola(100+i));
            dSprite.addBebida(new Sprite(200 + i));
        }
    }

    public Bebida comprarBebida(Moneda mon, int sele){
        Bebida ret = null;
        int vuelto = 0;

        switch (sele){
            case COCA:{
                if(mon != null && mon.getValor() >= precio){
                    vuelto = mon.getValor() - precio;
                    ret = dCoca.getBebida();
                    break;
                }
            }
            case SPRITE:{
                if(mon != null && mon.getValor() >= precio) {
                    vuelto = mon.getValor() - precio;
                    ret = dSprite.getBebida();
                    break;
                }
            }
        }
        if(ret != null){
            vuelto /= 100;
            for(int i = 0; i < vuelto; i++){
                dVuelto.addMoneda(new Moneda100());
            }
            mon = null;
        }
        else{
            dVuelto.addMoneda(mon);
        }
        return ret;
    }

    public Moneda getVuelto(){
        return dVuelto.getMoneda();
    }
}

class Comprador{
    private String sonido;
    private int vuelto;

    public Comprador(Moneda m, int bebidaId, Expendedor ex){
        vuelto = 0;

        Bebida beb = ex.comprarBebida(m, bebidaId);
        if(beb != null) {
            sonido = beb.beber();
        }
        Moneda aux = ex.getVuelto();
        while (aux != null){
            vuelto += aux.getValor();
            aux = ex.getVuelto();
        }
    }

    public int cuantoVuelto() {return vuelto;}

    public String queBebiste() {return sonido;}

}
abstract class Moneda{

    protected int valor;
    protected int serie;

    public Moneda(){
    }
    public Moneda getSerie(){
        return this;
    }

    public abstract int getValor();

}

class Moneda100 extends Moneda{
    public Moneda100(){
        super();
        super.valor = 100;
    }
    public int getValor(){
        return super.valor;
    }
}

class Moneda500 extends Moneda{
    public Moneda500(){
        super();
        super.valor = 500;
    }
    public int getValor(){
        return super.valor;
    }
}

class Moneda1000 extends Moneda{
    public Moneda1000(){
        super();
        super.valor = 1000;
    }
    public int getValor(){
        return super.valor;
    }
}

class Moneda1500 extends Moneda{
    public Moneda1500(){
        super();
        super.valor = 1500;
    }
    public int getValor(){
        return super.valor;
    }
}
public class Main {
    public static void main(String[] args){
        Expendedor exp = new Expendedor(5,300);
        Moneda m = null;
        Comprador c=null;
        m = new Moneda1000();
        c = new Comprador(m,Expendedor.COCA,exp);
        System.out.println(c.queBebiste()+", "+c.cuantoVuelto());
    }

}