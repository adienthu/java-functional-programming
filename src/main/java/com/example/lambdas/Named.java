package com.example.lambdas;

/**
 * Created by adityad on 8/7/17.
 */

public interface Named {
    default String getName() {
        return "John (Named)";
    }

    static String getText() {
        return "FooBar";
    }
}
