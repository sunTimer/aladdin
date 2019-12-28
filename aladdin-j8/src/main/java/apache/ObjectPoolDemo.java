package apache;

import org.apache.commons.pool.impl.GenericObjectPool;

public class ObjectPoolDemo {

    public static void main(String[] args) {
        GenericObjectPool<Connection> genericObjectPool = new GenericObjectPool<>();
    }
}


class Connection{

}