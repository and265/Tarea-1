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