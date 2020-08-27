package com.example.testbasic;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamTests {

    /**
     *  Arrays.stream()
     */
    @Test
    public void testStream1() {
        Integer[] array = new Integer[]{3,4,8,16,19,27,23,99,76,232,33,96};
        //Arrays.stream()方法来使用Stream
        //找出array中大于20的个数
        long count = Arrays.stream(array).filter(i -> i>20).count();
        System.out.println(count);
    }

    /**
     * Stream.of()
     * 当面对数组时除了可以使用Arrays.stream()方法外，还可以使用Stream将需要的数组转成Stream。
     * 这个方法不但支持传入数组，将数组转成Stream，也支持传入多个参数，将参数最终转成Stream
     */
    @Test
    public void testStream2(){
        Integer[] array = new Integer[]{3,4,8,16,19,27,23,99,76,232,33,96};
        long count = Stream.of(array).filter(i -> i > 20).count();
        long sum = Stream.of(12, 77, 59, 3, 654).filter(i -> i>20).mapToInt(Integer::intValue).sum();
        System.out.println(count);
        System.out.println(sum);
    }

    /**
     * Stream.generate()
     * Stream接口有两个用来创建无限Stream的静态方法。generate()方法接受一个参数函数，
     * 可以使用类似如下代码来创建一个你需要的Stream。
     * 运行结果：[test, test, test, test, test, test, test, test, test, test]
     */
    @Test
    public void testStream3(){
        //普通写法
//        Stream<String> stream = Stream.generate(new Supplier<String>() {
//            @Override
//            public String get() {
//                return "test";
//            }
//        });

        //lambda写法
        Stream<String> stream = Stream.generate(()-> "test").limit(10);

        //普通写法
//        String[] strArr = stream.toArray(new IntFunction<String[]>() {
//            @Override
//            public String[] apply(int value) {
//                return new String[10];
//            }
//        });

        //构造器引用写法：
        String[] strArr = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(strArr));
    }

    /**
     * Stream接口的另一用来创建无限Stream的静态方法就是iterate()方法。iterate()方法也是接受一个参数函数，
     * 可以用类似如下代码来创建一个你需要的Stream。（从0开始每10叠加）
     *
     * 运行结果[0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
     */
    @Test
    public void testStream4(){
        //普通写法
//        Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, new UnaryOperator<BigInteger>() {
//            @Override
//            public BigInteger apply(BigInteger bigInteger) {
//                return bigInteger.add(BigInteger.TEN);
//            };
//        }).limit(10);
        //lambda写法：
        Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10);

        //普通写法
//        BigInteger[] bigIntArr = bigIntStream.toArray(new IntFunction<BigInteger[]>() {
//            @Override
//            public BigInteger[] apply(int value) {
//                return new BigInteger[10];
//            }
//        });

        //构造器引用的写法
        BigInteger[] bigIntArr = bigIntStream.toArray(BigInteger[]::new);
        System.out.println(Arrays.toString(bigIntArr));
    }


    /**
     * flatMap的方法
     */
    @Test
    public void testStream5(){
        List<Integer> oneList = Lists.newArrayList();
        List<Integer> twoList = Lists.newArrayList();
        oneList.add(34);
        oneList.add(23);
        oneList.add(87);
        twoList.add(29);
        twoList.add(48);
        twoList.add(94);

        Map<String, List<Integer>> testMap = new HashMap<>();
        testMap.put("1", oneList);
        testMap.put("2", twoList);

        List<Stream<Integer>> list = testMap.values().stream().map(new Function<List<Integer>, Stream<Integer>>() {
            @Override
            public Stream<Integer> apply(List<Integer> integers) {
                return integers.stream();
            }

            ;
        }).collect(Collectors.toList());

    }

}
