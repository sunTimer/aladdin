package linkedlist;

public class _160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenOfListA = 0;
        ListNode tmpA = headA;
        while (tmpA != null) {
            lenOfListA++;
            tmpA = tmpA.next;
        }

        int lenOfListB = 0;
        ListNode tmpB = headB;
        while (tmpB != null) {
            lenOfListB++;
            tmpB = tmpB.next;
        }

        tmpA = headA;
        tmpB = headB;

        while (lenOfListA > lenOfListB) {
            tmpA = tmpA.next;
            lenOfListA--;
        }

        while (lenOfListA < lenOfListB) {
            tmpB = tmpB.next;
            lenOfListB--;
        }

        while (tmpA != null) {
            if (tmpA == tmpB) {
                return tmpA;
            }
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }
        return null;
    }
}
