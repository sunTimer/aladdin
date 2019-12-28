import com.huaban.analysis.jieba.JiebaSegmenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;

public class Fenci {

    private static Map<String, Integer> countMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        segmenter.sentenceProcess(readFile()).forEach(i -> {
            if (countMap.containsKey(i)) {
                countMap.put(i, countMap.get(i) + 1);
            } else {
                countMap.put(i, 1);
            }
        });


        List<Map.Entry<String, Integer>> list = new ArrayList<>(countMap.entrySet());
        list.sort((o1, o2) -> (o2.getValue() - o1.getValue()));

        for(Map.Entry<String, Integer> t:list){
            System.out.println(t.getKey()+":"+t.getValue());
        }

    }

    private static String readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\shixu.NUCC\\Documents\\cb.txt"));
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        StringBuilder stringBuilder = new StringBuilder();
        while (fileChannel.read(byteBuffer) != -1) {
            Charset charset = Charset.forName("utf-8");
            byteBuffer.flip();
            stringBuilder.append(charset.decode(byteBuffer).array());
            byteBuffer.clear();
        }

        return stringBuilder.toString();
    }
}
