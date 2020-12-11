import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class test {

    @Test
    public void test22(){
        Integer.valueOf(100);
    }

    @Test
    public void testProcessBuilder() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = processBuilder.command();
        Map<String, String> environment = processBuilder.environment();
        System.out.println(environment);
        for (String s : command) {
            System.out.println(s);
        }
    }

    @Test
    public void test() {

        LinkedList<Integer> list = new LinkedList<>();
        list.remove(3);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.incrementAndGet();
        System.out.println(reverse(435));
    }

    public int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            flag = false;
        }

        String s = String.valueOf(x);
        char[] chars;
        if (flag) {
            chars = s.toCharArray();
        } else {
            chars = s.substring(1).toCharArray();
        }
        swap(chars, 0, chars.length - 1);

        String reversed = new String(chars);
        long l = Long.parseLong(reversed);
        if (flag) {
            if (l > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) l;
        } else {
            if (-l < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) -l;
        }
    }

    private void swap(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    @Test
    public void test2() {
        System.out.println(reverse2(Integer.MAX_VALUE));
    }

    public int reverse2(int i) {
        int sum = 0;
        while (i != 0) {
            int h = i % 10;
            int tmp = sum * 10 + h;
            long tmpL = (long) sum * 10 + h;
            if (tmp != tmpL) {
                return 0;
            }
            sum = tmp;
            i = i / 10;
        }
        return sum;
    }


    @Test
    public void testAdd() {
        int a = 1;
        int add = add(a);
        System.out.println(a);
        System.out.println(add);
    }


    public int add(int a) {
        a = a + 1;

        return a;
    }


    public String[] getWordsChain(String[] words) {
        Map<Character, List<String>> map = new HashMap<>();
        for (String word : words) {
            List<String> strings = map.computeIfAbsent(word.charAt(0), k -> new ArrayList<>());
            strings.add(word);
        }

        List<String> ret = new ArrayList<>();
        boolean isFirst = true;
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        String currWord;
        char ch = ' ';

        while (!wordList.isEmpty()) {
            if (isFirst) {
                currWord = wordList.get(0);
                ret.add(currWord);
                ch = currWord.charAt(currWord.length() - 1);
                map.get(currWord.charAt(0)).remove(currWord);

                wordList.remove(currWord);

                isFirst = false;
                continue;
            }

            List<String> strings = map.get(ch);
            if (strings != null && strings.size() > 0) {
                currWord = strings.get(0);
                strings.remove(currWord);
                wordList.remove(currWord);
                ret.add(currWord);
                ch = currWord.charAt(currWord.length() - 1);
            } else {
                if (ch == 'z') {
                    ch = 'a';
                } else {
                    ch = (char) (ch + 1);
                }
            }
        }
        String[] a = new String[ret.size()];
        return ret.toArray(a);
    }

    @Test
    public void testdemo() {


        String[] args = {"abs", "fgg", "solute", "hello", "mi", "di", "po"};
        String[] demo = getWordsChain(args);
        System.out.println("输入：");
        for (String arg : args) {
            System.out.print(arg + "， ");
        }
        System.out.println();

        System.out.println("输出：");
        for (String s : demo) {
            System.out.print(s + "， ");
        }

    }

}

class User {

}

