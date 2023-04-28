abstract class Moneda implements Comparable<Moneda>{

    protected int valor;
    protected int serie;

    public Moneda(){
    }
    public Moneda getSerie(){
        return this;
    }

    public abstract int getValor();

    @Override
    public int compareTo(Moneda o) {
        return (this.getValor() - o.getValor());
    }
}