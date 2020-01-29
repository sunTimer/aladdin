package offer;


import javafx.scene.effect.SepiaTone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class CloneRandomListNode {
    public RandomListNode Clone(RandomListNode pHead) {

        RandomListNode pCurr = pHead;
        while (pCurr != null) {
            RandomListNode tmp = pCurr.next;
            RandomListNode node = new RandomListNode(pCurr.label);
            node.next = tmp;
            pCurr.next = node;
            pCurr.next = node.next;
        }

        pCurr = pHead;
        while (pCurr != null) {
            if (pCurr.random != null) {
                pCurr.next.random = pCurr.random.next;
            }
            pCurr = pCurr.next.next;
        }

        RandomListNode newHead = pHead.next;
        pCurr = newHead;
        while (pCurr != null) {
            pCurr.next = pCurr.next.next;
            if (pCurr.next.next == null) {
                break;
            }
            pCurr = pCurr.next;
        }
        return newHead;
    }
}
