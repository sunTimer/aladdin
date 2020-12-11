public class Dict<K,V> {

    Dictht<K,V>[] ht;

    Dictype type;

    int rehashindex;

    int iterators;

}

class Dictype{

}

class Dictht<K,V>{

    DictEntry<K,V>[] tables;

    long size;

    long used;
}

class DictEntry<K,V>{
    K key;
    V value;
    DictEntry<K,V> next;
}