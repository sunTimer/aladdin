package io.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SocketDemo {

    public static void main(String[] args) throws IOException {

        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        lruCache.put(5, 5);
        lruCache.put(6, 6);

        for (Integer integer : lruCache) {
            System.out.println(integer);
        }
    }

}


class LRUCache implements Iterable<Integer> {

    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

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

        unlink(node);
        appendHead(node);
        return node.value;
    }


    public void unlink(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.next = null;
        node.pre = null;
    }

    public Node removeTail() {
        Node pre = tail.pre;
        pre.pre.next = tail;
        tail.pre = pre.pre;

        pre.next = null;
        pre.pre = null;
        return pre;
    }

    public void appendHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
            unlink(oldNode);
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        appendHead(newNode);
        if (this.map.size() > this.capacity) {
            Node node = removeTail();
            map.remove(node.key);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            Node head0 = head.next;

            @Override
            public boolean hasNext() {
                return head0 != tail;
            }

            @Override
            public Integer next() {
                int value = head0.value;
                head0 = head0.next;
                return value;
            }
        };
    }
}


class Node {
    Node next;
    Node pre;

    int key;
    int value;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        next = null;
        pre = null;
    }
}