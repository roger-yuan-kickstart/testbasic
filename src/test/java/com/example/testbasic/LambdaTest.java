package com.example.testbasic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;

/**
 * @author Roger Yuan
 * @create 2020-08-09 10:46 p.m.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaTest {
    @Test
    public void testLambda1(){
        //普通写法
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Lambda测试1");
            }
        };
        r1.run();

        System.out.println("**************************");

        //Lambda写法
        Runnable r2 = () -> System.out.println("Lambda测试2");
        r2.run();
    }

    @Test
    public void test2(){
        //普通的写法
        Comparator<Integer> com1 = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = com1.compare(12, 22);
        System.out.println(compare1);

        System.out.println("***********************");

        //lambda写法（->）
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = com2.compare(12, 22);
        System.out.println(compare2);

        System.out.println("***********************");

        //方法引用的写法（::）
        Comparator<Integer> com3 = Integer::compare;
        int compare3 = com3.compare(12, 22);
        System.out.println(compare3);


    }
}
