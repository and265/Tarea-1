import java.util.ArrayList;

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
