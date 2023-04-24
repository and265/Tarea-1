public class Main {
    public static void main(String[] args) {

        Expendedor exp = new Expendedor(0, 500);
        Moneda m = null;
        Comprador c = null;
        m = new Moneda500();
        System.out.println(c.queBebiste()+", "+c.cuantoVuelto());

        try {

            c = new Comprador(m, 1, exp);
            exp.comprarBebida(m, 1);
        } catch (PagoIncorrectoException e) {

            e.printStackTrace(System.out);

            System.out.println("El pago ingresado no es correcto");

        } catch (PagoInsuficienteException e) {

            e.printStackTrace(System.out);

            System.out.println("El pago ingresado no es suficiente");

        } catch (NoHayProductoException e) {

            System.out.println("El producto solicitado no se encuentra disponible");

        }


    }
}