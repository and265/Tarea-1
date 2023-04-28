class Comprador{
    private String sonido;
    private int vuelto;

    public Comprador(Moneda m, int productoId, Expendedor ex) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        vuelto = 0;
        if( m ==  null){
            throw new PagoIncorrectoException("Se intento comprar sin moneda");
        }
        Producto p = ex.comprarProducto(m, productoId);
        if(p != null) {
            sonido = p.consumir();
        }
        Moneda aux = ex.getVuelto();
        while (aux != null){
            vuelto += aux.getValor();
            aux = ex.getVuelto();
        }
    }

    public int cuantoVuelto() {return vuelto;}

    public String queConsumiste() {return sonido;}

}
