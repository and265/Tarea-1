class Expendedor{

    public static final int COCA = 1;

    public static final int SPRITE = 2;

    public static final int SNICKERS = 3;

    public static final int SUPER8 = 4;

    private int precioB;
    private int precioD;

    private Deposito<CocaCola> dCoca;
    private Deposito<Sprite> dSprite;
    private Deposito<Snickers> dSnickers;
    private Deposito<Super8> dSuper8;
    private Deposito<Moneda> dVuelto;

    public Expendedor(int n, int precioB, int precioD){
        this.precioB = precioB;
        this.precioD = precioD;
        this.dVuelto = new Deposito<Moneda>();
        dCoca = new Deposito<CocaCola>();
        dSprite = new Deposito<Sprite>();
        dSnickers = new Deposito<Snickers>();
        dSuper8 = new Deposito<Super8>();
        for(int i = 0; i < n; i++){
            dCoca.addObj(new CocaCola(100+i));
            dSprite.addObj(new Sprite(200 + i));
            dSnickers.addObj(new Snickers(300 + i));
            dSuper8.addObj(new Super8(400 + i));
        }
    }

    public Producto comprarProducto(Moneda mon, int sele) throws PagoInsuficienteException, PagoIncorrectoException, NoHayProductoException {
        Producto ret = null;
        int vuelto = 0;

        //Excepciones
        if(((sele==1 || sele==2) && mon.getValor() < precioB) || ((sele==3 || sele==4) && mon.getValor() < precioD)){
            throw new PagoInsuficienteException("El pago no es suficiente.");
        }
        else if(mon == null){
            throw new PagoIncorrectoException("No hay moneda para comprar.");
        }

        switch (sele) {
            case COCA -> {
                vuelto = mon.getValor() - precioB;
                ret = dCoca.getObj();
            }
            case SPRITE -> {
                vuelto = mon.getValor() - precioB;
                ret = dSprite.getObj();
            }

            case SNICKERS -> {
                vuelto = mon.getValor() - precioD;
                ret = dSnickers.getObj();
            }

            case SUPER8 -> {
                vuelto = mon.getValor() - precioD;
                ret = dSuper8.getObj();
            }
        }

        if(ret != null){
            vuelto /= 100;
            for(int i = 0; i < vuelto; i++){
                dVuelto.addObj(new Moneda100());
            }
            mon = null;
        }

        else{
            for(int i = 0; i < mon.getValor()/100; i++){
                dVuelto.addObj(new Moneda100());
            }
            mon = null;
            throw new NoHayProductoException("El producto solicitado no se encuentra disponible");
        }

        return ret;
    }

    public Moneda getVuelto(){
        return dVuelto.getObj();
    }
}
