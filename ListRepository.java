import java.util.ArrayList;
public class ListRepository<ID,T> implements Repository<ID,T>{
    //Data member
    private ArrayList<Pair<ID,T>> list;
    //Default constructor
    public ListRepository(){
        list = new ArrayList<Pair<ID,T>>();
    }
    /*
     * Modifier method to add a new item
     * @param id ID of the item to be added
     * @param item Item to be added
     * @return true if added successfully, false otherwise
     */
    public boolean add(ID id, T item){
        Pair<ID,T> pair = new Pair<>(id,item);
        if(!list.contains(pair)){
            list.add(pair);
            return true;
        }
        else{return false;}
    }
    /*
     * Accessor method to find an item
     * @param id ID of the item to be found
     * @return Item if found, null otherwise
     */
    public T find(ID id){
        for(Pair<ID,T> p:list){
            if(p.getFirst()==id){
                return (T)p.getSecond();
            }
        }
        return null;
    }
    /*
     * Modifier method to remove an item
     * @param id ID of the item to be removed
     * @return Item if removed successfully, null otherwise
     */
    public T remove(ID id){
        T temp = null;
        Pair<ID,T> tbr = null;
        for(Pair<ID,T> p:list){
            if(p.getFirst()==id){
                temp = p.getSecond();
                tbr = p;
            }
        }
        if(tbr!=null){list.remove(tbr);}
        return temp;
    }
    /*
     * Accessor method to count the number of items
     * @return Number of items
     */
    public int count(){return list.size();}
    public ArrayList<T> all(){
        ArrayList<T> tList = new ArrayList(list.size());
        for(Pair<ID,T> p:list){
            tList.add(p.getSecond());
        }
        return tList;
    }
}