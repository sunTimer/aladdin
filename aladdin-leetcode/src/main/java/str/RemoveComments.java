package str;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {

    public List<String> removeComments(String[] source) {

        String lineFlag = "//";
        String blockFlag_Start = "/*";
        String blockFlag_End = "*/";

        boolean[] tmp = new boolean[source.length];

        boolean isBlockEnd = true;
        for (int i = 0; i < source.length; i++) {
            String line = source[i];
            if (line.trim().startsWith(lineFlag)) {
                tmp[i] = true;
                continue;
            }
            if (line.startsWith(blockFlag_Start)) {
                tmp[i] = true;

                if (line.endsWith(blockFlag_End)) {
                    isBlockEnd = true;
                } else {
                    isBlockEnd = false;
                }
                continue;
            }

            if (line.endsWith(blockFlag_End)) {
                tmp[i] = true;
                isBlockEnd = true;
                continue;
            }

            if (!isBlockEnd) {
                tmp[i] = true;
            }

        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            if (!tmp[i]) {
                System.out.println(source[i]);
                res.add(source[i]);
            }
        }
        return res;
    }

    @Test
    public void test() {
        String[] sources = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        removeComments(sources);
    }
}
