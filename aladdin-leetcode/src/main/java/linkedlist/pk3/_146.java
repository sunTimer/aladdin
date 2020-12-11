package linkedlist.pk3;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _146 {

    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 2);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 3);
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(2));
    }
}


class LRUCache {

    Map<Integer, Node> map;

    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;

        head.pre = null;
        tail.next = null;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        remove(node);

        addLast(node);
        return node.value;
    }


    public void put(int key, int value) {
        Node node = map.get(key);

        if (node == null) {
            if (this.capacity == map.size()) {
                Node removed = head.next;
                remove(removed);
                map.remove(removed.key);
            }
        } else {
            remove(node);
        }
        node = new Node(key, value);

        map.put(key, node);
        addLast(node);
    }

    public void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.next = null;
        node.pre = null;
    }

    public void addLast(Node node) {
        Node pre = tail.pre;
        pre.next = node;
        node.pre = pre;

        node.next = tail;
        tail.pre = node;
    }
}


class Node {
    int key;
    int value;
    Node next;
    Node pre;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}