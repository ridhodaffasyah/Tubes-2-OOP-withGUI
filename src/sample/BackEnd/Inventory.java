package sample.BackEnd;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class Inventory<T> {
    protected SortedMap<T, Integer> things = new TreeMap<T,Integer>(Collections.reverseOrder());   //dengan T merupakan elemennya dan Integer merupakan banyak elemennya
    protected static int currentCapacity = 0;
    protected static int maxCapacity = 50;

    public Inventory(){}

    public SortedMap<T, Integer> getThings(){
        return things;
    }

    public static int getCurrentCapacity(){
        return currentCapacity;
    }

    public static int getMaxCapacity(){
        return maxCapacity;
    }

    public boolean deleteThing(T o) throws InventoryException{
        if (things.isEmpty()){
            String msg = "Inventory " + o.getClass().getName() + " kosong!";
            throw new InventoryException(msg);
        }else{
            boolean foundObj = false;
            for (T obj: things.keySet()){
                if (obj.equals(o)){
                    foundObj = true;
                    if (things.get(obj)>1){     //kalo masih ada barang kurangi dulu kuantitasnya
                        things.put(obj, things.get(obj)-1);
                    }else{
                        things.remove(obj);
                        break;
                    }
                }
            }
            return foundObj;
        }
    }

    public void addThing(T o) throws InventoryException{
        if (getMaxCapacity()-getCurrentCapacity()==0){
            String msg = "Inventory " + o.getClass().getName() + " penuh!";
            throw new InventoryException(msg);
        }else{
            if (things.containsKey(o)){
                things.put(o, things.get(o)+1);
            }else{
                things.put(o, 1);
            }
        }
    }
}
