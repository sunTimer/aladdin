package linkedlist;

import org.junit.Test;

public class _1290 {

    public int getDecimalValue(ListNode head) {
        if (head == null) {
            return 0;
        }

        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int ret = 0;
        curr = head;
        while (curr != null) {
            len--;
            if (curr.val == 1) {
                ret += curr.val << len;
            }
            curr = curr.next;
        }
        return ret;
    }

    public int getDecimalValue2(ListNode listNode){
        int ret = 0;
        while (listNode != null) {
            ret = (ret << 1) + listNode.val;
            listNode = listNode.next;
        }
        return ret;
    }

    @Test
    public void test(){
        int[] a = {1,0,1};
        ListNode listNode = ListNode.buildList(a);
        int decimalValue2 = getDecimalValue2(listNode);
        System.out.println(decimalValue2);
    }
}
