package tree.pk4;

import org.junit.Assert;

/**
 * <pre>
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * </pre>
 */
public class Trie {

    TrieNode root;


    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        insert(word, 0, root);
    }

    public void insert(String word, int keyIndex, TrieNode root) {

        TrieNode nextRoot = null;

        char key = word.charAt(keyIndex);
        for (int i = 0; i < root.nextIndex; i++) {
            if (key == root.childNode[i].key) {
                nextRoot = root.childNode[i];
                break;
            }
        }

        if (nextRoot == null) {
            nextRoot = new TrieNode();
            nextRoot.key = key;
            root.childNode[root.nextIndex] = nextRoot;
            root.nextIndex++;
        }

        if (keyIndex == word.length() - 1) {
            nextRoot.value = word;
        } else {
            keyIndex++;
            insert(word, keyIndex, nextRoot);
        }

    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return search(word, 0, root);
    }

    public boolean search(String word, int keyIndex, TrieNode root) {
        char key = word.charAt(keyIndex);
        for (int i = 0; i < root.nextIndex; i++) {
            if (root.childNode[i].key == key) {
                if (keyIndex == word.length() - 1) {
                    return root.childNode[i].value != null && root.childNode[i].value.equals(word);
                }
                keyIndex++;
                return search(word, keyIndex, root.childNode[i]);
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0, root);
    }

    public boolean startsWith(String prefix, int keyIndex, TrieNode root) {
        char key = prefix.charAt(keyIndex);
        for (int i = 0; i < root.nextIndex; i++) {
            if (root.childNode[i].key == key) {
                if (keyIndex == prefix.length() - 1) {
                    return true;
                }
                keyIndex++;
                return startsWith(prefix, keyIndex, root.childNode[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        Assert.assertTrue(trie.startsWith("app"));
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));     // 返回 true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class TrieNode {
    TrieNode[] childNode;
    int nextIndex;
    char key;
    String value;

    public TrieNode() {
        this.childNode = new TrieNode[26];
        this.nextIndex = 0;
    }
}