public class List<T> {

    ListNode<T> head;
    ListNode<T> tail;
    int len;
    void free(){}

    List<T> listCreate(){
        List<T> tList = new List<T>();
        tList.head = null;
        tList.tail = null;
        tList.len = 0;
        return tList;
    }
}


class ListNode<T> {
    private ListNode pre;
    private ListNode next;
    private T value;
}