class Expendedor{

    public static final int COCA = 1;

    public static final int SPRITE = 2;

    private int precio;
    private Deposito<CocaCola> dCoca;
    private Deposito<Sprite> dSprite;

    private Deposito<Moneda> dVuelto;
    public Expendedor(int n, int precio){
        this.precio = precio;
        this.dVuelto = new Deposito<Moneda>();
        dCoca = new Deposito<CocaCola>();
        dSprite = new Deposito<Sprite>();
        for(int i = 0; i < n; i++){
            dCoca.addObj(new CocaCola(100+i));
            dSprite.addObj(new Sprite(200 + i));
        }
    }

    public Bebida comprarBebida(Moneda mon, int sele) throws PagoInsuficienteException, PagoIncorrectoException, NoHayProductoException {
        Bebida ret = null;
        int vuelto = 0;

        //Excepciones
        if(mon.getValor() < precio){
            throw new PagoInsuficienteException("El pago no es suficiente.");
        }
        else if(mon == null){
            throw new PagoIncorrectoException("No hay moneda para comprar.");
        }

        switch (sele) {
            case COCA -> {
                vuelto = mon.getValor() - precio;
                ret = dCoca.getObj();
            }
            case SPRITE -> {
                vuelto = mon.getValor() - precio;
                ret = dSprite.getObj();
            }
        }

        if(ret != null){
            vuelto /= 100;
            for(int i = 0; i < vuelto; i++){
                dVuelto.addObj(new Moneda100());
            }
            mon = null;
        }

        return ret;
    }

    public Moneda getVuelto(){
        return dVuelto.getObj();
    }
}