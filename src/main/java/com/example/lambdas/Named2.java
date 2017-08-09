package com.example.lambdas;

/**
 * Created by adityad on 8/7/17.
 */

public interface Named2 {
    default String getName() {
        return "John (Named2)";
    }
}
