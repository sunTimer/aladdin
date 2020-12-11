package array.pk2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class YangHui {

    @Test
    public void test(){
        List<List<Integer>> lists = generate(5);
        System.out.println(lists);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> pre = null;
        for (int i = 0; i < numRows; i++) {
            List<Integer> tmpRet = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    tmpRet.add(1);
                } else {
                    tmpRet.add(pre.get(j) + pre.get(j - 1));
                }
            }
            pre = tmpRet;
            ret.add(tmpRet);
        }
        return ret;
    }
}
