package tree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryTreeNode {


    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int val) {
        this.val = val;
    }
}
