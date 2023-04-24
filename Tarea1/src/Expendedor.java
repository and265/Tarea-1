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

    public Bebida comprarBebida(Moneda mon, int sele) throws PagoInsuficienteException, PagoIncorrectoException, NoHayProductoException {
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
        if(mon.getValor() < precio){
            throw new PagoInsuficienteException("El pago no es suficiente.");
        }
        else if(mon == null){
            throw new PagoIncorrectoException("No hay moneda para comprar.");
        }
        else if(ret == null){
            throw new NoHayProductoException("No hay producto en el dispensador.");
        }
        return ret;
    }

    public Moneda getVuelto(){
        return dVuelto.getMoneda();
    }
}