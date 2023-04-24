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
