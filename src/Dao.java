

import java.util.ArrayList;

public interface Dao {

    Object getByID(int ID);
    void deleteByID(int ID);
    void add(Object obj);
    ArrayList getALL();
}