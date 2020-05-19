package lambda.stream;

import lambda.model.Artist;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertEquals;

public class Demo {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("test")).start();
    }

    List<Artist> artists;

    @Before
    public void before() {
        artists = asList(new Artist("zhoujielun", Collections.emptyList(), "taiwan"),
                new Artist("zhouhuajian", Collections.emptyList(), "taiwan"),
                new Artist("caiyilin", Collections.emptyList(), "taiwan"),
                new Artist("huangxiaoming", Collections.emptyList(), "qingdao"));
    }

    @Test
    public void test1() {
        int count = 0;
        for (Artist artist : artists) {
            if (artist.isFrom("taiwan")) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * for 循环其实是一个封装了迭代的语法糖
     */
    @Test
    public void test2() {
        int count = 0;
        Iterator<Artist> iterator = artists.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isFrom("taiwan")) {
                count++;
            }
        }
        System.out.println(count);
    }

    @Test
    public void test3() {
        long count = artists.stream().filter(new Predicate<Artist>() {
            @Override
            public boolean test(Artist artist) {
                System.out.println(artist);
                return artist.isFrom("taiwan");
            }
        }).count();
        System.out.println(count);
    }

    /**
     * test4和test3是等价的
     */
    @Test
    public void test4() {
        long count = artists.stream().filter(artist -> {
            System.out.println(artist);
            return artist.isFrom("taiwan");
        }).count();
        System.out.println(count);
    }

    /**
     * test5
     */
    @Test
    public void test5() {
        List<String> collected = Stream.of("a", "b", "c").collect(toList());
        assertEquals(asList("a", "b", "c"), collected);
        List<String> beginningWithNumbers
                = Stream.of("a", "1abc", "abc1")
                .filter(value -> isDigit(value.charAt(0)))
                .collect(toList());

        assertEquals(beginningWithNumbers, asList("1abc"));
    }

    @Test
    public void test6() {
        List<String> collected = Stream.of("abc", "fsdfs").collect(toList());

        collected.stream().map(i -> new StringBuilder(i)).forEach(System.out::println);
    }

    @Test
    public void test7() {
        IntStream.range(0, 5).forEach(System.out::println);
    }
}
