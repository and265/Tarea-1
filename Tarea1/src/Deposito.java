import java.util.ArrayList;

class Deposito{
    private ArrayList<Bebida> bebidas;

    public Deposito(){
        bebidas = new ArrayList<Bebida>();
    }

    public void addBebida(Bebida beb){
        bebidas.add(beb);
    }

    public Bebida getBebida(){
        if (bebidas.size() == 0){
            return null;
        }
        else{
            Bebida ret = bebidas.get(0);
            bebidas.remove(0);
            return ret;
        }
    }
}

