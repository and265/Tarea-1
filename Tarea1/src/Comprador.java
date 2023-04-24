class Comprador{
    private String sonido;
    private int vuelto;

    public Comprador(Moneda m, int bebidaId, Expendedor ex) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        vuelto = 0;
        if( m ==  null){
            throw new PagoIncorrectoException("Se intento comprar sin moneda");
        }
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
