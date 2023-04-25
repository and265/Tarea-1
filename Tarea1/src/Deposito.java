import java.util.ArrayList;

class Deposito<T>{
    private ArrayList<T> dep;

    public Deposito(){
        dep = new ArrayList<T>();
    }

    public void addObj(T bar){
        dep.add(bar);
    }

    public T getObj(){
        if (dep.size() == 0){
            return null;
        }
        else{
            T ret = dep.get(0);
            dep.remove(0);
            return ret;
        }
    }
}

