package cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LRUCache<K, V> implements Iterable<K>{

    Node<K, V> head;
    Node<K, V> tail;
    Map<K, Node<K, V>> storage;
    int capacity;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.pre = head;

        this.storage = new HashMap<>(capacity);
    }

    public Node<K, V> get(K k) {
        if (!storage.containsKey(k)) {
            return null;
        }

        Node<K, V> node = storage.get(k);
        unlink(node);
        append2Head(node);

        return node;
    }

    public void put(K k, V v) {
        if (storage.containsKey(k)) {
            Node<K, V> node;
            node = storage.get(k);
            unlink(node);
        }
        Node<K, V> node = new Node<>(k, v);
        storage.put(k, node);
        append2Head(node);

        if (storage.size() > capacity) {
            Node<K, V> toRemove = removeTail();
            storage.remove(toRemove.k);
        }
    }

    private void append2Head(Node<K, V> node) {
        Node<K, V> next = head.next;
        node.next = next;
        next.pre = node;

        head.next = node;
        node.pre = head;
    }

    private void unlink(Node<K, V> node) {
        Node<K, V> next = node.next;
        Node<K, V> pre = node.pre;
        pre.next = next;
        next.pre = pre;
        node.next = null;
        node.pre = null;
    }

    private Node<K, V> removeTail() {
        Node<K, V> node = tail.pre;
        Node<K, V> pre = node.pre;

        pre.next = tail;
        tail.pre = pre;

        node.next = null;
        node.pre = null;

        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}

class Node<K, V> {
    Node<K, V> next;
    Node<K, V> pre;

    public Node(K k, V v) {
        this.k = k;
        this.v = v;
    }

    K k;
    V v;

    @Override
    public String toString() {
        return "Node{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
