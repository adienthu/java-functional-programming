package com.example.lambdas;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adityad on 8/7/17.
 */

public class Foo {
    private String mTemp = "temp";

    private static void printArray(String[] strings) {
        for (String s : strings) {
            System.out.print(s + " ");
        }
    }

    public void m1() {
        String[] strings = new String[]{"pp", "aaa", "cccc"};

        Arrays.sort(strings, (s, t1) -> {
            if (s.length() > t1.length())
                return -1;
            else if(s.length() < t1.length())
                return 1;
            else
                return 0;
        });

        printArray(strings);
    }

    public void m2() {
        String[] strings = new String[]{"pp", "aaa", "cccc"};
        List<String> list = Arrays.asList(strings);
        list.forEach(s -> System.out.print(s + " "));
    }

    public void m3() {
        String[] strings = new String[]{"pp", "aaa", "cccc"};
        List<String> list = Arrays.asList(strings);
        list.forEach(s -> System.out.print(s + this.mTemp + " "));
    }

    public static void repeatMessage(String msg, int count) {
        Runnable r = () -> {
                for (int i = 0;i < count; i++) {
                    System.out.println(msg);
                    Thread.yield();
                }
        };
//        count--; // Modification of count means its not longer effectively final
        new Thread(r).start();
    }
}
